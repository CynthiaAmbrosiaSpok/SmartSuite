package ssWeb.pageMethods;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PagingSearchLocators;

public class PagingSearchPage extends PagingSearchLocators {
	
	//Constructor
	public PagingSearchPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Paging Search Title/Tab ~~~ //
	
	public void verifyActivePagingPage(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the 'Paging Search' page is the current page");
		
		//Check that the 'Paging Search' page is the current page
		if (reportLoggerMethods.getAttribute(pagingSearchTitle, pagingSearchTitleName, "style").contains("color: white;")) {
			//Report the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Paging Search' page is currently active", "The 'Paging Search' page is currently active");
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Paging Search' page is currently active");
		} else {
			//Report the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Paging Search' page is not currently active", "The 'Paging Search' page is currently active");
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Paging Search' page is not currently active", "verifyActivePagingPage");
		}
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String firstName, String lastName, String department, String groupFunctionName, String id) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the search criteria");
		
		//Enter the 'First Name' search criteria
		reportLoggerMethods.sendKeys(firstNameField, firstNameFieldName, firstName);
		
		//Enter the 'Last Name' search criteria
		reportLoggerMethods.sendKeys(lastNameField, lastNameFieldName, lastName);
		
		//Enter the 'Department' search criteria
		reportLoggerMethods.sendKeys(departmentField, departmentFieldName, department);
		
		//Enter the 'Group/Function Name' search criteria
		reportLoggerMethods.sendKeys(groupFunctionNameField, groupFunctionNameFieldName, groupFunctionName);
		
		//Enter the 'ID' search criteria
		reportLoggerMethods.sendKeys(idField, idFieldName, id);
		
