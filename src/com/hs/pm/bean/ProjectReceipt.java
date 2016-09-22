package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tb_projectreceipt")
public class ProjectReceipt {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String projectsn;//��Ŀ���
	
	private String projectvendor;//�ɹ���λ
	private String receiptcontent;//�տ�ժҪ
	private float receiptbillamount;//�տƱ���
	private float receiptamount;//�տ�ؿ���
	private String receipttype;//�տʽ
	private String description;//�տע
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
	public String getProjectvendor() {
		return projectvendor;
	}
	public void setProjectvendor(String projectvendor) {
		this.projectvendor = projectvendor;
	}
	public String getReceiptcontent() {
		return receiptcontent;
	}
	public void setReceiptcontent(String receiptcontent) {
		this.receiptcontent = receiptcontent;
	}
	public float getReceiptbillamount() {
		return receiptbillamount;
	}
	public void setReceiptbillamount(float receiptbillamount) {
		this.receiptbillamount = receiptbillamount;
	}
	public float getReceiptamount() {
		return receiptamount;
	}
	public void setReceiptamount(float receiptamount) {
		this.receiptamount = receiptamount;
	}
	public String getReceipttype() {
		return receipttype;
	}
	public void setReceipttype(String receipttype) {
		this.receipttype = receipttype;
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
	
	
	
	
}
