package com.cap.ppa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todotask")
public class TodoItem {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="dueDate")
	private String dueDate;
	
	@Column(name="Status")
	private String status;
	
	public TodoItem(Long id, String title,String description,String dueDate,String status) {
		super();
		this.id = id;
		this.title = title;
		this.status = status;
		this.dueDate=dueDate;
		this.description=description;
	}
	
	public TodoItem() {
		super();
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "TodoItem [id=" + id + ", title=" + title +", Description="+description+", Due Date= "+dueDate+", status=" +status+ "]";
	}
}
