package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * ��Ŀ�������
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
	
	private float projectplancost;//Ԥ��ɱ�
	private String projectprogress;//��Ŀ���Ȱٷֱ�
	private String projectoverview;//��Ŀ���˵��
	private String projectplanreceipt;//�ؿ�ʱ���
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
