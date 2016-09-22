package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * 财务中心数据
 * @author lenvon
 *
 */

@Entity
@Table(name="tb_projectfinance")
public class ProjectFinance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String projectsn;
	
	private String financecontent;//摘要
	private String salary;//工资
	private String financecost;//财务费用
	private String financecredit;//资金信用额
	private String operator;//录入人
	private String datetime;//录入时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectsn() {
		return projectsn;
	}
	public void setProjectsn(String projectsn) {
		this.projectsn = projectsn;
	}
	public String getFinancecontent() {
		return financecontent;
	}
	public void setFinancecontent(String financecontent) {
		this.financecontent = financecontent;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getFinancecost() {
		return financecost;
	}
	public void setFinancecost(String financecost) {
		this.financecost = financecost;
	}
	public String getFinancecredit() {
		return financecredit;
	}
	public void setFinancecredit(String financecredit) {
		this.financecredit = financecredit;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	
	
}
