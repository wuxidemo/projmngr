package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_projectstart")
public class ProjectStart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true,nullable=false)
	private String projectsn;//项目编号
	
	private String projectname;//项目名称
	private String projectvendor;//采购单位
	private String projectmanager;//项目负责人
	private String datetime;//录入时间
	private String operator;//录入人姓名
	private Boolean recordstatus;//记录是否已审批
	
	
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
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectvendor() {
		return projectvendor;
	}
	public void setProjectvendor(String projectvendor) {
		this.projectvendor = projectvendor;
	}
	public String getProjectmanager() {
		return projectmanager;
	}
	public void setProjectmanager(String projectmanager) {
		this.projectmanager = projectmanager;
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
	public Boolean getRecordstatus() {
		return recordstatus;
	}
	public void setRecordstatus(Boolean recordstatus) {
		this.recordstatus = recordstatus;
	}
	
	

}
