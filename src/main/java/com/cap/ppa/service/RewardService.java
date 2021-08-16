package com.cap.ppa.service;

import java.util.List;


import com.cap.ppa.model.Rewards;

public interface RewardService {
	public String bulkcreate();
	public String create( Rewards rewards);
	public String updateTitle( int id, String title);
	public String updaterequiredPoints( int id, int requiredPoints);
	public String Points( String title);
	public String deleteAllRewards();
	public String deleteReward( int id);
	public List<Rewards> findReward();
	public Rewards Search( int id);
	public List<Rewards> fetchDataByName(String title);
}
