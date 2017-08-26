<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<div class="scanlines"></div>
<div class="header-wrapper opacity">
	<div class="header">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/">
				<h1>DX博客</h1>
			</a>
		</div>

		<div id="menu-wrapper">
			<div id="menu" class="menu">
				<ul id="tiny">
					<li class="active"><a
						href="${pageContext.request.contextPath}/">博客</a>
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/listArticle.action">所有博文</a></li>
						</ul></li>
					<li><a href="#" title="进入我的Github">Github</a></li>
					<li><a href="#" title="进入我的CSDN博客">CSDN博客</a></li>
					<li><a
						href="${pageContext.request.contextPath}/commentUI.action"
						title="给我留言">留言板</a></li>
					<li><a href="#">Menu</a>
						<ul>
							<li><a href="/s/">仓库管理系统</a></li>
							<li><a href="/WorkUpload/">你画我猜小游戏</a></li>
							<li><a href="/uShare/">IOnline</a></li>
							<li><a
								href="${pageContext.request.contextPath}/UserAction_logi.action">管理员</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<!-- 
<div class="wrapper">
	<ul class="social">
		<li><a class="rss" href="#" title="博客订阅"></a></li>
		<li><a class="qq" href="#" title="QQ"></a></li>
		<li><a class="weibo" href="#" title="新浪微博"></a></li>
		<li><a class="wechat"
			href="${pageContext.request.contextPath}/public/wechat.jsp"
			title="微信"></a></li>
		<li><a class="email" href="#" title="邮件"></a></li>
		<li><a class="phone" href="tel:15233338888" title="手机"></a></li>
	</ul>
<div class="intro">Intro...</div> 
</div>
-->