<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建帐号</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<%!
String userName;
String userAccount;
%>

<style> 
body{ text-align:center} 
.div{ margin:0 auto; width:850px;  } 
</style> 

<script>

function submitcreateaccount(){
	
	var username = $("#username").val();
	if(username == ""){
		alert("请输入姓名");
		return;
	}
	
	var useraccount = $("#useraccount").val();
	if(useraccount == ""){
		alert("请输入帐号");
		return;
	}
	
	var password = $("#password").val();
	if(password == ""){
		alert("请输入密码");
		return;
	}
    
	var pj1 = $('#pj1').get(0).checked;
	var pj2 = $('#pj2').get(0).checked;
	var pj3 = $('#pj3').get(0).checked;
	var pj4 = $('#pj4').get(0).checked;
	var pj5 = $('#pj5').get(0).checked;
	var pj6 = $('#pj6').get(0).checked;
	var pj7 = $('#pj7').get(0).checked;
	var pj8 = $('#pj8').get(0).checked;

	var spj1 = $('#spj1').get(0).checked;
	var spj2 = $('#spj2').get(0).checked;
	var spj3 = $('#spj3').get(0).checked;
	var spj4 = $('#spj4').get(0).checked;
	var spj5 = $('#spj5').get(0).checked;
	
	var f = document.createElement("form");
	document.body.appendChild(f);
	f.action = "submitcreateuser?username=" + username + "&useraccount=" + useraccount + "&password=" + password 
			+ "&pj1=" + pj1 + "&pj2=" + pj2 + "&pj3=" + pj3 + "&pj4=" + pj4 + "&pj5=" + pj5 + "&pj6=" + pj6 + "&pj7=" + pj7 + "&pj8=" + pj8
			+ "&spj1=" + spj1 + "&spj2=" + spj2 + "&spj3=" + spj3 + "&spj4=" + spj4 + "&spj5=" + spj5;
	f.method = "POST";
	f.submit();
}


function gotree(){
	//$("#aa").submit();
    var f = document.createElement("form");
    document.body.appendChild(f);
	f.action = "setdep";
	f.method = "GET";
	f.submit();
}
</script>

<script>
$(document).ready(function(){
    <%
	userName = (String)session.getAttribute("loginName");
    userAccount = (String)session.getAttribute("loginUser");

    Boolean hasPermission = (Boolean)session.getAttribute("loginPermission");
    Boolean adminPermission = (Boolean)session.getAttribute("adminPermission");
    
    %>

});
</script>

</head>

<body>

<%
    String result = (String)request.getParameter("adduserresult");
    if(result != null){
    	if(result.equals("success")){
%>
<script>
alert("新建账号成功！");

</script>
<%
    	}else{
%>
<script>
alert("帐号创建失败，用户名/姓名重复");
</script>
<%    		
    	}
    }
%>

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
 
    
%>

<nav class="navbar navbar-default" role="navigation">


      <ul class="nav navbar-nav">
         <li class="navbar-header">
         <a href="mainmenu">系统主页</a>
         </li>
         <li class="dropdown">
         <a href="#" class="dropdown-toggle" data-toggle="dropdown">录入功能 </a>
         <ul class="dropdown-menu">
         <li><a href="projectstart">项目立项</a></li>
         <li class="divider"></li>
         <li><a href="projectcontract">合同录入</a></li>
         <li class="divider"></li>
         <li><a href="projectreceipt">回款开票</a></li>
         <li class="divider"></li>
         <li><a href="projectpay">付款收票</a></li>
         <li class="divider"></li>      
         <li><a href="projectfee">事业部费用</a></li>
         <li class="divider"></li>
         <li><a href="projectprogress">项目进度</a></li>
         <li class="divider"></li>     
         <li><a href="projectfinish">项目完工</a></li>
         <li class="divider"></li>
         <li><a href="projectfinance">财务中心数据</a></li>
         </ul>
         </li> 
         
         <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">查询功能 </a>
            <ul class="dropdown-menu">
               <li><a href="searchcontracts">合同清单查询</a></li>
               <li class="divider"></li>
               <li><a href="searchoneproject">单项目查询</a></li>
               <li class="divider"></li>                 
               <li><a href="searchpay">付款明细查询</a></li>
               <li class="divider"></li>            
               <li><a href="searchallproject">项目汇总查询</a></li>       
               <li class="divider"></li>             
               <li><a href="searchdepartment">事业部汇总查询</a></li> 
            </ul>
         </li> 
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






<h2 class="form-signin-heading">新增帐号</h2>
<div class="container div">
    
    <div >
    <div style="float:left; width:400px;">
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon">姓名:</span>
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="" name="username" id="username" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon text-right">帐号:</span>
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="" name="useraccount" id="useraccount" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon text-right">密码:</span>
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="000000" name="password" id="password" />
    </div>     
    </div>
    
    <div style="float:right; width:400px; ">
    <br><br>
    <button style="width:400px;text-align:center;" class="btn btn-lg btn-primary btn-block" type="submit" onclick="submitcreateaccount()">创建帐号</button>
    <br><br><br><br>
    <!-- button style="width:400px;text-align:center;" class="btn btn-lg btn-primary btn-block" type="submit" onclick="gotree()" disabled>查看所有用户</button-->
    </div>

 
    <div style="float:left; width:400px; border:2px solid #0000FF;">
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目立项录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj1" id="pj1" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目合同录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj2" id="pj2" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">收款开票录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj3" id="pj3" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">付款收票录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj4" id="pj4" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">事业部费用录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj5" id="pj5" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目进度录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj6" id="pj6" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目完工录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj7" id="pj7" />
    </div> 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">财务中心录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="pj8" id="pj8" />
    </div>
    </div>
    
    <div  style="float:right; width:420px; border-right:2px solid #0000FF; border-top:2px solid #0000FF; border-bottom:2px solid #0000FF;">
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">合同清单查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="spj1" id="spj1" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">单项目查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="spj2" id="spj2" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">付款明细查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="spj3" id="spj3" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目汇总查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="spj4" id="spj4" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">事业部汇总查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="form-control" value="" name="spj5" id="spj5" />
    </div>   
    </div>         
    <br>
    </div>
    
</div>


</body>
</html>