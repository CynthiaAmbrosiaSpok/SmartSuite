package globalSearch;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.GlobalSearchPage;
import ssWeb.pageMethods.MainPage;
import utilityClasses.ExcelMethods;

public class SearchTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	int rowCountOutputColumn;
	
	//Constructor
	public SearchTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		globalSearchPage = new GlobalSearchPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("globalSearchDataTable"));
		excelMethods.setSheetName("Global Search");
		rowCountOutputColumn = 21;
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_globalSearch_SearchTest(String active, String reportTitle, String searchCriteria, String refreshTest, String profileCheckbox, String onCallCheckbox, String directoryCheckbox, String setMaxRows, String checkMessageID, String messageID, String checkPhoneNumber, String phoneNumber, String expectedName, String expectedType, String expectedTitle, String expectedDepartment, String expectedSpecialty, String expectedAlias, String expectedRowCount, String actualRowCount, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_GlobalSearch_SearchTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Navigate to the 'Global Search' tab
			mainPage.clickGlobalSearchTab();
			
			//Set the maximum number of rows being displayed for the search results
			globalSearchPage.setMaxSearchRows(setMaxRows);
			
			//Check if the search checkboxes need to be selected or unselected
			globalSearchPage.checkSearchCheckboxes(profileCheckbox, onCallCheckbox, directoryCheckbox);
			
			//Perform a search
			globalSearchPage.performSearch(searchCriteria);
			
			//Check if an error message relating to 'Set Max Rows' appeared
			globalSearchPage.verifyMaxRowStatus(checkpoint, expectedRowCount);
			
			//Check if the desired search result(s) are present (including the Message ID)
			if (checkMessageID.equalsIgnoreCase("yes") || checkMessageID.equalsIgnoreCase("y")) {
				globalSearchPage.verifySearchedResultsWithMessageID(checkpoint, messageID, expectedName, expectedType, expectedTitle, expectedDepartment, expectedSpecialty, expectedAlias, expectedRowCount, iteration, rowCountOutputColumn);
			}
			
			//Check if the desired search result(s) are present (including the Phone Number)
			if (checkPhoneNumber.equalsIgnoreCase("yes") || checkPhoneNumber.equalsIgnoreCase("y")) {
				globalSearchPage.verifySearchedResultsWithPhoneNumber(checkpoint, phoneNumber, expectedName, expectedType, expectedTitle, expectedDepartment, expectedSpecialty, expectedAlias, expectedRowCount, iteration, rowCountOutputColumn);
			}
			
			//Check if the desired search result(s) are present
			globalSearchPage.verifySearchedResults(checkpoint, expectedName, expectedType, expectedTitle, expectedDepartment, expectedSpecialty, expectedAlias, expectedRowCount, iteration, rowCountOutputColumn);
			
			//Refresh the 'Global Search' page & check for no search results
			if (refreshTest.equalsIgnoreCase("yes") || refreshTest.equalsIgnoreCase("y")) {
				//Refresh the 'Global Search' page
				mainPage.refreshGlobalSearchTab();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(1000);
				
				//Check if there are no current search results
				globalSearchPage.verifyNoSearchResults(checkpoint);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}