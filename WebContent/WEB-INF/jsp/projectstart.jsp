<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>项目立项</title>

<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-table.min.css" rel="stylesheet"/>

<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.min.js"></script>
<script src="js/bootstrap-table-export.min.js"></script>
<script src="js/tableExport.min.js"></script>
<script src="js/jquery.easyui.min.js"></script>
<script src="js/extensions/export/bootstrap-table-export.min.js"></script>
<script src="js/locales/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script src="js/locales/easyui-lang-zh_CN.js" type="text/javascript"></script>
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
	document.getElementById("savemodification").disabled="true";
	
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
function submitcreateproject(){
	var projectsn = $("#projectsn").val();
	if(projectsn == ""){
		alert("请输入项目编号");
		return;
	}
	var projectname = $("#projectname").val();
	if(projectname == ""){
		alert("请输入项目名称");
		return;
	}
	var projectvendor = $("#projectvendor").val();
	if(projectvendor == ""){
		alert("请输入项目采购单位");
		return;
	}
	var projectmanager = $("#projectmanager").val();
	if(projectmanager == ""){
		alert("请输入项目负责人");
		return;
	}
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("POST","submitcreateproject?projectsn=" + projectsn + "&projectname=" + projectname + "&projectvendor=" + projectvendor + "&projectmanager=" + projectmanager);

	xmlhttp.onreadystatechange=alertinfo;
	xmlhttp.send();	
}

function submitmodifyproject(){
	
	var projectsn = $("#projectsn").val();
	if(projectsn == ""){
		alert("请输入项目编号");
		return;
	}
	var projectname = $("#projectname").val();
	if(projectname == ""){
		alert("请输入项目名称");
		return;
	}
	var projectvendor = $("#projectvendor").val();
	if(projectvendor == ""){
		alert("请输入项目采购单位");
		return;
	}
	var projectmanager = $("#projectmanager").val();
	if(projectmanager == ""){
		alert("请输入项目负责人");
		return;
	}
	$.ajax({
		type:"POST",
		url:"/hsprojectmngr/submitmodifyproject?projectsn=" + projectsn + "&projectname=" + projectname + "&projectvendor=" + projectvendor + "&projectmanager=" + projectmanager,
		success:function(data){
			var data = eval ("(" + data + ")");
			
			$('#recordtable').bootstrapTable('load',data);
			$('#recordtable').bootstrapTable({
			    data: data,
			    striped:true,
			    search:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    rowStyle:function(row,index){
			    	if(row.recordstatus == '待审批，点击修改'){
			    		return { css: {"color": "red", } };
			    	}else{
			    		return { classes:'none' };
			    	}
			    	
			    },
			    columns: [{
			        field: 'index',
			        title: '序号',
			        sortable:true,
			    },{
			        field: 'projectsn',
			        title: '项目编号',
			    },{
			        field: 'projectname',
			        title: '项目名称',
			    },{
			        field: 'projectvendor',
			        title: '建设单位',
			    },{
			        field: 'projectmanager',
			        title: '项目责任人',
			    },{
			        field: 'recordstatus',
			        title: '状态',
			    },],
			    onClickCell:function(field, value, row, $element){
			    	//alert(field + "_" + value + "_" + row);
			    	if(clickbutton == 1){
			    		<% if(adminPermission){ %> 
			    	    document.getElementById("savemodification").disabled=false;
			    	    document.getElementById("createnewrecord").disabled=true;
					    document.getElementById("projectsn").value = row.projectsn;
					    document.getElementById("projectsn").readOnly=true;
					    document.getElementById("projectname").value = row.projectname;
					    document.getElementById("projectvendor").value = row.projectvendor;
					    document.getElementById("projectmanager").value = row.projectmanager;
			    	<%} %>
			    	if(row.recordstatus != '已审批'){
			    		document.getElementById("savemodification").disabled=false;
				    	document.getElementById("createnewrecord").disabled=true;
						document.getElementById("projectsn").value = row.projectsn;
						document.getElementById("projectsn").readOnly=true;
						document.getElementById("projectname").value = row.projectname;
						document.getElementById("projectvendor").value = row.projectvendor;
						document.getElementById("projectmanager").value = row.projectmanager;
			    	}
			    	}else if(clickbutton == 2){
			    		$.ajax({
	                		type:"POST",
	                		url:"/hsprojectmngr/projectstartadminfun?projectsn=" + row.projectsn,
	                		success:function(data){
	                			alert("审批成功");
	                			projectStartAdminQuery()
	                		}
	                	});
			    	}

			    }
			});
			alert("修改完成");
			document.getElementById("savemodification").disabled=true;
			document.getElementById("createnewrecord").disabled=false;
			document.getElementById("projectsn").readOnly=false;
			document.getElementById("projectsn").value = "";
			document.getElementById("projectname").value = "";
			document.getElementById("projectvendor").value = "";
			document.getElementById("projectmanager").value = "";
		}
	});	
}

function alertinfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			//
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.createproject);
			if(json.length > 0){
				var msg = json[0].msg;
				if(msg != "" && msg != undefined){
					alert(msg);					
					document.getElementById("projectsn").value = "";
					document.getElementById("projectname").value = "";
					document.getElementById("projectvendor").value = "";
					document.getElementById("projectmanager").value = "";
				}
			}
		}
	}

}

function submitSearch(projectsn){
	if(document.getElementById("savemodification").disabled == false){
		return;
	}
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("GET","getprojectexist?projectsn="+$("#projectsn").val());

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
				if(msg != "" && msg != undefined){
					alert(msg);
					document.getElementById("projectsn").value = "";
				}
			}
		}
	}

}

var clickbutton;
function projectStartQuery(){
	clickbutton = 1;
	$.ajax({
		type:"GET",
		url:"/hsprojectmngr/projectstartquery",
		success:function(data){
			var data = eval ("(" + data + ")");
			
			$('#recordtable').bootstrapTable('load',data);
			$('#recordtable').bootstrapTable({
			    data: data,
			    striped:true,
			    search:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    rowStyle:function(row,index){
			    	if(row.recordstatus == '待审批，点击修改'){
			    		return { css: {"color": "red", } };
			    	}else{
			    		return { classes:'none' };
			    	}
			    	
			    },
			    columns: [{
			        field: 'index',
			        title: '序号',
			        sortable:true,
			    },{
			        field: 'projectsn',
			        title: '项目编号',
			    },{
			        field: 'projectname',
			        title: '项目名称',
			    },{
			        field: 'projectvendor',
			        title: '建设单位',
			    },{
			        field: 'projectmanager',
			        title: '项目责任人',
			    },{
			        field: 'operator',
			        title: '录入人',
			        sortable:true,
			    },{
			        field: 'time',
			        title: '录入时间',
			        sortable:true,
			    },{
			        field: 'recordstatus',
			        title: '状态',
			        sortable:true,
			    },],
			    onClickCell:function(field, value, row, $element){
			    	//alert(field + "_" + value + "_" + row);
			    	if(clickbutton == 1){
			    		<% if(adminPermission){ %> 
			    	    document.getElementById("savemodification").disabled=false;
			    	    document.getElementById("createnewrecord").disabled=true;
					    document.getElementById("projectsn").value = row.projectsn;
					    document.getElementById("projectsn").readOnly=true;
					    document.getElementById("projectname").value = row.projectname;
					    document.getElementById("projectvendor").value = row.projectvendor;
					    document.getElementById("projectmanager").value = row.projectmanager;
			    	<%} %>
			    	if(row.recordstatus != '已审批'){
			    		document.getElementById("savemodification").disabled=false;
				    	document.getElementById("createnewrecord").disabled=true;
						document.getElementById("projectsn").value = row.projectsn;
						document.getElementById("projectsn").readOnly=true;
						document.getElementById("projectname").value = row.projectname;
						document.getElementById("projectvendor").value = row.projectvendor;
						document.getElementById("projectmanager").value = row.projectmanager;
			    	}
			    	}else if(clickbutton == 2){
			    		$.ajax({
	                		type:"POST",
	                		url:"/hsprojectmngr/projectstartadminfun?projectsn=" + row.projectsn,
	                		success:function(data){
	                			alert("审批成功");
	                			projectStartAdminQuery()
	                		}
	                	});
			    	}
			    	
			    }
			});
			
		}
	});	
}