		//Click 'Search' to submit the search criteria
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	public void performNameSearch(String firstName, String lastName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the search criteria");
		
		//Enter the 'First Name' search criteria
		reportLoggerMethods.sendKeys(firstNameField, firstNameFieldName, firstName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(600);
		
		//Enter the 'Last Name' search criteria
		reportLoggerMethods.sendKeys(lastNameField, lastNameFieldName, lastName);
		
		//Click 'Search' to submit the search criteria
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	public int retrieveSearchResultCount() {
		try {
			//Retrieve the message that indicates the number of search results (Ex. 'Displaying 1 - 10 of 33')
			String searchResultsCountMessage = searchResultsCountLabel.getText().toLowerCase();
			
			//Split the message into parts, separated by spaces (Ex. 'Displaying', '1', '-', '10', 'of', and '33')
			List<String> searchResultsCountComponents = Arrays.asList(searchResultsCountMessage.split(" "));
			
			//Retrieve the last part of the message, which only has the total amount of search results
			String searchResultsCount = searchResultsCountComponents.get(searchResultsCountComponents.size()-1);
			
			//Return the number of search results
			return Integer.parseInt(searchResultsCount);
		} catch (Exception e) {
			//Return 0 if the message was not found
			return 0;
		}
	}
	
	public void verifySearchedResults(SoftAssert softAssert, String name, String department, String status, String type, String id, String resultsRowCount, int excelRow, int excelColumn) {
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
		
		//Check 'Row Count' match expectations for the report
		if (numOfSearchResults != Integer.parseInt(resultsRowCount)) {
			correctInfo = false;
			failureMessage += "Expected Row Count of '" + resultsRowCount + "', but found '" + numOfSearchResults + "'." + System.lineSeparator();
		}
		
		if (numOfSearchResults == 0) {
			//Compare the non-existent search results to expectations
			softAssert.assertEquals("", name);
			softAssert.assertEquals("", department);
			softAssert.assertEquals("", status);
			softAssert.assertEquals("", type);
			softAssert.assertEquals("", id);
			
			//Check if 'Name' matches expectations for the report
			if (!name.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Name of '" + name + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Department' matches expectations for the report
			if (!department.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Department of '" + department + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Status' matches expectations for the report
			if (!status.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Status of '" + status + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'Type' matches expectations for the report
			if (!type.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Type of '" + type + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check if 'ID' matches expectations for the report
			if (!id.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected ID of '" + id + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Report the assert status to the Extent Report & System
			if (correctInfo) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResults");
			}
			
			//Finish the method with an Extent Report status
			return;
		}
		
		//Check if the desired search result(s) are present
		for (int i = 0; i < numOfSearchResults; i++) {
			//Initialize Variable(s)
			String searchResultsName = reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i));
			String searchResultsDepartment = reportLoggerMethods.getText(searchResultsDepartmentField.get(i), searchResultsDepartmentFieldName(i));
			String searchResultsStatus = reportLoggerMethods.getText(searchResultsStatusField.get(i), searchResultsStatusFieldName(i));
			String searchResultsTypeDropDown = reportLoggerMethods.getText(searchResultsTypeDropDownValue.get(i), searchResultsTypeDropDownValueName(i));
			String searchResultsID = reportLoggerMethods.getText(searchResultsIDField.get(i), searchResultsIDFieldName(i));
			
			//Check if a specific search result row has the relevant name before comparing info to expectations
			if (searchResultsName.equalsIgnoreCase(name) &&
					searchResultsDepartment.equalsIgnoreCase(department) &&
					searchResultsStatus.equalsIgnoreCase(status) &&
					searchResultsTypeDropDown.equalsIgnoreCase(type) &&
					searchResultsID.equalsIgnoreCase(id)) {
				
				//Confirm that the desired search entry has been found
				foundSearchEntry = true;
				
				//Perform Soft Asserts on the relevant search entry's details
				softAssert.assertEquals(searchResultsName.toLowerCase(), name.toLowerCase());
				softAssert.assertEquals(searchResultsDepartment.toLowerCase(), department.toLowerCase());
				softAssert.assertEquals(searchResultsStatus.toLowerCase(), status.toLowerCase());
				softAssert.assertEquals(searchResultsTypeDropDown.toLowerCase(), type.toLowerCase());
				softAssert.assertEquals(searchResultsID.toLowerCase(), id.toLowerCase());
				
				//Check 'Name' match expectations for the report
				if (!searchResultsName.equalsIgnoreCase(name)) {
					correctInfo = false;
					failureMessage += "Expected Name of '" + name + "', but found '" + searchResultsName + "'." + System.lineSeparator();
				}
				
				//Check 'Department' match expectations for the report
				if (!searchResultsDepartment.equalsIgnoreCase(department)) {
					correctInfo = false;
					failureMessage += "Expected Department of '" + department + "', but found '" + searchResultsDepartment + "'." + System.lineSeparator();
				}
				
				//Check 'Status' match expectations for the report
				if (!searchResultsStatus.equalsIgnoreCase(status)) {
					correctInfo = false;
					failureMessage += "Expected Status of '" + status + "', but found '" + searchResultsStatus + "'." + System.lineSeparator();
				}
				
				//Check 'Type' match expectations for the report
				if (!searchResultsTypeDropDown.equalsIgnoreCase(type)) {
					correctInfo = false;
					failureMessage += "Expected Type of '" + type + "', but found '" + searchResultsTypeDropDown + "'." + System.lineSeparator();
				}
				
				//Check 'ID' match expectations for the report
				if (!searchResultsID.equalsIgnoreCase(id)) {
					correctInfo = false;
					failureMessage += "Expected ID of '" + id + "', but found '" + searchResultsID + "'." + System.lineSeparator();
				}
				
				//Exit the for-loop, since the desired search result was found
				break;
			}
		}
		
		//Check if the desired search result(s) are present
		if (!foundSearchEntry) {
			//Assert the status of no search results when expecting a search result
			softAssert.assertEquals("Search results not found", "Search Results Found");
			
			//Setup a failure message for the Extent Report
			correctInfo = false;
			failureMessage += "Expected results not found." + System.lineSeparator() + System.lineSeparator() +
								"Expected Name = '" + name + "'" + System.lineSeparator() +
								"Expected Department = '" + department + "'" + System.lineSeparator() +
								"Expected Status = '" + status + "'" + System.lineSeparator() +
								"Expected Type = '" + type + "'" + System.lineSeparator() +
								"Expected ID = '" + id + "'" + System.lineSeparator();
		}
		
		//Report the assert status to the Extent Report & System
		if (correctInfo) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResults");
		}
	}
	
	// ~~~ Send a Page ~~~ //
	
	public void sendPage(String name, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Locating then sending a page from the desired search result");
		
		//Initialize Variable(s)
		boolean foundExpectedResult = false;
		int searchRow = -1;
		
		//Locate the relevant desired search result & send the page
		for (int i = 0; i < reportLoggerMethods.getSize(searchResultsNameField, searchResultsNameFieldName); i++) {
			//Check if the full name matches expectations
			if (reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i)).equalsIgnoreCase(name)) {
				//Indicate that the expected Paging Search Result was found
				foundExpectedResult = true;
				
				//Indicate which row the expected Paging Search Result was found in
				searchRow = i;
			}
		}
		
		//Check if the expected result was found
		if (foundExpectedResult) {
			//Click the 'Add Recipient List' button
			reportLoggerMethods.click(addRecipientList.get(searchRow), addRecipientListName(searchRow));
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(600);
			
			//Click the 'Compose Message' button
			reportLoggerMethods.click(composeMessageButton, composeMessageButtonName);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(2000);
			
			//Enter the message
			reportLoggerMethods.sendKeys(pageMessageField, pageMessageFieldName, message);
			
			//Submit the message
			reportLoggerMethods.click(sendPageButton, sendPageButtonName);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert/pop-up
			reportLoggerMethods.acceptAlert();
		} else {
			//Did not locate the expected Paging Search - send an error to the Extent Report
			reportLoggerMethods.reportFailedCheckpoint("Failure: Did not find the expected search result from the Paging Search", "sendPage");
			
			//Assert a failure do to not locating the expected Paging Search Result
			Assert.assertEquals("Did not find the Paging Search Result of '" + name + "'", "Found the Paging Search Result of '" + name + "'");
		}
	}
	
	// ~~~ Personal Group ~~~ //
	
	public void deleteAllPersonalMessageGroups() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting all Personal Message Groups");
		
		//Delete all Personal Message Groups
		while (reportLoggerMethods.isDisplayed(firstPersonalMessageDeleteButton, firstPersonalMessageDeleteButtonName)) {
			//Select the first Personal Message Group on the list
			reportLoggerMethods.click(firstPersonalMessageDeleteButton, firstPersonalMessageDeleteButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public void createPersonalMessageGroup(String id, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a Personal Message Group");
		
		//Initialize Variable(s)
		boolean foundExpectedResult = false;
		int searchRow = -1;
		
		//Locate the relevant desired search result & send the page
		for (int i = 0; i < reportLoggerMethods.getSize(searchResultsIDField, searchResultsIDFieldName); i++) {
			//Check if the full name matches expectations]
			if (reportLoggerMethods.getText(searchResultsIDField.get(i), searchResultsIDFieldName(i)).equalsIgnoreCase(id)) {
				//Indicate that the expected Paging Search Result was found
				foundExpectedResult = true;
				
				//Indicate which row the expected Paging Search Result was found in
				searchRow = i;
			}
		}
		
		//Check if the expected result was found
		if (foundExpectedResult) {
			//Click the 'Add Recipient List' button
			reportLoggerMethods.click(addRecipientList.get(searchRow), addRecipientListName(searchRow));
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Enter the 'Personal Message Group' message
			reportLoggerMethods.sendKeys(personalMessageGroupField, personalMessageGroupFieldName, message);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Click to save the message
			reportLoggerMethods.click(personalMessageGroupSaveButton, personalMessageGroupSaveButtonName);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(3000);
			
			//Accept the alert/pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Clear the selected Recipients
			reportLoggerMethods.click(clearRecipientsButton, clearRecipientsButtonName);
		} else {
			//Did not locate the expected Paging Search - send an error to the Extent Report
			reportLoggerMethods.reportFailedCheckpoint("Failure: Did not find the expected search result from the Paging Search", "sendPage");
			
			//Assert a failure do to not locating the expected Paging Search Result
			Assert.assertEquals("Did not find the Paging Search Result of '" + id + "'", "Found the Paging Search Result of '" + id + "'");
		}
	}
	
	public void sendPageViaPersonalMessageGroup(String message) {
		//Output an info status to the Extent Report & Syste
		reportLoggerMethods.reportInfo("Sending a page from the Personal Message Group");
		
		//Click the relevant Personal Message Group
		reportLoggerMethods.click(firstPersonalMessageListing, firstPersonalMessageListingName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the 'Compose Message' button
		reportLoggerMethods.click(composeMessageButton, composeMessageButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(2000);
		
		//Enter the message
		reportLoggerMethods.sendKeys(pageMessageField, pageMessageFieldName, message);
		
		//Submit the message
		reportLoggerMethods.click(sendPageButton, sendPageButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	public void verifyPersonalMessageGroup(SoftAssert softAssert, String expectedPersonalMessageGroup) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Personal Message Group was created successfully");
		
		try {
			//Check if the Personal Message Group was created successfully
			if (firstPersonalMessageListing.getText().equalsIgnoreCase(expectedPersonalMessageGroup)) {
				//Report the assert status to the Extent Report & System
				softAssert.assertEquals("The Personal Message Group was created successfully", "The Personal Message Group was created successfully");
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Personal Message Group was created successfully");
			} else {
				//Report the assert status to the Extent Report & System
				softAssert.assertEquals("The Personal Message Group was not created successfully", "The Personal Message Group was created successfully");
				reportLoggerMethods.reportFailedCheckpoint("Failure: The Personal Message Group was not created successfully", "verifyPersonalMessageGroup");
			}
		} catch (Exception e) {
			//Report the assert status to the Extent Report & System
			softAssert.assertEquals("The Personal Message Group was not created successfully", "The Personal Message Group was created successfully");
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Personal Message Group was not created successfully", "verifyPersonalMessageGroup");
		}
	}
	
}