package com.newlight77.kata.survey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Campaign implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String id ;
    private String surveyId;
    private List<AddressStatus> addressStatuses;
}
