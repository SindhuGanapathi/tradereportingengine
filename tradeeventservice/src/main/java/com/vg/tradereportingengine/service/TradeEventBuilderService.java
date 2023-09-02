package com.vg.tradereportingengine.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vg.tradereportingengine.model.TradeEventDetails;
import com.vg.tradereportingengine.repository.TradeEventDetailsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeEventBuilderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeEventBuilderService.class);
	private final TradeEventDetailsRepository eventDetailRepository;
	
	@Autowired 
	public TradeEventBuilderService(TradeEventDetailsRepository eventDetailRepository) {
		this.eventDetailRepository = eventDetailRepository; }

	/*
	 * Get the Filtered Event Details based on business criteria
	 */
	public List<TradeEventDetails> getFilteredData() { 
		LOGGER.debug("Trade Events request triggered");
		List<TradeEventDetails> eventList =
				eventDetailRepository.findByCriteria(); 
		return eventList.stream().filter(el-> areNotAnagram(el)).collect(Collectors.toList(
				)); }

	/*
	 * Seller party and buyer party must not be Anagrams
	 */
	public Boolean areNotAnagram(TradeEventDetails tradeEventDetails) {
		String buyerRef = tradeEventDetails.getBuyerReference();
		String sellerRef = tradeEventDetails.getSellerReference();
		if(buyerRef.length() != sellerRef.length()) {
			return true;
		}
		char[] buyer = buyerRef.toCharArray();
		char[] seller = sellerRef.toCharArray();
		Arrays.sort(buyer);
		Arrays.sort(buyer);
		return !Arrays.equals(buyer, seller);
	}
}
