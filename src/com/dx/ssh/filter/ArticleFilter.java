package com.dx.ssh.filter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dx.ssh.service.ArticleService;
import com.dx.ssh.utils.TemplateUtils;
import com.opensymphony.xwork2.ActionContext;

public class ArticleFilter implements Filter {
	public static final String ARTICLE_TOKEN = "view";
	private ArticleService articleService;

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	// protected Object getBean(ServletContext servletContext, String id) {
	// WebApplicationContext context =
	// WebApplicationContextUtils.getWebApplicationContext(servletContext);
	// return context.getBean(id);
	// }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// ArticleService articleService = (ArticleService)
		// this.getBean(request.getServletContext(), "articleService");

		String path = request.getRequestURL().toString();
		// 模式匹配
		Pattern pattern = Pattern.compile("/blog/([0-9]+)/([0-9]+)-([0-9]+)");
		Matcher matcher = pattern.matcher(path);

		// 不匹配，路径不正确
		if (!matcher.find()) {
			// 路径不对，报错404
			response.sendError(404, "您输入路径的不存在！");
			return;
		}
		// 模版文件绝对路径
		String realPath = request.getServletContext().getRealPath("/");
		// 文件路径
		String filePath = realPath + matcher.group() + ".ftl";

		filePath = realPath + matcher.group() + ".ftl";
		filePath = realPath + "blog/template/template.ftl";
		filePath = filePath.replace("/", "\\");
		filePath = filePath.replace("\\\\", "\\");

		System.out.println(filePath);
		// 查看服务器资源是否存在
		File file = new File(filePath);
		if (!file.exists()) {
			// 路径不对，报错404
			response.sendError(404, "您输入路径的不存在！");
			return;
		}
		// 解析路径中的文章id
		int articleId = Integer.parseInt(matcher.group(3));
		System.out.println("articleId = " + articleId);

		// 防止同一用户session添加多次访问量
		boolean isNew = false;
		// 获取当前用户session
		HttpSession session = request.getSession();
		// 还没看过就能添加访问量
		if (session.getAttribute(ARTICLE_TOKEN + articleId) == null) {
			isNew = true;
			// 设置当前的用户session已经看过文章了
			session.setAttribute(ARTICLE_TOKEN + articleId, "true");
		}

		// 填充模版信息
		Map<String, Object> params = null;
		try {
			// 得到freemarker模版文件所需参数
			params = this.getArticleService().getTemplateParams(articleId, request.getContextPath(), isNew);
			params.put("contextPath", request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String templateDir = realPath + File.separator + "blog";
		String templateFile = matcher.group(1) + "/" + matcher.group(2) + "-" + matcher.group(3) + ".ftl";
		templateFile = "template/template.ftl";
		boolean result = TemplateUtils.parserTemplate(templateDir, templateFile, params, response.getOutputStream());

		if (!result) {
			// 服务器异常
			response.sendError(500, "服务器未知异常！");
		}
		response.getOutputStream().close();
	}

}
