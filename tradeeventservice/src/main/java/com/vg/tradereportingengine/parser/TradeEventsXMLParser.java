package com.vg.tradereportingengine.parser;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vg.tradereportingengine.exceptions.CustomException;
import com.vg.tradereportingengine.model.TradeEventDetails;
import com.vg.tradereportingengine.repository.TradeEventDetailsRepository;
import com.vg.tradereportingengine.service.TradeEventsByCriteriaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TradeEventsXMLParser {

	private static final String buyer_party = "//buyerPartyReference/@href";
	private static final String seller_party = "//sellerPartyReference/@href";
	private static final String premium_amount = "//paymentAmount/amount";
	private static final String premium_currency = "//paymentAmount/currency";
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeEventsXMLParser.class);
	
	private final TradeEventDetailsRepository tradeEventDetailsRepo;

	@Autowired 
	public TradeEventsXMLParser(TradeEventDetailsRepository eventDetailRepository) {
		this.tradeEventDetailsRepo = eventDetailRepository; }

	/*
	 * Parse the Trade events list of XMLs to build the model
	 */
	public void fetchTradeEvents() {
		LOGGER.debug("Trade Reporting Engine executing");
		try {
			File fileResource = ResourceUtils.getFile("./src/main/resources/eventXML");
			for (File file : Objects.requireNonNull(fileResource.listFiles())) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(file);
				doc.getDocumentElement().normalize();
				XPath xPath = XPathFactory.newInstance().newXPath();

				NodeList buyerNode = (NodeList) xPath.compile(buyer_party).evaluate(doc, XPathConstants.NODESET);
				NodeList sellerNode = (NodeList) xPath.compile(seller_party).evaluate(doc, XPathConstants.NODESET);
				NodeList amountNode = (NodeList) xPath.compile(premium_amount).evaluate(doc, XPathConstants.NODESET);
				NodeList currencyNode = (NodeList) xPath.compile(premium_currency).evaluate(doc, XPathConstants.NODESET);
				TradeEventDetails tradeEventDetails = new TradeEventDetails();
				tradeEventDetails.setBuyerReference(getContent(buyerNode));
				tradeEventDetails.setSellerReference(getContent(sellerNode));
				tradeEventDetails.setPremiumAmount(Float.parseFloat(getContent(amountNode)));
				tradeEventDetails.setPremiumCurrency(getContent(currencyNode));
				storeEventDetails(tradeEventDetails);
				LOGGER.debug("Filtered trade events stored to database");
			}
		}catch (XPathExpressionException e) {
			throw new CustomException("Error reading xml path");
		} catch (ParserConfigurationException e1) {
			throw new CustomException("Error parsing xml data");
		} catch (SAXException e) {
			throw new CustomException("Error parsing the xml data");
		} catch (IOException e) {
			throw new CustomException("Error reading xml file");
		}
	};

	/*
	 * Save the trade events to database
	 */
	public TradeEventDetails storeEventDetails(TradeEventDetails eventDetails) {
		return tradeEventDetailsRepo.save(eventDetails);	
	}

	/*
	 * Extract the content from the XML node
	 */
	private String getContent (NodeList node) {
		return node.item(0)!= null ? node.item(0).getTextContent(): null;

	}
}
