package personalProfile.DeviceManagement;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.DirectorySearchPage;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_DeviceManagementPage;
import utilityClasses.ExcelMethods;

public class SendToAllDevicesIconTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SendToAllDevicesIconTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfile_DeviceManagementPage = new PersonalProfile_DeviceManagementPage(eDriver, reportLogger);
		directorySearchPage = new DirectorySearchPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("personalProfileDeviceManagementDataTable"));
		excelMethods.setSheetName("Send to all devices");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_DeviceManagement_SendToAllDevicesIconTest(String active, String reportTitle, String lastNameSearchCriteria, String phoneNumberSearchCriteria, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_DeviceManagement_SendToAllDevicesIconTest()");
			
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
			
			//Check the 'Send to all devices' checkbox
			personalProfile_DeviceManagementPage.checkSendToAllDevicesCheckbox();
			
			//Navigate to the 'Directory Search' tab
			mainPage.clickDirectorySearchTab();
			
			//Perform a search
			directorySearchPage.performSearch("", "", lastNameSearchCriteria, "", phoneNumberSearchCriteria, "", "");
			
			//Check if the 'Send to all devices' icon appeared, as expected
			directorySearchPage.verifySendToAllDevicesIcon(checkpoint);
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Navigate to the 'Device Management' tab
			mainPage.clickDeviceManagementTab();
			
			//Check the 'Send to all devices' checkbox
			personalProfile_DeviceManagementPage.uncheckSendToAllDevicesCheckbox();
			
			//Navigate to the 'Directory Search' tab
			mainPage.clickDirectorySearchTab();
			
			//Perform a search
			directorySearchPage.performSearch("", "", lastNameSearchCriteria, "", phoneNumberSearchCriteria, "", "");
			
			//Check if the 'Send to all devices' icon appeared, as expected
			directorySearchPage.verifyNoSendToAllDevicesIcon(checkpoint);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}