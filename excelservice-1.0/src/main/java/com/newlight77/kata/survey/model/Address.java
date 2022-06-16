package com.newlight77.kata.survey.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String city;
}
