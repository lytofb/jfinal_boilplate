package main.java.com.toolkit;

/**
 * Created by root on 2017/4/5.
 */
import org.apache.http.client.methods.HttpPost;

import java.security.MessageDigest;
import java.util.Date;

public class LiveCheckSumBuilder {

    public static HttpPost httpPostMaker(String appkey,String appSecret,String nonce,String posttype) {
        String baseurl = "https://vcloud.163.com/app/channel/";
        String url = baseurl + posttype;
        if ("address".equals(posttype)) {
            url = "https://vcloud.163.com/app/address";
        }
        return channelhttpPostMaker(appkey,appSecret,nonce,url);
    }

    public static HttpPost channelhttpPostMaker(String appkey,String appSecret,String nonce,String url){
        HttpPost httpPost = new HttpPost(url);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = LiveCheckSumBuilder.getCheckSum(appSecret, nonce, curTime);//参考 计算CheckSum的java代码
        // 设置请求的header
        httpPost.addHeader("AppKey", appkey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");

        return httpPost;
    }

    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode("sha1", appSecret + nonce + curTime);
    }
    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}