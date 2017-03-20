package main.java.com.bean;

import java.util.List;

/**
 * Created by root on 2016/9/6.
 */
public class Createorder {
    public Long vipcardid;
    public Long[] single;
    public Long suite;
    public String desc;
    public Long ext_money;

    public Long getVipcardid() {
        return vipcardid;
    }

    public void setVipcardid(Long vipcardid) {
        this.vipcardid = vipcardid;
    }

    public Long[] getSingle() {
        return single;
    }

    public void setSingle(Long[] single) {
        this.single = single;
    }

    public Long getSuite() {
        return suite;
    }

    public void setSuite(Long suite) {
        this.suite = suite;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getExt_money() {
        return ext_money==null?0L:ext_money;
    }

    public void setExt_money(Long ext_money) {
        this.ext_money = ext_money;
    }
}
