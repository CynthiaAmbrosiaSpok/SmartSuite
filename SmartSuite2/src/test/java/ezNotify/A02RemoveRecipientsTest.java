package ezNotify;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ezNotify.pageMethods.EZ_CreateEventPage;
import ezNotify.pageMethods.EZ_LoginPage;
import utilityClasses.ExcelMethods;

public class A02RemoveRecipientsTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public A02RemoveRecipientsTest() {
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
		excelMethods.setSheetName("Remove Recipients");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_ezNotify_RemoveRecipientsTest(String active, String reportTitle, String selectRepicientsIndividuals, String selectRepicientsConfirmation, String selectRepicientsYesNoQuestion, String recipients, String removeMethod, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "02_testcases_ezNotify_RemoveRecipientsTest");
			
			//Go to the desired website
			ezLoginPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			ezLoginPage.loginIfAble(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Create New Event' Page
			ezCreateEventPage.openNewEventPage();
			
			//Check the desired 'Select Recipients' checkboxes
			ezCreateEventPage.checkSelectRecipientsCheckboxes(selectRepicientsIndividuals, selectRepicientsConfirmation, selectRepicientsYesNoQuestion);
			
			//Select the desired Recipients
			ezCreateEventPage.selectRecipients(recipients);
			
			//Remove the Recipients via the Specified Method
			if (removeMethod.equalsIgnoreCase("each")) {
				//Removing Recipients from the 'Added Recipients' List
				ezCreateEventPage.verifyRemovingEachRecipient(checkpoint, recipients);
			} else if (removeMethod.equalsIgnoreCase("all")) {
				//Removing Recipients from the 'Added Recipients' List
				ezCreateEventPage.verifyRemovingAllRecipients(checkpoint, recipients);
			} else {
				//Assert the Specified Removal Method
				checkpoint.assertEquals(removeMethod, "Expected the specified Removal Method of 'Each' or 'All'");
				
				//Check the Specified Removal Method
				reportLoggerMethods.reportFailedCheckpoint("Failure: Expected input of 'Each' or 'All' for the method of removing recipients", "testcases_ezNotify_RemoveRecipientsTest");
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}