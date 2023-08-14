package personalProfile.QuickViewSettings;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_QuickViewPage;
import ssWeb.pageMethods.PersonalProfile_QuickViewSettingsPage;
import utilityClasses.ExcelMethods;

public class AddOncallGroupTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AddOncallGroupTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfile_QuickViewSettingsPage = new PersonalProfile_QuickViewSettingsPage(eDriver, reportLogger);
		oncall_QuickViewPage = new Oncall_QuickViewPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("personalProfileQuickViewDataTable"));
		excelMethods.setSheetName("Oncall Groups");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_QuickViewSettings_AddOncallGroupTest(String active, String reportTitle, String createOncallGroupName, String expectedOncallGroupName, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_QuickViewSettings_AddOncallGroupTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Quick View Settings' tab
			mainPage.clickQuickViewSettingsTab();
			
			//Delete all Oncall Groups
			personalProfile_QuickViewSettingsPage.deleteAllOncallGroup();
			
			//Create an Oncall Group
			personalProfile_QuickViewSettingsPage.createAndSaveOncallGroup(createOncallGroupName);
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Navigate to the 'Quick View' tab
			mainPage.clickQuickViewTab();
			
			//Check if the expected Oncall Group exists
			oncall_QuickViewPage.verifyOncallGroup(checkpoint, expectedOncallGroupName);
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Navigate to the 'Quick View Settings' tab
			mainPage.clickQuickViewSettingsTab();
			
			//Delete all Oncall Groups
			personalProfile_QuickViewSettingsPage.deleteAllOncallGroup();
			
			//Check if all Oncall Groups were deleted
			personalProfile_QuickViewSettingsPage.verifyDeletedOncallGroup(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}