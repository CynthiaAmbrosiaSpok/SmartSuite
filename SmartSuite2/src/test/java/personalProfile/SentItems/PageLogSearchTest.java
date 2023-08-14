package personalProfile.SentItems;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_SentItemsPage;
import utilityClasses.ExcelMethods;

public class PageLogSearchTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public PageLogSearchTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfile_SentItemsPage = new PersonalProfile_SentItemsPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("personalProfileSentItemsDataTable"));
		excelMethods.setSheetName("Page Log Search");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_SentItems_PageLogSearchTest(String active, String reportTitle, String recipient, String pageMessage, String messageSearchCriteria, String startDateYearSearchResult, String startDateMonthSearchResult, String startDateDaySearchResult, String endDateYearSearchResult, String endDateMonthSearchResult, String endDateDaySearchResult, String expectedMessage, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_SentItems_PageLogSearchTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Click the 'Quick Page' link to open up the 'Send a Page' pop-up
			mainPage.clickQuickPageLink();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Send a Page
			mainPage.sendPage(recipient, pageMessage);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Oncall' tab
			mainPage.clickPersonalProfileTab();
			
			//Search with the 'Message' as criteria
			personalProfile_SentItemsPage.performSearch(messageSearchCriteria, startDateYearSearchResult, startDateMonthSearchResult, startDateDaySearchResult, endDateYearSearchResult, endDateMonthSearchResult, endDateDaySearchResult);
			
			//Check if the search result matches expectation(s)
			personalProfile_SentItemsPage.verifyPageLogSearchResults(checkpoint, expectedMessage);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}