package com.support.app.crm;

import java.util.List;

import com.support.app.model.SupportCase;

public abstract class AbstractCrmSystemIntegration implements CrmSystemIntegration {

	@Override
	public List<SupportCase> getSupportCases() {
		
		return null;
	}

	
}
