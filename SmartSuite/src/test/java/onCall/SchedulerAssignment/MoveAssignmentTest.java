package onCall.SchedulerAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_SchedulerAssignmentPage;
import utilityClasses.ExcelMethods;

public class MoveAssignmentTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public MoveAssignmentTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		oncall_SchedulerAssignmentPage = new Oncall_SchedulerAssignmentPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallSchedulerAssignmentDataTable"));
		excelMethods.setSheetName("Oncall Scheduler Assignment");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_SchedulerAssignment_MoveAssignmentTest(String active, String reportTitle, String groupNameSearchCriteria, String groupIDSearchCriteria, String msgID, String moveLocation, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_SchedulerAssignment_MoveAssignmentTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Oncall Scheduler Assignment' tab
			mainPage.clickSchedulerAssignmentTab();
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, groupIDSearchCriteria);
			
			//Delete all existing Scheduler Assignments
			oncall_SchedulerAssignmentPage.deleteAllCurrentSchedulerAssignments();
			
			//Create a new Scheduler Assignment
			oncall_SchedulerAssignmentPage.createNewAssignment(msgID);
			
			try {
				//Locate and move a specified scheduler assignment
				if (moveLocation.equalsIgnoreCase("next shift")) {
					//Locate and move a specified scheduler assignment to the next shift
					oncall_SchedulerAssignmentPage.moveSchedulerAssignmentToNextShift();
				} else if (moveLocation.equalsIgnoreCase("next day")) {
					//Locate and move a specified scheduler assignment to the next day
					oncall_SchedulerAssignmentPage.moveSchedulerAssignmentToNextDay();
				} else {
					//Report failure to the System & Extent Report
					reportLoggerMethods.reportFailedCheckpoint("Failure: The Excel input should be 'Next Shift' or 'Next Day', and not '" + moveLocation + "'", "OncallMoveShedulerAssignmentTest");
				}
			} catch (Exception e) {
				//Report failure to the System & Extent Report
				reportLoggerMethods.reportFailedCheckpoint("Failure: Unable to move a 'Scheduler Assignment' to the next shift or day", "OncallMoveShedulerAssignmentTest");
			}
			
			//Check if the search result matches expectation(s)
			oncall_SchedulerAssignmentPage.verifyMovedSchedulerAssignment(checkpoint);
			
			//Delete all existing Scheduler Assignments
			oncall_SchedulerAssignmentPage.deleteAllCurrentSchedulerAssignments();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}