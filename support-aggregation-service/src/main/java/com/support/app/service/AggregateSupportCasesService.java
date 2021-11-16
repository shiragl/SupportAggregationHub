package com.support.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.support.app.model.AggregatedSupportCases;
import com.support.app.model.SupportCase;
import com.support.app.model.SupportCaseStatusEnum;
import com.support.app.repository.SupportCasesRepository;

@Service
public class AggregateSupportCasesService {

	@Autowired
	SupportCasesRepository suportCasesRepo;
	
	private List<AggregatedSupportCases> aggregatedSupportCasesList;
	
	public List<AggregatedSupportCases> getAggregatedSupportCases(String providerId, String errorCode, SupportCaseStatusEnum status) {
		
		List<SupportCase> supportCases;

		supportCases = suportCasesRepo.getCasesByParameters(providerId, errorCode, status);
		aggregateSupportCases(supportCases);
		
		return aggregatedSupportCasesList;
	}
	
	
	private void aggregateSupportCases(List<SupportCase> supportCases) {
		
		Map<String, Map<String, List<SupportCase>>> aggregatedSupportCasesMap = new HashMap<String, Map<String, List<SupportCase>>>();
		for (SupportCase supportCase:supportCases) {
			String provider = supportCase.getProvider();
			if (aggregatedSupportCasesMap.containsKey(provider)){
				String errorCode = supportCase.getErrorCode();
				if (aggregatedSupportCasesMap.get(provider).containsKey(errorCode)) {
					aggregatedSupportCasesMap.get(provider).get(errorCode).add(supportCase);
				} else {
					aggregatedSupportCasesMap.get(provider).put(errorCode, new ArrayList<SupportCase>() {{add(supportCase);}});
				}
			} else {
				aggregatedSupportCasesMap.put(provider, new HashMap<String, List<SupportCase>>(){{ put(supportCase.getErrorCode(), new ArrayList<SupportCase>() {{add(supportCase);}});}});
			}
		}
		
		buildAggregatedSupportCasesList(aggregatedSupportCasesMap);
		
	}
	
	private void buildAggregatedSupportCasesList(Map<String, Map<String, List<SupportCase>>> aggregatedSupportCasesMap) {
		
		List<AggregatedSupportCases> ascList = new ArrayList<AggregatedSupportCases>();
		
		 for (Map.Entry<String, Map<String, List<SupportCase>>> providerEntry : aggregatedSupportCasesMap.entrySet()) {
			 	 
			 for (Map.Entry<String, List<SupportCase>> errorEntry : providerEntry.getValue().entrySet()) {
				 AggregatedSupportCases aggregatedSupportCases = new AggregatedSupportCases();
				 aggregatedSupportCases.setProviderId(providerEntry.getKey());
				 aggregatedSupportCases.setErrorCode(errorEntry.getKey());
				 aggregatedSupportCases.setAggregatedCasesList(errorEntry.getValue());
				 
				 ascList.add(aggregatedSupportCases);
			 }
			 	 	 
		 }
		 
		 aggregatedSupportCasesList = ascList;
		
	}


	
}
