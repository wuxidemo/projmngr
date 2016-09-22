package com.hs.pm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.pm.bean.Department;
import com.hs.pm.bean.Project;
import com.hs.pm.bean.ProjectContract;
import com.hs.pm.bean.ProjectFee;
import com.hs.pm.bean.ProjectFinance;
import com.hs.pm.bean.ProjectFinish;
import com.hs.pm.bean.ProjectPay;
import com.hs.pm.bean.ProjectProgress;
import com.hs.pm.bean.ProjectReceipt;
import com.hs.pm.bean.ProjectStart;
import com.hs.pm.bean.User;
import com.hs.pm.dao.DepartmentDAO;
import com.hs.pm.dao.ProjectContractDAO;
import com.hs.pm.dao.ProjectDAO;
import com.hs.pm.dao.ProjectFeeDAO;
import com.hs.pm.dao.ProjectFinanceDAO;
import com.hs.pm.dao.ProjectFinishDAO;
import com.hs.pm.dao.ProjectPayDAO;
import com.hs.pm.dao.ProjectProgressDAO;
import com.hs.pm.dao.ProjectReceiptDAO;
import com.hs.pm.dao.ProjectStartDAO;
import com.hs.pm.dao.UserDAO;


@Controller
public class PMController {
	
	
	@RequestMapping("/")
	public static String homePage(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}

	@RequestMapping("/login")
	public static String login(HttpServletRequest request,HttpServletResponse response){
		return "index";
	}
	
	@RequestMapping("/mainmenu")
	public static String mainmenu(HttpServletRequest request,HttpServletResponse response){
		return "mainmenu";
	}

	@RequestMapping("/projectstart")
	public static String projectstart(HttpServletRequest request,HttpServletResponse response){
		return "projectstart";
	}
	
	
	@RequestMapping("/projectcontract")
	public static String projectcontract(HttpServletRequest request,HttpServletResponse response){
		return "projectcontract";
	}	
	
	@RequestMapping("/projectreceipt")
	public static String projectreceipt(HttpServletRequest request,HttpServletResponse response){
		return "projectreceipt";
	}
	
	@RequestMapping("/projectpay")
	public static String projectpay(HttpServletRequest request,HttpServletResponse response){
		return "projectpay";
	}
	
	@RequestMapping("/projectfinish")
	public static String projectfinish(HttpServletRequest request,HttpServletResponse response){
		return "projectfinish";
	}

	@RequestMapping("/projectfee")
	public static String projectfee(HttpServletRequest request,HttpServletResponse response){
		return "projectfee";
	}
	
	@RequestMapping("/projectfinance")
	public static String projectfinance(HttpServletRequest request,HttpServletResponse response){
		return "projectfinance";
	}
	
	@RequestMapping("/projectprogress")
	public static String projectprogress(HttpServletRequest request,HttpServletResponse response){
		return "projectprogress";
	}
	
	@RequestMapping("/searchcontracts")
	public static String searchcontracts(HttpServletRequest request,HttpServletResponse response){
		return "searchcontracts";
	}	

	@RequestMapping("/searchoneproject")
	public static String searchoneproject(HttpServletRequest request,HttpServletResponse response){
		return "searchoneproject";
	}
	@RequestMapping("/searchpay")
	public static String searchpay(HttpServletRequest request,HttpServletResponse response){
		return "searchpay";
	}
	@RequestMapping("/searchallproject")
	public static String searchallproject(HttpServletRequest request,HttpServletResponse response){
		return "searchallproject";
	}
	@RequestMapping("/searchdepartment")
	public static String searchdepartment(HttpServletRequest request,HttpServletResponse response){
		return "searchdepartment";
	}
	
	@RequestMapping("/resetpassword")
	public static String resetpassword(HttpServletRequest request,HttpServletResponse response){
		return "userpassword";
	}

	@RequestMapping("/accountinfo")
	public static String accountinfo(HttpServletRequest request,HttpServletResponse response){
		return "userinfo";
	}
	
	@RequestMapping("/adduser")
	public static String adduser(HttpServletRequest request,HttpServletResponse response){
		return "useradd";
	}	

	@RequestMapping("/modifyuser")
	public static String modifyuser(HttpServletRequest request,HttpServletResponse response){
		return "usermodify";
	}
	
