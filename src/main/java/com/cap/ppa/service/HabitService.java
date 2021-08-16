package com.cap.ppa.service;

import java.util.List;

import com.cap.ppa.model.Habits;

public interface HabitService {
	public void saveHabitTask(Habits habits,int i);
	public void deleteHabitTask(long id);
	public void deleteAllHabitTask();
	public void updateHabitTaskBySkill(long id,String title);
	public String updateHabitTaskByTime(long id, String duration,String date);
	public String searchHabitTask(long id);
	public List<Habits> searchByHabitTaskTitle(String skill);
}
