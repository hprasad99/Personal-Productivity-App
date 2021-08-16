package com.cap.ppa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cap.ppa.model.Rewards;
import com.cap.ppa.repository.RewardRepository;
import com.cap.ppa.serviceimpl.RewardServiceImpl;

@ExtendWith(MockitoExtension.class)
class RewardsControllerUnitTests {
	@InjectMocks
	RewardServiceImpl rews;
	
	@Mock
	RewardRepository repository;
	
	@Test
	void testupdateTitle() {
		
			
			// given
			Rewards r1 = new Rewards(1, 100, "AR");
			Rewards r2 = new Rewards(2, 200, "RA");
			List<Rewards> list = new ArrayList<Rewards>();
			list.addAll(Arrays.asList(r1, r2));

			rews.create(r1);		
			rews.create(r2);
			
			r1.setTitle("New Task");
			Mockito.when(repository.findById(1)).thenReturn(r1);
			rews.updateTitle(1,"New Task");
			
			Mockito.when(repository.findAll()).thenReturn(list);
			// when
			List<Rewards> result = rews.findAll();

			// then
			assertNotEquals(result.get(0).getTitle(),"New New Task");
			assertEquals(result.get(0).getTitle(),r1.getTitle());
			
		
	}
	
	@Test
   public void  testfindReward(){
		// given
		Rewards r1 = new Rewards(1, 100, "AR");
		Rewards r2 = new Rewards(2, 200, "RA");
		List<Rewards> list = new ArrayList<Rewards>();
		list.addAll(Arrays.asList(r1, r2));

		rews.create(r1);		
		rews.create(r2);
		
		Mockito.when(repository.findAll()).thenReturn(list);
		// when
		List<Rewards> result = rews.findReward();

		// then
		assertEquals(result.get(0).getTitle(),r1.getTitle());
		assertEquals(result.get(1).getTitle(),r2.getTitle());

	}
	
	@Test
	public void testFetchDataByName() {
		// given
		             Rewards r1 = new Rewards(1, 100, "AR");
		
					List<Rewards> list = new ArrayList<Rewards>();
					list.add(r1);

					rews.create(r1);
					
					Mockito.when(repository.findBytitle("AR")).thenReturn(list);
					
					// when
					List<Rewards> result=rews.fetchDataByName("AR");

					// then
					assertNotEquals(result.get(0).getTitle(),"New New Task");
					assertEquals(result.get(0).getTitle(),r1.getTitle());
	}

}
