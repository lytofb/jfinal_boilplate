package main.java.com.controller;

import com.jfinal.ext2.core.ControllerExt;
import com.jfinal.plugin.activerecord.Db;
import main.java.com.bean.CreateMerchant;
import main.java.com.dao.contact_merchant_operator;
import main.java.com.dao.data_merchant;
import main.java.com.dao.data_operator;
import main.java.com.toolkit.RequestKit;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by root on 2017/4/10.
 */
public class MerchantController extends ControllerExt {
    @Override
    public void onExceptionError(Exception e) {

    }

    public void toCreateMerchant(){
        render("merchantcreate.html");
    }


    public void createMerchant() {
        CreateMerchant merchant = getBean(CreateMerchant.class);
        recordMerchant(merchant);
        renderNull();
    }

    public void mockMerchant(){
        CreateMerchant cm = new CreateMerchant();
        cm.setTel_num(13700088213L);
        cm.setAddress("hehe");
        cm.setMerchant_name("create_merchant");
        renderText(recordMerchant(cm));
    }

    private String recordMerchant(CreateMerchant merchant){
        data_merchant data_merchant = new data_merchant();
        data_operator data_operator = new data_operator();
        contact_merchant_operator cmo = new contact_merchant_operator();
        data_merchant.put("merchant_name",merchant.getMerchant_name());
        data_merchant.put("tel_num",merchant.getTel_num());
        data_merchant.put("address",merchant.getAddress());
        data_merchant.save();
        String generateKey = RequestKit.generateuuid().replaceAll("-", "");
        data_operator.put("operator_name","超级管理员");
        data_operator.put("operator_account", generateKey.substring(0, 8));
        data_operator.put("operator_password",generateKey.substring(8,14));
        data_operator.save();

        cmo.put("merchant_id",data_merchant.getLong("id"));
        cmo.put("merchant_name",merchant.getMerchant_name());
        cmo.put("operator_id",data_operator.getLong("id"));
        cmo.put("operator_name",data_operator.getStr("operator_name"));
        cmo.put("issuper",1);
        cmo.save();

        createMerchantTable(data_merchant.getLong("id"));

        return generateKey.substring(0, 8)+";"+generateKey.substring(8,14);
    }

    private void createMerchantTable(Long merchantid){
        String createTpl = "CREATE TABLE `z%s_liveshow` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                "  `user_account` varchar(255) DEFAULT NULL,\n" +
                "  `cid` varchar(255) DEFAULT NULL,\n" +
                "  `chname` varchar(512) DEFAULT NULL,\n" +
                "  `pushUrl` varchar(512) DEFAULT NULL,\n" +
                "  `pullHttpUrl` varchar(512) DEFAULT NULL,\n" +
                "  `pullHLSUrl` varchar(512) DEFAULT NULL,\n" +
                "  `pullRTMPUrl` varchar(512) DEFAULT NULL,\n" +
                "  `createtime` datetime DEFAULT NULL,\n" +
                "  `enabled` int(11) DEFAULT '1',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;";
        String createExerciseTpl = "CREATE TABLE `z%s_exercise` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(255) DEFAULT NULL,\n" +
                "  `html` varchar(255) DEFAULT NULL,\n" +
                "  `answer` varchar(255) DEFAULT NULL,\n" +
                "  `group_id` bigint(20) DEFAULT NULL,\n" +
                "  `group_name` varchar(255) DEFAULT NULL,\n" +
                "  `user_id` bigint(20) DEFAULT NULL,\n" +
                "  `user_name` varchar(255) DEFAULT NULL,\n" +
                "  `enabled` int(11) DEFAULT '1',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;\n";
        String createExerciseGroupTpl = "CREATE TABLE `z%s_exercise_group` (\n" +
                "  `id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                "  `group_name` varchar(255) DEFAULT NULL,\n" +
                "  `user_id` bigint(20) DEFAULT NULL,\n" +
                "  `user_name` varchar(255) DEFAULT NULL,\n" +
                "  `enabled` int(11) DEFAULT '1',\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
        String createMerchantSql = String.format(createTpl,merchantid.toString());
        String createExerciseSql = String.format(createExerciseTpl,merchantid.toString());
        String createExerciseGroupSql = String.format(createExerciseGroupTpl,merchantid.toString());
        Db.update(createMerchantSql);
        Db.update(createExerciseSql);
        Db.update(createExerciseGroupSql);
    }
}
