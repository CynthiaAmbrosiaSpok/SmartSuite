package onCall.History;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_HistoryPage;
import utilityClasses.ExcelMethods;

public class SearchTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SearchTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		oncall_HistoryPage = new Oncall_HistoryPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallHistoryDataTable"));
		excelMethods.setSheetName("Oncall History Search");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_History_SearchTest(String active, String reportTitle, String startDateYearSearchCriteria, String expectedGroupNameSearchResult, String expectedStartDateSearchResult, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_History_SearchTest");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Oncall History' tab
			mainPage.clickHistoryTab();
			
			//Clear the existing search criteria
			oncall_HistoryPage.clearSearchResults();
			
			//Search with the 'Start Date Year' as criteria
			oncall_HistoryPage.searchStartDateYear(startDateYearSearchCriteria);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(2000);
			
			//Check if the search result matches expectation(s)
			oncall_HistoryPage.verifySearchResults(checkpoint, expectedGroupNameSearchResult, expectedStartDateSearchResult);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}