package ezNotify;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ezNotify.pageMethods.EZ_ActivateEventPage;
import ezNotify.pageMethods.EZ_CreateEventPage;
import ezNotify.pageMethods.EZ_ExistingEventPage;
import ezNotify.pageMethods.EZ_LoginPage;
import ezNotify.pageMethods.EZ_WebResponsePage;
import utilityClasses.ExcelMethods;

public class A13EZNotifyResponseTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	String ezNotifyRequestNumber;
	String webresponse = "http://10.50.132.100/webresponse/";
	
	//Constructor
	public A13EZNotifyResponseTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		ezLoginPage = new EZ_LoginPage(eDriver, reportLogger);
		ezCreateEventPage = new EZ_CreateEventPage(eDriver, reportLogger);
		ezExistingEventPage = new EZ_ExistingEventPage(eDriver, reportLogger);
		ezActivateEventPage = new EZ_ActivateEventPage(eDriver, reportLogger);
		ezWebResponsePage = new EZ_WebResponsePage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.ezNotifyDataTablePath + prop.getProperty("ezNotifyDataTable"));
		excelMethods.setSheetName("ezNotify Response");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_ezNotify_EZNotifyResponseTest(String active, String reportTitle, String eventName, String eventMessage, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String addAdditionalOptions, String eventDuration, String deviceEscalation, String recipientDeviceList, String procedureDeviceList, String viewProcedureDeviceList, String webResponseUserID, String webResponseUserPassword, String webResponseUserEncryptedPassword, String personResponding, String expectedResponse, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Encrypt the Listed Password
		webResponseUserEncryptedPassword = encryptionMethods.encryptPassword(webResponseUserPassword, iteration-1, 19, 20);
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "13_EZNotifyResponseTest");
			
			//Go to the desired website
			ezLoginPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			ezLoginPage.loginIfAble(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			// ~~~ Check Most Recent Event Template Name & ID ~~~ //
			
			//Reset the 'Most Recent Event Template' Info
			ezActivateEventPage.resetMostRecentEventTemplateInfo();
			
			//Navigate to the 'Create New Event' Page
			ezActivateEventPage.openActivateEventPage();
			
			//Performing a Search for an Event Template
			ezActivateEventPage.searchTemplates(eventName);
			
			//Retrieve the ID of the most Recently Created Event Template
			ezActivateEventPage.getMostRecentEventTemplateID(eventName);
			
			// ~~~ Create & Verify New Event ~~~ //
			
			//Retrieve the Amount of Existing ezNotify Events
			ezExistingEventPage.getExistingEventSize(eventName);
			
			//Create the ezNotify Event
			ezCreateEventPage.createEZNotifyEvent(eventName, eventMessage, shortMessage, responseType, responseTypeQuestion, selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups, recipients, addAdditionalOptions, eventDuration, deviceEscalation, recipientDeviceList, procedureDeviceList, viewProcedureDeviceList);
			
			//Verify the Newly Created ezNotify Event
			ezExistingEventPage.verifyExistingEZNotifyEvent(checkpoint, eventName, eventMessage, recipients, addAdditionalOptions, deviceEscalation, recipientDeviceList, procedureDeviceList);
			
			//Retrieve the Create ezNotify Event's Request Number
			ezNotifyRequestNumber = ezExistingEventPage.getEzNotifyEventRequestNumber();
			
			//Go to the desired website
			ezLoginPage.accessWebsite(webresponse);
			
			//Attempt to login to 'Web Response'
			ezLoginPage.loginIfAble(webResponseUserID, encryptionMethods.decryptText(webResponseUserEncryptedPassword));
			
			//Select the ezNotify Event to respond to
			ezWebResponsePage.selectWebResponse(ezNotifyRequestNumber);
			
//			//Submit an ezNotify Event Response
//			ezWebResponsePage.submitWebResponse();
//			
//			//Go to the desired website
//			ezLoginPage.accessWebsite(activeWebsite);
//			
//			//Pause the script for a bit
//			genMethods.waitForMilliseconds(3000);
//			
//			//Verify the ezNotify's Web Response
//			ezExistingEventPage.verifyResponse(checkpoint, eventName, personResponding, expectedResponse);
//			
//			// ~~~ Check Newly Created Event Template ~~~ //
//			
//			//Verify the New Event Template Info
//			ezActivateEventPage.verifyNewEventTemplateInfo(checkpoint, eventName, eventMessage, shortMessage, responseType, responseTypeQuestion, addAdditionalOptions, eventDuration, deviceEscalation, recipientDeviceList, procedureDeviceList);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}