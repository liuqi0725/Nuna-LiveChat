<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    String contentPath = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contentPath;

    //放入pageContext中
    pageContext.setAttribute("basePath",basePath);

%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >

    <link rel="stylesheet" href="${basePath}/statics/css/nunachat.css">
    <link rel="stylesheet" href="${basePath}/statics/vendor/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${basePath}/statics/vendor/bootstrapValidator/css/bootstrapValidator.css">
    <script type="application/javascript">
        var Chat = {};

        Chat.socket = null;

        Chat.connect = (function(host) {
            if ('WebSocket' in window) {
                Chat.socket = new WebSocket(host);
            } else if ('MozWebSocket' in window) {
                Chat.socket = new MozWebSocket(host);
            } else {
                Console.log('Error: WebSocket is not supported by this browser.');
                return;
            }

            Chat.socket.onopen = function () {
                Console.log('Info: WebSocket connection opened.');
                document.getElementById('chat').onkeydown = function(event) {
                    if (event.keyCode == 13) {
                        Chat.sendMessage();
                    }
                };
            };

            Chat.socket.onclose = function () {
                document.getElementById('chat').onkeydown = null;
                Console.log('Info: WebSocket closed.');
            };

            Chat.socket.onmessage = function (message) {
                Console.log(message.data);
            };
        });

        Chat.initialize = function() {
            if (window.location.protocol == 'http:') {
                Chat.connect('ws://' + window.location.host + '/websocket/chat');
            } else {
                Chat.connect('wss://' + window.location.host + '/websocket/chat');
            }
        };

        Chat.sendMessage = (function() {
            var message = document.getElementById('chat').value;
            if (message != '') {
                Chat.socket.send(message);
                document.getElementById('chat').value = '';
            }
        });

        var Console = {};

        Console.log = (function(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.innerHTML = message;
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        });

        Chat.initialize();


        //chat 的状态， 如果有一个坐席在服务，name 设置为1，默认为所有坐席为离线状态
        Chat.status = 1;


        document.addEventListener("DOMContentLoaded", function() {
            // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
            var noscripts = document.getElementsByClassName("noscript");
            for (var i = 0; i < noscripts.length; i++) {
                noscripts[i].parentNode.removeChild(noscripts[i]);
            }
        }, false);



        /**
         * 每60秒获取一次 chat 状态
         */
        setTimeout(getChatStatus,600000);

        /**
         * 获取 chat 的状态
         */
        function getChatStatus(){

        }

    </script>

</head>
<body>


    <div class="noscript">
        <h2 style="color: #ff0000">
            Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable
        Javascript and reload this page!
        </h2>
    </div>
    <div>
        <p>
            <input type="text" placeholder="type and press enter to chat" id="chat" />
        </p>
        <div id="console-container">
            <div id="console"/>
        </div>
    </div>


    <div>
        <button onclick="tips_pop()">开始聊天</button>
    </div>

    <div class="panel panel-primary chat_div" id="nuna-chat" hide="true">
        <div class="panel-heading">
            <span id="chat-head">开始聊天</span>
        </div>

        <div class="panel-body">

            <!-- agent -->
            <div class="media fr">

                <div class="media-body nuna-chat-msg agent-msg">
                    <%--<h4 class="media-heading">Media heading</h4>--%>
                    <span>您好，请问您需要什么帮助？</span>
                </div>

                <div class="media-right">
                    <a href="#">
                        <img class="media-object img-circle user-head-img" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514537310692&di=192146df47bc3cd4a3b6355ae11ed777&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-02-02%2F153209128.jpg" alt="...">
                    </a>
                </div>
            </div>

            <!-- user -->
            <div class="media fl">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object img-circle user-head-img" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514537310692&di=192146df47bc3cd4a3b6355ae11ed777&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-02-02%2F153209128.jpg" alt="...">
                    </a>
                </div>
                <div class="media-body nuna-chat-msg user-msg">
                    <%--<h4 class="media-heading">Media heading</h4>--%>
                    <span>您好，请问您需要什么帮助？</span>
                </div>
            </div>
        </div>

        <div class="panel-footer chatWinFooter">

            <div class="container-fluid">
                <form class="form-inline">
                    <div class="form-group">
                        <input type="text" class="form-control" id="send-text" placeholder="Send Text">
                    </div>
                    <button type="submit" class="btn btn-primary">发送</button>
                </form>
            </div>
            Powered by Alexliu
        </div>

    </div>




</body>
<script type="text/javascript" src="${basePath}/statics/vendor/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${basePath}/statics/vendor/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/statics/vendor/bootstrapValidator/js/bootstrapValidator.js"></script>

</html>
