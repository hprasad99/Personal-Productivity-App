package com.cap.ppa.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.ppa.model.Rewards;
import com.cap.ppa.serviceimpl.RewardServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin (origins="*")
@RestController
@RequestMapping("/rewards")
@Api(value="/rewards", tags="Rewards module")
public class RewardsController {
	@Autowired
	RewardServiceImpl rews;
	
	@ApiOperation(value="Create the predefined reward list")
	@GetMapping("/bulkcreate")
	public String bulkcreate() {
		//repository.save();
		String result = rews.bulkcreate();
		return result;
	}
	
	@ApiOperation(value="Create the user defined reward")
	@PostMapping("/create")
	public String create(@RequestBody Rewards rewards) {
		String result = rews.create(rewards);
		return result;
	}
	
	@ApiOperation(value="Update the title of rewards")
	@PutMapping("/update/title")
	public String updateTitle(@RequestParam int id,@RequestParam String title) {
		String result = rews.updateTitle(id, title);
		return result;
	}
	
	
	@ApiOperation(value="Update the points required for rewards")
	@PutMapping("/update/requiredPoints")
	public String updaterequiredPoints(@RequestParam int id,@RequestParam int requiredPoints) {
		String result = rews.updaterequiredPoints(id, requiredPoints);
		return result;
	}
	
	@ApiOperation(value="Update the user's collected points")
	@PutMapping("/update/Points")
	public String Points(@RequestParam String title) {
		String result = rews.Points(title);
		return result;
	}
	
	@ApiOperation(value="Delete all the rewards present")
	@DeleteMapping("/deleteall")
	public String deleteAllRewards() {
		String result = rews.deleteAllRewards();
		return result;
	}
	
	@ApiOperation(value="Delete the particular reward")
	@DeleteMapping("/delete/{id}")
	public String deleteReward(@PathVariable int id) {
		String result = rews.deleteReward(id);
		return result;
	}
	
	
	@ApiOperation(value="Show all the rewards in the list")
	@GetMapping("/findall")
	public List<Rewards> findAll(){
		
		List<Rewards> rewardsList= rews.findAll();
		
		return rewardsList;
	}
	
	@ApiOperation(value="Show all the rewards available to the user for their collected points")
	@GetMapping("/findreward")
	public List<Rewards> findReward(){
		
		List<Rewards> rewardsList = rews.findReward();
		
		return rewardsList;
	}
	
	@ApiOperation(value="Search rewards by id")
	@GetMapping("/search/{id}")
	public Rewards Search(@RequestParam int id) {
	 Rewards result = rews.Search(id);
		return result;
	}

	@ApiOperation(value="Search rewards by their name")
	@GetMapping("/searchbyrewardname/{title}")
	public List<Rewards> fetchDataByName(@PathVariable String title){
		
		List<Rewards> rewList =rews.fetchDataByName(title);
		
		
		return rewList;
	}
}
