package main.java.com.websocketx.buslevel;

import io.netty.channel.Channel;

/**
 * Created by root on 2017/3/21.
 */
public class ChannelAggregate {
    Channel ch;
    Long startTimestamp;
    String token;

    public Channel getCh() {
        return ch;
    }

    public void setCh(Channel ch) {
        this.ch = ch;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
