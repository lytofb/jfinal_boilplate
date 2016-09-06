package com.service;

import com.bean.Createvip;
import com.dao.contact_merchant_user;
import com.toolkit.RequestKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.UUID;

/**
 * Created by root on 2016/9/6.
 */
public class VipService {
    public void createvip(Createvip vipservice){
        String uuid = RequestKit.generateuuid();
        Session session = SecurityUtils.getSubject().getSession();
        Long merchant_id = (Long) session.getAttribute("merchant_id");
        String merchant_name = (String) session.getAttribute("merchant_name");

        contact_merchant_user cmu = new contact_merchant_user();
        cmu.set("vip_name",vipservice.getName());
        cmu.set("car_num",vipservice.getCarnum());
        cmu.set("car_name",vipservice.getCarname());
        cmu.set("old_card_id",vipservice.getOriginalcardid());
        cmu.set("card_id",vipservice.getNewcardid());
        cmu.set("tel_num",vipservice.getTel_num());
        cmu.set("card_total",vipservice.getTotalnum());
        cmu.set("validate_key",uuid);
        cmu.set("merchant_id",merchant_id);
        cmu.set("merchant_name",merchant_name);
        cmu.save();
    }

}
