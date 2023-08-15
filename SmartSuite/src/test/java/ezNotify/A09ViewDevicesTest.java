package ezNotify;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ezNotify.pageMethods.EZ_CreateEventPage;
import ezNotify.pageMethods.EZ_LoginPage;
import utilityClasses.ExcelMethods;

public class A09ViewDevicesTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public A09ViewDevicesTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		ezLoginPage = new EZ_LoginPage(eDriver, reportLogger);
		ezCreateEventPage = new EZ_CreateEventPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.ezNotifyDataTablePath + prop.getProperty("ezNotifyDataTable"));
		excelMethods.setSheetName("Device List");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_ezNotify_ViewDevicesTest(String active, String reportTitle, String procedureDeviceList, String order, String deviceType, String phoneType, String pageRoute, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "09_testcases_ezNotify_ViewDevicesTest");
			
			//Go to the desired website
			ezLoginPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			ezLoginPage.loginIfAble(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigating to the 'Create New Event' Page
			ezCreateEventPage.openNewEventPage();
			
			//Navigate to the 'Advanced Options' Page
			ezCreateEventPage.openAdvancedOptionsPage();
			
			//Verify the Procedure Device List's List of Info
			ezCreateEventPage.verifyDevices(checkpoint, procedureDeviceList, order, deviceType, phoneType, pageRoute);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}