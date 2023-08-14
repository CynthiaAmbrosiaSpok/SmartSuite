package ezNotify;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import utilityClasses.ExcelMethods;

public class EncryptPasswords extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public EncryptPasswords() {
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
		excelMethods.setDataTablePath(constantVariables.ezNotifyDataTablePath + prop.getProperty("ezNotifyDataTable"));
		excelMethods.setSheetName("Login");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_generalSmartSuite_LoginTest(String environment, String userRole, String userID, String beforeEncryption, String afterEncryption, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow)-1; //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Encrypt the Listed Password
		String encryptedPassword = encryptionMethods.encryptPassword(beforeEncryption, iteration, 4, 5);
		
		//Set the SmartSuite password
		if (smartwebUsername.equalsIgnoreCase(userID)) {
			smartwebPassword = encryptedPassword;
		}
	}
}