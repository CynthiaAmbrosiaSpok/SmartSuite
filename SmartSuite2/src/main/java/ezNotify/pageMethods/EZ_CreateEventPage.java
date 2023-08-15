package ezNotify.pageMethods;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ezNotify.pageLocators.EZ_CreateEventLocators;

public class EZ_CreateEventPage extends EZ_CreateEventLocators {
	
	//Constructor
	public EZ_CreateEventPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Create Event ~~~ //
	
	public void openNewEventPage() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Create New Event' Page");
		
		//Click 'Create New Event' to open the Event Creation Page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
	}
	
	public void openAdvancedOptionsPage() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Advanced Options' Page");
		
		//Click 'Advanced Options' to open the Event Creation Page
		reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
	}
	
	public void checkSelectRecipientsCheckboxes(String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking the desired 'Select Recipients' Checkboxes");
		
		//Check if the 'Select Recipients -> Individuals' checkbox should be checked
		if (selectRepicientsIndividuals.equalsIgnoreCase("y") || selectRepicientsIndividuals.equalsIgnoreCase("yes")) {
			//Check if the 'Select Recipients -> Individuals' checkbox is already checked
			if (!reportLoggerMethods.isSelected(recipientsCheckboxes.get(0), recipientsIndividualsCheckboxName)) {
				//Check the 'Select Recipients -> Individuals' checkbox
				reportLoggerMethods.click(recipientsCheckboxes.get(0), recipientsIndividualsCheckboxName);
			}
		} else {
			//Check if the 'Select Recipients -> Individuals' checkbox is already checked
			if (reportLoggerMethods.isSelected(recipientsCheckboxes.get(0), recipientsIndividualsCheckboxName)) {
				//Un-check the 'Select Recipients -> Individuals' checkbox
				reportLoggerMethods.click(recipientsCheckboxes.get(0), recipientsIndividualsCheckboxName);
			}
		}
		
		//Check if the 'Select Recipients -> Message Groups' checkbox should be checked
		if (selectRepicientsMessageGroups.equalsIgnoreCase("y") || selectRepicientsMessageGroups.equalsIgnoreCase("yes")) {
			//Check if the 'Select Recipients -> Message Groups' checkbox is already checked
			if (!reportLoggerMethods.isSelected(recipientsCheckboxes.get(1), recipientsMessageGroupsCheckboxName)) {
				//Check the 'Select Recipients -> Message Groups' checkbox
				reportLoggerMethods.click(recipientsCheckboxes.get(1), recipientsMessageGroupsCheckboxName);
			}
		} else {
			//Check if the 'Select Recipients -> Message Groups' checkbox is already checked
			if (reportLoggerMethods.isSelected(recipientsCheckboxes.get(1), recipientsMessageGroupsCheckboxName)) {
				//Un-check the 'Select Recipients -> Message Groups' checkbox
				reportLoggerMethods.click(recipientsCheckboxes.get(1), recipientsMessageGroupsCheckboxName);
			}
		}
		
		//Check if the 'Select Recipients -> Oncall Groups' checkbox should be checked
		if (selectRepicientsOncallGroups.equalsIgnoreCase("y") || selectRepicientsOncallGroups.equalsIgnoreCase("yes")) {
			//Check if the 'Select Recipients -> Oncall Groups' checkbox is already checked
			if (!reportLoggerMethods.isSelected(recipientsCheckboxes.get(2), recipientsOnCallGroupsCheckboxName)) {
				//Check the 'Select Recipients -> Oncall Groups' checkbox
				reportLoggerMethods.click(recipientsCheckboxes.get(2), recipientsOnCallGroupsCheckboxName);
			}
		} else {
			//Check if the 'Select Recipients -> Oncall Groups' checkbox is already checked
			if (reportLoggerMethods.isSelected(recipientsCheckboxes.get(2), recipientsOnCallGroupsCheckboxName)) {
				//Un-check the 'Select Recipients -> Oncall Groups' checkbox
				reportLoggerMethods.click(recipientsCheckboxes.get(2), recipientsOnCallGroupsCheckboxName);
			}
		}
	}
	
	public void selectRecipients(String recipients) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Selecting the desired Recipients");
		
		//Initialize Variable(s)
		String[] recipientsList = recipients.split("/");
		
		//Add all specified recipients
		for (int i = 0; i < recipientsList.length; i++) {
			//Enter the specified reipient
			reportLoggerMethods.clear(recipientsSearchField, recipientsSearchFieldName);
			reportLoggerMethods.sendKeys(recipientsSearchField, recipientsSearchFieldName, recipientsList[i].trim());
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);
			
			//Select the first result of the recipient search list
			reportLoggerMethods.click(firstRecipientSearchResult, firstRecipientSearchResultName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public void enterEZNotifyEventInfo(String eventName, String message, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the ezNotify Event info");
		
		//Select 'Create New Event' to open the event creation page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
		
		//Enter the Event Name
		reportLoggerMethods.clear(eventNameField, eventNameFieldName);
		reportLoggerMethods.sendKeys(eventNameField, eventNameFieldName, eventName);
		
		//Enter the Event Message
		reportLoggerMethods.clear(eventMessageField, eventMessageFieldName);
		reportLoggerMethods.sendKeys(eventMessageField, eventMessageFieldName, message);
		
		//Enter the Event Short Message
		reportLoggerMethods.clear(eventShortMessageField, eventShortMessageFieldName);
		reportLoggerMethods.sendKeys(eventShortMessageField, eventShortMessageFieldName, shortMessage);
		
		//Select the Response Type
		if (responseType.equalsIgnoreCase("None")) {
			reportLoggerMethods.click(responseTypeNoneRadioButton, responseTypeNoneRadioButtonName);
		} else if (responseType.equalsIgnoreCase("Confirmation")) {
			reportLoggerMethods.click(responseTypeConfirmationRadioButton, responseTypeConfirmationRadioButtonName);
		} else if (responseType.equalsIgnoreCase("Yes/No Question")) {
			reportLoggerMethods.click(responseTypeYesNoQuestionRadioButton, responseTypeYesNoQuestionRadioButtonName);
			
			//Enter the Response Type Yes/No Question
			reportLoggerMethods.clear(responseTypeYesNoQuestionTextarea, responseTypeYesNoQuestionTextareaName);
			reportLoggerMethods.sendKeys(responseTypeYesNoQuestionTextarea, responseTypeYesNoQuestionTextareaName, responseTypeQuestion);
		}
		
		//Check the specified 'Select Recipients' checkboxes
		checkSelectRecipientsCheckboxes(selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups);
		
		//Select the specified 'Recipients'
		if (!recipients.equalsIgnoreCase("")) {
			selectRecipients(recipients);
		}
	}
	
	public void enterEZNotifyEventAdditionalOptionsInfo(String eventDuration, String deviceEscalation, String recipientsDeviceList, String procedureDeviceLiist, String viewProcedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Entering the ezNotify Event's Additional Options info");
		
		//Navigate to the Additional Options Page
		reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Enter a value for the 'Event Duration (Minutes)' Field
		reportLoggerMethods.clear(eventDurationField, eventDurationFieldName);
		reportLoggerMethods.sendKeys(eventDurationField, eventDurationFieldName, eventDuration);
		
		//Select Device Escalation
		if (deviceEscalation.equalsIgnoreCase("Use Device Escalation")) {
			reportLoggerMethods.click(deviceEscalationRadioButton, deviceEscalationRadioButtonName);
		} else if (deviceEscalation.equalsIgnoreCase("Notify all devices simultaneously")) {
			reportLoggerMethods.click(notifyAllDevicesSimultaneouslyRadioButton, notifyAllDevicesSimultaneouslyRadioButtonName);
		}
		
		//Use Recipient's Device List (if Available & Desired)
		if (recipientsDeviceList.equalsIgnoreCase("y") || recipientsDeviceList.equalsIgnoreCase("yes")) {
			reportLoggerMethods.click(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName);
		}
		
		//Select Procedure Device List
		reportLoggerMethods.selectDropDown(procedureDeviceDropDown, procedureDeviceDropDownName, procedureDeviceLiist);
		
		//View Devices for the selected Procedure Device List
		if (viewProcedureDeviceList.equalsIgnoreCase("y") || viewProcedureDeviceList.equalsIgnoreCase("yes")) {
			//Bring up the 'View Devices' Pop-up
			reportLoggerMethods.click(viewProcedureDevicesButton, viewProcedureDevicesButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);
			
			//Close the 'View Devices' Pop-up
			reportLoggerMethods.click(closeViewProcedureDevicesPopupButton, closeViewProcedureDevicesPopupButtonName);
		}
		
		//Save Additional Options
		reportLoggerMethods.click(saveAdditionalOptionsButton, saveAdditionalOptionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
	}
	
	public void createEZNotifyEvent(String eventName, String message, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String addAdditionalOptions, String eventDuration, String deviceEscalation, String recipientDeviceList, String procedureDeviceList, String viewProcedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating an ezNotify Event");
		
		//Enter the ezNotify Event info
		enterEZNotifyEventInfo(eventName, message, shortMessage, responseType, responseTypeQuestion, selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups, recipients);
		
		//Enter the ezNotify Event's Additional Options info, if desired
		if (addAdditionalOptions.equalsIgnoreCase("y") || addAdditionalOptions.equalsIgnoreCase("yes")) {
			enterEZNotifyEventAdditionalOptionsInfo(eventDuration, deviceEscalation, recipientDeviceList, procedureDeviceList, viewProcedureDeviceList);
		}
		
		//Create the New Event
		reportLoggerMethods.click(activateNewEventButton, activateNewEventButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Confirm the Creation of the New Event
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Accept the 'Created Event' Message
		reportLoggerMethods.acceptAlert();
	}
	
	public void createEZNotifyEvent2(String eventName, String message, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String eventDuration) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating an ezNotify Event");
		
		//Enter the ezNotify Event info
		enterEZNotifyEventInfo(eventName, message, shortMessage, responseType, responseTypeQuestion, selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups, recipients);
		
		//Navigate to the Additional Options Page
		reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Enter a value for the 'Event Duration (Minutes)' Field
		reportLoggerMethods.clear(eventDurationField, eventDurationFieldName);
		reportLoggerMethods.sendKeys(eventDurationField, eventDurationFieldName, eventDuration);
		
		//Save Additional Options
		reportLoggerMethods.click(saveAdditionalOptionsButton, saveAdditionalOptionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Create the New Event
		reportLoggerMethods.click(activateNewEventButton, activateNewEventButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Confirm the Creation of the New Event
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Accept the 'Created Event' Message
		reportLoggerMethods.acceptAlert();
	}
	
	public void verifyCreateEZNotifyEventContents(SoftAssert softAssert, String eventName, String message, String shortMessage, String responseType, String responseTypeQuestion) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the ezNotify Event's Values");
		
		//Initialize Variable(s)
		String successMessage = "Success: The 'Create ezNotify Event' Values match Expectations";
		String failureMessage = "Failure ";
		boolean successfulMatch = true;
		
		String actualEventName = reportLoggerMethods.getAttribute(eventNameField, eventNameFieldName, "value");
		String actualEventMessage = reportLoggerMethods.getAttribute(eventMessageField, eventMessageFieldName, "value");
		String actualEventShortMessage = reportLoggerMethods.getAttribute(eventShortMessageField, eventShortMessageFieldName, "value");
		
		boolean actualResponseTypeNoneRadioButton = reportLoggerMethods.isSelected(responseTypeNoneRadioButton, responseTypeNoneRadioButtonName);
		boolean actualResponseTypeConfirmationRadioButton = reportLoggerMethods.isSelected(responseTypeConfirmationRadioButton, responseTypeConfirmationRadioButtonName);
		boolean actualResponseTypeYesNoQuestionRadioButton = reportLoggerMethods.isSelected(responseTypeYesNoQuestionRadioButton, responseTypeYesNoQuestionRadioButtonName);
		String actualResponseTypeYesNoQuestion = "";
		boolean noRecipients = false;
		
		//Get the Yes/No Question
		if (reportLoggerMethods.isDisplayed(responseTypeYesNoQuestionTextarea, responseTypeYesNoQuestionTextareaName)) {
			actualResponseTypeYesNoQuestion = reportLoggerMethods.getAttribute(responseTypeYesNoQuestionTextarea, responseTypeYesNoQuestionTextareaName, "value");
		}
		
		//Check if the 'No Recipients' Message Exists
		if (reportLoggerMethods.isDisplayed(noRecipientsLabel, noRecipientsLabelName)) {
			noRecipients = true;
		}
		
		String expectedEventName = eventName;
		String expectedEventMessage = message;
		String expectedEventShortMessage = shortMessage;
		
		boolean expectedResponseTypeNoneRadioButton = false;
		boolean expectedResponseTypeConfirmationRadioButton = false;
		boolean expectedResponseTypeYesNoQuestionRadioButton = false;
		String expectedResponseTypeYesNoQuestion = responseTypeQuestion;
		
		//Set Expectations for Response Type
		if (responseType.equalsIgnoreCase("None")) {
			expectedResponseTypeNoneRadioButton = true;
			expectedResponseTypeConfirmationRadioButton = false;
			expectedResponseTypeYesNoQuestionRadioButton = false;
		} else if (responseType.equalsIgnoreCase("Confirmation")) {
			expectedResponseTypeNoneRadioButton = false;
			expectedResponseTypeConfirmationRadioButton = true;
			expectedResponseTypeYesNoQuestionRadioButton = false;
		} else if (responseType.equalsIgnoreCase("Yes/No Question")) {
			expectedResponseTypeNoneRadioButton = false;
			expectedResponseTypeConfirmationRadioButton = false;
			expectedResponseTypeYesNoQuestionRadioButton = true;
		}
		
		//Assert the 'Advanced Options' Default Values
		softAssert.assertEquals(actualEventName, expectedEventName);
		softAssert.assertEquals(actualEventMessage, expectedEventMessage);
		softAssert.assertEquals(actualEventShortMessage, expectedEventShortMessage);
		
		softAssert.assertEquals(actualResponseTypeNoneRadioButton, expectedResponseTypeNoneRadioButton);
		softAssert.assertEquals(actualResponseTypeConfirmationRadioButton, expectedResponseTypeConfirmationRadioButton);
		softAssert.assertEquals(actualResponseTypeYesNoQuestionRadioButton, expectedResponseTypeYesNoQuestionRadioButton);
		
		softAssert.assertEquals(actualResponseTypeYesNoQuestion, expectedResponseTypeYesNoQuestion);
		
		//Assert the Number of Recipients
		if (noRecipients) {
			softAssert.assertEquals("No Recipients attached to the ezNotify Event", "No Recipients attached to the ezNotify Event");
		} else {
			softAssert.assertEquals("One or more Recipients are attached to the ezNotify Event", "No Recipients attached to the ezNotify Event");
			
			//Add onto the Error Message
			failureMessage += "One or more Recipients are attached to the ezNotify Event instead of none, as expected. ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Event Name'
		if (!actualEventName.equalsIgnoreCase(expectedEventName)) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Event Name' (" + actualEventName + ") does not match Expectations (" + expectedEventName + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Event Message'
		if (!actualEventMessage.equalsIgnoreCase(expectedEventMessage)) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Event Message' (" + actualEventMessage + ") does not match Expectations (" + expectedEventMessage + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Event Short Message'
		if (!actualEventShortMessage.equalsIgnoreCase(expectedEventShortMessage)) {
			//Add onto the Error Message
			failureMessage += "The actual 'Event Short Message' (" + actualEventShortMessage + ") does not match Expectations (" + expectedEventShortMessage + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Response Type - None' Radio Button
		if (actualResponseTypeNoneRadioButton != expectedResponseTypeNoneRadioButton) {
			//Add onto the Error Message
			failureMessage += "The actual 'Response Type - None' Checkbox's checked status (" + actualResponseTypeNoneRadioButton + ") does not match Expectations (" + expectedResponseTypeNoneRadioButton + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Response Type - Confirmation' Radio Button
		if (actualResponseTypeConfirmationRadioButton != expectedResponseTypeConfirmationRadioButton) {
			//Add onto the Error Message
			failureMessage += "The actual 'Response Type - Confirmation' Checkbox's checked status (" + actualResponseTypeConfirmationRadioButton + ") does not match Expectations (" + expectedResponseTypeConfirmationRadioButton + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Response Type - Yes/No Question' Radio Button
		if (actualResponseTypeYesNoQuestionRadioButton != expectedResponseTypeYesNoQuestionRadioButton) {
			//Add onto the Error Message
			failureMessage += "The actual 'Response Type - Yes/No Question' Checkbox's checked status (" + actualResponseTypeYesNoQuestionRadioButton + ") does not match Expectations (" + expectedResponseTypeYesNoQuestionRadioButton + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Response Type - Yes/No Question'
		if (!actualResponseTypeYesNoQuestion.equalsIgnoreCase(expectedResponseTypeYesNoQuestion)) {
			//Add onto the Error Message
			failureMessage += "The actual 'Response Type - Yes/No Question' (" + actualResponseTypeYesNoQuestion + ") does not match Expectations (" + expectedResponseTypeYesNoQuestion + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Report the Verify Method Results to the Extent Report
		if (successfulMatch) {
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyCreateEZNotifyEventContents");
		}
	}
	
	public void createFailedEZNotifyEvent(SoftAssert softAssert, String eventName, String message, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String expectedErrorMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating an ezNotify Event");
		
		//Enter the ezNotify Event info
		enterEZNotifyEventInfo(eventName, message, shortMessage, responseType, responseTypeQuestion, selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups, "");
		
		//Create the New Event
		reportLoggerMethods.click(activateNewEventButton, activateNewEventButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Confirm the Creation of the New Event
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Get the Error Message
		String actualErrorMessage = reportLoggerMethods.getAlertText();
		
		//Assert the Error Message
		softAssert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
		//Accept the 'Created Event' Message
		reportLoggerMethods.acceptAlert();
		
		//Check the Error Message
		if (actualErrorMessage.equalsIgnoreCase(expectedErrorMessage)) {
			//Report the Verify Method Results to the Extent Report
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Error Message appeared and matched Expectations");
		} else {
			//Report the Verify Method Results to the Extent Report
			reportLoggerMethods.reportFailedCheckpoint("Failure: The actual Error Message (" + actualErrorMessage + ") does not match Expectations (" + expectedErrorMessage  + ")", "createFailedEZNotifyEvent");
		}
		
		//Verify the ezNotify Event's Values
		verifyCreateEZNotifyEventContents(softAssert, eventName, message, shortMessage, responseType, responseTypeQuestion);
		
		//Select the specified 'Recipients'
		selectRecipients(recipients);
		
		//Create the New Event
		reportLoggerMethods.click(activateNewEventButton, activateNewEventButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Confirm the Creation of the New Event
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Accept the 'Created Event' Message
		reportLoggerMethods.acceptAlert();
	}
	
	public void createEZNotifyTemplate(String eventName, String message, String shortMessage, String responseType, String responseTypeQuestion, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String addAdditionalOptions, String eventDuration, String deviceEscalation, String recipientDeviceList, String procedureDeviceList, String viewProcedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating an ezNotify Event");
		
		//Enter the ezNotify Event info
		enterEZNotifyEventInfo(eventName, message, shortMessage, responseType, responseTypeQuestion, selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups, recipients);
		
		//Enter the ezNotify Event's Additional Options info, if desired
		if (addAdditionalOptions.equalsIgnoreCase("y") || addAdditionalOptions.equalsIgnoreCase("yes")) {
			enterEZNotifyEventAdditionalOptionsInfo(eventDuration, deviceEscalation, recipientDeviceList, procedureDeviceList, viewProcedureDeviceList);
		}
		
		//Create the New Event
		reportLoggerMethods.click(createNewTemplateButton, createNewTemplateButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Confirm the Creation of the New Event
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Accept the 'Created Event' Message
		reportLoggerMethods.acceptAlert();
	}
	
	// ~~~ Cancel out of ezNotify Pages ~~~ //
	
	public void cancelAdvancedOptionsPage(String eventDuration, String deviceEscalation, String recipientsDeviceList, String procedureDeviceLiist) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating the ezNotify Event");
		
		//Select 'Create New Event' to open the event creation page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
		
		//Navigate to the Additional Options Page
		reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
		
		//Enter a value for the 'Event Duration (Minutes)'
		reportLoggerMethods.clear(eventDurationField, eventDurationFieldName);
		reportLoggerMethods.sendKeys(eventDurationField, eventDurationFieldName, eventDuration);
		
		//Select Device Escalation
		if (deviceEscalation.equalsIgnoreCase("Use Device Escalation")) {
			reportLoggerMethods.click(deviceEscalationRadioButton, deviceEscalationRadioButtonName);
		} else if (deviceEscalation.equalsIgnoreCase("Notify all devices simultaneously")) {
			reportLoggerMethods.click(notifyAllDevicesSimultaneouslyRadioButton, notifyAllDevicesSimultaneouslyRadioButtonName);
		}
		
		//Use Recipient's Device List (if Available & Desired)
		if (recipientsDeviceList.equalsIgnoreCase("y") || recipientsDeviceList.equalsIgnoreCase("yes")) {
			reportLoggerMethods.click(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName);
		}
		
		//Select Procedure Device List
		reportLoggerMethods.selectDropDown(procedureDeviceDropDown, procedureDeviceDropDownName, procedureDeviceLiist);
		
		//Cancel out of the 'Advanced Options'
		reportLoggerMethods.click(cancelAdditionalOptionsButton, cancelAdditionalOptionsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Confirm the Creation of the New Event
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
	}
	
	public void verifyCancelledNewEventPage(SoftAssert softAssert, String eventDuration, String deviceEscalation, String recipientDeviceList, String procedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying that the ezNotify Event Page was Cancelled & the site is displaying the Base Page (ezNotify Pages not displaying their respective WebElements)");
		
		//Verify if the correct page is displayed
		if (reportLoggerMethods.isDisplayed(baseEZNotifyPage, baseEZNotifyPageName)) {
			//Retrieve the amount of WebElements existing in the right section of the page (if zero WebElements, then no ezNotify Event Page is up, which is desired)
			int webElementCount = reportLoggerMethods.getSize(baseEZNotifyPageChildWebElementsList, baseEZNotifyPageChildWebElementsListName);
			
			//Assert the status of the ezNotify Event Page
			softAssert.assertEquals(webElementCount, 0);
			
			//Check the status of the ezNotify Event Page
			if (webElementCount == 0) {
				//Assert the status of the ezNotify Event Page
				softAssert.assertEquals("ezNotify Page was exited", "ezNotify Page was exited");
				
				//Output an info status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Cancel' Button exited the ezNotify Event Page and went back to the base webpage");
			} else {
				//Assert the status of the ezNotify Event Page
				softAssert.assertEquals("ezNotify Page is currently up", "ezNotify Page was exited");
				
				//Output an info status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Cancel' Button did not exit the ezNotify Event Page", "verifyCancelledNewEventPage");
			}
		} else {
			//Assert the status of the ezNotify Event Page
			softAssert.assertEquals("The page for ezNotify Event info was not found", "The page for ezNotify Event Info should be found, but without ezNotify Event WebElements");
			
			//Output an info status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: Unable to find the page for ezNotify Event info", "verifyCancelledNewEventPage");
		}
		
		//Select 'Create New Event' to open the event creation page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
		
		//
		verifyAdvancedOptionsDefaultValues(softAssert, eventDuration, deviceEscalation, recipientDeviceList, procedureDeviceList);
	}
	
	public void verifyAdvancedOptionsDefaultValues(SoftAssert softAssert, String eventDuration, String deviceEscalation, String recipientDeviceList, String procedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the 'Advanced Options' Default Values");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(15000);
		
		//Navigate to the Additional Options Page
		reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
		
		//Initialize Variable(s)
		String successMessage = "Success: The Advanced Options' Default Values match Expectations";
		String failureMessage = "Failure ";
		boolean successfulMatch = true;
		
		String actualEventDuration = reportLoggerMethods.getAttribute(eventDurationField, eventDurationFieldName, "value");
		boolean actualDeviceEscalationSelection = reportLoggerMethods.isSelected(deviceEscalationRadioButton, deviceEscalationRadioButtonName);
		boolean actualNotifyAllSelection = reportLoggerMethods.isSelected(notifyAllDevicesSimultaneouslyRadioButton, notifyAllDevicesSimultaneouslyRadioButtonName);
		boolean actualUseRecipientsSelection = reportLoggerMethods.isSelected(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName);
		String actualProcedureDeviceSelection = reportLoggerMethods.getText(procedureDeviceSelectedOptionLabel, procedureDeviceSelectedOptionLabelName);
		
		String expectedEventDuration = eventDuration;
		boolean expectedDeviceEscalationSelection = false;
		boolean expectedNotifyAllSelection = false;
		boolean expectedUseRecipientsSelection = false;
		String expectedProcedureDeviceSelection = procedureDeviceList;
		
		//Set the value of the expected 'Device Escalation'
		if (deviceEscalation.equalsIgnoreCase("Use Device Escalation")) {
			expectedDeviceEscalationSelection = true;
			expectedNotifyAllSelection = false;
		} else if (deviceEscalation.equalsIgnoreCase("Notify all devices simultaneously")) {
			expectedDeviceEscalationSelection = false;
			expectedNotifyAllSelection = true;
		} else {
			//Add onto thee 'Error Message'
			failureMessage += "The Device Escalation (Expectation specified in the Excel File) should be set to 'Use Device Escalation' or 'Notify all devices simultaneously' instead of '" + deviceEscalation+ "'. ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Set the value of the 'Recipients Selection'
		if (recipientDeviceList.equalsIgnoreCase("y") || recipientDeviceList.equalsIgnoreCase("yes")) {
			expectedUseRecipientsSelection = true;
		} else {
			expectedUseRecipientsSelection = false;
		}
		
		//Assert the 'Advanced Options' Default Values
		softAssert.assertEquals(actualEventDuration, expectedEventDuration);
		softAssert.assertEquals(actualDeviceEscalationSelection, expectedDeviceEscalationSelection);
		softAssert.assertEquals(actualNotifyAllSelection, expectedNotifyAllSelection);
		softAssert.assertEquals(actualUseRecipientsSelection, expectedUseRecipientsSelection);
		softAssert.assertEquals(actualProcedureDeviceSelection, expectedProcedureDeviceSelection);
		
		//Check the 'Event Duration' Value
		if (!actualEventDuration.equalsIgnoreCase(expectedEventDuration)) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Event Duration' (" + actualEventDuration + ") does not match Expectations (" + expectedEventDuration + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Device Escalation' Selection
		if (actualDeviceEscalationSelection != expectedDeviceEscalationSelection) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Use Device Escalation' Checkbox's checked status is (" + actualDeviceEscalationSelection + "), but expected it to be (" + expectedDeviceEscalationSelection + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Notify all devices simultaneously' Selection
		if (actualNotifyAllSelection != expectedNotifyAllSelection) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Notify all devices simultaneously' Checkbox's checked status is (" + actualNotifyAllSelection + "), but expected it to be (" + expectedNotifyAllSelection + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Use recipient's device list (if available)' Selection
		if (actualUseRecipientsSelection != expectedUseRecipientsSelection) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Use recipient's device list (if available)' Checkbox's checked status is (" + actualUseRecipientsSelection + "), but expected it to be (" + expectedUseRecipientsSelection + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check the 'Procedure Device List' Selection
		if (!actualProcedureDeviceSelection.equalsIgnoreCase(expectedProcedureDeviceSelection)) {
			//Add onto the 'Error Message'
			failureMessage += "The actual 'Procedure Device List' (" + actualProcedureDeviceSelection + ") does not match Expectations (" + expectedProcedureDeviceSelection + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Report the Verify Method Results to the Extent Report
		if (successfulMatch) {
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyAdvancedOptionsDefaultValues");
		}
	}
	
	// ~~~ Recipient Info ~~~ //
	
	public void verifyDevices(SoftAssert softAssert, String deviceListSelection, String order, String deviceType, String phoneType, String pageRoute) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Bringing up the 'Procedure Devices List'");
		
		//Select the desired Procedure Device List
		reportLoggerMethods.selectDropDown(procedureDeviceDropDown, procedureDeviceDropDownName, deviceListSelection);
		
		//Bring up the 'View Devices' Pop-up
		reportLoggerMethods.click(viewProcedureDevicesButton, viewProcedureDevicesButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Verify the Procedure Device List's List of Info
		verifyDeviceListInfo(softAssert, order, deviceType, phoneType, pageRoute);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Close the 'View Devices' Pop-up
		reportLoggerMethods.click(closeViewProcedureDevicesPopupButton, closeViewProcedureDevicesPopupButtonName);
	}
	
	public void verifyDeviceListInfo(SoftAssert softAssert, String order, String deviceType, String phoneType, String pageRoute) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the Procedure Device List's List of Info");
		
		//Initialize Variable(s)
		String successMessage = "Success: The 'Procedure Device List' Info match Expectations";
		String failureMessage = "Failure ";
		boolean successfulMatch = true;
		
		String actualOrder;
		String actualDeviceType;
		String actualPhoneType;
		String actualPageRoute;
		int actualRowCount = reportLoggerMethods.getSize(deviceListOrderList, deviceListOrderListName);
		
		List<String> expectedOrderList = Arrays.asList(order.split("/"));
		List<String> expectedDeviceTypeList = Arrays.asList(deviceType.split("/"));
		List<String> expectedPhoneTypeList = Arrays.asList(phoneType.split("/"));
		List<String> expectedPageRouteList = Arrays.asList(pageRoute.split("/"));
		
		String expectedOrder;
		String expectedDeviceType;
		String expectedPhoneType;
		String expectedPageRoute;
		
		//Assert the Amount of rows expected
		softAssert.assertEquals("Procedure Device List's Info Row Count of " + actualRowCount, "Procedure Device List's Info Row Count of " + expectedOrderList.size());
		softAssert.assertEquals("Procedure Device List's Info Row Count of " + actualRowCount, "Procedure Device List's Info Row Count of " + expectedDeviceTypeList.size());
		softAssert.assertEquals("Procedure Device List's Info Row Count of " + actualRowCount, "Procedure Device List's Info Row Count of " + expectedPhoneTypeList.size());
		softAssert.assertEquals("Procedure Device List's Info Row Count of " + actualRowCount, "Procedure Device List's Info Row Count of " + expectedPageRouteList.size());
		
		//Check if the amount of 'Order' rows match Expectations
		if (actualRowCount != expectedOrderList.size()) {
			//Add onto the 'Error Message'
			failureMessage += "The actual Procedure Device List's 'Order' Info Row Count (" + actualRowCount + ") does not match Expectations (" + expectedOrderList.size() + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check if the amount of 'Device Type' rows match Expectations
		if (actualRowCount != expectedDeviceTypeList.size()) {
			//Add onto the 'Error Message'
			failureMessage += "The actual Procedure Device List's 'Device Type' Info Row Count (" + actualRowCount + ") does not match Expectations (" + expectedDeviceTypeList.size() + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check if the amount of 'Phone Type' rows match Expectations
		if (actualRowCount != expectedPhoneTypeList.size()) {
			//Add onto the 'Error Message'
			failureMessage += "The actual Procedure Device List's 'Phone Type' Info Row Count (" + actualRowCount + ") does not match Expectations (" + expectedPhoneTypeList.size() + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Check if the amount of 'Page Route' rows match Expectations
		if (actualRowCount != expectedPageRouteList.size()) {
			//Add onto the 'Error Message'
			failureMessage += "The actual Procedure Device List's 'Page Route' Info Row Count (" + actualRowCount + ") does not match Expectations (" + expectedPageRouteList.size() + "). ";
			
			//Indicate that an error occurred
			successfulMatch = false;
		}
		
		//Loop through each row of the 'Device List' info
		for (int i = 0; i < actualRowCount; i++) {
			//Initialize Variable(s)
			actualOrder = reportLoggerMethods.getText(deviceListOrderList.get(i), deviceListOrderListName(i));
			actualDeviceType = reportLoggerMethods.getText(deviceListDeviceTypeList.get(i), deviceListDeviceTypeListName(i));
			actualPhoneType = reportLoggerMethods.getText(deviceListPhoneTypeList.get(i), deviceListPhoneTypeListName(i));
			actualPageRoute = reportLoggerMethods.getText(deviceListPageRouteList.get(i), deviceListPageRouteListName(i));
			
			expectedOrder = expectedOrderList.get(i).trim();
			expectedDeviceType = expectedDeviceTypeList.get(i).trim();
			expectedPhoneType = expectedPhoneTypeList.get(i).trim();
			expectedPageRoute = expectedPageRouteList.get(i).trim();
			
			//Assert the Amount of rows expected
			softAssert.assertEquals(actualOrder, expectedOrder);
			softAssert.assertEquals(actualDeviceType, expectedDeviceType);
			softAssert.assertEquals(actualPhoneType, expectedPhoneType);
			softAssert.assertEquals(actualPageRoute, expectedPageRoute);
			
			//Check the Procedure Device List's 'Order' Label
			if (!actualOrder.equalsIgnoreCase(expectedOrder)) {
				//Add onto the 'Error Message'
				failureMessage += "The actual Procedure Device List's #" + i + " 'Order' Label (" + actualOrder + ") does not match Expectations (" + expectedOrder + "). ";
				
				//Indicate that an error occurred
				successfulMatch = false;
			}
			
			//Check the Procedure Device List's 'Device Type' Label
			if (!actualDeviceType.equalsIgnoreCase(expectedDeviceType)) {
				//Add onto the 'Error Message'
				failureMessage += "The actual Procedure Device List's #" + i + " 'Device Type' Label (" + actualDeviceType + ") does not match Expectations (" + expectedDeviceType + "). ";
				
				//Indicate that an error occurred
				successfulMatch = false;
			}
			
			//Check the Procedure Device List's 'Phone Type' Label
			if (!actualPhoneType.equalsIgnoreCase(expectedPhoneType)) {
				//Add onto the 'Error Message'
				failureMessage += "The actual Procedure Device List's #" + i + " 'Phone Type' Label (" + actualPhoneType + ") does not match Expectations (" + expectedPhoneType + "). ";
				
				//Indicate that an error occurred
				successfulMatch = false;
			}
			
			//Check the Procedure Device List's 'Page Route' Label
			if (!actualPageRoute.equalsIgnoreCase(expectedPageRoute)) {
				//Add onto the 'Error Message'
				failureMessage += "The actual Procedure Device List's #" + i + " 'Page Route' Label (" + actualPageRoute + ") does not match Expectations (" + expectedPageRoute + "). ";
				
				//Indicate that an error occurred
				successfulMatch = false;
			}
		}
		
		//Report the Verify Method Results to the Extent Report
		if (successfulMatch) {
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyDeviceListInfo");
		}
	}
	
	// ~~~ Recipient Info ~~~ //
	
	public void viewRecipientDirectoryInfo(SoftAssert softAssert, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String recipients, String expectedDirectoryTitle, String expectedDirectoryDepartment) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking the Directory Info for the Recipient(s)");
		
		//Select 'Create New Event' to open the event creation page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
		
		//Check the specified 'Select Recipients' checkboxes
		checkSelectRecipientsCheckboxes(selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups);
		
		//Initialize Variable(s)
		String[] recipientsList = recipients.split("/");
		
		//Add all specified recipients
		for (int i = 0; i < recipientsList.length; i++) {
			//Enter the specified recipient
			reportLoggerMethods.clear(recipientsSearchField, recipientsSearchFieldName);
			reportLoggerMethods.sendKeys(recipientsSearchField, recipientsSearchFieldName, recipientsList[i].trim());
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(3000);
			
			//Select the 'View Directory' button for the first result of the recipient search list
			if (reportLoggerMethods.isDisplayed(firstRecipientSearchResultViewDirectoryButton, firstRecipientSearchResultViewDirectoryButtonName)) {
				reportLoggerMethods.click(firstRecipientSearchResultViewDirectoryButton, firstRecipientSearchResultViewDirectoryButtonName);
			} else if (reportLoggerMethods.isDisplayed(firstOnCallGroupsSearchResultViewDirectoryButton, firstOnCallGroupsSearchResultViewDirectoryButtonName)) {
				reportLoggerMethods.click(firstOnCallGroupsSearchResultViewDirectoryButton, firstOnCallGroupsSearchResultViewDirectoryButtonName);
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'View Directory' Button for the Recipient '" + recipientsList[i].trim() + "' was not located", "viewRecipientDirectoryInfo");
				return;
			}
			
			//Output an info status to the Extent Report & System
			reportLoggerMethods.reportInfo("Verifying the 'Directory Info' for Searched Recipient(s)");
			
			//Verify the 'Directory Info' for the Recipient(s)
			verifyRecipientDirectoryInfo(softAssert, expectedDirectoryTitle, expectedDirectoryDepartment);
			
			//Exit the 'View Directory' pop-up
			if (reportLoggerMethods.isDisplayed(exitRecipientViewDirectoryPopup, exitRecipientViewDirectoryPopupName)) {
				reportLoggerMethods.click(exitRecipientViewDirectoryPopup, exitRecipientViewDirectoryPopupName);
			} else if (reportLoggerMethods.isDisplayed(exitOncallGroupViewDirectoryPopup, exitOncallGroupViewDirectoryPopupName)) {
				reportLoggerMethods.click(exitOncallGroupViewDirectoryPopup, exitOncallGroupViewDirectoryPopupName);
			} else {
				//Assert the Exit Button of the pop-up/modal page
				softAssert.assertEquals("The 'View Directory' Button did not open a New Pop-up/Modal Window", "The 'View Directory' Button opens a New Pop-up/Modal Window");
				
				//Output the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'View Directory' is not a Pop-up/Modal Page", "viewRecipientDirectoryInfo");
			}
			
			//Select the first result of the recipient search list
			reportLoggerMethods.click(firstRecipientSearchResult, firstRecipientSearchResultName);
			
			//Select the 'View Directory' button for the first active recipient
			reportLoggerMethods.click(firstRecipientViewDirectoryButton, firstRecipientViewDirectoryButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(2000);
			
			//Verify the 'Directory Info' for the Recipient(s)
			reportLoggerMethods.reportInfo("Verifying the 'Directory Info' for Added Recipient(s)");
			
			//Verify the 'Directory Info' for the Recipient(s)
			verifyRecipientDirectoryInfo(softAssert, expectedDirectoryTitle, expectedDirectoryDepartment);
			
			//Exit the 'View Directory' pop-up
			if (reportLoggerMethods.isDisplayed(exitRecipientViewDirectoryPopup, exitRecipientViewDirectoryPopupName)) {
				reportLoggerMethods.click(exitRecipientViewDirectoryPopup, exitRecipientViewDirectoryPopupName);
			} else if (reportLoggerMethods.isDisplayed(exitOncallGroupViewDirectoryPopup, exitOncallGroupViewDirectoryPopupName)) {
				reportLoggerMethods.click(exitOncallGroupViewDirectoryPopup, exitOncallGroupViewDirectoryPopupName);
			} else {
				//Assert the Exit Button of the pop-up/modal page
				softAssert.assertEquals("The 'View Directory' Button did not open a New Pop-up/Modal Window", "The 'View Directory' Button opens a New Pop-up/Modal Window");
				
				//Output the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'View Directory' is not a Pop-up/Modal Page", "viewRecipientDirectoryInfo");
			}
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Delete All Active Recipients
			reportLoggerMethods.click(removeAllRecipientsButton, removeAllRecipientsButtonName);
		}
	}
	
	public void verifyRecipientDirectoryInfo(SoftAssert softAssert, String expectedDirectoryTitle, String expectedDirectoryDepartment) {
		//Initialize Variable(s)
		String[] directoryTitleList = expectedDirectoryTitle.split("/");
		String[] directoryDepartmentList = expectedDirectoryDepartment.split("/");
		boolean directoryInfoMatch = true;
		
		//Assert the Number of 'Department Title/Department' Rows
		softAssert.assertEquals(reportLoggerMethods.getSize(directoryTitleLabel, directoryTitleLabelName), directoryTitleList.length);
		softAssert.assertEquals(reportLoggerMethods.getSize(directoryDepartmentLabel, directoryDepartmentLabelName), directoryDepartmentList.length);
		
		//Check the Number of 'Department Title/Department' Rows
		if ((reportLoggerMethods.getSize(directoryTitleLabel, directoryTitleLabelName) != directoryTitleList.length) || (reportLoggerMethods.getSize(directoryDepartmentLabel, directoryDepartmentLabelName) != directoryDepartmentList.length)) {
			//Report the Failed Status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The number of 'Directory Title/Department' Rows (" + reportLoggerMethods.getSize(directoryTitleLabel, directoryTitleLabelName) + ") do not match expectations (" + directoryTitleList.length + ")", "verifyRecipientDirectoryInfo");
			
			//Indicate that the Directory Info does not match Expectations
			directoryInfoMatch = false;
		}
		
		//Check if Recipient(s) match Expectations
		for (int i = 0; i < directoryTitleList.length; i++) {
			//Initialize Variable(s)
			expectedDirectoryTitle = directoryTitleList[i].trim().toLowerCase();
			expectedDirectoryDepartment = directoryDepartmentList[i].trim().toLowerCase();
			
			String actualDirectoryTitle = reportLoggerMethods.getText(directoryTitleRow(i), directoryTitleRowName(i)).toLowerCase();
			String actualDirectoryDepartment = reportLoggerMethods.getText(directoryDepartmentRow(i), directoryDepartmentRowName(i)).toLowerCase();
			
			//Assert the #i Directory Info
			softAssert.assertEquals(actualDirectoryTitle, expectedDirectoryTitle);
			softAssert.assertEquals(actualDirectoryDepartment, expectedDirectoryDepartment);
			
			//Check if the #i Directory Title Matches Expectations
			if (!actualDirectoryTitle.equalsIgnoreCase(expectedDirectoryTitle)) {
				//Report the Failed Status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The #" + i + " Directory Title (" + actualDirectoryTitle + ") does not match expectations (" + expectedDirectoryTitle + ")", "verifyRecipientDirectoryInfo");
				
				//Indicate that the Directory Info does not match Expectations
				directoryInfoMatch = false;
			}
			
			//Check if the #i Directory Department Matches Expectations
			if (!actualDirectoryDepartment.equalsIgnoreCase(expectedDirectoryDepartment)) {
				//Report the Failed Status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The #" + i + " Directory Department (" + actualDirectoryDepartment + ") does not match expectations (" + expectedDirectoryDepartment + ")", "verifyRecipientDirectoryInfo");
				
				//Indicate that the Directory Info does not match Expectations
				directoryInfoMatch = false;
			}
		}
		
		//Check if all the Directory Info matched Expectations
		if (directoryInfoMatch) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: All the (Individual Recipient) Directory Info matched Expectations");
		}
	}
	
	// ~~~ Group Info ~~~ //
	
	public void viewGroupDirectoryInfo(SoftAssert softAssert, String selectRepicientsIndividuals, String selectRepicientsMessageGroups, String selectRepicientsOncallGroups, String groups, String oncallGroupRecipientNames) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the Directory Info for the Group's Recipient Names");
		
		//Select 'Create New Event' to open the event creation page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
		
		//Check the specified 'Select Recipients' checkboxes
		checkSelectRecipientsCheckboxes(selectRepicientsIndividuals, selectRepicientsMessageGroups, selectRepicientsOncallGroups);
		
		//Initialize Variable(s)
		String[] groupsList = groups.split("/");
		
		//Add all specified recipients
		for (int i = 0; i < groupsList.length; i++) {
			//Enter the specified recipient
			reportLoggerMethods.clear(recipientsSearchField, recipientsSearchFieldName);
			reportLoggerMethods.sendKeys(recipientsSearchField, recipientsSearchFieldName, groupsList[i].trim());
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Select the 'View Directory' button for the first result of the OnCall Group search list
			reportLoggerMethods.click(firstOnCallGroupsSearchResultViewDirectoryButton, firstOnCallGroupsSearchResultViewDirectoryButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(4000);
			
			//Check the Group's 'List of Recipient Names'
			if (reportLoggerMethods.isAlertDisplayedAndAccept()) {
				//Assert 'No Recipient Names'
				softAssert.assertEquals("", oncallGroupRecipientNames.trim());
				
				//Check for 'No Recipient Names'
				if (oncallGroupRecipientNames.trim().equalsIgnoreCase("")) {
					reportLoggerMethods.reportSuccessfulCheckpoint("Success: There are no Recipient Names listed, as expected");
				} else {
					reportLoggerMethods.reportFailedCheckpoint("Failure: Expected the Recipient Names of '" + oncallGroupRecipientNames + "', but found 'No Recipient Names'", "viewGroupDirectoryInfo");
				}
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Select the first result of the recipient search list
				reportLoggerMethods.click(firstRecipientSearchResult, firstRecipientSearchResultName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Check if the OnCall Group can be added to the ezNotify Event
				if (reportLoggerMethods.isAlertDisplayedAndAccept()) {
					//Check if the OnCall Group not being added matches Expectations
					if (oncallGroupRecipientNames.trim().equalsIgnoreCase("")) {
						//Assert the status of the OnCall Group being able to be added to the Event
						softAssert.assertEquals("OnCall Group unable to added due to no containing no recipients", "OnCall Group unable to added due to no containing no recipients");
						
						//Report to the Extent Report & System
						reportLoggerMethods.reportSuccessfulCheckpoint("Success: Unable to add the OnCall Group due to no recipients, as expected");
					} else {
						//Assert the status of the OnCall Group being able to be added to the Event
						softAssert.assertEquals("OnCall Group unable to added due to no containing no recipients", "OnCall Group should be able to be added since the expectations of it having recipients");
						
						//Report to the Extent Report & System
						reportLoggerMethods.reportFailedCheckpoint("Failure: The OnCall Group should be able to be added, but was not added (due to expecting the OnCall Group to have Recipient Name(s), but not having any Recipient Name(s))", "viewGroupDirectoryInfo");
					}
				} else {
					//Check if the Added OnCall Group matches Expectations
					if (oncallGroupRecipientNames.trim().equalsIgnoreCase("")) {
						//Assert the status of the OnCall Group being able to be added to the Event
						softAssert.assertEquals("OnCall Group unable to added due to no containing no recipients", "OnCall Group was added despite supposedly having no recipients");
						
						//Report to the Extent Report & System
						reportLoggerMethods.reportFailedCheckpoint("Failure: The OnCall Group was added despite supposedly having no recipients (should not be able to add without recipients)", "viewGroupDirectoryInfo");
					} else {
						//Assert the status of the OnCall Group being able to be added to the Event
						softAssert.assertEquals("OnCall Group was added to the ezNotify Event", "OnCall Group was added to the ezNotify Event");
						
						//Report to the Extent Report & System
						reportLoggerMethods.reportSuccessfulCheckpoint("Success: The OnCall Group was added to the ezNotify Event");
					}
					
					//Delete All Active Recipients
					reportLoggerMethods.click(removeAllRecipientsButton, removeAllRecipientsButtonName);
				}
				
				//Exit the Method
				return;
			} else {
				//Verify the 'Directory Info' for the Recipient(s)
				reportLoggerMethods.reportInfo("Verifying the List of Member Names for the Searched OnCall Group of (" + groupsList[i].trim() + ")");
				
				//Verify the 'Directory Info' for the OnCall Group(s)
				verifyGroupDirectoryInfo(softAssert, oncallGroupRecipientNames);
				
				//Exit the 'View Directory' pop-up
				reportLoggerMethods.click(exitOncallGroupViewDirectoryPopup, exitOncallGroupViewDirectoryPopupName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Select the first result of the recipient search list
				reportLoggerMethods.click(firstRecipientSearchResult, firstRecipientSearchResultName);
				
				//Select the 'View Directory' button for the first active recipient
				reportLoggerMethods.click(firstOnCallGroupViewDirectoryButton, firstOnCallGroupViewDirectoryButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(2000);
				
				//Verify the 'Directory Info' for the Recipient(s)
				reportLoggerMethods.reportInfo("Verifying the List of Member Names for the Added OnCall Group of (" + groupsList[i].trim() + ")");
				
				//Verify the 'Directory Info' for the Recipient(s)
				verifyGroupDirectoryInfo(softAssert, oncallGroupRecipientNames);
				
				//Exit the 'View Directory' pop-up
				reportLoggerMethods.click(exitOncallGroupViewDirectoryPopup, exitOncallGroupViewDirectoryPopupName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Delete All Active Recipients
				reportLoggerMethods.click(removeAllRecipientsButton, removeAllRecipientsButtonName);
			}
		}
	}
	
	public void verifyGroupDirectoryInfo(SoftAssert softAssert, String expectedNames) {
		//Check if Expecting no Recipient Names
		if (expectedNames.trim().equalsIgnoreCase("")) {
			
			//Assert the status of existing Recipient Names
			softAssert.assertEquals("Recipient Names exist", "Expected No Recipient Names");
			
			//Report to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The OnCall Group has Recipient Names, despite expecting no Recipient Names", "verifyGroupDirectoryInfo");
			
			//Exit the Method
			return;
		}
		
		//Initialize Variable(s)
		String[] namesList = expectedNames.split("/");
		genMethods.waitForMilliseconds(3000);
		String[] groupDirectoryMemberCountLabelArray = reportLoggerMethods.getText(groupDirectoryMemberCountLabel, groupDirectoryMemberCountLabelName).split(" ");
		int recipientNameSize = Integer.parseInt(groupDirectoryMemberCountLabelArray[groupDirectoryMemberCountLabelArray.length-2]);
		boolean directoryInfoMatch = true;
		
		//Assert the Number of 'Recipient Names'
		softAssert.assertEquals(recipientNameSize, namesList.length);
		
		//Check the Number of 'Department Title/Department' Rows
		if (recipientNameSize != namesList.length) {
			//Report the Failed Status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The number of 'Directory Title/Department' Rows (" + reportLoggerMethods.getSize(directoryTitleLabel, directoryTitleLabelName) + ") do not match expectations (" + namesList.length + ")", "verifyGroupDirectoryInfo");
			
			//Indicate that the Directory Info does not match Expectations
			directoryInfoMatch = false;
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Open the 'Recipient Names List' Page
		reportLoggerMethods.click(groupDirectoryViewMembersLink, groupDirectoryViewMembersLinkName);
		
		//groupDirectoryMembersList
		for (int j = 0; j < reportLoggerMethods.getSize(groupDirectoryMembersList, groupDirectoryMembersListName); j++) {
			//Initialize Variable(s)
			String actualName = reportLoggerMethods.getText(groupDirectoryMembersList.get(j), groupDirectoryMembersListName).trim();
			String expectedName = namesList[j].trim();
			
			//Assert the #j Group Directory Listed Name/Member
			softAssert.assertEquals(actualName, expectedName);
			
			//Check the #j Group Directory Listed Name/Member
			if (!actualName.equals(expectedName)) {
				//Report the Failed Status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: OnCall Group's #" + (j+1) + "'s Member Name (" + actualName + ") does not match Expectations (" + expectedName + ")", "verifyGroupDirectoryInfo");
				
				//Indicate that the Directory Info does not match Expectations
				directoryInfoMatch = false;
			}
		}
		
		//Check if all the Directory Info matched Expectations
		if (directoryInfoMatch) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: All the (OnCall Group Recipient) Directory Info matched Expectations");
		}
	}
	
	// ~~~ Add/Remove Recipients ~~~ //
	
	public void verifyRemovingEachRecipient(SoftAssert softAssert, String recipients) {
		//Initialize Variable(s)
		String[] recipientsList = recipients.split("/");
		
		//Remove Recipients from the 'Added Recipients' List
		verifyRemovingEachRecipient(softAssert, recipientsList.length);
	}
	
	public void verifyRemovingEachRecipient(SoftAssert softAssert, int recipientsCount) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Removing Recipients one by one from the 'Added Recipients' List");
		
		//Initialize Variable(s)
		int addedRecipientsList = reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName);
		boolean removedAllRecipients = true;
		
		//Assert the Initial Amount of Recipients in the 'Added Recipients' List
		softAssert.assertEquals(addedRecipientsList, recipientsCount);
		
		//Check if the Initial Amount of Recipients in the 'Added Recipients' List match Expectations
		if (addedRecipientsList != recipientsCount) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: Expected " + recipientsCount + " recipients in the list, but found " + addedRecipientsList + " recipients", "verifyRemovingEachRecipient");
			removedAllRecipients = false;
		}
		
		//Loop through all 'Added Recipients'
		for (int i = 0; i < addedRecipientsList; i++) {
			//Assert the Amount of Recipients in the 'Added Recipients' List
			softAssert.assertEquals(reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName), (addedRecipientsList - i));
			
			//Check if the Amount of Recipients in the 'Added Recipients' List match Expectations
			if (reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName) != (addedRecipientsList - i)) {
				reportLoggerMethods.reportFailedCheckpoint("Failure: Expected " + (addedRecipientsList - i) + " recipients in the list, but found " + reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName) + " recipients", "verifyRemovingEachRecipient");
				removedAllRecipients = false;
			}
			
			//Click the 'Remove Recipient' #i Button
			reportLoggerMethods.click(removeRecipientButtonList.get(0), removeRecipientButtonListName(0));
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		}
		
		//Check if All Recipients were Removed
		if (removedAllRecipients && reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName) == 0) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: Removed All Recipients");
		}
	}
	
	public void verifyRemovingAllRecipients(SoftAssert softAssert, String recipients) {
		//Initialize Variable(s)
		String[] recipientsList = recipients.split("/");
		
		//Remove Recipients from the 'Added Recipients' List
		verifyRemovingAllRecipients(softAssert, recipientsList.length);
	}
	
	public void verifyRemovingAllRecipients(SoftAssert softAssert, int recipientsCount) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Removing All Recipients at once from the 'Added Recipients' List");
		
		//Initialize Variable(s)
		int addedRecipientsList = reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName);
		
		//Assert the Initial Amount of Recipients in the 'Added Recipients' List
		softAssert.assertEquals(addedRecipientsList, recipientsCount);
		
		//Check if the Initial Amount of Recipients in the 'Added Recipients' List match Expectations
		if (addedRecipientsList != recipientsCount) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: Expected " + recipientsCount + " recipients in the list, but found " + addedRecipientsList + " recipients", "verifyRemovingAllRecipients");
		}
		
		//Click the 'Remove All Recipients' Button
		reportLoggerMethods.click(removeAllRecipientsButton, removeAllRecipientsButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Assert the Amount of Recipients in the 'Added Recipients' List after clicking to Remove All Recipients
		softAssert.assertEquals(reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName), 0);
		
		//Check if the Initial Amount of Recipients in the 'Added Recipients' List match Expectations
		if (reportLoggerMethods.getSize(removeRecipientButtonList, removeRecipientButtonListName) != 0) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: Expected " + recipientsCount + " recipients in the list, but found " + addedRecipientsList + " recipients", "verifyRemovingAllRecipients");
		} else {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: Removed All Recipients");
		}
	}
	
}