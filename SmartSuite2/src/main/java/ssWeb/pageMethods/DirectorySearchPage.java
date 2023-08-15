package ssWeb.pageMethods;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.DirectorySearchLocators;

public class DirectorySearchPage extends DirectorySearchLocators {
	
	//Constructor
	public DirectorySearchPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String firstName, String lastName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the search criteria");
		
		//Enter the 'First Name' search criteria
		reportLoggerMethods.sendKeys(firstNameField, firstNameFieldName, firstName);
		
		//Enter the 'Last Name' search criteria
		reportLoggerMethods.sendKeys(lastNameField, lastNameFieldName, lastName);
		
		//Click 'Search' to submit the search criteria
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	public void performSearch(String title, String firstName, String lastName, String department, String phoneNumber, String phoneNumberType, String listingAlias) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the search criteria");
		
		//Initialize Variable(s)
		phoneNumberType = phoneNumberType.toUpperCase();
		
		//Enter the 'Title' search criteria
		reportLoggerMethods.sendKeys(titleField, titleFieldName, title);
		
		//Enter the 'First Name' search criteria
		reportLoggerMethods.sendKeys(firstNameField, firstNameFieldName, firstName);
		
		//Enter the 'Last Name' search criteria
		reportLoggerMethods.sendKeys(lastNameField, lastNameFieldName, lastName);
		
		//Enter the 'Department' search criteria
		reportLoggerMethods.sendKeys(departmentField, departmentFieldName, department);
		
		//Enter the 'Phone Number' search criteria
		reportLoggerMethods.sendKeys(phoneNumberField, phoneNumberFieldName, phoneNumber);
		
		//Enter the 'Phone Number Type' search criteria
		reportLoggerMethods.selectDropDown(phoneNumberTypeDropDown, phoneNumberTypeDropDownName, phoneNumberType);
		
		//Enter the 'Listing Alias' search criteria
		reportLoggerMethods.sendKeys(listingAliasField, listingAliasFieldName, listingAlias);
		
		//Click 'Search' to submit the search criteria
		if (reportLoggerMethods.isDisplayed(searchButton, searchButtonName)) {
			reportLoggerMethods.click(searchButton, searchButtonName);
		} else if (reportLoggerMethods.isDisplayed(searchButton2, searchButton2Name)) {
			reportLoggerMethods.click(searchButton2, searchButton2Name);
		} else {
			//Output an error to the Extent Report & System due to no 'Directory Search' Button
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Directory Search' button was not found", "DirectorySearchButton");
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the department listing from the search results, if necessary
		if (reportLoggerMethods.isDisplayed(searchDepartmentsListing, searchDepartmentsListingName)) {
			reportLoggerMethods.click(searchDepartmentsListing, searchDepartmentsListingName);
		}
	}
	
	public int retrieveSearchResultCount() {
		if (reportLoggerMethods.isDisplayed(searchResultsCountLabel, searchResultsCountLabelName)) {
			//Retrieve the message that indicates the number of search results (Ex. 'Displaying 1 - 10 of 33')
			String searchResultsCountMessage = reportLoggerMethods.getLowercaseText(searchResultsCountLabel, searchResultsCountLabelName);
			
			//Split the message into parts, separated by spaces (Ex. 'Displaying', '1', '-', '10', 'of', and '33')
			List<String> searchResultsCountComponents = Arrays.asList(searchResultsCountMessage.split(" "));
			
			//Retrieve the last part of the message, which only has the total amount of search results
			String searchResultsCount = searchResultsCountComponents.get(searchResultsCountComponents.size()-1);
			
			//Return the number of search results
			return Integer.parseInt(searchResultsCount);
		} else {
			//Return 0 if the message was not found
			return 0;
		}
	}
	
	public void verifySearchedResults(SoftAssert softAssert, String name, String phoneNumber, String title, String department, String alias, String resultsRowCount, int excelRow, int excelColumn) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired search result(s) are present");
		
		//Initialize Variable(s)
		String successMessage = "Desired search result found";
		String failureMessage = "";
		int numOfSearchResults = retrieveSearchResultCount();
		boolean foundSearchEntry = false;
		boolean correctInfo = true;
		
		//Output the number of search results rows
		excelMethods.setDataTableCell(numOfSearchResults, excelRow, excelColumn);
		
		//Assert the status of the number of search results
		softAssert.assertEquals(numOfSearchResults, Integer.parseInt(resultsRowCount));
		
		//Check if 'Row Count' matches expectations for the report
		if (numOfSearchResults != Integer.parseInt(resultsRowCount)) {
			correctInfo = false;
			failureMessage += "Expected Row Count of '" + resultsRowCount + "', but found '" + numOfSearchResults + "'." + System.lineSeparator();
		}
		
