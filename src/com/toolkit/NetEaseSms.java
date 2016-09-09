package com.toolkit;

import java.util.Date;
import java.util.HashMap;

public class NetEaseSms {
    static String appSecret = "505a0b8aa570";
    static String appkey = "7a21f3d9f90bf3e55418eaa3da2b13a5";
    static String url = "https://api.netease.im/sms/sendtemplate.action";

    public static String sendNETemplateSMS(String merchantname,String msg,String mobilenum){
        HashMap<String,String> headers = getheader();
        String datatemplate = "templateid=3029118&mobiles=[\"%s\"]&params=[\"%s\",\"%s\"]";
        String senddata = String.format(datatemplate,mobilenum,merchantname,msg);
        String result = HttpKitExt.post(url,senddata,headers);
        System.out.println(result);
        return result;
    }

    private static HashMap<String,String> getheader(){
        HashMap<String,String> headers = new HashMap<String, String>();
        Long current = new Date().getTime();
        String checkSum = CheckSumBuilder.getCheckSum(appSecret,"000000000",current.toString());
        headers.put("AppKey",appkey);
        headers.put("CurTime",current.toString());
        headers.put("CheckSum",checkSum);
        headers.put("Nonce","000000000");
        headers.put("Content-Type","application/x-www-form-urlencoded");
        headers.put("charset","utf-8");
        return headers;
    }

    public static void main(String[] args){
        sendNETemplateSMS("feiyuhehe","token","18525435925");
    }

}