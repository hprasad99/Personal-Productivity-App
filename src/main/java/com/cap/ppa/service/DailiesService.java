package com.cap.ppa.service;

import java.util.List;

import com.cap.ppa.model.Dailies;

public interface DailiesService {
	
	public void saveDailiesTask(Dailies dailies);
	public void deleteDailiesTask(int id);
	public void deleteAllDailiesTask();
	public List<Dailies> findAllDailiesTask();
	public List<Dailies> findAllTodayDailies();
	public void updateDailiesTaskByTitle(int id,String title);
	public void updateDailiesTaskByTime(int id,String completedTime);
	public void resetDailiesTaskByTime();
	public void updateDailiesTaskByImp(int id,boolean important);
	public Dailies searchDailiesTask(int id);
	public List<Dailies> searchByDailiesTaskTitle(String title);
	public List<Dailies> searchByDailiesTaskPriority(boolean important);
	public List<Dailies> searchByDailiesTaskCompleted(boolean completed);

}
