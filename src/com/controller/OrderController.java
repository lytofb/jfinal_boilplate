package com.controller;

import com.bean.Createorder;
import com.bean.Createvip;
import com.dao.data_preorder;
import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 2016/9/6.
 */
public class OrderController extends ControllerExt{
    @Override
    public void onExceptionError(Exception e) {

    }

    enum Servicetype{
        single,suite
    }

    Session session = SecurityUtils.getSubject().getSession();
    Long merchant_id = (Long) session.getAttribute("merchant_id");
    String merchant_name = (String) session.getAttribute("merchant_name");
    Long operator_id = (Long) session.getAttribute("operator_id");
    String operator_name = (String) session.getAttribute("operator_name");

    public void ordercreate() {

        String merchant_service_table = String.format("z%s_service",String.valueOf(merchant_id));
        String sqltemplate = "select * from %s where enabled = 1 and service_type = ?";
        List<Record> singleServiceList =
                Db.find(String.format(sqltemplate,merchant_service_table),Servicetype.single.toString());
        List<Record> suiteServiceList =
                Db.find(String.format(sqltemplate,merchant_service_table),Servicetype.suite.toString());
        addshowTitle(singleServiceList);
        addshowTitle(suiteServiceList);
        setAttr("singleServiceList",singleServiceList);
        setAttr("suiteServiceList",suiteServiceList);
        render("ordercreate.html");
    }

    private void addshowTitle(List<Record> singleServiceList){
        for (Record singleService:singleServiceList){
            String showtitle = String.format("%s（%s）",
                    singleService.getStr("detail_name"),
                    String.valueOf(singleService.getLong("detail_price")));
            singleService.set("showtitle",showtitle);
        }
    }

    public void createOrder(){
        Long[] singlevalue = getParaValuesToLong("createorder.single");
        Createorder createOrder = getBean(Createorder.class,true);
        createOrder.setSingle(singlevalue);

        Integer is_member = createOrder.getVipcardid()==null?0:1;
        ArrayList<Object> objects = caculateById(createOrder);
        Long total = (Long)objects.get(0);
        String content_detail_desc = (String) objects.get(1);
        Long discount = 0L;
        Long realtotal = total-discount;
        Long ext_money = createOrder.getExt_money();
        String desc = createOrder.getDesc();
        //merchant_order
        Record merchant_order = new Record();
        merchant_order.set("is_member",is_member);
        merchant_order.set("merchant_name",merchant_name);
        merchant_order.set("total",total);
        merchant_order.set("realtotal",realtotal+ext_money);
        merchant_order.set("content_detail_desc",content_detail_desc);
        merchant_order.set("ext_money",ext_money);
        merchant_order.set("updatetime",new Date());
        merchant_order.set("desc",desc);
        String merchant_order_tablename = "z"+merchant_id+"_merchant_order";
        Db.save(merchant_order_tablename,merchant_order);
//        String insert_merchant_order = "insert into %s " +
//                "(is_member,merchant_name,total,realtotal,ext_money,desc) " +
//                "values (?,?,?,?,?,?)";
//        String insert_sql = String.format(insert_merchant_order,"z"+merchant_id+"_merchant_order");
//        Db.update(insert_sql,is_member,merchant_name,total,realtotal,ext_money,desc);
        //
        Integer history_type = 1;
        String history_name = "建立订单";
        Long merchant_order_id = merchant_order.getLong("id");
        Record operate_history = new Record();
        operate_history.set("history_type",history_type);
        operate_history.set("history_name",history_name);
        operate_history.set("merchant_order_id",merchant_order_id);
        operate_history.set("operator_id",operator_id);
        operate_history.set("operator_name",operator_name);
        String merchant_operate_history_tablename = "z"+merchant_id+"_data_operate_history";
        Db.save(merchant_operate_history_tablename,operate_history);
        renderText("success");
    }

    public void index() {
        Integer pagenum = getParaToInt(0,1);
        String merchant_order_tablename = "z"+merchant_id+"_merchant_order";
        String sqlExpect = " from "+merchant_order_tablename+" where enabled=1 order by id desc";
        Page<Record> page = Db.paginate(pagenum, 20, "select *", sqlExpect);
        setAttr("page",page);
        render("accountbook.html");
    }

