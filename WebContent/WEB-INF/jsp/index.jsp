<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目管理系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
<%
    String result = (String)request.getParameter("result");
    if(result != null){
    	if(result.equals("registersuccess")){
    		//注册成功
%>

<script>
alert("注册成功，请登录！");
</script>
<%    		
    	}else if(result.equals("loginfailpassword")){
    		//登陆失败 password
%>
<script>
    		alert("密码错误，请重试！");
</script>
<%     		
    }else if(result.equals("loginfailaccount")){
		//登陆失败 account
%>
<script>
    	alert("帐号错误，请重试！");
</script>
<%     		
    }
    }
%>

<br>
<div class="container">
    <form class="form-signin" role="form" action="logincheck" method="post">
    <h2 class="form-signin-heading">欢迎登陆项目管理系统</h2>
    <input type="text" class="form-control" placeholder="输入账号" name="useraccount" required autofocus />
    <input type="password" class="form-control" placeholder="输入密码" name="password" required /><br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button><br><br>
    
    </form>

    <button class="btn btn-lg btn-primary btn-block" type="submit" disabled>请联系管理员注册帐号</button> 

</div>



</body>
</html>