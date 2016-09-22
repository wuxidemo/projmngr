<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>财务中心数据</title>

<link href="css/bootstrap.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<%!
String userName;
String userAccount;
%>

<style> 
body{ text-align:center} 
.div{ margin:0 auto; width:650px;  } 
</style>

<script>

$(document).ready(function(){
	<%
    userName = (String)session.getAttribute("loginName");
    if(userName == null){
    	response.sendRedirect("login");
    	return;
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

var xmlhttp;
function submitcreatefinance(){
	var projectsn = $("#projectsn").val();
	if(projectsn == ""){
		alert("请输入项目编号");
		return;
	}
	var financecontent = $("#financecontent").val();

	var salary = $("#salary").val();

	var financecost = $("#financecost").val();
	var financecredit = $("#financecredit").val();
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("GET","submitcreatefinance?projectsn=" + projectsn + "&financecontent=" + financecontent + "&salary=" + salary 
			 + "&financecost=" + financecost + "&financecredit=" + financecredit);
	xmlhttp.onreadystatechange=alertinfo;
	xmlhttp.send();
}

function alertinfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.createfinance);
			if(json.length > 0){
				var msg = json[0].msg;
				if(msg != "" && msg != undefined){
					alert(msg);					
					document.getElementById("projectsn").value = "";
					document.getElementById("financecontent").value = "";
					document.getElementById("salary").value = "";
					document.getElementById("financecost").value = "";
					document.getElementById("financecredit").value = "";
				}
			}
		}
	}

}

function submitSearch(projectsn){
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("GET","getprojectinfocontract?projectsn="+$("#projectsn").val());

	xmlhttp.onreadystatechange=updateinfo;
	xmlhttp.send();
}

function updateinfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			//
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.projectinfo);
			if(json.length > 0){
				var msg = json[0].msg;
				if(msg == 'notexist'){
					alert("项目编号不存在，请确认后重新输入");
					document.getElementById("projectsn").value = "";
					document.getElementById("financecontent").value = "";
					document.getElementById("salary").value = "";
					document.getElementById("financecost").value = "";
					document.getElementById("financecredit").value = "";
				}
			}
		}
	}

}


</script>
 
</head>
<body>


<%
    String result = (String)request.getParameter("result");
    if(result != null){
    	if(result.equals("createsuccess")){
%>

<script>
alert("财务中心数据录入成功！");
</script>
<%    		
    	}else if(result.equals("createfail")){
%>
<script>
    		alert("财务中心数据录入失败，请检查项目编号是否存在！");
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
         <li class="dropdown active">
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
         <li class="dropdown">
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


<h2 class="form-signin-heading">请输入财务中心数据相关信息</h2>
<div class="container div">
    <div >
 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon">项目编号*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入项目编号(必填)" name="projectsn" id="projectsn" onblur="submitSearch()" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">摘要:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入摘要" name="financecontent" id="financecontent" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">工资:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入工资" name="salary" id="salary" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
    </div> 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">财务费用:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入财务费用" name="financecost" id="financecost" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">资金信用额:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入资金信用额" name="financecredit" id="financecredit" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
    </div> 
    <br>
    <button style="width:620px;text-align:center;" class="btn btn-lg btn-primary btn-block" type="submit" onclick="submitcreatefinance()">财务中心数据录入</button><br>

    
    </div>
    
</div>


</body>
</html>