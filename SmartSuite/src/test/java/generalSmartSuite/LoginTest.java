package generalSmartSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import utilityClasses.ExcelMethods;

public class LoginTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public LoginTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("generalSmartSuiteDataTable"));
		excelMethods.setSheetName("Login");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_generalSmartSuite_LoginTest(String active, String reportTitle, String logout, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_LoginTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Log out of Smart Suite
			mainPage.logoutIfAble();
			
			//Verify if the user is logged out
			mainPage.verifyLogoutStatus(checkpoint);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Check if the desired search result(s) are present
			mainPage.verifyLoginStatus(checkpoint);
			
			//Logout of Smart Suite, if desired
			if (logout.equalsIgnoreCase("y") || logout.equalsIgnoreCase("yes")) {
				mainPage.logout();
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}