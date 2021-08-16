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

import com.cap.ppa.model.Login;
import com.cap.ppa.repository.LoginRepository;
import com.cap.ppa.serviceimpl.LoginServiceImpl;



@ExtendWith(MockitoExtension.class)

class LoginControllerUnitTests {
	@InjectMocks
	LoginServiceImpl loginService;
	
	@Mock
	LoginRepository loginRepository;
	
	@Test
	public void testFindAll() 
	{
		// given
		Login l1 = new Login(0, "Satyam", "satyamsharma@gmail.com", "9990892298", "Delhi", "ASDG");
		Login l2 = new Login(0, "Sharma","Sharma@gmail.com", "9812349876", "Haryana", "LKHK");
		
		List<Login> list = new ArrayList<Login>();
		list.addAll(Arrays.asList(l1, l2));

		loginService.addUser(l1);
		loginService.addUser(l2);
		Mockito.when(loginRepository.findAll()).thenReturn(list);

		// when
		List<Login> result = loginService.findAll();

		// then
		assertEquals(result.size(),2);
		
		assertEquals(result.get(0).getName(),l1.getName());
		assertEquals(result.get(1).getName(),l2.getName());
		
		assertEquals(result.get(0).getUsername(),l1.getUsername());
		assertEquals(result.get(1).getUsername(),l2.getUsername());
		
		assertEquals(result.get(0).getIdProof(),l1.getIdProof());
		assertEquals(result.get(1).getIdProof(),l2.getIdProof());
		
		assertEquals(result.get(0).getPlace(),l1.getPlace());
		assertEquals(result.get(1).getPlace(),l2.getPlace());
		
		assertEquals(result.get(0).getMobile(),l1.getMobile());
		assertEquals(result.get(1).getMobile(),l2.getMobile());
	}

	@Test
	public void testFindByUsername() 
	{
		// given
		Login l1 = new Login(0, "Satyam", "satyamsharma@gmail.com", "9990892298", "Delhi", "ASDG");
		Login l2 = new Login(0, "Sharma","Sharma@gmail.com", "9812349876", "Haryana", "LKHK");
		
		List<Login> list = new ArrayList<Login>();
		list.addAll(Arrays.asList(l1, l2));

		loginService.addUser(l1);
		loginService.addUser(l2);
		Mockito.when(loginRepository.findAll()).thenReturn(list);

		// when
		List<Login> result = loginService.findAll();

		// then
		assertEquals(result.size(),2);
		
		assertEquals(result.get(0).getName(),l1.getName());
		assertEquals(result.get(1).getName(),l2.getName());
		
		assertEquals(result.get(0).getUsername(),l1.getUsername());
		assertEquals(result.get(1).getUsername(),l2.getUsername());
		
		assertEquals(result.get(0).getIdProof(),l1.getIdProof());
		assertEquals(result.get(1).getIdProof(),l2.getIdProof());
		
		assertEquals(result.get(0).getPlace(),l1.getPlace());
		assertEquals(result.get(1).getPlace(),l2.getPlace());
		
		assertEquals(result.get(0).getMobile(),l1.getMobile());
		assertEquals(result.get(1).getMobile(),l2.getMobile());
	}

	@Test
	public void testUpdatePassword() 
	{
		// given
		Login l1 = new Login(0, "Satyam", "satyamsharma@gmail.com", "9990892298", "Delhi", "ASDG", "Satyam@123");
		//Login l2 = new Login(0, "Sharma","Sharma@gmail.com", "9812349876", "Haryana", "LKHK");
		//Login l2 = new Login("Sharma","Sharma@34");
		List<Login> list = new ArrayList<Login>();
		//list.addAll(Arrays.asList(l1, l2));
		list.add(l1);

		loginService.addUser(l1);
		//loginService.addUser(l2);
		
		l1.setPassword("Asdfgh@12");
		Mockito.when(loginRepository.findByUsername("Satyam")).thenReturn(list);
		loginService.updatePass("Satyam","Asdfgh@12");
		
         
		
		// when
		List<Login> result = loginService.fetchDataByUsername("Satyam");

		// then
		assertNotEquals(result.get(0).getPassword(),"Satyam@21");
		assertEquals(result.get(0).getPassword(),l1.getPassword());
	}
	
	@Test
	public void testUpdatePlace() 
	{
		// given
		Login l1 = new Login(0, "Satyam", "satyamsharma@gmail.com", "9990892298", "Delhi", "ASDG");
		//Login l2 = new Login(0, "Sharma","Sharma@gmail.com", "9812349876", "Haryana", "LKHK");
		//Login l2 = new Login("Sharma","Sharma@34");
		List<Login> list = new ArrayList<Login>();
		//list.addAll(Arrays.asList(l1, l2));
		list.add(l1);

		loginService.addUser(l1);
		//loginService.addUser(l2);
		
		l1.setPlace("Punjab");
		Mockito.when(loginRepository.findByUsername("Satyam")).thenReturn(list);
		loginService.updatePlace("Satyam","Punjab");
		
         
		
		// when
		List<Login> result = loginService.fetchDataByUsername("Satyam");

		// then
		assertNotEquals(result.get(0).getPlace(),"Delhi");
		assertEquals(result.get(0).getPlace(),l1.getPlace());
	}
	
	@Test
	public void testUpdateMobile() 
	{
		// given
		Login l1 = new Login(0, "Satyam", "satyamsharma@gmail.com", "9990892298", "Delhi", "ASDG");
		//Login l2 = new Login(0, "Sharma","Sharma@gmail.com", "9812349876", "Haryana", "LKHK");
		//Login l2 = new Login("Sharma","Sharma@34");
		List<Login> list = new ArrayList<Login>();
		//list.addAll(Arrays.asList(l1, l2));
		list.add(l1);

		loginService.addUser(l1);
		//loginService.addUser(l2);
		
		l1.setMobile("9876543215");
		Mockito.when(loginRepository.findByUsername("Satyam")).thenReturn(list);
		loginService.updateMobile("Satyam","9876543215"); 
		
		// when
		List<Login> result = loginService.fetchDataByUsername("Satyam");

		// then
		assertNotEquals(result.get(0).getMobile(),"9990892298");
		assertEquals(result.get(0).getMobile(),l1.getMobile());
	}
}
