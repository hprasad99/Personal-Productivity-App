package com.cap.ppa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cap.ppa.model.Dailies;

public interface DailiesRepository extends JpaRepository<Dailies, Integer>{
	List<Dailies> findByTitle(String title);
	List<Dailies> findByImportant(boolean important);
	List<Dailies> findByCompleted(boolean completed);
	List<Dailies> findAll();
	Dailies findById(int id);
}
