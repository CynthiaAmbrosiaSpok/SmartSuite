package onCall.BlockAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_BlockAssignmentPage;
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
		oncall_BlockAssignmentPage = new Oncall_BlockAssignmentPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallBlockAssignmentDataTable"));
		excelMethods.setSheetName("Create Block Assignment");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_BlockAssignment_CreateModifyAndDeleteAssignmentTest(String active, String reportTitle, String groupNameSearchCriteria, String messageID, String startTime, String endTime, String expectedMessageID, String expectedFullName, String expectedStartTime, String expectedEndTime, String modifiedStartTime, String modifiedEndTime, String expectedModifiedStartTime, String expectedModifiedEndTime, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_BlockAssignment_CreateModifyAndDeleteAssignmentTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Oncall Blocks Assignment' tab
			mainPage.clickBlockAssignmentTab();
			
			//Search Group Name
			oncall_BlockAssignmentPage.searchGroupName(groupNameSearchCriteria);
			
			//Click the first Group search result
			oncall_BlockAssignmentPage.clickFirstGroupSearchResult();
			
			//Delete all existing Block Assignments
			oncall_BlockAssignmentPage.deleteAllCurrentBlockAssignments();
			
			//Create a Block Assignment
			oncall_BlockAssignmentPage.createBlockAssignment(checkpoint, messageID, startTime, endTime);
			
			//Check if the newly created/modified Block Assignment matches expectation(s)
			oncall_BlockAssignmentPage.verifyBlockAssignment(checkpoint, expectedMessageID, expectedFullName, expectedStartTime, expectedEndTime);
			
			//Modify a Block Assignment
			oncall_BlockAssignmentPage.modifyBlockAssignment(modifiedStartTime, modifiedEndTime);
			
			//Check if the newly created/modified Block Assignment matches expectation(s)
			oncall_BlockAssignmentPage.verifyBlockAssignment(checkpoint, expectedMessageID, expectedFullName, expectedModifiedStartTime, expectedModifiedEndTime);

			//Delete all existing Block Assignments
			oncall_BlockAssignmentPage.deleteAllCurrentBlockAssignments();
			
			//Check if the Block Assignment was deleted
			oncall_BlockAssignmentPage.verifyDeletedBlockAssignment(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}