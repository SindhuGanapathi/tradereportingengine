package com.vg.tradereportingengine.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.vg.tradereportingengine.TradeEventServiceApplication;
import com.vg.tradereportingengine.model.TradeEventDetails;
import com.vg.tradereportingengine.parser.TradeEventsXMLParser;
import com.vg.tradereportingengine.repository.TradeEventDetailsRepository;
import com.vg.tradereportingengine.service.TradeEventBuilderService;

@SpringBootTest(classes = {TradeEventServiceApplication.class})
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EventBuilderServiceTest {
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private TradeEventBuilderService tradeEventBuilderService;
	private TradeEventsXMLParser tradeEventsXMLParser;

	@Mock
	private TradeEventDetailsRepository tradeEventDetailsRepo;

	@SuppressWarnings("deprecation")
	@BeforeEach 
	public void init() { 
		MockitoAnnotations.initMocks(this);
		tradeEventsXMLParser = new TradeEventsXMLParser(tradeEventDetailsRepo); 
		tradeEventBuilderService = new TradeEventBuilderService(tradeEventDetailsRepo);
	}

	@Test
	public void testGetFilterdData() throws Exception {
		when(tradeEventDetailsRepo.findByCriteria()).thenReturn(filteredData());
		List<TradeEventDetails> tradeEventDetailsList =  tradeEventBuilderService.getFilteredData();
		TradeEventDetails tradeEvent = tradeEventDetailsList.get(1);
		assertEquals(2, tradeEventDetailsList.size());
		assertEquals("ANZ", tradeEvent.getBuyerReference());
		assertEquals("EMU_BANK", tradeEvent.getSellerReference());
		assertEquals("AUD", tradeEvent.getPremiumCurrency());
		assertEquals(400.00F, tradeEvent.getPremiumAmount());	
	}

	@Test 
	public void testAnagram() throws Exception { 
		TradeEventDetails tradeEventDetails= new TradeEventDetails();
		tradeEventDetails.setSellerReference("EMU");
		tradeEventDetails.setBuyerReference("UEM");
		tradeEventDetails.setPremiumCurrency("AUD");
		tradeEventDetails.setPremiumAmount(200.00F);
		assertFalse(tradeEventBuilderService.areNotAnagram(tradeEventDetails)); 
	}

	@Test 
	public void testNotAnagram() throws Exception { 
		TradeEventDetails tradeEventDetails= new TradeEventDetails();
		tradeEventDetails.setSellerReference("EMUD_BANK");
		tradeEventDetails.setBuyerReference("MUN_BAKE");
		tradeEventDetails.setPremiumCurrency("AUD");
		tradeEventDetails.setPremiumAmount(100.00F);
		assertTrue(tradeEventBuilderService.areNotAnagram(tradeEventDetails)); 
	}


	@Test 
	public void testSaveEventDetails() throws Exception{
		when(tradeEventDetailsRepo.save(any())).thenAnswer(invocation ->invocation.getArguments()[0]); 

		TradeEventDetails eventDetails = new TradeEventDetails(); 
		eventDetails.setSellerReference("EMUD_BANK");
		eventDetails.setBuyerReference("MUN_BAKE");
		eventDetails.setPremiumCurrency("AUD");
		eventDetails.setPremiumAmount(100.00F); 

		TradeEventDetails result = tradeEventsXMLParser.storeEventDetails(eventDetails);
		assertEquals(eventDetails.getBuyerReference(),result.getBuyerReference() );
		assertEquals(eventDetails.getSellerReference(), result.getSellerReference());
		assertEquals(eventDetails.getPremiumAmount(), result.getPremiumAmount());
		assertEquals(eventDetails.getPremiumCurrency(),result.getPremiumCurrency()); }


	public List<TradeEventDetails> filteredData() {
		List<TradeEventDetails> eventList = new ArrayList<>();
		TradeEventDetails eventDetails = new TradeEventDetails();
		eventDetails.setSellerReference("EMU_BANK");
		eventDetails.setBuyerReference("MUN_BAKE");
		eventDetails.setPremiumCurrency("AUD");
		eventDetails.setPremiumAmount(300.00F);
		eventList.add(eventDetails);
		TradeEventDetails eventDetails1 = new TradeEventDetails();
		eventDetails1.setSellerReference("EMU_BANK");
		eventDetails1.setBuyerReference("ANZ");
		eventDetails1.setPremiumCurrency("AUD");
		eventDetails1.setPremiumAmount(400.00F);
		eventList.add(eventDetails1);
		return eventList;
	}


}
