package utilityClasses;

import java.io.File;

public class ConstantVariables {
	//Drivers Path
	public String firefoxDriverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "drivers" + File.separator + "geckodriver";
	
	//Properties File Location
	public String propertiesFileLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "config" + File.separator + "config.properties";
	
	//Screenshot location
	public String screenshotLocation = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
	
	//Report Location
	public String extentFactoryReportLocation = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "TestSuiteResults.html";
	
	//DataTable
	public String smartSuiteDataTablePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "ssWeb" + File.separator + "testdata" + File.separator;
	public String ezNotifyDataTablePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "ezNotify" + File.separator + "testdata" + File.separator;
	
}