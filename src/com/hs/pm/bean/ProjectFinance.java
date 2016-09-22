package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 
 * ������������
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
	
	private String financecontent;//ժҪ
	private String salary;//����
	private String financecost;//�������
	private String financecredit;//�ʽ����ö�
	private String operator;//¼����
	private String datetime;//¼��ʱ��
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
