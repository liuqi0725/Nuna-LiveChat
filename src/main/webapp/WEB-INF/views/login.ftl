<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>NUNA-LiveChat Login</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/statics/css/login.css"/>

</head>
<body>


<div class="wrapper fadeInDown">

    <#if message??>
        <div class="alert alert-warning alert-dismissible" id="alert">
            <span id="message">${message}</span>
        </div>
    </#if>
    <div class="formContent">
        <!-- Tabs Titles -->
        <h2 class="active" id="login-btn"> 登陆 Nuna </h2>
        <h2 class="inactive underlineHover" id="register-btn"> 免费注册 </h2>

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="${basePath}/statics/image/logo-2.png" id="icon" alt="User Icon" />
        </div>

        <!-- Login Form -->
        <form action="" id="userForm" method="post">
            <input type="text" id="name" class="second" name="name" placeholder="姓名">
            <input type="text" id="email" class="fadeIn second" name="email" placeholder="邮箱">
            <input type="text" id="phone" class="second" name="phone" placeholder="移动电话">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="密码">

            <input type="hidden" id="submit-type" value="login">
            <input type="button" class="fadeIn fourth" id="submit-btn" value="登 陆">
        </form>

        <!-- Remind Passowrd -->
        <div id="formFooter">
            <a class="underlineHover" href="#">忘记密码?</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${basePath}/statics/vendor/jquery-1.12.4.js"></script>
<script>

    $("#register-btn").click(function(){
        switchAction("register");
    });

    $("#login-btn").click(function(){
        switchAction("login");
    });

    $("#submit-btn").click(function(){

        var url = "";
        if($("#submit-type").val() === "login"){
            url = "/login";
        }else{
            url = "/register";
        }

        $("#userForm").attr("action",url);
        $("#userForm").submit();

    });

    function switchAction(act){
        $("#name").val("");
        $("#password").val("");
        $("#phone").val("");
        $("#email").val("");

        if(act === "login"){
            $("#password").val("");
            $("#login-btn").attr("class","active");
            $("#register-btn").attr("class","inactive underlineHover");
            $("#formFooter").show();
            $("#submit-btn").val("登陆");
            $("#email").attr("placeholder","Nuna 账号[邮箱]");
            $("#submit-type").val("login");

            $("#name").hide();
            $("#phone").hide();
        }else{
            $("#name").show();
            $("#phone").show();

            $("#register-btn").attr("class","active");
            $("#login-btn").attr("class","inactive underlineHover");
            $("#formFooter").hide();
            $("#submit-btn").val("免费注册");
            $("#email").attr("placeholder","您的常用邮箱");
            $("#submit-type").val("register");
        }

    }

</script>
<#if register??>
<script>
    switchAction("register");
</script>
<#else>
<script>
    switchAction("login");
</script>
</#if>


</html>
