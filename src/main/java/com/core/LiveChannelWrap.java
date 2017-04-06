package main.java.com.core;

/**
 * Created by root on 2017/4/5.
 */
public class LiveChannelWrap {
    private LiveChannel liveChannel;
    private Boolean isAlive;
    private String userid;

    public LiveChannelWrap(LiveChannel liveChannel, String userid) {
        setLiveChannel(liveChannel);
        setUserid(userid);
        setIsAlive(true);
    }

    public LiveChannel getLiveChannel() {
        return liveChannel;
    }

    public void setLiveChannel(LiveChannel liveChannel) {
        this.liveChannel = liveChannel;
    }

    public Boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(Boolean isAlive) {
        this.isAlive = isAlive;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "LiveChannelWrap{" +
                "liveChannel=" + liveChannel.toString() +
                ", isAlive=" + isAlive +
                ", userid='" + userid + '\'' +
                '}';
    }
}
