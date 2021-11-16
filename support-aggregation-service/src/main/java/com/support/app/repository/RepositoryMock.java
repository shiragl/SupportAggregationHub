package com.support.app.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;

import com.support.app.model.SupportCase;



public class RepositoryMock {
	
	Random randomizer = new Random();

	@Value("${dataGenerator.numberOfCases}")
	private int numberOfCases;
	
	private List<SupportCase> supportCases = new ArrayList<SupportCase>();
	
	public List<SupportCase> getSupportCases() {
		return supportCases;
	}

	private AtomicInteger caseId = new AtomicInteger(1);
	private List<String> providerIds = Arrays.asList("1234", "2345", "4554", "7653", "4873");
	private List<String> errorCodes = Arrays.asList("123", "456", "789", "357");
	private List<String> customerIds = Arrays.asList("456544", "346886", "234567", "345608", "638674");
	public enum productName { RED, GREEN, BLUE, YELLOW}
	private List<productName> productNameValues = Collections.unmodifiableList(Arrays.asList(productName.values()));
	public enum status { Open, Closed}
	private List<status> statusValues = Collections.unmodifiableList(Arrays.asList(status.values()));
			
	@PostConstruct
	public void loadCases() {
		
		IntStream.range(0, numberOfCases).forEach(i -> generateSupportCase());
	}
	
	public void generateSupportCase() {
		
		SupportCase supportCase = new SupportCase();
		supportCase.setCaseId(String.valueOf(caseId.getAndIncrement()));
		supportCase.setCustomerId(customerIds.get(randomizer.nextInt(customerIds.size())));
		supportCase.setProvider(providerIds.get(randomizer.nextInt(providerIds.size())));
		supportCase.setErrorCode(errorCodes.get(randomizer.nextInt(errorCodes.size())));
		supportCase.setCreateDate(new Date());  
		supportCase.setProductName(productNameValues.get(randomizer.nextInt(productNameValues.size())).toString());;
		supportCase.setStatus(statusValues.get(randomizer.nextInt(statusValues.size())).toString());;
		
		supportCases.add(supportCase);
	}
	
}
