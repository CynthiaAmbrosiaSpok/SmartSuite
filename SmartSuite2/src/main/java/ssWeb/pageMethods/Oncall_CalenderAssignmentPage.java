package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.OncallCalenderAssignmentLocators;

public class Oncall_CalenderAssignmentPage extends OncallCalenderAssignmentLocators {
	
	//Constructor
	public Oncall_CalenderAssignmentPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Group Search ~~~ //
	
	public void searchGroupName(String groupName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching Group Name");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(groupNameSearchField, groupNameSearchFieldName, groupName);
		
		//Click 'Search'
		reportLoggerMethods.click(groupNameSearchButton, groupNameSearchButtonName);
	}
	
	public void clickFirstGroupSearchResult() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the first Group search result");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click the first Group search result
		reportLoggerMethods.click(groupNameFirstSearchResult, groupNameFirstSearchResultName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	// ~~~ Assignment ~~~ //
	
	public void deleteAllCurrentCalenderAssignments() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting any/all existing Calender Assignment(s)");
		
		//Initialize Variable(s)
		int calenderAssignmentCount = reportLoggerMethods.getSize(calenderCellBodyList, calenderCellBodyListName);
		
		//Loop through all the existing Calender Assignments to delete them
		for (int i = 0; i < calenderAssignmentCount; i++) {
			//Click the existing calender Assignment
			reportLoggerMethods.click(calenderCellBodyList.get(0), calenderCellBodyListName(0));
			
			//Click the 'Delete' button
			reportLoggerMethods.click(deleteButton, deleteButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
//			//Accept the alert pop-up
//			reportLoggerMethods.acceptAlert();
//			
//			//Pause the script for a bit
//			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public void createCalenderAssignment(String msgID, String fullName, String startTime, String endTime, String timeZone) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a Calender Assignment");
		
		//Click the calender date to create a Calender Assignment
		reportLoggerMethods.click(calenderCellHeaderList.get(10), calenderCellHeaderListName(10));
		
		//Click the 'New' button
		reportLoggerMethods.click(newButton, newButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Enter the Msg ID
		reportLoggerMethods.sendKeys(createMsgIDField, createMsgIDFieldName, msgID);
		
		//Enter the Name
		reportLoggerMethods.sendKeys(createNameField, createNameFieldName, fullName);
		
		//Enter the Start Time
		reportLoggerMethods.sendKeys(createStartTimeField, createStartTimeFieldName, startTime);
		
		//Enter the End Time
		reportLoggerMethods.sendKeys(createEndTimeField, createEndTimeFieldName, endTime);
		
		//Select the Time Zone
		reportLoggerMethods.selectDropDown(createTimeZoneDropDown, createTimeZoneDropDownName, timeZone);
		
		//Click the Save button
		reportLoggerMethods.click(createSaveButton, createSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	
	public void modifyCalenderAssignment(String msgID, String fullName, String startTime, String endTime, String timeZone) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying a Calender Assignment");
		
		//Click the calander date to create a Calender Assignment
		reportLoggerMethods.click(calenderCellBodyList.get(0), calenderCellBodyListName(0));
		
		//Click the 'Edit' button
		reportLoggerMethods.click(editButton, editButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Enter the Msg ID
		reportLoggerMethods.clear(modifyMsgIDField, modifyMsgIDFieldName);
		reportLoggerMethods.sendKeys(modifyMsgIDField, modifyMsgIDFieldName, msgID);
		
		//Enter the Name
		reportLoggerMethods.clear(modifyNameField, modifyNameFieldName);
		reportLoggerMethods.sendKeys(modifyNameField, modifyNameFieldName, fullName);
		
		//Enter the Start Time
		reportLoggerMethods.clear(modifyStartTimeField, modifyStartTimeFieldName);
		reportLoggerMethods.sendKeys(modifyStartTimeField, modifyStartTimeFieldName, startTime);
		
		//Enter the End Time
		reportLoggerMethods.clear(modifyEndTimeField, modifyEndTimeFieldName);
		reportLoggerMethods.sendKeys(modifyEndTimeField, modifyEndTimeFieldName, endTime);
		
		//Select the Time Zone
		reportLoggerMethods.selectDropDown(modifyTimeZoneDropDown, modifyTimeZoneDropDownName, timeZone);
		
		//Click the Save button
		reportLoggerMethods.click(modifySaveButton, modifySaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Accept the alert pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifyCalenderAssignment(SoftAssert softAssert, String expectedMsgID, String expectedName, String expectedStartTime, String expectedEndTime, String expectedTimeZone) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the newly created/modified Calender Assignment matches expectation(s)");
		
		//Initialize Variable(s)
		String successfulMessage = "Successful: The Calender Assignment was successfully created/modified with the expected details";
		String failureMessage = "Failure: The Calender Assignment was not successfully created/modified. ";
		
		if (reportLoggerMethods.isDisplayed(calenderCellBodyList.get(0), calenderCellBodyListName(0))) {
			//Click the calander date to create a Calender Assignment
			reportLoggerMethods.click(calenderCellBodyList.get(0), calenderCellBodyListName(0));
			
			//Click the 'Edit' button
			reportLoggerMethods.click(editButton, editButtonName);
			
			//Initialize Variable(s)
			String modifyMsgID = reportLoggerMethods.getAttribute(modifyMsgIDField, modifyMsgIDFieldName, "value");
			String modifyName = reportLoggerMethods.getAttribute(modifyNameField, modifyNameFieldName, "value");
			String modifyStartTime = reportLoggerMethods.getAttribute(modifyStartTimeField, modifyStartTimeFieldName, "value");
			String modifyEndTime = reportLoggerMethods.getAttribute(modifyEndTimeField, modifyEndTimeFieldName, "value");
			String modifyTimeZone = reportLoggerMethods.getAttribute(modifyTimeZoneDropDown, modifyTimeZoneDropDownName, "value");
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Perform asserts on the created Calender Assignment details
			softAssert.assertEquals(modifyMsgID, expectedMsgID);
			softAssert.assertEquals(modifyName, expectedName);
			softAssert.assertEquals(modifyStartTime, expectedStartTime);
			softAssert.assertEquals(modifyEndTime, expectedEndTime);
			softAssert.assertEquals(modifyTimeZone, expectedTimeZone);
			
			//Check if the newly created Calender Assignment matches expectations
			if (modifyMsgID.equalsIgnoreCase(expectedMsgID) && modifyName.equalsIgnoreCase(expectedName) && modifyStartTime.equalsIgnoreCase(expectedStartTime) && modifyEndTime.equalsIgnoreCase(expectedEndTime) && modifyTimeZone.equalsIgnoreCase(expectedTimeZone)) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint(successfulMessage);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Cancel out of the Calender Assignment detail pop-up
				reportLoggerMethods.click(modifyCancelButton, modifyCancelButtonName);
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Check if the Message ID matches expectations
			if (!modifyMsgID.equalsIgnoreCase(expectedMsgID)) {
				failureMessage += "Expected Message ID of '" + expectedMsgID + "', but found '" + modifyMsgID + "'. ";
			}
			
			//Check if the Name matches expectations
			if (!modifyName.equalsIgnoreCase(expectedName)) {
				failureMessage += "Expected Name of '" + expectedName + "', but found '" + modifyName + "'. ";
			}
			
			//Check if the Start Time matches expectations
			if (!modifyStartTime.equalsIgnoreCase(expectedStartTime)) {
				failureMessage += "Expected Start Time of '" + expectedStartTime + "', but found '" + modifyStartTime + "'. ";
			}
			
			//Check if the End Time matches expectations
			if (!modifyEndTime.equalsIgnoreCase(expectedEndTime)) {
				failureMessage += "Expected End Time of '" + expectedEndTime + "', but found '" + modifyEndTime + "'. ";
			}
			
			//Check if the Time Zone matches expectations
			if (!modifyTimeZone.equalsIgnoreCase(expectedTimeZone)) {
				failureMessage += "Expected Time Zone of '" + expectedTimeZone + "', but found '" + modifyTimeZone + "'. ";
			}
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyCalenderAssignment");
		} else {
			//Perform an assert on the created Calender Assignment
			softAssert.assertEquals("The Calender Assignment was not successfully created/modified", "The Calender Assignment was successfully created/modified");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Calender Assignment was not successfully created/modified", "verifyCalenderAssignment");
		}
		
		//Cancel out of the Calender Assignment detail pop-up
		reportLoggerMethods.click(modifyCancelButton, modifyCancelButtonName);
	}
	
	public void verifyDeletedCalenderAssignment(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Calender Assignment was deleted");
		
//		//Check if Calender Assignments exist
//		if (reportLoggerMethods.isDisplayed(calenderCellBodyList.get(0), calenderCellBodyListName)) {
//			//Check if the Calender Assignment was deleted
//			if (reportLoggerMethods.getSize(calenderCellBodyList, calenderCellBodyListName) > 0) {
//				//Assert the status of the Calender Assignment's attempted deletion
//				softAssert.assertEquals("The Calender Assignment was not deleted", "The Calender Assignment was deleted");
//				
//				//Report the assert status to the Extent Report & System
//				reportLoggerMethods.reportFailedCheckpoint("Failure: The Calender Assignment was not deleted after attempting to delete it", "verifyDeletedCalenderAssignment");
//			} else {
//				//Assert the status of the Calender Assignment's attempted deletion
//				softAssert.assertEquals("The Calender Assignment was deleted", "The Calender Assignment was deleted");
//				
//				//Report the assert status to the Extent Report & System
//				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Calender Assignment was deleted");
//			}
//		} else {
//			//Assert the status of the Calender Assignment's attempted deletion
//			softAssert.assertEquals("The Calender Assignment was deleted", "The Calender Assignment was deleted");
//			
//			//Report the assert status to the Extent Report & System
//			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Calender Assignment was deleted");
//		}
		
		//Check if Calender Assignments exist
		try {
			//Check if any Calender Assignments exist
			if (!reportLoggerMethods.isDisplayed(calenderCellBodyList.get(0), calenderCellBodyListName)) {
				//Assert the status of the Calender Assignment's attempted deletion
				softAssert.assertEquals("The Calender Assignment was deleted", "The Calender Assignment was deleted");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Calender Assignment was deleted");
			}
			
			//Check if the Calender Assignment was deleted
			if (reportLoggerMethods.getSize(calenderCellBodyList, calenderCellBodyListName) > 0) {
				//Assert the status of the Calender Assignment's attempted deletion
				softAssert.assertEquals("The Calender Assignment was not deleted", "The Calender Assignment was deleted");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The Calender Assignment was not deleted after attempting to delete it", "verifyDeletedCalenderAssignment");
			} else {
				//Assert the status of the Calender Assignment's attempted deletion
				softAssert.assertEquals("The Calender Assignment was deleted", "The Calender Assignment was deleted");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Calender Assignment was deleted");
			}
		} catch (Exception e) {
			//Assert the status of the Calender Assignment's attempted deletion
			softAssert.assertEquals("The Calender Assignment was deleted", "The Calender Assignment was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Calender Assignment was deleted");
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
		reportLoggerMethods.reportInfo("Checking that the Search Tree is hidden in the 'Oncall Calender Assignment' tab");
		
		//Check that the Search Tree is hidden
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "hidden");
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("hidden")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is hidden in the 'Oncall Calender Assignment' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not hidden in the 'Oncall Calender Assignment' tab", "verifyHiddenTree");
		}
	}
	
	public void verifyShownTree(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the Search Tree is shown in the 'Oncall Calender Assignment' tab");
		
		//Check that the Search Tree is shown
		softAssert.assertEquals(reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility"), "visible");
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getCssValue(treeColumn, treeColumnName, "visibility").equalsIgnoreCase("visible")) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The 'Tree Column' is shown in the 'Oncall Calender Assignment' tab");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Tree Column' is not shown in the 'Oncall Calender Assignment' tab", "verifyHiddenTree");
		}
	}
	
	// ~~~ Calender ~~~ //
	
	public String getCurrentCalenderMonth() {
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Initialize Variable(s)
		String calenderMonth = reportLoggerMethods.getText(currentDateLabel, currentDateLabelName);
		
		//Return the Calender Month
		return calenderMonth.substring(0, calenderMonth.length() - 5);
	}
	
	public String getCurrentCalenderYear() {
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Initialize Variable(s)
		String calenderYear = reportLoggerMethods.getText(currentDateLabel, currentDateLabelName);
		
		//Return the Calender Month
		return calenderYear.substring(calenderYear.length() - 4, calenderYear.length());
	}
	
	public String getNewCurrentCalenderMonth(String forwardOrBackward, String amountOfMonths) {
		int numberOfMonths = Integer.parseInt(amountOfMonths);
		int currentMonth  = getMonthNumber(getCurrentCalenderMonth());
		
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
		int currentMonth  = getMonthNumber(getCurrentCalenderMonth());
		int currentYear = Integer.parseInt(getCurrentCalenderYear());
		
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
		reportLoggerMethods.reportInfo("Moving forward a month on the 'Calender Assignment' calender");
		
		//Move forward a month on the 'Calender Assignment' calender
		for (int i = 0; i < Integer.parseInt(amountOfMonths); i++) {
			reportLoggerMethods.click(nextMonthButton, nextMonthButtonName);
		}
	}
	
	public void clickBackwardMonthButton(String amountOfMonths) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Moving backward a month on the 'Calender Assignment' calender");
		
		//Initialize Variable(s)
		int monthCount = Integer.parseInt(amountOfMonths);
		
		//Increase the month count by 1, if moving back past 4 months in order to avoid losing a month
		if (monthCount >= 4) {
			monthCount += 1;
		}
		
		//Move backward a month on the 'Calender Assignment' calender
		for (int i = 0; i < monthCount; i++) {
			reportLoggerMethods.click(previousMonthButton, previousMonthButtonName);
		}
	}
	
	public void verifyCalenderDate(SoftAssert softAssert, String expectedMonth, String expectedYear) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the 'Calender Assignment' calender is set to the correct year and month");
		
		//Check that the 'Calender Assignment' calender is set to the correct year and month
		if (getCurrentCalenderYear().equalsIgnoreCase(expectedYear) && getCurrentCalenderMonth().equalsIgnoreCase(expectedMonth)) {
			//Assert the status of the current date of the 'Calender Assignment' calender
			softAssert.assertEquals(getCurrentCalenderYear(), expectedYear);
			softAssert.assertEquals(getCurrentCalenderMonth(), expectedMonth);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Calender Assignment' calender's current year and month matches expectations");
		} else {
			//Assert the status of the current date of the 'Calender Assignment' calender
			softAssert.assertEquals(getCurrentCalenderYear(), expectedYear);
			softAssert.assertEquals(getCurrentCalenderMonth(), expectedMonth);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Calender Assignment' calender's date is currently set to: " + getCurrentCalenderMonth() + ", " + getCurrentCalenderYear() + " instead of " + expectedMonth + ", " + expectedYear, "verifyValidInput");
		}
	}
	
}