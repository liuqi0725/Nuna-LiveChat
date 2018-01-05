<!-- top nav start -->
<div class="layui-header">
    <div class="layui-logo">NUNA-LiveChat</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
    	<#if Session.user?exists>
			<li class="layui-nav-item"><a target="_blank" href="${basePath}/solr/admin.html">搜索引擎管理</a></li>
		</#if>
		<li class="layui-nav-item"><a target="_blank" href="https://qiliu3.gitbooks.io/dam/content/">帮助文档</a></li> 
    
        <!-- <li class="layui-nav-item">
            <a href="javascript:;">其它系统</a>
            <dl class="layui-nav-child">
                <dd><a href="">邮件管理</a></dd>
                <dd><a href="">消息管理</a></dd>
                <dd><a href="">授权管理</a></dd>
            </dl>
        </li> -->
    </ul>
    <ul class="layui-nav layui-layout-right">
      	<li class="layui-nav-item"><a style="color: #009688;" href="${basePath}/ftr/index">全文搜索</a></li>
        <li class="layui-nav-item">
        	<#if Session.user?exists>
                   <a href="javascript:;"><img src="${basePath}/assets/img/admin.png" class="layui-nav-img">${ Session.user.username }</a>
                   <dl class="layui-nav-child">
                       <dd><a href="javascript:void(0);" onclick="menuJS.changePassword('m')">修改密码</a></dd>
                       <dd><a href="javascript:void(0);" onclick="menuJS.loginOut('m')">退出</a></dd>
                   </dl>
            <#else>
                <a href="javascript:void(0);" onclick="menuJS.login('m');">登陆</a>
            </#if>
        </li>
    </ul>
</div>
<!-- top nav end -->
