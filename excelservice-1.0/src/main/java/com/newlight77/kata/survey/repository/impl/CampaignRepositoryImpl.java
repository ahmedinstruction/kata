package com.newlight77.kata.survey.repository.impl;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.repository.CampaignRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
/**
 * 
 * @author Ahmed
 *
 */
@Component
public class CampaignRepositoryImpl implements CampaignRepository{

	private WebClient webClient;

	public CampaignRepositoryImpl(WebClient webClient) {
		this.webClient = webClient;
	}

    /**
     * create compaign
     * 
     * @param campaign
     */
    @Override
    public void createCampaign(Campaign campaign) {
        webClient.post()
                .uri("/campaigns")
                .syncBody(campaign);
    }

    /**
     * get compaign
     * 
     * @param id
     * @return
     */
    @Override
    public Campaign getCampaign(String id) {
        return webClient.get()
                .uri("/campaigns/" + id)
                .retrieve()
                .bodyToMono(Campaign.class).block();
    }
}
