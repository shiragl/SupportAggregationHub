package com.support.app.client;

import java.util.stream.Collectors;

import com.support.app.model.AggregatedSupportCases;
import com.support.app.model.SupportCaseStatusEnum;
import com.support.app.service.AggregateSupportCasesService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Support Aggregation Hub")
@Route(value="")
public class SupportAggregationUi extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	TextField errorCode = new TextField();
	TextField providerName = new TextField();
	Button submit = new Button("Submit");
	ComboBox<SupportCaseStatusEnum> statusComboBox = new ComboBox<SupportCaseStatusEnum>();
	Grid<AggregatedSupportCases> grid = new Grid<>(AggregatedSupportCases.class);
	
	private AggregateSupportCasesService ascService;
		
	public SupportAggregationUi(AggregateSupportCasesService ascService) {
		
		this.ascService = ascService;
		setSizeFull();
		setGrid();
			
		add(getFilters(), grid);
		
		updateGrid();
	
	}
	
	public void updateGrid() {
		grid.setItems(ascService.getAggregatedSupportCases(providerName.getValue(), errorCode.getValue(), statusComboBox.getValue()));
	}
	
	public Component getFilters() {
		errorCode.setPlaceholder("Enter Error Code...");
		errorCode.setClearButtonVisible(true);
		errorCode.setValueChangeMode(ValueChangeMode.LAZY);
		providerName.setPlaceholder("Enter Provider Name...");
		providerName.setClearButtonVisible(true);
		providerName.setValueChangeMode(ValueChangeMode.LAZY);
		statusComboBox.setPlaceholder("Status...");
		statusComboBox.setItems(SupportCaseStatusEnum.values());
		statusComboBox.setClearButtonVisible(true);
		submit.addClickListener(e -> updateGrid());
		HorizontalLayout hl = new HorizontalLayout(errorCode, providerName, statusComboBox, submit);
		
		return hl;
	}
	
	public void setGrid(){
		grid.setSizeFull();
		grid.setColumns("errorCode", "providerId");
		grid.addColumn(aggregatedSupportCase -> aggregatedSupportCase.getAggregatedCasesList().stream().map(supportCase -> supportCase.getProductName()).collect(Collectors.joining(","))).setHeader("Products affected");
		grid.addColumn(aggregatedSupportCase -> aggregatedSupportCase.getAggregatedCasesList().size()).setHeader("Number of support cases");
		grid.addColumn(aggregatedSupportCase -> aggregatedSupportCase.getAggregatedCasesList().stream().map(supportCase -> supportCase.getCaseId()).collect(Collectors.joining(","))).setHeader("List of support cases");
		
		grid.getColumns().forEach(col -> col.setAutoWidth(true));
	}

}
