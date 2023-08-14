package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PersonalProfile_ProfileLocators;

public class PersonalProfile_ProfilePage extends PersonalProfile_ProfileLocators {
	
	//Constructor
	public PersonalProfile_ProfilePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Exceptions ~~~ //
	
	public void createException(String exceptionType, String messagingID, String day, String month, String year, String hour, String minutes, String ampm, String timeZone, String remark) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a Coverage Exception");
		
		//Check an Exception Type checkbox
		if (exceptionType.equalsIgnoreCase("coverage")) {
			//Check the 'Coverage Exception' checkbox
			reportLoggerMethods.click(coverageCheckbox, coverageCheckboxName);
		} else if (exceptionType.equalsIgnoreCase("referral")) {
			//Check the 'Referral Exception' checkbox
			reportLoggerMethods.click(referralCheckbox, referralCheckboxName);
		} else if (exceptionType.equalsIgnoreCase("page block")) {
			//Check the 'Page Block Exception' checkbox
			reportLoggerMethods.click(pageBlockCheckbox, pageBlockCheckboxName);
		}
		
		//Click 'New' to start creating a new Coverage Exception
		reportLoggerMethods.click(newExceptionButton, newExceptionButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the 'Messaging ID'
		if (exceptionType.equalsIgnoreCase("coverage")) {
			reportLoggerMethods.sendKeys(coverageExceptionMessageIDField, coverageExceptionMessageIDFieldName, messagingID);
		} else if (exceptionType.equalsIgnoreCase("referral")) {
			reportLoggerMethods.sendKeys(referralExceptionMessageIDField, referralExceptionMessageIDFieldName, messagingID);
		} else if (exceptionType.equalsIgnoreCase("page block")) {
			reportLoggerMethods.sendKeys(pageBlockExceptionMessageIDField, pageBlockExceptionMessageIDFieldName, messagingID);
		}
		
		//Enter the 'end day'
		reportLoggerMethods.sendKeys(exceptionEndDateDayField, exceptionEndDateDayFieldName, day);
		
		//Enter the 'end month'
		reportLoggerMethods.selectDropDown(exceptionEndDateMonthDropDown, exceptionEndDateMonthDropDownName, month);
		
		//Enter the 'end year'
		reportLoggerMethods.sendKeys(exceptionEndDateYearField, exceptionEndDateYearFieldName, year);
		
		//Enter the 'end hour'
		reportLoggerMethods.sendKeys(exceptionEndDateHourField, exceptionEndDateHourFieldName, hour);
		
		//Enter the 'end minutes'
		reportLoggerMethods.sendKeys(exceptionEndDateMinutesField, exceptionEndDateMinutesFieldName, minutes);
		
		//Enter the 'end am/pm'
		reportLoggerMethods.selectDropDown(exceptionEndDateAMPMDropDown, exceptionEndDateAMPMDropDownName, ampm.toUpperCase());
		
		//Enter the 'end time zone'
		reportLoggerMethods.selectDropDown(exceptionTimeZoneDropDown, exceptionTimeZoneDropDownName, timeZone.toUpperCase());
		
		//Enter the 'remark'
		reportLoggerMethods.sendKeys(exceptionRemarkField, exceptionRemarkFieldName, remark);
		
		//Save the Exception
		reportLoggerMethods.click(exceptionSaveButton, exceptionSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	public void createException(String exceptionType, String messagingID, String startDay, String startMonth, String startYear, String startHour, String startMinutes, String startAMPM, String endDay, String endMonth, String endYear, String endHour, String endMinutes, String endAMPM, String timeZone, String remark) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a Coverage Exception");
		
		//Check an Exception Type checkbox
		if (exceptionType.equalsIgnoreCase("coverage")) {
			//Check the 'Coverage Exception' checkbox
			reportLoggerMethods.click(coverageCheckbox, coverageCheckboxName);
		} else if (exceptionType.equalsIgnoreCase("referral")) {
			//Check the 'Referral Exception' checkbox
			reportLoggerMethods.click(referralCheckbox, referralCheckboxName);
		} else if (exceptionType.equalsIgnoreCase("page block")) {
			//Check the 'Page Block Exception' checkbox
			reportLoggerMethods.click(pageBlockCheckbox, pageBlockCheckboxName);
		}
		
		//Click 'New' to start creating a new Coverage Exception
		reportLoggerMethods.click(newExceptionButton, newExceptionButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the messaging ID
		if (exceptionType.equalsIgnoreCase("coverage")) {
			reportLoggerMethods.sendKeys(coverageExceptionMessageIDField, coverageExceptionMessageIDFieldName, messagingID);
		} else if (exceptionType.equalsIgnoreCase("referral")) {
			reportLoggerMethods.sendKeys(referralExceptionMessageIDField, referralExceptionMessageIDFieldName, messagingID);
		} else if (exceptionType.equalsIgnoreCase("page block")) {
			reportLoggerMethods.sendKeys(pageBlockExceptionMessageIDField, pageBlockExceptionMessageIDFieldName, messagingID);
		}
		
		//Clear the existing 'start day' value
		reportLoggerMethods.clear(exceptionStartDateDayField, exceptionStartDateDayFieldName);
		
		//Enter the 'start day'
		reportLoggerMethods.sendKeys(exceptionStartDateDayField, exceptionStartDateDayFieldName, startDay);
		
		//Enter the 'start month'
		reportLoggerMethods.selectDropDown(exceptionStartDateMonthDropDown, exceptionStartDateMonthDropDownName, startMonth);
		
		//Clear the existing 'start year' value
		reportLoggerMethods.clear(exceptionStartDateYearField, exceptionStartDateYearFieldName);
		
		//Enter the 'start year'
		reportLoggerMethods.sendKeys(exceptionStartDateYearField, exceptionStartDateYearFieldName, startYear);
		
		//Clear the existing 'start hour' value
		reportLoggerMethods.clear(exceptionStartDateHourField, exceptionStartDateHourFieldName);
		
		//Enter the 'start hour'
		reportLoggerMethods.sendKeys(exceptionStartDateHourField, exceptionStartDateHourFieldName, startHour);
		
		//Clear the existing 'start minutes' value
		reportLoggerMethods.clear(exceptionStartDateMinutesField, exceptionStartDateMinutesFieldName);
		
		//Enter the 'start minutes'
		reportLoggerMethods.sendKeys(exceptionStartDateMinutesField, exceptionStartDateMinutesFieldName, startMinutes);
		
		//Enter the 'start am/pm'
		reportLoggerMethods.selectDropDown(exceptionStartDateAMPMDropDown, exceptionStartDateAMPMDropDownName, startAMPM.toUpperCase());
		
		//Enter the 'end day'
		reportLoggerMethods.sendKeys(exceptionEndDateDayField, exceptionEndDateDayFieldName, endDay);
		
		//Enter the 'end month'
		reportLoggerMethods.selectDropDown(exceptionEndDateMonthDropDown, exceptionEndDateMonthDropDownName, endMonth);
		
		//Enter the 'end year'
		reportLoggerMethods.sendKeys(exceptionEndDateYearField, exceptionEndDateYearFieldName, endYear);
		
		//Enter the 'end hour'
		reportLoggerMethods.sendKeys(exceptionEndDateHourField, exceptionEndDateHourFieldName, endHour);
		
		//Enter the 'end minutes'
		reportLoggerMethods.sendKeys(exceptionEndDateMinutesField, exceptionEndDateMinutesFieldName, endMinutes);
		
		//Enter the 'end am/pm'
		reportLoggerMethods.selectDropDown(exceptionEndDateAMPMDropDown, exceptionEndDateAMPMDropDownName, endAMPM.toUpperCase());
		
		//Enter the 'end time zone'
		reportLoggerMethods.selectDropDown(exceptionTimeZoneDropDown, exceptionTimeZoneDropDownName, timeZone.toUpperCase());
		
		//Enter the 'remark'
		reportLoggerMethods.sendKeys(exceptionRemarkField, exceptionRemarkFieldName, remark);
		
		//Save the Exception
		reportLoggerMethods.click(exceptionSaveButton, exceptionSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
	}
	
	public void verifyCreatedCoverageException(SoftAssert softAssert, String messageID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Coverage Exception was created successfully");
		
		//Check if the Coverage Exception was created successfully (by checking for it being displayed)
		if (reportLoggerMethods.isDisplayed(existingCoverageExceptionLabel, existingCoverageExceptionLabelName) && reportLoggerMethods.isDisplayed(exceptionDeleteButton, exceptionDeleteButtonName)) {
			//Check if the 'Covered By' label value matches expectation(s)
			if (reportLoggerMethods.getText(existingExceptionMessageIDLabel, existingExceptionMessageIDLabelName).contains(messageID)) {
				//Perform a successful assert for the SoftAssert, since the Coverage Exception was created & is displayed successfully
				softAssert.assertEquals("Coverage Exception was created successfully", "Coverage Exception was created successfully");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Coverage Exception was created successfully");
			} else {
				//Perform a failed assert for the SoftAssert, since the Coverage Exception has an incorrect 'Covered By' value
				softAssert.assertEquals("The Coverage Exception has a 'Covered By' value of '" + reportLoggerMethods.getText(existingExceptionMessageIDLabel, existingExceptionMessageIDLabelName) + "'", "The Coverage Exception contains '" + messageID + "' in its value");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The existing Coverage Exception's 'Covered By' value does not match expectations", "verifyCreatedCoverageException");
			}
		} else {
			//Output the test result to the report
			softAssert.assertEquals("Coverage Exception was not created", "Coverage Exception was created successfully");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Coverage Exception was not created", "verifyCreatedCoverageException");
		}
	}
	
	public void verifyCreatedReferralException(SoftAssert softAssert, String messageID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Referral Exception was created successfully");
		
		//Check if the Referral Exception was created successfully (by checking for it being displayed)
		if (reportLoggerMethods.isDisplayed(existingReferralExceptionLabel, existingReferralExceptionLabelName) && reportLoggerMethods.isDisplayed(exceptionDeleteButton, exceptionDeleteButtonName)) {
			//Check if the 'Callback Number' label value matches expectation(s)
			if (reportLoggerMethods.getText(existingExceptionMessageIDLabel, existingExceptionMessageIDLabelName).equalsIgnoreCase(messageID)) {
				//Perform a successful assert for the SoftAssert, since the Referral Exception was created & is displayed successfully
				softAssert.assertEquals("Referral Exception was created successfully", "Referral Exception was created successfully");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Referral Exception was created successfully");
			} else {
				//Perform a failed assert for the SoftAssert, since the Referral Exception has an incorrect 'Callback Number' value
				softAssert.assertEquals("The Referral Exception has a 'Callback Number' value of '" + reportLoggerMethods.getText(existingExceptionMessageIDLabel, existingExceptionMessageIDLabelName) + "'", "The Referral Exception has a 'Callback Number' value of '" + messageID + "'");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The existing Referral Exception's 'Callback Number' value does not match expectations", "verifyCreatedReferralException");
			}
		} else {
			//Output the test result to the report
			softAssert.assertEquals("Referral Exception was not created", "Referral Exception was created successfully");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Referral Exception was not created", "verifyCreatedReferralException");
		}
	}
	
	public void verifyCreatedPageBlockException(SoftAssert softAssert, String messageID) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Page Block Exception was created successfully");
		
		//Check if the Page Block Exception was created successfully (by checking for it being displayed)
		if (reportLoggerMethods.isDisplayed(existingPageBlockExceptionLabel, existingPageBlockExceptionLabelName) && reportLoggerMethods.isDisplayed(exceptionDeleteButton, exceptionDeleteButtonName)) {
			//Check if the 'Message' label value matches expectation(s)
			if (reportLoggerMethods.getText(existingExceptionMessageIDLabel, existingExceptionMessageIDLabelName).equalsIgnoreCase(messageID)) {
				//Perform a successful assert for the SoftAssert, since the Page Block Exception was created & is displayed successfully
				softAssert.assertEquals("Page Block Exception was created successfully", "Page Block Exception was created successfully");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Page Block Exception was created successfully");
			} else {
				//Perform a failed assert for the SoftAssert, since the Page Block Exception has an incorrect 'Message' value
				softAssert.assertEquals("The Page Block Exception has a 'Message' value of '" + reportLoggerMethods.getText(existingExceptionMessageIDLabel, existingExceptionMessageIDLabelName) + "'", "The Page Block Exception has a 'Message' value of '" + messageID + "'");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The existing Page Block Exception's 'Message' value does not match expectations", "verifyCreatedPageBlockException");
			}
		} else {
			//Perform a failed assert for the SoftAssert, since the 'Page Block Exception' was not created
			softAssert.assertEquals("Page Block Exception was not created", "Page Block Exception was created successfully");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Page Block Exception was not created", "verifyCreatedPageBlockException");
		}
	}
	
	public void verifyCreatedFutureReferralException2(SoftAssert softAssert, String messageID, String startDate, String endDate, String timeZone) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Page Referral Exception was created successfully");
		try {
		//Loop through all the 'Future Exceptions'
		for (int i = 0; i < reportLoggerMethods.getSize(existingFutureReferralExceptionLabelList, existingFutureReferralExceptionLabelListName); i++) {
			//Initialize Variable(s)
			String existingFutureReferralExceptionLabel = reportLoggerMethods.getText(existingFutureReferralExceptionLabelList.get(i), existingFutureReferralExceptionLabelListName(i));
			String existingFutureExceptionMessageLabel = reportLoggerMethods.getText(existingFutureExceptionMessageLabelList.get(i), existingFutureExceptionMessageLabelListName(i));
			String existingFutureExceptionStartDate = reportLoggerMethods.getText(existingFutureExceptionStartDateList.get(i), existingFutureExceptionStartDateListName(i));
			String existingFutureExceptionEndDate = reportLoggerMethods.getText(existingFutureExceptionEndDateList.get(i), existingFutureExceptionEndDateListName(i));
			String existingFutureExceptionTimeZone = reportLoggerMethods.getText(existingFutureExceptionTimeZoneList.get(i), existingFutureExceptionTimeZoneListName(i));
			
			//Check if the current 'Future Exception' has the details that match expectation(s)
			if (existingFutureReferralExceptionLabel.equalsIgnoreCase("referral") &&
					existingFutureExceptionMessageLabel.equalsIgnoreCase(messageID) &&
					existingFutureExceptionStartDate.equalsIgnoreCase(startDate) &&
					existingFutureExceptionEndDate.equalsIgnoreCase(endDate) &&
					existingFutureExceptionTimeZone.equalsIgnoreCase(timeZone)) {
				
				//Perform asserts for the created 'Future Referral Exception'
				softAssert.assertEquals(existingFutureReferralExceptionLabel.toLowerCase(), "referral");
				softAssert.assertEquals(existingFutureExceptionMessageLabel, messageID);
				softAssert.assertEquals(existingFutureExceptionStartDate, startDate);
				softAssert.assertEquals(existingFutureExceptionEndDate, endDate);
				softAssert.assertEquals(existingFutureExceptionTimeZone, timeZone);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Future Referral Exception' was successfully created");
				
				//Finish the method with an Extent Report status
				return;
			}
		}
		
		//Perform asserts for the created 'Future Referral Exception'
		softAssert.assertEquals("Desired 'Future Referral Exception' not found", "Desired 'Future Referral Exception' found");
		
		//Report the assert status to the Extent Report & System
		reportLoggerMethods.reportFailedCheckpoint("Failure: Desired 'Future Referral Exception' not found (Must be a 'Future Referral Exception' with Message = '" + messageID + "', Start Date = '" + startDate + "', End Date = '" + endDate + "', and Time Zone = '" + timeZone + "')", "verifyCreatedFutureReferralException");
		} catch (Exception e) {
			System.out.println("qwer");
		}
	}
	
	public void verifyCreatedFutureReferralException(SoftAssert softAssert, String messageID, String startDate, String endDate, String timeZone) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Page Referral Exception was created successfully");
		
		//Loop through all the 'Future Exceptions'
		for (int i = 0; i < reportLoggerMethods.getSize(existingFutureReferralExceptionLabelList, existingFutureReferralExceptionLabelListName); i++) {
			//Initialize Variable(s)
			String existingFutureReferralExceptionLabel = reportLoggerMethods.getText(existingFutureReferralExceptionLabelList.get(i), existingFutureReferralExceptionLabelListName(i));
			String existingFutureExceptionMessageLabel = reportLoggerMethods.getText(existingFutureExceptionMessageLabelList.get(i), existingFutureExceptionMessageLabelListName(i));
			String existingFutureExceptionStartDate = reportLoggerMethods.getText(existingFutureExceptionStartDateList.get(i), existingFutureExceptionStartDateListName(i));
			String existingFutureExceptionTimeZone = reportLoggerMethods.getText(existingFutureExceptionTimeZoneList.get(i), existingFutureExceptionTimeZoneListName(i));
			
			//Check if the current 'Future Exception' has the details that match expectation(s)
			if (existingFutureReferralExceptionLabel.equalsIgnoreCase("referral") &&
					existingFutureExceptionMessageLabel.equalsIgnoreCase(messageID) &&
					existingFutureExceptionStartDate.equalsIgnoreCase(startDate) &&
					existingFutureExceptionTimeZone.equalsIgnoreCase(timeZone)) {
				
				//Perform asserts for the created 'Future Referral Exception'
				softAssert.assertEquals(existingFutureReferralExceptionLabel.toLowerCase(), "referral");
				softAssert.assertEquals(existingFutureExceptionMessageLabel, messageID);
				softAssert.assertEquals(existingFutureExceptionStartDate, startDate);
				softAssert.assertEquals(existingFutureExceptionTimeZone, timeZone);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Future Referral Exception' was successfully created");
				
				//Finish the method with an Extent Report status
				return;
			}
		}
		
		//Perform asserts for the created 'Future Referral Exception'
		softAssert.assertEquals("Desired 'Future Referral Exception' not found", "Desired 'Future Referral Exception' found");
		
		//Report the assert status to the Extent Report & System
		reportLoggerMethods.reportFailedCheckpoint("Failure: Desired 'Future Referral Exception' not found (Must be a 'Future Referral Exception' with Message = '" + messageID + "', Start Date = '" + startDate + "', End Date = '" + endDate + "', and Time Zone = '" + timeZone + "')", "verifyCreatedFutureReferralException");
	}
	
