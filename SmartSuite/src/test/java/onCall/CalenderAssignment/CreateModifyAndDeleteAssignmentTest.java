package onCall.CalenderAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_CalenderAssignmentPage;
import utilityClasses.ExcelMethods;

public class CreateModifyAndDeleteAssignmentTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public CreateModifyAndDeleteAssignmentTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		oncall_CalenderAssignmentPage  = new Oncall_CalenderAssignmentPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallCalenderAssignmentDataTable"));
		excelMethods.setSheetName("Create Calender Assignment");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_CalenderAssignment_CreateModifyAndDeleteAssignmentTest(String active, String reportTitle, String groupNameSearchCriteria, String messageID, String fullName, String startTime, String endTime, String timeZone, String modifiedStartTime, String modifiedEndTime, String modifiedTimeZone, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_CalenderAssignment_CreateModifyAndDeleteAssignmentTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Oncall Blocks Assignment' tab
			mainPage.clickCalenderAssignmentTab();
			
			//Search Group Name
			oncall_CalenderAssignmentPage.searchGroupName(groupNameSearchCriteria);
			
			//Click the first Group search result
			oncall_CalenderAssignmentPage.clickFirstGroupSearchResult();
			
			//Delete any/all existing Calender Assignment(s)
			oncall_CalenderAssignmentPage.deleteAllCurrentCalenderAssignments();
			
			//Create a Calender Assignment
			oncall_CalenderAssignmentPage.createCalenderAssignment(messageID, fullName, startTime, endTime, timeZone);
			
			//Check if the newly created/modified Calender Assignment matches expectation(s)
			oncall_CalenderAssignmentPage.verifyCalenderAssignment(checkpoint, messageID, fullName, startTime, endTime, timeZone);
			
			//Modify a Calender Assignment
			oncall_CalenderAssignmentPage.modifyCalenderAssignment(messageID, fullName, modifiedStartTime, modifiedEndTime, modifiedTimeZone);
			
			//Check if the newly created/modified Calender Assignment matches expectation(s)
			oncall_CalenderAssignmentPage.verifyCalenderAssignment(checkpoint, messageID, fullName, modifiedStartTime, modifiedEndTime, modifiedTimeZone);
			
			//Delete a Calender Assignment
			oncall_CalenderAssignmentPage.deleteAllCurrentCalenderAssignments();
			
			//Check if the Calender Assignment was deleted
			oncall_CalenderAssignmentPage.verifyDeletedCalenderAssignment(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}