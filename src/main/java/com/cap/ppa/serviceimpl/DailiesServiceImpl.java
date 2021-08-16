package com.cap.ppa.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.ppa.exception.Exception;
import com.cap.ppa.exception.Exception;
import com.cap.ppa.model.Dailies;
import com.cap.ppa.repository.DailiesRepository;
import com.cap.ppa.service.DailiesService;

@Service
public class DailiesServiceImpl implements DailiesService {

	@Autowired
	private DailiesRepository dailiesRepository;
	
	public void saveDailiesTask(Dailies dailies) {
		if(dailies.getTitle()== "" || dailies.getStartDate()== "" || dailies.getEndDate()== "" ||
			dailies.getTimePeriod()== "" || dailies.getCompletedTime()== "" )
			throw new Exception("Field cannot be empty");
		else 
			if(!(Pattern.matches("^[1-9][0-9]*\\s((min(ute){0,1}[s]{0,1})|(h(ou){0,1}r[s]{0,1}))$", dailies.getTimePeriod())
				&& Pattern.matches("^[0-9]+\\s((min(ute){0,1}[s]{0,1})|(h(ou){0,1}r[s]{0,1}))$", dailies.getCompletedTime())))
			throw new Exception("Invalid Time Entered (Correct: 20 mins, 2 hrs, 1 min)");
//		else 
//			if(!(Pattern.matches("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-2021", dailies.getStartDate())
//				&& Pattern.matches("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-2021", dailies.getEndDate())))
//			throw new Exception("Invalid Date Entered (Correct: 15-02-2021, 03-11-2021)");
		else
				dailiesRepository.save(new Dailies(dailies.getTitle(), dailies.getStartDate(), dailies.getEndDate(),
						dailies.getTimePeriod(), dailies.getCompletedTime(), dailies.isImportant(), dailies.isCompleted()));
	}
	
	public void deleteDailiesTask(int id) {
		String task = "";
		Dailies d = dailiesRepository.findById(id);
		if(d==null)
			throw new Exception("Task not found");
		else 
		{		task=d.toString();
		dailiesRepository.deleteById(id);
		}
	}
	
	public void deleteAllDailiesTask() {
		dailiesRepository.deleteAll();	
	}
	
	public List<Dailies> findAllDailiesTask(){
		List<Dailies> dailies=dailiesRepository.findAll();
		if(dailies.size()==0)
			throw new Exception("Task not found");
		else
		return dailies;
	}
	
	public List<Dailies> findAllTodayDailies(){
		List<Dailies> dailies = dailiesRepository.findAll();
		if(dailies.size()==0)
			throw new Exception("Task not found");
		else
		{
		List<Dailies> dailiesList = new ArrayList<>();
		
		for (Dailies c : dailies) {
			
			System.out.println("Run Run");
			
//			Date currentDate = Calendar.getInstance().getTime();
			Date currentDate= new Date();
				
				try {
					SimpleDateFormat formatter3=new SimpleDateFormat("yyyy-MM-dd");  
					String strDate= formatter3.format(currentDate);

//					System.out.println("Run Run"+currentDate);
					SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
					
					Date startDate = formatter1.parse(c.getStartDate());
//					System.out.println("Run Run"+startDate);
					SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");  
					Date endDate=formatter2.parse(c.getEndDate());
//					System.out.println("Run Run"+endDate);
					SimpleDateFormat formatter4=new SimpleDateFormat("yyyy-MM-dd");  
					Date nowDate = formatter4.parse(strDate);
//					System.out.println("Run Run"+nowDate);
					if(startDate.compareTo(nowDate) * nowDate.compareTo(endDate) >= 0)
						    dailiesList.add(c);
//					System.out.println("Run Run List"+dailiesList);	
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}

		return dailiesList;
		}
	}
	
	public void updateDailiesTaskByTitle(int id,String title) {
		Dailies dailies=dailiesRepository.findById(id);
		if(dailies==null)
			throw new Exception("Task not found");
		else 
		{
			dailies.setTitle(title);
			dailiesRepository.save(dailies);
		}
	}
	
	public void updateDailiesTaskByTime(int id,String completedTime) {
		Dailies dailies=dailiesRepository.findById(id);
		if(dailies==null)
			throw new Exception("Task not found");
		else if(!(Pattern.matches("^[0-9]+\\s((min(ute){0,1}[s]{0,1})|(h(ou){0,1}r[s]{0,1}))$", completedTime)))
			throw new Exception("Invalid Time Entered (Correct: 20 mins, 2 hrs, 1 min)");
		else 
		{
			dailies.setCompletedTime(completedTime);
			dailies.calculatePendingTime();
			dailiesRepository.save(dailies);
		}
	}
	
	public void resetDailiesTaskByTime() {
		List<Dailies> dailies = dailiesRepository.findAll();
		if(dailies.size()==0)
			throw new Exception("Task not found");
		else
		{
			for (Dailies c : dailies) {
				updateDailiesTaskByTime(c.getId(),"0 mins");
			}
		}
	}
	public void updateDailiesTaskByImp(int id,boolean important) {
		Dailies dailies=dailiesRepository.findById(id);
		if(dailies==null)
			throw new Exception("Task not found");
		else if(dailies.isImportant()!=false && dailies.isImportant()!=true)
			throw new Exception("Invalid Important Entered (Correct: true, false)");
		else
		{
			dailies.setImportant(important);
			dailiesRepository.save(dailies);
		}
	}
	
	public Dailies searchDailiesTask(int id){
		Dailies d = dailiesRepository.findById(id);
		if(d==null)
			throw new Exception("Task not found");
		else 
			return d;
	}
	
	public List<Dailies> searchByDailiesTaskTitle(String title){
		
		List<Dailies> dailies = dailiesRepository.findByTitle(title);
		if(dailies.size()==0)
			throw new Exception("Task not found");
		else
		{
			List<Dailies> dailiesList = new ArrayList<>();
		
			for (Dailies c : dailies) {
				dailiesList.add(new Dailies(c.getTitle(), c.getStartDate(), c.getEndDate(),
						c.getTimePeriod(), c.getCompletedTime(), c.isImportant(), c.isCompleted()));
			}

			return dailiesList;
		}
	}
	

	public List<Dailies> searchByDailiesTaskPriority(boolean important){
		
		List<Dailies> dailies = dailiesRepository.findByImportant(important);
		if(dailies.size()==0)
			throw new Exception("Task not found");
		else
		{
			List<Dailies> dailiesList = new ArrayList<>();
		
			for (Dailies c : dailies) {
				dailiesList.add(new Dailies(c.getTitle(), c.getStartDate(), c.getEndDate(),
					c.getTimePeriod(), c.getCompletedTime(), c.isImportant(),c.isCompleted()));
			}

			return dailiesList;
		}
	}

	public List<Dailies> searchByDailiesTaskCompleted(boolean completed){
	
		List<Dailies> dailies = dailiesRepository.findByCompleted(completed);
		if(dailies.size()==0)
			throw new Exception("Task not found");
		else
		{
			List<Dailies> dailiesList = new ArrayList<>();
		
			for (Dailies c : dailies) {
				dailiesList.add(new Dailies(c.getTitle(), c.getStartDate(), c.getEndDate(),
						c.getTimePeriod(), c.getCompletedTime(), c.isImportant(), c.isCompleted()));
			}

			return dailiesList;
		}
	}
}
