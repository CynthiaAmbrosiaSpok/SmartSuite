package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallBlockAssignmentLocators;

public class Oncall_BlockAssignmentPage extends OncallBlockAssignmentLocators {
	
	//Constructor
	public Oncall_BlockAssignmentPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Group Search ~~~ //
	
	public void searchGroupName(String groupName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching Group Name");
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(groupNameSearchField, groupNameSearchFieldName, groupName);
		
		//Click 'Search'
		reportLoggerMethods.click(groupNameSearchButton, groupNameSearchButtonName);
	}
	
	public void clickFirstGroupSearchResult() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the first Group search result");
		
		//Click the first Group search result
		reportLoggerMethods.click(groupNameFirstSearchResult, groupNameFirstSearchResultName);
	}
	
	public void verifyGroupNameSearchResult(SoftAssert softAssert, String groupName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Group Name search result matches expectations (Expected result of '" + groupName + "')");
		
		//Initialize Variable(s)
		String groupNameFirstSearchResultStr = reportLoggerMethods.getText(groupNameFirstSearchResult, groupNameFirstSearchResultName);
		
		//Assert the 'Group Name' search result
		softAssert.assertEquals(groupNameFirstSearchResultStr, groupName);
		
		//Report the assert status to the Extent Report & System
		if (groupNameFirstSearchResultStr.equalsIgnoreCase(groupName)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The Group Name search result matches expectations (Search result '" + groupName + "' found)");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Group Name search expected '" + groupName + "', but found '" + groupNameFirstSearchResultStr + "'", "verifyGroupNameSearchResult");
		}
	}
	
	// ~~~ Non-Member Search ~~~ //
	
	public void clickNonMemberSearchTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Non-Member Search' tab");
		
		//Navigate to the 'Non-Member Search' tab
		reportLoggerMethods.click(nonMemberSearchTab, nonMemberSearchTabName);
	}
	
	public void searchNonMemberID(String nonMemberID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching with the Non-Member ID of '" + nonMemberID + "'");
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(nonMemberSearchField, nonMemberSearchFieldName, nonMemberID);
		
		//Click 'Search'
		reportLoggerMethods.click(nonMemberSearchButton, nonMemberSearchButtonName);
		
		//Double-Click the search result to bring up extra details
		reportLoggerMethods.doubleClickPerform(nonMemberNameResult, nonMemberNameResultName);
	}
	
	public void verifyNonMemberSearchResult(SoftAssert softAssert, String msgID, String name) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Non-Member search result matches expectations (Expected result containing 'Msg ID' = '" + msgID + "' and 'Name' = '" + name + "')");
		
		//Initialize Variable(s)
		String successMessage = "Successful: The Non-Member search result matches expectations (Search result with 'Msg ID' = '" + msgID + "' and 'Name' = '" + name + "' found)";
		String failedMessage = "Failure: ";
		
		String nonMemberMsgID = reportLoggerMethods.getText(nonMemberMsgIDResult, nonMemberMsgIDResultName);
		String assignmentDetailsMsgID = reportLoggerMethods.getAttribute(assignmentDetailsMsgIDField, assignmentDetailsMsgIDFieldName, "value");
		String nonMemberName = reportLoggerMethods.getText(nonMemberNameResult, nonMemberNameResultName);
		String assignmentDetailsName = reportLoggerMethods.getAttribute(assignmentDetailsNameField, assignmentDetailsNameFieldName, "value");
		
		//Assert the 'Group Name' search result
		softAssert.assertEquals(nonMemberMsgID, msgID);
		softAssert.assertEquals(assignmentDetailsMsgID, msgID);
		softAssert.assertEquals(nonMemberName, name);
		softAssert.assertEquals(assignmentDetailsName, name);
		
		//Check if the 'Msg ID' matches expectations
		if (!nonMemberMsgID.equalsIgnoreCase(msgID) || !assignmentDetailsMsgID.equalsIgnoreCase(msgID)) {
			failedMessage += "The expected 'Msg ID' under the non-member search result & Assignment Details is '" + msgID + "', but found '" + nonMemberMsgID + "' under non-member search result and '" + assignmentDetailsMsgID + "' under the Assignment Details.";
		}
		
		//Check if the 'Name' matches expectations
		if (!nonMemberName.equalsIgnoreCase(name) || !assignmentDetailsName.equalsIgnoreCase(name)) {
			failedMessage += "The expected 'Name' under the non-member search result & Assignment Details is '" + name + "', but found '" + nonMemberName + "' under non-member search result and '" + assignmentDetailsName + "' under the Assignment Details.";
		}
		
		//Report the assert status to the Extent Report & System
		if (nonMemberMsgID.equalsIgnoreCase(msgID) && assignmentDetailsMsgID.equalsIgnoreCase(msgID) && nonMemberName.equalsIgnoreCase(name) && assignmentDetailsName.equalsIgnoreCase(name)) {
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint(failedMessage, "verifyGroupNameSearchResult");
		}
	}
	
	// ~~~ Assignment ~~~ //
	
	public void deleteAllCurrentBlockAssignments() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting all existing Block Assignments");
		
		//Delete all existing Block Assignments
		while (reportLoggerMethods.isDisplayed(firstBlockAssignmentCheckbox, firstBlockAssignmentCheckboxName)) {
			//Select the first Block Assignment on the list
			reportLoggerMethods.click(firstBlockAssignmentCheckbox, firstBlockAssignmentCheckboxName);
			
			//Click the 'Delete' button
			reportLoggerMethods.click(deleteBlockAssignmentButton, deleteBlockAssignmentButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public void createBlockAssignment(SoftAssert softAssert, String msgID, String startTime, String endTime) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a Block Assignment");
		
		//Initialize Variable(s)
		String firstGroupMemberMsgID = reportLoggerMethods.getText(firstGroupMemberMsgIDLabel, firstGroupMemberMsgIDLabelName);
		
		//Check if the 'Msg ID' being displayed matches expectations
		if (!firstGroupMemberMsgID.equalsIgnoreCase(msgID)) {
			//Assert the status of the expected Msg ID
			softAssert.assertEquals(firstGroupMemberMsgID, msgID);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The First Group Member's 'Msg ID' Label (" + firstGroupMemberMsgID + ") does not match expectations (" + msgID + ")", "clickFirstGroupMemberMsgIDLabel");
		}
		
		//Double-Click the first Group Member Msg ID Label
		reportLoggerMethods.doubleClick(firstGroupMemberMsgIDLabel, firstGroupMemberMsgIDLabelName);
		
		//Enter the Msg ID
		reportLoggerMethods.sendKeys(assignmentDetailsMsgIDField, assignmentDetailsMsgIDFieldName, msgID);
		
		//Enter the Start Time
		reportLoggerMethods.sendKeys(assignmentDetailsStartField, assignmentDetailsStartFieldName, startTime);
		
		//Enter the End Time
		reportLoggerMethods.sendKeys(assignmentDetailsEndField, assignmentDetailsEndFieldName, endTime);
		
		//Select a date on the calendar
		reportLoggerMethods.click(calendarDayTenCell, calendarDayTenCellName);
		
		//Click 'Save' to create the Block Assignment
		reportLoggerMethods.click(assignmentDetailsSaveButton, assignmentDetailsSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void modifyBlockAssignment(String startTime, String endTime) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying a Block Assignment");
		
		//Select the first Block Assignment from the list
		reportLoggerMethods.click(firstBlockAssignmentCheckbox, firstBlockAssignmentCheckboxName);
		
		//Click 'Edit' to beginning editing the selected Assignment(s)
		reportLoggerMethods.click(editBlockAssignmentButton, editBlockAssignmentButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(200);
		
		//Clear any existing data in the 'Start Date' field
		reportLoggerMethods.clear(assignmentDetailsStartField, assignmentDetailsStartFieldName);
		
		//Enter the Start Time
		reportLoggerMethods.sendKeys(assignmentDetailsStartField, assignmentDetailsStartFieldName, startTime);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(200);
		
		//Clear any existing data in the 'End Date' field
		reportLoggerMethods.clear(assignmentDetailsEndField, assignmentDetailsEndFieldName);
		
		//Enter the End Time
		reportLoggerMethods.sendKeys(assignmentDetailsEndField, assignmentDetailsEndFieldName, endTime);
		
		//Click 'Save' to modify the Block Assignment
		reportLoggerMethods.click(assignmentDetailsSaveButton, assignmentDetailsSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifyBlockAssignment(SoftAssert softAssert, String expectedMsgID, String expectedName, String expectedStartTime, String expectedEndTime) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the newly created/modified Block Assignment matches expectation(s)");
		
		//Initialize Variable(s)
		String successfulMessage = "Successful: The Block Assignment was successfully created/modified with the expected details";
		String failureMessage = "Failure: The Block Assignment was not successfully created/modified. ";
		
		if (reportLoggerMethods.isDisplayed(blockAssignmentMsgIDLabel, blockAssignmentMsgIDLabelName)) {
			//Initialize Variable(s)
			String blockAssignmentMsgID = reportLoggerMethods.getText(blockAssignmentMsgIDLabel, blockAssignmentMsgIDLabelName);
			String blockAssignmentName = reportLoggerMethods.getText(blockAssignmentNameLabel, blockAssignmentNameLabelName);
			String blockAssignmentStart = reportLoggerMethods.getText(blockAssignmentStartLabel, blockAssignmentStartLabelName);
			String blockAssignmentEnd = reportLoggerMethods.getText(blockAssignmentEndLabel, blockAssignmentEndLabelName);
			
			//Perform asserts on the created Block Assignment details
			softAssert.assertEquals(blockAssignmentMsgID, expectedMsgID);
			softAssert.assertEquals(blockAssignmentName, expectedName);
			softAssert.assertEquals(blockAssignmentStart, expectedStartTime);
			softAssert.assertEquals(blockAssignmentEnd, expectedEndTime);
			
			//Check if the newly created Block Assignment matches expectations
			if (blockAssignmentMsgID.equalsIgnoreCase(expectedMsgID) && blockAssignmentName.equalsIgnoreCase(expectedName) && blockAssignmentStart.equalsIgnoreCase(expectedStartTime) && blockAssignmentEnd.equalsIgnoreCase(expectedEndTime)) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint(successfulMessage);
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Check if the Message ID matches expectations
			if (!blockAssignmentMsgID.equalsIgnoreCase(expectedMsgID)) {
				failureMessage += "Expected Message ID of '" + expectedMsgID + "', but found '" + blockAssignmentMsgID + "'. ";
			}
			
			//Check if the Name matches expectations
			if (!blockAssignmentName.equalsIgnoreCase(expectedName)) {
				failureMessage += "Expected Name of '" + expectedName + "', but found '" + blockAssignmentName + "'. ";
			}
			
			//Check if the Start Time matches expectations
			if (!blockAssignmentStart.equalsIgnoreCase(expectedStartTime)) {
				failureMessage += "Expected Start Time of '" + expectedStartTime + "', but found '" + blockAssignmentStart + "'. ";
			}
			
			//Check if the End Time matches expectations
			if (!blockAssignmentEnd.equalsIgnoreCase(expectedEndTime)) {
				failureMessage += "Expected End Time of '" + expectedEndTime + "', but found '" + blockAssignmentEnd + "'. ";
			}
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyBlockAssignment");
		} else {
			//Perform an assert on the created Block Assignment
			softAssert.assertEquals("The Block Assignment was not successfully created/modified", "The Block Assignment was successfully created/modified");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Block Assignment was not successfully created/modified", "verifyBlockAssignment");
		}
	}
	
	public void verifyDeletedBlockAssignment(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Block Assignment was deleted");
		
		//Check if the Block Assignment was deleted
		if (reportLoggerMethods.isDisplayed(blockAssignmentMsgIDLabel, blockAssignmentMsgIDLabelName)) {
			//Assert the status of the Block Assignment's attempted deletion
			softAssert.assertEquals("The Block Assignment was not deleted", "The Block Assignment was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Block Assignment was not deleted after attempting to delete it", "verifyDeletedSchedulerAssignment");
		} else {
			//Assert the status of the Block Assignment's attempted deletion
			softAssert.assertEquals("The Block Assignment was deleted", "The Block Assignment was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Block Assignment was deleted");
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
		reportLoggerMethods.reportInfo("Checking that the Search Tree is hidden in the 'Oncall Block Assignment' tab");
		
		//Check that the Search Tree is hidden
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "hidden");
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("hidden")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is hidden in the 'Oncall Block Assignment' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not hidden in the 'Oncall Block Assignment' tab", "verifyHiddenTree");
		}
	}
	
	public void verifyShownTree(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Search Tree is shown in the 'Oncall Block Assignment' tab");
		
		//Check that the Search Tree is shown
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "visible");
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("visible")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is shown in the 'Oncall Block Assignment' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not shown in the 'Oncall Block Assignment' tab", "verifyHiddenTree");
		}
	}
	
	// ~~~ Navigate Calender ~~~ //
	
	public String getNewCurrentCalenderMonth(String forwardOrBackward, String amountOfMonths) {
		int numberOfMonths = Integer.parseInt(amountOfMonths);
		int currentMonth  = getMonthNumber(reportLoggerMethods.getText(currentMonthLabel, currentMonthLabelName));
		
		//Initialize Variable(s)
		if (forwardOrBackward.equalsIgnoreCase("forward")) {
			currentMonth = (currentMonth + numberOfMonths) % 12;
		} else {
			currentMonth = (currentMonth - numberOfMonths) % 12;
			if (currentMonth < 0) {
				currentMonth = 12-(0-currentMonth);
			}
		}
		
		//Return the month name
		return getMonthName(currentMonth);
	}
	
	public String getNewCurrentCalenderYear(String forwardOrBackward, String amountOfMonths) {
		int numberOfMonths = Integer.parseInt(amountOfMonths);
		int currentMonth  = getMonthNumber(reportLoggerMethods.getText(currentMonthLabel, currentMonthLabelName));
		int currentYear = Integer.parseInt(reportLoggerMethods.getText(currentYearLabel, currentYearLabelName));
		
		//Initialize Variable(s)
		if (forwardOrBackward.equalsIgnoreCase("forward")) {
			currentMonth = currentMonth + numberOfMonths;
			while (currentMonth > 12) {
				currentYear += 1;
				currentMonth -= 12;
			}
		} else {
			while (numberOfMonths > 12) {
				currentYear -= 1;
				numberOfMonths -= 12;
			}
			currentMonth = currentMonth - numberOfMonths;
			if (currentMonth <= 0) {
				currentYear -= 1;
			}
		}
		
		//Return the month name
		return String.valueOf(currentYear);
	}
	
	public void clickForwardMonthButton(String amountOfMonths) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Moving forward a month on the 'Block Assignment' calender");
		
		//Move forward a month on the 'Block Assignment' calender
		for (int i = 0; i < Integer.parseInt(amountOfMonths); i++) {
			reportLoggerMethods.click(nextMonthButton, nextMonthButtonName);
		}
	}
	
	public void clickBackwardMonthButton(String amountOfMonths) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Moving backward a month on the 'Block Assignment' calender");
		
		//Initialize Variable(s)
		int monthCount = Integer.parseInt(amountOfMonths);
		
