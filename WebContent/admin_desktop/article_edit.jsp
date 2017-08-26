<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html class="no-js fixed-layout">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>博客后台管理</title>
<meta name="description" content="编辑文章">
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
<script type="text/javascript" src="js/ckeditor.js"></script>
</head>
<body>
	<%@include file="/public/menu.jsp"%>
	<!-- content start -->
	<div class="admin-content">
		<div class="admin-content-body">
			<div class="am-cf am-padding am-padding-bottom-0">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">表单</strong> / <small>form</small>
				</div>
			</div>

			<form action="AdminArticleAction_do${method}.action" method="post">
				<div class="am-g am-margin-top">
					<div class="am-u-sm-12 am-u-md-12">所属类别</div>
					<div class="am-u-sm-12 am-u-md-12">
					<input type="hidden" name="id" value="${articleBean.id}" >
						<select data-am-selected="{btnSize: 'sm'}" style="width: 470px;"
							name="categoryId" id="categoryId">
							<c:forEach items="${categories}" var="cate">
								<option value="${cate.id}"
									${articleBean.categoryId==cate.id?'selected':''}>${cate.title}</option>
							</c:forEach>
						</select> <a
							href="javascript:addCategory('${pageContext.request.contextPath}/AdminArticleCategoryAction_doCreate.action')">添加类别</a>
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-12 am-u-md-12">类型类别</div>
					<div class="am-u-sm-12 am-u-md-12">
						<select id="type" name="type" style="width: 470px;">
							<option value="原创" ${articleBean.type=='原创'?'selected':''}>原创</option>
							<option value="转载" ${articleBean.type=='转载'?'selected':''}>转载</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-12 am-u-md-12">是否置顶</div>
					<div class="am-u-sm-12 am-u-md-12">
						<select id="top" name="top" style="width: 470px;">
							<option value="0" ${articleBean.top==0?'selected':''}>不顶置</option>
							<option value="1" ${articleBean.top==1?'selected':''}>顶置</option>
						</select>
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-12 am-u-md-12">文章标题</div>
					<div class="am-u-sm-12 am-u-md-12">
						<input type="text" class="am-input-sm" name="title"
							style="width: 470px;" value="${fn:escapeXml(articleBean.title)}">
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-12 am-u-md-12">内容摘要</div>
					<div class="am-u-sm-12 am-u-md-12">
						<textarea name="meta" cols="100" style="width: 1000px;" rows="3">${fn:escapeXml(articleBean.meta)}</textarea>
					</div>
				</div>
				<div class="am-g am-margin-top">
					<div class="am-u-sm-12 am-u-md-12">内容描述</div>
					<div class="am-u-sm-12 am-u-md-12">
						<textarea name="content" id="content">${fn:escapeXml(articleBean.content)}</textarea>
					</div>
				</div>
		</div>

		<div class="am-margin">
			<button type="submit" class="am-btn am-btn-primary am-btn-xs">提交保存</button>
			<button type="submit" class="am-btn am-btn-primary am-btn-xs">放弃保存</button>
		</div>
		</form>
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
<script type="text/javascript">
	CKEDITOR.replace('content');
	function addCategory(url) {
		var categoryName = window.prompt("添加文章类别");
		if (categoryName == null || categoryName.trim().length <= 0) {
			alert("文章类别不能为空哦！");
			return;
		}

		//类别已存在
		var select = document.getElementById('categoryId');
		var options = select.options;
		for (var i = 0; i < options.length; i++) {
			if (options[i].innerHTML == categoryName.trim()) {
				alert("你添加的类别已存在！");
				return;
			}
		}
		//符合条件，进行请求,模拟post表单
		var form = document.createElement("form");
		form.action = url;
		form.method = 'post';
		form.style.display = 'none';
		var input = document.createElement("input");
		input.type = "text";
		input.name = "title";
		input.value = categoryName;
		form.appendChild(input);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>
