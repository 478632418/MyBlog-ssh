package com.dx.ssh.listener;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dx.ssh.entity.PageViewEntity;
import com.dx.ssh.entity.UserEntity;
import com.dx.ssh.service.PageViewService;
import com.dx.ssh.settings.ConstsSettings;
import com.dx.ssh.utils.AddressUtils;
import com.dx.ssh.utils.AgentUtils;

public final class SessionListener
		implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
	private final static Map<Integer, UserEntity> onlineUserItems = new HashMap<>();
	private static PageViewService pageViewService;

	public static Collection<UserEntity> getOnlineUserItems() {
		return onlineUserItems.values();
	}

	public static Integer getYestodayTotal() {
		return pageViewService.getYestodayTotal();
	}

	public void contextInitialized(ServletContextEvent sce) {
		// ctx = new
		// FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
		// ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		if (springContext != null) {
			pageViewService = (PageViewService) springContext.getBean("pageViewService");
		} else {
			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
					"classpath:/applicationContext.xml");
			if (ctx != null) {
				pageViewService = (PageViewService) ctx.getBean("pageViewService");
			} else {
				System.out.println("获取classpath:/applicationContext.xml失败!");
				return;
			}
		}
		System.out.println("初始化系统服务!");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed(ServletContextEvent sce)");
	}

	/**
	 * 使用这个来统计访问量
	 */
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("sessionCreated(HttpSessionEvent se)");
		PageViewEntity entity = new PageViewEntity();

		String ip = "";
		String address = "";
		try {
			ip = AddressUtils.getIpAddr(ServletActionContext.getRequest());
			address = AddressUtils.getAddresses("ip=" + ip, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}

		String userAgent = ServletActionContext.getRequest().getHeader("USER-AGENT");
		String browser = AgentUtils.getBrowser(userAgent);

		entity.setIp(ip);
		entity.setIsp(address);
		entity.setBrowser(browser);
		entity.setCreateDate(new Timestamp(new Date().getTime()));

		pageViewService.update(entity);
	}

	public void attributeAdded(HttpSessionBindingEvent se) {
		Object object = se.getValue();
		System.out.println("attributeAdded(HttpSessionBindingEvent se)===========" + se.getValue());
		if (object != null) {
			UserEntity user = (UserEntity) object;
			if (user != null && !onlineUserItems.containsKey(user.getId())) {
				onlineUserItems.put(user.getId(), user);
			}
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		Object object = se.getSession().getAttribute(ConstsSettings.ADMIN_SESSION_KEY);
		System.out.println("sessionDestroyed(HttpSessionEvent se)===========" + object);

		UserEntity user = (UserEntity) object;
		if (user != null && onlineUserItems.containsKey(user.getId())) {
			onlineUserItems.remove(user.getId());
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("attributeRemoved(HttpSessionBindingEvent se)");
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		System.out.println("attributeReplaced(HttpSessionBindingEvent se)");
	}
}
