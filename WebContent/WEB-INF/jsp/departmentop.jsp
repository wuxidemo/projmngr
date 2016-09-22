<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>事业部创建及项目分配</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-table.min.css" rel="stylesheet"/>

<script src="js/jquery-1.12.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-table.min.js"></script>
<script src="js/bootstrap-table-export.min.js"></script>
<script src="js/tableExport.min.js"></script>
<script src="js/extensions/export/bootstrap-table-export.min.js"></script>
<script src="js/locales/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>

<style> 
.black_overlay { 
display: none; 
position: absolute; 
top: 0%; 
left: 0%; 
width: 100%; 
height: 100%; 
background-color: black; 
z-index: 1001; 
-moz-opacity: 0.8; 
opacity: .80; 
filter: alpha(opacity = 80); 
} 

.white_content { 
display: none; 
position: absolute; 
top: 20%; 
left: 30%; 
width: 30%; 
height: 50%; 
border: 5px solid lightblue; 
background-color: white; 
z-index: 1002; 
overflow: auto; 
} 

</style> 

<%!
String userName;
String userAccount;
%>

<script>
$(document).ready(function(){
	startQueryDep();
	startQueryProj();
});

var clickprojectsn;
function startQueryProj(){
	$.ajax({
		type:"GET",
		url:"/hsprojectmngr/projectsquery",
		success:function(data){
			var data = eval ("(" + data + ")");
			
			$('#projecttable').bootstrapTable('load',data);
			$('#projecttable').bootstrapTable({
			    data: data,
			    striped:true,
			    pagination:true,
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
			        field: 'assign',
			        title: '分配',
			    },],
			    onClickCell:function(field, value, row, $element){
			    	if(field == 'assign'){
			    		//alert("分配事业部");
			    		clickprojectsn = row.projectsn;
			    		showDepList('MyDep','fade');
			    	}
			    }
			});
			
		}
	});	
}

var gdata;
function showDepList(show_div,bg_div){

	$.ajax({
		type:"GET",
		url:"/hsprojectmngr/departmentquery",
		success:function(data){
			gdata = eval ("(" + data + ")");
			
			document.getElementById("newdep").value = "";
		    document.getElementById(show_div).style.display='block'; 
		    document.getElementById(bg_div).style.display='block' ; 
		    var bgdiv = document.getElementById(bg_div); 
		    bgdiv.style.width = document.body.scrollWidth; 
		    $("#"+bg_div).height($(document).height()); 
			
		    var div = document.getElementById(show_div);
		    var length = (div.childNodes.length-9)/2;
		    for(var i=0;i<length;i++){
		    	var childradio = document.getElementById("radio" + i);
		    	var childlabel = document.getElementById("label" + i);
		    	if(childradio != null){
		    		div.removeChild(childradio);	
		    	}
		    	if(childlabel != null){
		    		div.removeChild(childlabel);
		    	}
		    	
		    }
		    for(var i=0;i<gdata.length-1;i++)
		    {
		    	var radio=document.createElement("input");
		    	radio.setAttribute("type","radio");
		    	radio.id="radio" + i;
		    	radio.name = "choosedep";
		    	var label=document.createElement("label");
		    	label.id="label" + i;
		    	label.innerHTML = gdata[i].depname;		    	
		    	div.appendChild(radio);
		    	div.appendChild(label);
		    	
		    }
		}
	});	
	

} 

function saveSelectDep(){
    var div = document.getElementById("MyDep");
    var length = (div.childNodes.length-9)/2;
    var index = -1;
    for(var i=0;i<length;i++){
    	var childradio = document.getElementById("radio" + i);
    	if(childradio.checked == true){
    		index = i;
    		break;
    	}
    }
    if(index == -1){
    	alert("请选择事业部！");
    	return;
    }else{
    	//data[index].depname
    	$.ajax({
    		type:"POST",
    		url:"/hsprojectmngr/departmentaddproj?dep=" + gdata[index].depname + "&proj=" + clickprojectsn,
    		success:function(data){
    			var json = eval ("(" + data + ")");
    			json = eval(json.setproj);
    			alert(json[0].msg);
    			closeDepList('MyDep','fade');
    			startQueryProj();
    			startQueryDep();
    		}
    	});	
    }
}


