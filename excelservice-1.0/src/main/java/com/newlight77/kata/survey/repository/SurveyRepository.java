package com.newlight77.kata.survey.repository;

import com.newlight77.kata.survey.model.Survey;

public interface SurveyRepository {
	public void createSurvey(Survey survey);
    public Survey getSurvey(String id);
}
