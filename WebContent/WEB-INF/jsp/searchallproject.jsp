<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单项目查询</title>
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

doSearch();
});

function doSearch(){
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	xmlhttp.open("GET","getallproject");

	xmlhttp.onreadystatechange=updateInfo;
	xmlhttp.send();
	
}

function updateInfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			var data = eval ("(" + xmlhttp.responseText + ")");
			$('#allprojecttable').bootstrapTable('load',data);
			$('#allprojecttable').bootstrapTable({
			    data: data,
			    striped:true,
			    search:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    columns: [{
			        field: 'id',
			        title: '序号',
			        sortable:true,
			    },{
			        field: 'projectname',
			        title: '工程名称',
			        sortable:true,
			    },{
			        field: 'projectsn',
			        title: '项目编号',
			        sortable:true,
			    },{
			        field: 'signcompany',
			        title: '签订单位',
			        sortable:true,
			    },{
			        field: 'signdate',
			        title: '签订日期',
			        sortable:true,
			    },{
			        field: 'contractvendor',
			        title: '发包方',
			        sortable:true,
			    },{
			        field: 'contractamouont',
			        title: '合同金额',
			        sortable:true,
			    },{
			        field: 'finalamount',
			        title: '最终审定价',
			        sortable:true,
			    },{
			        field: 'billreceiptamount',
			        title: '已开票',
			        sortable:true,
			    },{
			        field: 'receiptamount',
			        title: '已收款',
			        sortable:true,
			    },{
			        field: 'planreceipt',
			        title: '预计可收款',
			        sortable:true,
			    },{
			        field: 'finishdate',
			        title: '竣工日期',
			        sortable:true,
			    },{
			        field: 'manager',
			        title: '市场经理',
			        sortable:true,
			    },{
			        field: 'overview',
			        title: '情况说明',
			    },{
			        field: 'plandate',
			        title: '回款时间',
			        sortable:true,
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


<h1 class="text-center text-primary">项目汇总查询</h1>
<table id="allprojecttable"></table> 
</body>
</html>