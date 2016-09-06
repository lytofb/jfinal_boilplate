package com.controller;

import com.bean.Createorder;
import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

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
        Long total = caculateById(createOrder);
        Long discount = 0L;
        Long realtotal = total-discount;
        Long ext_money = createOrder.getExt_money();
        String desc = createOrder.getDesc();
        //merchant_order
        String insert_merchant_order = "insert into %s " +
                "(is_member,merchant_name,total,realtotal,ext_money,desc) " +
                "values (?,?,?,?,?,?)";
        String insert_sql = String.format(insert_merchant_order,"z"+merchant_id+"_merchant_order");
        Db.update(insert_sql,is_member,merchant_name,total,realtotal,ext_money,desc);
        //
        Integer history_type = 1;
        String history_name = "建立订单";

        renderText("success");
    }

    private Long caculateById(Createorder createOrder){
        Long totalPrice = 0L;
        Long[] singleValue = createOrder.getSingle();
        Long suiteValue = createOrder.getSuite();
        String serviceQueryTemplate = "select * from %s where enabled = 1 and id = ?";
        for (int i = 0; i < singleValue.length; i++) {
            Long servicePrice = getPriceById(singleValue[i]);
            totalPrice = totalPrice+servicePrice;
        }
        totalPrice = totalPrice+getPriceById(suiteValue);
        return totalPrice;
    }

    private Long getPriceById(Long id){
        String serviceQueryTemplate = "select * from %s where enabled = 1 and id = ?";
        String sql = String.format(serviceQueryTemplate,"z"+merchant_id+"_service");
        Record service = Db.findFirst(sql, id);
        Long servicePrice = service.getLong("detail_price");
        return servicePrice;
    }
}
