package main.java.com.nnit.pvc.authentication;

import org.apache.shiro.authc.UsernamePasswordToken;

public class PVCUserNamePasswordToken extends UsernamePasswordToken {
	private String dbtarget;
	
	public String getDbtarget() {
		return dbtarget;
	}

	public void setDbtarget(String dbtarget) {
		this.dbtarget = dbtarget;
	}

	public PVCUserNamePasswordToken(UsernamePasswordToken token,String dbtarget) {
		// TODO Auto-generated constructor stub
		super(token.getUsername(),token.getPassword());
		setDbtarget(dbtarget);
	}

}
