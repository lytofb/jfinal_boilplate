package com.common;

import java.util.HashMap;

import com.dao.DataSource;
import com.dao.Permission;
import com.dao.Role_Menu_Rel;
import com.dao.Role_Permission_Rel;
import com.dao.Role_Scheme_Rel;
import com.dao.Roles;
import com.dao.SysMenu;
import com.dao.Template;
import com.dao.User_Role_Rel;
import com.dao.Users;
import com.ext.MysqlDeleteDialect;
import com.ext.Slf4jLoggerFactory;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.shiro.ShiroInterceptor;
import com.jfinal.ext.plugin.shiro.ShiroPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.cache.EhCache;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
import com.jfinal.render.IErrorRenderFactory;
import com.jfinal.render.JsonRender;
import com.jfinal.render.Render;
import com.nnit.interceptor.CommonInterceptor;
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
		me.setError401View("/emp2/common/login.html");
		me.setLoggerFactory(new Slf4jLoggerFactory());
//		me.setI18n("i18n");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		this.routes = me;
		me.add("/", CommonController.class,"/emp2/common");
		me.add("/eco",EntryController.class,"/emp2/emp");
		me.add("/saleForce",SaleForceController.class,"/emp2/saleForce");
		me.add("/user",UsersController.class,"");
		me.add("/role",RoleController.class,"");
		me.add("/permission",PermissionController.class,"");
		me.add("/template",TemplateController.class,"");
		//设置权限错误路径
		
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0   数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"),getProperty("Driver"));
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		Class clazz = null;
	    try {
	        clazz = Class.forName("com.ext."+getProperty("Dialect"));
	        arp.setDialect((Dialect) clazz.newInstance());
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
		me.add(arp);
		arp.addMapping("user_info", Users.class);
		arp.addMapping("role_info", Roles.class);
		arp.addMapping("permission_info", Permission.class);
		arp.addMapping("role_permission_rel", Role_Permission_Rel.class);
		arp.addMapping("user_role_rel", User_Role_Rel.class);
		arp.addMapping("m_datasource", DataSource.class);
		arp.addMapping("role_scheme_rel", Role_Scheme_Rel.class);
		arp.addMapping("user_template", Template.class);
		
		arp.addMapping("sys_menu", "menu_id", SysMenu.class);
		arp.addMapping("role_menu_rel", Role_Menu_Rel.class);
		
//		C3p0Plugin c3p0PluginDis = new C3p0Plugin(getProperty("userDistributejdbcUrl"), getProperty("userDistributejdbcUrluser"), getProperty("userDistributejdbcUrlpassword").trim());
//		me.add(c3p0PluginDis);
//		ActiveRecordPlugin arpDis = new ActiveRecordPlugin("userDitribute",c3p0PluginDis);
//		arpDis.setDialect(new MysqlDeleteDialect());
//		me.add(arpDis);
		
//		C3p0Plugin c3p0PluginUInfo = new C3p0Plugin(getProperty("ujdbcUrl"), getProperty("uuser"), getProperty("upassword").trim());
//		me.add(c3p0PluginUInfo);
//		ActiveRecordPlugin arpDisU = new ActiveRecordPlugin("UInfo",c3p0PluginUInfo);
//		arpDisU.setDialect(new MysqlDeleteDialect());
//		me.add(arpDisU);
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
		me.add(new ShiroInterceptor());
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
		JFinal.start("WebRoot", 8085, "/", 5);
	}
}
