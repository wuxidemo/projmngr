package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * ��ҵ������¼��
 * @author lenvon
 *
 */



@Entity
@Table(name="tb_projectfee")
public class ProjectFee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String projectsn;
	
	private String feecontent;//ժҪ
	private float receiptamount;//��Ʊ���
	private float payamount;//������
	private String operator;//¼����
	private String datetime;//
	
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
	public String getFeecontent() {
		return feecontent;
	}
	public void setFeecontent(String feecontent) {
		this.feecontent = feecontent;
	}
	public float getReceiptamount() {
		return receiptamount;
	}
	public void setReceiptamount(float receiptamount) {
		this.receiptamount = receiptamount;
	}
	public float getPayamount() {
		return payamount;
	}
	public void setPayamount(float payamount) {
		this.payamount = payamount;
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
