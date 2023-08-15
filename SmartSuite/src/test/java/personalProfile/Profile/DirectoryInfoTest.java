package personalProfile.Profile;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_ProfilePage;
import utilityClasses.ExcelMethods;

public class DirectoryInfoTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	int directoryRow;
	
	//Constructor
	public DirectoryInfoTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfile_ProfilePage = new PersonalProfile_ProfilePage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("personalProfileProfileDataTable"));
		excelMethods.setSheetName("Add Directory");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_Profile_DirectoryInfoTest(String active, String reportTitle, String department, String phoneNumber, String phoneNumberType, String addressType, String street1, String street2, String city, String state, String postalCode, String expectedAddress, String expectedAddressType, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_Profile_DirectoryInfoTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Profile' tab
			mainPage.clickProfileTab();
			
			//Create a new Directory
			personalProfile_ProfilePage.createDirectory(department, phoneNumber, phoneNumberType);
			
			//Retrieve the newly created Directory row
			directoryRow = personalProfile_ProfilePage.returnCreatedDirectoryRow(department, phoneNumber, phoneNumberType);
			
			//Check if the Directory was created with the expected details
			personalProfile_ProfilePage.verifyCreatedDirectory(checkpoint, directoryRow, department, phoneNumber, phoneNumberType);
			
			//Modify an existing Directory
			personalProfile_ProfilePage.modifyDirectory(checkpoint, directoryRow, addressType, street1, street2, city, state, postalCode);
			
			//Retrieve the newly modified Directory row
			directoryRow = personalProfile_ProfilePage.returnModifiedDirectoryRow(expectedAddress, expectedAddressType);
			
			//Check if the Directory was modified with the expected details
			personalProfile_ProfilePage.verifyModifyDirectory(checkpoint, directoryRow, expectedAddress, expectedAddressType);
			
			//Delete the recently created Directory
			personalProfile_ProfilePage.deleteDirectory(directoryRow);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}