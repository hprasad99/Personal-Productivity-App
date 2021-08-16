package com.cap.ppa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "Login", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"username", "password"})
	}) 
public class Login implements Serializable{
private static final long serialVersionUID = -2343243243242432341L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private long id;
	
	@ApiModelProperty(notes="Username")
	@Column(name="username")
	private String username;
	
	@ApiModelProperty(notes="Full Name")
	@Column(name="Name")
	private String name;
	
	@ApiModelProperty(notes="Place")
	@Column(name="Place")
	private String place;
	
	@ApiModelProperty(notes="Mobile number")
	@Column(name="mobile")
	private String mobile;
	
	@ApiModelProperty(notes="ID Proof")
	@Column(name="idproof")
	private String IdProof;
	
	@Column(name="password")
	private String password;

	
	public String getIdProof() {
		return IdProof;
	}

	public void setIdProof(String idProof) {
		IdProof = idProof;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Login(long id, String username, String name, String place, String mobile, String idProof, String password) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.place = place;
		this.mobile = mobile;
		IdProof = idProof;
		this.password = password;
	}
	
	

	public Login(long id, String username, String name, String place, String mobile, String idProof) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.place = place;
		this.mobile = mobile;
		IdProof = idProof;
	}

	public Login() {
		super();
		// TODO Auto-generated constructor stub
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
			this.password=password;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", username=" + username + ", name=" + name + ", place=" + place + ", mobile="
				+ mobile + ", IdProof=" + IdProof + ", password=" + password + "]";
	}
}
