package com.vg.tradereportingengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vg.tradereportingengine.parser.TradeEventsXMLParser;

@SpringBootApplication (scanBasePackages = { "com.vg.tradereportingengine" })
@EntityScan("com.vg.tradereportingengine.model")
@EnableJpaRepositories("com.vg.tradereportingengine.repository")	
@EnableAutoConfiguration
@EnableTransactionManagement
public class TradeReportingEngineApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TradeReportingEngineApplication.class, args);
		TradeEventsXMLParser service = applicationContext.getBean(TradeEventsXMLParser.class);
		service.fetchTradeEvents();
	}
}
