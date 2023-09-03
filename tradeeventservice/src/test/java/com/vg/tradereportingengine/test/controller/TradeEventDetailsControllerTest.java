package com.vg.tradereportingengine.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.vg.tradereportingengine.TradeReportingEngineApplication;
import com.vg.tradereportingengine.model.TradeEventDetails;
import com.vg.tradereportingengine.repository.TradeEventDetailsRepository;
import com.vg.tradereportingengine.service.TradeEventsByCriteriaService;

@SpringBootTest(classes = {TradeReportingEngineApplication.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
public class TradeEventDetailsControllerTest {	
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private TradeEventsByCriteriaService tradeEventsByCriteriaService;
	@Mock
	private TradeEventDetailsRepository tradeEventDetailsRepo;

	@SuppressWarnings("deprecation")
	@BeforeEach 
	public void init() { 
		MockitoAnnotations.initMocks(this);
		tradeEventsByCriteriaService = new TradeEventsByCriteriaService(tradeEventDetailsRepo);
	}


	@Test
	public void shouldReturnStatusOK() throws Exception {
		when(tradeEventsByCriteriaService.getFilteredData()).thenReturn(filteredData());
		this.mockMvc.perform(get("/v1/tradeEvents/getFilteredData")).andDo(print()).andExpect(status().isOk());
	}

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
