<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<%!
String userName;
String userAccount;

%>

<style> 
body{ text-align:center} 
.div{ margin:0 auto; width:450px;  } 
</style> 

<script>
function submitresetpassword(){
	var oldpassword = $("#oldpassword").val();
	if(oldpassword == ""){
		alert("请输入旧密码");
		return;
	}
	var password1 = $("#newpassword1").val();
	if(password1 == ""){
		alert("请输入新密码");
		return;
	}	
	var password2 = $("#newpassword2").val();
	if(password2 == ""){
		alert("请再次输入新密码");
		return;
	}	
	if(password1 != password2){
		alert("两次输入新密码不一样");
		return;
	}

	var f = document.createElement("form");
	document.body.appendChild(f);
	f.action = "resetpasswordaction?oldpassword=" + oldpassword + "&newpassword=" + password1;
	f.method = "POST";
	f.submit();
}

</script>


<script>
$(document).ready(function(){
	<%
    userName = (String)session.getAttribute("loginName");
    if(userName == null){
    	response.sendRedirect("login");
    	return;
    }else{
    	//
    }
    userAccount = (String)session.getAttribute("loginUser");
    if(userAccount == null){
    	response.sendRedirect("login");
    	return;
    }
  
    String loginAuthority = (String)session.getAttribute("loginAuthority");
    String searchAuthority = (String)session.getAttribute("searchAuthority");
    int value = Integer.valueOf(loginAuthority);
    int pj1 = value/10000000;
    int pj2 = value/1000000%10;
    int pj3 = value/100000%10;
    int pj4 = value/10000%10;
    int pj5 = value/1000%10;
    int pj6 = value/100%10;
    int pj7 = value/10%10;
    int pj8 = value%10;
    int value2 = Integer.valueOf(searchAuthority);
    int spj1 = value2/10000%10;
    int spj2 = value2/1000%10;
    int spj3 = value2/100%10;
    int spj4 = value2/10%10;
    int spj5 = value2%10;
    Boolean adminPermission = (Boolean)session.getAttribute("adminPermission");

%>	
});
</script>
</head>

<body>

<%
    String result = (String)request.getParameter("resetpasswordresult");
    if(result != null){
    	if(result.equals("ok")){
%>
<script>
alert("密码修改成功！");
</script>
<%    		
    	}else if(result.equals("error")){
%>
<script>
alert("密码修改失败，原密码输入错误！");
</script>
<%
    	}
    }
%>



<nav class="navbar navbar-default" role="navigation">


      <ul class="nav navbar-nav">
         <li class="navbar-header">
         <a href="mainmenu">系统主页</a>
         </li>
         <% if(pj1 == 1 || pj2 == 1 || pj3 == 1 || pj4 == 1 || pj5 == 1 || pj6 == 1 || pj7 == 1 || pj8 == 1){ %>
         <li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">录入功能 </a>
         <ul class="dropdown-menu">
         <% if(pj1 == 1){ %> 
         <li><a href="projectstart">项目立项</a></li>
         <li class="divider"></li>
         <% }
         if(pj2 == 1){%>
         <li><a href="projectcontract">合同录入</a></li>
         <li class="divider"></li>
         <% }
         if(pj3 == 1){%>
         <li><a href="projectreceipt">回款开票</a></li>
         <li class="divider"></li>
         <% }
         if(pj4 == 1){%>
         <li><a href="projectpay">付款收票</a></li>
         <li class="divider"></li>
         <% }
         if(pj5 == 1){%>         
         <li><a href="projectfee">事业部费用</a></li>
         <li class="divider"></li>
         <% }
         if(pj6 == 1){%>  
         <li><a href="projectprogress">项目进度</a></li>
         <li class="divider"></li>  
         <% }
         if(pj7 == 1){%>    
         <li><a href="projectfinish">项目完工</a></li>
         <li class="divider"></li>
         <% }
         if(pj8 == 1){%> 
         <li><a href="projectfinance">财务中心数据</a></li>
         <% }%>
         </ul>
         </li> 
         <% }%>
         
         <% if(spj1 == 1 || spj2 == 1 || spj3 == 1 || spj4 == 1 || spj5 == 1){ %>
         <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">查询功能 </a>
            <ul class="dropdown-menu">
               <% if(spj1 == 1){ %> 
               <li><a href="searchcontracts">合同清单查询</a></li>
               <li class="divider"></li>
               <% }
               if(spj2 == 1){%> 
               <li><a href="searchoneproject">单项目查询</a></li>
               <li class="divider"></li>       
               <% }
               if(spj3 == 1){%>                
               <li><a href="searchpay">付款明细查询</a></li>
               <li class="divider"></li>
               <% }
               if(spj4 == 1){%>                
               <li><a href="searchallproject">项目汇总查询</a></li>       
               <li class="divider"></li>
               <% }
               if(spj5 == 1){%>                
               <li><a href="searchdepartment">事业部汇总查询</a></li> 
               <% }%>   
            </ul>
         </li> 
         <% }%>    
         <li class="dropdown active">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">帐号设置 </a>
            <ul class="dropdown-menu">
               <li><a href="resetpassword">密码设置</a></li>
               <li class="divider"></li>
               <li><a href="accountinfo">帐号信息</a></li>
               <li class="divider"></li>
               <% if(adminPermission){ %> 
               <li><a href="department">创建事业部</a></li>
               <li class="divider"></li>
               <li><a href="adduser">创建帐号</a></li>
               <li class="divider"></li>
               <li><a href="modifyuser">修改帐号</a></li>            
               <% } %>
            </ul>
         </li>

         <li>
         <p class="navbar-text navbar-right">
         <span class="label label-default"><%= userName %></span></p>
         </li>
         <li><a href="logout">&nbsp&nbsp退出</a></li>
      </ul>

    
</nav>



<h2 class="form-signin-heading">修改密码</h2>
<div class="container div">
    <div >
    
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon text-right">帐  号:</span>
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="<%=userAccount  %>" name="useraccount" id="useraccount" readonly />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon">旧密码:</span>
         <input type="password" style="width:250px;text-align:left;" class="form-control" placeholder="输入原密码" name="oldpassword" id="oldpassword" required />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon">新密码:</span>
         <input type="password" style="width:250px;text-align:left;" class="form-control" placeholder="输入新密码" name="newpassword1" id="newpassword1" required />
    </div>      
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon">确认密码:</span>
         <input type="password" style="width:250px;text-align:left;" class="form-control" placeholder="再次输入新密码" name="newpassword2" id="newpassword2" required />
    </div>
    <br>
    <button style="width:400px;text-align:center;" class="btn btn-lg btn-primary btn-block" type="submit" onclick="submitresetpassword()">确认修改</button><br><br>
    </div>
    
</div>



</body>
</html>