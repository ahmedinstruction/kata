package com.newlight77.kata.survey.service;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.exception.TechnicalException;

public interface ExportCompaignService {
    /**
     * sent results
     * @param campaign
     * @param survey
     * @throws TechnicalException
     */
	public void exportCompaign(Campaign campaign, Survey survey) throws TechnicalException;

	
}
