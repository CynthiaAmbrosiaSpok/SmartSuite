package onCall;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_PersonAssignmentPage;
import ssWeb.pageMethods.Oncall_QuickViewPage;
import ssWeb.pageMethods.Oncall_SchedulerAssignmentPage;
import ssWeb.pageMethods.Oncall_TodayPage;
import utilityClasses.ExcelMethods;

public class SearchTodayPersonQuickViewTabsTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SearchTodayPersonQuickViewTabsTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		oncall_SchedulerAssignmentPage = new Oncall_SchedulerAssignmentPage(eDriver, reportLogger);
		oncall_PersonAssignmentPage = new Oncall_PersonAssignmentPage(eDriver, reportLogger);
		oncall_TodayPage = new Oncall_TodayPage(eDriver, reportLogger);
		oncall_QuickViewPage = new Oncall_QuickViewPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallDataTable"));
		excelMethods.setSheetName("Today Person Quick View Search");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_SearchTodayTabTest(String active, String reportTitle, String groupNameSearchCriteria, String msgID, String remark, String todaySearchGroup, String todaySearchCriteria, String todayExpectedSearchResult, String personAssignmentExpectedSearchResult, String quickViewExpectedSearchResult, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_SearchTodayTabTest()");
			
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
			
			//Delete all existing Scheduler Assignments
			oncall_SchedulerAssignmentPage.deleteAllCurrentSchedulerAssignments();
			
			//Create a new Scheduler Assignment
			oncall_SchedulerAssignmentPage.createNewAssignment(msgID, remark);
			
			//Navigate to the 'Oncall Today' tab
			mainPage.clickTodayTab();
			
			//Today Tab
			oncall_TodayPage.performSearch(todaySearchGroup, todaySearchCriteria);
			
			//Check if the search result(s) matches expectations
			oncall_TodayPage.verifyTodaySchedulerSearch(checkpoint, todayExpectedSearchResult);
			
			//Person Assignment Tab
			mainPage.clickPersonAssignmentTab();
			
			//Perform a Person Assignment search
			oncall_PersonAssignmentPage.performSearch(todaySearchCriteria);
			
			//Checking if the search result(s) matches expectation(s)
			oncall_PersonAssignmentPage.verifySearchResults(checkpoint, personAssignmentExpectedSearchResult);
			
			//Quick View Tab
			mainPage.clickQuickViewTab();
			
			//Search Button
			oncall_QuickViewPage.performSearch(todaySearchCriteria);
			
			//First Search Listing
			oncall_QuickViewPage.verifySearchResults(checkpoint, quickViewExpectedSearchResult);
			
			//Navigate to the 'Oncall Scheduler Assignment' tab
			mainPage.clickSchedulerAssignmentTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Search & select the desired group search result
			oncall_SchedulerAssignmentPage.performSearch(groupNameSearchCriteria, "");
			
			//Delete all existing Scheduler Assignments
			oncall_SchedulerAssignmentPage.deleteAllCurrentSchedulerAssignments();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}