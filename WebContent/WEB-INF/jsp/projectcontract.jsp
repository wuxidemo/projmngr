<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>合同录入</title>

<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<%!
String userName;
String userAccount;
%>

<style> 
body{ text-align:center} 
.div{ margin:0 auto; width:650px;  } 
</style>

<script>

$(function () {
    $('.form_datetime').datetimepicker({
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

var xmlhttp;
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

var selectedContractType = "";
function contracttypeselectOnChange(){
	var obj = document.getElementById("contracttypeselect");
	selectedContractType = obj.options[obj.selectedIndex].text;
	if($("#projectsn").val() != "" && selectedContractType == "建设合同"){
		submitSearch();
	}
}

function submitcreatecontract(){
	var receivedate = $("#receivedate").val();
	var signdate = $("#signdate").val();
	
	var projectsn = $("#projectsn").val();
	if(projectsn == ""){
		alert("请输入项目编号");
		return;
	}
	
	if(selectedContractType == "请选择合同类型(必填)" || selectedContractType == ""){
		alert("请选择合同类型");
		return;
	}
	
	var contractcontent = $("#contractcontent").val();
	
	var contractvendor = $("#contractvendor").val();
	if(contractvendor == ""){
		alert("请输入合同采购单位/供应商");
		return;
	}
	var contractamount = $("#contractamount").val(); //合同金额
	var contractwho = $("#contractwho").val(); //合同交接人
	var description = $("#description").val(); //合同备注
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("POST","submitcreatecontract?receivedate=" + receivedate + "&signdate=" + signdate + "&projectsn=" + projectsn + "&selectedContractType=" + selectedContractType
			   + "&contractcontent=" + contractcontent + "&contractvendor=" + contractvendor + "&contractamount=" + contractamount + "&contractwho=" + contractwho
			   + "&description=" + description);

	xmlhttp.onreadystatechange=alertinfo;
	xmlhttp.send();	
}

function alertinfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			//
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.createcontract);
			if(json.length > 0){
				var msg = json[0].msg;
				if(msg != "" && msg != undefined){
					alert(msg);					
					document.getElementById("projectsn").value = "";
					document.getElementById("contracttypeselect").value = "请选择合同类型";
					document.getElementById("contractcontent").value = "";
					document.getElementById("contractvendor").value = "";
					document.getElementById("contractamount").value = "";
					document.getElementById("contractwho").value = "";
					document.getElementById("description").value = "";
				}
			}
		}
	}

}

function submitCheck(projectsn){
	
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

    xmlhttp.open("GET","getprojectinfocontract?projectsn="+$("#projectsn").val());

	xmlhttp.onreadystatechange=notifyinfo;
	xmlhttp.send();
}
function notifyinfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			//
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.projectinfo);
			if(json.length > 0){
				if(json[0].msg == 'notexist'){
					alert("项目编号不存在，请确认后输入！")
					document.getElementById("projectsn").value = "";
				}else{
					if(selectedContractType == "建设合同"){
						submitSearch();
					}
				}
				
			}
		}
	}

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
					document.getElementById("contractvendor").value = "";
				}else{
					document.getElementById("contractvendor").value = json[0].msg;
					document.getElementById("contractcontent").value = json[0].content;
					document.getElementById("contractwho").value = json[0].pm;
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
alert("合同录入成功！");
</script>
<%    		
    	}else if(result.equals("createfail")){
%>
<script>
    		alert("合同录入失败，请检查项目编号是否存在！");
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


<h2 class="form-signin-heading">请输入合同相关信息</h2>
<div class="container div">
    <div >
 
     <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">项目编号*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入项目编号(必填)" name="projectsn" id="projectsn" onblur="submitCheck()"/>
    </div>
      
    <div class="input-group date form_datetime">
         <span style="width:250px;text-align:right;" class="input-group-addon">收到日期:</span>
         <input type="text" class="form-control" style="width:350px;height:50px;" id="receivedate"  value="" readonly placeholder="请选择收到合同日期" />
		 <span class="input-group-addon" style="width:50px;"><span class="glyphicon glyphicon-th"></span></span>
    </div>
    
    <div class="input-group date form_datetime">
         <span style="width:250px;text-align:right;" class="input-group-addon">签订日期:</span>
         <input class="form-control" style="width:350px;height:50px;" id="signdate" size="16" type="text" value="" readonly placeholder="请选择签订合同日期" />
		 <span class="input-group-addon" style="width:50px;"><span class="glyphicon glyphicon-th"></span></span>
    </div>
         
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon">合同类型*:</span>
         <select id="contracttypeselect" style="width:400px;text-align:left;" class="selectpicker show-tick form-control" onchange="contracttypeselectOnChange()">
          <option selected disabled>请选择合同类型</option>
          <option>建设合同</option>
          <option>采购合同</option>
          <option>分包合同</option>
          <option>其它辅材</option>
        </select>
    </div>
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">合同内容:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入项目名称/合同内容" name="contractcontent" id="contractcontent" />
    </div> 
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">建设单位/供应商*:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入采购单位/供应商(必填)" name="contractvendor" id="contractvendor" />
    </div>    
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">合同金额:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入合同金额" name="contractamount" id="contractamount" onkeyup="value=value.replace(/[^\d.]/g,'')"/>
    </div>   
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">交接人:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入交接人" name="contractwho" id="contractwho" />
    </div>  
    <div class="input-group input-group-lg">
         <span style="width:250px;text-align:right;" class="input-group-addon text-right">备注:</span>
         <input type="text" style="width:400px;text-align:left;" class="form-control" value="" placeholder="请输入备注" name="description" id="description" />
    </div>      
    <br>
    <button style="width:620px;text-align:center;" class="btn btn-lg btn-primary btn-block" type="submit" onclick="submitcreatecontract()">项目合同录入</button><br>

    
    </div>
    
</div>


</body>
</html>