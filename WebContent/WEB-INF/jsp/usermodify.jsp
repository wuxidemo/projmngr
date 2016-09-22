<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>帐号信息</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<%!
String userName;
String userAccount;
String userRole;
String userDep;
%>

<style> 
body{ text-align:center} 
.div{ margin:0 auto; width:850px;  } 
</style> 

<script>
var selectedDep = 0;
var selectedDeptx = "<%= userDep %>";
var selectedTitle = 0;
var selectedTitletx = "<%= userRole %>";
function depselectOnChange(){
	var obj = document.getElementById("usredep");
	selectedDep = obj.selectedIndex; 
    selectedDeptx = obj.options[selectedDep].text;
}
function jobtitleselectOnChange(){
	var obj = document.getElementById("usertitle");
	selectedTitle = obj.selectedIndex; 
	selectedTitletx = obj.options[selectedTitle].text;	
}

var xmlhttp;
function searchaccount(){
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	var name = $("#searchname").val();
    xmlhttp.open("GET","searchuserbyname?username=" + name);

	xmlhttp.onreadystatechange=searchaccountfun;
	xmlhttp.send();

}


function searchaccountfun(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.userinfo);
			if(json.length > 0){
				var msg = json[0].msg;
				if(msg == 'notexist'){
					alert("未找到此姓名对应的帐号信息！请确认姓名正确并已创建帐号");
					document.getElementById("username").value="";
					document.getElementById("useraccount").value="";
					/*$('#pj1').removeAttr('checked');
					$('#pj2').removeAttr('checked');
					$('#pj3').removeAttr('checked');
					$('#pj4').removeAttr('checked');
					$('#pj5').removeAttr('checked');
					$('#pj6').removeAttr('checked');
					$('#pj7').removeAttr('checked');
					$('#pj8').removeAttr('checked');				
					$('#spj1').removeAttr('checked');
					$('#spj2').removeAttr('checked');
					$('#spj3').removeAttr('checked');
					$('#spj4').removeAttr('checked');
					$('#spj5').removeAttr('checked');*/
					$('.pj').prop('checked',false);
					$('.spj').prop('checked',false);
				}else if(msg == 'ok'){
					document.getElementById("username").value=json[0].username;
					document.getElementById("useraccount").value=json[0].useraccount;
					var authority = parseInt(json[0].authority);
					var searchauthority = parseInt(json[0].searchauthority);

					var pj1 = parseInt(authority/10000000);
					if(pj1 == 0){
						//$('#pj1').removeAttr('checked');
						$('#pj1').prop('checked',false);
					}else{
						$('#pj1').prop('checked',true);
					}
					var pj2 = parseInt(authority/1000000%10);
					if(pj2 == 0){
						//$('#pj2').removeAttr('checked');
						$('#pj2').prop('checked',false);
					}else{
						$('#pj2').prop('checked',true);
					}
					var pj3 = parseInt(authority/100000%10);
					if(pj3 == 0){
						//$('#pj3').removeAttr('checked');
						$('#pj3').prop('checked',false);
					}else{
						$('#pj3').prop('checked','true');
					}
					var pj4 = parseInt(authority/10000%10);
					if(pj4 == 0){
						//$('#pj4').removeAttr('checked');
						$('#pj4').prop('checked',false);
					}else{
						$('#pj4').prop('checked','true');
					}
					var pj5 = parseInt(authority/1000%10);
					if(pj5 == 0){
						//$('#pj5').removeAttr('checked');
						$('#pj5').prop('checked',false);
					}else{
						$('#pj5').prop('checked','true');
					}
					var pj6 = parseInt(authority/100%10);
					if(pj6 == 0){
						//$('#pj6').removeAttr('checked');
						$('#pj6').prop('checked',false);
					}else{
						$('#pj6').prop('checked','true');
					}
					var pj7 = parseInt(authority/10%10);
					if(pj7 == 0){
						//$('#pj7').removeAttr('checked');
						$('#pj7').prop('checked',false);
					}else{
						$('#pj7').prop('checked','true');
					}
					var pj8 = parseInt(authority%10);
					if(pj8 == 0){
						//$('#pj8').removeAttr('checked');
						$('#pj8').prop('checked',false);
					}else{
						$('#pj8').prop('checked','true');
					}
					var spj1 = parseInt(searchauthority/10000);
					if(spj1 == 0){
						$('#spj1').prop('checked',false);
					}else{
						$('#spj1').prop('checked',true);
					}
					var spj2 = parseInt(searchauthority/1000%10);
					if(spj2 == 0){
						$('#spj2').prop('checked',false);
					}else{
						$('#spj2').prop('checked',true);
					}
					var spj3 = parseInt(searchauthority/100%10);
					if(spj3 == 0){
						$('#spj3').prop('checked',false);
					}else{
						$('#spj3').prop('checked',true);
					}
					var spj4 = parseInt(searchauthority/10%10);
					if(spj4 == 0){
						$('#spj4').prop('checked',false);
					}else{
						$('#spj4').prop('checked','true');
					}
					var spj5 = parseInt(searchauthority%10);
					if(spj5 == 0){
						$('#spj5').prop('checked',false);
					}else{
						$('#spj5').prop('checked',true);
					}					
				}
			}
			

		}
	}

}

