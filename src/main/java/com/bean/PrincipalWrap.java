package main.java.com.bean;

/**
 * Created by root on 2017/4/11.
 */
public class PrincipalWrap {

    public PrincipalWrap(String name,Object model) {
        setModel(model);
        setName(name);
    }
    String name;
    Object model;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return name;
    }
}