//		//Increase the month count by 1, if moving back past 4 months in order to avoid losing a month
//		if (monthCount >= 8) {
//			monthCount += 1;
//		}
		
		//Move backward a month on the 'Block Assignment' calender
		for (int i = 0; i < monthCount; i++) {
			reportLoggerMethods.click(previousMonthButton, previousMonthButtonName);
		}
	}
	
	public void verifyCalenderDate(SoftAssert softAssert, String expectedMonth, String expectedYear) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the 'Block Assignment' calender is set to the correct year and month");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Initialize Variable(s)
		String currentYear = reportLoggerMethods.getText(currentYearLabel, currentYearLabelName);
		String currentMonth = reportLoggerMethods.getText(currentMonthLabel, currentMonthLabelName);
		
		//Assert the status of the current date of the 'Block Assignment' calender
		softAssert.assertEquals(currentYear, expectedYear);
		softAssert.assertEquals(currentMonth, expectedMonth);
		
		//Check that the 'Block Assignment' calender is set to the correct year and month & Report the assert status to the Extent Report & System
		if (currentYear.equalsIgnoreCase(expectedYear) && currentMonth.equalsIgnoreCase(expectedMonth)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Blocking Assignment' calender's current year and month matches expectations");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Blocking Assignment' calender's date is currently set to: '" + currentMonth + ", " + currentYear + "' instead of '" + expectedMonth + ", " + expectedYear + "'", "verifyValidInput");
		}
	}
	
}