function closeDepList(show_div,bg_div){
	document.getElementById("newdep").value = "";
    document.getElementById(show_div).style.display='none'; 
    document.getElementById(bg_div).style.display='none'; 
} 

var gprojs;
function startQueryDep(){
	$.ajax({
		type:"GET",
		url:"/hsprojectmngr/departmentquery",
		success:function(data){
			var data = eval ("(" + data + ")");
			
			$('#departmenttable').bootstrapTable('load',data);
			$('#departmenttable').bootstrapTable({
			    data: data,
			    striped:true,
			    search:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    columns: [{
			        field: 'index',
			        title: '序号',
			        sortable:true,
			    },{
			        field: 'depname',
			        title: '事业部',
			    },{
			        field: 'projects',
			        title: '项目汇总',
			    },],
			    onClickCell:function(field, value, row, $element){
			    	if(field == 'depname' && value == '点击增加事业部'){
			    		//alert("增加事业部");
			    		showDiv('MyDiv','fade');
			    	}else if(field == 'depname' && value != '点击增加事业部'){
			    		//修改事业部名字
			    		//document.getElementById("newdep").value = value;
			    		gprojs = row.projects;
			    		showAndModifyDiv('MyDiv','fade',value);
			    	}
			    }
			});
			
		}
	});
}

//弹出隐藏层
var oldname;
function showAndModifyDiv(show_div,bg_div,value){
	oldname = value;
	document.getElementById("newdep").value = value;
    document.getElementById(show_div).style.display='block'; 
    document.getElementById(bg_div).style.display='block' ; 
    var bgdiv = document.getElementById(bg_div); 
    bgdiv.style.width = document.body.scrollWidth; 
    $("#"+bg_div).height($(document).height()); 
} 

//弹出隐藏层 
function showDiv(show_div,bg_div){ 
	oldname = "";
	document.getElementById("newdep").value = "";
    document.getElementById(show_div).style.display='block'; 
    document.getElementById(bg_div).style.display='block' ; 
    var bgdiv = document.getElementById(bg_div); 
    bgdiv.style.width = document.body.scrollWidth; 
    $("#"+bg_div).height($(document).height()); 
} 
//关闭弹出层 
function closeDiv(show_div,bg_div){
	document.getElementById("newdep").value = "";
    document.getElementById(show_div).style.display='none'; 
    document.getElementById(bg_div).style.display='none'; 
    oldname = "";
} 

//关闭弹出层 
function deleteAndcloseDiv(show_div,bg_div){
	var name = $('#newdep').val();
	if(gprojs != "" && gprojs != null){
		alert("此事业部已分配项目，删除后则将跟项目解除关联，请重新分配项目。");
	}
	
	document.getElementById("newdep").value = "";
    document.getElementById(show_div).style.display='none'; 
    document.getElementById(bg_div).style.display='none'; 
    oldname = "";
	$.ajax({
		type:"POST",
		url:"/hsprojectmngr/departmentdel?name=" + name,
		success:function(data){
			var data = eval ("(" + data + ")");
			
			$('#departmenttable').bootstrapTable('load',data);
			$('#departmenttable').bootstrapTable({
			    data: data,
			    striped:true,
			    search:true,
			    pagination:true,
			    showExport:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc', 'excel'],
			    columns: [{
			        field: 'index',
			        title: '序号',
			        sortable:true,
			    },{
			        field: 'depname',
			        title: '事业部',
			    },{
			        field: 'projects',
			        title: '项目汇总',
			    },],
			    onClickCell:function(field, value, row, $element){
			    	if(field == 'depname' && value == '点击增加事业部'){
			    		//alert("增加事业部");
			    		showDiv('MyDiv','fade');
			    	}
			    }
			});
			
		}
	});
	startQueryProj();
} 

