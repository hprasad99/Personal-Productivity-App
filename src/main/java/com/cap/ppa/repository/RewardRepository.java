package com.cap.ppa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cap.ppa.model.Rewards;

public interface RewardRepository extends CrudRepository<Rewards, Integer>{
	List<Rewards> findBytitle(String title);
	List<Rewards> findAll();
	//@Query("from Rewards where id=?1")
	Rewards findById(int id);
}
