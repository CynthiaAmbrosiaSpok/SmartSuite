package pagingTab;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PagingSearchPage;
import ssWeb.pageMethods.PersonalProfile_InboxPage;
import utilityClasses.ExcelMethods;

public class SendPageTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SendPageTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		pagingSearchPage = new PagingSearchPage(eDriver, reportLogger);
		personalProfileInboxPage = new PersonalProfile_InboxPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("pagingTabDataTable"));
		excelMethods.setSheetName("Paging Tab Send Page");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_pagingTab_SendPageTest(String active, String reportTitle, String firstNameSearchCriteria, String lastNameSearchCriteria, String expectedName, String pageMessage, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Set the Page Message to a random number, if necessary
		if (pageMessage.equals("Random Number")) {
			Random randomizer = new Random();
			int randomNumber = randomizer.nextInt(1000);
			pageMessage = String.valueOf(randomNumber);
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PagingTab_SendPageTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//Navigate to the 'Paging' tab
			mainPage.clickPagingTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Perform a search
			pagingSearchPage.performNameSearch(firstNameSearchCriteria, lastNameSearchCriteria);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(2000);
			
			//Locate then send a page from the desired search result
			pagingSearchPage.sendPage(expectedName, pageMessage);
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Navigate to the 'Inbox' tab
			mainPage.clickInboxTab();
			
			//Check for the page in the 'Personal Profile -> Inbox' tab
			personalProfileInboxPage.verifyActiveMessage(checkpoint, pageMessage);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}