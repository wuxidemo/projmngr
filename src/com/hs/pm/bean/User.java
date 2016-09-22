package com.hs.pm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique=true,nullable=false)
	private String useraccount;//�ʺ�
	
	@Column(unique=true,nullable=false)
	private String username;//����
	
	private String password;//����
	private String authority;//¼��Ȩ��Ȩ��
	private String searchauthority;//¼��Ȩ��Ȩ��
	private String salt;//����
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUseraccount() {
		return useraccount;
	}
	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getSearchauthority() {
		return searchauthority;
	}
	public void setSearchauthority(String searchauthority) {
		this.searchauthority = searchauthority;
	}
	
	
	
	

}
