package com.cap.ppa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.ppa.model.TodoItem;
import com.cap.ppa.serviceimpl.TodoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/todo")
@Api(value="/todo", tags="Todo List Modules")
public class TodoController {
	
	@Autowired
	private TodoServiceImpl todoService;
	
	@ApiOperation(value = "Display all tasks")
	
	@GetMapping("/findall")
	public List<TodoItem> findAllTodoItem(){
		//Display all tasks
		List<TodoItem> todoItem= todoService.findAllTasks();
		return todoItem;
	}
	
	@ApiOperation(value = "Create new todo task")
	@PostMapping("/create")
	public String saveTodoItem(@Validated @RequestBody TodoItem todoItem) {
		//create a task
		todoService.saveTodoTask(todoItem);
		return "Task is created";
	}
	
	@ApiOperation(value = "Delete task by specific id")
	@DeleteMapping("/delete/{id}")
	public String deleteTodoItem(@Validated @PathVariable long id) {
		//delete a task by id
		todoService.deleteTodoTask(id);
		return "Task is deleted";
	}
	
	@ApiOperation(value = "Delete all task")
	@DeleteMapping("/deleteall")
	public String deleteAllTodoItems() {
		//delete all tasks
		todoService.deleteAllTodoTasks();
		return "All tasks are deleted";
	}
	
	@ApiOperation(value = "Update the task")
	@PutMapping("/update")
	public String updateTodoItem(@RequestBody TodoItem todoItem) {
		// update the task
		todoService.updateTodoTask(todoItem);
		return "Task is Updated";
	}
	
	
	
	@ApiOperation(value = "Search task by specific id")	
	@GetMapping("/search/{id}")
	public TodoItem searchTodoItem(@Validated @PathVariable long id){
		// search the task by id
		return todoService.searchTodoTask(id);
	}
	
	@ApiOperation(value = "Search task(s) by specific title")	
	@GetMapping("/searchbytitle/{title}")
	public List<TodoItem> fetchDataByTodoItemTitle(@PathVariable String title){
		// search the task by title

		List<TodoItem> todoItem = todoService.searchByTodoTaskTitle(title);
		return todoItem;
	}
}
