package main.java.com.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import main.java.com.toolkit.LiveCheckSumBuilder;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 2017/4/5.
 */
public class LiveChannelManager {

    public final static String appKey = "8e1a80e2a54f551181be13cd73f9aabf";

    public final static String appSec = "402efa6e6c5b";

    public final static String nonce = "lytofbttc";

    private static ConcurrentHashMap<String,LiveChannelWrap> liveChannelConcurrentHashMap = new ConcurrentHashMap<String, LiveChannelWrap>();

    public static LiveChannel getLiveChannel(String cid){
        return liveChannelConcurrentHashMap.get(cid).getLiveChannel();
    }

    public static Boolean removeChannel(String cid){
        liveChannelConcurrentHashMap.remove(cid);
        return true;
    }

    public static LiveChannelWrap createChannel(String channelName,String account) throws IOException {
        LiveChannel liveChannel = makeChannel(channelName);
        LiveChannelWrap wrap = new LiveChannelWrap(liveChannel,account);
        liveChannelConcurrentHashMap.put(liveChannel.getCid(),wrap);
        return wrap;
    }

    public static LiveChannel retryChannel(String cid) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = LiveCheckSumBuilder.httpPostMaker(appKey, appSec, nonce, "address");
        StringEntity params = new StringEntity("{\"cid\":\""+cid+"\"}", Consts.UTF_8);
        httpPost.setEntity(params);
        HttpResponse response = client.execute(httpPost);
        String jsonresult = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(jsonresult);
        String code = jsonObject.getString("code");
        if (code.equals("200")){
            LiveChannel liveChannel = jsonObject.parseObject(jsonObject.getString("ret"), LiveChannel.class);
            liveChannel.setCid(cid);
            return liveChannel;
        }
        throw new RuntimeException();
    }

    public static LiveChannel makeChannel(String channelName) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = LiveCheckSumBuilder.httpPostMaker(appKey, appSec, nonce, "create");
//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("name", channelName));
//        urlParameters.add(new BasicNameValuePair("type", "0"));
//        httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
        StringEntity params = new StringEntity("{\"name\":\""+channelName+"\", \"type\":0}", Consts.UTF_8);
        httpPost.setEntity(params);
        HttpResponse response = client.execute(httpPost);
        String jsonresult = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(jsonresult);
        String code = jsonObject.getString("code");
        if (code.equals("200")){
            LiveChannel liveChannel = jsonObject.parseObject(jsonObject.getString("ret"), LiveChannel.class);
            return liveChannel;
        }
        throw new RuntimeException();
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(copymakeChannel());
//        System.out.println(makeChannel("hehe"));
        System.out.println(retryChannel("30f52dbd21ee4359bb537bf8aae53922"));
    }

}
