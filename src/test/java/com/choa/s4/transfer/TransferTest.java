package com.choa.s4.transfer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.s4.MyTestCase;
import com.choa.s4.card.Card;
import com.choa.s4.transfer.Bus;
import com.choa.s4.transfer.Subway;

public class TransferTest extends MyTestCase{

	@Autowired
	private Bus bus;
	//@Autowired
	private Subway subway;
	//@Autowired
	private Card card;
	
	@Autowired
	private Taxi taxi;
	
	@Test
	public void test() {
	//	bus.takeBus(30, "jiseung");
	//	subway.takeSubway();
		
		taxi.getTaxi();
	}
		
	
}
