package globalSearch;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.GlobalSearchPage;
import ssWeb.pageMethods.MainPage;
import utilityClasses.ExcelMethods;

public class AdvancedSearchLinksTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AdvancedSearchLinksTest() {
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
		excelMethods.setSheetName("Advanced Search Links");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_globalSearch_AdvancedSearchLinksTest(String active, String reportTitle, String advancedSearchType, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_GlobalSearch_AdvancedSearchLinksTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Navigate to the 'Global Search' tab
			mainPage.clickGlobalSearchTab();
			
			//Navigate to another page via clicking an 'Advanced Search Link' in the 'Global Search' page
			if (advancedSearchType.equalsIgnoreCase("Directory Search") || advancedSearchType.equalsIgnoreCase("Advanced Directory Search")) {
				//Click the 'Advanced Directory Search' link
				globalSearchPage.clickAdvancedDirectorySearch();
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Check that the 'Directory Page' is the current page
				mainPage.verifyActiveDirectoryPage(checkpoint);
			} else if (advancedSearchType.equalsIgnoreCase("Paging Search") || advancedSearchType.equalsIgnoreCase("Advanced Paging Search")) {
				//Click the 'Advanced Paging Search' link
				globalSearchPage.clickAdvancedPagingSearch();
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Check that the 'Paging Page' is the current page
				mainPage.verifyActivePagingPage(checkpoint);
			} else if (advancedSearchType.equalsIgnoreCase("Oncall Search") || advancedSearchType.equalsIgnoreCase("Advanced Oncall Search")) {
				//Click the 'Advanced Oncall Search' link
				globalSearchPage.clickAdvancedOncallSearch();
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Check that the 'Oncall Page' is the current page
				mainPage.verifyActiveOncallPage(checkpoint);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}