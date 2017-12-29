(function(window,$){

    //系统常量

    var ChatConstants = {
        HOST : "192.168.1.103",//真实的为 domain

    };


    var Chat = {};

    window.nuna_chat_init = function(key){

        validationDomain(key);
    };

    function validationDomain(key){
        var url = "http://"+ChatConstants.HOST+":8080/nunachat/auth/domain?callback=?";
        var data = {"host":document.domain, "siteKey":key};

        // var wrap=document.createElement("div");
        // wrap.innerHTML = "12312312323";
        // wrap.setAttribute("id","123");
        // var last = document.body.lastChild;//得到页面的第一个元素
        // document.body.insertBefore(wrap,last);


        $.getJSON(url, data,function(result) {
            console.log(result);
            if(result.success){
                loadBootStrap();
            }else{
                alert("no");
            }
        });

    }

    /**
     * 加载 bootstrap
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2017/12/29 下午5:52
     */
    function loadBootStrap(){

        var bootstrap_css = document.createElement("link");
        bootstrap_css.rel = "stylesheet";
        bootstrap_css.href = "https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css";

        var c = document.getElementsByTagName("link")[0];
        c.parentNode.insertBefore(bootstrap_css, c);

        var bootstrap_js = document.createElement("script");
        bootstrap_js.src = "https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bootstrap_js, s);

    }



    /**
     * ajax get 请求
     *
     * @param url
     * @param data
     * @param callback
     */
    function ajax_doGet(url,data,callback){
        $.get(url,data, function(result){
            if(typeof callback === "function"){
                callback(result);
            }
        });
    }

    /**
     * ajax post 请求
     *
     * @param url
     * @param data
     * @param callback
     */
    function ajax_doPost(url,data,callback){
        $.post(url,data,function(result){


            if(typeof callback === "function"){
                callback(result);
            }
        },"json")
    }


})(window,jQuery);