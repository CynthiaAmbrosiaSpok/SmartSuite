package pagingTab;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PagingSearchPage;
import utilityClasses.ExcelMethods;

public class SearchTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	int rowCountOutputColumn;
	
	//Constructor
	public SearchTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		pagingSearchPage = new PagingSearchPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("pagingTabDataTable"));
		excelMethods.setSheetName("Paging Search");
		rowCountOutputColumn = 15;
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_pagingTab_SearchTest(String active, String reportTitle, String firstNameSearchCriteria, String lastNameSearchCriteria, String departmentSearchCriteria, String groupFunctionNameSearchCriteria, String idSearchCriteria, String expectedName, String expectedDepartment, String expectedStatus, String expectedType, String expectedID, String expectedRowCount, String actualRowCount, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PagingTab_SearchTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Navigate to the 'Paging' tab
			mainPage.clickPagingTab();
			
			//Perform a search
			pagingSearchPage.performSearch(firstNameSearchCriteria, lastNameSearchCriteria, departmentSearchCriteria, groupFunctionNameSearchCriteria, idSearchCriteria);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(3000);
			
			//Check if the desired search result(s) are present
			pagingSearchPage.verifySearchedResults(checkpoint, expectedName, expectedDepartment, expectedStatus, expectedType, expectedID, expectedRowCount, iteration, rowCountOutputColumn);
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}