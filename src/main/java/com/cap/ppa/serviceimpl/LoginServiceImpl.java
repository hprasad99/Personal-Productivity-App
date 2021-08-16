package com.cap.ppa.serviceimpl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.ppa.exception.Exception;
import com.cap.ppa.model.Login;
import com.cap.ppa.repository.LoginRepository;
import com.cap.ppa.service.LoginService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginRepository repository;
	
	public String addUser(Login login)
	{
		String result;
		
		if(login.getUsername()=="" || login.getName()==""|| login.getIdProof()=="" || login.getPlace()==""||login.getMobile()=="")
			throw new Exception("Field cannot be empty");
		else {
			List<Login> list=repository.findByUsername(login.getUsername());
			
			if(list.size()!=0)
			{
			result="Oops!  There is already a user registered with the Username provided.";
			
			}
			else
			{
				//int rand = (int)(Math.random() * 1000);
				result = login.getName() +login.getPlace().substring(0,2)+"@"+login.getMobile().substring(1,3);
			login.setPassword(result);
			repository.save(login);
			result="user added";
			}
		}
		
		
		return result;
	}
	
	
	public String updatePassword(long id, String password) {
		String result;
		
		String user = "";
		user = repository.findById(id).toString();
		boolean isPresent=user.contains("id");
		if(!isPresent)
			throw new Exception("User not found");
		else
		{
			Login login = repository.findByid(id);
			String pattern ;
			pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
			if(password.matches(pattern))
			{
				login.setPassword(password);
				repository.save(login);
				result= "password Updated";
			}
			else
			{
				result ="Password does not met requirements";
			}
			
	}
		
		return result;
	}
	
	
	public String updatePass(String username, String password) {
		String result;
		Login login = repository.findByUserName(username);
		if(login !=null) {
			String pattern ;
			pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
			if(password.matches(pattern))
			{
				login.setPassword(password);
				repository.save(login);
				result= "password Updated";
			}
			else
			{
				result ="Password does not met requirements";
			}
			
		}
		else
		{
			result="Username not found";
		}
		return result;
		
	}
	
	
	
	
	public String updatePlace(String username, String place) {
		String result;
		Login login = repository.findByUserName(username);
		if(login !=null) {
			
				login.setPlace(place);
				repository.save(login);
				result= "Place Updated";
			
		}
		else
		{
			result="Username not found";
		}
		return result;
		
	}
	
	
	public String updateMobile(String username, String mobile) {
		String result;
		Login login = repository.findByUserName(username);
		if(login !=null) {
			
				login.setMobile(mobile);
				repository.save(login);
				result= "Mobile Updated";
			
		}
		else
		{
			result="Username not found";
		}
		return result;
		
	}
	
	
	
	
	
	public String login(Login logins) {
		String result;
	    Login login = repository.findByUserName(logins.getUsername());
		if(login!=null)
		{
			String uname=login.getUsername();
			String upass=login.getPassword();
		
			if(logins.getUsername().equalsIgnoreCase(uname) && logins.getPassword().equalsIgnoreCase(upass)) 
			{
				result = "Login successful";			
			}
			else 
			{
				result= "Invalid Credentials";
			}
		}
		else
		{
			result= "No user found";
		}
		return result;
	}
	
	
		
	

	
	
	public String deleteAllUser() {
		repository.deleteAll();
		return "All User Deleted";
	}
	
	
	public String deleteUser(long id) {
		
		String user = "";
		user = repository.findById(id).toString();
		boolean isPresent=user.contains("id");
		if(!isPresent)
			throw new Exception("User not found");
		else
		{
			repository.deleteById(id);
	}
		
		return "User Deleted";
	}
	
	
	
	public List<Login> findAll(){
		List<Login> login = repository.findAll();
		List<Login> loginList = new ArrayList<>();
		for(Login l:login) {
			loginList.add(l);
		}
		return loginList;
	}
	
	
	public List<Login> fetchDataByUsername(String username){
		List<Login> login = repository.findByUsername(username);
		List<Login> loginList = new ArrayList<>();
		
		for(Login l: login) {
			loginList.add(l);
		}
		return loginList;
	}


	
}
