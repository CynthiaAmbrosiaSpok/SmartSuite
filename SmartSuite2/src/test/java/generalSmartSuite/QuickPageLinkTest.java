package generalSmartSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_InboxPage;
import utilityClasses.ExcelMethods;

public class QuickPageLinkTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public QuickPageLinkTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfileInboxPage = new PersonalProfile_InboxPage(eDriver, reportLogger);
	}

	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("generalSmartSuiteDataTable"));
		excelMethods.setSheetName("Quick Page Link");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_generalSmartSuite_QuickPageLinkTest(String active, String reportTitle, String recipient, String pageMessage, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_QuickPageLinkTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Click the 'Quick Page' link to open up the 'Send a Page' pop-up
			mainPage.clickQuickPageLink();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Send a Page
			mainPage.sendPage(recipient, pageMessage);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Inbox' tab
			mainPage.clickInboxTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Active Messages' page
			personalProfileInboxPage.goToActiveMessages();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Check for the page in the 'Personal Profile -> Inbox' tab
			personalProfileInboxPage.verifyActiveMessage(checkpoint, pageMessage);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}