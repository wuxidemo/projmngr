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
	private String projectsn;//��Ŀ���
	
	private String payvendor;//���Ӧ��
	private String contracttype;//��ͬ����
	private String paycontent;//����ժҪ
	private float paybillamount;//������Ʊ���
	private float payamount;//������
	private String paytype;//���ʽ
	private String description;//���ע
	private String datetime;//¼��ʱ��
	private String operator;//¼����
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
