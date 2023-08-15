package onCall.BlockAssignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_BlockAssignmentPage;
import utilityClasses.ExcelMethods;

public class CalenderNavigationTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	String newCurrentMonth;
	String newCurrentYear;
	
	//Constructor
	public CalenderNavigationTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		oncall_BlockAssignmentPage = new Oncall_BlockAssignmentPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallBlockAssignmentDataTable"));
		excelMethods.setSheetName("Assignment");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_BlockAssignment_CalenderNavigationTest(String active, String reportTitle, String forwardOrBackward, String amountOfMonths, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_BlockAssignment_CalenderNavigationTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Block Assignment' tab
			mainPage.clickBlockAssignmentTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Retrieve the expected month/year
			newCurrentMonth = oncall_BlockAssignmentPage.getNewCurrentCalenderMonth(forwardOrBackward, amountOfMonths);
			newCurrentYear = oncall_BlockAssignmentPage.getNewCurrentCalenderYear(forwardOrBackward, amountOfMonths);
			
			//Move backward/forward a month on the 'Block Assignment' calender
			if (forwardOrBackward.equalsIgnoreCase("forward")) {
				//Move forward a month on the 'Block Assignment' calender
				oncall_BlockAssignmentPage.clickForwardMonthButton(amountOfMonths);
			} else if (forwardOrBackward.equalsIgnoreCase("backward")) {
				//Move backward a month on the 'Block Assignment' calender
				oncall_BlockAssignmentPage.clickBackwardMonthButton(amountOfMonths);
			} else {
				System.out.println("The 'Move Month Forward or Backward' value in Excel must be set to 'Forward' or 'Backward', and not '" + forwardOrBackward + "'");
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Move Month Forward or Backward' value in Excel must be set to 'Forward' or 'Backward'", "blockAssignmentCalenderNavigationTest");
			}
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Check that the Block Assignment Calender is set to the correct year and month
			oncall_BlockAssignmentPage.verifyCalenderDate(checkpoint, newCurrentMonth, newCurrentYear);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}