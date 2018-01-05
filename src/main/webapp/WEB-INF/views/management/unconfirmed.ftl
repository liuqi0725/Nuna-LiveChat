
<@override name="title">NUNA - Management Dashboard</@override>

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

    .page-header {
        padding: 30px 0 0 0;
    }

</style>

</@override>

<@override name="page_content">
    <div class="layui-container">

        <div class="page-header">
            <h1>
                您好, ${Session.user.name}!
            </h1>

        </div>
        <hr/>
        <h3>您目前尚未激活您的账号。</h3>
        <br/>
        <p>
            你必须激活您的账号才能访问,及使用网站的所有内容.
            请检查您的 <mark>${Session.user.email}</mark> 邮箱,并通过激活邮件激活您的账号。
        </p>
        <br/>
        <p>
            没有收到激活的邮件?
            <a href="${basePath}/" class="layui-btn layui-btn-warm layui-btn-xs">重新发送</a>

        </p>
    </div>
</@override>

<@override name="scripts">
    <@super />

<#--<script type="text/javascript" src="${basePath}/assets/vendor/echarts/echarts.min.js"></script>-->
<#--<script>-->

    <#--//处理 pie 的数据-->

    <#--function getPieData(){-->
        <#--var pie_size = '${data.pie_data?size}';-->
        <#--var pie_data = [];-->
        <#--if(pie_size != 0){-->
		<#--<#list pie_data as item >-->
			<#--pie_data.push({value:'${item.value}',name:'${item.name}'});-->
        <#--</#list>-->
        <#--}else{-->
            <#--pie_data = [];-->
        <#--}-->

        <#--return pie_data;-->
    <#--}-->

    <#--//处理 word 的数据-->
    <#--var words_size = '${data.hot_words_data?size}';-->
    <#--var word_name_data = new Array();-->
    <#--var word_num_data = new Array();-->

    <#--if(words_size != 0){-->
	<#--<#list hot_words_data as item >-->
		<#--word_name_data.push('${item.word}');-->
		<#--word_num_data.push('${item.num}');-->
    <#--</#list>-->
    <#--}else{-->
        <#--word_name_data = [];-->
        <#--word_num_data = [];-->
    <#--}-->

<#--</script>-->
<!-- 放在加载数据后执行 -->
<#--<script type="text/javascript" src="${basePath}/assets/js/management/dashboard.js"></script>-->
</@override>

<@extends name="/management/common/management_base.ftl"/>
