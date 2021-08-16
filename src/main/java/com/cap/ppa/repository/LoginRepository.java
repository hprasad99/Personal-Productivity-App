package com.cap.ppa.repository;
import java.util.List;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cap.ppa.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{
	List<Login> findByUsername(String username);
	List<Login> findAll();
	@Query("from Login where id=?1")
	Login findByid(long id);
	@Query("from Login where username=?1")
	public Login findByUserName(String username);
	
}
