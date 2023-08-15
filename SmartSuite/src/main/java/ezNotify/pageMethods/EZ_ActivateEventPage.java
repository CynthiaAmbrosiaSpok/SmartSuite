package ezNotify.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ezNotify.pageLocators.EZ_ActivateEventLocators;

public class EZ_ActivateEventPage extends EZ_ActivateEventLocators {
	
	//Initialize Variable(s)
	int mostRecentEventTemplateID = 0;
	String mostRecentEventTemplateName = "";
	
	//Constructor
	public EZ_ActivateEventPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void enterProcedureSection() {
		reportLoggerMethods.click(procedure1Button, procedure1ButtonName);
	}
	
	public void resetMostRecentEventTemplateInfo() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Reseting the 'Most Recent Event Template' Info");
		
		//Reset the 'Most Recent Event Template' Info
		mostRecentEventTemplateID = 0;
		mostRecentEventTemplateName = "";
	}
	
	public void openActivateEventPage() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Activate Existing Event' Page");
		
		//Click 'Activate Existing Event' to open the Event Template page
		reportLoggerMethods.click(activateEventButton, activateEventButtonName);
	}
	
	public void searchTemplates(String eventName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a Search for the Event Template -> " + eventName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Search for the Event Template
		reportLoggerMethods.clear(searchField, searchFieldName);
//		genMethods.waitForMilliseconds(1000);
		reportLoggerMethods.sendKeys(searchField, searchFieldName, eventName);
	}
	
	public void searchAndSelectMostRecentEventTemplate() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a Search for the Most Recent Event Template -> " + mostRecentEventTemplateName);
		
		//Search for the Event Template
		reportLoggerMethods.clear(searchField, searchFieldName);
		reportLoggerMethods.sendKeys(searchField, searchFieldName, mostRecentEventTemplateID);
		
		//Select the Most Recent Event Template from the Search Results
		reportLoggerMethods.click(eventTemplateSearchResultsList.get(0), eventTemplateSearchResultsListName(0));
	}
	
	public void getMostRecentEventTemplateID(String eventName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Retrieving the ID of the most Recently Created Event Template for the Event Name -> " + eventName);
		
		//Iterate through all Searched Event Template Results
		for (int i = 0; i < reportLoggerMethods.getSize(eventTemplateSearchResultsList, eventTemplateSearchResultsListName); i++) {
			//Initialize Variable(s) - Setup variable equal to highest ID found in Search Results
			String eventTemplateSearchResult = reportLoggerMethods.getText(eventTemplateSearchResultsList.get(i), eventTemplateSearchResultsListName(i));
			String[] eventTemplateSearchResultArray = eventTemplateSearchResult.split(" ");
			
			int eventTemplateID = Integer.parseInt(eventTemplateSearchResultArray[eventTemplateSearchResultArray.length-1].substring(0, eventTemplateSearchResultArray[eventTemplateSearchResultArray.length-1].length()-1));
			
			//If the active Search Result has a high ID (more recently created Event Template), update the 'Most Recent Event Template' variable(s)
			if (eventTemplateID > mostRecentEventTemplateID) {
				//Update 'Most Recent Event Template' variable(s)
				mostRecentEventTemplateName = eventTemplateSearchResult;
				mostRecentEventTemplateID = eventTemplateID;
			}
		}
	}
	
	public void locateMostRecentEventTemplate(String eventName) {
		//Navigate to the 'Create New Event' Page
		openActivateEventPage();
		
		//Performing a Search for an Event Template
		searchTemplates(eventName);
		
		//Retrieve the ID of the most Recently Created Event Template
		getMostRecentEventTemplateID(eventName);
		
		//Perform a Search for the Most Recent Event Template
		searchAndSelectMostRecentEventTemplate();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void modifyEventTemplateInfo(String eventName, String modifiedEventName, String modifiedEventMessage, String modifiedShortMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying the Event Template Info for the Event Template -> " + mostRecentEventTemplateName);
		
		//Locate the Most Recent ezNotify Event Template for the specified ezNotify Event Name
		locateMostRecentEventTemplate(eventName);
		
		//Enter the Modified Event Name
		reportLoggerMethods.clear(searchField, searchFieldName);
		reportLoggerMethods.sendKeys(searchField, searchFieldName, modifiedEventName);
		
		//Enter the Modified Event Message Name
		reportLoggerMethods.clear(editMessageField, editMessageFieldName);
		reportLoggerMethods.sendKeys(editMessageField, editMessageFieldName, modifiedEventMessage);
		
		//Enter the Modified Short Message Name
		reportLoggerMethods.clear(editShortMessageField, editShortMessageFieldName);
		reportLoggerMethods.sendKeys(editShortMessageField, editShortMessageFieldName, modifiedShortMessage);
		
		//Activate the Modified Event
		reportLoggerMethods.click(activateNewEventButton, activateNewEventButtonName);
		
		//Accept the Alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Accept the Alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void modifyEventTemplateProcedureInfo(String cancelEdit, String editEventDuration, String editDeviceEscalation, String editRecipientDeviceList, String editProcedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying the Event Template Info for the Event Template -> " + mostRecentEventTemplateName);
		
		//Edit the 'Event Duration'
		reportLoggerMethods.clear(eventDurationField, eventDurationFieldName);
		reportLoggerMethods.sendKeys(eventDurationField, eventDurationFieldName, editEventDuration);
		
		//Select Device Escalation
		if (editDeviceEscalation.equalsIgnoreCase("Use Device Escalation")) {
			reportLoggerMethods.click(deviceEscalationRadioButton, deviceEscalationRadioButtonName);
		} else if (editDeviceEscalation.equalsIgnoreCase("Notify all devices simultaneously")) {
			reportLoggerMethods.click(notifyAllDevicesSimultaneouslyRadioButton, notifyAllDevicesSimultaneouslyRadioButtonName);
		}
		
		//Use Recipient's Device List (if Available & Desired)
		if ((editRecipientDeviceList.equalsIgnoreCase("y") || editRecipientDeviceList.equalsIgnoreCase("yes")) && !reportLoggerMethods.isSelected(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName)) {
			reportLoggerMethods.click(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName);
		} else if ((editRecipientDeviceList.equalsIgnoreCase("n") || editRecipientDeviceList.equalsIgnoreCase("no")) && reportLoggerMethods.isSelected(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName)) {
			reportLoggerMethods.click(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName);
		}
		
		//Select Procedure Device List
		reportLoggerMethods.selectDropDown(procedureDeviceDropDown, procedureDeviceDropDownName, editProcedureDeviceList);
		
		//Cancel or Save the changes
		if (cancelEdit.equalsIgnoreCase("y") || cancelEdit.equalsIgnoreCase("yes")) {
			//Output an info status to the Extent Report & System
			reportLoggerMethods.reportInfo("Cancelling the Modified Event Template Info for the Event Template -> " + mostRecentEventTemplateName);
			
			//Cancel the modified info for the 'Procedure Level' Info
			reportLoggerMethods.click(cancelProcedureInfoButton, cancelProcedureInfoButtonName);
			
			//Accept the Alert
			reportLoggerMethods.acceptAlert();
		} else {
			//Save the modified info for the 'Procedure Level' Info
			reportLoggerMethods.click(saveProcedureInfoButton, saveProcedureInfoButtonName);
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Navigate out of the 'Procedure Info' Page & back to the Event Template's Base Info Page
		reportLoggerMethods.click(exitProcedureInfoPageButton, exitProcedureInfoPageButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Activate the Modified Event
		reportLoggerMethods.click(activateNewEventButton, activateNewEventButtonName);
		
		//Accept the Alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the Alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void verifyEventTemplateInfo(SoftAssert softAssert, String expectedEventName, String expectedEventMessage, String expectedShortMessage, String responseType, String responseTypeQuestion) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the Event Template Info for the Event Template -> " + mostRecentEventTemplateName);
		
		//Initialize Variable(s)
		String actualEventName = reportLoggerMethods.getText(eventTemplateSearchResultsList.get(0), eventTemplateSearchResultsListName(0));
		String actualEventMessage = reportLoggerMethods.getText(editMessageField, editMessageFieldName);
		String actualShortMessage = reportLoggerMethods.getText(editShortMessageField, editShortMessageFieldName);
		expectedEventName = expectedEventName + " [ID: " + mostRecentEventTemplateID + "]";
		
		String failureMessage = "Failure: ";
		boolean accurateTemplateInfo = true;
		
		//Assert the Event Template Info
		softAssert.assertEquals(actualEventName, expectedEventName);
		softAssert.assertEquals(actualEventMessage, expectedEventMessage);
		softAssert.assertEquals(actualShortMessage, expectedShortMessage);
		
		//Check the 'Event Template Name'
		if (!actualEventName.equalsIgnoreCase(expectedEventName)) {
			//Add to the Failure Message
			failureMessage += "The Actual Event Template Name '" + actualEventName + "' does not match Expectations '" + expectedEventName + "'. ";
			
			//Indicate that the ezNotify Template has an incorrect name
			accurateTemplateInfo = false;
		}
		
		//Check the 'Event Template Message'
		if (!actualEventMessage.equalsIgnoreCase(expectedEventMessage)) {
			//Add to the Failure Message
			failureMessage += "The Actual Event Template Message '" + actualEventMessage + "' does not match Expectations '" + expectedEventMessage + "'. ";
			
			//Indicate that the ezNotify Template has an incorrect message
			accurateTemplateInfo = false;
		}
		
		//Check the 'Event Template Short Message'
		if (!actualShortMessage.equalsIgnoreCase(expectedShortMessage)) {
			//Add to the Failure Message
			failureMessage += "The Actual Event Template Short Message '" + actualShortMessage + "' does not match Expectations '" + expectedShortMessage + "'. ";
			
			//Indicate that the ezNotify Template has an incorrect short message
			accurateTemplateInfo = false;
		}
		
		//Check if the Newly Created ezNotify Template Info matches expectations
		if (accurateTemplateInfo) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Newly Created ezNotify Event Named '" + expectedEventName + "' was Created with the Expected Details");
		} else {
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyEventTemplateInfo");
		}
		
		// ~~~ Event Template Fields~~~ //
		
		//Navigate to the 'Additional Options' Page
		reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
		
		//Navigate to the 'Step 1' Subsection
		reportLoggerMethods.click(step1Button, step1ButtonName);
		
		//Initialize Variable(s)
		boolean responeTypeNoneSelection = reportLoggerMethods.isSelected(responseTypeNoneRadioButton, responseTypeNoneRadioButtonName);
		boolean responeTypeConfirmationSelection = reportLoggerMethods.isSelected(responseTypeConfirmationRadioButton, responseTypeConfirmationRadioButtonName);
		boolean responeTypeYesNoQuestionSelection = reportLoggerMethods.isSelected(responseTypeYesNoQuestionRadioButton, responseTypeYesNoQuestionRadioButtonName);
		String responseTypeYesNoQuestionText = reportLoggerMethods.getText(responseTypeYesNoQuestionTextarea, responseTypeYesNoQuestionTextareaName);
		
		failureMessage = "Failure: ";
		accurateTemplateInfo = true;
		
		//Assert the 'Response Type' Selection
		if (responseType.equalsIgnoreCase("none")) {
			softAssert.assertEquals(responeTypeNoneSelection, true);
			softAssert.assertEquals(responeTypeConfirmationSelection, false);
			softAssert.assertEquals(responeTypeYesNoQuestionSelection, false);
		} else if (responseType.equalsIgnoreCase("confirmation")) {
			softAssert.assertEquals(responeTypeNoneSelection, false);
			softAssert.assertEquals(responeTypeConfirmationSelection, true);
			softAssert.assertEquals(responeTypeYesNoQuestionSelection, false);
		} else if (responseType.equalsIgnoreCase("yes/no question") || responseType.equalsIgnoreCase("yesno question") || responseType.equalsIgnoreCase("yes no question")) {
			softAssert.assertEquals(responeTypeNoneSelection, false);
			softAssert.assertEquals(responeTypeConfirmationSelection, false);
			softAssert.assertEquals(responeTypeYesNoQuestionSelection, true);
		} else {
			//Assert the (Incorrect) 'Response Type' Selection
			softAssert.assertEquals("None, Confirmation, or 'Yes/No Question'", responseType);
			
			//Add to the Failure Message
			failureMessage += "The Response Type should be None, Confirmation, or 'Yes/No Question', but expectations listed is '" + responseType + "'. ";
			
			//Indicate that the ezNotify Template has an incorrect short message
			accurateTemplateInfo = false;
		}
		
		//Assert the Event Template Info
		softAssert.assertEquals(responseTypeYesNoQuestionText, responseTypeQuestion);
		
		//Check the Response Type Status
		if (responseType.equalsIgnoreCase("none") && responeTypeNoneSelection && !responeTypeConfirmationSelection && !responeTypeYesNoQuestionSelection) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Response Type matches expectations");
		} else if (responseType.equalsIgnoreCase("confirmation") && !responeTypeNoneSelection && responeTypeConfirmationSelection && !responeTypeYesNoQuestionSelection) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Response Type matches expectations");
		} else if (responseType.equalsIgnoreCase("yes/no question") && !responeTypeNoneSelection && !responeTypeConfirmationSelection && responeTypeYesNoQuestionSelection) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Response Type matches expectations");
		} else {
			//Add to the Failure Message
			failureMessage += "The actual Response Type does not match expectations (Expected Response Type of '" + responseType + "' and Yes/No Question of '" + responseTypeQuestion + "'). ";
			
			//Indicate that the ezNotify Template has an incorrect short message
			accurateTemplateInfo = false;
		}
		
		//Check if the Newly Created ezNotify Template Info matches expectations
		if (responseType.equalsIgnoreCase("yes/no question") && responseTypeYesNoQuestionText.equalsIgnoreCase(responseTypeQuestion)) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Response Type Question matches expectations");
		} else if (responseType.equalsIgnoreCase("yes/no question") && !responseTypeYesNoQuestionText.equalsIgnoreCase(responseTypeQuestion)) {
			//Add to the Failure Message
			failureMessage += "The actual Response Type (" + responseTypeYesNoQuestionText + ") does not match expectations (" + responseTypeQuestion + "). ";
			
			//Indicate that the ezNotify Template has an incorrect short message
			accurateTemplateInfo = false;
		} else if (!responseType.equalsIgnoreCase("yes/no question") && !responseTypeQuestion.equalsIgnoreCase("")) {
			//Add to the Failure Message
			failureMessage += "There should be no 'Yes/No Question', with the data's Response Type expectation of '" + responseTypeQuestion + "', but the data also expects a 'Yes/No Question of '" + responseTypeQuestion + "'. ";
			
			//Indicate that the ezNotify Template has an incorrect short message
			accurateTemplateInfo = false;
		}
		
		//Check if the Newly Created ezNotify Template Info matches expectations
		if (accurateTemplateInfo) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Newly Created ezNotify Event Named '" + expectedEventName + "' was Created with the Expected Details");
		} else {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyEventTemplateInfo");
		}
	}
	
	public void verifyProcedureInfo(SoftAssert softAssert, String eventDuration, String deviceEscalationSelection, String useRecipientsDeviceListSelection, String procedureDeviceSelection) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the Event Template's 'Procedure 1' Info for the Event Template -> " + mostRecentEventTemplateName);
		
		//Navigate to the 'Additional Options' Page, if needed
		if (reportLoggerMethods.isDisplayed(additionalOptionsButton, additionalOptionsButtonName)) {
			reportLoggerMethods.click(additionalOptionsButton, additionalOptionsButtonName);
		}
		
		//Navigate to the 'Procedure 1' Subsection
		reportLoggerMethods.click(procedure1Button, procedure1ButtonName);
		
		//Initialize Variable(s)
		String successfulMessage = "Success: The Event Template's 'Procedure 1' Info Matches Expectations";
		String failureMessage = "Failure: ";
		boolean passedExpectations = true;
		
		String actualEventDuration = reportLoggerMethods.getAttribute(eventDurationField, eventDurationFieldName, "value");
		boolean actualUseDeviceEscalationSelection = reportLoggerMethods.isSelected(deviceEscalationRadioButton, deviceEscalationRadioButtonName);
		boolean actualNotifyAllDevicesSimultaneouslySelection = reportLoggerMethods.isSelected(notifyAllDevicesSimultaneouslyRadioButton, notifyAllDevicesSimultaneouslyRadioButtonName);
		boolean actualUseRecipientsDeviceListSelection = reportLoggerMethods.isSelected(useRecipientsDeviceListCheckbox, useRecipientsDeviceListCheckboxName);
		String actualProcedureDeviceSelection = reportLoggerMethods.getText(procedureDeviceSelectedDropDown, procedureDeviceSelectedDropDownName);
		
		String expectedEventDuration = eventDuration;
		boolean expectedUseDeviceEscalationSelection = true;
		boolean expectedNotifyAllDevicesSimultaneouslySelection = false;
		boolean expectedUseRecipientsDeviceListSelection;
		String expectedProcedureDeviceSelection = procedureDeviceSelection;
		
		//Set the value for the Expected 'Device Escalation' Selection
		if (deviceEscalationSelection.equalsIgnoreCase("Use Device Escalation")) {
			expectedUseDeviceEscalationSelection = true;
			expectedNotifyAllDevicesSimultaneouslySelection = false;
		} else if (deviceEscalationSelection.equalsIgnoreCase("Notify all devices simultaneously")) {
			expectedUseDeviceEscalationSelection = false;
			expectedNotifyAllDevicesSimultaneouslySelection = true;
		} else {
			//Assert the 'Device Escalation' Status
			softAssert.assertEquals("Device Escalation Expectation should be 'Deivce Escalation' or 'Notify all devices simultaneously'", "Deivce Escalation Expectation is set to " + deviceEscalationSelection);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Expected 'Device Escalation' should be set to 'Deivce Escalation' or 'Notify all devices simultaneously', not '" + deviceEscalationSelection + "'. ", "verifyProcedureInfo");
		}
		
		//Set the value for the Expected 'Use Recipients Device List' Selection
		if (useRecipientsDeviceListSelection.equalsIgnoreCase("y") || useRecipientsDeviceListSelection.equalsIgnoreCase("yes")) {
			expectedUseRecipientsDeviceListSelection = true;
		} else {
			expectedUseRecipientsDeviceListSelection = false;
		}
		
		//Assert the 'Procedure 1' Info
		softAssert.assertEquals(actualEventDuration, expectedEventDuration);
		softAssert.assertEquals(actualUseDeviceEscalationSelection, expectedUseDeviceEscalationSelection);
		softAssert.assertEquals(actualNotifyAllDevicesSimultaneouslySelection, expectedNotifyAllDevicesSimultaneouslySelection);
		softAssert.assertEquals(actualUseRecipientsDeviceListSelection, expectedUseRecipientsDeviceListSelection);
		softAssert.assertEquals(actualProcedureDeviceSelection, expectedProcedureDeviceSelection);
		
		//Check the 'Event Duration'
		if (!actualEventDuration.equalsIgnoreCase(expectedEventDuration)) {
			//Add to the Failure Message
			failureMessage += "The Actual 'Event Duration' (" + actualEventDuration + ") does not match Expectations (" + expectedEventDuration + ") ";
			
			//Specify that the Actual Results do not Expectations
			passedExpectations = false;
		}
		
		//Check the 'Device Escalation Selection'
		if (actualUseDeviceEscalationSelection != expectedUseDeviceEscalationSelection) {
			//Add to the Failure Message
			failureMessage += "The Actual 'Device Escalation Selection' (" + actualUseDeviceEscalationSelection + ") does not match Expectations (" + expectedUseDeviceEscalationSelection + ") ";
			
			//Specify that the Actual Results do not Expectations
			passedExpectations = false;
		}
		
		//Check the 'Notify All Devices Simultaneously Selection'
		if (actualNotifyAllDevicesSimultaneouslySelection != expectedNotifyAllDevicesSimultaneouslySelection) {
			//Add to the Failure Message
			failureMessage += "The Actual 'Notify All Devices Simultaneously Selection' (" + actualNotifyAllDevicesSimultaneouslySelection + ") does not match Expectations (" + expectedNotifyAllDevicesSimultaneouslySelection + ") ";
			
			//Specify that the Actual Results do not Expectations
			passedExpectations = false;
		}
		
		//Check the 'Use Recipients Device List Selection'
		if (actualUseRecipientsDeviceListSelection != expectedUseRecipientsDeviceListSelection) {
			//Add to the Failure Message
			failureMessage += "The Actual 'Use Recipients Device List Selection' (" + actualUseRecipientsDeviceListSelection + ") does not match Expectations (" + expectedUseRecipientsDeviceListSelection + ") ";
			
			//Specify that the Actual Results do not Expectations
			passedExpectations = false;
		}
		
		//Check the 'Procedure Device Selection'
		if (!actualProcedureDeviceSelection.equalsIgnoreCase(expectedProcedureDeviceSelection)) {
			//Add to the Failure Message
			failureMessage += "The Actual 'Procedure Device Selection' (" + actualProcedureDeviceSelection + ") does not match Expectations (" + expectedProcedureDeviceSelection + ") ";
			
			//Specify that the Actual Results do not Expectations
			passedExpectations = false;
		}
		
		//Report the Results to the Extent Report & System
		if (passedExpectations) {
			reportLoggerMethods.reportSuccessfulCheckpoint(successfulMessage);
		} else {
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyProcedureInfo");
		}
	}
	
	public void verifyNewEventTemplateInfo(SoftAssert softAssert, String eventName, String eventMessage, String shortMessage, String responseType, String responseTypeQuestion, String addAdditionalOptions, String eventDuration, String deviceEscalation, String useRecipientsDeviceList, String procedureDevice) {
		//Locate the Most Recent ezNotify Event Template for the specified ezNotify Event Name
		locateMostRecentEventTemplate(eventName);
		
		//Verify the Event Template Info
		verifyEventTemplateInfo(softAssert, eventName, eventMessage, shortMessage, responseType, responseTypeQuestion);
		
		//Verify the Event Procedure 1 Info, if desired
		if (addAdditionalOptions.equalsIgnoreCase("y") || addAdditionalOptions.equalsIgnoreCase("yes")) {
			verifyProcedureInfo(softAssert, eventDuration, deviceEscalation, useRecipientsDeviceList, procedureDevice);
		}
	}
	
}