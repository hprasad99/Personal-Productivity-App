package com.cap.ppa.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cap.ppa.model.Habits;

public interface HabitRepository extends CrudRepository<Habits, Long>{
	List<Habits> findBySkill(String title);
	List<Habits> findAll();
	Habits findById(long id);
}
