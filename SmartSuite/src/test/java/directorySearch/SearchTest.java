package directorySearch;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.DirectorySearchPage;
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
		directorySearchPage = new DirectorySearchPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("directorySearchDataTable"));
		excelMethods.setSheetName("Directory Search");
		rowCountOutputColumn = 16;
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_directorySearch_SearchTest(String active, String reportTitle, String firstNameSearchCriteria, String lastNameSearchCriteria, String phoneNumberSearchCriteria, String phoneNumberTypeSearchCriteria, String titleSearchCriteria, String departmentSearchCriteria, String listingAliasSearchCriteria, String expectedName, String expectedPhoneNumber, String expectedTitle, String expectedDepartment, String expectedAlias, String expectedRowCount, String actualRowCount, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_DirectorySearch_SearchTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Perform a search
			directorySearchPage.performSearch(titleSearchCriteria, firstNameSearchCriteria, lastNameSearchCriteria, departmentSearchCriteria, phoneNumberSearchCriteria, phoneNumberTypeSearchCriteria, listingAliasSearchCriteria);
			
			//Check if the desired search result(s) are present
			directorySearchPage.verifySearchedResults(checkpoint, expectedName, expectedPhoneNumber, expectedTitle, expectedDepartment, expectedAlias, expectedRowCount, iteration, rowCountOutputColumn);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}