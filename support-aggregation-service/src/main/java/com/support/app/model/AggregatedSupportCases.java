package com.support.app.model;

import java.util.List;

public class AggregatedSupportCases {
	
	private String errorCode;
	private String providerId;
	private List<SupportCase> aggregatedCasesList;
	

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public List<SupportCase> getAggregatedCasesList() {
		return aggregatedCasesList;
	}

	public void setAggregatedCasesList(List<SupportCase> aggregatedCasesList) {
		this.aggregatedCasesList = aggregatedCasesList;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
