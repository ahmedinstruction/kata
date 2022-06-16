package com.newlight77.kata.survey.service;

import com.newlight77.kata.survey.model.Survey;

public interface SurveyService {
	/**
	 * create survey
	 * 
	 * @param survey
	 */
	void createSurvey(Survey survey);
	/**
	 * get survey by id
	 * 
	 * @param id
	 * @return
	 */
	
	
	Survey getSurvey(String id);
}
