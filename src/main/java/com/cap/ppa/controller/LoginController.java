package com.cap.ppa.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cap.ppa.model.Login;
import com.cap.ppa.repository.LoginRepository;
import com.cap.ppa.serviceimpl.LoginServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/logon")
@Api(value="/login", tags="login modules")
public class LoginController {
	@Autowired
	LoginServiceImpl loginservice;
	
	@ApiOperation(value="Register for new user")
	@PostMapping("/register")
	public String addUser(@RequestBody Login login)
	{
		String result = loginservice.addUser(login);
		
		return result;
	}
	
	
	@ApiOperation(value="Update passowrd using id")
	@PutMapping("/update/password")
	public String updatePassword(@RequestParam long id,@RequestParam String password) {
		 
		String result = loginservice.updatePassword(id, password);
		return result;
	}

	
	@ApiOperation(value="update password using username")
	@PutMapping("/update/pass")
	public String updatePass(@RequestBody Login login) {
		
		String result = loginservice.updatePass(login.getUsername(), login.getPassword());
		return result;
	}
	
	
	
	
	
	@ApiOperation(value="update place using username")
	@PutMapping("/update/place")
	public String updatePlace(@RequestBody Login login) {
		
		String result = loginservice.updatePlace(login.getUsername(),login.getPlace());
		return result;
	}
	
	@ApiOperation(value="update mobile using username")
	@PutMapping("/update/mobile")
	public String updateMobile(@RequestBody Login login) {
		
		String result = loginservice.updateMobile(login.getUsername(),login.getMobile());
		return result;
	}
	
	
	
	
	
	@ApiOperation(value="login")
	@PostMapping("/login")
	public String login(@RequestBody Login login) {
		
		
		String result = loginservice.login(login);
		
		return result;
	}
	
	
	
	
	
	
	@ApiOperation(value="Delete all the users")
	@DeleteMapping("/deleteall")
	public String deleteAllUser() {
		
		String result = loginservice.deleteAllUser();
		return result;
	}
	
	@ApiOperation(value="Delete users by their id")
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		String result = loginservice.deleteUser(id);
		return result;
	}
	
	
	@ApiOperation(value="Display all the users")
	@GetMapping("/findall")
	public List<Login> findAll(){
		
		List<Login> loginList = loginservice.findAll();
		
		return loginList;
	}
	
	@ApiOperation(value="Search the user by their username")
	@GetMapping("/searchbyusername/{username}")
	public List<Login> fetchDataByUsername(@PathVariable String username){
		
		List<Login> loginList = loginservice.fetchDataByUsername(username);
		
		
		return loginList;
	}

}
