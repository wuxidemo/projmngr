<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>合同清单查询</title>

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

$(function () {
    $('#beginreceivedate').datetimepicker({
    	format:"yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        minView: "month",
        todayHighlight: 1,
                
    });
})
$(function () {
    $('#endreceivedate').datetimepicker({
    	format:"yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        minView: "month",
        todayHighlight: 1,
                
    });
})
$(function () {
    $('#beginsigndate').datetimepicker({
    	format:"yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        minView: "month",
        todayHighlight: 1,
                
    });
})
$(function () {
    $('#endsigndate').datetimepicker({
    	format:"yyyy-mm-dd",
        language: 'zh-CN',
        autoclose: true,
        minView: "month",
        todayHighlight: 1,
                
    });
})

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


function clearinput(){
	$("#projectsn").val("");
	$("#beginreceivedate").val("");
	$("#endreceivedate").val("");
	$("#contractwho").val("");
	$("#beginsigndate").val("");
	$("#endsigndate").val("");
	$("#contracttype").val("请选择合同类型");
	$("#mincontractamount").val("");
	$("#maxcontractamount").val("");		
}

var xmlhttp;
function submitsearch(){
	var projectsn = $("#projectsn").val();
	var beginreceivedate = $("#beginreceivedate").val();
	var endreceivedate = $("#endreceivedate").val();
	var contractwho = $("#contractwho").val();
	var beginsigndate = $("#beginsigndate").val();
	var endsigndate = $("#endsigndate").val();
	var contracttype = $("#contracttype").val();
	var mincontractamount = $("#mincontractamount").val();
	var maxcontractamount = $("#maxcontractamount").val();	
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("GET","submitsearchcontracts?projectsn=" + projectsn + "&beginreceivedate=" + beginreceivedate + "&endreceivedate=" + endreceivedate 
			+ "&contractwho=" + contractwho + "&beginsigndate=" + beginsigndate + "&endsigndate=" + endsigndate
			+ "&contracttype=" + contracttype + "&mincontractamount=" + mincontractamount + "&maxcontractamount=" + maxcontractamount);

	xmlhttp.onreadystatechange=createtb;
	xmlhttp.send();	
}


function createtb(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
            var data = eval ("(" + xmlhttp.responseText + ")");
            $('#contractstable').bootstrapTable('load',data);
			$('#contractstable').bootstrapTable({
			    data: data,
			    striped:true,
			    pagination:true,
			    search:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    columns: [{
			        field: 'receivedate',
			        title: '收到日期',
			        sortable:true
			    }, {
			        field: 'signdate',
			        title: '签订日期',
			        sortable:true,
			    }, {
			        field: 'projectsn',
			        title: '项目编号',
			        sortable:true,
			    }, {
			        field: 'contracttype',
			        title: '合同类型',
			        sortable:true,
			    }, {
			        field: 'contractname',
			        title: '合同名称',
			        sortable:true,
			    }, {
			        field: 'contractvendor',
			        title: '签订方单位',
			        sortable:true,
			    }, {
			        field: 'contractamount',
			        title: '合同金额',
			        sortable:true,
			    }, {
			        field: 'contractwho',
			        title: '交接人',
			        sortable:true,
			    }, {
			        field: 'description',
			        title: '备注',
			        sortable:true,
			    }]
			});
		}
	}

}
function submitSearch(){
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("GET","getprojectinforeceipt?projectsn="+$("#projectsn").val());

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
				if(json[0].msg == 'notexist'){
					alert("项目编号不存在，请确认后输入！")
					document.getElementById("projectsn").value = "";
				}
			}
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

<h1 class="text-center text-primary">合同清单查询</h1>

