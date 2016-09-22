package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tb_projectpay")
public class ProjectPay {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String projectsn;//项目编号
	
	private String payvendor;//付款供应商
	private String contracttype;//合同类型
	private String paycontent;//付款摘要
	private float paybillamount;//付款收票金额
	private float payamount;//付款金额
	private String paytype;//付款方式
	private String description;//付款备注
	private String datetime;//录入时间
	private String operator;//录入人
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
	public String getPayvendor() {
		return payvendor;
	}
	public void setPayvendor(String payvendor) {
		this.payvendor = payvendor;
	}
	public String getPaycontent() {
		return paycontent;
	}
	public void setPaycontent(String paycontent) {
		this.paycontent = paycontent;
	}
	public float getPaybillamount() {
		return paybillamount;
	}
	public void setPaybillamount(float paybillamount) {
		this.paybillamount = paybillamount;
	}
	public float getPayamount() {
		return payamount;
	}
	public void setPayamount(float payamount) {
		this.payamount = payamount;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getContracttype() {
		return contracttype;
	}
	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}
	
	
}