	public void deleteCurrentException() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting any existing, current Exception");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(6000);
		
		//Delete the existing Exception
		reportLoggerMethods.click(exceptionDeleteButton, exceptionDeleteButtonName);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(6000);
	}
	
	public void deleteFutureException() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting any existing, future Exception");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(6000);
		
		//Select the checkbox for the 'Future Exception' before deleting the selected 'Future Exceptions'
		reportLoggerMethods.click(checkFirstExistingFutureExceptionCheckbox, checkFirstExistingFutureExceptionCheckboxName);
		
		//Delete the Future Exception
		reportLoggerMethods.click(deleteExceptionButton, deleteExceptionButtonName);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(6000);
	}
	
	// ~~~ Instructions ~~~ //
	
	public void deleteInstructions() {
		//Delete the Instructions
		reportLoggerMethods.click(deleteInstructionsButton, deleteInstructionsButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void enterInstructions(String instructions) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering/Modifying the Instructions");
		
		//Delete any existing Instructions
		deleteInstructions();
		
		//Enter the Instructions
		reportLoggerMethods.sendKeys(instructionsTextArea, instructionsTextAreaName, instructions);
		
		//Save the Instructions
		reportLoggerMethods.click(saveInstructionsButton, saveInstructionsButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
	}
	
	// ~~~ Directory ~~~ //
	
	public void createDirectory(String directoryDepartment, String phoneNumber, String phoneNumberType) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating a new Directory");
		
		//Click the 'New Directory' button
		reportLoggerMethods.click(newDirectoryButton, newDirectoryButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Select the Department
		reportLoggerMethods.selectDropDown(newDirectoryDepartmentDropDown, newDirectoryDepartmentDropDownName, directoryDepartment);
		
		//Enter the Phone Number
		reportLoggerMethods.sendKeys(newDirectoryPhoneNumberField, newDirectoryPhoneNumberFieldName, phoneNumber);
		
		//Select the Department
		reportLoggerMethods.selectDropDown(newDirectoryPhoneNumberTypeDropDown, newDirectoryPhoneNumberTypeDropDownName, phoneNumberType);
		
		//Click 'Save' to create the Directory
		reportLoggerMethods.click(directorySaveButton, directorySaveButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public int returnCreatedDirectoryRow(String expectedDirectoryDepartment, String expectedPhoneNumber, String expectedPhoneNumberType) {
		//Loop through the list of existing Directories
		for (int i = 0; i < reportLoggerMethods.getSize(existingDirectoryPhoneNumberLabelList, existingDirectoryPhoneNumberLabelListName); i++) {
			//Initialize Variable(s)
			String directoryPhhoneNumber = reportLoggerMethods.getText(existingDirectoryPhoneNumberLabelList.get(i), existingDirectoryPhoneNumberLabelListName(i));
			String directoryPhoneNumberType = reportLoggerMethods.getText(existingDirectoryPhoneNumberTypeLabelList.get(i), existingDirectoryPhoneNumberTypeLabelListName(i));
			String directoryDepartment = reportLoggerMethods.getText(existingDirectoryDepartmentLabelList.get(i), existingDirectoryDepartmentLabelListName(i));
			
			//Check if the Directory Info matches Expectations
			if (directoryPhhoneNumber.equalsIgnoreCase(expectedPhoneNumber) && directoryPhoneNumberType.equalsIgnoreCase(expectedPhoneNumberType) && directoryDepartment.equalsIgnoreCase(expectedDirectoryDepartment)) {
				//Return the relevant Directory row
				return i;
			}
		}
		
		//Return an invalid value to indicate that no relevant Directory row was found
		return -1;
	}
	
	public void verifyCreatedDirectory(SoftAssert softAssert, int directoryRow, String expectedDirectoryDepartment, String expectedPhoneNumber, String expectedPhoneNumberType) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Directory was created with the expected details");
		
		//Check Directory Info of a specific row
		if (directoryRow == -1) {
			//Assert the status of the created Directory
			softAssert.assertEquals("The Directory was not created", "The Directory was created");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Directory was not created", "verifyCreatedDirectory");
		} else {
			//Initialize Variable(s)
			String successMessage = "Successful: The Directory was created";
			String failureMessage = "Failure: ";
			
			String existingDirectoryPhoneNumberLabel = reportLoggerMethods.getText(existingDirectoryPhoneNumberLabelList.get(directoryRow), existingDirectoryPhoneNumberLabelListName(directoryRow));
			String existingDirectoryPhoneNumberTypeLabel = reportLoggerMethods.getText(existingDirectoryPhoneNumberTypeLabelList.get(directoryRow), existingDirectoryPhoneNumberTypeLabelListName(directoryRow));
			String existingDirectoryDepartmentLabel = reportLoggerMethods.getText(existingDirectoryDepartmentLabelList.get(directoryRow), existingDirectoryDepartmentLabelListName(directoryRow));
			
			//Assert the new Directory's details
			softAssert.assertEquals(existingDirectoryPhoneNumberLabel, expectedPhoneNumber);
			softAssert.assertEquals(existingDirectoryPhoneNumberTypeLabel, expectedPhoneNumberType);
			softAssert.assertEquals(existingDirectoryDepartmentLabel, expectedDirectoryDepartment);
			
			//Report Success to the report
			if (existingDirectoryPhoneNumberLabel.equalsIgnoreCase(expectedPhoneNumber) && existingDirectoryPhoneNumberTypeLabel.equalsIgnoreCase(expectedPhoneNumberType) && existingDirectoryDepartmentLabel.equalsIgnoreCase(expectedDirectoryDepartment)) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Check if the Phone Number listed does not match expectations
			if (!existingDirectoryPhoneNumberLabel.equalsIgnoreCase(expectedPhoneNumber)) {
				failureMessage += "The Phone Number found (" + existingDirectoryPhoneNumberLabel + ") does not match the expected Phone Number (" + expectedPhoneNumber + "). ";
			}
			
			//Check if the Phone Number Type listed does not match expectations
			if (!existingDirectoryPhoneNumberTypeLabel.equalsIgnoreCase(expectedPhoneNumberType)) {
				failureMessage += "The Phone Number Type found (" + existingDirectoryPhoneNumberTypeLabel + ") does not match the expected Phone Number Type (" + expectedPhoneNumberType + "). ";
			}
			
			//Check if the Department listed does not match expectations
			if (!existingDirectoryDepartmentLabel.equalsIgnoreCase(expectedDirectoryDepartment)) {
				failureMessage += "The Department found (" + existingDirectoryDepartmentLabel + ") does not match the expected Department (" + expectedDirectoryDepartment + "). ";
			}
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyCreatedDirectory");
		}
	}
	
	public void modifyDirectory(SoftAssert softAssert, int directoryRow, String addressType, String street1, String street2, String city, String state, String postalCode) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying an existing Directory");
		
		//Check Directory Info of a specific row
		if (directoryRow == -1) {
			//Assert the status of the created Directory
			softAssert.assertEquals("The Directory to be modified was not found", "The Directory to be modified was found");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Directory to be modified was not found", "modifyDirectory");
		} else {
			//Check the desired Directory's checkbox
			reportLoggerMethods.click(directoryCheckbox(directoryRow), directoryCheckboxName(directoryRow));
			
			//Click the edit Directory button
			reportLoggerMethods.click(editDirectoryButton, editDirectoryButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Select the Address Type
			reportLoggerMethods.selectDropDown(editDirectoryAddressTypeDropDown, editDirectoryAddressTypeDropDownName, addressType);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Enter the Street 1 value
			reportLoggerMethods.clear(editDirectoryStreet1Field, editDirectoryStreet1FieldName);
			reportLoggerMethods.sendKeys(editDirectoryStreet1Field, editDirectoryStreet1FieldName, street1);
			
			//Enter the Street 2 value
			reportLoggerMethods.clear(editDirectoryStreet2Field, editDirectoryStreet2FieldName);
			reportLoggerMethods.sendKeys(editDirectoryStreet2Field, editDirectoryStreet2FieldName, street2);
			
			//Enter the City
			reportLoggerMethods.clear(editDirectoryCityField, editDirectoryCityFieldName);
			reportLoggerMethods.sendKeys(editDirectoryCityField, editDirectoryCityFieldName, city);
			
			//Select the State
			reportLoggerMethods.selectDropDown(editDirectoryStateDropDown, editDirectoryStateDropDownName, state);
			
			//Enter the Postal Code
			reportLoggerMethods.clear(editDirectoryPostalCodeField, editDirectoryPostalCodeFieldName);
			reportLoggerMethods.sendKeys(editDirectoryPostalCodeField, editDirectoryPostalCodeFieldName, postalCode);
			
			//Click 'Save' to modify the Directory
			reportLoggerMethods.click(directorySaveButton, directorySaveButtonName);
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert/pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public int returnModifiedDirectoryRow(String expectedAddress, String expectedAddressType) {
		//Loop through the list of existing Directories
		for (int i = 0; i < reportLoggerMethods.getSize(existingDirectoryAddressLabelList, existingDirectoryAddressLabelListName); i++) {
			if (reportLoggerMethods.getText(existingDirectoryAddressLabelList.get(i), existingDirectoryAddressLabelListName(i)).equalsIgnoreCase(expectedAddress) && reportLoggerMethods.getText(existingDirectoryAddressTypeLabelList.get(i), existingDirectoryAddressTypeLabelListName(i)).equalsIgnoreCase(expectedAddressType)) {
				//Return the relevant Directory row
				return i;
			}
		}
		
		//Return an invalid value to indicate that no relevant Directory row was found
		return -1;
	}
	
	public void verifyModifyDirectory(SoftAssert softAssert, int directoryRow, String expectedAddress, String expectedAddressType) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the Directory was modified with the expected details");
		
		//Check Directory Info of a specific row
		if (directoryRow == -1) {
			//Assert the status of the modified Directory
			softAssert.assertEquals("The Directory was not modified", "The Directory was modified");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Directory was not modified", "verifyModifyDirectory");
		} else {
			//Initialize Variable(s)
			String successMessage = "Successful: The Directory was modified";
			String failureMessage = "Failure: ";
			
			String existingDirectoryAddressLabel = reportLoggerMethods.getText(existingDirectoryAddressLabelList.get(directoryRow), existingDirectoryAddressLabelListName(directoryRow));
			String existingDirectoryAddressTypeLabel = reportLoggerMethods.getText(existingDirectoryAddressTypeLabelList.get(directoryRow), existingDirectoryAddressTypeLabelListName(directoryRow));
			
			//Assert the modified Directory's details
			softAssert.assertEquals(existingDirectoryAddressLabel, expectedAddress);
			softAssert.assertEquals(existingDirectoryAddressTypeLabel, expectedAddressType);
			
			//Check the modified Directory's details
			if (existingDirectoryAddressLabel.equalsIgnoreCase(expectedAddress) && existingDirectoryAddressTypeLabel.equalsIgnoreCase(expectedAddressType)) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Check if the Address listed does not match expectations
			if (!existingDirectoryAddressLabel.equalsIgnoreCase(expectedAddress)) {
				failureMessage += "The Address found (" + existingDirectoryAddressLabel + ") does not match the expected Address (" + expectedAddress + "). ";
			}
			
			//Check if the Address Type listed does not match expectations
			if (!existingDirectoryAddressTypeLabel.equalsIgnoreCase(expectedAddressType)) {
				failureMessage += "The Address Type found (" + existingDirectoryAddressTypeLabel + ") does not match the expected Address Type (" + expectedAddressType + "). ";
			}
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyModifyDirectory");
		}
	}
	
	public void deleteDirectory(int directoryRow) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting the recently created Directory");
		
		//Check Directory Info of a specific row
		if (directoryRow != -1) {
			//Check the desired Directory's checkbox
			reportLoggerMethods.click(directoryCheckbox(directoryRow), directoryCheckboxName(directoryRow));
			
			//Click the delete Directory button
			reportLoggerMethods.click(deleteDirectoryButton, deleteDirectoryButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert/pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert/pop-up
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	// ~~~ User Status ~~~ //
	
	public void updateUserStatus(String updateStatus, String updateStatusReason) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Updating the user's status");
		
		//Select the new user status
		reportLoggerMethods.selectDropDown(userStatusDropDown, userStatusDropDownName, updateStatus);
		
		//Check Directory Info of a specific row
		if (updateStatus.contains("-1")) {
			//Click the 'update' button
			reportLoggerMethods.click(continueStatusUpdateButton, continueStatusUpdateButtonName);
			
			//Enter the 'User Defined' status
			reportLoggerMethods.clear(userDefinedStatusField, userDefinedStatusFieldName);
			reportLoggerMethods.sendKeys(userDefinedStatusField, userDefinedStatusFieldName, updateStatusReason);
			
			//Click the 'save' button
			reportLoggerMethods.click(saveUserDefinedUpdatedStatusButton, saveUserDefinedUpdatedStatusButtonName);
		} else {
			//Click the 'save' button
			reportLoggerMethods.click(saveUpdatedStatusButton, saveUpdatedStatusButtonName);
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifyUserStatus(SoftAssert softAssert, String expectedStatusCode, String expectedStatusMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the user's current status matches expectations");
		
		//Initialize Variable(s)
		String successMessage = "Success: The user's status matches expectations";
		String failureMessage = "Failure: ";
		
		String currentStatusCode = reportLoggerMethods.getAttribute(currentStatusCodeLabel, currentStatusCodeLabelName, "innerHTML");
		String currentStatusReason = reportLoggerMethods.getAttribute(currentStatusReasonLabel, currentStatusReasonLabelName, "innerHTML");
		
		//Assert the status of the user's current status
		softAssert.assertEquals(currentStatusCode, expectedStatusCode);
		softAssert.assertEquals(currentStatusReason, expectedStatusMessage);
		
		//Check if the user's status matches expectations
		if (currentStatusCode.equalsIgnoreCase(expectedStatusCode) && currentStatusReason.equalsIgnoreCase(expectedStatusMessage)) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
			
			//Finish the method with an Extent Report status
			return;
		}
		
		//Check if the user's current status code matches expectation
		if (!currentStatusCode.equalsIgnoreCase(expectedStatusCode)) {
			failureMessage += "The user's status code (" + currentStatusCode + ") does not match expectations (" + expectedStatusCode + "). ";
		}
		
		//Check if the user's current status message matches expectation
		if (!currentStatusReason.equalsIgnoreCase(expectedStatusMessage)) {
			failureMessage += "The user's status (" + currentStatusReason + ") does not match expectations (" + expectedStatusMessage + "). ";
		}
		
		//Report the assert status to the Extent Report & System
		reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyUserStatus");
	}
	
}