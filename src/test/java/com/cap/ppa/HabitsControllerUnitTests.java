package com.cap.ppa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cap.ppa.model.Habits;
import com.cap.ppa.repository.HabitRepository;
import com.cap.ppa.serviceimpl.HabitServiceImpl;


@ExtendWith(MockitoExtension.class)
class HabitsControllerUnitTests {

	@InjectMocks
	HabitServiceImpl habitService;
	
	@Mock
	HabitRepository habitRepository;
	
	@Test
	public void testFindAll() {
		//given
		Habits h1 = new Habits("ABCD","2021-04-14","50",0);
		Habits h2 = new Habits("QWER","2021-04-13","40",0);
		List<Habits> list = new ArrayList<Habits>();
		list.addAll(Arrays.asList(h1,h2));
		
		habitService.saveHabitTask(h1,10);
		habitService.saveHabitTask(h2, 20);
		
		Mockito.when(habitRepository.findAll()).thenReturn(list);
		
		//when
		List<Habits> result = habitService.findAllHabitTask();
		
		//then
		assertEquals(result.size(), 2);
		
		assertEquals(result.get(0).getSkill(),h1.getSkill());
		assertEquals(result.get(1).getSkill(),h2.getSkill());
		
		assertEquals(result.get(0).getDuration(),h1.getDuration());
		assertEquals(result.get(1).getDuration(), h2.getDuration());
	}
	
	@Test
	public void testFindAllToday() {
		
		//given
		Habits h1 = new Habits("ABCD","2021-04-14","50",0);
		Habits h2 = new Habits("QWER","2021-04-13","40",0);
		List<Habits> list = new ArrayList<Habits>();
		list.addAll(Arrays.asList(h1,h2));
				
		habitService.saveHabitTask(h1,10);
		habitService.saveHabitTask(h2, 20);
		Mockito.when(habitRepository.findAll()).thenReturn(list);
		
		//when
		List<Habits> result = habitService.findAllHabitTask();
		
		//then
		assertEquals(result.size(), 2);
		
		assertNotEquals(result.get(0).getSkill(), h2.getSkill());
		assertEquals(result.get(0).getSkill(), h1.getSkill());
		
		assertEquals(result.get(0).getDuration(),h1.getDuration());
	}
	
	@Test
	public void testUpdateHabitSkill() {
		//given
		Habits h1 = new Habits("ABCD","2021-04-14","50",0);
		Habits h2 = new Habits("QWER","2021-04-13","40",0);
		List<Habits> list = new ArrayList<Habits>();
		list.addAll(Arrays.asList(h1,h2));
					
		habitService.saveHabitTask(h1,10);
		habitService.saveHabitTask(h2, 20);
		
		h1.setSkill("New Task");
		Mockito.when(habitRepository.findById(1l)).thenReturn(h1);
		habitService.updateHabitTaskBySkill(1l, "New Task");
		
		Mockito.when(habitRepository.findAll()).thenReturn(list);
		//when
		List<Habits> result = habitService.findAllHabitTask();
		
		//then
		assertNotEquals(result.get(0).getSkill(), "New New Task");
		assertEquals(result.get(0).getSkill(), h1.getSkill());
	}
	
	@Test
	public void testUpdateHabitByTime() {
		
		//given
		Habits h1 = new Habits("ABCD","2021-04-14","50",0);
		Habits h2 = new Habits("QWER","2021-04-13","40",0);
		List<Habits> list = new ArrayList<Habits>();
		list.addAll(Arrays.asList(h1,h2));
					
		habitService.saveHabitTask(h1,10);
		habitService.saveHabitTask(h2, 20);
		
		h2.setDuration("40");
		Mockito.when(habitRepository.findById(21)).thenReturn(h2);
		habitService.updateHabitTaskBySkill(21, "ABCD");
		
		Mockito.when(habitRepository.findAll()).thenReturn(list);
		//when
		List<Habits> result = habitService.findAllHabitTask();
		
		//then
		assertNotEquals(result.get(1).getDuration(), "30");
		assertEquals(result.get(1).getDuration(), h2.getDuration());
	}
	
	

}
