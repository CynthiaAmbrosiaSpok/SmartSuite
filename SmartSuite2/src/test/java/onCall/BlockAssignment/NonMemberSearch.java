package onCall.BlockAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_BlockAssignmentPage;
import utilityClasses.ExcelMethods;

public class NonMemberSearch extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public NonMemberSearch() {
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
		excelMethods.setSheetName("Oncall Block Assignment Search");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_BlockAssignment_NonMemberSearch(String active, String reportTitle, String groupNameSearchCriteria, String expectedGroupNameResult, String nonMemberSearchCriteria, String expectedMessageIDResult, String expectedNonMemberNameResult, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_BlockAssignment_NonMemberSearch()");
			
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
			
			//Check that the Group Name search result matches expectations
			oncall_BlockAssignmentPage.verifyGroupNameSearchResult(checkpoint, expectedGroupNameResult);
			
			//Navigate to the 'Non-Member Search' tab
			oncall_BlockAssignmentPage.clickNonMemberSearchTab();
			
			//Search with a Non-Member ID
			oncall_BlockAssignmentPage.searchNonMemberID(nonMemberSearchCriteria);
			
			//Check that the Non-Member search result matches expectations
			oncall_BlockAssignmentPage.verifyNonMemberSearchResult(checkpoint, expectedMessageIDResult, expectedNonMemberNameResult);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}