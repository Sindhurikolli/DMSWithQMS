package com.pss.dms.LMSRejectionFlow;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PropertiesFileUpdateLMSDocdetails {

	@Test

	public static void main() throws IOException {
		try {

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			Properties p = new Properties();
			FileReader reader = new FileReader("src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			p.load(reader);
			System.out.println(p.getProperty("user"));
			String name = p.getProperty("DocumentName");
			String number = p.getProperty("DocumentNumber");

			dmsproperties.setProperty("DOCUMENT_NAME_SOP_DOCT_REQUEST", name);
			dmsproperties.setProperty("DOCT_NO_INITIATE_NEW_DOCT_SOP", number);
			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

}
