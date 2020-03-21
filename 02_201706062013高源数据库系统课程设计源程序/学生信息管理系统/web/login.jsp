<%--
  Created by IntelliJ IDEA.
  User: 13345
  Date: 2019/7/5
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if(session.getAttribute("userType")!=null){
    session.removeAttribute("userType");
}
   if(session.getAttribute("userID")!=null){
       session.removeAttribute("userID");
   }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>particles.js</title>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="css/login.css">
</head>
<body>
<!-- particles.js container -->
<div id="particles-js" style="display: flex;align-items: center;justify-content: center">
</div>
<div class="login-page">
    <div class="login-content">
        <form action="login.do" method="post">
            <div class="login-tit">登录</div>
            <div class="login-input">
                <input type="text" placeholder="学号/工号" name="userID">
            </div>
            <div class="login-input">
                <input type="password" placeholder="密码" name="password">
            </div>
            <div class="login-input2">
                学生<input type="radio" name="userType" value="student" style="margin-right: 50px;">
                教师<input type="radio" name="userType" value="teacher" style="margin-right: 50px;">
                管理员<input type="radio" name="userType" value="manager">
            </div>
            <div class="login-btn">
                <div class="login-btn-left">
                    <!--        <span>登录</span>-->
                    <input type="submit" value="登录">
                </div>
                <div class="login-btn-right" onClick="changeImg()">
                    <img src="img/check.png" alt="" id="picture"> 记住密码
                </div>
            </div>
        </form>
    </div>
</div>


<!-- scripts -->
<script src="js/particles.js"></script>
<script src="js/app.js"></script>
<script>
    function changeImg() {
        let pic = document.getElementById('picture');
        console.log(pic.src)
        if (pic.getAttribute("src", 2) == "img/check.png") {
            pic.src = "img/checked.png"
        } else {
            pic.src = "img/check.png"
        }
    }
</script>
</body>
</html>
