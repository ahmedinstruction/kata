package com.newlight77.kata.survey.service.impl;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.repository.CampaignRepository;
import com.newlight77.kata.survey.service.CompaignService;
import com.newlight77.kata.survey.service.exception.TechnicalException;
import com.newlight77.kata.survey.service.utils.ExportCompaignUtils;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Service
public class CampaignServiceImpl implements CompaignService{

  private CampaignRepository campaignRepository;


  public CampaignServiceImpl(final CampaignRepository campaignRepository) {
    this.campaignRepository = campaignRepository;
  }
  
  @Override
  public void createCampaign(Campaign campaign) {
	  campaignRepository.createCampaign(campaign);
  }
  
  @Override
  public Campaign getCampaign(String id) {
    return campaignRepository.getCampaign(id);
  }
  


}
