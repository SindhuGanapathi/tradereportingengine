package com.vg.tradereportingengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "TRADE_EVENT_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TradeEventDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;

	@Column(name = "BUYER_PARTY")
	private String buyerReference;

	@Column(name = "SELLER_PARTY")
	private String sellerReference;

	@Column(name = "PREMIUM_AMOUNT")
	private float premiumAmount;

	@Column(name = "PREMIUM_CURRENCY")
	private String premiumCurrency;

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getBuyerReference() {
		return buyerReference;
	}

	public void setBuyerReference(String buyerReference) {
		this.buyerReference = buyerReference;
	}

	public String getSellerReference() {
		return sellerReference;
	}

	public void setSellerReference(String sellerReference) {
		this.sellerReference = sellerReference;
	}

	public float getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(float premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPremiumCurrency() {
		return premiumCurrency;
	}

	public void setPremiumCurrency(String premiumCurrency) {
		this.premiumCurrency = premiumCurrency;
	}

}

