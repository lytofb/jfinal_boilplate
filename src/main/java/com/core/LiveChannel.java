package main.java.com.core;

/**
 * Created by root on 2017/4/5.
 */
public class LiveChannel{

    public LiveChannel(){

    }

    String cid;
    String ctime;
    String name;
    String pushurl;
    String httpPullUrl;
    String hlsPullUrl;
    String rtmpPullUrl;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getPushurl() {
        return pushurl;
    }

    public void setPushurl(String pushurl) {
        this.pushurl = pushurl;
    }

    public String getHttpPullUrl() {
        return httpPullUrl;
    }

    public void setHttpPullUrl(String httpPullUrl) {
        this.httpPullUrl = httpPullUrl;
    }

    public String getHlsPullUrl() {
        return hlsPullUrl;
    }

    public void setHlsPullUrl(String hlsPullUrl) {
        this.hlsPullUrl = hlsPullUrl;
    }

    public String getRtmpPullUrl() {
        return rtmpPullUrl;
    }

    public void setRtmpPullUrl(String rtmpPullUrl) {
        this.rtmpPullUrl = rtmpPullUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LiveChannel{" +
                "cid='" + cid + '\'' +
                ", ctime='" + ctime + '\'' +
                ", name='" + name + '\'' +
                ", pushurl='" + pushurl + '\'' +
                ", httpPullUrl='" + httpPullUrl + '\'' +
                ", hlsPullUrl='" + hlsPullUrl + '\'' +
                ", rtmpPullUrl='" + rtmpPullUrl + '\'' +
                '}';
    }
}
