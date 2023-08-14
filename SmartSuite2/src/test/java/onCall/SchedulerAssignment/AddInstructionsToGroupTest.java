package onCall.SchedulerAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_SchedulerAssignmentPage;
import utilityClasses.ExcelMethods;

public class AddInstructionsToGroupTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AddInstructionsToGroupTest() {
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
		excelMethods.setSheetName("Add Instructions");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_SchedulerAssignment_AddInstructionsToGroupTest(String active, String reportTitle, String groupNameSearchCriteria, String createInstructions, String modifyInstructions, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_SchedulerAssignment_AddInstructionsToGroupTest()");
			
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
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, "");
			
			//Delete Scheduler Assignment Instructions
			oncall_SchedulerAssignmentPage.deleteInstructions();
			
			//Refresh the 'Scheduler Assignment' page
			mainPage.refreshSchedulerAssignmentPage();
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, "");
			
			//Create Scheduler Assignment Instructions
			oncall_SchedulerAssignmentPage.addInstructions(createInstructions);
			
			//Refresh the 'Scheduler Assignment' page
			mainPage.refreshSchedulerAssignmentPage();
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, "");
			
			//Check if the existing Instructions match expectations
			oncall_SchedulerAssignmentPage.verifyExistingInstructions(checkpoint, createInstructions);
			
			//Modify Scheduler Assignment Instructions
			oncall_SchedulerAssignmentPage.addInstructions(modifyInstructions);
			
			//Refresh the 'Scheduler Assignment' page
			mainPage.refreshSchedulerAssignmentPage();
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, "");
			
			//Check if the existing Instructions match expectations
			oncall_SchedulerAssignmentPage.verifyExistingInstructions(checkpoint, modifyInstructions);
			
			//Delete Scheduler Assignment Instructions
			oncall_SchedulerAssignmentPage.deleteInstructions();
			
			//Refresh the 'Scheduler Assignment' page
			mainPage.refreshSchedulerAssignmentPage();
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, "");
			
			//Check if the Instructions were deleted
			oncall_SchedulerAssignmentPage.verifyDeletedInstructions(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}