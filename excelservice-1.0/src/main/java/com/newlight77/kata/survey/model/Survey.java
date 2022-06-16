package com.newlight77.kata.survey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Survey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
    private String sommary;
    private String client;
    private Address clientAddress;
    private List<Question> questions;
}

