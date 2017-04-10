package main.java.com.bean;

/**
 * Created by root on 2017/4/10.
 */
public class CreateMerchant {
    String merchant_name;
    Long tel_num;
    String address;

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public Long getTel_num() {
        return tel_num;
    }

    public void setTel_num(Long tel_num) {
        this.tel_num = tel_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
