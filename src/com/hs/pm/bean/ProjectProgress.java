package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 项目进度情况
 * @author lenvon
 *
 */

@Entity
@Table(name="tb_projectprogress")
public class ProjectProgress {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable=false)
	private String projectsn;
	
	private float projectplancost;//预算成本
	private String projectprogress;//项目进度百分比
	private String projectoverview;//项目情况说明
	private String projectplanreceipt;//回款时间点
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
	public float getProjectplancost() {
		return projectplancost;
	}
	public void setProjectplancost(float projectplancost) {
		this.projectplancost = projectplancost;
	}
	public String getProjectprogress() {
		return projectprogress;
	}
	public void setProjectprogress(String projectprogress) {
		this.projectprogress = projectprogress;
	}
	public String getProjectoverview() {
		return projectoverview;
	}
	public void setProjectoverview(String projectoverview) {
		this.projectoverview = projectoverview;
	}
	public String getProjectplanreceipt() {
		return projectplanreceipt;
	}
	public void setProjectplanreceipt(String projectplanreceipt) {
		this.projectplanreceipt = projectplanreceipt;
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
