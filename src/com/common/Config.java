package com.common;

import com.controller.CommonController;
import com.controller.OrderController;
import com.controller.VipController;
import com.dao.ModelEx;
import com.ext.Slf4jLoggerFactory;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.nnit.interceptor.DipSysMenuInterceptor;
import com.nnit.interceptor.XssInterceptor;

/**
 * API引导式配置
 */
public class Config extends JFinalConfig {
	
	Routes routes;
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用getProperty(...)获取值
		loadPropertyFile("config.properties");
		me.setDevMode(getPropertyToBoolean("devMode", false));
//		me.setError401View("/emp2/common/login.html");
		me.setLogFactory(new Slf4jLoggerFactory());
//		me.setI18n("i18n");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		this.routes = me;
		me.add("/", CommonController.class,"/html");
		me.add("/vip", VipController.class,"/html/vip");
		me.add("/order", OrderController.class,"/html/order");
		//设置权限错误路径
		
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0   数据库连接池插件
		DruidPlugin duirdPlugin = new DruidPlugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"),getProperty("Driver"));
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(duirdPlugin);
		atbp.addExcludeClasses(ModelEx.class);
		me.add(duirdPlugin);
		
		// 配置ActiveRecord插件
//		ActiveRecordPlugin arp = new ActiveRecordPlugin(duirdPlugin);
		Class clazz = null;
	    try {
	        clazz = Class.forName("com.ext."+getProperty("Dialect"));
	        atbp.setDialect((Dialect) clazz.newInstance());
		 } catch (InstantiationException e) {
			// TODO Auto-generated catch block
		    e.printStackTrace();
	     } catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
	        e.printStackTrace();
	     }catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	     	e.printStackTrace();
		  }
		me.add(atbp);
		//shiro plugin
		me.add(new ShiroPlugin(routes));
		
		//Timer Plugin
//		TimerPlugin tp = new TimerPlugin();
		//EhCache Plugin
		me.add(new EhCachePlugin());
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
//		me.add(new ShiroInterceptor());
//		me.add(new CommonInterceptor());
		me.add(new DipSysMenuInterceptor());
		me.add(new XssInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("base_path"));
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 5);
	}
}
