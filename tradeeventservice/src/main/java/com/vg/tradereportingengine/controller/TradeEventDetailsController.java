package com.vg.tradereportingengine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vg.tradereportingengine.model.TradeEventDetails;
import com.vg.tradereportingengine.service.TradeEventBuilderService;

@RestController
@RequestMapping("/v1/tradeEvents") 
public class TradeEventDetailsController {
	@Autowired 
	private TradeEventBuilderService tradeEventBuilderService;

	@GetMapping(path = "/getFilteredData")
	public ResponseEntity<List<TradeEventDetails>>
	getTradeEventsByCriteria(){
		List<TradeEventDetails> response = tradeEventBuilderService.getFilteredData(); 
		return ResponseEntity.ok(response);
	}
}
