package globalSearch;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.GlobalSearchPage;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_InboxPage;
import utilityClasses.ExcelMethods;

public class SendPageMessageTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SendPageMessageTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		globalSearchPage = new GlobalSearchPage(eDriver, reportLogger);
		personalProfileInboxPage = new PersonalProfile_InboxPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("globalSearchDataTable"));
		excelMethods.setSheetName("Global Search Send Page");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_globalSearch_SendPageMessageTest(String active, String reportTitle, String searchCriteria, String sender, String name, String pageMessage, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_GlobalSearch_SendPageMessageTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Global Search' tab
			mainPage.clickGlobalSearchTab();
			
			//Perform a search
			globalSearchPage.performSearch(searchCriteria);
			
			//Locate & send a page from the desired search result
			globalSearchPage.sendPage(searchCriteria, pageMessage);
			
			//Click the 'Active Messages' link to go to the 'Messages' page
			personalProfileInboxPage.clickActiveMessagesLink();
			
			//Check if the relevant active message in located in the 'Active Messages' inbox
			personalProfileInboxPage.verifyActiveMessage(checkpoint, sender, name, pageMessage);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}