function projectStartAdminQuery(){
	clickbutton = 2;
	$.ajax({
		type:"GET",
		url:"/hsprojectmngr/projectstartadminquery",
		success:function(data){
			var data = eval ("(" + data + ")");
			
			$('#recordtable').bootstrapTable('load',data);
			$('#recordtable').bootstrapTable({
			    data: data,
			    striped:true,
			    search:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    rowStyle:function(row,index){
			    	if(row.recordstatus == '待审批，点击修改'){
			    		return { css: {"color": "red", } };
			    	}else{
			    		return { classes:'none' };
			    	}
			    	
			    },
			    exportTypes:['doc', 'excel'],

			    columns: [{
			        field: 'index',
			        title: '序号',
			        sortable:true,
			    },{
			        field: 'projectsn',
			        title: '项目编号',
			    },{
			        field: 'projectname',
			        title: '项目名称',
			    },{
			        field: 'projectvendor',
			        title: '建设单位',
			    },{
			        field: 'projectmanager',
			        title: '项目责任人',
			    },{
			        field: 'operator',
			        title: '操作人',
			        sortable:true,
			    },{
			        field: 'time',
			        title: '录入时间',
			        sortable:true,
			    },{
			        field: 'recordstatus',
			        title: '状态',
			        sortable:true,
			    },],
			    onClickCell:function(field, value, row, $element){
			    	//alert(field + "_" + value + "_" + row);
			    	if(clickbutton == 1){
			    		<% if(adminPermission){ %> 
			    	    document.getElementById("savemodification").disabled=false;
			    	    document.getElementById("createnewrecord").disabled=true;
					    document.getElementById("projectsn").value = row.projectsn;
					    document.getElementById("projectsn").readOnly=true;
					    document.getElementById("projectname").value = row.projectname;
					    document.getElementById("projectvendor").value = row.projectvendor;
					    document.getElementById("projectmanager").value = row.projectmanager;
			    	<%} %>
			    	if(row.recordstatus != '已审批'){
			    		document.getElementById("savemodification").disabled=false;
				    	document.getElementById("createnewrecord").disabled=true;
						document.getElementById("projectsn").value = row.projectsn;
						document.getElementById("projectsn").readOnly=true;
						document.getElementById("projectname").value = row.projectname;
						document.getElementById("projectvendor").value = row.projectvendor;
						document.getElementById("projectmanager").value = row.projectmanager;
			    	}
			    	}else if(clickbutton == 2){
			    		$.ajax({
	                		type:"POST",
	                		url:"/hsprojectmngr/projectstartadminfun?projectsn=" + row.projectsn,
	                		success:function(data){
	                			alert("审批成功");
	                			projectStartAdminQuery()
	                		}
	                	});
			    	}
                	
                    
			    }
			});
			
		}
	});	
}



</script>
 
</head>
<body>




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


<h2 class="form-signin-heading">请输入项目相关信息</h2>
<div class="container div">
    <div >
 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon">项目编号*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入项目编号(必填)" name="projectsn" id="projectsn" onblur="submitSearch()"  />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目名称*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入项目名称(必填)" name="projectname" id="projectname" />
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">建设单位*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入采购单位(必填)" name="projectvendor" id="projectvendor" />
    </div> 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目责任人*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入项目责任人(必填)" name="projectmanager" id="projectmanager" />
    </div>    

    <br>
    <button  class="btn btn-lg btn-primary " type="submit" onclick="submitcreateproject()" id="createnewrecord">新建立项</button>
    <button  class="btn btn-lg btn-primary " type="submit" onclick="submitmodifyproject()" id="savemodification">修改立项</button>
    <button  class="btn btn-lg btn-primary " type="submit" onclick="projectStartQuery()">查看立项</button>
   <% if(adminPermission){ %> 
    <button  class="btn btn-lg btn-primary " type="submit" onclick="projectStartAdminQuery()">审批立项</button>
   <% } %>
    </div>
    
</div>
<table id="recordtable"></table>
</body>
</html>