package personalProfile.DeviceManagement;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_DeviceManagementPage;
import utilityClasses.ExcelMethods;

public class AddModifyDeleteEmailTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AddModifyDeleteEmailTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfile_DeviceManagementPage = new PersonalProfile_DeviceManagementPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("personalProfileDeviceManagementDataTable"));
		excelMethods.setSheetName("Device Management - Email");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_DeviceManagement_AddModifyDeleteEmailTest(String active, String reportTitle, String newEmailOrder, String newEmailAddress, String modifyEmailOrder, String modifyEmailAddress, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_DeviceManagement_AddModifyDeleteEmailTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Device Management' tab
			mainPage.clickDeviceManagementTab();
			
			//Delete any existing email that contains the same info as the email being added/modified
			personalProfile_DeviceManagementPage.deleteEmail(newEmailOrder, newEmailAddress);
			
			//Delete any existing email that contains the same info as the email being added/modified
			personalProfile_DeviceManagementPage.deleteEmail(modifyEmailOrder, modifyEmailAddress);
			
			//Add a new email
			personalProfile_DeviceManagementPage.addNewEmail(newEmailOrder, newEmailAddress);
			
			//Check if the expected email is listed
			personalProfile_DeviceManagementPage.verifyEmailInfo(checkpoint, newEmailOrder, newEmailAddress);
			
			//Modify an existing email
			personalProfile_DeviceManagementPage.modifyEmail(checkpoint, newEmailOrder, newEmailAddress, modifyEmailOrder, modifyEmailAddress);
			
			//Check if the expected email is listed
			personalProfile_DeviceManagementPage.verifyEmailInfo(checkpoint, modifyEmailOrder, modifyEmailAddress);
			
			//Delete any existing email that contains the same info as the email being added/modified
			personalProfile_DeviceManagementPage.deleteEmail(modifyEmailOrder, modifyEmailAddress);
			
			//Check if the desired email was deleted
			personalProfile_DeviceManagementPage.verifyDeletedEmailEntry(checkpoint, modifyEmailOrder, modifyEmailAddress);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}