		//Check if zero search results match expectation(s)
		if (numOfSearchResults == 0) {
			//Assert the status of no search result details to expectation(s)
			softAssert.assertEquals("", name);
			softAssert.assertEquals("", phoneNumber);
			softAssert.assertEquals("", title);
			softAssert.assertEquals("", department);
			softAssert.assertEquals("", alias);
			
			//Check if 'Name' matches expectations for the report
			if (!name.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Name of '" + name + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Phone Number' matches expectations for the report
			if (!phoneNumber.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Phone Number of '" + phoneNumber + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Title' matches expectations for the report
			if (!title.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Title of '" + title + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Department' matches expectations for the report
			if (!department.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Department of '" + department + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Alias' matches expectations for the report
			if (!alias.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Alias of '" + alias + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Output the assert status to the Extent Report & System
			if (correctInfo) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResults");
			}
			
			//Finish the method with an Extent Report status
			return;
		}
		
		//Check if the desired search result(s) are present
//		for (int i = 0; i < numOfSearchResults; i++) {
		for (int i = 0; i < 10; i++) {
			//Initialize Variable(s)
			String searchNameResult = reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i));
			String searchResultsPhoneNumberType = reportLoggerMethods.getText(searchResultsPhoneNumberTypeField.get(i), searchResultsPhoneNumberTypeFieldName(i));
			String searchResultsTitle = reportLoggerMethods.getText(searchResultsTitleField.get(i), searchResultsTitleFieldName(i));
			String searchResultsDepartment = reportLoggerMethods.getText(searchResultsDepartmentField.get(i), searchResultsDepartmentFieldName(i));
			String searchResultsAlias = reportLoggerMethods.getText(searchResultsAliasField.get(i), searchResultsAliasFieldName(i));
			
			//Check if a specific search result row has the relevant name before comparing info to expectations
			if (searchNameResult.equalsIgnoreCase(name) &&
					searchResultsPhoneNumberType.equalsIgnoreCase(phoneNumber) &&
					searchResultsTitle.equalsIgnoreCase(title) &&
					searchResultsDepartment.equalsIgnoreCase(department) &&
					searchResultsAlias.equalsIgnoreCase(alias)) {
				
				//Confirm that the desired search entry has been found
				foundSearchEntry = true;
				
				//Perform Soft Asserts on the relevant search entry's details
				softAssert.assertEquals(searchNameResult.toLowerCase(), name.toLowerCase());
				softAssert.assertEquals(searchResultsPhoneNumberType.toLowerCase(), phoneNumber.toLowerCase());
				softAssert.assertEquals(searchResultsTitle.toLowerCase(), title.toLowerCase());
				softAssert.assertEquals(searchResultsDepartment.toLowerCase(), department.toLowerCase());
				softAssert.assertEquals(searchResultsAlias.toLowerCase(), alias.toLowerCase());
				
				//Check if 'Name' matches expectations for the report
				if (!searchNameResult.equalsIgnoreCase(name)) {
					correctInfo = false;
					failureMessage += "Expected Name of '" + name + "', but found '" + searchNameResult + "'." + System.lineSeparator();
				}
				
				//Check if 'Phone Number' matches expectations for the report
				if (!searchResultsPhoneNumberType.equalsIgnoreCase(phoneNumber)) {
					correctInfo = false;
					failureMessage += "Expected Phone Number of '" + phoneNumber + "', but found '" + searchResultsPhoneNumberType + "'." + System.lineSeparator();
				}
				
				//Check if 'Title' matches expectations for the report
				if (!searchResultsTitle.equalsIgnoreCase(title)) {
					correctInfo = false;
					failureMessage += "Expected Title of '" + title + "', but found '" + searchResultsTitle + "'." + System.lineSeparator();
				}
				
				//Check if 'Department' matches expectations for the report
				if (!searchResultsDepartment.equalsIgnoreCase(department)) {
					correctInfo = false;
					failureMessage += "Expected Department of '" + department + "', but found '" + searchResultsDepartment + "'." + System.lineSeparator();
				}
				
				//Check if 'Alias' matches expectations for the report
				if (!searchResultsAlias.equalsIgnoreCase(alias)) {
					correctInfo = false;
					failureMessage += "Expected Alias of '" + alias + "', but found '" + searchResultsAlias + "'." + System.lineSeparator();
				}
				
				//Exit the for-loop, since the desired search result was found
				break;
			}
			
			try {
				//Navigate to the next page to check the next page's search results
				if (numOfSearchResults > 10 && i != 0 && i % 10 == 0) {
					reportLoggerMethods.click(nextPageOfSearchResultsButton, nextPageOfSearchResultsButtonName);
				}
			} catch (Exception e) {
				
			}
			
			//Navigate to the next page to check the next page's search results
			if (numOfSearchResults > 10 && i != 0 && i % 10 == 0 && reportLoggerMethods.isDisplayed(nextPageOfSearchResultsButton, nextPageOfSearchResultsButtonName)) {
				reportLoggerMethods.click(nextPageOfSearchResultsButton, nextPageOfSearchResultsButtonName);
			}
		}
		
		//Check if the desired search result(s) are present
		if (!foundSearchEntry) {
			//Assert the status of no search results when expecting a search result
			softAssert.assertEquals("Search results not found", "Search Results Found");
			
			//Setup a failure message for the Extent Report
			correctInfo = false;
			failureMessage += "Expected results not found." + System.lineSeparator() +
								"Expected Name = '" + name + "'" + System.lineSeparator() +
								"Expected Phone Number = '" + phoneNumber + "'" + System.lineSeparator() +
								"Expected Title = '" + title + "'" + System.lineSeparator() +
								"Expected Department = '" + department + "'" + System.lineSeparator() +
								"Expected Alias = '" + alias + "'" + System.lineSeparator();
		}
		
		//Output the assert status to the Extent Report & System
		if (correctInfo) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResults");
		}
	}
	
