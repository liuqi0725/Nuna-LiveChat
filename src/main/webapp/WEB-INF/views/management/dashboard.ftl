
<@override name="title">文档管理</@override>

<@override name="head">
<@super />

<style>

	.card {
		/* 去圆角 */
		-webkit-border-radius: 0;
    	-moz-border-radius: 0;
    	border-radius: 0;
    	
    	font-size: 14px;
	}

	.info_card {
		position: relative;
		padding:-15px;
	    
	    margin: 0px 0px 0px 15px;
	    width: 23%;
	}
	
	.chart_card {
		padding: -30px 0 0 0;
		margin: 0px 0px 0px 15px;
	}
	
	.large_card {
		position: relative;
		padding:-15px;
	    margin: 15px 0px 15px 15px;
	}
	
</style>

</@override>

<@override name="page_content">
    <div class="layui-fluid">
    
    	<!-- info start -->
    	<div class="layui-row">
    		<div class="layui-col-md3 card info_card panel panel-default">
				<div class="panel-body">
					<div>
						<h5>授权信息</h5>
						<span class="label label-success">授权时间 ${data.info.REGISTER_TIME }</span>
						<span class="label label-success">永久生效</span>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md3 card info_card panel panel-default">
				<div class="panel-body">
					<div>
						<h5>计算机信息</h5>
						<span class="label label-info">${data.info.COMPUTER_OS_VERSION }</span>
						<span class="label label-info">${data.info.COMPUTER_OS_BIT }</span>
						<span class="label label-info">${data.info.COMPUTER_NET_MAC }</span>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md3 card info_card panel panel-default">
				<div class="panel-body">
					<div>
						<h5>服务器信息</h5>
						<span class="label label-info">已运行 ${data.info.DAM_START_TIME }分钟</span>
						<span class="label label-warning">OCR线程 ${data.info.THREAD_ACTIVE }</span>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md3 card info_card panel panel-default">
				<div class="panel-body">
					<div>
						<h5>版本信息</h5>
						<span class="label label-info">DAM V${data.info.DAM_VERSION }</span>
						<span class="label label-info">SCRIPT V${data.info.DAM_SCRIPT_VERSION }</span>
					</div>
				</div>
			</div>
    	</div>
    	<!-- info end -->
    	
    	<!-- large info start -->
    	<div class="layui-row">
    		<div class="layui-col-md2 card large_card panel panel-default">
				<div class="panel-body">
					<div style="text-align: center">
						<h2 class="text-success">
						<#if data.parse_success == 0 >
							0
						<#else>
							${(data.parse_success / (data.parse_success + ocr_faild_count + ftr_faild_count)) * 100  }
						</#if>
						&nbsp;
						<span style="font-size: 14px;">%</span></h2>
						<h6 class="text-muted">解析成功占比</h6>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md2 card large_card panel panel-default">
				<div class="panel-body">
					<div style="text-align: center">
						<h2 class="text-primary">${data.fileCount}&nbsp;<span style="font-size: 14px;">个</span></h2>
						<h6 class="text-muted">已管理文件数</h6>
					</div>
				</div>
			</div>
			
			<div class="layui-col-md2 card large_card panel panel-default">
				<div class="panel-body">
					<a href="${basePath}/parse/manage#tab=ocr-faild" style="text-decoration:none;">
						<div style="text-align: center">
							<h2 class="text-danger">${ocr_faild_count }&nbsp;<span style="font-size: 14px;">个</span></h2>
							<h6 class="text-muted">OCR 失败</h6>
						</div>
					</a>
				</div>
			</div>
			
			<div class="layui-col-md2 card large_card panel panel-default">
				<div class="panel-body">
					<a href="${basePath}/parse/manage#tab=ftr-faild" style="text-decoration:none;">
						<div style="text-align: center">
							<h2 class="text-danger">${ftr_faild_count }&nbsp;<span style="font-size: 14px;">个</span></h2>
							<h6 class="text-muted">加入搜索引擎失败</h6>
						</div>
					</a>
				</div>
			</div>
    	</div>
    	<!-- large info end -->
    	    	
    	<!-- chart start -->
    	<div class="layui-row layui-col-md4">
    		<div class="card chart_card panel panel-default">
				<div class="panel-body">
					<div id="data-pie" style="width: 90%; height: 300px;"></div>
				</div>
			</div>
    	</div>
    	
    	<div class="layui-row layui-col-md8">
    		<div class="card chart_card panel panel-default">
				<div class="panel-body">
					<div id="data-search-word" style="width: 90%; height: 300px;"></div>
				</div>
			</div>
    	</div>
    	<!-- chart end -->
    	
    	<!-- 处理 freemarker 数据 -->
    	<#assign pie_data = data.pie_data />
    	<#assign hot_words_data = data.hot_words_data />
    	
    </div>
</@override>

<@override name="scripts">
<@super />

<script type="text/javascript" src="${basePath}/assets/vendor/echarts/echarts.min.js"></script>
<script>

//处理 pie 的数据

function getPieData(){
	var pie_size = '${data.pie_data?size}';  
	var pie_data = [];
	if(pie_size != 0){
		<#list pie_data as item >  
			pie_data.push({value:'${item.value}',name:'${item.name}'});
		</#list>  
	}else{
		pie_data = [];
	}
	
	return pie_data;
}

//处理 word 的数据
var words_size = '${data.hot_words_data?size}'; 
var word_name_data = new Array();
var word_num_data = new Array();

if(words_size != 0){
	<#list hot_words_data as item >  
		word_name_data.push('${item.word}');
		word_num_data.push('${item.num}');
	</#list>  
}else{
	word_name_data = [];
	word_num_data = [];
}

</script>
<!-- 放在加载数据后执行 -->
<script type="text/javascript" src="${basePath}/assets/js/management/dashboard.js"></script>
</@override>

<@extends name="/management/common/management_base.ftl"/> 
