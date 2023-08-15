package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PersonalProfile_SentItemsLocators;

public class PersonalProfile_SentItemsPage extends PersonalProfile_SentItemsLocators {
	
	//Constructor
	public PersonalProfile_SentItemsPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Page Log Search ~~~ //
	
	public void performSearch(String message, String startDateYear, String startDateMonth, String startDateDay, String endDateYear, String endDateMonth, String endDateDay) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a search");
		
		//Search with the 'Message' as criteria
		reportLoggerMethods.sendKeys(messageField, messageFieldName, message);
		
		//Clear the previous existing 'Start Date Year' criteria
		reportLoggerMethods.clear(startDateYearField, startDateYearFieldName);
		
		//Enter the 'Start Date Year' as criteria
		reportLoggerMethods.sendKeys(startDateYearField, startDateYearFieldName, startDateYear);
		
		//Select the 'Start Date Month' as criteria
		reportLoggerMethods.selectDropDown(startDateMonthField, startDateMonthFieldName, startDateMonth);
		
		//Clear the previous existing 'Start Date Day' criteria
		reportLoggerMethods.clear(startDateDayField, startDateDayFieldName);
		
		//Enter the 'Start Date Day' as criteria
		reportLoggerMethods.sendKeys(startDateDayField, startDateDayFieldName, startDateDay);
		
		//Clear the previous existing criteria
		reportLoggerMethods.clear(endDateYearField, endDateYearFieldName);
		
		//Enter the 'Start Date Year' as criteria
		reportLoggerMethods.sendKeys(endDateYearField, endDateYearFieldName, endDateYear);
		
		//Enter the 'Start Date Month' as criteria
		reportLoggerMethods.selectDropDown(endDateMonthField, endDateMonthFieldName, endDateMonth);
		
		//Clear the previous existing criteria
		reportLoggerMethods.clear(endDateDayField, endDateDayFieldName);
		
		//Enter the 'Start Date Day' as criteria
		reportLoggerMethods.sendKeys(endDateDayField, endDateDayFieldName, endDateDay);
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	public int getSearchResultCount() {
		//Return the value listed in the 'Search Results' label that appears when multiple pages appear
		if (reportLoggerMethods.isDisplayed(searchCountMessageLabel, searchCountMessageLabelName)) {
			//Initialize Variable(s)
			String searchCountMessage = reportLoggerMethods.getText(searchCountMessageLabel, searchCountMessageLabelName); //Retrieve the message indicating how many search results were returned
			searchCountMessage = searchCountMessage.substring(21, searchCountMessage.length()); //Strip down any unnecessary info relating to the message indicating how many search results were returned
			
			return Integer.parseInt(searchCountMessage);
		}
		
		//Return the count of search results rows on a single page (when there is only a single page of search results)
		if (reportLoggerMethods.getSize(searchResultsRowList, searchResultsRowListName) > 0) {
			return reportLoggerMethods.getSize(searchResultsRowList, searchResultsRowListName)+1;
		} else if (reportLoggerMethods.isDisplayed(searchResultsFirstRow, searchResultsFirstRowName)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public void verifyPageLogSearchResults(SoftAssert softAssert, String expectedSearchResultMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result matches expectation(s)");
		
		//Initialize Variable(s)
		int searchCountNumber = getSearchResultCount(); //Keeps track of how many search results were returned - used to loop through all search results
		int currentSearchResult = 0; //Keeps track of the specific search result row on a page being looked at
		String currentSearchResultMessage; //Keeps track of the actual search result message being displayed
		String[] splitSearchResultMessage;
		boolean correctSearchResult = false; //Keeps track if a search result does not match expectations
		
		//Loop through every returned search result
		for (int i = 0; i < searchCountNumber; i++) {
			//Retrieve the search result message (in lower case values to avoid capitalization causing failures when comparing existing search results to expected search results)
			currentSearchResultMessage = reportLoggerMethods.getText(searchResultMessageLabel(currentSearchResult), searchResultMessageLabelName(currentSearchResult));
			splitSearchResultMessage = currentSearchResultMessage.split("\\|");
			currentSearchResultMessage = "";
			
			//Remove the last part of the current search result message
			for (int j = 0; j < splitSearchResultMessage.length-1; j++) {
				currentSearchResultMessage += splitSearchResultMessage[j];
			}
			
			//Output a failed status to the report, if a search results does not match expectations
			if (currentSearchResultMessage.equalsIgnoreCase(expectedSearchResultMessage)) {
				//Perform an assert for the SoftAssert
				softAssert.assertEquals(currentSearchResultMessage, expectedSearchResultMessage);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The expected search result was found (expected message of '" + expectedSearchResultMessage + "')");
				
				//Indicate that a search result did not match expectations, so no 'success' status is sent to the report
				correctSearchResult = true;
				
				//Exit the for loop
				break;
			}
			
			//Navigate to the next page of search results, if needed
			if (currentSearchResult % 9 == 0 && currentSearchResult != 0) {
				//Reset the search result counter back to 0 to start looking at the top search result of a page, again
				currentSearchResult = 0;
				
				//Click the 'Next Page' button
				reportLoggerMethods.click(nextPageButton, nextPageButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
			} else {
				//Increase the search result counter by 1 to look at the next search result on a page
				currentSearchResult++;
			}
		}
		
		//Output a failure status to the report, if the expected search result was not found
		if (!correctSearchResult) {
			//Perform an assert for the SoftAssert
			softAssert.assertEquals("Expected search result was not found", "Expected search result was found");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The expected search result was not found (expected message of '" + expectedSearchResultMessage + "')", "verifyPageLogSearchResults");
		}
	}
	
}