function saveAndCloseDiv(show_div,bg_div){
	var name = $('#newdep').val();
	
    document.getElementById(show_div).style.display='none'; 
    document.getElementById(bg_div).style.display='none'; 
    if(oldname == "" && name != ""){
    	//新增
    	$.ajax({
    		type:"POST",
    		url:"/hsprojectmngr/departmentadd?name=" + name,
    		success:function(data){
    			var data = eval ("(" + data + ")");
    			
    			$('#departmenttable').bootstrapTable('load',data);
    			$('#departmenttable').bootstrapTable({
    			    data: data,
    			    striped:true,
    			    search:true,
    			    pagination:true,
    			    showExport:true,
    			    Icons:'glyphicon-export icon-share',
    			    exportTypes:['doc', 'excel'],
    			    columns: [{
    			        field: 'index',
    			        title: '序号',
    			        sortable:true,
    			    },{
    			        field: 'depname',
    			        title: '事业部',
    			    },{
    			        field: 'projects',
    			        title: '项目汇总',
    			    },],
    			    onClickCell:function(field, value, row, $element){
    			    	if(field == 'depname' && value == '点击增加事业部'){
    			    		//alert("增加事业部");
    			    		showDiv('MyDiv','fade');
    			    	}
    			    }
    			});
    			
    		}
    	});
    }else if(oldname == "" && name == ""){
    	//空
    	return;
    }else if(oldname != "" && name == ""){
    	alert("名字为空，如果需要删除，点击删除按钮");
    	return;    	
    }else if(oldname != "" && name != ""){
    	if(oldname != name){
    		//更新事业部名字
    		$.ajax({
    			type:"POST",
    			url:"/hsprojectmngr/departmentmodify?name=" + name + "&oldname=" + oldname,
    			success:function(data){
    				var data = eval ("(" + data + ")");
    				
    				$('#departmenttable').bootstrapTable('load',data);
    				$('#departmenttable').bootstrapTable({
    				    data: data,
    				    striped:true,
    				    search:true,
    				    pagination:true,
    				    showExport:true,
    				    Icons:'glyphicon-export icon-share',
    				    exportTypes:['doc', 'excel'],
    				    columns: [{
    				        field: 'index',
    				        title: '序号',
    				        sortable:true,
    				    },{
    				        field: 'depname',
    				        title: '事业部',
    				    },{
    				        field: 'projects',
    				        title: '项目汇总',
    				    },],
    				    onClickCell:function(field, value, row, $element){
    				    	if(field == 'depname' && value == '点击增加事业部'){
    				    		//alert("增加事业部");
    				    		showDiv('MyDiv','fade');
    				    	}
    				    }
    				});
    				
    			}
    		});
    	}else{
    		//do nothing
    		return;
    	}
    }
    
    oldname = ""; 

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


<h1 class="text-primary">事业部及项目归属</h1>
<table id="departmenttable"></table>
<h2 class="text-primary">未分配项目列表</h2>
<table id="projecttable"></table>

<div id="fade" class="black_overlay"> 
</div> 
<div id="MyDiv" class="white_content"> 
    <div style="text-align: right; cursor: default; height: 40px;"> 
        <span style="font-size: 16px;" onclick="closeDiv('MyDiv','fade')">关闭</span> 
    </div> 
    <p>事业部名字:</p>
    <input id="newdep"></input>
    <button onclick="saveAndCloseDiv('MyDiv','fade')">保存</button>
    <button onclick="deleteAndcloseDiv('MyDiv','fade')">删除</button>
</div>



<div id="MyDep" class="white_content"> 
    <div style="text-align: right; cursor: default; height: 40px;"> 
        <span style="font-size: 16px;" onclick="closeDepList('MyDep','fade')">关闭</span> 
    </div> 
    <p>选择事业部:</p>
<br>
<button onclick="saveSelectDep()">保存</button>
</div>
</body>
</html>