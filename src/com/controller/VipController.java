package com.controller;

import com.bean.Createvip;
import com.jfinal.ext2.core.ControllerExt;
import com.service.VipService;

/**
 * Created by root on 2016/9/5.
 */
public class VipController extends ControllerExt {

    VipService vipService = new VipService();
    @Override
    public void onExceptionError(Exception e) {

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
