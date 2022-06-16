package com.newlight77.kata.survey.service;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.exception.TechnicalException;
/**
 * @author AHC
 *
 */
public interface CompaignService {

	/**
	 * create compain
	 * 
	 * @param campaign
	 */
	void createCampaign(Campaign campaign);
	
	/**
	 * get compain by id
	 * 
	 * @param id
	 * @return
	 */
	Campaign getCampaign(String id);
	
	
}
