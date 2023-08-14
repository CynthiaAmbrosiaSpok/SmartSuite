package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallHistoryLocators;

public class Oncall_HistoryPage extends OncallHistoryLocators {
	
	//Constructor
	public Oncall_HistoryPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void clearSearchResults() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clearing the existing search criteria");
		
		//Clear the search field
		reportLoggerMethods.clear(groupNameField, groupNameFieldName);
		
		//Search with the 'Group Name' as criteria
		reportLoggerMethods.sendKeys(groupNameField, groupNameFieldName, ".");
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Clear the existing search criteria
		reportLoggerMethods.clear(groupNameField, groupNameFieldName);
	}
	
	public void searchStartDateYear(String startDateYear) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching with the 'Start Date Year' as criteria");
		
		//Clear the previous existing criteria
		reportLoggerMethods.clear(startDateYearField, startDateYearFieldName);
		
		//Search with the 'Start Date Year' as criteria
		reportLoggerMethods.sendKeys(startDateYearField, startDateYearFieldName, startDateYear);
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Click the first search result
		reportLoggerMethods.click(searchResultFirstListing, searchResultFirstListingName);
	}
	
	public void verifySearchResults(SoftAssert softAssert, String expectedSearchResult, String expectedStartDate) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result matches expectation(s)");
		
		//Checking if the search result matches expectation(s)
		if (reportLoggerMethods.isDisplayed(searchResultFirstListing, searchResultFirstListingName)) {
			//Initialize Variable(s)
			String successMessage = "Success: The search result appeared as expected";
			String failMessage = "Failure: ";
			
			//Initialize Variable(s)
			String searchResultFirstListingStr = reportLoggerMethods.getText(searchResultFirstListing, searchResultFirstListingName);
			String startDateFirstSearchResultStr = reportLoggerMethods.getText(startDateFirstSearchResult, startDateFirstSearchResultName);
			
			//Verify if the search results matches expectation(s)
			softAssert.assertEquals(searchResultFirstListingStr, expectedSearchResult);
			softAssert.assertEquals(startDateFirstSearchResultStr, expectedStartDate);
			
			//Report the assert status to the Extent Report & System
			if (searchResultFirstListingStr.equalsIgnoreCase(expectedSearchResult) && startDateFirstSearchResultStr.equalsIgnoreCase(expectedStartDate)) {
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
				return;
			}
			
			//Verify the status of the 'Group Name' search result
			if (!searchResultFirstListingStr.equalsIgnoreCase(expectedSearchResult)) {
				//Add to the error message if the 'Group Name' does not match expectations
				failMessage += "The search result for 'Group Name' returned (" + searchResultFirstListingStr + "), but expected (" + expectedSearchResult + ")";
			}
			
			//Verify the status of the 'Start Date' search result
			if (!startDateFirstSearchResultStr.equalsIgnoreCase(expectedStartDate)) {
				//Add to the error message if the 'Start Date' does not match expectations
				failMessage += "The search result for 'Start Date' returned (" + startDateFirstSearchResultStr + "), but expected (" + expectedStartDate + ")";
			}
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failMessage, "verifySearchResults");
			
		} else {
			//Verify if the search results matches expectation(s)
			if (expectedSearchResult.equalsIgnoreCase("No Search Results")) {
				softAssert.assertEquals("No Search Results", expectedSearchResult);
			} else {
				softAssert.assertEquals("", expectedSearchResult);
			}
			
			//Report the assert status to the Extent Report & System
			if (expectedSearchResult.equalsIgnoreCase("No Search Results") || expectedSearchResult.equalsIgnoreCase("")) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: No search results appeared as expected");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: No search results appeared, instead of the expected group result of '" + expectedSearchResult + "' and start date of '" + expectedStartDate + "'", "verifySearchResults");
			}
		}
	}
	
	// ~~~ Tree ~~~ //
	
	public void clickHideTreeButton() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Hide Tree' button");
		
		//Click the 'Hide Tree' button
		reportLoggerMethods.click(hideTreeButton, hideTreeButtonName);
	}
	
	public void clickShowTreeButton() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Show Tree' button");
		
		//Click the 'Show Tree' button
		reportLoggerMethods.click(showTreeButton, showTreeButtonName);
	}
	
	public void verifyHiddenTree(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Search Tree is hidden in the 'Oncall History' tab");
		
		//Check that the Search Tree is hidden
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "hidden");
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("hidden")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is hidden in the 'Oncall History' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not hidden in the 'Oncall History' tab", "verifyHiddenTree");
		}
	}
	
	public void verifyShownTree(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Search Tree is shown in the 'Oncall History' tab");
		
		//Check that the Search Tree is shown
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "visible");
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("visible")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is shown in the 'Oncall History' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not shown in the 'Oncall History' tab", "verifyHiddenTree");
		}
	}
	
}