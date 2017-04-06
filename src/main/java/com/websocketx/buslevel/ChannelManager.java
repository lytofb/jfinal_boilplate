package main.java.com.websocketx.buslevel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by root on 2017/3/21.
 */
public class ChannelManager {

    // token-userid map
    private static ConcurrentHashMap<String,Long> tokenUserMap = new ConcurrentHashMap<String, Long>();

    private static ConcurrentHashMap<ChannelId,ChannelAggregate> channelMap = new ConcurrentHashMap<ChannelId, ChannelAggregate>();

    public static ConcurrentHashMap<ChannelId,ChannelAggregate> getChannelMap(){
        return channelMap;
    }

    public static String putChannelMap(Channel ch){
        ChannelId chid = ch.id();
        if (!channelMap.containsKey(chid)){
            ChannelAggregate ca = new ChannelAggregate();
            String token = generateToken();
            Long timestamp = new Date().getTime();
            ca.setCh(ch);
            ca.setStartTimestamp(timestamp);
            ca.setToken(token);
            channelMap.put(chid,ca);
//            tokenUserMap.put(token,chid.asLongText());
            return token;
        } else {
            return channelMap.get(chid).getToken();
        }
    }

    public static String generateToken(){
        Long timestamp = new Date().getTime();
        String token = timestamp.toString()+RandomStringUtils.randomAlphanumeric(5);
        return token;
    }

    public static ChannelAggregate getChannelByToken(String token){
//        String mappedid = tokenUserMap.get(token);
        Set<ChannelId> channelIdSet = getChannelMap().keySet();
        for(ChannelId channelid:channelIdSet){
            String channelidString = channelid.asLongText();
//            if(channelidString.equals(mappedid)){
                return channelMap.get(channelid);
//            }
        }
        return null;
    }

    public static Channel getChannel(ChannelId chid ){
        return channelMap.get(chid).getCh();
    }

    public static Channel getChannel(String token) {
        ChannelAggregate ca = getChannelByToken(token);
        if (ca!=null) {
            return ca.getCh();
        } else {
            return null;
        }
    }
}
