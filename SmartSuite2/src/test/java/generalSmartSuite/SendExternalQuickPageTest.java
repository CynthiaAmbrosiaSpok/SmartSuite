package generalSmartSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_InboxPage;
import utilityClasses.ExcelMethods;

public class SendExternalQuickPageTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SendExternalQuickPageTest() {
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
		excelMethods.setSheetName("External Page");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_generalSmartSuite_SendExternalQuickPageTest(String active, String reportTitle, String pageMessage, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_SendExternalQuickPageTest()");
			
			//Initialize Variable(s)
			String externalPageLink;
			
			//Initialize website link for the 'External Page Link'
			if (activeWebsite.charAt(activeWebsite.length()-1) == '/') {
				externalPageLink = activeWebsite + "pages/paging/extQPaging.jsf?MSGID=9999&TEXT=" + pageMessage + "&PRIORITY=1";
			} else {
				externalPageLink = activeWebsite + "/pages/paging/extQPaging.jsf?MSGID=9999&TEXT=" + pageMessage + "&PRIORITY=1";
			}
			
			//Send the external page
			mainPage.accessWebsite(externalPageLink);
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Inbox' tab
			mainPage.clickInboxTab();
			
			//Navigate to the 'Active Messages' page
			personalProfileInboxPage.goToActiveMessages();
			
			//Check for the page in the 'Personal Profile -> Inbox' tab
			personalProfileInboxPage.verifyActiveExternalMessage(checkpoint, pageMessage);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}