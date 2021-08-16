package com.cap.ppa;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.cap.ppa.exception.Exception;
import com.cap.ppa.model.TodoItem;
import com.cap.ppa.repository.TodoRepository;
import com.cap.ppa.serviceimpl.TodoServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TodoControllerUnitTest {
	@Mock
	private TodoRepository todoRepo;
	
	@Mock
	private TodoItem todoItem;
	
	@InjectMocks
	private TodoServiceImpl todoService;
	
	@Test
	void testsaveTodoTask()  {
		todoItem.setId(1l);
		todoItem.setTitle("call");
		todoItem.setDescription("call mom");
		todoItem.setDueDate("14/4/2021");
		todoItem.setStatus("incomplete");
			
		given(todoRepo.save(todoItem)).willReturn(todoItem);
		String savedStaff=todoService.saveTodoTask(todoItem);
		Assertions.assertThat(savedStaff).isNotNull();
		verify(todoRepo).save(any(TodoItem.class));
	}
	@Test
	void testsearchTodoTask() throws Exception  {
		todoItem.setId(1l);
		todoItem.setTitle("call");
		todoItem.setDescription("call mom");
		todoItem.setDueDate("14/4/2021");
		todoItem.setStatus("incomplete");
		given(todoRepo.findById(1l)).willReturn(Optional.of(todoItem));
		TodoItem getTask= todoService.searchTodoTask(1l);
		Assertions.assertThat(getTask).isNotNull();
		
	}
	
	
	@Test
	void testfindAllTasks() {
		List<TodoItem>todoList=new ArrayList<TodoItem>();
		todoItem.setId(1l);
		todoItem.setTitle("call");
		todoItem.setDescription("call mom");
		todoItem.setDueDate("14/4/2021");
		todoItem.setStatus("incomplete");
		
		given(todoRepo.findAll()).willReturn(todoList);
		List<TodoItem>todoList1=todoService.findAllTasks();
		Assertions.assertThat(todoList1).isEqualTo(todoList);
	}
	@Test
	void testUpdateTodoTask() {
		given(todoRepo.save(todoItem)).willReturn(todoItem);
		String expectedTask=todoService.updateTodoTask(todoItem);
		Assertions.assertThat(expectedTask).isNotNull();
	    
	    verify(todoRepo).save(any(TodoItem.class));
	}
}
