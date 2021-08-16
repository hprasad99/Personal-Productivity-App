package com.cap.ppa.service;

import java.util.List;

import com.cap.ppa.model.Login;

public interface LoginService {
	public String addUser(Login login);
	public String updatePassword(long id, String password);
	public String updatePass(String username, String password);
	public String updateMobile(String username, String mobile);
	//public String updateMobil(String username, String mobile);
	//public String updateP(String username, String password);
	public String updatePlace(String username, String place);
	public String login(Login login);
	public String deleteAllUser();
	public String deleteUser(long id);
	//public List<Login> fetchDataByUsername(String username);
}
