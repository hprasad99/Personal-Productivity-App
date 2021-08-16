package com.cap.ppa.service;

import java.util.List;

import com.cap.ppa.model.TodoItem;

public interface TodoService {
	public List<TodoItem> findAllTasks();
	public String saveTodoTask(TodoItem todoItem);
	public void deleteTodoTask(long id);
	public void deleteAllTodoTasks();
	public String updateTodoTask(TodoItem todoItem);
	public TodoItem searchTodoTask(long id);
	public List<TodoItem> searchByTodoTaskTitle(String title);
}
