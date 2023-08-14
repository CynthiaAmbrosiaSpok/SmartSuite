package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallSearchLocators;

public class Oncall_SearchPage extends OncallSearchLocators {
	
	//Constructor
	public Oncall_SearchPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void clearSearchResults() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clearing the existing search criteria");
		
		//Clear the search field
		reportLoggerMethods.clear(groupNameSearchField, groupNameSearchFieldName);
		
		//Search with the 'Group Name' as criteria
		reportLoggerMethods.sendKeys(groupNameSearchField, groupNameSearchFieldName, ".");
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Clear the search field
		reportLoggerMethods.clear(groupNameSearchField, groupNameSearchFieldName);
	}
	
	public void enterGroupName(String groupName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the 'Group Name' search criteria");
		
		//Enter the 'Group Name' search criteria
		reportLoggerMethods.sendKeys(groupNameSearchField, groupNameSearchFieldName, groupName);
	}
	
	public void enterGroupID(String groupID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the 'Group ID' search criteria");
		
		//Enter the 'Group ID' search criteria
		reportLoggerMethods.sendKeys(groupIDField, groupIDFieldName, groupID);
	}
	
	public void clickSearch() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching with the current search criteria");
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	public void verifySearchResults(SoftAssert softAssert, String expectedSearchResult) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result matches expectation(s)");
		
		//Check if the search result matches expectation(s)
		if (reportLoggerMethods.isDisplayed(groupSearchResultFirstListing, groupSearchResultFirstListingName)) {
			//Initialize Variable(s)
			String firstGroupSearchResult = reportLoggerMethods.getText(groupSearchResultFirstListing, groupSearchResultFirstListingName);
			
			//Check if the search result matches expectation(s)
			softAssert.assertEquals(firstGroupSearchResult, expectedSearchResult);
			
			//Report the assert status to the Extent Report & System
			if (firstGroupSearchResult.equalsIgnoreCase(expectedSearchResult)) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result of '" + expectedSearchResult + "' appeared as expected");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The search result of '" + firstGroupSearchResult + "' appeared, instead of the expected result of '" + expectedSearchResult + "'", "verifySearchResults");
			}
		} else {
			//Check if the search result matches expectation(s)
			if (expectedSearchResult.equalsIgnoreCase("No Search Results")) {
				softAssert.assertEquals("No Search Results", expectedSearchResult);
			} else {
				softAssert.assertEquals("", expectedSearchResult);
			}
			
			//Report the assert status to the Extent Report & System
			if (expectedSearchResult.equalsIgnoreCase("No Search Results") || expectedSearchResult.equalsIgnoreCase("")) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result of '" + expectedSearchResult + "' appeared as expected");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: No search results appeared, instead of the expected result of '" + expectedSearchResult + "'", "verifySearchResults");
			}
		}
	}
	
}