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
});


var xmlhttp;
function dosearch(){
	//alert("searching");
	var projectsn = $('#inputprojectsn').val();
	if(projectsn == ""){
		alert("请输入项目编号，然后点击查询！")
	}else{
		//alert(projectsn);
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}

	    xmlhttp.open("GET","getoneprojectinfo?projectsn="+projectsn);

		xmlhttp.onreadystatechange=updateinfo;
		xmlhttp.send();
	}
	
	
}

function updateinfo(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			//
			var json = eval ("(" + xmlhttp.responseText + ")");
			json = eval(json.projectinfo);
			if(json.length > 0){
				if(json[0].msg != undefined){
					alert(json[0].msg);
					document.getElementById("projectname").value = "";
					document.getElementById("projectamount").value = "";
					document.getElementById("projectcost").value = "";
					document.getElementById("projectalert").value = "";
					document.getElementById("projectvendor").value = "";
					document.getElementById("projectreceipt").value = "";
					document.getElementById("projectpay").value = "";
					document.getElementById("projectprofit").value = "";
					document.getElementById("projectsn").value = "";
					document.getElementById("projectsigndate").value = "";
					document.getElementById("projectcontactwho").value = "";
					document.getElementById("projectfinishamount").value = "";
					document.getElementById("projectfinishdate").value = "";
					return;
				}
				document.getElementById("projectname").value = json[0].projectname;
				document.getElementById("projectamount").value = json[0].projectamount;
				document.getElementById("projectcost").value = json[0].projectcost;
				document.getElementById("projectalert").value = json[0].projectalert;
				document.getElementById("projectvendor").value = json[0].projectvendor;
				document.getElementById("projectreceipt").value = json[0].projectreceipt;
				document.getElementById("projectpay").value = json[0].projectpay;
				document.getElementById("projectprofit").value = json[0].projectprofit;
				document.getElementById("projectsn").value = json[0].projectsn;
				document.getElementById("projectsigndate").value = json[0].projectsigndate;
				document.getElementById("projectcontactwho").value = json[0].projectcontactwho;
				document.getElementById("projectfinishamount").value = json[0].projectfinishamount;
				document.getElementById("projectfinishdate").value = json[0].projectfinishdate;
				
				startBuildTable();
 
			}
		}
	}
}


function startBuildTable(){
	var projectsn = $('#inputprojectsn').val();
	if(projectsn == ""){
		alert("请输入项目编号，然后点击查询！")
	}else{
		//alert(projectsn);
		if (window.XMLHttpRequest)
		{// code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		}
		else
		{// code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}

	    xmlhttp.open("GET","getoneprojectinfotable?projectsn="+projectsn);

		xmlhttp.onreadystatechange=buildtable;
		xmlhttp.send();
	}	
}

function buildtable(){
	if(xmlhttp.readyState == 4){
		if(xmlhttp.status == 200){
			var data = eval ("(" + xmlhttp.responseText + ")");
			//$('#projecttable').bootstrapTable('load',data);
			$('#projecttable').bootstrapTable({
			    data: data,
			    striped:true,
			    pagination:true,
			    Icons:'glyphicon-export icon-share',
			    exportTypes:['doc'],
			    columns: [{
			        field: 'time',
			        title: '时间',
			        sortable:true,
			    },{
			        field: 'vendor',
			        title: '建设单位/供应商',
			        sortable:true,
			    },{
			        field: 'content',
			        title: '摘要',
			    },{
			        field: 'receiptamount',
			        title: '工程收款金额',
			        sortable:true,
			    },{
			        field: 'receiptbillamount',
			        title: '工程收款开票',
			        sortable:true,
			    },{
			        field: 'payamount',
			        title: '工程支出金额',
			        sortable:true,
			    },{
			        field: 'playbillamount',
			        title: '工程支出开票',
			        sortable:true,
			    },{
			        field: 'bond',
			        title: '保证金',
			        sortable:true,
			    },{
			        field: 'projectfee',
			        title: '费用收支金额',
			        sortable:true,
			    },{
			        field: 'projectbillfee',
			        title: '费用收票金额',
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


<h1 class="text-center text-primary">单项目查询</h1>

<div class="row">
    <div class="col-lg-12">
      <div class="panel panel-default">
        <div class="panel-heading">
          <input type="text" style="width:300px; text-align:left; height:48px;" class="" value="" placeholder="请输入项目编号 然后点击查询" name="inputprojectsn" id="inputprojectsn" />
          <input type="button" class="btn btn-primary" value="项目查询" onclick="dosearch()" />
        </div>
        <div class="panel-body">
          <div class="row">
            <div class="col-lg-6 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-2 control-label">项目名称</div>
                  <div class="col-md-10">
                    <input type="text" class="form-control" value=""  name="projectname" id="projectname" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-2 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">合同金额</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectamount" id="projectamount" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-2 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">项目成本</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectcost" id="projectcost" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-2 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">成本预警</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectalert" id="projectalert" readonly/>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="row">
            <div class="col-lg-6 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-2 control-label">甲方单位(建设单位)</div>
                  <div class="col-md-10">
                    <input type="text" class="form-control" value=""  name="projectvendor" id="projectvendor" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-2 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">实际收款</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectreceipt" id="projectreceipt" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-2 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">实际支出</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectpay" id="projectpay" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-2 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">实际利润</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectprofit" id="projectprofit" readonly/>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-6 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-2 control-label">项目编号</div>
                  <div class="col-md-10">
                    <input type="text" class="form-control" value=""  name="projectsn" id="projectsn" readonly/>
                  </div>
                </div> 
              </div>
            </div>
            <div class="col-lg-3 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">合同日期</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectsigndate" id="projectsigndate" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-5 control-label">工程联系人</div>
                  <div class="col-md-7">
                    <input type="text" class="form-control" value=""  name="projectcontactwho" id="projectcontactwho" readonly/>
                  </div>
                </div>
              </div>
            </div>
          </div>     


          <div class="row">
            <div class="col-lg-6 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-2 control-label">审定金额</div>
                  <div class="col-md-10">
                    <input type="text" class="form-control" value=""  name="projectfinishamount" id="projectfinishamount" readonly/>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-6 col-sm-8 col-xs-8 col-xxs-12">
              <div class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-2 control-label">完工日期</div>
                  <div class="col-md-10">
                    <input type="text" class="form-control" value=""  name="projectfinishdate" id="projectfinishdate" readonly/>
                  </div>
                </div>
              </div>
            </div>
          </div>  
                         
          <!--row结束-->
        </div>
      </div>
    </div>
    <!--/col-->
  </div>

<table id="projecttable" />




</body>
</html>