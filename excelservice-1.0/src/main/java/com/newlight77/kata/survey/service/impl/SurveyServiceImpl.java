package com.newlight77.kata.survey.service.impl;

import org.springframework.stereotype.Service;

import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.repository.SurveyRepository;
import com.newlight77.kata.survey.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{
	private SurveyRepository surveyRepository;
	
	  public SurveyServiceImpl(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}

	@Override
	  public void createSurvey(Survey survey) {
		  surveyRepository.createSurvey(survey);
	  }
	  
	  @Override
	  public Survey getSurvey(String id) {
	    return surveyRepository.getSurvey(id);
	  }
}
