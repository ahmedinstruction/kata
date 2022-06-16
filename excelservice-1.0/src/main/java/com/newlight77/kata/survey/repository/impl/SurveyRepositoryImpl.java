package com.newlight77.kata.survey.repository.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.repository.SurveyRepository;

@Component
public class SurveyRepositoryImpl implements SurveyRepository{

	private WebClient webClient;
	
	public SurveyRepositoryImpl(WebClient webClient) {
		this.webClient = webClient;
	}



	@Override
	public void createSurvey(Survey survey) {
        webClient.post()
                .uri("/surveys")
                .syncBody(survey)
                .retrieve();
    }



	@Override
    public Survey getSurvey(String id) {
        return webClient.get()
                .uri("/surveys/" + id)
                .retrieve()
                .bodyToMono(Survey.class).block();
    }

}
