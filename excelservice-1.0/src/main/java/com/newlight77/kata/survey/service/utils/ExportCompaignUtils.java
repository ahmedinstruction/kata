package com.newlight77.kata.survey.service.utils;

import org.apache.poi.ss.usermodel.Workbook;

import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.exception.TechnicalException;

public interface ExportCompaignUtils {

	Workbook exportCompainWorkBook(Campaign campaign, Survey survey);
	
	void writeFileAndSend(Survey survey, Workbook workbook) throws TechnicalException;
}
