package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallQuickViewLocators;

public class Oncall_QuickViewPage extends OncallQuickViewLocators {
	
	//Constructor
	public Oncall_QuickViewPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String messagingID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a Quick View search");
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(messagingIDSearchField, messagingIDSearchFieldName, messagingID);
		
		//Click search
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifySearchResults(SoftAssert softAssert, String expectedSearchResult) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result(s) matches expectations");
		
		//Initialize Variable(s)
		String firstSearchResultMsgID = reportLoggerMethods.getText(firstSearchResultMsgIDLabel, firstSearchResultMsgIDLabelName);
		
		//Assert the expected search result(s)
		softAssert.assertEquals(firstSearchResultMsgID, expectedSearchResult);
		
		//Check if the search result(s) matches expectations
		if (firstSearchResultMsgID.equalsIgnoreCase(expectedSearchResult)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result(s) for the 'Quick View' tab search result matches expectations");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The search result(s) for the 'Quick View' tab search result (" + firstSearchResultMsgID + ") does not match expectations (" + expectedSearchResult + ")", "verifySearchResults");
		}
	}
	
	public void verifyOncallGroup(SoftAssert softAssert, String expectedOncallGroup) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the expected Oncall Group exists");
		
		//Initialize Variable(s)
		String firstOncallGroup = reportLoggerMethods.getText(firstOncallGroupListing, firstOncallGroupListingName);
		
		//Assert the expected Oncall Group(s)
		softAssert.assertEquals(firstOncallGroup, expectedOncallGroup);
		
		//Check if the expected Oncall Group exists
		if (firstOncallGroup.equalsIgnoreCase(expectedOncallGroup)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The expected Oncall Group(s) in the 'Quick View' tab match expectations");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The expected Oncall Group(s) in the 'Quick View' tab do not match expectations", "verifyOncallGroup");
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
		reportLoggerMethods.reportInfo("Checking that the Search Tree is hidden in the 'Oncall Quick View' tab");
		
		//Check that the Search Tree is hidden
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "hidden");
		
		//Output the results to the report
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("hidden")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is hidden in the 'Oncall Quick View' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not hidden in the 'Oncall Quick View' tab", "verifyHiddenTree");
		}
	}
	
	public void verifyShownTree(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Search Tree is shown in the 'Oncall Quick View' tab");
		
		//Check that the Search Tree is shown
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "visible");
		
		//Output the results to the report
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("visible")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is shown in the 'Oncall Quick View' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not shown in the 'Oncall Quick View' tab", "verifyHiddenTree");
		}
	}
	
}