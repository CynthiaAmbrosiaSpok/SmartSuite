package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallPersonAssignmentLocators;

public class Oncall_PersonAssignmentPage extends OncallPersonAssignmentLocators {
	
	//Constructor
	public Oncall_PersonAssignmentPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String messagingID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a Person Assignment search");
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(messagingIDSearchField, messagingIDSearchFieldName, messagingID);
		
		//Click search
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifySearchResults(SoftAssert softAssert, String expectedSearchResult) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result(s) matches expectation(s)");
		
		//Initialize Variable(s)
		String firstSearchMsgID = reportLoggerMethods.getAttribute(firstSearchMsgIDResult, firstSearchMsgIDResultName, "value");
		
		//Assert the expected search result(s)
		softAssert.assertEquals(firstSearchMsgID, expectedSearchResult);
		
		//Report the assert status to the Extent Report & System
		if (firstSearchMsgID.equalsIgnoreCase(expectedSearchResult)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result(s) for the 'Person Assignment' tab search result matches expectations");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The search result(s) for the 'Person Assignment' tab search result (" + firstSearchMsgID + ") does not match expectations (" + expectedSearchResult + ")", "verifySearchResults");
		}
	}
	
}