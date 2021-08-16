package com.cap.ppa.serviceimpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cap.ppa.exception.Exception;
import com.cap.ppa.model.Rewards;
import com.cap.ppa.repository.RewardRepository;
import com.cap.ppa.service.RewardService;

@Service
public class RewardServiceImpl implements  RewardService {
	@Autowired
	RewardRepository repository;
	
	
	public String bulkcreate() {
		//repository.save();
		repository.saveAll(Arrays.asList(new Rewards(1,100,"Hammer"),new Rewards(2,200,"Shield"), 
				new Rewards(3,300,"Silver Scepter"),new Rewards(4,400,"Golden Scepter"),
				new Rewards(5,500,"Blade"),new Rewards(6,600,"Dark Soul Blade"),
				new Rewards(7,700,"Crystel Blade"),new Rewards(8,800,"Silver Wand"),
				new Rewards(9,900,"Dark wand"),new Rewards(10,1000,"Snowflake Wand"),
				new Rewards(11,1100,"Soul Stone"),new Rewards(12,1200,"Philospher Stone"),
				new Rewards(13,1300,"Invisiblity cape"),new Rewards(14,1400,"Elder wand")));
		return "Rewards created";
	}
	
	
	public String create( Rewards rewards) {
		if(rewards.getTitle()=="")
			throw new Exception("Title cannot be empty");   //throw exception
		else {
			repository.save(rewards);
			return "New Reward added";
			
		}
		
	}
	
	
	public String updateTitle( int id, String title) {
		Rewards rewards = repository.findById(id);
		rewards.setTitle(title);
		repository.save(rewards);
		return "Title Updated";
	}
	
	
	
	public String updaterequiredPoints( int id, int requiredPoints) {
		
		String user = "";
		user = repository.findById(id).toString();
		boolean isPresent=user.contains("id");
		if(!isPresent)
			throw new Exception("Reward not found");
		else
		{
			Rewards rewards = repository.findById(id);
			
			rewards.setRequiredPoints(requiredPoints);
			repository.save(rewards);
	}
		
		
		return "Points Updated";
	}
	

	public String Points( String title) {
		List<Rewards> rewards = repository.findBytitle(title);
		int i = -1;
		String s = "";
		for(Rewards r : rewards) {
			if(r.getTitle().equals(title) ) {
				if(r.getPoints(0)>=r.getRequiredPoints()) {
				i=r.getPoints(r.getRequiredPoints());
				}
				else {
					s = "Less Than Required Points";
				}
			}
			else {
				s = "Rewards Not Found";
			}
		}
		if(i != -1)
		s = "Redeemed "+title;
	    return s;
	}
	
	
	public String deleteAllRewards() {
		repository.deleteAll();
		return "All Rewards Deleted";
	}
	
	
	
	public String deleteReward( int id) {
		String user = "";
		user = repository.findById(id).toString();
		boolean isPresent=user.contains("id");
		if(!isPresent)
			throw new Exception("Reward not found");
		else
		{
			repository.deleteById(id);
	}
		
		return "User Deleted";
	}
	
	
	
	
	public List<Rewards> findAll(){
		List<Rewards> rewards = repository.findAll();
		List<Rewards> rewardsList = new ArrayList<>();
		for(Rewards r : rewards) {
			rewardsList.add(new Rewards(r.getId(),r.getRequiredPoints(),r.getTitle()));
		}
		return rewardsList;
	}
	
	
	public List<Rewards> findReward(){
		List<Rewards> rewards = repository.findAll();
		List<Rewards> rewardsList = new ArrayList<>();
		for(Rewards r : rewards) {
			if(r.getPoints(100)>=r.getRequiredPoints())
				rewardsList.add(new Rewards(r.getId(),r.getRequiredPoints(),r.getTitle()));
	}
		//System.out.println(rewardsList);
		return rewardsList;
	}
	
	
	public Rewards Search( int id) {
		String task = "";
		Rewards r = repository.findById(id);
		task=r.toString();
		boolean isPresent=task.contains("id");
		if(!isPresent)
			throw new Exception("Reward not found");
		else
			return r;
	
	}
	
//	public String Search( int id) {
//        String task = "";
//        task = repository.findById(id).toString();
//        boolean isPresent=task.contains("id");
//        if(!isPresent)
//            throw new Exception("reward not found");
//        else
//            return task;
//   
//    }

	
	public List<Rewards> fetchDataByName( String title){
		List<Rewards> rewards = repository.findBytitle(title);
		List<Rewards> rewList = new ArrayList<>();
		
		for(Rewards r : rewards) {
			rewList.add(new Rewards(r.getId(),r.getRequiredPoints(),r.getTitle()));
		}
		return rewList;
	}
}
