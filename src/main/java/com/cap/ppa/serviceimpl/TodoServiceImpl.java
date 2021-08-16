package com.cap.ppa.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cap.ppa.exception.Exception;
import com.cap.ppa.model.TodoItem;
import com.cap.ppa.repository.TodoRepository;
import com.cap.ppa.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService{
	@Autowired
	private TodoRepository todoRepo;
	
	public List<TodoItem> findAllTasks(){
		List<TodoItem> todoItem=todoRepo.findAll();
		return todoItem;
	}
	public String saveTodoTask(TodoItem todoItem) {
		if(todoItem.getTitle()=="" || todoItem.getDescription()=="" || todoItem.getDueDate()=="" 
				|| todoItem.getStatus()=="")
			throw new Exception("Field cannot be empty");
		else {
				todoRepo.save(todoItem);
				return "Task is created";
		}
		
	}
	public void deleteTodoTask(long id) {
		String task = "";
		task = todoRepo.findById(id).toString();
		boolean isPresent=task.contains("id");
		if(!isPresent)
			throw new Exception("Task not found");
		else {
			todoRepo.deleteById(id);
		}
		
	}
	
	public void deleteAllTodoTasks() {
		todoRepo.deleteAll();
		
	}
	
	public String updateTodoTask(TodoItem todoItem) {
		saveTodoTask(todoItem);
		return "Task is updated";
	}
	
	public TodoItem searchTodoTask(long id){
		Optional<TodoItem> optional= todoRepo.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			throw new Exception("Task not found");
	}
	
	public List<TodoItem> searchByTodoTaskTitle(String title){
		
		List<TodoItem> todoItem = todoRepo.findByTitle(title);
		List<TodoItem> todoItem1=new ArrayList<>();
		for (TodoItem todo : todoItem) {
			todoItem1.add(new TodoItem(todo.getId(),todo.getTitle(),todo.getDescription(),todo.getDueDate(),todo.getStatus()));
		}
		return todoItem1;
	}
}
