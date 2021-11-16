package com.support.app.crm;

import java.util.List;

import com.support.app.model.SupportCase;

public interface CrmSystemIntegration {
	
	// Here I connect to the crm system in a scheduled job, get the cases and add to my repository.
	
	public List<SupportCase> getSupportCases();
	
}
