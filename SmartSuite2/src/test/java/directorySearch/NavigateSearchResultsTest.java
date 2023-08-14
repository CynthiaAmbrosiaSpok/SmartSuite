package directorySearch;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.DirectorySearchPage;
import ssWeb.pageMethods.MainPage;
import utilityClasses.ExcelMethods;

public class NavigateSearchResultsTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public NavigateSearchResultsTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		directorySearchPage = new DirectorySearchPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("directorySearchDataTable"));
		excelMethods.setSheetName("Navigate Directory");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_directorySearch_NavigateSearchResultsTests(String active, String reportTitle, String lastNameSearchCriteria, String expectedNextPageLabel, String expectedFirstPageLabel, String expectedFastForwardPageLabel, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_DirectorySearch_NavigateSearchResultsTests()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Perform a search
			directorySearchPage.performSearch("", lastNameSearchCriteria);
			
			//Click the 'Next Page' button for the search results page
			directorySearchPage.navigateNextPageOfSearchResults(checkpoint, expectedNextPageLabel);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(100);
			
			//Click the 'First Page' button for the search results page
			directorySearchPage.navigateFirstPageOfSearchResults(checkpoint, expectedFirstPageLabel);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(100);
			
			//Clicking the 'Fast Forward' button for the search results page
			directorySearchPage.navigateFastForwardPageOfSearchResults(checkpoint, expectedFastForwardPageLabel);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}