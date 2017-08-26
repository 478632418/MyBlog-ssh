<%@page import="com.dx.ssh.entity.UserEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
					<strong class="am-text-primary am-text-lg">个人资料</strong> / <small>Personal
						information</small>
				</div>
			</div>
			<hr />
			<div class="am-g">
				<div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
					<div class="am-panel am-panel-default">
						<div class="am-panel-bd">
							<div class="am-g">
								<div class="am-u-md-4">
									<img class="am-img-circle am-img-thumbnail"
										src="<s:if
										test="#request.onlineuser.avatar==null||#request.onlineuser.avatar.trim().length()==0">
									files/avatar/bw-2014-06-19.jpg
									</s:if>
									<s:else>${onlineuser.avatar}</s:else>"
										alt="" />
								</div>
								<div class="am-u-md-8">
									<p>
										你可以使用<a href="#">gravatar.com</a>提供的头像或者使用本地上传头像。
									</p>
									<form class="am-form" method="post"
										enctype="multipart/form-data"
										action="AdminUserAction_doEditAvatar.action">
										<div class="am-form-group">
											<input type="file" name="myFile" 　id="myFile" />
											<p class="am-form-help">请选择要上传的文件...</p>
											<button type="submit" class="am-btn am-btn-primary am-btn-xs">保存</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
					<form class="am-form am-form-horizontal" method="post"
						action="AdminUserAction_doEdit.action">
						<div class="am-form-group">
							<label for="user-name" class="am-u-sm-3 am-form-label">姓名
								/ Name</label>
							<div class="am-u-sm-9">
								<input type="text" name="name" value="${onlineuser.name }"
									placeholder="姓名 / Name"> <input type="hidden"
									name="userBean.id" value="${onlineuser.id }" /> <small>输入你的名字，让我们记住你。</small>
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-email" class="am-u-sm-3 am-form-label">电子邮件
								/ Email</label>
							<div class="am-u-sm-9">
								<input type="email" name="email" value="${onlineuser.email }"
									placeholder="输入你的电子邮件 / Email"> <small>邮箱你懂得...</small>
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-phone" class="am-u-sm-3 am-form-label">电话
								/ Telephone</label>
							<div class="am-u-sm-9">
								<input type="tel" name="phone" value="${onlineuser.phone }"
									placeholder="输入你的电话号码 / Telephone">
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-QQ" class="am-u-sm-3 am-form-label">QQ</label>
							<div class="am-u-sm-9">
								<input type="number" pattern="[0-9]*" name="qq"
									value="${onlineuser.qq }" placeholder="输入你的QQ号码">
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-weibo" class="am-u-sm-3 am-form-label">微博
								/ Twitter</label>
							<div class="am-u-sm-9">
								<input type="text" name="weibo" value="${onlineuser.weibo }"
									placeholder="输入你的微博 / Twitter">
							</div>
						</div>

						<div class="am-form-group">
							<label for="user-intro" class="am-u-sm-3 am-form-label">简介
								/ Intro</label>
							<div class="am-u-sm-9">
								<textarea class="" rows="5" name="intro" placeholder="输入个人简介">${onlineuser.intro }</textarea>
								<small>250字以内写出你的一生...</small>
							</div>
						</div>

						<div class="am-form-group">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<button type="submit" class="am-btn am-btn-primary">保存修改</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<footer class="admin-content-footer">
		<hr>
		<p class="am-padding-left">© 2014 AllMobilize, Inc. Licensed under
			MIT license.</p>
		</footer>

	</div>
	<!-- content end -->
	</div>

	<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
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