function submitresetaccount(){
	
	var useraccount = $("#useraccount").val();
	if(useraccount == ""){
		alert("帐号不能为空");
		return;
	}
	
	var username = $("#username").val();
	if(username == ""){
		alert("请输入用户名");
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
	f.action = "updateuseraction?username=" + username + "&useraccount=" + useraccount
	             + "&pj1=" + pj1 + "&pj2=" + pj2 + "&pj3=" + pj3 + "&pj4=" + pj4 + "&pj5=" + pj5+ "&pj6=" + pj6 + "&pj7=" + pj7 + "&pj8=" + pj8
	             + "&spj1=" + spj1 + "&spj2=" + spj2 + "&spj3=" + spj3 + "&spj4=" + spj4 + "&spj5=" + spj5;
	f.method = "POST";
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
    String result = (String)request.getParameter("modifyuserresult");
    if(result != null){
    	if(result.equals("ok")){
    		//修改帐号信息成功
%>
<script>
alert("帐号信息修改成功！");

</script>
<%
    	}else{
%>
<script>
alert("没有搜索到此帐号！");
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






<h2 class="form-signin-heading">修改员工信息与权限</h2>

    
<div class="container div">
    <div >
    
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon text-right">姓名:</span>         
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="" name="searchname" id="searchname" placeholder="输入员工姓名，然后搜索" />
         <button style="width:400px;text-align:center;" class="btn  btn-primary btn-block" type="submit" onclick="searchaccount()">搜索</button>
    </div>
    <br>
    <br>
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon">姓名:</span>
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="" name="username" id="username" required />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;" class="input-group-addon text-right">帐号:</span>
         <input type="text" style="width:250px;text-align:left;" class="form-control" value="" name="useraccount" id="useraccount" readonly />
    </div>
    
    
    
    <div style="float:left; width:400px; border:2px solid #0000FF;">
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目立项录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj1" id="pj1" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目合同录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj2" id="pj2" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">收款开票录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj3" id="pj3" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">付款收票录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj4" id="pj4" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">事业部费用录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj5" id="pj5" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目进度录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj6" id="pj6" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目完工录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj7" id="pj7" />
    </div> 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">财务中心录入权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="pj" value="" name="pj8" id="pj8" />
    </div>
    </div>
    
    <div  style="float:right; width:420px; border-right:2px solid #0000FF; border-top:2px solid #0000FF; border-bottom:2px solid #0000FF;">
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">合同清单查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="spj" value="" name="spj1" id="spj1" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">单项目查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="spj" value="" name="spj2" id="spj2" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">付款明细查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="spj" value="" name="spj3" id="spj3" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目汇总查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="spj" value="" name="spj4" id="spj4" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">事业部汇总查询权限:</span>
         <input type="checkbox" style="width:150px;height:45px;text-align:left;" class="spj" value="" name="spj5" id="spj5" />
    </div>   
    </div>   
    
    </div>
    <button style="width:400px;text-align:center;" class="btn btn-lg btn-primary btn-block" type="submit" onclick="submitresetaccount()">确认修改</button><br><br>
</div>



</body>
</html>