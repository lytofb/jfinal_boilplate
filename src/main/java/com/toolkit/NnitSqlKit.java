package main.java.com.toolkit;

import java.util.ArrayList;

/**
 * Created by root on 2017/4/7.
 */
public class NnitSqlKit {

    public static Object[] turnArrayToList(ArrayList<Object> objectList){
        Integer listlength = 0;
        ArrayList<Object> newarray = new ArrayList<Object>();
        for (Object o:objectList){
            if (o!=null&&!"".equals(o)){
                listlength++;
                newarray.add(o);
            }
        }
        Object[] objects = new Object[listlength];
        for (int i = 0; i < listlength; i++) {
            objects[i] = newarray.get(i);
        }
        return objects;
    }

    public static String buildSql(String sqlbase,ArrayList<Object> paras,String orderby,String... columns){
        assert columns.length==paras.size();
        for (int i = 0; i < paras.size(); i++) {
            if (paras.get(i)==null||"".equals(paras.get(i))){
                continue;
            } else {
                sqlbase = sqlbase+" and "+columns[i]+" = ? ";
            }
        }
        return sqlbase+orderby;
    }

}
