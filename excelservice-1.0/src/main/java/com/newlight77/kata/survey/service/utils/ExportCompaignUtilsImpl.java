package com.newlight77.kata.survey.service.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.newlight77.kata.survey.config.MailServiceConfig;
import com.newlight77.kata.survey.model.AddressStatus;
import com.newlight77.kata.survey.model.Campaign;
import com.newlight77.kata.survey.model.Survey;
import com.newlight77.kata.survey.service.exception.TechnicalException;

@Component
public class ExportCompaignUtilsImpl implements ExportCompaignUtils {
	private static final Logger logger = LoggerFactory.getLogger(ExportCompaignUtilsImpl.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private JavaMailSender mailSender;
	private MailServiceConfig mailServiceConfig;
	
	public ExportCompaignUtilsImpl(JavaMailSender mailSender, MailServiceConfig mailServiceConfig) {
		this.mailSender = mailSender;
		this.mailServiceConfig = mailServiceConfig;
	}

	/**
	 * export CompainWorkBook 
	 * 
	 * @param campaign
	 * @param survey
	 */
	@Override
	public Workbook exportCompainWorkBook(Campaign campaign, Survey survey) {
		Workbook workbook = new XSSFWorkbook();

	    Sheet sheet = workbook.createSheet("Survey");
	    sheet.setColumnWidth(0, 10500);
	    for (int i = 1; i <= 18; i++) {
	      sheet.setColumnWidth(i, 6000);
	    }

	    // 1ere ligne =  l'entête
	    Row header = sheet.createRow(0);

	    CellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
	    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
	    font.setFontName("Arial");
	    font.setFontHeightInPoints((short) 14);
	    font.setBold(true);
	    headerStyle.setFont(font);
	    headerStyle.setWrapText(false);

	    Cell headerCell = header.createCell(0);
	    headerCell.setCellValue("Survey");
	    headerCell.setCellStyle(headerStyle);

	    CellStyle titleStyle = workbook.createCellStyle();
	    titleStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    XSSFFont titleFont = ((XSSFWorkbook) workbook).createFont();
	    titleFont.setFontName("Arial");
	    titleFont.setFontHeightInPoints((short) 12);
	    titleFont.setUnderline(FontUnderline.SINGLE);
	    titleStyle.setFont(titleFont);

	    CellStyle style = workbook.createCellStyle();
	    style.setWrapText(true);

	    // section client
	    Row row = sheet.createRow(2);
	    Cell cell = row.createCell(0);
	    cell.setCellValue("Client");
	    cell.setCellStyle(titleStyle);

	    Row clientRow = sheet.createRow(3);
	    Cell nomClientRowLabel = clientRow.createCell(0);
	    nomClientRowLabel.setCellValue(survey.getClient());
	    nomClientRowLabel.setCellStyle(style);

	    String clientAddress = survey.getClientAddress().getStreetNumber() + " "
	            + survey.getClientAddress().getStreetName() + survey.getClientAddress().getPostalCode() + " "
	            + survey.getClientAddress().getCity();

	    Row clientAddressLabelRow = sheet.createRow(4);
	    Cell clientAddressCell = clientAddressLabelRow.createCell(0);
	    clientAddressCell.setCellValue(clientAddress);
	    clientAddressCell.setCellStyle(style);

	    row = sheet.createRow(6);
	    cell = row.createCell(0);
	    cell.setCellValue("Number of surveys");
	    cell = row.createCell(1);
	    cell.setCellValue(campaign.getAddressStatuses().size());

	    Row surveyLabelRow = sheet.createRow(8);
	    Cell surveyLabelCell = surveyLabelRow.createCell(0);
	    surveyLabelCell.setCellValue("N° street");
	    surveyLabelCell.setCellStyle(style);

	    surveyLabelCell = surveyLabelRow.createCell(1);
	    surveyLabelCell.setCellValue("streee");
	    surveyLabelCell.setCellStyle(style);

	    surveyLabelCell = surveyLabelRow.createCell(2);
	    surveyLabelCell.setCellValue("Postal code");
	    surveyLabelCell.setCellStyle(style);

	    surveyLabelCell = surveyLabelRow.createCell(3);
	    surveyLabelCell.setCellValue("City");
	    surveyLabelCell.setCellStyle(style);

	    surveyLabelCell = surveyLabelRow.createCell(4);
	    surveyLabelCell.setCellValue("Status");
	    surveyLabelCell.setCellStyle(style);

	    int startIndex = 9;
	    int currentIndex = 0;

	    for (AddressStatus addressStatus : campaign.getAddressStatuses()) {

	      Row surveyRow = sheet.createRow(startIndex + currentIndex);
	      Cell surveyRowCell = surveyRow.createCell(0);
	      surveyRowCell.setCellValue(addressStatus.getAddress().getStreetNumber());
	      surveyRowCell.setCellStyle(style);

	      surveyRowCell = surveyRow.createCell(1);
	      surveyRowCell.setCellValue(addressStatus.getAddress().getStreetName());
	      surveyRowCell.setCellStyle(style);

	      surveyRowCell = surveyRow.createCell(2);
	      surveyRowCell.setCellValue(addressStatus.getAddress().getPostalCode());
	      surveyRowCell.setCellStyle(style);

	      surveyRowCell = surveyRow.createCell(3);
	      surveyRowCell.setCellValue(addressStatus.getAddress().getCity());
	      surveyRowCell.setCellStyle(style);

	      surveyRowCell = surveyRow.createCell(4);
	      surveyRowCell.setCellValue(addressStatus.getStatus().toString());
	      surveyRowCell.setCellStyle(style);

	      currentIndex++;

	    }
		return workbook;
	}
	
	
	  /**
	   * send mail file
	   * 
	   * @param survey
	   * @param workbook
	   */
		@Override
		public void writeFileAndSend(Survey survey, Workbook workbook) throws TechnicalException {
			try {
			      File resultFile = new File(System.getProperty("java.io.tmpdir"), "survey-" + survey.getId() + "-" + dateTimeFormatter.format(LocalDate.now()) + ".xlsx");
			      FileOutputStream outputStream = new FileOutputStream(resultFile);
			      workbook.write(outputStream);
			      send(resultFile);
			      resultFile.deleteOnExit();
			    } catch(Exception ex) {
			        throw new TechnicalException("Error while trying to send email");
			    } finally {
			        try {
						workbook.close();
					} catch (Exception e) {
						throw new TechnicalException("Error in reading file");
					}
			    }
			
		}

	  public void send (File attachment) {
	    MimeMessagePreparator messagePreparator = mimeMessage -> {
	      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
	      messageHelper.setFrom(mailServiceConfig.getFrom());
	      messageHelper.setTo(mailServiceConfig.getTo());
	      messageHelper.setSubject("Campaign Results");
	      messageHelper.setText("Hi,\n\nYou will find in the attached file the campaign results.");

	      FileSystemResource file = new FileSystemResource(attachment);
	      messageHelper.addAttachment(file.getFilename(), file);
	    };
	    try {
	      mailSender.send(messagePreparator);
	      logger.info("Email sent successfully");
	    } catch (MailException e) {
	      throw new RuntimeException("An error occurred while sending an email", e);
	    }
	  }


}
