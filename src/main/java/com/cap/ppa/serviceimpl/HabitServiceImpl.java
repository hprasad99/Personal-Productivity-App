package com.cap.ppa.serviceimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.ppa.exception.Exception;
import com.cap.ppa.exception.Exception;
import com.cap.ppa.model.Habits;
import com.cap.ppa.repository.HabitRepository;
import com.cap.ppa.service.HabitService;

@Service
public class HabitServiceImpl implements HabitService{
	@Autowired
	private HabitRepository habitRepository;
	
	public void saveHabitTask(Habits habits,int i) {
		if(habits.getSkill()==""||habits.getCurrentDate()==""||habits.getDuration()=="") {
			throw new Exception("Field cannot be empty");
		}else {
			habitRepository.save(new Habits(habits.getSkill(),habits.getCurrentDate(),habits.getDuration(),i));
		}
	}
	
	public void deleteHabitTask(long id) {
		String task = "";
		task = habitRepository.findById(id).toString();
		boolean isPresent = task.contains("id");
		if(!isPresent) {
			throw new Exception("Task not found");
		}else {
			habitRepository.deleteById(id);
		}
	}
	
	public void deleteAllHabitTask() {
		habitRepository.deleteAll();
	}
	
	public List<Habits> findAllHabitTask(long id,String title){
		List<Habits> habits =  habitRepository.findAll();
		return habits;
	}
	
	public void updateHabitTaskBySkill(long id,String title) {
		Habits habit = habitRepository.findById(id);
		boolean isPresent = habit.toString().contains("id");
		if(!isPresent) {
			throw new Exception("Task not found");
		}else {
			habit.setSkill(title);
			habitRepository.save(habit);
		}
	}
	
	public String updateHabitTaskByTime(long id, String duration,String date) {
		Habits habits = habitRepository.findById(id);
		boolean isPresent = habits.toString().contains("id");
		if(!isPresent) {
			throw new Exception("Task not found");
		}else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date today = Calendar.getInstance().getTime();
			String dateToString = df.format(today);
			int i=0;
			try {
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
				Date userDate;
				userDate = formatter1.parse(date);
				
				DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
				Date currentDate = formatter2.parse(dateToString);
				if(userDate.compareTo(currentDate)==0) {
					i = 0;
				}else 
					i=1;
				}catch(ParseException e) {
					e.printStackTrace();
				}
				String s="";
				if(i==1) {
					s = "Duration Updated New Day";
					habitRepository.save(new Habits(habits.getSkill(),date,duration,(habits.getPoints()+1)));
				}else {
					habits.setDuration(duration);
					habitRepository.save(habits);
					s="Duration Updated";
				}
			return s;
		}
	}

	public List<Habits> findAllHabitTask() {
		// TODO Auto-generated method stub
		List<Habits> habit = habitRepository.findAll();
		return habit;
	}

	public String searchHabitTask(long id) {
		// TODO Auto-generated method stub
		String task = "";
		task = habitRepository.findById(id).toString();
		boolean isPresent = task.contains("id");
		if(!isPresent)
			throw new Exception("Task not found");
		else
			return task;
	}

	public List<Habits> searchByHabitTaskTitle(String skill) {
		// TODO Auto-generated method stub
		List<Habits> habits = habitRepository.findBySkill(skill);
		if(habits.size()<1) 
			throw new Exception("Task not found");
		else {
			List<Habits> habList = new ArrayList<>();
			for(Habits h:habits) {
				habList.add(new Habits(h.getSkill(),h.getCurrentDate(),h.getDuration(),h.getPoints()));
			}
			return habList;
		}
	}


}
