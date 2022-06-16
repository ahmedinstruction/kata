package com.newlight77.kata.survey.repository;

import com.newlight77.kata.survey.model.Campaign;

public interface CampaignRepository {
	/**
	 * create campaign
	 * 
	 * @param campaign
	 */
    public void createCampaign(Campaign campaign);

    /**
     * get compain
     * 
     * @param id
     * @return
     */
    public Campaign getCampaign(String id);
}
