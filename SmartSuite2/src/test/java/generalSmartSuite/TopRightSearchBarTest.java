package generalSmartSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.GlobalSearchPage;
import ssWeb.pageMethods.MainPage;
import utilityClasses.ExcelMethods;

public class TopRightSearchBarTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	int rowCountOutputColumn;
	
	//Constructor
	public TopRightSearchBarTest() {
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
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("generalSmartSuiteDataTable"));
		excelMethods.setSheetName("Top Right Search Bar");
		rowCountOutputColumn = 15;
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_generalSmartSuite_TopRightSearchBarTest(String active, String reportTitle, String searchOnTab, String searchCriteria, String checkMessageID, String messageID, String expectedName, String expectedType, String expectedTitle, String expectedDepartment, String expectedSpecialty, String expectedAlias, String expectedRowCount, String actualRowCount, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_TopRightSearchBarTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Navigate to the 'Paging' tab
			if (searchOnTab.equalsIgnoreCase("paging")) {
				mainPage.clickPagingTab();
			}
		
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Perform a search
			mainPage.performSearch(searchCriteria);
			
			//Check if the desired search result(s) are present
			if (checkMessageID.equalsIgnoreCase("yes") || checkMessageID.equalsIgnoreCase("y")) {
				globalSearchPage.verifySearchedResultsWithMessageID(checkpoint, messageID, expectedName, expectedType, expectedTitle, expectedDepartment, expectedSpecialty, expectedAlias, expectedRowCount, iteration, rowCountOutputColumn);
			} else {
				globalSearchPage.verifySearchedResults(checkpoint, expectedName, expectedType, expectedTitle, expectedDepartment, expectedSpecialty, expectedAlias, expectedRowCount, iteration, rowCountOutputColumn);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}