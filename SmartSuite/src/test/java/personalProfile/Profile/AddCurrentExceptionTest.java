package personalProfile.Profile;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_ProfilePage;
import utilityClasses.ExcelMethods;

public class AddCurrentExceptionTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public AddCurrentExceptionTest() {
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
		excelMethods.setSheetName("Add Current Exception");
	}

	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_Profile_AddCurrentExceptionTest(String active, String reportTitle, String exceptionType, String messagingID, String day, String month, String year, String hour, String minutes, String ampm, String timeZone, String remark, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_Profile_AddCurrentExceptionTest()");
			
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
			
			//Create a Coverage Exception
			personalProfile_ProfilePage.createException(exceptionType, messagingID, day, month, year, hour, minutes, ampm, timeZone, remark);
			
			//Check if the Exception was created successfully
			if (exceptionType.equalsIgnoreCase("coverage")) {
				//Check if the Coverage Exception was created successfully
				personalProfile_ProfilePage.verifyCreatedCoverageException(checkpoint, messagingID);
			} else if (exceptionType.equalsIgnoreCase("referral")) {
				//Check if the Referral Exception was created successfully
				personalProfile_ProfilePage.verifyCreatedReferralException(checkpoint, messagingID);
			} else if (exceptionType.equalsIgnoreCase("page block")) {
				//Check if the Page Block Exception was created successfully
				personalProfile_ProfilePage.verifyCreatedPageBlockException(checkpoint, messagingID);
			}
			
			//Delete any existing, current Exception
			personalProfile_ProfilePage.deleteCurrentException();
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}