    public void preorder() {
        Integer pagenum = getParaToInt(0,1);
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("merchant_id",merchant_id);
        Page<data_preorder> page = data_preorder.dao.searchPaginate(pagenum, 20,paramMap,"order by order_time desc");
        setAttr("page",page);
        render("preorder.html");
    }

    public void updateorderstatus(){
        Integer is_payed = getParaToInt("is_payed");
        Long id = getParaToLong("id");
        String merchant_order_tablename = "z"+merchant_id+"_merchant_order";
        String sql = "update %s set is_payed = ? where id = ?";
        Db.update(String.format(sql,merchant_order_tablename),is_payed,id);

        Record merchant_order = Db.findById(merchant_order_tablename,id);
        if (merchant_order!=null){
            String talbleName = String.format("z%s_income",String.valueOf(merchant_id));
            Record income = new Record();
            income.set("income_total",merchant_order.getLong("realtotal"));
            income.set("payer_name",merchant_order.getStr("user_name"));
            income.set("payer_id",merchant_order.getLong("user_id"));
            income.set("card_id",merchant_order.getLong("user_id"));
            Db.save(talbleName,income);
            //record order confirm operation
            recordOperateHistory(is_payed,merchant_order.getLong("id"));
            renderText("success");
        } else {
            renderError(403);
        }
        //
    }

    private void recordOperateHistory(Integer is_payed,Long merchant_order_id){
        Record operate_history = new Record();
        Integer history_type = 1;
        String history_name;
        if (is_payed-1==0){
            history_name = "确认订单收款";
        } else {
            history_type = 0;
            history_name = "吊销收款";
        }
        operate_history.set("history_type",history_type);
        operate_history.set("history_name",history_name);
        operate_history.set("merchant_order_id",merchant_order_id);
        operate_history.set("operator_id",operator_id);
        operate_history.set("operator_name",operator_name);
        String merchant_operate_history_tablename = "z"+merchant_id+"_data_operate_history";
        Db.save(merchant_operate_history_tablename,operate_history);
    }

    private ArrayList<Object> caculateById(Createorder createOrder){
        ArrayList<Object> objects = new ArrayList<Object>();
        Long totalPrice = 0L;
        String detail_desc = "";
        objects.add(totalPrice);objects.add(detail_desc);
        Long[] singleValue = createOrder.getSingle();
        Long suiteValue = createOrder.getSuite();
        String serviceQueryTemplate = "select * from %s where enabled = 1 and id = ?";
//        for (int i = 0; i < singleValue.length; i++) {
//            Long servicePrice = getPriceById(singleValue[i]);
//            totalPrice = totalPrice+servicePrice;
//        }
//        totalPrice = totalPrice+getPriceById(suiteValue);
        for (int i = 0; i < singleValue.length; i++) {
            recordService(objects,singleValue[i]);
        }
        recordService(objects,suiteValue);
        return objects;
    }

    private void recordService(ArrayList<Object> objects,Long id){
        assert objects.size()==2;
        if (id==null){
            return;
        }
        String serviceQueryTemplate = "select * from %s where enabled = 1 and id = ?";
        String sql = String.format(serviceQueryTemplate,"z"+merchant_id+"_service");
        Record service = Db.findFirst(sql, id);
        Long servicePrice = service.getLong("detail_price");
        objects.set(0,(Long)objects.get(0)+servicePrice);
        objects.set(1,(Long)objects.get(1)+String.format("%s[%s]",service.getStr("detail_name"),service.get("detail_price").toString())+",");
    }

    private Long getPriceById(Long id){
        if (id==null){
            return 0L;
        }
        String serviceQueryTemplate = "select * from %s where enabled = 1 and id = ?";
        String sql = String.format(serviceQueryTemplate,"z"+merchant_id+"_service");
        Record service = Db.findFirst(sql, id);
        Long servicePrice = service.getLong("detail_price");
        return servicePrice;
    }
}
