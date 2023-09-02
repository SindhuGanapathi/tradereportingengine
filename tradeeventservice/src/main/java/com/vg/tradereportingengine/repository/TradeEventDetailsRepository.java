package com.vg.tradereportingengine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vg.tradereportingengine.model.TradeEventDetails;

@Repository
public interface TradeEventDetailsRepository extends CrudRepository<TradeEventDetails, Integer> {

	@Query(nativeQuery = true, value="SELECT * FROM TRADE_EVENT_DETAILS ed WHERE (ed.SELLER_PARTY = 'EMU_BANK' AND ed.PREMIUM_CURRENCY='AUD') or (ed.SELLER_PARTY = 'BISON_BANK' AND ed.PREMIUM_CURRENCY='USD')"
			) 
	List<TradeEventDetails> findByCriteria();
}