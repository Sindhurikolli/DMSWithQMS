package com.pss.dms.PropertiesFileUpdate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UpdateDMSProperties {

	@Test
	@Parameters({ "DOCUMENT_NAME_SOP_DOCT_REQUEST", "DocumentType", "ProductCode", "DOCT_NO_INITIATE_NEW_DOCT_SOP" })
	public static void NewDocument(String DOCUMENT_NAME_SOP_DOCT_REQUEST, String DocumentType, String ProductCode,
			String DOCT_NO_INITIATE_NEW_DOCT_SOP) {
		try {

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
			dmsproperties.setProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST", DOCUMENT_NAME_SOP_DOCT_REQUEST + timeStamp);
			dmsproperties.setProperty("DocumentType", DocumentType);
			dmsproperties.setProperty("ProductCode", ProductCode);
			dmsproperties.setProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP", DOCT_NO_INITIATE_NEW_DOCT_SOP + timeStamp);
			dmsproperties.setProperty("DocumentVersion", "1.0");
			/* dmsproperties.setProperty("LMSDocumentVersion", "-1.0"); */

			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Parameters({ "DocumentType", "ProductCode" })
	public static void ChangeDocument(String DocumentType, String ProductCode) {
		try {

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			dmsproperties.setProperty("DocumentType", DocumentType);
			dmsproperties.setProperty("ProductCode", ProductCode);
			String timeStamp = new SimpleDateFormat("ddHHmmss").format(new Date());
			String ChangeDoc_QtionBank = "Change";
			dmsproperties.setProperty("ChangeDoc_QtionBank", ChangeDoc_QtionBank + timeStamp);
			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Parameters({ "DocumentType", "ProductCode" })
	public static void TerminateDocument(String DocumentType, String ProductCode) {
		try {

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			dmsproperties.setProperty("DocumentType", DocumentType);
			dmsproperties.setProperty("ProductCode", ProductCode);
			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Parameters({ "ADHOC_SCHEDULE_NAME" })
	public static void ScheduleName(String ADHOC_SCHEDULE_NAME) {
		try {

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
			dmsproperties.setProperty("ADHOC_SCHEDULE_NAME", ADHOC_SCHEDULE_NAME + timeStamp);
			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Parameters({ "DOCUMENT_NAME_SOP_DOCT_REQUEST", "DocumentType", "DOCT_NO_INITIATE_NEW_DOCT_SOP" })
	public static void NewLMSDocument(String DOCUMENT_NAME_SOP_DOCT_REQUEST, String DocumentType,String DOCT_NO_INITIATE_NEW_DOCT_SOP) {
		try {

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
			dmsproperties.setProperty("DocumentName", DOCUMENT_NAME_SOP_DOCT_REQUEST + timeStamp);
			dmsproperties.setProperty("DocTypeLMS", DocumentType);
			dmsproperties.setProperty("DocumentNumber", DOCT_NO_INITIATE_NEW_DOCT_SOP + timeStamp);
//			dmsproperties.setProperty("VersionNo", "1.0");
			dmsproperties.setProperty("LMSDocumentVersion", "1.0"); 

			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}
}