	// ~~~ Navigate Search Result Pages ~~~ //
	
	public void navigateNextPageOfSearchResults(SoftAssert softAssert, String nextPageLabelCheck) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Next Page' button for the search results page");
		
		//Click the 'Next Page' button
		reportLoggerMethods.click(nextPageOfSearchResultsButton, nextPageOfSearchResultsButtonName);
		
		//Initialize Variable(s)
		String searchResultsCountLabelStr = reportLoggerMethods.getText(searchResultsCountLabel, searchResultsCountLabelName);
		
		//Assert the current page of search results
		softAssert.assertEquals(searchResultsCountLabelStr, nextPageLabelCheck);
		
		//Output the assert status to the Extent Report & System
		if (searchResultsCountLabelStr.equalsIgnoreCase(nextPageLabelCheck)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Next Page' button returned the correct page of search results");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Next Page' button did not return the correct page of search results (Actual search results page label = '" + searchResultsCountLabelStr + "', expected search results page label = '" + nextPageLabelCheck + "')", "navigateNextPageOfSearchResults");
		}
	}
	
	public void navigateFirstPageOfSearchResults(SoftAssert softAssert, String firstPageLabelCheck) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'First Page' button for the search results page");
		
		//Click the 'First Page' button
		reportLoggerMethods.click(firstPageOfSearchResultsButton, firstPageOfSearchResultsButtonName);
		
		//Initialize Variable(s)
		String searchResultsCountLabelStr = reportLoggerMethods.getText(searchResultsCountLabel, searchResultsCountLabelName);
		
		//Assert the current page of search results
		softAssert.assertEquals(searchResultsCountLabelStr, firstPageLabelCheck);
		
		//Output the assert status to the Extent Report & System
		if (searchResultsCountLabelStr.equalsIgnoreCase(firstPageLabelCheck)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'First Page' button returned the correct page of search results");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'First Page' button did not return the correct page of search results (Actual search results page label = '" + searchResultsCountLabelStr + "', expected search results page label = '" + firstPageLabelCheck + "')", "navigateSearchResultsPages");
		}
	}
	
	public void navigateFastForwardPageOfSearchResults(SoftAssert softAssert, String fastForwardPageLabelCheck) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Fast Forward' button for the search results page");
		
		//Click the 'Fast Forward' button
		reportLoggerMethods.click(fastForwardPageOfSearchResultsButton, fastForwardPageOfSearchResultsButtonName);
		
		//Initialize Variable(s)
		String searchResultsCountLabelStr = reportLoggerMethods.getText(searchResultsCountLabel, searchResultsCountLabelName);
		
		//Assert the current page of search results
		softAssert.assertEquals(searchResultsCountLabelStr, fastForwardPageLabelCheck);
		
		//Output the assert status to the Extent Report & System
		if (searchResultsCountLabelStr.equalsIgnoreCase(fastForwardPageLabelCheck)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Fast Forward Page' button returned the correct page of search results");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Fast Forward Page' button did not return the correct page of search results (Actual search results page label = '" + searchResultsCountLabelStr + "', expected search results page label = '" + fastForwardPageLabelCheck + "')", "navigateSearchResultsPages");
		}
	}
	
	// ~~~ Send a Page ~~~ //
	
	public void sendPage(String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Sending a page");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the message
		reportLoggerMethods.sendKeys(pageMessageField, pageMessageFieldName, message);
		
		//Submit the message
		reportLoggerMethods.click(sendPageButton, sendPageButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert
		reportLoggerMethods.acceptAlert();
	}
	
	public void sendPageViaSearchResult(String name, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Locating & sending a page from the desired search result");
		
		//Locate the desired search result
		for (int i = 0; i < reportLoggerMethods.getSize(searchResultsNameField, searchResultsNameFieldName); i++) {
			//Check if the name matches expectations
			if (reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i)).equalsIgnoreCase(name)) {
				//Click the 'Send Page' button
				reportLoggerMethods.click(pageButton.get(i), pageButtonName(i));
				break;
			}
		}
		
		//Send a page
		sendPage(message);
	}
	
	public void sendPageViaDetailView(String name, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired search result(s) are present");
		
		//Initialize Variable(s)
		boolean foundSearchEntry = false;
		int searchResultRow = -1;
		
		//Handle the error of no search results being found/returned
		if (reportLoggerMethods.getSize(searchResultsRowList, searchResultsRowListName) == 0) {
			//Output the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No search results returned - unable to send page via Detail View with no search results", "sendPageViaDetailView");
			
			//Perform the assert regarding no search results (which causes the script to end & fail)
			Assert.assertEquals("No search result(s) found", "Search result(s) returned successfully");
		}
		
		//Check if the desired search result(s) are present
		for (int i = 0; i < reportLoggerMethods.getSize(searchResultsRowList, searchResultsRowListName); i++) {
			//Check if a specific search result row has the relevant name before comparing info to expectations
			if (reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i)).equalsIgnoreCase(name)) {
				//Confirm that the desired search entry has been found
				foundSearchEntry = true;
				
				//Indicate which row of the search results matches expectations
				searchResultRow = i;
			}
		}
		
		//Check if the desired search result(s) are present
		if (foundSearchEntry) {
			//Click the name of the search result to bring up the Detail View
			reportLoggerMethods.click(searchResultsNameLink.get(searchResultRow), searchResultsNameLinkName(searchResultRow));
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Click the 'Send Page' button to bring up the 'Send Page' pop-up
			reportLoggerMethods.click(sendPageDetailViewButton.get(1), sendPageDetailViewButtonName);
			
			//Send a page
			sendPage(message);
		} else {
			//Output the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The desired search result not found - unable to send page via Detail View without the expected search result", "sendPageViaDetailView");
			
			//Perform the assert regarding no search results (which causes the script to end & fail)
			Assert.assertEquals("Expected search result not found", "Expected Search result found");
		}
	}
	
	// ~~~ Verify 'Send to All Devices'  ~~~ //
	
	public void verifySendToAllDevicesIcon(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the 'Send to all devices' icon appears in the Directory search results");
		
		try {
			//Check if the 'Send to all devices' icon appeared, as expected
			if (reportLoggerMethods.isDisplayed(sendToAllDevicesIcon, sendToAllDevicesIconName)) {
				//Perform assert on the 'Send to all devices' icon being displayed
				softAssert.assertEquals("The 'Send to all devices' icon is displayed", "The 'Send to all devices' icon is displayed");
				
				//Output the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Send to all devices' icon appeared, as expected");
			} else {
				//Perform assert on the 'Send to all devices' icon being displayed
				softAssert.assertEquals("The 'Send to all devices' icon is not displayed", "The 'Send to all devices' icon is displayed");
				
				//Output the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Send to all devices' icon is not displayed", "verifySendToAllDevicesIcon");
			}
		} catch (Exception e) {
			//Perform assert on the 'Send to all devices' icon being displayed
			softAssert.assertEquals("The 'Send to all devices' icon is not displayed", "The 'Send to all devices' icon is displayed");
			
			//Output the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Send to all devices' icon is not displayed", "verifySendToAllDevicesIcon");
		}
	}
	
	public void verifyNoSendToAllDevicesIcon(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the 'Send to all devices' icon does not appear in the Directory search results");
		
		try {
			//Check if the 'Send to all devices' icon appeared, as expected
			if (reportLoggerMethods.isDisplayed(sendToAllDevicesIcon, sendToAllDevicesIconName)) {
				//Perform assert on the 'Send to all devices' icon being displayed
				softAssert.assertEquals("The 'Send to all devices' icon is displayed", "The 'Send to all devices' icon is not displayed");
				
				//Output the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Send to all devices' icon is displayed", "verifyNoSendToAllDevicesIcon");
			} else {
				//Perform assert on the 'Send to all devices' icon being displayed
				softAssert.assertEquals("The 'Send to all devices' icon is not displayed", "The 'Send to all devices' icon is not displayed");
				
				//Output the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Send to all devices' icon is not displayed, as expected");
			}
		} catch (Exception e) {
			//Perform assert on the 'Send to all devices' icon being displayed
			softAssert.assertEquals("The 'Send to all devices' icon is not displayed", "The 'Send to all devices' icon is not displayed");
			
			//Output the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Send to all devices' icon is not displayed, as expected");
		}
	}
	
}