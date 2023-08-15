package onCall;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_BlockAssignmentPage;
import ssWeb.pageMethods.Oncall_CalenderAssignmentPage;
import ssWeb.pageMethods.Oncall_HistoryPage;
import ssWeb.pageMethods.Oncall_QuickViewPage;
import ssWeb.pageMethods.Oncall_SchedulerAssignmentPage;
import utilityClasses.ExcelMethods;

public class NavigateTreesTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	int waitTime = 600;
	
	//Constructor
	public NavigateTreesTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		oncall_BlockAssignmentPage = new Oncall_BlockAssignmentPage(eDriver, reportLogger);
		oncall_SchedulerAssignmentPage = new Oncall_SchedulerAssignmentPage(eDriver, reportLogger);
		oncall_CalenderAssignmentPage = new Oncall_CalenderAssignmentPage(eDriver, reportLogger);
		oncall_HistoryPage = new Oncall_HistoryPage(eDriver, reportLogger);
		oncall_QuickViewPage = new Oncall_QuickViewPage(eDriver, reportLogger);
	}

	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("onCallDataTable"));
		excelMethods.setSheetName("Navigate Oncall Trees");
	}

	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_onCall_NavigateTreesTest(String active, String reportTitle, String searchTab, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_OnCall_NavigateTreesTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Oncall' tab
			mainPage.clickOncallTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(waitTime);
			
			//Perform the test on the specified tab
			if (searchTab.equalsIgnoreCase("block assignment")) {
				//Navigate to the 'Oncall Block Assignment' tab
				mainPage.clickBlockAssignmentTab();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Click the 'Hide Tree' button
				oncall_BlockAssignmentPage.clickHideTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is hidden in the 'Oncall Block Assignment' tab
				oncall_BlockAssignmentPage.verifyHiddenTree(checkpoint);
				
				//Click the 'Show Tree' button
				oncall_BlockAssignmentPage.clickShowTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is shown in the 'Oncall Block Assignment' tab
				oncall_BlockAssignmentPage.verifyShownTree(checkpoint);
			} else if (searchTab.equalsIgnoreCase("scheduler assignment")) {
				//Navigate to the 'Oncall Scheduler Assignment' tab
				mainPage.clickSchedulerAssignmentTab();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Click the 'Hide Tree' button
				oncall_SchedulerAssignmentPage.clickHideTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is hidden in the 'Oncall Scheduler Assignment' tab
				oncall_SchedulerAssignmentPage.verifyHiddenTree(checkpoint);
				
				//Click the 'Show Tree' button
				oncall_SchedulerAssignmentPage.clickShowTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is shown in the 'Oncall Scheduler Assignment' tab
				oncall_SchedulerAssignmentPage.verifyShownTree(checkpoint);
			} else if (searchTab.equalsIgnoreCase("calender assignment")) {
				//Navigate to the 'Oncall Calender Assignment' tab
				mainPage.clickCalenderAssignmentTab();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Click the 'Hide Tree' button
				oncall_CalenderAssignmentPage.clickHideTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is hidden in the 'Oncall Calender Assignment' tab
				oncall_CalenderAssignmentPage.verifyHiddenTree(checkpoint);
				
				//Click the 'Show Tree' button
				oncall_CalenderAssignmentPage.clickShowTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is shown in the 'Oncall Calender Assignment' tab
				oncall_CalenderAssignmentPage.verifyShownTree(checkpoint);
			} else if (searchTab.equalsIgnoreCase("history")) {
				//Navigate to the 'Oncall History' tab
				mainPage.clickHistoryTab();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Click the 'Hide Tree' button
				oncall_HistoryPage.clickHideTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is hidden in the 'Oncall History' tab
				oncall_HistoryPage.verifyHiddenTree(checkpoint);
				
				//Click the 'Show Tree' button
				oncall_HistoryPage.clickShowTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is shown in the 'Oncall History' tab
				oncall_HistoryPage.verifyShownTree(checkpoint);
			} else if (searchTab.equalsIgnoreCase("quick view")) {
				//Navigating to the 'Oncall Quick View' tab
				mainPage.clickQuickViewTab();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Click the 'Hide Tree' button
				oncall_QuickViewPage.clickHideTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is hidden in the 'Oncall Quick View' tab
				oncall_QuickViewPage.verifyHiddenTree(checkpoint);
				
				//Click the 'Show Tree' button
				oncall_QuickViewPage.clickShowTreeButton();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(waitTime);
				
				//Check that the Search Tree is shown in the 'Oncall Quick View' tab
				oncall_QuickViewPage.verifyShownTree(checkpoint);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}