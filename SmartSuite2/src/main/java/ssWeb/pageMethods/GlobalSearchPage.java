package ssWeb.pageMethods;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.GlobalSearchLocators;

public class GlobalSearchPage extends GlobalSearchLocators {
	
	//Constructor
	public GlobalSearchPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Global Search Tab's Advanced Search Links ~~~ //
	
	public void clickAdvancedDirectorySearch() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Advanced Directory Search' link");
		
		//Click the 'Advanced Directory Search' link
		reportLoggerMethods.click(advancedDirectorySearchLink, advancedDirectorySearchLinkName);
	}
	
	public void clickAdvancedPagingSearch() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Advanced Paging Search' link");
		
		//Click the 'Advanced Paging Search' link
		reportLoggerMethods.click(advancedPagingSearchLink, advancedPagingSearchLinkName);
	}
	
	public void clickAdvancedOncallSearch() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Advanced Oncall Search' link");
		
		//Click the 'Advanced Directory Search' link
		reportLoggerMethods.click(advancedOncallSearchLink, advancedOncallSearchLinkName);
	}
	
	// ~~~ Perform Search & Search Checkboxes ~~~ //
	
	public void selectProfileCheckox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Selecting the 'Profile' checkbox");
		
		//Ensure that the 'Profile' checkbox is checked
		if (!reportLoggerMethods.isSelected(profileSearchCriteriaCheckbox, profileSearchCriteriaCheckboxName)) {
			reportLoggerMethods.click(profileSearchCriteriaCheckbox, profileSearchCriteriaCheckboxName);
		}
	}
	
	public void unselectProfileCheckox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Unselecting the 'Profile' checkbox");
		
		//Ensure that the 'Profile' checkbox is un-checked
		if (reportLoggerMethods.isSelected(profileSearchCriteriaCheckbox, profileSearchCriteriaCheckboxName)) {
			reportLoggerMethods.click(profileSearchCriteriaCheckbox, profileSearchCriteriaCheckboxName);
		}
	}
	
	public void selectOnCallCheckox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Selecting the 'On Call' checkbox");
		
		//Ensure that the 'On Call' checkbox is checked
		if (!reportLoggerMethods.isSelected(onCallSearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName)) {
			reportLoggerMethods.click(onCallSearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName);
		}
	}
	
	public void unselectOnCallCheckox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Unselecting the 'On Call' checkbox");
		
		//Ensure that the 'On Call' checkbox is un-checked
		if (reportLoggerMethods.isSelected(onCallSearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName)) {
			reportLoggerMethods.click(onCallSearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName);
		}
	}
	
	public void selectDirectoryCheckox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Selecting the 'Directory' checkbox");
		
		//Ensure that the 'Directory' checkbox is checked
		if (!reportLoggerMethods.isSelected(directorySearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName)) {
			reportLoggerMethods.click(directorySearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName);
		}
	}
	
	public void unselectDirectoryCheckox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Unselecting the 'Directory' checkbox");
		
		//Ensure that the 'Directory' checkbox is un-checked
		if (reportLoggerMethods.isSelected(directorySearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName)) {
			reportLoggerMethods.click(directorySearchCriteriaCheckbox, onCallSearchCriteriaCheckboxName);
		}
	}
	
	public void checkSearchCheckboxes(String profileCheckbox, String onCallCheckbox, String directoryCheckbox) {
		//Check if the 'Profile' checkbox needs to be selected or unselected
		if (profileCheckbox.equalsIgnoreCase("yes") || profileCheckbox.equalsIgnoreCase("y")) {
			selectProfileCheckox();
		} else {
			unselectProfileCheckox();
		}
		
		//Check if the 'On Call' checkbox needs to be selected or unselected
		if (onCallCheckbox.equalsIgnoreCase("yes") || onCallCheckbox.equalsIgnoreCase("y")) {
			selectOnCallCheckox();
		} else {
			unselectOnCallCheckox();
		}
		
		//Check if the 'Directory' checkbox needs to be selected or unselected
		if (directoryCheckbox.equalsIgnoreCase("yes") || directoryCheckbox.equalsIgnoreCase("y")) {
			selectDirectoryCheckox();
		} else {
			unselectDirectoryCheckox();
		}
	}
	
	public void setMaxSearchRows(String rowCount) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Setting the maximum number of rows being displayed for the search results");
		
		//Clear any existing value in the field
		reportLoggerMethods.clear(setMaxSearchRowsField, setMaxSearchRowsFieldName);
		
		//Set the maximum number of rows being displayed for the search results
		reportLoggerMethods.sendKeys(setMaxSearchRowsField, setMaxSearchRowsFieldName, rowCount);
	}
	
	public void performSearch(String searchCriteria) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a search with the value: " + searchCriteria);
		
		//Enter search criteria
		reportLoggerMethods.sendKeys(searchCriteriaField, searchCriteriaFieldName, searchCriteria);
		
		//Click 'Search' to perform the search
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	// ~~~ Verify Searches ~~~ //
	
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
	
	public void verifyNoSearchResults(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if there are no current search results");
		
		//Initialize Variable(s)
		int numOfSearchResults = retrieveSearchResultCount();
		
		//Check if there are no current search results
		softAssert.assertEquals(numOfSearchResults, 0);
		
		//Report the assert status to the Extent Report & System
		if (numOfSearchResults != 0) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are search results, when there should be none", "verifyNoSearchResults");
		} else {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: There are no search results after refreshing the 'Global Search' page");
		}
	}
	
	public void verifySearchedResults(SoftAssert softAssert, String name, String type, String title, String department, String specialty, String alias, String resultsRowCount, int excelRow, int excelColumn) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired search result(s) are present");
		
		//Initialize Variable(s)
		String successMessage = "Desired search result found";
		String failureMessage = "";
		int numOfSearchResults = retrieveSearchResultCount();
		boolean foundSearchEntry = false;
		boolean correctInfo = true;
		
		//Set the row count expectations to zero, if expecting an error message
		if (resultsRowCount.equalsIgnoreCase("error message")) {
			resultsRowCount = "0";
		}
		
		//Output the number of search results rows
		excelMethods.setDataTableCell(numOfSearchResults, excelRow, excelColumn);
		
		//Assert the status of the number of search results
		softAssert.assertEquals(numOfSearchResults, Integer.parseInt(resultsRowCount));
		
		//Check 'Row Count' match expectations for the report
		if (numOfSearchResults != Integer.parseInt(resultsRowCount)) {
			correctInfo = false;
			failureMessage += "Expected Row Count of '" + resultsRowCount + "', but found '" + numOfSearchResults + "'." + System.lineSeparator();
		}
		
		//Check if zero search results match expectation(s)
		if (numOfSearchResults == 0) {
			//Compare the non-existent search results to expectations
			softAssert.assertEquals("", name);
			softAssert.assertEquals("", type);
			softAssert.assertEquals("", title);
			softAssert.assertEquals("", department);
			softAssert.assertEquals("", specialty);
			softAssert.assertEquals("", alias);
			
			//Check 'Name' match expectations for the report
			if (!name.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Name of '" + name + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Type' match expectations for the report
			if (!type.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Type of '" + type + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Title' match expectations for the report
			if (!title.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Title of '" + title + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Department' match expectations for the report
			if (!department.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Department of '" + department + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Specialty' match expectations for the report
			if (!specialty.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Specialty of '" + specialty + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Alias' match expectations for the report
			if (!alias.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Alias of '" + alias + "', but there are no search results to be found." + System.lineSeparator();
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
			String searchResultsType = reportLoggerMethods.getText(searchResultsTypeField.get(i), searchResultsTypeFieldName(i));
			String searchResultsTitle = reportLoggerMethods.getText(searchResultsTitleField.get(i), searchResultsTitleFieldName(i));
			String searchResultsDepartment = reportLoggerMethods.getText(searchResultsDepartmentField.get(i), searchResultsDepartmentFieldName(i));
			String searchResultsSpecialty = reportLoggerMethods.getText(searchResultsSpecialtyField.get(i), searchResultsSpecialtyFieldName(i));
			String searchResultsAlias = reportLoggerMethods.getText(searchResultsAliasField.get(i), searchResultsAliasFieldName(i));
			
			//Check if a specific search result row has the relevant name before comparing info to expectations
			if (searchResultsName.equalsIgnoreCase(name) &&
					searchResultsType.equalsIgnoreCase(type) &&
					searchResultsTitle.equalsIgnoreCase(title) &&
					searchResultsDepartment.equalsIgnoreCase(department) &&
					searchResultsSpecialty.equalsIgnoreCase(specialty) &&
					searchResultsAlias.equalsIgnoreCase(alias)) {
				
				//Confirm that the desired search entry has been found
				foundSearchEntry = true;
				
				//Perform Soft Asserts on the relevant search entry's details
				softAssert.assertEquals(searchResultsName.toLowerCase(), name.toLowerCase());
				softAssert.assertEquals(searchResultsType.toLowerCase(), type.toLowerCase());
				softAssert.assertEquals(searchResultsTitle.toLowerCase(), title.toLowerCase());
				softAssert.assertEquals(searchResultsDepartment.toLowerCase(), department.toLowerCase());
				softAssert.assertEquals(searchResultsSpecialty.toLowerCase(), specialty.toLowerCase());
				softAssert.assertEquals(searchResultsAlias.toLowerCase(), alias.toLowerCase());
				
				//Check 'Name' match expectations for the report
				if (!searchResultsName.equalsIgnoreCase(name)) {
					correctInfo = false;
					failureMessage += "Expected Name of '" + name + "', but found '" + searchResultsName + "'." + System.lineSeparator();
				}
				
				//Check 'Type' match expectations for the report
				if (!searchResultsType.equalsIgnoreCase(type)) {
					correctInfo = false;
					failureMessage += "Expected Type of '" + type + "', but found '" + searchResultsType + "'." + System.lineSeparator();
				}
				
				//Check 'Title' match expectations for the report
				if (!searchResultsTitle.equalsIgnoreCase(title)) {
					correctInfo = false;
					failureMessage += "Expected Title of '" + title + "', but found '" + searchResultsTitle + "'." + System.lineSeparator();
				}
				
				//Check 'Department' match expectations for the report
				if (!searchResultsDepartment.equalsIgnoreCase(department)) {
					correctInfo = false;
					failureMessage += "Expected Department of '" + department + "', but found '" + searchResultsDepartment + "'." + System.lineSeparator();
				}
				
				//Check 'Specialty' match expectations for the report
				if (!searchResultsSpecialty.equalsIgnoreCase(specialty)) {
					correctInfo = false;
					failureMessage += "Expected Specialty of '" + specialty + "', but found '" + searchResultsSpecialty + "'." + System.lineSeparator();
				}
				
				//Check 'Alias' match expectations for the report
				if (!searchResultsAlias.equalsIgnoreCase(alias)) {
					correctInfo = false;
					failureMessage += "Expected Alias of '" + alias + "', but found '" + searchResultsAlias + "'." + System.lineSeparator();
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
								"Expected Type = '" + type + "'" + System.lineSeparator() +
								"Expected Title = '" + title + "'" + System.lineSeparator() +
								"Expected Department = '" + department + "'" + System.lineSeparator() +
								"Expected Specialty = '" + specialty + "'" + System.lineSeparator() +
								"Expected Alias = '" + alias + "'" + System.lineSeparator();
		}
		
		//Report the assert status to the Extent Report & System
		if (correctInfo) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResults");
		}
	}
	
	public void verifyMaxRowStatus(SoftAssert softAssert, String expectedRowCount) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if an error message relating to 'Set Max Rows' appeared");
		
		try {
			//Initialize Variable(s)
			String errorMsg = eDriver.switchTo().alert().getText();
			
			//Check if the alert/error-message appears as expected and Report the assert status to the Extent Report & System
			if (expectedRowCount.equalsIgnoreCase("error message")) {
				softAssert.assertEquals("'Set max rows' error message exists", "'Set max rows' error message exists");
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The error message for a non-valid 'Set Max Row' value has appeared");
			} else {
				softAssert.assertEquals(errorMsg, setMaxSearchRowsField.getAttribute("value"));
				reportLoggerMethods.reportFailedCheckpoint("Failure: The value: '" + setMaxSearchRowsField.getAttribute("value") + "' cannot be used to set the max amount of rows for search results since it is not an integer", "verifyMaxRowStatus");
			}
			
			//If an alert message appeared, click 'Ok'
			reportLoggerMethods.acceptAlert();
		} catch (Exception e) {
			//Check if the alert/error-message appears as expected and Report the assert status to the Extent Report & System
			if (expectedRowCount.equalsIgnoreCase("error message")) {
				softAssert.assertEquals("No 'Set max rows' error message exists", "'Set max rows' error message exists");
				reportLoggerMethods.reportFailedCheckpoint("Failure: The error message for a non-valid 'Set Max Row' value did not appear", "verifyMaxRowStatus");
			}
		}
	}
	
	public String retrieveMessageID(int searchResultRow) {
		//Initialize Variable(s)
		String messageID;
		
		//Open a search result pop-up
		reportLoggerMethods.click(searchResultsNameLink.get(searchResultRow), searchResultsNameLinkName(searchResultRow));
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Retrieve the Message ID
		messageID = reportLoggerMethods.getText(messageIDLabel, messageIDLabelName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Close the search result pop-up
		reportLoggerMethods.click(closeSearchResultPopup, closeSearchResultPopupName);
		
		//Return the Message ID
		return messageID;
	}
	
	public void verifySearchedResultsWithMessageID(SoftAssert softAssert, String messageID, String name, String type, String title, String department, String specialty, String alias, String resultsRowCount, int excelRow, int excelColumn) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired search result(s) are present");
		
		//Initialize Variable(s)
		String successMessage = "Desired search result found";
		String failureMessage = "";
		int numOfSearchResults = retrieveSearchResultCount();
		boolean foundSearchEntry = false;
		boolean correctInfo = true;
		
		//Set the row count expectations to zero, if expecting an error message
		if (resultsRowCount.equalsIgnoreCase("error message")) {
			resultsRowCount = "0";
		}
		
		//Output the number of search results rows
		excelMethods.setDataTableCell(numOfSearchResults, excelRow, excelColumn);
		
		//Assert the status of the number of search results
		softAssert.assertEquals(numOfSearchResults, Integer.parseInt(resultsRowCount));
		
		//Check 'Row Count' match expectations for the report
		if (numOfSearchResults != Integer.parseInt(resultsRowCount)) {
			correctInfo = false;
			failureMessage += "Expected Row Count of '" + resultsRowCount + "', but found '" + numOfSearchResults + "'." + System.lineSeparator();
		}
		
		//Check if zero search results match expectation(s)
		if (numOfSearchResults == 0) {
			//Compare the non-existent search results to expectations
			softAssert.assertEquals("", messageID);
			softAssert.assertEquals("", name);
			softAssert.assertEquals("", type);
			softAssert.assertEquals("", title);
			softAssert.assertEquals("", department);
			softAssert.assertEquals("", specialty);
			softAssert.assertEquals("", alias);
			
			//Check 'Message ID' match expectations for the report
			if (!messageID.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Message ID of '" + messageID + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Name' match expectations for the report
			if (!name.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Name of '" + name + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Type' match expectations for the report
			if (!type.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Type of '" + type + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Title' match expectations for the report
			if (!title.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Title of '" + title + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Department' match expectations for the report
			if (!department.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Department of '" + department + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Specialty' match expectations for the report
			if (!specialty.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Specialty of '" + specialty + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Alias' match expectations for the report
			if (!alias.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Alias of '" + alias + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Report the assert status to the Extent Report & System
			if (correctInfo) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResultsWithMessageID");
			}
			
			//Finish the method with an Extent Report status
			return;
		}
		
		//Check if the desired search result(s) are present
		for (int i = 0; i < numOfSearchResults; i++) {
			//Initialize Variable(s)
			String searchResultsName = reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i));
			String searchResultsType = reportLoggerMethods.getText(searchResultsTypeField.get(i), searchResultsTypeFieldName(i));
			String searchResultsTitle = reportLoggerMethods.getText(searchResultsTitleField.get(i), searchResultsTitleFieldName(i));
			String searchResultsDepartment = reportLoggerMethods.getText(searchResultsDepartmentField.get(i), searchResultsDepartmentFieldName(i));
			String searchResultsSpecialty = reportLoggerMethods.getText(searchResultsSpecialtyField.get(i), searchResultsSpecialtyFieldName(i));
			String searchResultsAlias = reportLoggerMethods.getText(searchResultsAliasField.get(i), searchResultsAliasFieldName(i));
			String actualMessageID = retrieveMessageID(i);
			
			//Check if a specific search result row has the relevant name before comparing info to expectations
			if (searchResultsName.equalsIgnoreCase(name) &&
					searchResultsType.equalsIgnoreCase(type) &&
					searchResultsTitle.equalsIgnoreCase(title) &&
					searchResultsDepartment.equalsIgnoreCase(department) &&
					searchResultsSpecialty.equalsIgnoreCase(specialty) &&
					searchResultsAlias.equalsIgnoreCase(alias) &&
					actualMessageID.equalsIgnoreCase(messageID)) {
				
				//Confirm that the desired search entry has been found
				foundSearchEntry = true;
				
				//Perform Soft Asserts on the relevant search entry's details
				softAssert.assertEquals(searchResultsName.toLowerCase(), name.toLowerCase());
				softAssert.assertEquals(searchResultsType.toLowerCase(), type.toLowerCase());
				softAssert.assertEquals(searchResultsTitle.toLowerCase(), title.toLowerCase());
				softAssert.assertEquals(searchResultsDepartment.toLowerCase(), department.toLowerCase());
				softAssert.assertEquals(searchResultsSpecialty.toLowerCase(), specialty.toLowerCase());
				softAssert.assertEquals(searchResultsAlias.toLowerCase(), alias.toLowerCase());
				softAssert.assertEquals(actualMessageID.toLowerCase(), messageID.toLowerCase());
				
				//Check 'Name' match expectations for the report
				if (!searchResultsName.equalsIgnoreCase(name)) {
					correctInfo = false;
					failureMessage += "Expected Name of '" + name + "', but found '" + searchResultsName + "'." + System.lineSeparator();
				}
				
				//Check 'Type' match expectations for the report
				if (!searchResultsType.equalsIgnoreCase(type)) {
					correctInfo = false;
					failureMessage += "Expected Type of '" + type + "', but found '" + searchResultsType + "'." + System.lineSeparator();
				}
				
				//Check 'Title' match expectations for the report
				if (!searchResultsTitle.equalsIgnoreCase(title)) {
					correctInfo = false;
					failureMessage += "Expected Title of '" + title + "', but found '" + searchResultsTitle + "'." + System.lineSeparator();
				}
				
				//Check 'Department' match expectations for the report
				if (!searchResultsDepartment.equalsIgnoreCase(department)) {
					correctInfo = false;
					failureMessage += "Expected Department of '" + department + "', but found '" + searchResultsDepartment + "'." + System.lineSeparator();
				}
				
				//Check 'Specialty' match expectations for the report
				if (!searchResultsSpecialty.equalsIgnoreCase(specialty)) {
					correctInfo = false;
					failureMessage += "Expected Specialty of '" + specialty + "', but found '" + searchResultsSpecialty + "'." + System.lineSeparator();
				}
				
				//Check 'Alias' match expectations for the report
				if (!searchResultsAlias.equalsIgnoreCase(alias)) {
					correctInfo = false;
					failureMessage += "Expected Alias of '" + alias + "', but found '" + searchResultsAlias + "'." + System.lineSeparator();
				}
				
				//Check 'Message ID' match expectations for the report
				if (!messageID.equalsIgnoreCase(actualMessageID)) {
					correctInfo = false;
					failureMessage += "Expected Message ID of '" + messageID + "', but found '" + actualMessageID + "'." + System.lineSeparator();
				}
				
				//Exit the loop after locating the proper search result
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
								"Expected Message ID = '" + messageID + "'" + System.lineSeparator() +
								"Expected Name = '" + name + "'" + System.lineSeparator() +
								"Expected Type = '" + type + "'" + System.lineSeparator() +
								"Expected Title = '" + title + "'" + System.lineSeparator() +
								"Expected Department = '" + department + "'" + System.lineSeparator() +
								"Expected Specialty = '" + specialty + "'" + System.lineSeparator() +
								"Expected Alias = '" + alias + "'" + System.lineSeparator();
		}
		
		//Report the assert status to the Extent Report & System
		if (correctInfo) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResultsWithMessageID");
		}
	}
	
	public String retrievePhoneNumber(int searchResultRow) {
		//Initialize Variable(s)
		String phoneNumber;
		
		//Open a search result pop-up
		reportLoggerMethods.click(searchResultsNameLink.get(searchResultRow), searchResultsNameLinkName(searchResultRow));
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Retrieve the Message ID
		phoneNumber = reportLoggerMethods.getText(searchedResultPhoneNumber, searchedResultPhoneNumberName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Close the search result pop-up
		reportLoggerMethods.click(closeSearchResultPopup, closeSearchResultPopupName);
		
		//Return the Message ID
		return phoneNumber;
	}
	
	public void verifySearchedResultsWithPhoneNumber(SoftAssert softAssert, String phoneNumber, String name, String type, String title, String department, String specialty, String alias, String resultsRowCount, int excelRow, int excelColumn) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired search result(s) are present");
		
		//Initialize Variable(s)
		String successMessage = "Desired search result found";
		String failureMessage = "";
		int numOfSearchResults = retrieveSearchResultCount();
		boolean foundSearchEntry = false;
		boolean correctInfo = true;
		
		//Set the row count expectations to zero, if expecting an error message
		if (resultsRowCount.equalsIgnoreCase("error message")) {
			resultsRowCount = "0";
		}
		
		//Output the number of search results rows
		excelMethods.setDataTableCell(numOfSearchResults, excelRow, excelColumn);
		
		//Assert the status of the number of search results
		softAssert.assertEquals(numOfSearchResults, Integer.parseInt(resultsRowCount));
		
		//Check 'Row Count' match expectations for the report
		if (numOfSearchResults != Integer.parseInt(resultsRowCount)) {
			correctInfo = false;
			failureMessage += "Expected Row Count of '" + resultsRowCount + "', but found '" + numOfSearchResults + "'." + System.lineSeparator();
		}
		
		//Check if zero search results match expectation(s)
		if (numOfSearchResults == 0) {
			//Compare the non-existent search results to expectations
			softAssert.assertEquals("", phoneNumber);
			softAssert.assertEquals("", name);
			softAssert.assertEquals("", type);
			softAssert.assertEquals("", title);
			softAssert.assertEquals("", department);
			softAssert.assertEquals("", specialty);
			softAssert.assertEquals("", alias);
			
			//Check 'Phone Number' match expectations for the report
			if (!phoneNumber.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Phone Number of '" + phoneNumber + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Name' match expectations for the report
			if (!name.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Name of '" + name + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Type' match expectations for the report
			if (!type.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Type of '" + type + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Title' match expectations for the report
			if (!title.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Title of '" + title + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Department' match expectations for the report
			if (!department.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Department of '" + department + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Specialty' match expectations for the report
			if (!specialty.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Specialty of '" + specialty + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Check 'Alias' match expectations for the report
			if (!alias.equalsIgnoreCase("")) {
				correctInfo = false;
				failureMessage += "Expected Alias of '" + alias + "', but there are no search results to be found." + System.lineSeparator();
			}
			
			//Report the assert status to the Extent Report & System
			if (correctInfo) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResultsWithPhoneNumber");
			}
			
			//Finish the method with an Extent Report status
			return;
		}
		
		//Check if the desired search result(s) are present
		for (int i = 0; i < numOfSearchResults; i++) {
			//Initialize Variable(s)
			String searchResultsName = reportLoggerMethods.getText(searchResultsNameField.get(i), searchResultsNameFieldName(i));
			String searchResultsType = reportLoggerMethods.getText(searchResultsTypeField.get(i), searchResultsTypeFieldName(i));
			String searchResultsTitle = reportLoggerMethods.getText(searchResultsTitleField.get(i), searchResultsTitleFieldName(i));
			String searchResultsDepartment = reportLoggerMethods.getText(searchResultsDepartmentField.get(i), searchResultsDepartmentFieldName(i));
			String searchResultsSpecialty = reportLoggerMethods.getText(searchResultsSpecialtyField.get(i), searchResultsSpecialtyFieldName(i));
			String searchResultsAlias = reportLoggerMethods.getText(searchResultsAliasField.get(i), searchResultsAliasFieldName(i));
			String actualPhoneNumber = retrievePhoneNumber(i);
			
			//Check if a specific search result row has the relevant name before comparing info to expectations
			if (searchResultsName.equalsIgnoreCase(name) &&
					searchResultsType.equalsIgnoreCase(type) &&
					searchResultsTitle.equalsIgnoreCase(title) &&
					searchResultsDepartment.equalsIgnoreCase(department) &&
					searchResultsSpecialty.equalsIgnoreCase(specialty) &&
					searchResultsAlias.equalsIgnoreCase(alias) &&
					actualPhoneNumber.equalsIgnoreCase(phoneNumber)) {
				
				//Confirm that the desired search entry has been found
				foundSearchEntry = true;
				
				//Perform Soft Asserts on the relevant search entry's details
				softAssert.assertEquals(searchResultsName.toLowerCase(), name.toLowerCase());
				softAssert.assertEquals(searchResultsType.toLowerCase(), type.toLowerCase());
				softAssert.assertEquals(searchResultsTitle.toLowerCase(), title.toLowerCase());
				softAssert.assertEquals(searchResultsDepartment.toLowerCase(), department.toLowerCase());
				softAssert.assertEquals(searchResultsSpecialty.toLowerCase(), specialty.toLowerCase());
				softAssert.assertEquals(searchResultsAlias.toLowerCase(), alias.toLowerCase());
				softAssert.assertEquals(actualPhoneNumber.toLowerCase(), phoneNumber.toLowerCase());
				
				//Check 'Name' match expectations for the report
				if (!searchResultsName.equalsIgnoreCase(name)) {
					correctInfo = false;
					failureMessage += "Expected Name of '" + name + "', but found '" + searchResultsName + "'." + System.lineSeparator();
				}
				
				//Check 'Type' match expectations for the report
				if (!searchResultsType.equalsIgnoreCase(type)) {
					correctInfo = false;
					failureMessage += "Expected Type of '" + type + "', but found '" + searchResultsType + "'." + System.lineSeparator();
				}
				
				//Check 'Title' match expectations for the report
				if (!searchResultsTitle.equalsIgnoreCase(title)) {
					correctInfo = false;
					failureMessage += "Expected Title of '" + title + "', but found '" + searchResultsTitle + "'." + System.lineSeparator();
				}
				
				//Check 'Department' match expectations for the report
				if (!searchResultsDepartment.equalsIgnoreCase(department)) {
					correctInfo = false;
					failureMessage += "Expected Department of '" + department + "', but found '" + searchResultsDepartment + "'." + System.lineSeparator();
				}
				
				//Check 'Specialty' match expectations for the report
				if (!searchResultsSpecialty.equalsIgnoreCase(specialty)) {
					correctInfo = false;
					failureMessage += "Expected Specialty of '" + specialty + "', but found '" + searchResultsSpecialty + "'." + System.lineSeparator();
				}
				
				//Check 'Alias' match expectations for the report
				if (!searchResultsAlias.equalsIgnoreCase(alias)) {
					correctInfo = false;
					failureMessage += "Expected Alias of '" + alias + "', but found '" + searchResultsAlias + "'." + System.lineSeparator();
				}
				
				//Check 'Phone Number' match expectations for the report
				if (!phoneNumber.equalsIgnoreCase(actualPhoneNumber)) {
					correctInfo = false;
					failureMessage += "Expected Phone Number of '" + phoneNumber + "', but found '" + actualPhoneNumber + "'." + System.lineSeparator();
				}
				
				//Exit the loop after locating the proper search result
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
								"Expected Phone Number = '" + phoneNumber + "'" + System.lineSeparator() +
								"Expected Name = '" + name + "'" + System.lineSeparator() +
								"Expected Type = '" + type + "'" + System.lineSeparator() +
								"Expected Title = '" + title + "'" + System.lineSeparator() +
								"Expected Department = '" + department + "'" + System.lineSeparator() +
								"Expected Specialty = '" + specialty + "'" + System.lineSeparator() +
								"Expected Alias = '" + alias + "'" + System.lineSeparator();
		}
		
		//Report the assert status to the Extent Report & System
		if (correctInfo) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: " + successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: " + System.lineSeparator() + failureMessage, "verifySearchedResultsWithPhoneNumber");
		}
	}
	
	// ~~~ Send a Page ~~~ //
	
	public int locateSendPageSearchResult(String msgID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Locating the desired search result");
		
		//Locate the relevant desired search result & send the page
		for (int i = 0; i < reportLoggerMethods.getSize(searchResultsNameField, searchResultsNameFieldName); i++) {
			//Open a search result's detail page
			reportLoggerMethods.click(searchResultsNameLink.get(i), searchResultsNameLinkName(i));
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Check if the msgID matches expectations
			if (reportLoggerMethods.getText(messageIDLabel, messageIDLabelName).equalsIgnoreCase(msgID)) {
				//Close the search results detail page
				reportLoggerMethods.click(closeSearchResultPopup, closeSearchResultPopupName);
				
				//Return the row number of the search result that has the desired msgID
				return i;
			}
			
			//Close the search results detail page
			reportLoggerMethods.click(closeSearchResultPopup, closeSearchResultPopupName);
		}
		
		//Return -1, since there were no search results were found with the desired msgID
		return -1;
	}
	
	public void sendPage(String msgID, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Locating & sending a page from the desired search result");
		
		//Locate the desired search result
		int searchResultRow = locateSendPageSearchResult(msgID);
		
		//Click the button to bring up the 'Send a Page Form'
		reportLoggerMethods.click(pageButton(searchResultRow), pageButtonName(searchResultRow));
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Enter the message
		reportLoggerMethods.sendKeys(pageMessageField, pageMessageFieldName, message);
		
		//Submit the message
		reportLoggerMethods.click(sendPageButton, sendPageButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	// ~~~ Instructions ~~~ //
	
	public void verifyGlobalSearchResultInstructions(SoftAssert softAssert, String instructions) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the Instructions found via Global Search");
		
		//Click the name of the first Global Search result to bring up the detail pop-up
		reportLoggerMethods.click(searchResultsNameLink.get(0), searchResultsNameLinkName(0));
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		try {
			//Verify the Global Search result Instructions
			softAssert.assertEquals(instructionsTextArea.getText(), instructions);
			
			//Output the status to the System & Extent Report
			if (instructionsTextArea.getText().equalsIgnoreCase(instructions)) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Instructions matched expectations");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The Instructions found (" + instructionsTextArea.getText() + ") did not match expectations (" + instructions + ")", "verifyGlobalSearchResultInstructions");
			}
		} catch (Exception e) {
			//Verify the Global Search result Instructions
			softAssert.assertEquals("", instructions);
			
			//Output the status to the System & Extent Report
			if (instructions.equalsIgnoreCase("")) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Instructions matched expectations");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: Instructions were not found, but expected Instructions (" + instructions + ")", "verifyGlobalSearchResultInstructions");
			}
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Cancel the Global Search result pop-up
		closeSearchResultPopup.click();
	}
	
}