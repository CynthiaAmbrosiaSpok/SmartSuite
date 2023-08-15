package onCall.Search;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_SearchPage;
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
		oncall_SearchPage = new Oncall_SearchPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallSearchDataTable"));
		excelMethods.setSheetName("Oncall Search");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_Search_SearchTest(String active, String reportTitle, String groupNameSearchCriteria, String groupIDSearchCriteria, String expectedSearchResult, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_Search_SearchTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Oncall Search' tab
			mainPage.clickSearchTab();
			
			//Clear the existing search criteria
			oncall_SearchPage.clearSearchResults();
			
			//Search with the 'Group Name' as criteria
			oncall_SearchPage.enterGroupName(groupNameSearchCriteria);
			
			//Search with the 'Group ID' as criteria
			oncall_SearchPage.enterGroupID(groupIDSearchCriteria);
			
			//Click the 'Search' button
			oncall_SearchPage.clickSearch();
			
			//Check if the search result matches expectation(s)
			oncall_SearchPage.verifySearchResults(checkpoint, expectedSearchResult);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}