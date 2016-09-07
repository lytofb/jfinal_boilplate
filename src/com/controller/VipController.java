package com.controller;

import com.bean.Createvip;
import com.dao.contact_merchant_user;
import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.service.VipService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.HashMap;

/**
 * Created by root on 2016/9/5.
 */
public class VipController extends ControllerExt {

    VipService vipService = new VipService();
    @Override
    public void onExceptionError(Exception e) {

    }

    Session session = SecurityUtils.getSubject().getSession();
    Long merchant_id = (Long) session.getAttribute("merchant_id");

    public void vipinfo() {
        render("vipinfo.html");
    }

    public void vipcharge() {
        render("vipcharge.html");
    }

    public void vipmakecharge() {
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

    public void addvip(){
        Createvip createvipInput = getBean(Createvip.class);
        vipService.createvip(createvipInput);
        renderText("success");
    }
}
