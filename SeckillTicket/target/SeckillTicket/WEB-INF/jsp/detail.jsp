<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>抢票</title>
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
    <script language="javascript">
        $(function () {
            $("#seckillBtn").popover("hide");

        });

        function seckill(ticketId) {
            alert(ticketId);
            if(isLogin()){

            }else{
                $("#seckillBtn").popover("show");
            }
        }

        function isLogin() {
            //if()
            return false;
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-head">
                <h2>${ticket.name}<span id="ticketNum">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余库存数：${ticket.num}张</span></h2>
            </div>
            <div class="panel-body">
                <h1 class="text-danger">
                    <span class="glyphicon glyphicon-time"></span><br><br>
                    <span class="glyphicon glyphicon-box" id="time_box"></span><br><br>
                    <span class="glyphicon glyphicon-box" id="result_box"></span>
                </h1>
               <%-- <button  id="seckillBtn" onclick="seckill(${ticket.id})"
                       data-toggle="popover" data-placement="top"
                         data-content="您还未登录，请先登录" class="btn btn-success">抢购</button>
                <a href="${base_path}/index.jsp" class="btn btn-default">去登录</a>--%>
            </div>
        </div>
    </div>

    <div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="emailModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="emailModalLabel">输入邮箱地址，秒杀成功后将发送邮件到该邮箱</h4>
                </div>
                <div class="modal-body">
                    <input type="text" id="killEmail" />
                    <span id="modelMessage"></span>
                </div>
                <div class="modal-footer">
                    <button type="button" id="modelBtn" class="btn btn-primary">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

    <script type="text/javascript" src="/js/seckill.js"></script>
    <script type="text/javascript">
        $(function () {
            var params={
                ticketId:${ticket.id},
                startTime:${ticket.startTime.time},
                endTime:${ticket.endTime.time},
                ticketName:'${ticket.name}'
            }
            seckill.detail.init(params);
        });
    </script>

</body>
</html>
