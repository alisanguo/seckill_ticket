<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:wb="http://open.weibo.com/wb">
<head>
    <meta charset="utf-8">
    <meta property="wb:webmaster" content="98155480548427ff" />
    <title>抢票</title>
    <script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js?appkey=1391940523 APPKEY&debug=true" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        // 如需添加回调函数，请在wbml标签中添加onlogin="login" onlogout="logout"，并定义login和logout函数。
        function login(o) {
            alert(o.screen_name)
        }

        function logout() {
            alert('logout');
        }

    </script>

</head>
<body>
<h2><a href="/ticket/list">抢票首页</a></h2>
<wb:login-button type="3,2" onlogin="login" onlogout="logout" ></wb:login-button>
</body>
</html>
