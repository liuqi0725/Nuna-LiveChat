(function(window){

    var NUNA_CHAT = {};

    window.NUNA_CHAT = NUNA_CHAT;

    /**
     * 系统常量
     * @type {{HOST: string, WELCOME_MSG: string, PAGE_MODEL_OFFLINE: string, PAGE_MODEL_ONLINE: string, PAGE_MODEL_LOGIN: string, USER_LOGIN_BTN_MSG: string, USER_LOGIN_USERNAME_QUE: string, USER_LOGIN_PHONE_QUE: string, COOKIE_USERNAME: string, COOKIE_USERPHONE: string, COOKIE_EMAIL: string, COOKIE_FIRST_LOGIN: string, COOKIE_LAST_CONNECT: string, COOKIE_DEFAULT_USERNAME: string, COOKIE_EXPIRES: number, COOKIE_PATH: string}}
     */
    NUNA_CHAT.Connstants = {
        HOST : "192.168.1.103:8080",//真实的为 domain
        WELCOME_MSG : "您好，请问您需要什么帮助吗？",

        PAGE_MODEL_OFFLINE : "offline",//离线
        PAGE_MODEL_ONLINE : "online",//可以聊天
        PAGE_MODEL_LOGIN : "login",//登录

        USER_LOGIN_BTN_MSG : "开始聊天",
        USER_LOGIN_USERNAME_QUE : "您的姓名",
        USER_LOGIN_PHONE_QUE : "您的电话",

        // cookie
        COOKIE_USERNAME : "nuna-chat-username",
        COOKIE_USERPHONE : "nuna-chat-userphone",
        COOKIE_EMAIL : "nuna-chat-email",

        COOKIE_FIRST_LOGIN : "", //第一次来访时间
        COOKIE_LAST_CONNECT : "", //上次联系时间

        COOKIE_DEFAULT_USERNAME : "Guest-"+new Date().getTime(),
        COOKIE_EXPIRES : 3 , //cookie 过期时间,天
        COOKIE_PATH : "/",
    };

    /**
     * chat 设置
     * @type {{hasOnLine: boolean, hasSendWelcome: boolean, pageModel: string, pageMainDoc: null, user_head_url: string, agent_head_url: string, showInter: null, hideInter: null}}
     */
    NUNA_CHAT.Setting = {

        /**
         * 是否在线
         */
        hasOnLine : false,

        /**
         * 是否发起 welcome 信息
         */
        hasSendWelcome: false,

        /**
         * 页面显示模式
         */
        pageModel : NUNA_CHAT.Connstants.PAGE_MODEL_LOGIN,

        /**
         * 页面的document
         */
        pageMainDoc : null,

        /**
         * 用户默认头像
         */
        user_head_url : "http://"+NUNA_CHAT.Connstants.HOST+"/statics/image/user.png",

        /**
         * 坐席默认头像
         */
        agent_head_url : "http://"+NUNA_CHAT.Connstants.HOST+"/statics/image/agent.png",

        /**
         * 打开窗口时 interval 对象
         */
        showInter : null,

        /**
         * 关闭窗口时 interval 对象
         */
        hideInter : null


};

    /**
     * 初始化
     * @param sitekey
     */
    window.NUNA_CHAT.Init = function(sitekey){
        /**
         * 初始化 chat 配置
         * @author liu.qi
         * @email liuqi_0725@aliyun.com
         * @date 2017/12/29 下午9:13
         */
        // var url = "http://"+NUNA_CHAT.Connstants.HOST+"/auth/domain?callback=?";
        // var data = {"host":document.domain, "siteKey":sitekey};
        // $.getJSON(url, data,function(result) {
        //     // console.log(result);
        //     if(result.success){
        //         NUNA_CHAT.Page.init();
        //     }
        // });

        jsonp({
            url: "http://"+NUNA_CHAT.Connstants.HOST+"/nunachat/auth/domain",
            data: {"host":document.domain, "siteKey":sitekey},
            success:function(res){
                if(res.success){
                    NUNA_CHAT.Page.init();
                }
            }
        });

    };

    /**
     *
     * @type {{init: init, loadChatAssets: loadChatAssets, setChatPageModel: setChatPageModel, __createChatMainHTML: __createChatMainHTML, __innerChatHeadHTML: __innerChatHeadHTML, __innerChatBodyHTML: __innerChatBodyHTML, __innerChatFooterHTML: __innerChatFooterHTML, __innerChatWebPageWakeUp: __innerChatWebPageWakeUp}}
     */
    NUNA_CHAT.Page = {

        /**
         * 初始化页面
         */
        init : function(){
            this.loadChatAssets();
            this.setChatPageModel();

            var last = document.body.lastChild;//得到页面的最后一个元素

            var mainDOC = this.__createChatMainHTML();
            mainDOC.appendChild(this.__innerChatHeadHTML());
            mainDOC.appendChild(this.__innerChatBodyHTML());
            mainDOC.appendChild(this.__innerChatFooterHTML());

            //将聊天窗口插入进去
            document.body.insertBefore(mainDOC,last);
            NUNA_CHAT.Setting.pageMainDoc = mainDOC;
            //插入唤醒按钮
            this.__innerChatWebPageWakeUp(last);
        },

        /**
         * 加载 chat 资源文件
         * @author liu.qi
         * @email liuqi_0725@aliyun.com
         * @date 2018/1/2 上午10:58
         */
        loadChatAssets : function(){
            var obj_link = document.getElementsByTagName("link")[0];
            //需要解决用户页面没有 link 的情况
            var css_link = null;
            //bootstrap
            // css_link = document.createElement("link");
            // css_link.rel = "stylesheet";
            // css_link.href = "https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css";
            // obj_link.parentNode.insertBefore(css_link, obj_link);

            //nunachat
            css_link = document.createElement("link");
            css_link.rel = "stylesheet";
            css_link.href = "http://"+NUNA_CHAT.Connstants.HOST+"/statics/css/nunachat.css";
            obj_link.parentNode.insertBefore(css_link, obj_link);

            // var bootstrap_js = document.createElement("script");
            // bootstrap_js.src = "https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js";
            // var s = document.getElementsByTagName("script")[0];
            // s.parentNode.insertBefore(bootstrap_js, s);

        },

        /**
         * 获取 chat 的 pagemodel
         * @author liu.qi
         * @email liuqi_0725@aliyun.com
         * @date 2018/1/2 上午11:00
         * @param loginState 用于人工切换登陆状态
         */
        setChatPageModel : function(loginState){

            var isLogin = loginState || NUNA_CHAT.User.getCookie();

            //获取坐席状态
            var agent_status = NUNA_CHAT.Agent.hasAgentOnline();

            if(isLogin && agent_status){
                //cookie 中有用户数据，并且坐席有人在线。状态未 online，可以聊天
                NUNA_CHAT.Setting.pageModel = NUNA_CHAT.Connstants.PAGE_MODEL_ONLINE;

                //获取用户服务器信息


            } else if (isLogin && !agent_status){
                //cookie 中有用户数据，但是坐席离线，显示 offline，用户只能提交问题单
                NUNA_CHAT.Setting.pageModel = NUNA_CHAT.Connstants.PAGE_MODEL_OFFLINE;

            } else if (!isLogin && !agent_status){
                //cookie 中有没有用户，并且坐席没有人在线，用户只能提交问题单
                NUNA_CHAT.Setting.pageModel = NUNA_CHAT.Connstants.PAGE_MODEL_OFFLINE;

            } else if (!isLogin && agent_status){
                //cookie 中有没有用户，坐席有人在线，用户需先登陆
                NUNA_CHAT.Setting.pageModel = NUNA_CHAT.Connstants.PAGE_MODEL_LOGIN;
            }

            console.log(NUNA_CHAT.Setting);

        },

        /**
         * 创建 main html
         * @returns {HTMLDivElement}
         * @private
         */
        __createChatMainHTML : function(){
            var chat_div = document.createElement("div");
            chat_div.id = "nuna-chat";
            //chat_div.className = "panel panel-primary chat_div";
            chat_div.className = "chat-panel chat-theme-primary chat-down-right-corner";

            chat_div.setAttribute("hide",true);

            return chat_div;

        },

        /**
         * 添加head html
         * @returns {HTMLDivElement}
         * @private
         */
        __innerChatHeadHTML : function(){
            //添加消息头
            var chat_head_div = document.createElement("div");
            chat_head_div.className = 'chat-panel-heading';
            var chat_head_span = document.createElement("span");
            chat_head_span.id = "chat-head";
            chat_head_span.innerHTML = "Nuna-LiveChat";
            chat_head_div.appendChild(chat_head_span);

            /////关闭按钮
            var chat_head_close_a = document.createElement("a");
            chat_head_close_a.innerHTML = "X";
            chat_head_close_a.className = "fr";
            chat_head_close_a.style.color = "white";
            chat_head_close_a.href = "javascript:void(0)";
            chat_head_close_a.onclick = function(){
                showChatWin();
            };
            chat_head_div.appendChild(chat_head_close_a);

            return chat_head_div;

        },

        /**
         * 添加消息 body
         * @param rebuild
         * @returns {*|HTMLElement|null|HTMLDivElement}
         * @private
         */
        __innerChatBodyHTML : function(rebuild){
            var chat_body_div = rebuild && document.getElementById("chat-panel-body") || document.createElement("div");

            chat_body_div.id = chat_body_div.id || "chat-panel-body";
            chat_body_div.className = chat_body_div.className || "chat-panel-body";

            rebuild ? chat_body_div.innerHTML = "" : "";

            //添加消息体
            // chat_body_div.className = "chat-panel-body";
            // chat_body_div.id = "chat-panel-body";

            // console.log(this.setting.pageModel);
            ///坐席全部离线的消息体
            if(NUNA_CHAT.Setting.pageModel === NUNA_CHAT.Connstants.PAGE_MODEL_OFFLINE){

            }

            ///用户 cookie 失效，需要登录的消息体
            if(NUNA_CHAT.Setting.pageModel === NUNA_CHAT.Connstants.PAGE_MODEL_LOGIN){

                var chat_body_login_div = document.createElement("div");
                chat_body_login_div.className = "chat-login-div";

                var chat_body_login_title_username = document.createElement("p");
                chat_body_login_title_username.innerHTML = NUNA_CHAT.Connstants.USER_LOGIN_USERNAME_QUE;
                chat_body_login_title_username.className = "chat-login-title";
                chat_body_login_div.appendChild(chat_body_login_title_username);

                var chat_body_login_username = document.createElement("input");
                chat_body_login_username.type = "text";
                chat_body_login_username.className = "chat-login-username";
                chat_body_login_username.id = "chat_login_uesrname";
                chat_body_login_div.appendChild(chat_body_login_username);

                var chat_body_login_title_phone = document.createElement("p");
                chat_body_login_title_phone.innerHTML = NUNA_CHAT.Connstants.USER_LOGIN_PHONE_QUE;
                chat_body_login_title_phone.className = "chat-login-title";
                chat_body_login_div.appendChild(chat_body_login_title_phone);

                var chat_body_login_phone = document.createElement("input");
                chat_body_login_phone.type = "text";
                chat_body_login_phone.className = "chat-login-username";
                chat_body_login_phone.id = "chat_login_phone";
                chat_body_login_div.appendChild(chat_body_login_phone);

                var chat_body_login_btn = document.createElement("input");
                chat_body_login_btn.type = "button";
                chat_body_login_btn.value = NUNA_CHAT.Connstants.USER_LOGIN_BTN_MSG;
                chat_body_login_btn.className = "chat-login-btn";
                chat_body_login_btn.onclick = function(){
                    NUNA_CHAT.User.login();
                };
                //////回车事件
                document.onkeydown = function(e){
                    if(!e) e = window.event;//火狐中是 window.event
                    if((e.keyCode || e.which) === 13){
                        NUNA_CHAT.User.login();
                    }

                };
                chat_body_login_div.appendChild(chat_body_login_btn);

                chat_body_div.appendChild(chat_body_login_div);
            }

            return chat_body_div;

        },


        /**
         * 添加底部
         * @param rebuild
         * @returns {*|HTMLElement|null|HTMLDivElement}
         * @private
         */
        __innerChatFooterHTML : function(rebuild){

            //添加消息底部
            var chat_footer_div = rebuild && document.getElementById("chat-panel-footer") || document.createElement("div");
            chat_footer_div.id = chat_footer_div.id || "chat-panel-footer";
            chat_footer_div.className = chat_footer_div.className || "chat-panel-footer chat-web-footer";

            var powered_p = rebuild && document.getElementById("chat-powered") || document.createElement("p");
            powered_p.id = powered_p.id || "chat-powered";

            var chat_footer_container_div = rebuild && document.getElementById("chat-footer-container") || document.createElement("div");
            chat_footer_container_div.id = chat_footer_container_div.id || "chat-footer-container";

            if(!rebuild){
                powered_p.innerHTML="Powered by Alexliu";
                ///////////Powered by Alexliu 添加到 div
                chat_footer_div.appendChild(powered_p);
            }

            if(NUNA_CHAT.Setting.pageModel === NUNA_CHAT.Connstants.PAGE_MODEL_ONLINE){
                // chat_footer_container_div.className = "container-fluid";
                var chat_footer_container_msg_input_div = document.createElement("div");
                chat_footer_container_msg_input_div.className = "nuna-inline";

                // var chat_footer_container_form = document.createElement("form");
                // chat_footer_container_form.className = "form-inline";
                // var chat_footer_container_form_group = document.createElement("div");
                // chat_footer_container_form_group.className = "form-group";

                var chat_footer_container_form_group_text = document.createElement("input");
                chat_footer_container_form_group_text.type = "text";
                chat_footer_container_form_group_text.className = "chat-msg-input";
                chat_footer_container_form_group_text.id = "nuna-chat-msg-send";
                chat_footer_container_form_group_text.setAttribute("placeholder","发送消息");
                ///////////发送按钮添加到 form-group
                chat_footer_container_msg_input_div.appendChild(chat_footer_container_form_group_text);

                var chat_footer_container_form_submit_btn = document.createElement("input");
                chat_footer_container_form_submit_btn.type = "button";
                chat_footer_container_form_submit_btn.className = "chat-msg-btn";
                chat_footer_container_form_submit_btn.value = "发送";
                chat_footer_container_form_submit_btn.onclick = function(){
                    var msg = document.getElementById("nuna-chat-msg-send").value;
                    NUNA_CHAT.User.sendMessage(msg);
                };
                //////回车事件
                document.onkeydown = function(e){
                    if(!e) e = window.event;//火狐中是 window.event
                    if((e.keyCode || e.which) === 13){
                        var msg = document.getElementById("nuna-chat-msg-send").value;
                        NUNA_CHAT.User.sendMessage(msg);
                    }
                };

                ///////////发送输入框，按钮。添加到 form
                //chat_footer_container_form.appendChild(chat_footer_container_form_group);
                chat_footer_container_msg_input_div.appendChild(chat_footer_container_form_submit_btn);
                ///////////form添加到 container
                chat_footer_container_div.appendChild(chat_footer_container_msg_input_div);

            }

            chat_footer_div.insertBefore(chat_footer_container_div,powered_p);


            return chat_footer_div;

        },

        /**
         * 添加唤醒
         * @private
         */
        __innerChatWebPageWakeUp : function(webPageLastElement){
            //右下角呼出按钮
            var chat_btn_div = document.createElement("div");
            chat_btn_div.innerHTML = "Nuna-LiveChat";
            chat_btn_div.id = "nuna-chat-btn";
            chat_btn_div.className = "chat-wake-up";
            chat_btn_div.onclick = function(){
                NUNA_CHAT.SwitchChatWin();
            };
            document.body.insertBefore(chat_btn_div,webPageLastElement);
        }
    };


    /**
     * 用户对象
     * @type {{username, phone, email, first_login, last_connect, loadUserInfoFromServer, getCookie, updateUserInfo2Server, updateUserInfo2Cookie, login, sendMessage}}
     */
    NUNA_CHAT.User = (function(){

        return {

            username : "",

            phone : "",

            email : "",

            first_login : "",

            last_connect : "",

            /**
             * 从服务端获取用户信息
             */
            loadUserInfoFromServer : function(){

            },

            /**
             * 从 cookie 中获取用户信息
             */
            getCookie : function(){
                var cookie_username = ChatCookie.getCookie(NUNA_CHAT.Connstants.COOKIE_USERNAME);

                //如果用户首次进入，或者 cookie 过期，保存用户 cookie
                if(cookie_username === ""){
                    return false;
                }else{
                    //获取当前用户信息
                    this.username = cookie_username;
                    this.phone = ChatCookie.getCookie(NUNA_CHAT.Connstants.COOKIE_USERPHONE);
                    this.email = ChatCookie.getCookie(NUNA_CHAT.Connstants.COOKIE_EMAIL);
                    this.first_login = ChatCookie.getCookie(NUNA_CHAT.Connstants.COOKIE_FIRST_LOGIN);
                    this.last_connect = ChatCookie.getCookie(NUNA_CHAT.Connstants.COOKIE_LAST_CONNECT);
                }

                return true;
            },

            /**
             * 修改服务器上的 user 信息
             */
            updateUserInfo2Server : function(){

            },

            /**
             * 修改 cookie 里的信息
             */
            updateUserInfo2Cookie : function(){

            },

            /**
             * 登陆
             */
            login : function(){
                var username = document.getElementById("chat_login_uesrname").value;
                var phone = document.getElementById("chat_login_phone").value;

                //验证 与 添加到 cookie

                if(NUNA_CHAT.Validation.checkInput("chat_login_uesrname")
                    && NUNA_CHAT.Validation.checkInput("chat_login_phone")){
                    ChatCookie.setCookie(
                        NUNA_CHAT.Connstants.COOKIE_USERNAME,
                        username,
                        NUNA_CHAT.Connstants.COOKIE_EXPIRES,
                        NUNA_CHAT.Connstants.COOKIE_PATH
                    );

                    ChatCookie.setCookie(
                        NUNA_CHAT.Connstants.COOKIE_USERPHONE,
                        phone,
                        NUNA_CHAT.Connstants.COOKIE_EXPIRES,
                        NUNA_CHAT.Connstants.COOKIE_PATH
                    );

                    //添加到对象里
                    NUNA_CHAT.User.username = username;
                    NUNA_CHAT.User.phone = phone;

                    //更新界面,手动设置 chatlogin 状态为 true
                    NUNA_CHAT.Page.setChatPageModel(true);
                    NUNA_CHAT.Page.__innerChatBodyHTML(true);
                    //添加底部
                    NUNA_CHAT.Page.__innerChatFooterHTML(true);
                    //发送欢迎
                    NUNA_CHAT.Message.sendWelcomeMsg();
                    NUNA_CHAT.Message.userInputOnFoce();

                    //建立 ws 连接
                    if(!NUNA_CHAT.Setting.hasOnLine){
                        NUNA_CHAT.WS.initialize();
                        NUNA_CHAT.Setting.hasOnLine = true;
                    }
                }

            },

            /**
             * 发送信息
             * @param msg
             */
            sendMessage : function(msg){

                //添加到窗口
                NUNA_CHAT.Message.addUserMsg(msg);
                //发送到服务端

                NUNA_CHAT.WS.sendMessage(msg);

            }


        }

    }());

    /**
     * 坐席对象
     * @type {{hasAgentOnline}}
     */
    NUNA_CHAT.Agent = (function(){

        return {

            /**
             * 是否有坐席在线
             * @returns {boolean}
             */
            hasAgentOnline : function(){

                return true;
            }


        }

    }());

    /**
     * 验证用户输入
     * @type {{validation, setError, setNormal, isEmpty, isSqlInjection}}
     */
    NUNA_CHAT.Validation = (function(){

        return {

            /**
             * 全部验证
             * @param e_id
             * @returns {boolean}
             */
            checkInput : function(e_id){
                var p_str = document.getElementById(e_id).value;

                if(this.isEmpty(p_str)){
                    this.setError(e_id,"内容不能为空。");
                    return false;
                } else{
                    this.setNormal(e_id);
                }

                return true;

            },

            /**
             * 添加 error class
             * @param e_id
             */
            setError : function(e_id,msg){

                var targetElement = document.getElementById(e_id);

                var _class_names = targetElement.className;

                if(_class_names.indexOf("input-error") === -1){
                    targetElement.className += " input-error";
                    //错误信息
                    var error_p = document.createElement("p");
                    error_p.className = "input-error-msg";
                    error_p.innerHTML = msg;

                    domOperation.insertAfter(error_p,targetElement);
                }
            },

            /**
             * 去掉 error class
             * @param e_id
             */
            setNormal : function(e_id){
                var targetElement = document.getElementById(e_id);

                var _class_names = targetElement.className;

                if(_class_names.indexOf("input-error") !== -1){
                    _class_names = _class_names.substring(0,_class_names.indexOf("input-error"));
                    //错误信息
                    domOperation.removeNode(targetElement.nextSibling);
                }

                document.getElementById(e_id).className = _class_names;

            },

            /**
             * 是否为空
             * @param p_str
             * @returns {boolean}
             */
            isEmpty : function(p_str){
                if(p_str === "" || p_str === undefined){
                    return true;
                }
                return false;
            },

            /**
             * 防止 SQL 注入攻击
             * @param p_str
             * @returns {{successful: boolean, hint: *}}
             */
            isSqlInjection : function (p_str) {
                var regex = /(\sand\s)|(\sor\s)|(\slike\s)|(select\s)|(insert\s)|(delete\s)|(update\s[\s\S].*\sset)|(create\s)|(\stable)|(\sexec)|(declare)|(\struncate)|(\smaster)|(\sbackup)|(\smid)|(\scount)|(\sadd\s)|(\salter\s)|(\sdrop\s)|(\sfrom\s)|(\struncate\s)|(\sunion\s)|(\sjoin\s)|(')/;
                var result = [];
                var successful = !regex.test(p_str);
                var matches = p_str.match(regex);
                var hint = p_str;
                if (matches && matches[0]) {
                    hint = matches[0];
                }

                return {
                    successful : successful,
                    hint : hint
                };
            }
        }

    }());

    /**
     * 切换窗口
     * @constructor
     */
    NUNA_CHAT.SwitchChatWin = function(){
        var chat_div = document.getElementById("nuna-chat");

        var hideAttribute = chat_div.attributes.getNamedItem("hide").value;

        //如果是隐藏的,显示chat的窗口
        if (hideAttribute === 'true'){
            chat_div.style.display = "block";
            //设置 hide 属性为 false
            chat_div.attributes.getNamedItem("hide").value = "false";
            //隐藏 chat-btn
            document.getElementById("nuna-chat-btn").style.display = "none";
            //每0.2毫秒执行一次变换高度，实现效果为淡入淡出
            NUNA_CHAT.Setting.showInter = setInterval("NUNA_CHAT.changeChatHeight('up')",2);

            //online 的情况下发送欢迎语
            if(NUNA_CHAT.Setting.pageModel === NUNA_CHAT.Connstants.PAGE_MODEL_ONLINE
                && !NUNA_CHAT.Setting.hasSendWelcome){
                //如果用户没有会话，发送欢迎
                NUNA_CHAT.Message.sendWelcomeMsg();
            }

            //焦点定位
            NUNA_CHAT.Message.userInputOnFoce();

        } else {
            chat_div.attributes.getNamedItem("hide").value = "true";
            NUNA_CHAT.Setting.hideInter = setInterval("NUNA_CHAT.changeChatHeight('down')",2);

        }
    };


    /**
     * 修改 chat div 高度
     * @author liu.qi
     * @email liuqi_0725@aliyun.com
     * @date 2017/12/29 下午10:04
     * @param action  动作 up 向上展示， down 向下隐藏
     */
    window.NUNA_CHAT.changeChatHeight = function(action) {
        var chat_div = document.getElementById("nuna-chat");
        //获取chat_div 高度
        var height = chat_div.style.height === "" ? 0 : parseInt(chat_div.style.height);
        if(action === "up"){

            //chat 的div 最大高度400
            if(height <= 400){
                //每次增加4px
                chat_div.style.height = (height+4).toString()+"px";
            } else{
                //满足条件后删除 showInter
                clearInterval(NUNA_CHAT.Setting.showInter);
            }
        }
        if(action === "down"){
            if(height >= 4){
                //每次减少4px
                chat_div.style.height = (height-4).toString()+"px";
            }
            else{
                //满足条件后删除 hideInter
                clearInterval(NUNA_CHAT.Setting.hideInter);
                chat_div.style.display="none"; //隐藏DIV
                //显示 chat-btn
                document.getElementById("nuna-chat-btn").style.display = "block";
            }
        }
    };


    /**
     * 消息对象
     * @type {{sendWelcomeMsg, clearUserInput, userInputOnFoce, addUserMsg, addAgentMsg, addConsole}}
     */
    NUNA_CHAT.Message = (function(){

        return {

            /**
             * 发送欢迎消息
             */
            sendWelcomeMsg : function(){
                this.addAgentMsg(NUNA_CHAT.Connstants.WELCOME_MSG);
            },

            /**
             * 清空聊天框输入
             */
            clearUserInput : function(){
                document.getElementById("nuna-chat-msg-send").value = "";
            },

            /**
             * 用户输入焦点定位
             */
            userInputOnFoce : function(){

                if (NUNA_CHAT.Setting.pageModel === NUNA_CHAT.Connstants.PAGE_MODEL_LOGIN){
                    document.getElementById("chat_login_uesrname").focus();
                }

                if (NUNA_CHAT.Setting.pageModel === NUNA_CHAT.Connstants.PAGE_MODEL_ONLINE){
                    document.getElementById("nuna-chat-msg-send").focus();
                }

            },

            /**
             * 添加用户消息
             * @param msg
             */
            addUserMsg : function(msg){
                var msg_div = document.createElement("div");
                msg_div.className = "chat-msg-item";

                var msg_content_div = document.createElement("div");
                msg_content_div.className = "chat-msg-item-left";

                var msg_user_name_p = document.createElement("p");
                msg_user_name_p.innerHTML = NUNA_CHAT.User.username;
                msg_user_name_p.className = "chat-msg-name";
                msg_content_div.appendChild(msg_user_name_p);

                var msg_head_div_img = document.createElement("img");
                msg_head_div_img.className = "chat-msg-item-head";
                msg_head_div_img.src = NUNA_CHAT.Setting.user_head_url;

                msg_content_div.appendChild(msg_head_div_img);

                var msg_body = document.createElement("div");
                msg_body.className = "chat-msg-item-body chat-msg-user";
                msg_body.innerHTML = msg;

                msg_content_div.appendChild(msg_body);

                msg_div.appendChild(msg_content_div);


                var firstChild = document.getElementById("chat-panel-body").firstChild;

                document.getElementById("chat-panel-body").insertBefore(msg_div,firstChild);

                this.clearUserInput();

                //焦点定位
                this.userInputOnFoce();
            },

            /**
             * 添加坐席消息
             * @param msg
             */
            addAgentMsg : function(msg){
                var msg_div = document.createElement("div");
                msg_div.className = "chat-msg-item";

                var msg_content_div = document.createElement("div");
                msg_content_div.className = "chat-msg-item-right";

                var msg_head_div_img = document.createElement("img");
                msg_head_div_img.className = "chat-msg-item-head";
                msg_head_div_img.src = NUNA_CHAT.Setting.agent_head_url;
                msg_content_div.appendChild(msg_head_div_img);

                var msg_body = document.createElement("div");
                msg_body.className = "chat-msg-item-body chat-msg-agent";
                msg_body.innerHTML = msg;
                msg_content_div.appendChild(msg_body);

                msg_div.appendChild(msg_content_div);

                var firstChild = document.getElementById("chat-panel-body").firstChild;

                document.getElementById("chat-panel-body").insertBefore(msg_div,firstChild);
            },

            /**
             * 添加 console 信息
             * @param msg
             * @param error
             */
            addConsole : function(msg,error){

                var console_center = document.getElementById("chat-console-id") || document.createElement("center");

                console_center.className = error ? "chat-console-error" : "chat-console-nomarl";
                console_center.id = "chat-console-id";
                console_center.innerHTML = msg;

                if(!document.getElementById("chat-console-id")){
                    var firstChild = document.getElementById("chat-panel-body").firstChild;
                    document.getElementById("chat-panel-body").insertBefore(console_center,firstChild);
                }

            }

        };
    }());

    /**
     * 与服务器通信对象
     * @type {{socket: null, connect: Function, initialize: initialize, sendMessage: Function}}
     */
    NUNA_CHAT.WS = {

        socket : null,

        msgQueue : [],

        online : false,

        connect : (function(host) {

            if ('WebSocket' in window) {
                this.socket = new WebSocket(host);
            } else if ('MozWebSocket' in window) {
                this.socket = new MozWebSocket(host);
            } else {
                NUNA_CHAT.Message.addConsole('错误: 此浏览器不支持 WebSocket 服务.',true);
                return;
            }

            this.socket.onopen = function (evt) {
                NUNA_CHAT.Message.addConsole('正在连接，请稍后',false);

                // document.getElementById('chat').onkeydown = function(event) {
                //     if (event.keyCode === 13) {
                //         Chat.sendMessage();
                //     }
                // };

                console.log(evt);
            };

            this.socket.onclose = function (evt) {
                //document.getElementById('chat').onkeydown = null;
                NUNA_CHAT.Message.addConsole('聊天已关闭。再见。',false);

            };

            this.socket.onmessage = function (evt) {



                NUNA_CHAT.Message.addAgentMsg(evt.data);

            };

            this.socket.onerror = function(evt){
                NUNA_CHAT.Message.addConsole(evt.data)
            };
        }),

        initialize : function() {
            if (window.location.protocol === 'http:') {
                this.connect('ws://' + NUNA_CHAT.Connstants.HOST + '/nunachat/connect/'+NUNA_CHAT.User.username + "/"+NUNA_CHAT.User.phone);
            } else {
                this.connect('wss://' + NUNA_CHAT.Connstants.HOST + '/nunachat/connect'+NUNA_CHAT.User.username + "/"+NUNA_CHAT.User.phone);
            }
        },

        sendMessage : (function(message) {

            //建立 ws 连接
            if(!this.online){
                this.initialize();
                this.online = true;
                this.msgQueue.push(message);
            }else{
                if (message !== '') {
                    this.socket.send(message);
                }
            }
        }),

        // var Console = {};
        //
        // Console.log = (function(message) {
        //     var console = document.getElementById('console');
        //     var p = document.createElement('p');
        //     p.style.wordWrap = 'break-word';
        //     p.innerHTML = message;
        //     console.appendChild(p);
        //     while (console.childNodes.length > 25) {
        //         console.removeChild(console.firstChild);
        //     }
        //     console.scrollTop = console.scrollHeight;
        // });

        // Chat.initialize();
    };


    var ChatCookie = {};

    /**
     * 设置 cookie
     * @param cname
     * @param cvalue
     * @param path
     * @param domain
     * @param secure
     */
    ChatCookie.setCookie = function(cname,cvalue,days,path,domain,secure){
        var cdata = cname + "=" + escape(cvalue);
        if(days) {
            var d = new Date();
            d.setTime(d.getTime() + (days * 24 * 3600 * 1000));
            cdata += "; expires=" + d.toUTCString();
        }

        cdata +=path ? ("; path=" + path) : "" ;
        cdata +=domain ? ("; domain=" + domain) : "" ;
        cdata +=secure ? ("; secure=" + secure) : "" ;
        document.cookie = cdata;
    };

    /**
     * 获取 cookie
     * @param cname
     * @returns {string}
     */
    ChatCookie.getCookie = function(cname) {
        var reg = eval("/(?:^|;\\s*)" + cname + "=([^=]+)(?:;|$)/");
        return reg.test(document.cookie) ? unescape(RegExp.$1) : "";
    };

    /**
     * 移除 cookie
     * @param cname
     * @param path
     * @param domain
     */
    ChatCookie.removeCookie = function(cname,path,domain){
        ChatCookie.setCookie(cname,"",-1,path,domain);
    };

    /**
     * 格式化日期  yyyy.MM.dd hh:mm:ss
     * @param date
     * @returns {String}
     */
    function formatDateSimple(date) {
        var myyear = date.getFullYear();
        var mymonth = date.getMonth() + 1;
        var myweekday = date.getDate();
        var hour = date.getHours();
        var minute = date.getMinutes();
        var seconds = date.getSeconds();

        if (mymonth < 10) {
            mymonth = "0" + mymonth;
        }
        if (myweekday < 10) {
            myweekday = "0" + myweekday;
        }
        return (hour + ":" +minute+ ":" +seconds);
    }


    /**
     *
     * @type {{insertAfter, removeNode}}
     */
    var domOperation = (function(){

        return {

            /**
             * 插入到对象之后
             * @param newElement
             * @param targetElement
             */
            insertAfter : function(newElement, targetElement){
                var parent = targetElement.parentNode;
                if (parent.lastChild === targetElement) {
                    // 如果最后的节点是目标元素，则直接添加。因为默认是最后
                    parent.appendChild(newElement);
                }
                else {
                    parent.insertBefore(newElement, targetElement.nextSibling);
                    //如果不是，则插入在目标元素的下一个兄弟节点 的前面。也就是目标元素的后面
                }
            },

            /**
             * 删除节点
             * @param targetElement
             */
            removeNode : function(targetElement){
                var parent = targetElement.parentElement;
                parent.removeChild(targetElement);
            }
        }

    }());


    /**
     *
     * @param params
     *
     * {
     *  url,
     *  jsonp,
     *  data,
     * }
     */
    function jsonp(params){
        //创建script标签并加入到页面中
        var callbackName = 'jsonpCallback';
        var head = document.getElementsByTagName('head')[0];
        // 设置传递给后台的回调参数名
        params.data['callback'] = callbackName;

        //格式化 data 变成 ?x=1&y=2&callback=
        var data = formatParams(params.data);

        //添加到 script 到 head
        var script = document.createElement('script');
        head.appendChild(script);

        //创建jsonp回调函数
        window[callbackName] = function(json) {
            head.removeChild(script);
            clearTimeout(script.timer);
            window[callbackName] = null;
            params.success && params.success(json);
        };

        //发送请求
        script.src = params.url + '?' + data;

        //超时处理
        if(params.time) {
            script.timer = setTimeout(function() {
                window[callbackName] = null;
                head.removeChild(script);
                params.error && params.error({
                    message: '超时'
                });
            }, time);
        }
    }

    //格式化参数
    function formatParams(data) {
        var arr = [];
        for(var name in data) {
            arr.push(encodeURIComponent(name) + '=' + encodeURIComponent(data[name]));
        };
        // 添加一个随机数，防止缓存
        arr.push('v=' + random());
        return arr.join('&');
    }
    // 获取随机数
    function random() {
        return Math.floor(Math.random() * 10000 + 500);
    }


})(window);