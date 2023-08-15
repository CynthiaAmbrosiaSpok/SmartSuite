package onCall.SchedulerAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_SchedulerAssignmentPage;
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
		oncall_SchedulerAssignmentPage = new Oncall_SchedulerAssignmentPage(eDriver, reportLogger);
	}
	
	//Setup variable(s) and other info for the class
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallSchedulerAssignmentDataTable"));
		excelMethods.setSheetName("Create Scheduler Assignment");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_SchedulerAssignment_CreateModifyAndDeleteAssignmentTest(String active, String reportTitle, String groupNameSearchCriteria, String groupIDSearchCriteria, String msgID, String remark, String modifyRemark, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_SchedulerAssignment_CreateModifyAndDeleteAssignmentTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(300);
			
			//Navigate to the 'Oncall Scheduler Assignment' tab
			mainPage.clickSchedulerAssignmentTab();
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, groupIDSearchCriteria);
			
			//Delete all existing Scheduler Assignments
			oncall_SchedulerAssignmentPage.deleteAllCurrentSchedulerAssignments();
			
			//Create a new Scheduler Assignment
			oncall_SchedulerAssignmentPage.createNewAssignment(msgID, remark);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Check if the newly created Scheduler Assignment matches expectation(s)
			oncall_SchedulerAssignmentPage.verifySchedulerAssignment(checkpoint, msgID, remark);
			
			//Modify a Scheduler Assignment
			oncall_SchedulerAssignmentPage.modifyAssignment(modifyRemark);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Check if the newly modified Scheduler Assignment matches expectation(s)
			oncall_SchedulerAssignmentPage.verifySchedulerAssignment(checkpoint, msgID, modifyRemark);
			
			//Delete all existing Scheduler Assignments
			oncall_SchedulerAssignmentPage.deleteAllCurrentSchedulerAssignments();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Check if the scheduler assignment was deleted
			oncall_SchedulerAssignmentPage.verifyDeletedSchedulerAssignment(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}