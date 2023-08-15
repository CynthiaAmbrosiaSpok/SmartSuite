package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallTodayLocators;

public class Oncall_TodayPage extends OncallTodayLocators {
	
	//Constructor
	public Oncall_TodayPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String searchGroup, String searchCriteria) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching for today's Scheduler Assignment(s)");
		
		//Select the Group Search Criteria
		reportLoggerMethods.selectDropDown(selectGroupDropDown, selectGroupDropDownName, searchGroup);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(searchField, searchFieldName, searchCriteria);
		
		//Click 'Search'
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifyTodaySchedulerSearch(SoftAssert softAssert, String searchExpectation) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result(s) matches expectations");
		
		//Initialize Variable(s)
		String firstSearchResultStr = reportLoggerMethods.getText(firstSearchResult, firstSearchResultName);
		
		//Assert the expected search result(s)
		softAssert.assertEquals(firstSearchResultStr, searchExpectation);
		
		//Report the assert status to the Extent Report & System
		if (firstSearchResultStr.equalsIgnoreCase(searchExpectation)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result(s) for the 'Today' tab search result matches expectation(s)");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The search result(s) for the 'Today' tab search result (" + firstSearchResultStr + ") does not match expectations (" + searchExpectation + ")", "verifyTodaySchedulerSearch");
		}
	}
	
}