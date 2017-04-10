package main.java.com.core;

/**
 * Created by root on 2017/4/5.
 */
public class LiveChannelWrap {
    private LiveChannel liveChannel;
    private Boolean isAlive;
    private String userAccount;

    public LiveChannelWrap(LiveChannel liveChannel, String userAccount) {
        setLiveChannel(liveChannel);
        setUserAccount(userAccount);
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "LiveChannelWrap{" +
                "liveChannel=" + liveChannel.toString() +
                ", isAlive=" + isAlive +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
