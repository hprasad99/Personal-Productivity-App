package com.cap.ppa.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "dailies") 
public class Dailies implements Serializable {

	private static final long serialVersionUID = 2072503026471411099L;
	
	@Id
	@GeneratedValue
	private int id;
 
	//Task name
	@ApiModelProperty(notes = "Task Name")
	@Column(name = "title")
	private String title;
 
	//Starting date of the task
	@ApiModelProperty(notes = "dd-MM-yyyy")
	@Column(name = "start_date")
	private String startDate;

	//Ending date of the task
	@ApiModelProperty(notes = "dd-MM-yyyy")
	@Column(name = "end_date")
	private String endDate;

	//Atleast time for the task everyday
	@ApiModelProperty(notes = "30 mins / 2 hrs")
	@Column(name = "time_period")
	private String timePeriod;
	
	//Completed time of the task today
	@ApiModelProperty(notes = "20 mins / 1 hr")
	@Column(name = "completed_time")
	private String completedTime;
	
	//Task is Important or not
	@ApiModelProperty(notes = "High Priority")
	@Column(name = "important", columnDefinition = "boolean default false")
	private boolean important=false;
	
	//Status of the task
	@ApiModelProperty(notes = "Status ")
	@Column(name="completed", columnDefinition = "boolean default false")
	private boolean completed=false;
 
	public Dailies() {
	}

	public Dailies(String title, String startDate, String endDate, String timePeriod, String completedTime,
			boolean important, boolean completed) {
		
		this.title = title;
		this.timePeriod = timePeriod;
		this.completedTime = completedTime;
		this.important = important;
		this.startDate=startDate;
		this.endDate=endDate;
		this.completed=completed;

		calculatePendingTime();
	}

	//Updating the Status of the task
	public void calculatePendingTime() {
		
		String[] totalTime= getTimePeriod().split(" ");
		String[] compTime= getCompletedTime().split(" "); 
		int total=Integer.parseInt(totalTime[0]);
		int comp=Integer.parseInt(compTime[0]);
		
		if(totalTime[1].toLowerCase().equals("hr") || totalTime[1].toLowerCase().equals("hrs")
				|| totalTime[1].toLowerCase().equals("hour") || totalTime[1].toLowerCase().equals("hours"))
			total=total*60;
		
		if(compTime[1].toLowerCase().equals("hr") || compTime[1].toLowerCase().equals("hrs")
				|| compTime[1].toLowerCase().equals("hour") || compTime[1].toLowerCase().equals("hours"))
			comp=comp*60;
		
		if(total > comp)
		{	
			setCompleted(false);
		}
		else setCompleted(true); 
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id)
	{
		this.id=id;
	}

	public String getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(String completedTime) {
		this.completedTime = completedTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {

		return startDate;
	}

	public String getEndDate() {

		return endDate;
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Dailies [ id= " + id + ", title= " + title + ", startDate= " + startDate + ", endDate= " + endDate
				+ ", timePeriod= " + timePeriod + ", completedTime= " + completedTime + ", important= " + important
				+ ", completed= " + completed + " ]";
	}

}