	@RequestMapping("/department")
	public static String departmentOp(HttpServletRequest request,HttpServletResponse response){
		return "departmentop";
	}
	
	
	@RequestMapping("/logout")
	public static String logout(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		session.removeAttribute("loginName");
		return "index";
	}	
	
	
	/*
	 * 
	 * 登陆检查
	 * 
	 */
	@RequestMapping("/logincheck")
	public static void loginCheck(HttpServletRequest request,HttpServletResponse response){
		String useraccount = request.getParameter("useraccount");
		String password = request.getParameter("password");
		UserDAO dao = new UserDAO();
		User u = dao.searchUserByAccount(useraccount);
		if( u != null){
			if(dao.decryptPassword(u, password)){//密码验证
				System.out.println("login success.");
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(-1);
				session.setAttribute("loginUser", useraccount);
				session.setAttribute("loginName", u.getUsername());

				session.setAttribute("loginAuthority", u.getAuthority());
				session.setAttribute("searchAuthority", u.getSearchauthority());
				
				boolean adminpermission = false;
				if(useraccount.equals("admin")){
					adminpermission = true;
				}
				session.setAttribute("adminPermission", adminpermission);

				
				try {
					response.sendRedirect("/hsprojectmngr/mainmenu?loginsuccess");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					response.sendRedirect("/hsprojectmngr?result=loginfailpassword");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}else{
			try {
				response.sendRedirect("/hsprojectmngr?result=loginfailaccount");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * 项目立项
	 * 
	 * 
	 */
	@RequestMapping("/submitcreateproject")
	public static void submitCreateProject(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String projectname = request.getParameter("projectname");
		String projectvendor = request.getParameter("projectvendor");
		String projectmanager = request.getParameter("projectmanager");
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectStart p = new ProjectStart();
		p.setProjectsn(projectsn);
		p.setProjectname(projectname);
		p.setProjectvendor(projectvendor);
		p.setProjectmanager(projectmanager);
		p.setDatetime(datetime);
		
		String username = (String)request.getSession().getAttribute("loginName");
		p.setOperator(username);
		ProjectStartDAO dao = new ProjectStartDAO();
		dao.createProjectStart(p);
		
		member.put("msg", "项目立项录入成功！");
		
		jsonMembers.add(member);
		json.put("createproject", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createproject : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		/*try {
			if(result){
				//创建成功
			    response.sendRedirect("/hsprojectmngr/projectstart?result=createsuccess");
			}else{
				//创建失败
				response.sendRedirect("/hsprojectmngr/projectstart?result=createfail");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}

	/*
	 * 修改项目立项
	 * 
	 * 
	 */
	@RequestMapping("/submitmodifyproject")
	public static void submitModifyProject(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String projectname = request.getParameter("projectname");
		String projectvendor = request.getParameter("projectvendor");
		String projectmanager = request.getParameter("projectmanager");
		
		String username = (String)request.getSession().getAttribute("loginName");
		
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectStartDAO dao = new ProjectStartDAO();
		
		ProjectStart p = dao.findByProjectSN(projectsn);
		if(p != null){
			p.setProjectname(projectname);
			p.setProjectvendor(projectvendor);
			p.setProjectmanager(projectmanager);
			p.setDatetime(datetime);
			
			p.setOperator(username);
			
			dao.update(p);
		}
		
		Boolean adminPermission = (Boolean)request.getSession().getAttribute("adminPermission");
		
		String hql ="from ProjectStart ps where ps.operator = '" + username + "'";
		List<ProjectStart> lists = dao.list(hql);
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("projectsn", lists.get(i).getProjectsn());
				member.put("projectname",lists.get(i).getProjectname());
				member.put("projectvendor",lists.get(i).getProjectvendor());
				member.put("projectmanager",lists.get(i).getProjectmanager());
				member.put("operator", lists.get(i).getOperator());
				member.put("time", lists.get(i).getDatetime());
				Boolean value = lists.get(i).getRecordstatus();
				if(value == null || value == false){
					member.put("recordstatus","待审批，点击修改");
				}else{
					if(adminPermission == true){
						member.put("recordstatus","已审批，点击修改");
					}else{
						member.put("recordstatus","已审批");
					}
				}
				jsonMembers.add(member);
			}
			
		}
		

		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("projectsmodifyquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 创建合同
	 * 
	 */
	@RequestMapping("/submitcreatecontract")
	public static void submitCreateContract(HttpServletRequest request,HttpServletResponse response){
		
		String receivedate = request.getParameter("receivedate");
		String signdate = request.getParameter("signdate");
		String projectsn = request.getParameter("projectsn");
		String selectedContractType = request.getParameter("selectedContractType");
		String contractcontent = request.getParameter("contractcontent");
		String contractvendor = request.getParameter("contractvendor");
		String contractamount = request.getParameter("contractamount");
		String contractwho = request.getParameter("contractwho");
		String description = request.getParameter("description");
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ProjectContract p = new ProjectContract();
		if(receivedate != null && !receivedate.equals("")){
				//Date date1; 2016-05-01  yyyy-mm-dd hh:mm:ss
				//date1 = sdf.parse(receivedate);
			receivedate += " 00:00:00";
		    p.setReceivedate(Timestamp.valueOf(receivedate));
		}

		if(signdate != null && !signdate.equals("")){
			//Date date1 = new Date(signdate);
			signdate += " 00:00:00";
			p.setSigndate(Timestamp.valueOf(signdate));
		}		
		p.setProjectsn(projectsn);
		p.setContracttype(selectedContractType);
		
		if(contractcontent != null){
			p.setContractname(contractcontent);
		}
		if(contractvendor != null){
			p.setContractvendor(contractvendor);
		}		
		if(contractamount != null && !contractamount.equals("")){
			p.setContractamount(Float.valueOf(contractamount));
		}else{
			p.setContractamount((float)0.0);
		}
			
		if(contractwho != null){
			p.setContractwho(contractwho);
		}	
		if(description != null){
			p.setDescription(description);
		}	
		p.setDatetime(datetime);
		
		String username = (String)request.getSession().getAttribute("loginName");
		p.setOperator(username);
		ProjectContractDAO dao = new ProjectContractDAO();
		dao.createProjectContract(p);
		
		member.put("msg", "合同录入成功！");
		
		jsonMembers.add(member);
		json.put("createcontract", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createcontract : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}				
		
	}
	
	/*
	 * 
	 * 创建收款开票
	 * 
	 */
	@RequestMapping("/submitcreatereceipt")
	public static void submitCreateReceipt(HttpServletRequest request,HttpServletResponse response){
		
		String projectsn = request.getParameter("projectsn");
		String projectvendor = request.getParameter("projectvendor");
		String receiptcontent = request.getParameter("receiptcontent");
		String receiptbillamount = request.getParameter("receiptbillamount");//开票金额
		String receiptamount = request.getParameter("receiptamount");//回款金额
		String receipttype = request.getParameter("receipttype");
		String description = request.getParameter("description");


		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectReceipt p = new ProjectReceipt();
		p.setProjectsn(projectsn);
		p.setProjectvendor(projectvendor);
		
		if(receiptcontent != null){
			p.setReceiptcontent(receiptcontent);
		}
		if(receiptbillamount != null && !receiptbillamount.equals("")){
			
			float value = Float.valueOf(receiptbillamount);
			//获取已回款金额
			float alreadybillreceipt = new ProjectReceiptDAO().findBillReceiptAmountByProjectSN(projectsn);
			//比较回款金额
			//如果有审定金额，则与审定金额比较
			float totalfininal = 0;
			ProjectFinish pf = new ProjectFinishDAO().findByProjectSN(projectsn);
			if(pf != null){
				totalfininal = pf.getAmount();
				if(totalfininal != 0){
					if((totalfininal - alreadybillreceipt) < value){
						//审定金额减去已回款金额=可回款金额
						member.put("msg", "billoverflow");
						jsonMembers.add(member);
						json.put("createreceipt", jsonMembers);
					    response.setContentType("text/plain");
					    response.setCharacterEncoding("utf-8");
						String result = json.toString();
					    System.out.println("submitcreatereceipt : " + result);
					    try {
							PrintWriter pw = response.getWriter();
							pw.write(result);
							pw.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
						
				}else{
					//如果没有审定金额，则与建设合同金额比较
					List<ProjectContract> lists = new ProjectContractDAO().findByProjectSNAndType1(projectsn);
					float total = 0f;
					if(lists != null && lists.size() > 0){
						for(int i=0;i<lists.size();i++){
							total += lists.get(i).getContractamount();
						}
					}
					if((total - alreadybillreceipt) < value){
						member.put("msg", "billoverflow");
						jsonMembers.add(member);
						json.put("createreceipt", jsonMembers);
					    response.setContentType("text/plain");
					    response.setCharacterEncoding("utf-8");
						String result = json.toString();
					    System.out.println("submitcreatereceipt : " + result);
					    try {
							PrintWriter pw = response.getWriter();
							pw.write(result);
							pw.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
				}
			}else{
				//如果没有审定金额，则与建设合同金额比较
				List<ProjectContract> lists = new ProjectContractDAO().findByProjectSNAndType1(projectsn);
				float total = 0f;
				if(lists != null && lists.size() > 0){
					for(int i=0;i<lists.size();i++){
						total += lists.get(i).getContractamount();
					}
				}
				if((total - alreadybillreceipt) < value){
					member.put("msg", "billoverflow");
					jsonMembers.add(member);
					json.put("createreceipt", jsonMembers);
				    response.setContentType("text/plain");
				    response.setCharacterEncoding("utf-8");
					String result = json.toString();
				    System.out.println("submitcreatereceipt : " + result);
				    try {
						PrintWriter pw = response.getWriter();
						pw.write(result);
						pw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			
			p.setReceiptbillamount(Float.valueOf(receiptbillamount));
		}else{
			p.setReceiptbillamount((float)0.0);
		}

		if(receiptamount != null && !receiptamount.equals("")){
			float value = Float.valueOf(receiptamount);
			//获取已回款金额
			float alreadyreceipt = new ProjectReceiptDAO().findReceiptAmountByProjectSN(projectsn);
			//比较回款金额
			//如果有审定金额，则与审定金额比较
			float totalfininal = 0;
			ProjectFinish pf = new ProjectFinishDAO().findByProjectSN(projectsn);
			if(pf != null){
				totalfininal = pf.getAmount();
				if(totalfininal != 0){
					if((totalfininal - alreadyreceipt) < value){
						//审定金额减去已回款金额=可回款金额
						member.put("msg", "overflow");
						jsonMembers.add(member);
						json.put("createreceipt", jsonMembers);
					    response.setContentType("text/plain");
					    response.setCharacterEncoding("utf-8");
						String result = json.toString();
					    System.out.println("submitcreatereceipt : " + result);
					    try {
							PrintWriter pw = response.getWriter();
							pw.write(result);
							pw.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
						
				}else{
					//如果没有审定金额，则与建设合同金额比较
					List<ProjectContract> lists = new ProjectContractDAO().findByProjectSNAndType1(projectsn);
					float total = 0f;
					if(lists != null && lists.size() > 0){
						for(int i=0;i<lists.size();i++){
							total += lists.get(i).getContractamount();
						}
					}
					if((total - alreadyreceipt) < value){
						member.put("msg", "overflow");
						jsonMembers.add(member);
						json.put("createreceipt", jsonMembers);
					    response.setContentType("text/plain");
					    response.setCharacterEncoding("utf-8");
						String result = json.toString();
					    System.out.println("submitcreatereceipt : " + result);
					    try {
							PrintWriter pw = response.getWriter();
							pw.write(result);
							pw.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
				}
			}else{
				//如果没有审定金额，则与建设合同金额比较
				List<ProjectContract> lists = new ProjectContractDAO().findByProjectSNAndType1(projectsn);
				float total = 0f;
				if(lists != null && lists.size() > 0){
					for(int i=0;i<lists.size();i++){
						total += lists.get(i).getContractamount();
					}
				}
				if((total - alreadyreceipt) < value){
					member.put("msg", "overflow");
					jsonMembers.add(member);
					json.put("createreceipt", jsonMembers);
				    response.setContentType("text/plain");
				    response.setCharacterEncoding("utf-8");
					String result = json.toString();
				    System.out.println("submitcreatereceipt : " + result);
				    try {
						PrintWriter pw = response.getWriter();
						pw.write(result);
						pw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			p.setReceiptamount(value);
		}else{
			p.setReceiptamount((float) 0.0);
		}
		if(receipttype != null){
			p.setReceipttype(receipttype);
		}			
		if(description != null){
			p.setDescription(description);
		}	
		p.setDatetime(datetime);
		
		String username = (String)request.getSession().getAttribute("loginName");
		p.setOperator(username);
		ProjectReceiptDAO dao = new ProjectReceiptDAO();
		dao.createProjectReceipt(p);
		
		
		member.put("msg", "success");
		jsonMembers.add(member);
		json.put("createreceipt", jsonMembers);
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		String result = json.toString();
	    System.out.println("submitcreatereceipt : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	
	/*
	 * 
	 * 创建付款开票记录
	 * 
	 */
	@RequestMapping("/submitcreatepay")
	public static void submitCreatePay(HttpServletRequest request,HttpServletResponse response){
		
		String projectsn = request.getParameter("projectsn");
		String payvendor = request.getParameter("payvendor");//供应商
		String paycontent = request.getParameter("paycontent");
		String contracttype = request.getParameter("contracttype");
		String paybillamount = request.getParameter("paybillamount");//收票金额
		String payamount = request.getParameter("payamount");//付款金额
		String paytype = request.getParameter("paytype");
		String description = request.getParameter("description");

		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		//增加比对
		//1.获取此供应商在此项目下合同总计金额 projectsn payvendor
        ProjectContractDAO pdao = new ProjectContractDAO();
        float totalvalue = pdao.findAmountByProjectSN(projectsn, payvendor,contracttype);
		
        //2.获取此供应商在此项目下所有的已付款记录
        ProjectPayDAO ppdao = new ProjectPayDAO();
        float payedvalue = ppdao.findAmountByProjectSN(projectsn, payvendor,contracttype);
        float payedbillvalue = ppdao.findBillAmountByProjectSN(projectsn, payvendor,contracttype);
        
        //3.比较收票金额和付款金额
        if(paybillamount != null && !paybillamount.equals("")){
        	if((totalvalue-payedbillvalue) < Float.valueOf(paybillamount) ){
        		try {
					response.sendRedirect("/hsprojectmngr/projectpay?result=overflowbill");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return;
            }  
        }
        if(payamount != null && !payamount.equals("")){
        	if((totalvalue-payedvalue) < Float.valueOf(payamount) ){
        		try {
					response.sendRedirect("/hsprojectmngr/projectpay?result=overflowpay");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return;
            }  
        }              

        
        
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectPay p = new ProjectPay();
		p.setProjectsn(projectsn);
		p.setPayvendor(payvendor);
		p.setContracttype(contracttype);
		
		if(paycontent != null){
			p.setPaycontent(paycontent);
		}
		if(paybillamount != null && !paybillamount.equals("") ){
			p.setPaybillamount(Float.valueOf(paybillamount));
		}else{
			p.setPaybillamount((float)0.0);
		}

		if(payamount != null && !payamount.equals("")){
			p.setPayamount(Float.valueOf(payamount));
		}
		if(paytype != null){
			p.setPaytype(paytype);
		}			
		if(description != null){
			p.setDescription(description);
		}	
		p.setDatetime(datetime);
		String username = (String)request.getSession().getAttribute("loginName");
		p.setOperator(username);
		ProjectPayDAO dao = new ProjectPayDAO();
		dao.createProjectPay(p);
		
		
		member.put("msg", "录入付款收票成功！");
		
		jsonMembers.add(member);
		json.put("createpay", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createpay : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 项目完工
	 * 
	 * 
	 */
	@RequestMapping("/submitcreatefinish")
	public static void submitCreateFinish(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String contractdate = request.getParameter("contractdate");
		String finishdate = request.getParameter("finishdate");
		String amount = request.getParameter("amount");
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectFinishDAO dao = new ProjectFinishDAO();
		ProjectFinish pf = dao.findByProjectSN(projectsn);
		String username = (String)request.getSession().getAttribute("loginName");
		if( pf != null){
			pf.setProjectsn(projectsn);
			if(contractdate != null){
				pf.setContractdate(contractdate);
			}else{
				pf.setContractdate("");
			}
	        if(finishdate != null){
	        	pf.setFinishdate(finishdate);
	        }else{
	        	pf.setFinishdate("");
	        }
	        if(amount != null && !amount.equals("")){
	        	pf.setAmount(Float.valueOf(amount));
	        }else{
	        	pf.setAmount((float) 0.0);
	        }
	        pf.setDatetime(datetime);
	        pf.setOperator(username);
	        dao.updateProjectFinish(pf);
		}else{
			pf = new ProjectFinish();
			pf.setProjectsn(projectsn);
			if(contractdate != null){
				pf.setContractdate(contractdate);
			}else{
				pf.setContractdate("");
			}
	        if(finishdate != null){
	        	pf.setFinishdate(finishdate);
	        }else{
	        	pf.setFinishdate("");
	        }
	        if(amount != null && !amount.equals("")){
	        	pf.setAmount(Float.valueOf(amount));
	        }else{
	        	pf.setAmount((float) 0.0);
	        }
			pf.setDatetime(datetime);
			pf.setOperator(username);
			dao.createProjectFinish(pf);
		}
		
		
		member.put("msg", "录入项目完工成功！");
		
		jsonMembers.add(member);
		json.put("createfinish", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createfinish : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * 创建事业部费用录入
	 * @param request
	 * @param response
	 */
	@RequestMapping("/submitcreatefee")
	public static void submitCreateFee(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String feecontent = request.getParameter("feecontent");
		String receiptamount = request.getParameter("receiptamount");
		String payamount = request.getParameter("payamount");
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();		
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectFeeDAO dao = new ProjectFeeDAO();
		ProjectFee pf = new ProjectFee();
		String username = (String)request.getSession().getAttribute("loginName");
		
		pf.setProjectsn(projectsn);
		if(feecontent != null){
			pf.setFeecontent(feecontent);
		}else{
			pf.setFeecontent("");
		}
        if(receiptamount != null && !receiptamount.equals("")){
        	pf.setReceiptamount(Float.valueOf(receiptamount));
        }else{
        	pf.setReceiptamount((float) 0.0);
        }
        if(payamount != null && !payamount.equals("")){
        	pf.setPayamount(Float.valueOf(payamount));
        }else{
        	pf.setPayamount((float) 0.0);;
        }
        pf.setDatetime(datetime);
        pf.setOperator(username);

		dao.createProjectFee(pf);
	

		member.put("msg", "录入事业部费用成功！");
		
		jsonMembers.add(member);
		json.put("createfee", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createfee : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}		

	/**
	 * 
	 * 创建事业部费用录入
	 * @param request
	 * @param response
	 */
	@RequestMapping("/submitcreateprogress")
	public static void submitCreateProgress(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String projectplancost = request.getParameter("projectplancost");
		String projectprogress = request.getParameter("projectprogress");
		String projectoverview = request.getParameter("projectoverview");
		String projectplanreceipt = request.getParameter("projectplanreceipt");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		ProjectProgressDAO dao = new ProjectProgressDAO();
		ProjectProgress pp = new ProjectProgress();
		String username = (String)request.getSession().getAttribute("loginName");
		
		pp.setProjectsn(projectsn);
		if(projectplancost != null && !projectplancost.equals("")){
			pp.setProjectplancost(Float.valueOf(projectplancost));
		}else{
			pp.setProjectplancost((float)0.0);
		}
        if(projectprogress != null){
        	pp.setProjectprogress(projectprogress);
        }else{
        	pp.setProjectprogress("");
        }
        if(projectoverview != null){
        	pp.setProjectoverview(projectoverview);
        }else{
        	pp.setProjectoverview("");;
        }
        if(projectplanreceipt != null){
        	pp.setProjectplanreceipt(projectplanreceipt);
        }else{
        	pp.setProjectplanreceipt("");;
        }
        
        pp.setDatetime(datetime);
        pp.setOperator(username);

		dao.createProjectProgress(pp);
	
		member.put("msg", "录入项目进度成功！");
		
		jsonMembers.add(member);
		json.put("createprogress", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createprogress : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}		
	
	/**
	 * 
	 * 创建财务中心录入
	 * @param request
	 * @param response
	 */
	@RequestMapping("/submitcreatefinance")
	public static void submitCreateFinance(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String financecontent = request.getParameter("financecontent");
		String salary = request.getParameter("salary");
		String financecost = request.getParameter("financecost");
		String financecredit = request.getParameter("financecredit");
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();	
		
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");		
		String datetime = format.format(date);
		
		ProjectFinanceDAO dao = new ProjectFinanceDAO();
		ProjectFinance pf = new ProjectFinance();
		String username = (String)request.getSession().getAttribute("loginName");
		
		pf.setProjectsn(projectsn);
		if(financecontent != null){
			pf.setFinancecontent(financecontent);
		}else{
			pf.setFinancecontent("");
		}
        if(salary != null){
        	pf.setSalary(salary);
        }else{
        	pf.setSalary("");
        }
        if(financecost != null){
        	pf.setFinancecost(financecost);
        }else{
        	pf.setFinancecost("");
        }
        if(financecredit != null){
        	pf.setFinancecredit(financecredit);
        }else{
        	pf.setFinancecredit("");
        }
        
        pf.setDatetime(datetime);
        pf.setOperator(username);

		dao.createProjectFinance(pf);
	

		member.put("msg", "录入财务中心数据成功！");
		
		jsonMembers.add(member);
		json.put("createfinance", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");

		
		String result = json.toString();
	    System.out.println("createfinance : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}		
	
	/*
	 * 
	 * 获取项目信息
	 * 
	 */
	@RequestMapping("/getprojectinfo")
	public static void getProjectInfo(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		//1.检查项目编号是否立项
		if(new ProjectStartDAO().checkProjectExist(projectsn)){
		    ProjectFinish pf = new ProjectFinishDAO().findByProjectSN(projectsn);
		    if(pf != null){
		    	member.put("contractdate", pf.getContractdate());
		    	member.put("finishdate", pf.getFinishdate());
		    	member.put("amount", pf.getAmount());
		    	member.put("msg", "update");
		    }else{
		    	member.put("msg", "create");
		    }
		}else{
			//此项目id尚未立项
			member.put("msg", "notexist");
		}
		jsonMembers.add(member);
		json.put("projectinfo", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("getprojectinfo : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * 收款开票获取项目信息
	 * 
	 */
	@RequestMapping("/getprojectinforeceipt")
	public static void getProjectInfoReceipt(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		ProjectStart ps = new ProjectStartDAO().findByProjectSN(projectsn);
		if(ps != null){
			member.put("msg", ps.getProjectvendor());
			member.put("content", ps.getProjectname());
			member.put("pm", ps.getProjectmanager());
		}else{
			member.put("msg", "notexist");
		}

		jsonMembers.add(member);
		json.put("projectinfo", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("getprojectinforeceipt : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * 
	 * 收款开票获取项目信息
	 * 
	 */
	@RequestMapping("/getprojectinfocontract")
	public static void getProjectInfoContract(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		ProjectStart ps = new ProjectStartDAO().findByProjectSN(projectsn);
		if(ps != null){
			member.put("msg", "exist");
		}else{
			member.put("msg", "notexist");
		}

		jsonMembers.add(member);
		json.put("projectinfo", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("getprojectinforeceipt : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 
	 * 查询项目是否存在
	 * 
	 */
	@RequestMapping("/getprojectexist")
	public static void getProjectExist(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		//1.检查项目编号是否立项
		if(new ProjectStartDAO().checkProjectExist(projectsn)){
			member.put("msg", "此项目编号已立项，请重新输入项目编号！");
		}
		jsonMembers.add(member);
		json.put("projectinfo", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("getprojectexist : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	/*
	 * 
	 * 重新设置密码
	 * 
	 */
	@RequestMapping("/resetpasswordaction")
	public static void resetpasswordaction(HttpServletRequest request,HttpServletResponse response){
		String oldpassword = request.getParameter("oldpassword");
		String newpassword = request.getParameter("newpassword");
		String useraccount = (String)request.getSession().getAttribute("loginUser");
		
		UserDAO dao = new UserDAO();
		User user = dao.searchUserByAccount(useraccount);
		if(dao.decryptPassword(user, oldpassword)){
			user.setPassword(newpassword);
			dao.updateUser(user);
			try {
				response.sendRedirect("/hsprojectmngr/resetpassword?resetpasswordresult=ok");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else{
			try {
				response.sendRedirect("/hsprojectmngr/resetpassword?resetpasswordresult=error");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	/*
	 * 
	 * 创建用户
	 * 
	 */
	@RequestMapping("/submitcreateuser")
	public static void submitcreateuser(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		String useraccount = request.getParameter("useraccount");
		String password = request.getParameter("password");
		String pj1 = request.getParameter("pj1");
		String pj2 = request.getParameter("pj2");
		String pj3 = request.getParameter("pj3");
		String pj4 = request.getParameter("pj4");
		String pj5 = request.getParameter("pj5");
		String pj6 = request.getParameter("pj6");
		String pj7 = request.getParameter("pj7");
		String pj8 = request.getParameter("pj8");
		
		String spj1 = request.getParameter("spj1");
		String spj2 = request.getParameter("spj2");
		String spj3 = request.getParameter("spj3");
		String spj4 = request.getParameter("spj4");
		String spj5 = request.getParameter("spj5");
		
		//获取用户权限列表
		String authority = "";//录入权限
		String searchauthority="";//查询权限
		if(pj1.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj2.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}		
		if(pj3.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj4.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj5.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj6.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj7.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj8.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}	
		
		if(spj1.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		if(spj2.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}		
		if(spj3.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		if(spj4.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		if(spj5.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		
		User u = new User();
		u.setUseraccount(useraccount);
		u.setUsername(username);
		u.setPassword(password);
		u.setAuthority(authority);
		u.setSearchauthority(searchauthority);
		boolean result = new UserDAO().createUser(u);

		try {
			if(result){
				response.sendRedirect("/hsprojectmngr/adduser?adduserresult=success");
			}else{
				response.sendRedirect("/hsprojectmngr/adduser?adduserresult=error");
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	/*
	 * 
	 * 更具用户名查询User信息
	 * 
	 */
	@RequestMapping("/searchuserbyname")
	public static void searchUserByName(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		JSONObject json = new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();

		User u = new UserDAO().searchUserByName(username);
		if(u != null)
		{
	    	member.put("username", u.getUsername());
	    	member.put("useraccount", u.getUseraccount());
	    	member.put("authority", u.getAuthority());
	    	member.put("searchauthority", u.getSearchauthority());
	    	member.put("msg", "ok");
		    
		}else{
			//此项目id尚未立项
			member.put("msg", "notexist");
		}
		jsonMembers.add(member);
		json.put("userinfo", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("getuserinfo : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 
	 * 更新用户
	 * 
	 */
	@RequestMapping("/updateuseraction")
	public static void updateUserAction(HttpServletRequest request,HttpServletResponse response){
		String username = request.getParameter("username");
		String useraccount = request.getParameter("useraccount");
		String pj1 = request.getParameter("pj1");
		String pj2 = request.getParameter("pj2");
		String pj3 = request.getParameter("pj3");
		String pj4 = request.getParameter("pj4");
		String pj5 = request.getParameter("pj5");
		String pj6 = request.getParameter("pj6");
		String pj7 = request.getParameter("pj7");
		String pj8 = request.getParameter("pj8");
		
		String spj1 = request.getParameter("spj1");
		String spj2 = request.getParameter("spj2");
		String spj3 = request.getParameter("spj3");
		String spj4 = request.getParameter("spj4");
		String spj5 = request.getParameter("spj5");		
		
		//获取用户权限列表
		String authority = "";//录入权限
		String searchauthority="";//查询权限
		if(pj1.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj2.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}		
		if(pj3.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj4.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj5.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj6.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj7.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}
		if(pj8.equals("true")){
			authority += "1";
		}else{
			authority += "0";
		}	
		
		if(spj1.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		if(spj2.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}		
		if(spj3.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		if(spj4.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		if(spj5.equals("true")){
			searchauthority += "1";
		}else{
			searchauthority += "0";
		}
		
		UserDAO dao = new UserDAO();
		User u = dao.searchUserByAccount(useraccount);
		u.setUsername(username);
		u.setAuthority(authority);
		u.setSearchauthority(searchauthority);
		dao.updateUser(u);

		try {		
			response.sendRedirect("/hsprojectmngr/modifyuser?modifyuserresult=ok");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
	
	
	@RequestMapping("/submitsearchcontracts")
	public static void submitSearchContracts(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		String beginreceivedate = request.getParameter("beginreceivedate");
		String endreceivedate = request.getParameter("endreceivedate");
		String contractwho = request.getParameter("contractwho");
		String beginsigndate = request.getParameter("beginsigndate");
		String endsigndate = request.getParameter("endsigndate");
		String contracttype = request.getParameter("contracttype");
		String mincontractamount = request.getParameter("mincontractamount");
		String maxcontractamount = request.getParameter("maxcontractamount");
		
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		ProjectContractDAO dao = new ProjectContractDAO();
		String hql = "from ProjectContract pc where 1=1";
		if(projectsn != null && !projectsn.equals("")){
		    hql += " and pc.projectsn like '" + projectsn + "'";	
		}

		if(contractwho != null && !contractwho.equals("")){
		    hql += " and pc.contractwho like '" + contractwho + "'";	
		}
		
		if(beginreceivedate != null && !beginreceivedate.equals("")){
			Timestamp beginTs = Timestamp.valueOf(beginreceivedate +" 00:00:00");//2016-05-04 00:00:00.0
			Timestamp endTs;
			if(endreceivedate != null && !endreceivedate.equals("")){
				endTs = Timestamp.valueOf(endreceivedate + " 00:00:00");					
			}else{
				endTs = new Timestamp(System.currentTimeMillis());				
			}
			hql += " and pc.receivedate between '" + beginTs + "' and '" + endTs + "'";
		}else{
			//开始日期为空
			if(endreceivedate != null && !endreceivedate.equals("")){
				Timestamp beginTs = Timestamp.valueOf("2000-01-01 00:00:00");//2016-05-04 00:00:00.0
				Timestamp endTs = Timestamp.valueOf(endreceivedate + " 00:00:00");
				hql += " and pc.receivedate between '" + beginTs + "' and '" + endTs + "'";
			}
		}
		
		if(contracttype != null && !contracttype.equals("") && !contracttype.equals("null")){
		    hql += " and pc.contracttype like '" + contracttype + "'";	
		}
		
		if(mincontractamount != null && !mincontractamount.equals("")){
			if(maxcontractamount != null && !maxcontractamount.equals("")){
				hql += " and pc.contractamount >= '" + mincontractamount + "' and pc.contractamount <= '" + maxcontractamount + "'";				
			}else{
				hql += " and pc.contractamount >= '" + mincontractamount + "'";			
			}
		}else{
			//开始日期为空
			if(maxcontractamount != null && !maxcontractamount.equals("")){
				hql += " and pc.contractamount <= '" + maxcontractamount + "'";
			}
		}
		
		if(beginsigndate != null && !beginsigndate.equals("")){
			Timestamp beginTs = Timestamp.valueOf(beginsigndate +" 00:00:00");//2016-05-04 00:00:00.0
			Timestamp endTs;
			if(endsigndate != null && !endsigndate.equals("")){
				endTs = Timestamp.valueOf(endsigndate + " 00:00:00");					
			}else{
				endTs = new Timestamp(System.currentTimeMillis());				
			}
			hql += " and pc.signdate between '" + beginTs + "' and '" + endTs + "'";
		}else{
			//开始日期为空
			if(endsigndate != null && !endsigndate.equals("")){
				Timestamp beginTs = Timestamp.valueOf("2000-01-01 00:00:00");//2016-05-04 00:00:00.0
				Timestamp endTs = Timestamp.valueOf(endsigndate + " 00:00:00");
				hql += " and pc.receivedate between '" + beginTs + "' and '" + endTs + "'";
			}
		}
		/*if(hql.length() == 30){
			hql = "from ProjectContract pc";
		}*/
		List<ProjectContract> lists = dao.lists(hql);
		for (int i=0;i<lists.size();i++) {
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Timestamp tsReceiveDate = lists.get(i).getReceivedate();
			if(tsReceiveDate != null){
			    member.put("receivedate", sdf.format(tsReceiveDate));
			}else{
				member.put("receivedate", "");
			}
			Timestamp tsSignDate = lists.get(i).getSigndate();
			if(tsSignDate != null){
			    member.put("signdate", sdf.format(tsSignDate));
			}else{
				member.put("signdate", "");
			}
			member.put("projectsn", lists.get(i).getProjectsn());
			member.put("contracttype", lists.get(i).getContracttype());
			member.put("contractname", lists.get(i).getContractname());
			member.put("contractvendor", lists.get(i).getContractvendor());
			member.put("contractamount",String.valueOf(lists.get(i).getContractamount()));
			member.put("contractwho", lists.get(i).getContractwho());
			member.put("description", lists.get(i).getDescription());
			jsonMembers.add(member);
		}
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
	    
	    String result = jsonMembers.toString();
	    System.out.println("submitSearchContracts : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	/*
	 * 
	 * 搜索单项目信息
	 * 
	 */
	@RequestMapping("/getoneprojectinfo")
	public static void getOneProjectInfo(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		ProjectStart ps = new ProjectStartDAO().findByProjectSN(projectsn);
		if(ps != null){
			member.put("projectcontactwho", ps.getProjectmanager());
		}else{
			member.put("msg", "项目不存在，请检查项目编号");
			jsonMembers.add(member);
			json.put("projectinfo", jsonMembers);
			
		    response.setContentType("text/plain");
		    response.setCharacterEncoding("utf-8");
			
		    String result = json.toString();
		    System.out.println("getOneProjectInfo : " + result);
		    try {
				PrintWriter pw = response.getWriter();
				pw.write(result);
				pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    return;
		}
		
		//建设合同查询
		float projectamount = 0;
		List<ProjectContract> list1 = new ProjectContractDAO().findByProjectSNAndType1(projectsn);
		if(list1 != null && list1.size()>0){
			member.put("projectname", list1.get(0).getContractname());
			member.put("projectvendor", list1.get(0).getContractvendor());
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			member.put("projectsigndate", sdf.format(list1.get(0).getSigndate()));
			for(int i=0;i<list1.size();i++){
				projectamount += list1.get(i).getContractamount();
			}
			member.put("projectamount",String.valueOf(projectamount));
		}else{
			member.put("projectname", "");
			member.put("projectamount","");
		}
		
		//项目成本非建设合同查询
		float projectcost = 0;
		List<ProjectContract> list2 = new ProjectContractDAO().findByProjectSNAndType2(projectsn);
		if(list2 != null && list2.size()>0){
			for(int i=0;i<list2.size();i++){
				projectcost += list2.get(i).getContractamount();
			}
			member.put("projectcost",String.valueOf(projectcost));
		}else{
			member.put("projectcost","");
		}
		
		member.put("projectsn", projectsn);
	

		
		ProjectFinish pf = new ProjectFinishDAO().findByProjectSN(projectsn);
		float finishamount = 0;//审定金额
		if(pf != null){
			member.put("projectfinishamount", pf.getAmount());
			member.put("projectfinishdate", pf.getFinishdate());
			finishamount = pf.getAmount();
		}else{
			member.put("projectfinishamount", "");
			member.put("projectfinishdate", "");
		}
		
		
		//实际收款  
		float receiptamount = 0,payamount = 0;
		List<ProjectReceipt> prlist = new ProjectReceiptDAO().findByProjectSN(projectsn);
		if(prlist != null && prlist.size()>0){
			for(int i=0;i<prlist.size();i++){
				receiptamount += prlist.get(i).getReceiptamount();
			}
			member.put("projectreceipt", String.valueOf(receiptamount));
		}else{
			member.put("projectreceipt", "");
		}
		
		//实际支出
		float temp = 0f;//费用收支金额
		List<ProjectFee> pflist = new ProjectFeeDAO().findByProjectSN(projectsn);
		if(pflist != null && pflist.size()>0){
			for(int i=0;i<pflist.size();i++){
				temp += pflist.get(i).getPayamount();
			}
		}
		
		List<ProjectPay> pplist = new ProjectPayDAO().findByProjectSN(projectsn);
		if(pplist != null && pplist.size()>0){
			for(int i=0;i<pplist.size();i++){
				payamount += pplist.get(i).getPayamount();
			}
			member.put("projectpay", String.valueOf(payamount + temp ));
		}else{
			member.put("projectpay", "");
		}
		
		//实际利润
		member.put("projectprofit", String.valueOf(receiptamount-payamount-temp));
		
		//0. 成本预警 = 审定金额 - 项目成本
		//1. 成本预警 = 合同金额 - 项目成本
		if(finishamount != 0){
			member.put("projectalert", String.valueOf(finishamount-projectcost));
		}else{
			member.put("projectalert", String.valueOf(projectamount-projectcost));
		}
		
		
		jsonMembers.add(member);
		json.put("projectinfo", jsonMembers);
		
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("getOneProjectInfo : " + result);
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * 查询单项目table数据
	 * 
	 */
	@RequestMapping("/getoneprojectinfotable")
	public static void getOneProjectInfoTable(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");	
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();

		//实际收款  
		float receiptamount = 0,receiptbillamount=0,payamount = 0,paybillamount=0;
		List<ProjectReceipt> prlist = new ProjectReceiptDAO().findByProjectSN(projectsn);
		if(prlist != null && prlist.size()>0){
			for(int i=0;i<prlist.size();i++){
				receiptamount += prlist.get(i).getReceiptamount();
				receiptbillamount += prlist.get(i).getReceiptbillamount();
				member.put("time", prlist.get(i).getDatetime());
				member.put("vendor", prlist.get(i).getProjectvendor());
				member.put("content", prlist.get(i).getReceiptcontent());
				member.put("receiptamount", String.valueOf(prlist.get(i).getReceiptamount()));
				member.put("receiptbillamount", String.valueOf(prlist.get(i).getReceiptbillamount()));
				member.put("payamount", "");
				member.put("playbillamount", "");
				member.put("bond", "");
				member.put("projectfee", "");
				member.put("projectbillfee", "");
				jsonMembers.add(member);
			}
		}
		
		//工程支出
		List<ProjectPay> pplist = new ProjectPayDAO().findByProjectSN(projectsn);
		if(pplist != null && pplist.size()>0){
			for(int i=0;i<pplist.size();i++){
				payamount += pplist.get(i).getPayamount();
				paybillamount += pplist.get(i).getPaybillamount();
				member.put("time", pplist.get(i).getDatetime());
				member.put("vendor", pplist.get(i).getPayvendor());
				member.put("content", pplist.get(i).getPaycontent());
				member.put("receiptamount", "");
				member.put("receiptbillamount", "");
				member.put("payamount", String.valueOf(pplist.get(i).getPayamount()));
				member.put("playbillamount", String.valueOf(pplist.get(i).getPaybillamount()));
				member.put("bond", "");
				member.put("projectfee", "");
				member.put("projectbillfee", "");				
				jsonMembers.add(member);
			}
		}
		
		//事业部费用支出
		float feeamount=0,feebillamount=0;
		List<ProjectFee> pflist = new ProjectFeeDAO().findByProjectSN(projectsn);
		if(pflist != null && pflist.size()>0){
			for(int i=0;i<pflist.size();i++){
				feeamount += pflist.get(i).getPayamount();
				feebillamount += pflist.get(i).getReceiptamount();		
				member.put("time", pflist.get(i).getDatetime());
				member.put("vendor", "");
				member.put("content", pflist.get(i).getFeecontent());
				member.put("receiptamount", "");
				member.put("receiptbillamount", "");
				member.put("payamount", "");
				member.put("playbillamount", "");
				member.put("bond", "");
				member.put("projectfee", String.valueOf(pflist.get(i).getPayamount()));
				member.put("projectbillfee", String.valueOf(pflist.get(i).getReceiptamount()));				
				jsonMembers.add(member);
			}
		}
		
		member.put("time", "合计");
		member.put("vendor", "");
		member.put("content", "");
		member.put("receiptamount", String.valueOf(receiptamount));
		member.put("receiptbillamount", String.valueOf(receiptbillamount));
		member.put("payamount", String.valueOf(payamount));
		member.put("playbillamount", String.valueOf(paybillamount));
		member.put("bond", "");
		member.put("projectfee", String.valueOf(feeamount));
		member.put("projectbillfee", String.valueOf(feebillamount));
		jsonMembers.add(member);

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("getoneprojectinfotable : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	
	/**
	 * 
	 * 获取付款明细标题
	 */
	@RequestMapping("/getpaytitle")
	public static void getPayTitle(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		String contractContent = "";//合同内容
		ProjectContractDAO dao = new ProjectContractDAO();
		//获取合同内容
		ProjectContract pc = dao.findByProjectSNAndType(projectsn,"建设合同");
		if(pc != null){
			contractContent = pc.getContractname();
			member.put("contractContent", contractContent);
		}else{
			member.put("contractContent", "error");
		}
		
		
		jsonMembers.add(member);

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("getPayTitle : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	 * 查询付款明细
	 * 
	 */
	@RequestMapping("/getpaydetails")
	public static void getPayDetails(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");	
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();

		ProjectContractDAO dao = new ProjectContractDAO();
		
		//供应商
		List<String> vendors = new LinkedList<String>();
		List<ProjectContract> lists = dao.findByProjectSNNorType(projectsn, "建设合同");
		if(lists != null && lists.size() > 0){
			//获取到所有非建设合同
			for(int i=0;i<lists.size();i++){
				String temp = lists.get(i).getContractvendor();
				if(!vendors.contains(temp)){
					vendors.add(temp);
				}				
				
			}
		}

		if(vendors != null && vendors.size()>0){
			float total1 = 0,total2=0,total3=0;
			for(int i=0;i<vendors.size();i++){
				String vendor = vendors.get(i);
				//搜索合同表
				String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contractvendor like '" + vendor + "' and pc.contracttype not like '建设合同'";
				List<ProjectContract> list = dao.lists(hql);
				// 合同金额
				float totalamount = 0;
				if(list != null && list.size()>0){
					int maxindex = 0;//合同金额最大的一条记录index
					for(int j=0;j<list.size();j++){
						totalamount += list.get(j).getContractamount();
						if(j>0){
							if(list.get(j).getContractamount() > list.get(maxindex).getContractamount()){
								maxindex = j;
							}
						}else{
							maxindex = 0;
						}
					}
					member.put("vendor", vendor);
					member.put("totalamount", String.valueOf(totalamount));
					member.put("name", list.get(maxindex).getContractname());
				}else{
					member.put("vendor", vendor);
					member.put("totalamount", "");
					member.put("name", "");
				}
				
				//搜索付款收票表
				float payamount = 0,receiptamount =0;
				String hql2 = "from ProjectPay pp where pp.projectsn like '" + projectsn + "' and pp.payvendor like '" + vendor + "'";
				List<ProjectPay> list2 = new ProjectPayDAO().lists(hql2);
				if(list2 != null && list2.size()>0){
					
					for(int j=0;j<list2.size();j++){
						payamount += list2.get(j).getPayamount();
						receiptamount += list2.get(j).getPaybillamount();
					}
					member.put("paylist", String.valueOf(payamount));
					member.put("receipt", String.valueOf(receiptamount));
				}else{
					member.put("paylist", "");
					member.put("receipt", "");
				}
				//转换成BigDecimal解决精度问题
				BigDecimal b1 = new BigDecimal(Float.toString(totalamount));
				BigDecimal b2 = new BigDecimal(Float.toString(payamount));
				
				member.put("notpayamount", String.valueOf(b1.subtract(b2).floatValue()));
				member.put("comment","");
				
				jsonMembers.add(member);
				total1 += totalamount;
				total2 += payamount;
				total3 += receiptamount;
			}
			
			member.put("vendor", "合计");
			member.put("name", "");
			member.put("totalamount", String.valueOf(total1));
			member.put("paylist", String.valueOf(total2));
			//转换成BigDecimal解决精度问题
			BigDecimal b1 = new BigDecimal(Float.toString(total1));
			BigDecimal b2 = new BigDecimal(Float.toString(total2));
			member.put("notpayamount", String.valueOf(b1.subtract(b2).floatValue()));
			member.put("receipt", String.valueOf(total3));

		}




		jsonMembers.add(member);

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("getPayDetails : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	/*
	 * 
	 * 搜索所有项目
	 * 
	 */
	@RequestMapping("/getallproject")
	public static void getAllProject(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		/*
		 * json数据格式：
		 * id 序号 
		 * projectname 工程名称
		 * projectsn   项目编号
		 * signcompany 签订单位（默认晓山）
		 * signdate    签订日期
		 * contractvendor  发包方
		 * contractamouont 合同金额
		 * finalamount 最终审定金额
		 * billreceiptamount 已开票
		 * receiptamount 已收款
		 * planreceipt 预计可收款
		 * finishdate 竣工日期
		 */
		
		//1. 查询所有项目编号添加到projectsnlist
		List<String> projectsnlist = new LinkedList<String>();
		String hql0 = "from ProjectStart ps";
		List<ProjectStart> list0 = new ProjectStartDAO().list(hql0);
		if(list0 != null && list0.size()>0){
			for(int t=0;t<list0.size();t++){
				projectsnlist.add(list0.get(t).getProjectsn());
			}
		}
		
		//2. 根据sn列表查询相关记录，一个sn一条记录（如有多条建设合同，则合并）
		
		if(projectsnlist != null && projectsnlist.size()>0){
			for(int j=0;j<projectsnlist.size();j++){
				
				//搜索所有建设合同
				String hql = "from ProjectContract pc where pc.contracttype like '建设合同' and pc.projectsn like '" + projectsnlist.get(j) + "' order by pc.datetime";
				List<ProjectContract> list1 = new ProjectContractDAO().lists(hql);
				float totalamount = 0;//合同金额
				if(list1 != null && list1.size()>0){	
					//1-7
					member.put("id", String.valueOf(j+1));//1
					DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");				
					member.put("projectname", list1.get(0).getContractname());//2
					member.put("projectsn", projectsnlist.get(j));//3
					member.put("signcompany", "晓山");//4
					if(list1.get(0).getSigndate() != null){
						member.put("signdate", sdf.format(list1.get(0).getSigndate()));//5
					}else{
						member.put("signdate", "");//5
					}				
					member.put("contractvendor", list1.get(0).getContractvendor());//6
					
										
					for(int i=0;i<list1.size();i++){
						totalamount += list1.get(i).getContractamount();
					}
					member.put("contractamouont", String.valueOf(totalamount));//7									
				}else{
					//1-7
					member.put("id", String.valueOf(j+1));//1			
					member.put("projectname", "");//2
					member.put("projectsn", projectsnlist.get(j));//3
					member.put("signcompany", "晓山");//4
					member.put("signdate", "");//5		
					member.put("contractvendor", "");//6
					member.put("contractamouont", "0");//7	
				}
				
				//8-最终审定金额
				ProjectFinish pf = new ProjectFinishDAO().findByProjectSN(projectsnlist.get(j));
				if(pf != null){
					member.put("finalamount", pf.getAmount());//8
				}else{
					member.put("finalamount", "");//8
				}
				
				//9 已开票 10 已收款
				String hql2 = "from ProjectReceipt pr where pr.projectsn like '" + projectsnlist.get(j) + "'";
				List<ProjectReceipt> list2 = new ProjectReceiptDAO().list(hql2); 
				float billreceiptamount=0,receiptamount=0;
				if(list2 != null && list2.size()>0){
					
					for(int k=0;k<list2.size();k++){
						billreceiptamount += list2.get(k).getReceiptbillamount();
						receiptamount += list2.get(k).getReceiptamount();
					}
					member.put("billreceiptamount", String.valueOf(billreceiptamount));//9
					member.put("receiptamount", String.valueOf(receiptamount));//10
				}else{
					member.put("billreceiptamount", "");//9
					member.put("receiptamount", "");//10
				}
				
				// 11 预计可收款		 12.竣工日期	
				ProjectFinish pfinish = new ProjectFinishDAO().findByProjectSN(projectsnlist.get(j));
				if(pfinish != null){
					if(pfinish.getAmount() != 0){
						BigDecimal b1 = new BigDecimal(Float.toString(pfinish.getAmount()));
						BigDecimal b2 = new BigDecimal(Float.toString(receiptamount));
						member.put("planreceipt",String.valueOf(b1.subtract(b2).floatValue()));//11
					}else{
						BigDecimal b1 = new BigDecimal(Float.toString(totalamount));
						BigDecimal b2 = new BigDecimal(Float.toString(receiptamount));
						member.put("planreceipt",String.valueOf(b1.subtract(b2).floatValue()));//11
					}
					member.put("finishdate", pfinish.getFinishdate());//12
				}else{
					BigDecimal b1 = new BigDecimal(Float.toString(totalamount));
					BigDecimal b2 = new BigDecimal(Float.toString(receiptamount));
					member.put("planreceipt",String.valueOf(b1.subtract(b2).floatValue()));//11
					member.put("finishdate", "");//12
				}
				
				
				// 13. 市场经理
				ProjectStart ps = new ProjectStartDAO().findByProjectSN(projectsnlist.get(j));
				if(ps != null){
					member.put("manager", ps.getProjectmanager());//13
				}else{
					member.put("manager","");//13
				}
					
				//14. 情况说明     15.回款时间
				ProjectProgress pp = new ProjectProgressDAO().findByProjectSN(projectsnlist.get(j));
				if(pp != null){
					member.put("overview",pp.getProjectoverview());//14
					member.put("plandate", pp.getProjectplanreceipt());//15
				}else{
					member.put("overview","");//14
					member.put("plandate", "");//15
				}
				
				jsonMembers.add(member);
			}
		}

		
		
		
		

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("getPayDetails : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 
	 * 搜索所有项目
	 * 
	 */
	@RequestMapping("/getalldepartments")
	public static void getAllDepartments(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		/*
		 * json数据格式：
		 * 1. id 序号 
		 * 2. projectsn 项目编号
		 * 3. projectvendor 建设单位   
		 * 4. projectname  工程名称
		 * 5. signvendor    签订合同单位 --默认晓山
		 * 6. plancost  预算成本
		 * 7. projectmanager 项目责任人
		 * 8. contractamount 合同金额
		 * 9. finalamount 审定金额
		 * 10. projectreceipt 工程回款
		 * 11. percent 回款比例(%)
		 * 12. creditamount 资金信用额
		 * 13. bidservicecharge 投标服务费
		 * 14. bidappearancefee 投标出场费
		 * 15. administrationfee 管理费用
		 * 16. purchasingfee 采购款(发票)
		 * 17. payedpurchasingfee 已付采购款
		 * 18. servicecharge 劳务费(发票)
		 * 19. payedservicecharge 已付劳务费
		 * 20. salary  工资
		 * 21. financefee 财务费用
		 * 22. totalpayed 支出合计
		 * 
		 */
		
		float totalcontractamount=0,totalfinalamount=0,totalprojectreceipt=0,totalcreditamount=0,totalbidservicecharge=0,totalbidapperaancefee=0,
			  totaladministrationfee=0,totalpurchasingfee=0,totalpayedpurchasingfee=0,totalservicecharge=0,totalpayedservicecharge=0,totalsalary=0,
			  totalfinancefee=0,totaltotalpayed=0;
		
		//1. 查询所有项目编号添加到projectsnlist
		List<String> projectsnlist = new LinkedList<String>();
		String hql0 = "from ProjectStart ps";
		List<ProjectStart> list0 = new ProjectStartDAO().list(hql0);
		if(list0 != null && list0.size()>0){
			for(int t=0;t<list0.size();t++){
				projectsnlist.add(list0.get(t).getProjectsn());
			}
		}
		
		
		//2. 根据sn列表查询相关记录，一个sn一条记录（如有多条建设合同，则合并）		
		if(projectsnlist != null && projectsnlist.size()>0){
			for(int i=0;i<projectsnlist.size();i++){
				String projectsn = projectsnlist.get(i);
				//1-2
				member.put("id", i+1);//1 
				member.put("projectsn", projectsn);//2
				
				//3-4
				ProjectContract pc = new ProjectContractDAO().findByProjectSNAndType(projectsn,"建设合同");
				if(pc != null){
					member.put("projectvendor", pc.getContractvendor());//3
					member.put("projectname", pc.getContractname());//4
				}else{
					member.put("projectvendor", "");//3
					member.put("projectname", "");//4
				}
				
				//5
				member.put("signvendor", "晓山");//5
				
				//6
				ProjectProgress pp = new ProjectProgressDAO().findByProjectSN(projectsn);
				if(pp != null){
					member.put("plancost", pp.getProjectplancost());//6
				}else{
					member.put("plancost", "");//6
				}
				
				//7
				ProjectStart ps = new ProjectStartDAO().findByProjectSN(projectsn);
				if(ps != null){
					member.put("projectmanager", ps.getProjectmanager());//7
				}else{
					member.put("projectmanager", "");//7
				}
				
				//8 合同金额				
				String hql1 = "from ProjectContract pc where pc.contracttype like '建设合同' and pc.projectsn like '" + projectsn + "'";
				List<ProjectContract> list1 = new ProjectContractDAO().lists(hql1);
				float totalamount = 0;//合同金额
				if(list1 != null && list1.size()>0){
					for(int j=0;j<list1.size();j++){
						totalamount += list1.get(j).getContractamount();
					}
					member.put("contractamount",String.valueOf(totalamount) );//8
					totalcontractamount += totalamount;
				}else{
					member.put("contractamount",String.valueOf(0) );//8
				}
				
				//10 工程回款
				List<ProjectReceipt> list2 = new ProjectReceiptDAO().findByProjectSN(projectsn);
				float totalreceipt = 0;
				if(list2 != null && list2.size()>0){
					
					for(int j=0;j<list2.size();j++){
						totalreceipt += list2.get(j).getReceiptamount();
					}
					member.put("projectreceipt", String.valueOf(totalreceipt));//10
					totalprojectreceipt += totalreceipt;
				}else{
					member.put("projectreceipt", String.valueOf(0));//10
				}
				
				//9 审定金额    11回款百分比
				ProjectFinish pf = new ProjectFinishDAO().findByProjectSN(projectsn);
				if(pf != null){
					float temp = 0;
					temp = pf.getAmount();
					member.put("finalamount", String.valueOf(temp));//9
					totalfinalamount += temp;
					if(temp == 0){
						member.put("percent", String.valueOf(0));//11
					}else{
					    member.put("percent", String.valueOf(100*(totalreceipt/temp)));//11
					}
				}else{
					member.put("finalamount", String.valueOf(0));//9
					if(totalamount == 0){
						member.put("percent", String.valueOf(0));//11
					}else{
						member.put("percent", String.valueOf(100*(totalreceipt/totalamount)));//11
					}
					
				}
				
                //12 资金信用额     20工资          21 财务费用
				ProjectFinance pff = new ProjectFinanceDAO().findByProjectSN(projectsn);
				float value20=0,value21=0;
				if(pff != null){
					float temp = 0;
					temp = Float.valueOf(pff.getFinancecredit());
					value20 = Float.valueOf(pff.getSalary());
					value21 = Float.valueOf(pff.getFinancecost());
					totalcreditamount += temp;
					totalsalary += value20;
					totalfinancefee += value21;
					member.put("creditamount", String.valueOf(pff.getFinancecredit()));//12
					member.put("salary", String.valueOf(value20));//20
					member.put("financefee", String.valueOf(value21));//21
				}else{
					member.put("creditamount", String.valueOf(0));//12
					member.put("salary", String.valueOf(0));//20
					member.put("financefee", String.valueOf(0));//21
				}
				
				//13- 14
				float value13 =0,value14=0;
				member.put("bidservicecharge", String.valueOf(0));//13
				totalbidservicecharge += value13;
				member.put("bidappearancefee", String.valueOf(0));//14
				totalbidapperaancefee += value14;
				
				//15 管理费用
				List<ProjectFee> list3 = new ProjectFeeDAO().findByProjectSN(projectsn);
				float value15 =0;
				if(list3 != null && list3.size()>0){
					for(int j=0 ;j<list3.size();j++){
						value15 += list3.get(j).getPayamount();
					}
					totaladministrationfee += value15;
					member.put("administrationfee", String.valueOf(value15));//15
				}else{
					member.put("administrationfee", String.valueOf(0));//15
				}
				
				//16采购款   17已付采购款    
				String hql2 = "from ProjectPay pp where pp.projectsn like '" + projectsn + "' and ( pp.contracttype like '采购合同' or pp.contracttype like '其它辅材' )";
				List<ProjectPay> list4 = new ProjectPayDAO().list(hql2);
				float value17=0;
				if(list4 != null && list4.size()>0){
					totalamount = 0;
					for(int j=0;j<list4.size();j++){
						totalamount += list4.get(j).getPaybillamount();
						value17 += list4.get(j).getPayamount();
					}
					totalpurchasingfee += totalamount;
					totalpayedpurchasingfee += value17;
					member.put("purchasingfee", String.valueOf(totalamount));//16
					member.put("payedpurchasingfee", String.valueOf(value17));//17
				}else{
					member.put("purchasingfee", String.valueOf(0));//16
					member.put("payedpurchasingfee", String.valueOf(0));//17
				}
				
				//18 劳务费  19已付劳务费
				String hql3 = "from ProjectPay pp where pp.projectsn like '" + projectsn + "' and pp.contracttype like '分包合同'";
				List<ProjectPay> list5 = new ProjectPayDAO().list(hql3);
				float value19 = 0;
				if(list5 != null && list5.size()>0){
					totalamount = 0;
					for(int j=0;j<list5.size();j++){
						totalamount += list5.get(j).getPaybillamount();
						value19 += list5.get(j).getPayamount();
					}
					totalservicecharge += totalamount;
					totalpayedservicecharge += value19;
					member.put("servicecharge", String.valueOf(totalamount));//18
					member.put("payedservicecharge", String.valueOf(value19));//19
				}else{
					member.put("servicecharge", String.valueOf(0));//18
					member.put("payedservicecharge", String.valueOf(0));//19
				}
				
				//22
				float value22 = value21 + value20 + value19 + value17 + value15 + value14 + value13;
				totaltotalpayed += value22;
				member.put("totalpayed", String.valueOf(value22));//22
				
				jsonMembers.add(member);
				
			}
		}
		
		member.put("id", "合计");//1 
		member.put("projectsn", "");//2
		member.put("projectvendor", "");//3
		member.put("projectname", "");//4
		member.put("signvendor", "");//5
		member.put("plancost", "");//6
		member.put("projectmanager", "");//7
		member.put("contractamount",String.valueOf(totalcontractamount) );//8
		member.put("finalamount", String.valueOf(totalfinalamount));//9 
		member.put("projectreceipt", String.valueOf(totalprojectreceipt));//10
		member.put("percent", "");//11
		member.put("creditamount", String.valueOf(totalcreditamount));//12
		member.put("bidservicecharge", String.valueOf(totalbidservicecharge));//13
		member.put("bidappearancefee", String.valueOf(totalbidapperaancefee));//14
		member.put("administrationfee", String.valueOf(totaladministrationfee));//15
		member.put("purchasingfee", String.valueOf(totalpurchasingfee));//16
		member.put("payedpurchasingfee", String.valueOf(totalpayedpurchasingfee));//17
		member.put("servicecharge", String.valueOf(totalservicecharge));//18
		member.put("payedservicecharge", String.valueOf(totalpayedservicecharge));//19
		member.put("salary", String.valueOf(totalsalary));//20
		member.put("financefee", String.valueOf(totalfinancefee));//21
		member.put("totalpayed", String.valueOf(totaltotalpayed));//22

		
		jsonMembers.add(member);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("getPayDetails : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * 事业部创建以及项目划分事业部
	 * 
	 * 
	 */
	@RequestMapping("/departmentquery")
	public static void projectAndDepartment(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		List<Department> lists = new DepartmentDAO().list();
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("depname", lists.get(i).getDepartment());
				String id = String.valueOf(lists.get(i).getId());
				String hql = "From Project p where p.department like '" + id + "'";
				List<Project> sublist = new ProjectDAO().list(hql);
				String projs = "";
				if(sublist != null && sublist.size()>0){
					for(int j=0;j<sublist.size();j++){
						projs = projs.concat(sublist.get(j).getProjectsn() + "   ");
					}
					
				}
				member.put("projects", projs);
				jsonMembers.add(member);
			}
			
		}
		
		member.put("index", "");
		member.put("depname", "点击增加事业部");
		member.put("projects", "");
		jsonMembers.add(member);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("departmentquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * 新增事业部
	 * 
	 * 
	 */
	@RequestMapping("/departmentadd")
	public static void addDepartment(HttpServletRequest request,HttpServletResponse response){
		
		String name = request.getParameter("name");
		
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		Department d = new Department();
		d.setDepartment(name);
		new DepartmentDAO().create(d);
		
		List<Department> lists = new DepartmentDAO().list();
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("depname", lists.get(i).getDepartment());
				String id = String.valueOf(lists.get(i).getId());
				String hql = "From Project p where p.department like '" + id + "'";
				List<Project> sublist = new ProjectDAO().list(hql);
				String projs = "";
				if(sublist != null && sublist.size()>0){
					for(int j=0;j<sublist.size();j++){
						projs = projs.concat(sublist.get(j).getProjectsn() + " ");
					}
					
				}
				member.put("projects", projs);
				jsonMembers.add(member);
			}
			
		}
		
		member.put("index", "");
		member.put("depname", "点击增加事业部");
		member.put("projects", "");
		jsonMembers.add(member);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("departmentquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 更新事业部
	 * 
	 * 
	 */
	@RequestMapping("/departmentmodify")
	public static void modifyDepartment(HttpServletRequest request,HttpServletResponse response){
		
		String name = request.getParameter("name");
		String oldname = request.getParameter("oldname");
		
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		DepartmentDAO dao = new DepartmentDAO();
		
		Department d = dao.findDepartment(oldname);
		if(d != null){
		    d.setDepartment(name);
		    dao.update(d);
		}
		
		List<Department> lists = new DepartmentDAO().list();
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("depname", lists.get(i).getDepartment());
				String id = String.valueOf(lists.get(i).getId());
				String hql = "From Project p where p.department like '" + id + "'";
				List<Project> sublist = new ProjectDAO().list(hql);
				String projs = "";
				if(sublist != null && sublist.size()>0){
					for(int j=0;j<sublist.size();j++){
						projs = projs.concat(sublist.get(j).getProjectsn() + " ");
					}
					
				}
				member.put("projects", projs);
				jsonMembers.add(member);
			}
			
		}
		
		member.put("index", "");
		member.put("depname", "点击增加事业部");
		member.put("projects", "");
		jsonMembers.add(member);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("departmentquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	/*
	 * 删除事业部
	 * 
	 * 
	 */
	@RequestMapping("/departmentdel")
	public static void delDepartment(HttpServletRequest request,HttpServletResponse response){
		
		String name = request.getParameter("name");
		
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		DepartmentDAO dao = new DepartmentDAO();
		
		Department d = dao.findDepartment(name);
		if(d != null){
		    dao.delete(d);
		    ProjectDAO pdao = new ProjectDAO();
		    //将跟此事业部关联的项目的事业部属性清空
		    {
		        String id = String.valueOf(d.getId());
		        String hql = "From Project p where p.department like '" + id + "'";
		        List<Project> plist = pdao.list(hql);
		        if(plist != null && plist.size()>0){
		        	for(int j=0;j<plist.size();j++){
		        		Project p = plist.get(j);
		        		p.setDepartment("");
		        		pdao.update(p);
		        	}
		        }
		    }
		}
		
		
		
		
		List<Department> lists = new DepartmentDAO().list();
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("depname", lists.get(i).getDepartment());
				String id = String.valueOf(lists.get(i).getId());
				String hql = "From Project p where p.department like '" + id + "'";
				List<Project> sublist = new ProjectDAO().list(hql);
				String projs = "";
				if(sublist != null && sublist.size()>0){
					for(int j=0;j<sublist.size();j++){
						projs = projs.concat(sublist.get(j).getProjectsn() + " ");
					}
					
				}
				member.put("projects", projs);
				jsonMembers.add(member);
			}
			
		}
		
		member.put("index", "");
		member.put("depname", "点击增加事业部");
		member.put("projects", "");
		jsonMembers.add(member);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("departmentquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * 查询所有未分配事业部项目列表
	 * 
	 */
	@RequestMapping("/projectsquery")
	public static void queryProjects(HttpServletRequest request,HttpServletResponse response){
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		List<Project> lists = new ProjectDAO().listNotDeployProjects();
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("projectsn", lists.get(i).getProjectsn());
				member.put("projectname", lists.get(i).getProjectname());
				member.put("assign", "点击分配事业部");
				jsonMembers.add(member);
			}
			
		}
		

		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("projectsquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 设置项目归属事业部
	 * 
	 */
	@RequestMapping("/departmentaddproj")
	public static void setDepartmentAndProj(HttpServletRequest request,HttpServletResponse response){
		String department = request.getParameter("dep");
		String projectsn = request.getParameter("proj");
		
		JSONObject json=new JSONObject();
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		Department d = new DepartmentDAO().findDepartment(department);
		if(d != null){
			new ProjectDAO().updateProjectBySn(projectsn, String.valueOf(d.getId()));
			member.put("msg", "项目分配事业部完成");
		}else{
			member.put("msg", "项目分配事业部失败，请联系管理员");
		}

		
		jsonMembers.add(member);
		json.put("setproj", jsonMembers);
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = json.toString();
	    System.out.println("departmentaddproj : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	

	/**
	 * 
	 * 查询本人所有录入的立项记录
	 * 
	 */
	@RequestMapping("/projectstartquery")
	public static void projectStartQuery(HttpServletRequest request,HttpServletResponse response){
		String username = (String)request.getSession().getAttribute("loginName");
		Boolean adminPermission = (Boolean)request.getSession().getAttribute("adminPermission");
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		String hql = "";
		if(adminPermission == true){
			hql ="from ProjectStart ps";
		}else{
			hql ="from ProjectStart ps where ps.operator = '" + username + "'";
		}
		
		List<ProjectStart> lists = new ProjectStartDAO().list(hql);
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("projectsn", lists.get(i).getProjectsn());
				member.put("projectname",lists.get(i).getProjectname());
				member.put("projectvendor",lists.get(i).getProjectvendor());
				member.put("projectmanager",lists.get(i).getProjectmanager());
				member.put("operator", lists.get(i).getOperator());
				member.put("time", lists.get(i).getDatetime());
				Boolean value = lists.get(i).getRecordstatus();
				if(value == null || value == false){
					member.put("recordstatus","待审批，点击修改");
				}else{
					if(adminPermission == true){
						member.put("recordstatus","已审批，点击修改");
					}else{
						member.put("recordstatus","已审批");
					}
					
				}
				jsonMembers.add(member);
			}
			
		}
		

		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("projectsquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * 
	 * 管理员查询所有
	 * 未审批的记录
	 * 
	 */
	@RequestMapping("/projectstartadminquery")
	public static void projectStartAdminQuery(HttpServletRequest request,HttpServletResponse response){
		//String username = (String)request.getSession().getAttribute("loginName");
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		String hql ="from ProjectStart p where p.recordstatus = null or p.recordstatus = false";
		List<ProjectStart> lists = new ProjectStartDAO().list(hql);
		if(lists != null && lists.size() > 0){
			for(int i=0;i<lists.size();i++){
				member.put("index", i+1);
				member.put("projectsn", lists.get(i).getProjectsn());
				member.put("projectname",lists.get(i).getProjectname());
				member.put("projectvendor",lists.get(i).getProjectvendor());
				member.put("projectmanager",lists.get(i).getProjectmanager());
				member.put("operator", lists.get(i).getOperator());
				member.put("time", lists.get(i).getDatetime());
				Boolean value = lists.get(i).getRecordstatus();
				if(value == null || value == false){
					member.put("recordstatus","待审批，点击审批");
				}else{
					member.put("recordstatus","已审批");
				}
				
				jsonMembers.add(member);
			}
			
		}
		

		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("projectsquery : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
	

	/**
	 * 
	 * 管理员审批立项记录
	 * 
	 * 
	 */
	@RequestMapping("/projectstartadminfun")
	public static void projectStartAdminFun(HttpServletRequest request,HttpServletResponse response){
		String projectsn = request.getParameter("projectsn");
		JSONArray jsonMembers = new JSONArray();
		JSONObject member = new JSONObject();
		
		ProjectStart ps = new ProjectStartDAO().findByProjectSN(projectsn);
		if(ps != null){
			ps.setRecordstatus(true);
			new ProjectStartDAO().update(ps);
		}
		
		member.put("msg","已审批");
		jsonMembers.add(member);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		
	    String result = jsonMembers.toString();
	    System.out.println("projectstartadminfun : " + result);   
	    try {
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}		
	
}
