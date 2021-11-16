package com.support.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.support.app.model.SupportCase;
import com.support.app.model.SupportCaseStatusEnum;

public class SupportCasesRepository {

	// Will connect to the DB and get support cases according to specific parameters
	
	@Autowired
	RepositoryMock repositoryMock;
	
	public List<SupportCase> getAllCases(){
		return repositoryMock.getSupportCases();
	}
		
	public List<SupportCase> getCasesByParameters(String providerId, String errorCode, SupportCaseStatusEnum status){
		List<SupportCase> allSupportCases =  repositoryMock.getSupportCases();
		List<SupportCase> filteredSupportCases = new ArrayList<SupportCase>();
		
		if (providerId.isEmpty() && errorCode.isEmpty() && status==null) {
			return allSupportCases;
		}
		for (SupportCase supportCase:allSupportCases) {
			if (!providerId.isEmpty() && !supportCase.getProvider().equals(providerId)) {
				continue;
			}
			if (!errorCode.isEmpty() && !supportCase.getErrorCode().equals(errorCode)) {
				continue;
			}
			if (status!= null && status!=SupportCaseStatusEnum.All && !status.name().equals(supportCase.getStatus())) {
				continue;
			}
			filteredSupportCases.add(supportCase);
		}
		
		return filteredSupportCases;	
	}
		
}
