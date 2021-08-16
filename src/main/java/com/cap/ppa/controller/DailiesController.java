package com.cap.ppa.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

import com.cap.ppa.model.Dailies;
import com.cap.ppa.serviceimpl.DailiesServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 

@CrossOrigin("*")
@RestController
@RequestMapping("/dailies")
@Api(value="/dailies", tags="Dailies Modules")
public class DailiesController {
	@Autowired
	private DailiesServiceImpl dailiesService;
	
//	@ApiOperation(value = "Pre defined dailies tasks")
//	@GetMapping(value="/bulkcreate")
//	public String bulkcreate(){
//		// save a list of tasks as examples
//		dailiesService.saveDailiesTask(new Dailies("Reading", "2021-04-01", "2021-04-20", "30 mins", "0 mins",false));
//		dailiesService.saveDailiesTask(new Dailies("Sleeping", "2021-05-04", "2021-09-20", "8 hrs", "5 hrs",false));
//		dailiesService.saveDailiesTask(new Dailies("Walking", "2021-05-02", "2021-05-11", "10 mins", "1 mins",true));
//		dailiesService.saveDailiesTask(new Dailies("Other", "2021-04-01", "2021-05-21", "50 mins", "10 mins",false));
//		return "Task bulk created";
//	}
	
	@ApiOperation(value = "Create new dailies task")
	@PostMapping(value="/create")
	public String create(@RequestBody Dailies dailies){
		// save a new task
		dailiesService.saveDailiesTask(dailies);
		return "New Task Added";
	}
	
	@ApiOperation(value = "Delete task by specific id")	
	@DeleteMapping(value="/delete/{id}")
    public String deletedDailies(@PathVariable int id){
        // delete a single task
		dailiesService.deleteDailiesTask(id);
        return "Task Deleted";
    }
	
	@ApiOperation(value = "Delete all task")	
	@DeleteMapping(value="/deleteall")
    public String deleteAllDailies(){
        // delete all tasks
		dailiesService.deleteAllDailiesTask();
        return "All Tasks Deleted";
    }
   
	@ApiOperation(value = "Display all tasks")	
	@GetMapping(value="/findall")
	public List<Dailies> findAllDailies(){
		//find all tasks
		List<Dailies> dailies = dailiesService.findAllDailiesTask();
		return dailies;
	}
	
	@ApiOperation(value = "Display all today task(s)")	
	@GetMapping(value="/findtoday")
	public List<Dailies> findAllToday(){
		// find all today tasks
		List<Dailies> dailies = dailiesService.findAllTodayDailies();
		return dailies;
	}
	
	@ApiOperation(value = "Update title of the task")
	@PutMapping(value="/update/title")
	public String updateDailiesTitle(@RequestBody Dailies dailies){
      // update title of a single task
		dailiesService.updateDailiesTaskByTitle(dailies.getId(),dailies.getTitle());
		return "Title Updated";
	}
	
	@ApiOperation(value = "Update completed time of the task")
	@PutMapping(value="/update/completedtime")
	  public String updateDailiesTime(@RequestBody Dailies dailies){
		// update completed time of a single task
		dailiesService.updateDailiesTaskByTime(dailies.getId(),dailies.getCompletedTime());
			return "Completed Time Updated";
	  }

	@ApiOperation(value = "Reset completed time of all tasks")
	@PutMapping(value="/reset/completedtime")
	  public String resetDailiesTime(){
	      // reset the completed time to zero
		dailiesService.resetDailiesTaskByTime();
			return "Completed Time Reset";
	  }

	@ApiOperation(value = "Update importance of the task")
	@PutMapping(value="/update/important")
	  public String updateDailiesImp(@RequestBody Dailies dailies){
		// update importance of a single task
		dailiesService.updateDailiesTaskByImp(dailies.getId(),dailies.isImportant());
			return "Priority Updated";
	  }

	@ApiOperation(value = "Search task by specific id")	
	@GetMapping(value="/search/{id}")
	public Dailies search(@PathVariable int id){
		// search a single task
		Dailies dailies=dailiesService.searchDailiesTask(id);
		return dailies;
	}
	
	@ApiOperation(value = "Search task(s) by specific title")		
	@GetMapping(value="/searchbytitle/{title}")
	public List<Dailies> fetchDataByTitle( @PathVariable String title){
		// search task by title
		List<Dailies> list= dailiesService.searchByDailiesTaskTitle(title);
		return list;
	}
	
	@ApiOperation(value = "Search task(s) by importance")	
	@GetMapping(value="/searchbyimportant/{important}")
	public List<Dailies> fetchDataByPriority( @PathVariable boolean important){
		// search task by importance
		List<Dailies> list= dailiesService.searchByDailiesTaskPriority(important);
		return list;
	}
	
	@ApiOperation(value = "Search pending/completed task(s)")	
	@GetMapping(value="/searchbycompleted/{completed}")
	public List<Dailies> fetchDataByStatus( @PathVariable boolean completed){
		// search task by status
		List<Dailies> list= dailiesService.searchByDailiesTaskCompleted(completed);
		return list;
	}
}
