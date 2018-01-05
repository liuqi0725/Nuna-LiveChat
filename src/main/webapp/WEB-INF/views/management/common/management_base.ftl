<!DOCTYPE html>
<html>
<head>

	<@block name="meta">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	</@block>
	
    <@block name="head">
    	<link rel="stylesheet" href="${basePath}/statics/vendor/layui/css/layui.css" media="all">
    </@block>

    <title>DAM - <@block name="title"> Title </@block></title>

</head>

<body class="layui-layout-body">
    <base id="base" href="${base}">
    
    <div class="layui-layout layui-layout-admin">
	
		<#include "/management/common/management_top.ftl">

		<#if Session.user.confirmed == 0>
			<#include "/management/common/management_left.ftl">
		</#if>

		<!-- content start -->
	    <div class="layui-body" style="background-color: white;">    			
	    
			<div style="margin: 10px 20px 0 10px;">
			
				<#if message??>
					<div class="alert alert-warning" role="alert">
	                    <button type="button" class="close" data-dismiss="alert">&times;</button>
	                    ${message}
	                </div>
				</#if>
	
	            <@block name="page_content">
	
	            </@block>
	        </div>
	    </div>
		<!-- content end -->
		
	    <!-- footer start -->
	    <div class="layui-footer footer footer-ext">
	        <div class="layui-main">
	            <p>© 2017 <a href="http://www.35liuqi.com" target="_blank">Alexliu</a> All Right</p>
	        </div>
	    </div>
	    <!-- footer end -->
	    
	    <@block name="layerModel">
	    
	    </@block>
	    
	    <#include "/management/common/user_menu.ftl">
	
	</div>
</body>
<@block name="scripts">
	<script type="text/javascript" src="${basePath}/statics/vendor/jquery-1.12.4.js"></script>
    <!-- layui -->
    <script type="text/javascript" src="${basePath}/statics/vendor/layer/layer.js"></script>
    <script type="text/javascript" src="${basePath}/statics/vendor/layui/layui.js"></script>

    <script type="text/javascript" src="${basePath}/statics/js/AppConstants.js"></script>

    <script type="text/javascript" src="${basePath}/statics/js/common.js"></script>
    <script type="text/javascript" src="${basePath}/statics/js/menu.js"></script>

	<script>
		//js 请求路径
		var ctx = '${basePath}';
	</script>
</@block>
</html>