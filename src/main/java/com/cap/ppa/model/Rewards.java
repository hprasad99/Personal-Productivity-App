package com.cap.ppa.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cap.ppa.repository.HabitRepository;
import com.cap.ppa.serviceimpl.HabitServiceImpl;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "rewards") 
public class Rewards implements Serializable{
private static final long serialVersionUID = -2343243243242432341L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	
	@ApiModelProperty(notes="Reward name")
	@Column(name="title")
	private String title;
	
	@ApiModelProperty(notes="Points required for the rewards")
	@Column(name="requiredPoints")
	private int requiredPoints;
	private int points;

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Rewards(int id, int requiredPoints, String title) {
		super();
		this.id=id;
		this.requiredPoints = requiredPoints;
		this.title = title;
	}

	public Rewards() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	
	@ApiModelProperty(notes="Gets the point from Habits")
	public int getPoints(int points) {
		int p=0;
//		HabitService obj= new HabitService();
//		List<Habits> list= obj.findAllHabitTask();
//		if(list.size()==0) p=201-i;
//		else {
//			if(list.get(0).getPoints()<201) 
				p=600-points;
//			else {
//				p=list.get(0).getPoints()-i;
//				list.get(0).setPoints(p);
//			}
//		}
		return p;
	}
	
	@Override
	public String toString() {
		return "Rewards [id=" + id + ", title=" + title + ", requiredPoints=" + requiredPoints + ", Points="+getPoints(0)+"]";
	}

}
