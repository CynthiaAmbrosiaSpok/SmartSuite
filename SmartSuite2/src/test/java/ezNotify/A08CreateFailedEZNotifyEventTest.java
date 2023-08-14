package ezNotify;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ezNotify.pageMethods.EZ_ActivateEventPage;
import ezNotify.pageMethods.EZ_CreateEventPage;
import ezNotify.pageMethods.EZ_ExistingEventPage;
import ezNotify.pageMethods.EZ_LoginPage;
import utilityClasses.ExcelMethods;

public class A08CreateFailedEZNotifyEventTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public A08CreateFailedEZNotifyEventTest() {
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
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.ezNotifyDataTablePath + prop.getProperty("ezNotifyDataTable"));
		excelMethods.setSheetName("Create Failed Event");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_ezNotify_CreateFailedEZNotifyEventTest(String active, String reportTitle, String eventName, String eventMessage, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String expectedErrorMessage, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "08_testcases_ezNotify_CreateFailedEZNotifyEventTest");
			
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
			ezCreateEventPage.createFailedEZNotifyEvent(checkpoint, eventName, eventMessage, shortMessage, responseType, responseTypeQuestion, selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups, recipients, expectedErrorMessage);
			
			//Verify the Newly Created ezNotify Event
			ezExistingEventPage.verifyExistingEZNotifyEvent(checkpoint, eventName, eventMessage, recipients, "", "", "", "");
			
			// ~~~ Check Newly Created Event Template ~~~ //
			
			//Verify the New Event Template Info
			ezActivateEventPage.verifyNewEventTemplateInfo(checkpoint, eventName, eventMessage, shortMessage, responseType, responseTypeQuestion, "", "", "", "", "");
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}