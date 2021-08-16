package com.cap.ppa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cap.ppa.model.Dailies;
import com.cap.ppa.repository.DailiesRepository;
import com.cap.ppa.serviceimpl.DailiesServiceImpl;

@ExtendWith(MockitoExtension.class)
class DailiesUnitTest {

	@InjectMocks
	DailiesServiceImpl dailiesService;
	
	@Mock
	DailiesRepository dailiesRepository;
	
	//FindAll() unit testing
	@Test
	public void testFindAll() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		Dailies d2 = new Dailies("Other", "2021-06-01", "2021-10-21", "50 mins", "10 mins",false, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.addAll(Arrays.asList(d1, d2));

		dailiesService.saveDailiesTask(d1);
		dailiesService.saveDailiesTask(d2);	
		Mockito.when(dailiesRepository.findAll()).thenReturn(list);

		// when
		List<Dailies> result = dailiesService.findAllDailiesTask();

		// then
		assertEquals(result.size(),2);
		
		assertEquals(result.get(0).getTitle(),d1.getTitle());
		assertEquals(result.get(1).getTitle(),d2.getTitle());
		
		assertEquals(result.get(0).getCompletedTime(),d1.getCompletedTime());
		assertEquals(result.get(1).getCompletedTime(),d2.getCompletedTime());
	}

	@Test
	public void testFindAllToday() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		Dailies d2 = new Dailies("Other", "2021-06-01", "2021-10-21", "50 mins", "10 mins",false, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.addAll(Arrays.asList(d1, d2));

		dailiesService.saveDailiesTask(d1);
		dailiesService.saveDailiesTask(d2);	
		Mockito.when(dailiesRepository.findAll()).thenReturn(list);

		// when
		List<Dailies> result = dailiesService.findAllTodayDailies();

		// then
		assertEquals(result.size(),1);
		
		assertNotEquals(result.get(0).getTitle(),d2.getTitle());
		assertEquals(result.get(0).getTitle(),d1.getTitle());
		
		assertNotEquals(result.get(0).getCompletedTime(),d2.getCompletedTime());
	}

	@Test
	public void testUpdateDailiesTitle() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		Dailies d2 = new Dailies("Other", "2021-06-01", "2021-10-21", "50 mins", "10 mins",false, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.addAll(Arrays.asList(d1, d2));

		dailiesService.saveDailiesTask(d1);		
		dailiesService.saveDailiesTask(d2);
		
		d1.setTitle("New Task");
		Mockito.when(dailiesRepository.findById(1)).thenReturn(d1);
		dailiesService.updateDailiesTaskByTitle(1,"New Task");
		
		Mockito.when(dailiesRepository.findAll()).thenReturn(list);
		// when
		List<Dailies> result = dailiesService.findAllDailiesTask();

		// then
		assertNotEquals(result.get(0).getTitle(),"New New Task");
		assertEquals(result.get(0).getTitle(),d1.getTitle());
	}

	@Test
	public void testUpdateDailiesTaskByTime() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		Dailies d2 = new Dailies("Other", "2021-06-01", "2021-10-21", "50 mins", "10 mins",false, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.addAll(Arrays.asList(d1, d2));

		dailiesService.saveDailiesTask(d1);		
		dailiesService.saveDailiesTask(d2);
		
		d2.setCompletedTime("25 mins");
		Mockito.when(dailiesRepository.findById(2)).thenReturn(d2);
		dailiesService.updateDailiesTaskByTitle(2,"25 mins");
		
		Mockito.when(dailiesRepository.findAll()).thenReturn(list);
		// when
		List<Dailies> result = dailiesService.findAllDailiesTask();

		// then
		assertNotEquals(result.get(1).getCompletedTime(),"30 mins");
		assertEquals(result.get(1).getCompletedTime(),d2.getCompletedTime());
	}
	
	@Test
	public void testUpdateDailiesTaskByImp() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		Dailies d2 = new Dailies("Other", "2021-06-01", "2021-10-21", "50 mins", "10 mins",false, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.addAll(Arrays.asList(d1, d2));

		dailiesService.saveDailiesTask(d1);		
		dailiesService.saveDailiesTask(d2);
		
		d1.setImportant(false);
		Mockito.when(dailiesRepository.findById(1)).thenReturn(d1);
		dailiesService.updateDailiesTaskByImp(1,false);
		
		Mockito.when(dailiesRepository.findAll()).thenReturn(list);
		// when
		List<Dailies> result = dailiesService.findAllDailiesTask();

		// then
		assertNotEquals(result.get(1).isImportant(),true);
		assertEquals(result.get(1).isImportant(),d2.isImportant());
	}

	@Test
	public void testSearchDailiesTask() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		Dailies d2 = new Dailies("Other", "2021-06-01", "2021-10-21", "50 mins", "10 mins",false, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.addAll(Arrays.asList(d1, d2));

		dailiesService.saveDailiesTask(d1);		
		dailiesService.saveDailiesTask(d2);
		
		Mockito.when(dailiesRepository.findById(1)).thenReturn(d1);
		
		// when
		Dailies result = dailiesService.searchDailiesTask(1);

		// then
		assertNotEquals(result.toString(),d2.toString());
		assertEquals(result.toString(),d1.toString());
	}
	
	@Test
	public void testSearchByDailiesTaskTitle() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.add(d1);

		dailiesService.saveDailiesTask(d1);		
		
		Mockito.when(dailiesRepository.findByTitle("ABCD")).thenReturn(list);
		
		// when
		List<Dailies> result = dailiesService.searchByDailiesTaskTitle("ABCD");

		// then
		assertEquals(result.get(0).getTitle(),d1.getTitle());
		assertEquals(result.get(0).isImportant(),d1.isImportant());
	}

	@Test
	public void testSearchByDailiesTaskPriority() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.add(d1);

		dailiesService.saveDailiesTask(d1);		
		
		Mockito.when(dailiesRepository.findByImportant(true)).thenReturn(list);
		
		// when
		List<Dailies> result = dailiesService.searchByDailiesTaskPriority(true);

		// then
		assertEquals(result.get(0).getTitle(),d1.getTitle());
		assertEquals(result.get(0).isImportant(),d1.isImportant());
	}
	
	@Test
	public void testSearchByDailiesTaskCompleted() 
	{
		// given
		Dailies d1 = new Dailies("ABCD", "2021-04-22", "2021-05-20", "10 mins", "1 mins",true, false);
		List<Dailies> list = new ArrayList<Dailies>();
		list.add(d1);

		dailiesService.saveDailiesTask(d1);		
		
		Mockito.when(dailiesRepository.findByCompleted(false)).thenReturn(list);
		
		// when
		List<Dailies> result = dailiesService.searchByDailiesTaskCompleted(false);

		// then
		assertEquals(result.get(0).getTitle(),d1.getTitle());
		assertEquals(result.get(0).isCompleted(),d1.isCompleted());
	}

}
