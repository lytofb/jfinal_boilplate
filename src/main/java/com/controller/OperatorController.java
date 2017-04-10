package main.java.com.controller;

import com.jfinal.ext2.core.ControllerExt;
import main.java.com.bean.CreateOperator;
import main.java.com.dao.contact_merchant_operator;
import main.java.com.dao.data_operator;
import main.java.com.toolkit.RequestKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created by root on 2017/4/10.
 */
public class OperatorController extends ControllerExt {

    Session session = SecurityUtils.getSubject().getSession();
    Long merchant_id = (Long) session.getAttribute("merchant_id");
    String merchant_name = (String) session.getAttribute("merchant_name");
    Long operator_id = (Long) session.getAttribute("operator_id");
    String operator_name = (String) session.getAttribute("operator_name");

    @Override
    public void onExceptionError(Exception e) {

    }

    public void CreateOperator(){
        CreateOperator co = getBean(CreateOperator.class);
        recordOperator(co);
    }

    public void mockOperator(){
        CreateOperator co = new CreateOperator();
        String generateKey = RequestKit.generateuuid().replaceAll("-", "");
        String operator_name = "mock_operator";
        String account = generateKey.substring(0,8);
        String password = generateKey.substring(8,14);
        co.setOperator_account(account);
        co.setOperator_name(operator_name);
        co.setOperator_password(password);
        recordOperator(co);
        renderJson(co);
    }

    private void recordOperator(CreateOperator co) {
        data_operator data_operator = new data_operator();
        contact_merchant_operator contact_merchant_operator = new contact_merchant_operator();
        data_operator.put("operator_name",co.getOperator_name());
        data_operator.put("operator_account",co.getOperator_account());
        data_operator.put("operator_password",co.getOperator_password());
        data_operator.save();

        contact_merchant_operator.put("merchant_id",merchant_id);
        contact_merchant_operator.put("merchant_name",merchant_name);
        contact_merchant_operator.put("operator_id",data_operator.getLong("id"));
        contact_merchant_operator.put("operator_name",data_operator.getStr("operator_name"));
        contact_merchant_operator.save();

    }
}
