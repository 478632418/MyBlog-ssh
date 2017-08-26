<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>博客后台管理</title>
<meta name="description" content="">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="assets/css/amazeui.min.css" />
<link rel="stylesheet" href="assets/css/admin.css">
</head>
<body>
	<%@include file="/public/menu.jsp"%>
	<!-- content start -->
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">编辑文章类别</strong> <small></small>
				</div>
			</div>
			<hr>
			<div class="am-tabs am-margin" data-am-tabs>
				<ul class="am-tabs-nav am-nav am-nav-tabs">
					<li><a href="#tab3">编辑文章类别</a></li>
				</ul>

				<div class="am-tabs-bd">
					<div class="am-tab-panel am-fade" id="tab3">
						<form class="am-form" action="AdminArticleCategoryAction_do${method}.action"
							method="post">
							<input type="hidden" name="id"
								value="${articleCategoryBean.id}" />
							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">类别名称</div>
								<div class="am-u-sm-8 am-u-md-4 am-u-end">
									<input type="text" class="am-input-sm"
										name="title"
										value="${articleCategoryBean.title}" />
								</div>
							</div>
							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-4 am-u-md-2 am-text-right">类别描述</div>
								<div class="am-u-sm-8 am-u-md-4 am-u-end">
									<input type="text" class="am-input-sm"
										name="remarks"
										value="${articleCategoryBean.remarks}" />
								</div>
							</div>
							<div class="am-margin">
								<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
								<button type="submit" class="am-btn am-btn-primary am-btn-xs">放弃保存</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<footer class="admin-content-footer">
			<hr>
			<p class="am-padding-left">© 2017 AllMobilize, Inc. Licensed under
				MIT license.</p>
		</footer>
	</div>
	<!-- content end -->

	</div>

	<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="assets/js/jquery.min.js"></script>
	<!--<![endif]-->
	<script src="assets/js/amazeui.min.js"></script>
	<script src="assets/js/app.js"></script>
</body>
</html>