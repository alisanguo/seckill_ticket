<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>抢票</title>
    <link href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="panel panel-default">
        <div class="panel-heading">
            抢票列表
        </div>
        <div class="panel-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>电影票名</th>
                        <th>价格</th>
                        <th>抢票开始时间</th>
                        <th>抢票结束时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ticketList}" var="ticket">
                        <tr>
                            <td>${ticket.name}</td>
                            <td>${ticket.price}</td>
                            <td>${ticket.startTime}</td>
                            <td>${ticket.endTime}</td>
                            <td>
                                <a href="/ticket/${ticket.id}/detail" target="_blank" class="btn btn-default">详情</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
