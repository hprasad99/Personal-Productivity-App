package com.cap.ppa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.ppa.model.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem,Long> {
	public List<TodoItem> findAll();
	public List<TodoItem> findByTitle(String title);
}
