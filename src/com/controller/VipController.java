package com.controller;

import com.bean.Createvip;
import com.dao.contact_merchant_user;
import com.dao.data_preorder;
import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.TextRender;
import com.service.VipService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 2016/9/5.
 */
@RequiresPermissions("login")
public class VipController extends ControllerExt {

    VipService vipService = new VipService();
    @Override
    public void onExceptionError(Exception e) {

    }

    Session session = SecurityUtils.getSubject().getSession();
    Long merchant_id = (Long) session.getAttribute("merchant_id");

    public void index() {
        Integer pagenum = getParaToInt(0,1);
        keepPara();
        StringBuilder sqlExpect =
                new StringBuilder(" from contact_merchant_user where enabled=1 ");
        String tailorderby = " order by id desc";
        String vipname = getPara("vipname");
        String vipcardid = getPara("vipcardid");
        String oldvipcardid = getPara("oldcardid");
        ArrayList<Object> listpara = new ArrayList();
        listpara.add(merchant_id);
        listpara.add(vipname);
        listpara.add(vipcardid);
        listpara.add(oldvipcardid);
        String dynamicSql = buildSql(sqlExpect.toString(),listpara,tailorderby,"merchant_id","vip_name","card_id","old_card_id");
        Page<Record> page = Db.paginate(pagenum, 20, "select *", dynamicSql,turnArrayToList(listpara));
        setAttr("page",page);
        render("vipinfo.html");
    }

    public void vipconsumption(){
        Integer pagenum = getParaToInt(0,1);
        Long cardid = getParaToLong("cardid");
        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        String merchant_order_tablename = "z"+merchant_id+"_merchant_order";
        String sqlExpect = " from "+merchant_order_tablename+" where enabled=1 and card_id = ? order by id desc";
        Page<Record> page = Db.paginate(pagenum, 20, "select *", sqlExpect,cardid);
        setAttr("page",page);
        render("vipconsumption.html");
    }

    public void vipcharge() {
        render("vipcharge.html");
    }

    public void vipmakecharge() {
//        renderError(403,new TextRender("test403"));
        Integer cardid = getParaToInt("cardid");
        Long cashtotal = getParaToLong("cashtotal");
        String desc = getPara("desc");
        String talbleName = String.format("z%s_income",String.valueOf(merchant_id));

        HashMap<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("card_id",cardid);
        paramMap.put("merchant_id",merchant_id);
        contact_merchant_user cmu = contact_merchant_user.dao.searchFirst(paramMap);
        if (cmu!=null){
            Long card_total = cmu.getLong("card_total");
            card_total = card_total+cashtotal;
            cmu.set("card_total",card_total);
            cmu.update();

            Record income = new Record();
            income.set("income_total",cashtotal);
            income.set("payer_name",cmu.getStr("user_name"));
            income.set("payer_id",cmu.getLong("user_id"));
            income.set("card_id",cardid);
            income.set("description",desc);

            Db.save(talbleName,income);
        }
    }

    public void vipcreate() {
        render("vipcreate.html");
    }

    public void gettotal(){
        Long result = 0L;
        Long vipcardid = getParaToLong("vipcardid");
        if (vipcardid!=null){
            HashMap<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("card_id",vipcardid);
            paramMap.put("merchant_id",merchant_id);
            contact_merchant_user cmu = contact_merchant_user.dao.searchFirst(paramMap);
            if (cmu!=null){
                result = cmu.getLong("card_total");
            }
        }
        renderText(result.toString());

    }

    public void addvip(){
        Createvip createvipInput = getBean(Createvip.class);
        vipService.createvip(createvipInput);
        renderText("success");
    }

    private Object[] turnArrayToList(ArrayList<Object> objectList){
        Integer listlength = 0;
        ArrayList<Object> newarray = new ArrayList<Object>();
        for (Object o:objectList){
            if (o!=null&&!"".equals(o)){
                listlength++;
                newarray.add(o);
            }
        }
        Object[] objects = new Object[listlength];
        for (int i = 0; i < listlength; i++) {
            objects[i] = newarray.get(i);
        }
        return objects;
    }

    private String buildSql(String sqlbase,ArrayList<Object> paras,String orderby,String... columns){
        assert columns.length==paras.size();
        for (int i = 0; i < paras.size(); i++) {
            if (paras.get(i)==null||"".equals(paras.get(i))){
                continue;
            } else {
                sqlbase = sqlbase+" and "+columns[i]+" = ? ";
            }
        }
        return sqlbase+orderby;
    }
}
