package com.newlight77.kata.survey.controler;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.CompaignService;
import com.newlight77.kata.survey.service.ExportCompaignService;
import com.newlight77.kata.survey.service.SurveyService;
import com.newlight77.kata.survey.service.exception.TechnicalException;

import org.springframework.web.bind.annotation.*;

@RestController
public class SurveyController {

    private CompaignService campaignService;
    private SurveyService surveyService;
    private ExportCompaignService exportCompaignService;
        
    public SurveyController(final CompaignService campaignService, SurveyService surveyService, ExportCompaignService exportCompaignService) {
      this.campaignService = campaignService;
      this.surveyService = surveyService;
      this.exportCompaignService = exportCompaignService;
    }

    @RequestMapping(value = "/api/survey/create", method = RequestMethod.POST)
    public void createSurvey(@RequestBody Survey survey) {
    	surveyService.createSurvey(survey);
    }

    @RequestMapping(value = "/api/survey/get", method = RequestMethod.GET)
    public Survey getSurvey(@RequestParam String id) {
        return surveyService.getSurvey(id);
    }

    @RequestMapping(value = "/api/survey/campaign/create", method = RequestMethod.POST)
    public void createCampaign(@RequestBody Campaign campaign) {
    	campaignService.createCampaign(campaign);
    }

    @RequestMapping(value = "/api/survey/campaign/get", method = RequestMethod.GET)
    public Campaign getCampaign(@RequestParam String id) {
        return campaignService.getCampaign(id);
    }

    @RequestMapping(value = "/api/survey/campaign/export", method = RequestMethod.POST)
    public void exportCampaign(@RequestParam String campaignId) throws TechnicalException {

        Campaign campaign = campaignService.getCampaign(campaignId);
        Survey survey = surveyService.getSurvey(campaign.getSurveyId());
        exportCompaignService.exportCompaign(campaign, survey);
        
    }
}