<div class="container div">

     <div class="input-group input-group-lg">


    </div> 
    
    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;height:48px;" class="input-group-addon text-right">项目编号:</span>
         <input type="text" style="width:200px;text-align:left; height:48px;" class="form-control" value="" placeholder="请输入项目编号" name="projectsn" id="projectsn" />   
         
         <div class="input-group date form_datetime">
         <span style="width:150px;text-align:right;" class="input-group-addon">开始收到日期:</span>
         <input type="text" class="form-control" style="width:200px;height:48px;" id="beginreceivedate"  value="" readonly placeholder="请选择开始收到日期" />
		 <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
         <span style="width:150px;text-align:right;" class="input-group-addon">结束收到日期:</span>
         <input type="text" class="form-control" style="width:200px;height:48px;" id="endreceivedate"  value="" readonly placeholder="请选择结束收到日期" />
		 <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>		 
         </div>
     
         <!-- span style="width:150px;text-align:right;" class="input-group-addon">开始收到日期:</span>
         <input type="text" style="width:200px;text-align:left;" class="form-control" value="" placeholder="请输入开始收到日期" name="beginreceivedate" id="beginreceivedate" />
         <span style="width:150px;text-align:right;" class="input-group-addon">结束收到日期:</span>
         <input type="text" style="width:200px;text-align:left;" class="form-control" value="" placeholder="请输入结束收到日期" name="endreceivedate" id="endreceivedate" /-->
    </div>    
    <div class="input-group input-group-lg">
          <span style="width:150px;text-align:right;height:48px;" class="input-group-addon text-right">交接人:</span>
         <input type="text" style="width:200px;text-align:left;height:48px;" class="form-control" value="" placeholder="请输入交接人" name="contractwho" id="contractwho" />
         
         <div class="input-group date form_datetime">
         <span style="width:150px;text-align:right;height:48px;" class="input-group-addon">开始签订日期:</span>
         <input type="text" class="form-control" style="width:200px;height:48px;" id="beginsigndate"  value="" readonly placeholder="请选择开始签订日期" />
		 <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
         <span style="width:150px;text-align:right;height:48px;" class="input-group-addon">结束签订日期::</span>
         <input type="text" class="form-control" style="width:200px;height:48px;" id="endsigndate"  value="" readonly placeholder="请选择结束签订日期" />
		 <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>		 
         </div>
         
         <!-- span style="width:150px;text-align:right;" class="input-group-addon text-right">开始签订日期:</span>
         <input type="text" style="width:200px;text-align:left;" class="form-control" value="" placeholder="请输入开始签订日期" name="beginsigndate" id="beginsigndate" />
         <span style="width:150px;text-align:right;" class="input-group-addon text-right">结束签订日期:</span>
         <input type="text" style="width:200px;text-align:left;" class="form-control" value="" placeholder="请输入结束签订日期" name="endsigndate" id="endsigndate" /-->
    </div>

    <div class="input-group input-group-lg">
         <span style="width:150px;text-align:right;height:48px;" class="input-group-addon text-right">合同类型:</span>
         <select id="contracttype" style="width:200px;text-align:left;height:48px;" class="selectpicker show-tick form-control">
          <option selected disabled>请选择合同类型</option>
          <option>建设合同</option>
          <option>采购合同</option>
          <option>分包合同</option>
          <option>其它辅材</option>
        </select>
         <span style="width:150px;text-align:right;height:48px;" class="input-group-addon text-right">最小合同金额:</span>
         <input type="text" style="width:240px;text-align:left;height:48px;" class="form-control" value="" placeholder="请输入最小合同金额" name="mincontractamount" id="mincontractamount" onkeyup="value=value.replace(/[^\d.]/g,'')"/>   
         <span style="width:150px;text-align:right;height:48px;" class="input-group-addon text-right">最大合同金额:</span>
         <input type="text" style="width:240px;text-align:left;height:48px;" class="form-control" value="" placeholder="请输入最大合同金额" name="maxcontractamount" id="maxcontractamount" onkeyup="value=value.replace(/[^\d.]/g,'')"/> 
    </div>      
    <br>
    
    <div style="width:1120px;">
    <div style="width:50%;padding:0;margin:0;float:left;box-sizing:border-box;">
    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="submitsearch()" class="join-btn">合同清单查询</button>
    </div>     
    <div style="width:50%;padding:0;margin:0;float:right;box-sizing:border-box;">
    <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="clearinput()" class="join-btn">清除查询条件</button>
    </div>
    </div>

</div>

<table id="contractstable"></table>
</body>
</html>