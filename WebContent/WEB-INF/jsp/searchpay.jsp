<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-table.min.css" rel="stylesheet"/>
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.min.js"></script>
<script src="js/bootstrap-table-export.min.js"></script>
<script src="js/tableExport.min.js"></script>
<script src="js/extensions/export/bootstrap-table-export.min.js"></script>
<script src="js/locales/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<%!
String userName;
String userAccount;
%>

<style>

.div{ margin:0 auto;  } 

.full-width{
    width:100%;
    margin-left:10px;
    margin-right:20px;
}
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

function doInitSearch(){

	var projectsn = $('#inputprojectsn').val();
	if(projectsn == ""){
		alert("请输入项目编号，然后点击查询！")
	}else{
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}

	    xmlhttp.open("GET","getpaytitle?projectsn="+projectsn);

		xmlhttp.onreadystatechange=updateInfo;
		xmlhttp.send();
	}	
}

function updateInfo(){
	var data = eval ("(" + xmlhttp.responseText + ")");
	if(data[0].contractContent == "error"){
		$('#namecontent').html("");
		alert("项目不存在或者此项目没有录入建设合同!")
	}else{
		$('#namecontent').html(data[0].contractContent);
	}
	
	doSearch();
}

function doSearch(){
	//$('#namecontent').html("abc");
	var projectsn = $('#inputprojectsn').val();
	if(projectsn == ""){
		alert("请输入项目编号，然后点击查询！")
	}else{
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}

	    xmlhttp.open("GET","getpaydetails?projectsn="+projectsn);

		xmlhttp.onreadystatechange=buildtable;
		xmlhttp.send();
	}	
}

function buildtable(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			var data = eval ("(" + xmlhttp.responseText + ")");
			$('#paytable').bootstrapTable('load',data);
			$('#paytable').bootstrapTable({
			    data: data,
			    striped:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    columns: [{
			        field: 'vendor',
			        title: '单位名称',
			        sortable:true,
			    },{
			        field: 'name',
			        title: '采购货物名称',
			        sortable:true,
			    },{
			        field: 'totalamount',
			        title: '合同总金额',
			        sortable:true,
			    },{
			        field: 'paylist',
			        title: '付款明细',
			        sortable:true,
			    },{
			        field: 'notpayamount',
			        title: '未付金额',
			        sortable:true,
			    },{
			        field: 'receipt',
			        title: '发票',
			        sortable:true,
			    },{
			        field: 'comment',
			        title: '备注',
			    },]
			});
		}
	}
}

</script>


</head>
<body>
<nav class="navbar navbar-default" role="navigation">
   <div class="navbar-header">
      <a class="active navbar-brand" href="mainmenu">系统主页</a>
   </div>
   <div>
      <ul class="nav navbar-nav">     
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
         <li class="dropdown active">
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
   </div>
    
</nav>

<h1 class="text-primary text-center"><label id="namecontent"></label>付款明细查询</h1>
<div class="panel-heading">
    <input type="text" style="width:300px; text-align:left; height:48px;" class="" placeholder="请输入项目编号 然后点击查询" name="inputprojectsn" id="inputprojectsn" />
    <input type="button" class="btn btn-primary" value="付款明晰查询" onclick="doInitSearch()" />
</div>

<table id="paytable"></table>     
</body>
</html>