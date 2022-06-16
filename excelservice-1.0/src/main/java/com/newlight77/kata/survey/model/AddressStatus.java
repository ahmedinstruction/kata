package com.newlight77.kata.survey.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressStatus implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String id;
    private Address address;
    private Status status;
}
