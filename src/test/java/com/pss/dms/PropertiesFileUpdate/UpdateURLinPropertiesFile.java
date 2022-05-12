package com.pss.dms.PropertiesFileUpdate;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UpdateURLinPropertiesFile {

	@Test
	@Parameters({ "ADMINURL", "DMSURL", "QMSLoginUrl", "LMSURL", "QMSRegistrationURL" })
	public static void main(String ADMINURL, String DMSURL, String QMSLoginUrl, String LMSURL,
			String QMSRegistrationURL) {
		try {
			PropertiesConfiguration adminproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\adminproperties.properties");
			adminproperties.setProperty("ADMINURL", ADMINURL);
			adminproperties.save();
			System.out.println("adminproperties.properties updated Successfully!!");

			PropertiesConfiguration dmsproperties = new PropertiesConfiguration(
					"src\\test\\java\\com\\pss\\dms\\properties\\DMSProperties.properties");
			dmsproperties.setProperty("DMSURL", DMSURL);
			dmsproperties.setProperty("QMSLoginUrl", QMSLoginUrl);
			dmsproperties.setProperty("LMSURL", LMSURL);
			dmsproperties.setProperty("QMSRegistrationURL", QMSRegistrationURL);

			dmsproperties.save();
			System.out.println("dmsproperties.properties updated Successfully!!");

		} catch (ConfigurationException e) {
			System.out.println(e.getMessage());
		}
	}

}
