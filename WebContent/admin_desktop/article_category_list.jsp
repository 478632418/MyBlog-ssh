<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js fixed-layout">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客后台管理</title>
<meta name="description" content="这是一个 index 页面">
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
					<strong class="am-text-primary am-text-lg">文章类别管理</strong>
				</div>
			</div>
			<hr>
			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<button type="button" class="am-btn am-btn-default">
								<a href="AdminArticleCategoryAction_create.action"><span
									class="am-icon-plus"></span> 新增</a>
							</button>
							<!-- <button type="button" class="am-btn am-btn-default">
								<span class="am-icon-save"></span> 保存
							</button>
							<button type="button" class="am-btn am-btn-default">
								<span class="am-icon-trash-o"></span> 删除
							</button> -->
						</div>
					</div>
				</div>

				<div class="am-u-sm-12 am-u-md-3">
					<div class="am-input-group am-input-group-sm">
						<input name="keywords" id="keywords" type="text"
							class="am-form-field"> 
							<span class="am-input-group-btn">
							<s:submit name="submit" value="搜索" class="am-btn am-btn-default"
								onclick="javascrit:doSubmit();"></s:submit> <script
								type="text/javascript">
									function doSubmit() {
										var keywords = document
												.getElementById("keywords").value;
										window.location.href = "${pageContext.request.contextPath}/AdminArticleCategoryAction_list.action?keywords="
												+ keywords;
									}
								</script>
						</span>
					</div>
				</div>
			</div>

			<div class="am-g">
				<div class="am-u-sm-12">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th class="table-check"><input type="checkbox" /></th>
								<th class="table-id">ID</th>
								<th class="table-type">类别</th>
								<th class="table-title">描述</th>
								<th class="table-date am-hide-sm-only">创建日期</th>
								<th class="table-date am-hide-sm-only">修改日期</th>
								<th class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="article_category">
								<tr>
									<td><input type="checkbox" /></td>
									<td>${article_category.id}</td>
									<td><a href="javascript:void(0);">${article_category.title}</a></td>
									<td><a href="javascript:void(0);">${article_category.remarks}</a></td>
									<td class="am-hide-sm-only">${article_category.createDate}</td>
									<td class="am-hide-sm-only">${article_category.modifyDate}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button
													class="am-btn am-btn-default am-btn-xs am-text-secondary">
													<span class="am-icon-pencil-square-o"></span> <a
														href="${pageContext.request.contextPath}/AdminArticleCategoryAction_modify.action?articleCategoryId=${article_category.id}">
														编辑</a>
												</button>
												&nbsp;
												<button
													class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
													<span class="am-icon-trash-o"></span> <a
														href="javascript:deleteConfirm('${article_category.title}','${article_category.id}')">删除</a>
												</button>
												&nbsp;
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%@include file="/public/page.jsp"%>
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
	function deleteConfirm(cname, cid) {
		var result = window.confirm("您确认删除 " + cname + " 这个类别吗？");
		if (result)
			window.location.href = "${pageContext.request.contextPath}/AdminArticleCategoryAction_delete.action?articleCategoryId="
					+ cid;
	}
</script>
</html>