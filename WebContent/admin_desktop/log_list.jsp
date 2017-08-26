<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
					<strong class="am-text-primary am-text-lg">日志管理</strong>
				</div>
			</div>
			<hr>

			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-3">
					<div class="am-btn-toolbar">
						<div class="am-btn-group am-btn-group-xs">
							<s:select name="logType" list="#request.logTypes" listKey="key"
								listValue="value" value="#request.logType" headerKey=""
								headerValue="请选择日志类型。。。" class="am-form-option">
							</s:select>
						</div>
					</div>
				</div>
				<div class="am-u-sm-12 am-u-md-6">
					<div class="am-input-group am-input-group-sm">
						<span class="am-input-group-btn"> <s:submit name="submit"
								value="搜索" class="am-btn am-btn-default"
								onclick="javascrit:doSubmit();"></s:submit> <script
								type="text/javascript">
									function doSubmit() {
										var logType = document
												.getElementById("logType").value;
										window.location.href = "${pageContext.request.contextPath}/AdminLogAction_list.action?logType="
												+ logType;
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
								<th class="table-type">用户</th>
								<th class="table-title">描述</th>
								<th class="table-date am-hide-sm-only">创建日期</th>
								<th class="table-set">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${page.list}" var="logItem">
								<tr>
									<td><input type="checkbox" /></td>
									<td>${logItem.id}</td>
									<td><a>${logItem.type}</a></td>
									<td><a>${logItem.account}</a></td>
									<td><a>${logItem.msg}</a></td>
									<td class="am-hide-sm-only">${logItem.createDate}</td>
									<td>
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button
													class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only">
													<span class="am-icon-trash-o"></span> <a
														href="javascript:deleteConfirm('${logItem.msg}','${logItem.id}')">删除</a>
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
			window.location.href = "${pageContext.request.contextPath}/AdminLogAction_delete.action?logId="
					+ cid;
	}
</script>
</html>
