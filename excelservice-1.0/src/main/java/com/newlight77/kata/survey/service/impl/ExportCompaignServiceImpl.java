package com.newlight77.kata.survey.service.impl;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.ExportCompaignService;
import com.newlight77.kata.survey.service.exception.TechnicalException;
import com.newlight77.kata.survey.service.utils.ExportCompaignUtils;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;


@Service
public class ExportCompaignServiceImpl implements ExportCompaignService {
	
  private ExportCompaignUtils exportCompaignUtilsImpl;

  public ExportCompaignServiceImpl(ExportCompaignUtils exportCompaignUtilsImpl) {
    this.exportCompaignUtilsImpl = exportCompaignUtilsImpl;
  }

  @Override
  public void exportCompaign(Campaign campaign, Survey survey) throws TechnicalException {
	Workbook workbook = exportCompaignUtilsImpl.exportCompainWorkBook(campaign, survey);
	exportCompaignUtilsImpl.writeFileAndSend(survey, workbook);

  }


}
