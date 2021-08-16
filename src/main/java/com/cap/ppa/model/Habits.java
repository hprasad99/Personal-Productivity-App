package com.cap.ppa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "habits") 
public class Habits implements Serializable{
	
	private static final long serialVersionUID = -2343243243242432341L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private long id;
	
	@Column(name="skill")
	private String skill;
	
	@Column(name="currentDateXYZ")
	private String currentDateXYZ;
	
	@Column(name="duration")
	private String duration;

	@Column(name="points_scored")
	private int points =0;
	
	public Habits(String skill, String currentDateXYZ, String duration,int points) {
		this.skill = skill;
		this.currentDateXYZ = currentDateXYZ;
		this.duration = duration;
		this.points = points;
		//calculaterewards();
	}

//	private void calculaterewards() {
//		// TODO Auto-generated method stub
//		
//	}

	public Habits() {
		points = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getCurrentDate() {
		return currentDateXYZ;
	}

	public void setCurrentDate(String currentDateXYZ) {
		this.currentDateXYZ = currentDateXYZ;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Habits [id=" + id + ", skill=" + skill + ", currentDate=" + currentDateXYZ + ", duration=" + duration
				+ ", points=" + points + "]";
	}
}
