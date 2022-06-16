package com.newlight77.kata.survey.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
    private String question;
}

