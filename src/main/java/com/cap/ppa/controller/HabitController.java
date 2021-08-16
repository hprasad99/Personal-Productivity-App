package com.cap.ppa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.ppa.model.Habits;
import com.cap.ppa.repository.HabitRepository;
import com.cap.ppa.serviceimpl.HabitServiceImpl;

import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/habits")
@Api(value="/habits")
public class HabitController {
	@Autowired
	private HabitServiceImpl habitService;
	

	
	@PostMapping("/create")
	public String create(@RequestBody Habits habits) {
		habitService.saveHabitTask(habits,0);
		return "New added";
	}
	
	@PutMapping("/update/skill")
	public String updateSkillTitle(@RequestParam long id,@RequestParam String skill) {

		
		habitService.updateHabitTaskBySkill(id,skill);
		return "Skill Updated";
	}
	
	@PutMapping("/update/duration")
	public String updateDuration(@RequestParam long id,@RequestParam String duration,@RequestParam String date) {

		String s = habitService.updateHabitTaskByTime(id,duration,date);
		return s;
	}
	
	@DeleteMapping("/deleteall")
	public String deleteAllSkills() {
		habitService.deleteAllHabitTask();
		return "All Skills Deleted";
	}
	
	@DeleteMapping("/delete/{id}")
	public String deletedSkill(@PathVariable long id) {
		habitService.deleteHabitTask(id);
		return "Skill Deleted";
	}
	
	@GetMapping("/findall")
	public List<Habits> findAll(){

		List<Habits> habits = habitService.findAllHabitTask();
		return habits;
	}
	
	@GetMapping("/search/{id}")
	public String search(@PathVariable long id) {

		String task = habitService.searchHabitTask(id);
		return task;
	}

	@GetMapping("/searchbyskill/{skill}")
	public List<Habits> fetchDataBySkill(@PathVariable String skill){
		
		List<Habits> list = habitService.searchByHabitTaskTitle(skill);
		return list;
	}

}
