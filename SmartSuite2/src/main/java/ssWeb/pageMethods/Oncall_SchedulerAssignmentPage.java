package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallSchedulerAssignmentLocators;

public class Oncall_SchedulerAssignmentPage extends OncallSchedulerAssignmentLocators {
	
	//Constructor
	public Oncall_SchedulerAssignmentPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String groupNameSearchCriteria, String groupIDSearchCriteria) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a search with the Group Name '" + groupNameSearchCriteria + "' and Group ID '" + groupIDSearchCriteria + "'");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Clear the search field
		reportLoggerMethods.clear(groupNameField, groupNameFieldName);
		
		//Search with the 'Group Name' as criteria
		reportLoggerMethods.sendKeys(groupNameField, groupNameFieldName, ".");
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Clear the existing search criteria
		reportLoggerMethods.clear(groupNameField, groupNameFieldName);
		
		//Enter the 'Group Name' search criteria
		reportLoggerMethods.sendKeys(groupNameField, groupNameFieldName, groupNameSearchCriteria);
		
		//Enter the 'Group ID' search criteria
		reportLoggerMethods.sendKeys(groupIDField, groupIDFieldName, groupIDSearchCriteria);
		
		//Click the 'Search' button
		reportLoggerMethods.click(searchButton, searchButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the first search result
		reportLoggerMethods.click(searchResultFirstListing, searchResultFirstListingName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
	}
	
	public void verifySearchResults(SoftAssert softAssert, String expectedSearchResult) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result matches expectation(s)");
		
		//Initialize Variable(s)
		String searchResultFirstListingStr = reportLoggerMethods.getText(searchResultFirstListing, searchResultFirstListingName);
		
		//Check if the search result matches expectation(s)
		if (reportLoggerMethods.isDisplayed(searchResultFirstListing, searchResultFirstListingName)) {
			//Assert the status of the Search Result
			softAssert.assertEquals(searchResultFirstListingStr, expectedSearchResult);
			
			//Output the results to the report
			if (searchResultFirstListingStr.equalsIgnoreCase(expectedSearchResult)) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result of '" + expectedSearchResult + "' appeared as expected");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The search result of '" + searchResultFirstListingStr + "' appeared, instead of the expected result of '" + expectedSearchResult + "'", "verifySearchResults");
			}
		} else {
			//Check if the search result matches expectation(s)
			if (expectedSearchResult.equalsIgnoreCase("No Search Results")) {
				softAssert.assertEquals("No Search Results", expectedSearchResult);
			} else {
				softAssert.assertEquals("", expectedSearchResult);
			}
			
			//Output the results to the report
			if (expectedSearchResult.equalsIgnoreCase("No Search Results") || expectedSearchResult.equalsIgnoreCase("")) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result of '" + expectedSearchResult + "' appeared as expected");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: No search results appeared, instead of the expected result of '" + expectedSearchResult + "'", "verifySearchResults");
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
		reportLoggerMethods.reportInfo("Checking that the Search Tree is hidden in the 'Oncall Scheduler Assignment' tab");
		
		//Check that the Search Tree is hidden
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "hidden");
		
		//Output the results to the report
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("hidden")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is hidden in the 'Oncall Scheduler Assignment' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not hidden in the 'Oncall Scheduler Assignment' tab", "verifyHiddenTree");
		}
	}
	
	public void verifyShownTree(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Search Tree is shown in the 'Oncall Scheduler Assignment' tab");
		
		//Check that the Search Tree is shown
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "visible");
		
		//Output the results to the report
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("visible")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is shown in the 'Oncall Scheduler Assignment' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not shown in the 'Oncall Scheduler Assignment' tab", "verifyHiddenTree");
		}
	}
	
	// ~~~ Assignment ~~~ //
	
	public void deleteAllCurrentSchedulerAssignments() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting all existing Scheduler Assignments");
		
		//Delete all existing Scheduler Assignments
		//Loop through all existing Scheduler Assignments
		while (reportLoggerMethods.isDisplayed(schedulerAssignmentCheckbox, schedulerAssignmentCheckboxName)) {
			//Select the first Scheduler Assignment on the list
			reportLoggerMethods.click(schedulerAssignmentCheckbox, schedulerAssignmentCheckboxName);
			
			//Click the 'Delete' button
			reportLoggerMethods.click(deleteButton, deleteButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public void createNewAssignment(String messageID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a new Scheduler Assignment");
		
		//Click the 'New' button
		reportLoggerMethods.click(newButton, newButtonName);
		
		//Initialize Variable(s)
		timeRemark = String.valueOf(System.currentTimeMillis());
		currentAssignmentShiftDate = reportLoggerMethods.getAttribute(newStartDateField, newStartDateFieldName, "value");
		String currentEndDate = currentAssignmentShiftDate.substring(0, currentAssignmentShiftDate.length()-8) + "11:59 PM";
		
		//Enter the Message ID
		reportLoggerMethods.sendKeys(newMsgIDField, newMsgIDFieldName, messageID);
		
		//Focus on the Remark field
		reportLoggerMethods.click(remarkField, remarkFieldName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the Remark
		reportLoggerMethods.sendKeys(remarkField, remarkFieldName, timeRemark);
		
		//Clear the 'End Date' field
		reportLoggerMethods.clear(newEndDateField, newEndDateFieldName);
		
		//Enter the new value for the 'End Date' field
		reportLoggerMethods.sendKeys(newEndDateField, newEndDateFieldName, currentEndDate);
		
		//Click the 'Save' button
		reportLoggerMethods.click(saveButton, saveButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(3000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	public void createNewAssignment(String messageID, String remark) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a new Scheduler Assignment");
		
		//Click the 'New' button
		reportLoggerMethods.click(newButton, newButtonName);
		
		//Initialize Variable(s)
		currentAssignmentShiftDate = reportLoggerMethods.getAttribute(newStartDateField, newStartDateFieldName, "value");
		String currentStartDate = currentAssignmentShiftDate.substring(0, currentAssignmentShiftDate.length()-8) + "12:00 AM";
		String currentEndDate = currentAssignmentShiftDate.substring(0, currentAssignmentShiftDate.length()-8) + "11:59 PM";
		
		//Enter the Message ID
		reportLoggerMethods.sendKeys(newMsgIDField, newMsgIDFieldName, messageID);
		
		//Focus on the Remark field
		reportLoggerMethods.click(remarkField, remarkFieldName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the Remark
		reportLoggerMethods.sendKeys(remarkField, remarkFieldName, remark);
		
		//Clear the 'Start Date' field
		reportLoggerMethods.clear(newStartDateField, newStartDateFieldName);
		
		//Enter the new value for the 'Start Date' field
		reportLoggerMethods.sendKeys(newStartDateField, newStartDateFieldName, currentStartDate);
		
		//Clear the 'End Date' field
		reportLoggerMethods.clear(newEndDateField, newEndDateFieldName);
		
		//Enter the new value for the 'End Date' field
		reportLoggerMethods.sendKeys(newEndDateField, newEndDateFieldName, currentEndDate);
		
		//Click the 'Save' button
		reportLoggerMethods.click(saveButton, saveButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(3000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	public void modifyAssignment(String remark) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying a Scheduler Assignment");
		
		//Remove any existing content in the Remark field
		reportLoggerMethods.clear(schedulerAssignmentRemarkField, schedulerAssignmentRemarkFieldName);
		
		//Enter the modified Remark
		reportLoggerMethods.sendKeys(schedulerAssignmentRemarkField, schedulerAssignmentRemarkFieldName, remark);
		
		//Click the 'Save' button
		reportLoggerMethods.click(saveButton, saveButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(3000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	public void verifySchedulerAssignment(SoftAssert softAssert, String expectedMsgID, String expectedRemark) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the newly created/modified Scheduler Assignment matches expectation(s)");
		
		//Initialize Variable(s)
		String successfulMessage = "Successful: The Scheduler Assignment was successfully created/modified with the expected details";
		String failureMessage = "Failure: The Scheduler Assignment was not successfully created/modified. ";
		
		if (reportLoggerMethods.isDisplayed(schedulerAssignmentMsgIDField, schedulerAssignmentMsgIDFieldName)) {
			//Initialize Variable(s)
			String schedulerAssignmentMsgID = reportLoggerMethods.getAttribute(schedulerAssignmentMsgIDField, schedulerAssignmentMsgIDFieldName, "value");
			String schedulerAssignmentRemark = reportLoggerMethods.getAttribute(schedulerAssignmentRemarkField, schedulerAssignmentRemarkFieldName, "value");
			
			//Perform asserts on the created Scheduler Assignment details
			softAssert.assertEquals(schedulerAssignmentMsgID, expectedMsgID);
			softAssert.assertEquals(schedulerAssignmentRemark, expectedRemark);
			
			//Check if the newly created Scheduler Assignment matches expectations
			if (schedulerAssignmentMsgID.equalsIgnoreCase(expectedMsgID) && schedulerAssignmentRemark.equalsIgnoreCase(expectedRemark)) {
				//Report the status of the script to the System & Extent Report
				reportLoggerMethods.reportSuccessfulCheckpoint(successfulMessage);
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Check if the Message ID matches expectations
			if (!schedulerAssignmentMsgID.equalsIgnoreCase(expectedMsgID)) {
				failureMessage += "Expected Message ID of '" + expectedMsgID + "', but found '" + schedulerAssignmentMsgID + "'. ";
			}
			
			//Check if the Remark matches expectations
			if (!schedulerAssignmentRemark.equalsIgnoreCase(expectedRemark)) {
				failureMessage += "Expected Remark of '" + expectedRemark + "', but found '" + schedulerAssignmentRemark + "'. ";
			}
			
			//Report the status of the script to the System & Extent Report
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyCreatedSchedulerAssignment");
		} else {
			//Perform an assert on the created Scheduler Assignment
			softAssert.assertEquals("The Scheduler Assignment was not successfully created/modified", "The Scheduler Assignment was successfully created/modified");
			
			//Report the status of the script to the System & Extent Report
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Scheduler Assignment was not successfully created/modified", "verifySchedulerAssignment");
		}
	}
	
	public void verifyDeletedSchedulerAssignment(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the scheduler assignment was deleted");
		
		//Check if the scheduler assignment was deleted
		if (reportLoggerMethods.isDisplayed(schedulerAssignmentRemarkField, schedulerAssignmentRemarkFieldName)) {
			//Assert the status of the Scheduler Assignment's attempted deletion
			softAssert.assertEquals("The Scheduler Assignment was not deleted", "The Scheduler Assignment was deleted");
			
			//Report the status of the script to the System & Extent Report
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Scheduler Assignment was not deleted after attempting to delete it", "verifyDeletedSchedulerAssignment");
		} else {
			//Assert the status of the Scheduler Assignment's attempted deletion
			softAssert.assertEquals("The Scheduler Assignment was deleted", "The Scheduler Assignment was deleted");
			
			//Report the status of the script to the System & Extent Report
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Scheduler was deleted");
		}
	}
	
	// ~~~ Move Assignment ~~~ //
	
	public void moveSchedulerAssignmentToNextShift() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Locating and moving a  specified scheduler assignment to the next shift");
		
		//Highlight the 'Next Shift' button
		reportLoggerMethods.highlightWebElement(nextShiftButton, nextShiftButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Initialize Variable(s)
		int searchCountNumber = getSearchResultCount(); //Keeps track of how many search results were returned - used to loop through all search results
		String remarkMessage; //Keeps track of the actual search result message being displayed
		boolean correctAssignment = false; //Keeps track if a search result does not match expectations
		
		//Loop through every search result
		for (int i = 0; i < searchCountNumber; i++) {
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Retrieve the search result remark (in lower case values to avoid capitalization causing failures when comparing existing search results remarks to expected search results remarks)
			remarkMessage = reportLoggerMethods.getAttribute(existingRemarkField(i), existingRemarkFieldName(i), "value").toLowerCase();
			
			//If the scheduler assignment was found, select the assignment & move it to the next shift, then exit the search loop
			if (remarkMessage.contains(timeRemark)) {
				//Check the assignment's checkbox
				reportLoggerMethods.click(existingCheckbox(i), existingCheckboxName(i));
				
				//Move the assignment to the next shift
				reportLoggerMethods.click(nextShiftButton, nextShiftButtonName);
				
				//Indicate that the scheduler assignment was moved
				correctAssignment = true;
				
				//Exit the search loop
				break;
			}
			
			//Navigate to the next page of search results, if needed
			if ((i+1) % 10 == 0) {
				//Click the 'Next Page' button
				reportLoggerMethods.click(nextPageButton, nextPageButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
			}
		}
		
		//Save the the scheduler assignment's move to the next shift
		if (correctAssignment) {
			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);
			
			//Retrieve the scheduler assignment's next shift start date
			nextAssignmentShiftDate = reportLoggerMethods.getAttribute(nextShiftStartDateField, nextShiftStartDateFieldName, "value");
			
			//Save the scheduler assignment change to the next shift
			reportLoggerMethods.click(saveButton, saveButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
		} else {
			//Report assert status to the System & Extent Report
			reportLoggerMethods.reportFailedCheckpoint("Failure: New Scheduler Assignment not found/created", "moveSchedulerAssignmentToNextShift");
			
			//Assert the status of the Scheduler Assignment being Found/Created
			Assert.assertEquals("New Scheduler Assignment not found/created", "New Scheduler Assignment created & found");
		}
	}
	
	public void moveSchedulerAssignmentToNextDay() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Locating and moving a  specified scheduler assignment to the next day");
		
		//Highlight the 'Next Shift' button
		reportLoggerMethods.highlightWebElement(nextDayButton, nextDayButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Initialize Variable(s)
		int searchCountNumber = getSearchResultCount(); //Keeps track of how many search results were returned - used to loop through all search results
		String remarkMessage; //Keeps track of the actual search result message being displayed
		boolean correctAssignment = false; //Keeps track if a search result does not match expectations
		
		//Loop through every search result
		for (int i = 0; i < searchCountNumber; i++) {
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Retrieve the search result remark (in lower case values to avoid capitalization causing failures when comparing existing search results remarks to expected search results remarks)
			remarkMessage = reportLoggerMethods.getAttribute(existingRemarkField(i), existingRemarkFieldName(i), "value").toLowerCase();
			
			//If the scheduler assignment was found, select the assignment & move it to the next shift, then exit the search loop
			if (remarkMessage.contains(timeRemark)) {
				//Check the assignment's checkbox
				reportLoggerMethods.click(existingCheckbox(i), existingCheckboxName(i));
				
				//Move the assignment to the next shift
				reportLoggerMethods.click(nextDayButton, nextDayButtonName);
				
				//Indicate that the scheduler assignment was moved
				correctAssignment = true;
				
				//Exit the search loop
				break;
			}
			
			//Navigate to the next page of search results, if needed
			if ((i+1) % 10 == 0) {
				//Click the 'Next Page' button
				reportLoggerMethods.click(nextPageButton, nextPageButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
			}
		}
		
		//Save the the scheduler assignment's move to the next shift
		if (correctAssignment) {
			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);
			
			//Retrieve the scheduler assignment's next shift start date
			nextAssignmentShiftDate = reportLoggerMethods.getAttribute(nextDayStartDateField, nextDayStartDateFieldName, "value");
			
			//Save the scheduler assignment change to the next shift
			reportLoggerMethods.click(saveButton, saveButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
		} else {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: New Scheduler Assignment not found/created", "moveSchedulerAssignmentToNextShift");
			
			//Assert the Status of the Scheduler Assignment being found/created
			Assert.assertEquals("New Scheduler Assignment not found/created", "New Scheduler Assignment created & found");
		}
	}
	
	public void verifyMovedSchedulerAssignment(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the scheduler assignment was moved to the next shift");
		
		//Initialize Variable(s)
		int searchCountNumber = getSearchResultCount(); //Keeps track of how many search results were returned - used to loop through all search results
		String remarkMessage; //Keeps track of the actual search result message being displayed
		String startDate;
		boolean correctAssignment = false; //Keeps track if a search result does not match expectations
		
		//Loop through every search result
		for (int i = 0; i < searchCountNumber; i++) {
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Retrieve the search result remark (in lower case values to avoid capitalization causing failures when comparing existing search results remarks to expected search results remarks)
			remarkMessage = reportLoggerMethods.getAttribute(existingRemarkField(i), existingRemarkFieldName(i), "value").toLowerCase();
			startDate = reportLoggerMethods.getAttribute(existingStartField(i), existingStartFieldName(i), "value");
			
			//If the scheduler assignment was found, select the assignment & move it to the next shift, then exit the search loop
			if (remarkMessage.contains(timeRemark) && startDate.equalsIgnoreCase(nextAssignmentShiftDate)) {
				//Check the assignment's checkbox
				reportLoggerMethods.click(existingCheckbox(i), existingCheckboxName(i));
				
				//Move the assignment to the next shift
				reportLoggerMethods.click(deleteButton, deleteButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(4000);
				
				//Accept the alert pop-up
				reportLoggerMethods.acceptAlert();
				
				//Indicate that the scheduler assignment was found
				correctAssignment = true;
				
				//Exit the search loop
				break;
			}
			
			//Navigate to the next page of search results, if needed
			if ((i+1) % 10 == 0) {
				//Click the 'Next Page' button
				reportLoggerMethods.click(nextPageButton, nextPageButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
			}
		}
		
		//Perform SoftAsserts
		if (correctAssignment) {
			//Assert success status
			softAssert.assertEquals("Schedular Assignment in the next shift was found", "Schedular Assignment in the next shift was found");
			
			//Report success to the System & Extent Report
			reportLoggerMethods.reportSuccessfulCheckpoint("The Scheduler Assignment was successfully created & moved to the next shift");
		} else {
			//Report assert status to the System & Extent Report
			softAssert.assertEquals("Schedular Assignment in the next shift was not found", "Schedular Assignment in the next shift was found");
			
			//Assert the status of the Scheduler Assignment being Found/Created
			reportLoggerMethods.reportFailedCheckpoint("Schedular Assignment in the next shift was not found", "verifySchedulerAssignmentToNextShift");
		}
	}
	
	// ~~~ Instructions ~~~ //
	
	public void addInstructions(String instructions) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating/Modifying Scheduler Assignment Instructions");
		
		//Click the 'Instructions' button to create/modify Instructions
		reportLoggerMethods.click(instructionsButton, instructionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Clear any existing text
		reportLoggerMethods.clear(instructionsTextField, instructionsTextFieldName);
		
		//Enter the instructions
		reportLoggerMethods.sendKeys(instructionsTextField, instructionsTextFieldName, instructions);
		
		//Save the instructions
		reportLoggerMethods.click(saveInstructionsButton, saveInstructionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Close the instructions pop-up
		reportLoggerMethods.click(closeInstructionsButton, closeInstructionsButtonName);
	}
	
	public void verifyExistingInstructions(SoftAssert softAssert, String expectedInstructions) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the existing Instructions match expectations");
		
		//Click the 'Existing Instructions' button
		reportLoggerMethods.click(existingInstructionsButton, existingInstructionsButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Initialize Variable(s)
		String existingInstructionsFieldText = reportLoggerMethods.getText(existingInstructionsField, existingInstructionsFieldName);
		
		//Check if the existing Instructions match expectations
		softAssert.assertEquals(existingInstructionsFieldText, expectedInstructions);
		
		//Output the results to the report
		if (existingInstructionsFieldText.equalsIgnoreCase(expectedInstructions)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The Existing Instructions info matches expectations");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Existing Instructions (" + existingInstructionsFieldText + ") does not match expectations (" + expectedInstructions + ")", "verifyExistingInstructins");
		}
		
		//Close the existing Instructions pop-up
		reportLoggerMethods.click(closeExistingInstructionsField, closeExistingInstructionsFieldName);
	}
	
	public void deleteInstructions() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting Scheduler Assignment Instructions");
		
		//Click the 'Instructions' button to delete Instructions
		reportLoggerMethods.click(instructionsButton, instructionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Delete the instructions
		reportLoggerMethods.click(deleteInstructionsButton, deleteInstructionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Close the instructions pop-up
		reportLoggerMethods.click(closeInstructionsButton, closeInstructionsButtonName);
	}
	
	public void verifyDeletedInstructions(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Instructions were deleted");
		
		//Check if the Instructions were deleted
		if (reportLoggerMethods.isDisplayed(existingInstructionsButton, existingInstructionsButtonName)) {
			//Assert the status of the 'Existing Instructions Button'
			softAssert.assertEquals(reportLoggerMethods.isDisplayed(existingInstructionsButton, existingInstructionsButtonName), false);
			
			//Output the results to the report
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Instructions were not deleted", "verifyDeletedInstructions");
		} else {
			//Check if the Instructions were deleted
			softAssert.assertEquals("The Instructions were properly deleted", "The Instructions were properly deleted");
			
			//Output the results to the report
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The Instructions were deleted, as expected");
		}
	}
	
	// ~~~ Show Shifts ~~~ //
	
	public void clickShiftsButton() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Shifts' button");
		
		//Click the 'Shifts' button
		reportLoggerMethods.click(shiftsButton, shiftsButtonName);
	}
	
	public void verifyShiftsResults(SoftAssert softAssert, String expectedShiftsResult) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the shifts result matches expectation(s)");
		
		//Assert the status of the Shift Table entries
		softAssert.assertNotEquals(reportLoggerMethods.getAttribute(shiftsTable, shiftsTableName, "innerText"), "\n");
		
		//Report the status of the script to the System & Extent Report
		if (!reportLoggerMethods.getAttribute(shiftsTable, shiftsTableName, "innerText").equalsIgnoreCase("\n")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Shifts Table is populated with one or more shifts");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Faiure: The Shifts Table is not populated with any shifts", "verifyShiftsResults");
		}
	}
	
	// ~~~ Show Lists ~~~ //
	
	public void clickNewButton() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'New' button");
		
		//Click the 'New' button
		reportLoggerMethods.click(newButton, newButtonName);
	}
	
	public void clickListButton() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'List' button");
		
		//Click the 'List' button
		reportLoggerMethods.click(listButton, listButtonName);
	}
	
	public void verifyListButtonPopup(SoftAssert softAssert, String expectedMsgID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the List button resulted in a pop-up with a group member with the expected 'Msg ID' of '" + expectedMsgID + "'");
		
		//Check that the expected 'Msg ID' exists in the List pop-up
		if (reportLoggerMethods.isDisplayed(msgIDLabel, msgIDLabelName)) {
			//Initialize Variable(s)
			String msgID = reportLoggerMethods.getText(msgIDLabel, msgIDLabelName);
			
			//Assert the status of 'Msg ID' Label being Displayed when the Scheduler Assignment's 'List' Pop-up is up
			softAssert.assertEquals(msgID, expectedMsgID);
			
			//Output the results to the report
			if (msgID.equalsIgnoreCase(expectedMsgID)) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Msg ID' of '" + expectedMsgID + "' appeared in the 'On Call Member List' pop-up");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Msg ID' being displayed is '" + msgID + "' instead of the expected value of '" + expectedMsgID + "'", "verifyListButtonPopup");
			}
		} else {
			//Perform an assert if the 'Msg ID' label was not found
			softAssert.assertEquals("No 'Msg ID' message found", "Expected 'Msg ID' of '" + expectedMsgID + "'");
			
			//Output the results to the report
			reportLoggerMethods.reportFailedCheckpoint("Failure: Expected value of '" + expectedMsgID + "', but no 'Msg ID' was found", "verifyListButtonPopup");
		}
	}
	
}