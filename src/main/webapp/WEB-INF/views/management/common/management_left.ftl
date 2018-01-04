<!-- left nav start -->
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree">
        
        	<#if Session.user?exists>
	        	<li class="layui-nav-item layui-nav-itemed" id="dashboard">
	                <a class="" href="${basePath}/mg/dashboard">系统概况</a> 
	            </li>
            </#if>
            
            <li class="layui-nav-item layui-nav-itemed">
                <a class="" href="javascript:;">数据管理</a>
                <dl class="layui-nav-child">
                    <dd id="file_manage"><a href="${basePath}/file/manage">文件列表</a></dd>
                    <dd id="doc_manage"><a href="${basePath}/doc/manage">档案列表</a></dd>
                   	<dd id="parse_manage">
                   		<a href="${basePath}/parse/manage#tab=ocr-do">
                   			文件解析列表
                   			<span class="layui-badge">${wait_all_count}</span>
                   		</a>
               		</dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">文档工具</a>
                <dl class="layui-nav-child">
                    <dd id="ocr_img_tool"><a href="${basePath}/parse/tool/img">图片 OCR 识别</a></dd>
                    <dd id="ocr_pdf_tool"><a href="${basePath}/parse/tool/pdf">PDF OCR 识别</a></dd>
                </dl>
            </li>
            <#if Session.user?exists>
	            <li class="layui-nav-item layui-nav-itemed">
	                <a href="javascript:;">系统设置</a>
	                <dl class="layui-nav-child">
   						<dd id="class_manage"><a href="${basePath}/config/class/manage">分类管理</a></dd>
	                    <dd id="config_manage"><a href="${basePath}/config/manage">配置管理</a></dd>
	                </dl>
	            </li>
            </#if>
        </ul>
    </div>
</div>
<!-- left nav end -->