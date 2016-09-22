package com.hs.pm.bean;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_projectcontract")
public class ProjectContract {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String projectsn;//��Ŀ���
	
	private Timestamp receivedate;//�յ���ͬ����
	private Timestamp signdate;//��ͬǩ������
	

	
	private String contracttype;//��ͬ����  �����ͬ 
	private String contractname;//��Ŀ����/��ͬ����
	private String contractvendor;//�ɹ���λ/��Ӧ��
	private float contractamount;//��ͬ���
	private String contractwho;//��ͬ������
	private String description;//��ͬ��ע
	private String datetime;//¼��ʱ��
	private String operator;//¼����
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/*public String getReceivedate() {
		if(receivedate != null){
		    return receivedate.toString();
		}else{
			return "";
		}
			
	}
	public void setReceivedate(String receivedate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			this.receivedate = (Date) sdf.parse(receivedate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getSigndate() {
		if(signdate != null){
		    return signdate.toString();
		}else{
			return "";
		}
	}
	public void setSigndate(String signdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			this.signdate = (Date) sdf.parse(signdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}*/
	public String getProjectsn() {
		return projectsn;
	}
	public void setProjectsn(String projectsn) {
		this.projectsn = projectsn;
	}
	public String getContracttype() {
		return contracttype;
	}
	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}
	public String getContractname() {
		return contractname;
	}
	public void setContractname(String contractname) {
		this.contractname = contractname;
	}
	public String getContractvendor() {
		return contractvendor;
	}
	public void setContractvendor(String contractvendor) {
		this.contractvendor = contractvendor;
	}
	public float getContractamount() {
		return contractamount;
	}
	public void setContractamount(float contractamount) {
		this.contractamount = contractamount;
	}
	public String getContractwho() {
		return contractwho;
	}
	public void setContractwho(String contractwho) {
		this.contractwho = contractwho;
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
	public Timestamp getReceivedate() {
		return receivedate;
	}
	public void setReceivedate(Timestamp receivedate) {
		this.receivedate = receivedate;
	}
	public Timestamp getSigndate() {
		return signdate;
	}
	public void setSigndate(Timestamp signdate) {
		this.signdate = signdate;
	}
	
	
	
}
