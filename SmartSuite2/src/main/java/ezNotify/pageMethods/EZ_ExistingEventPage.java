package ezNotify.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ezNotify.pageLocators.EZ_ExistingEventLocators;

public class EZ_ExistingEventPage extends EZ_ExistingEventLocators {
	
	//Initialize Variable(s)
	int existingEventSize = 0;
	
	//Constructor
	public EZ_ExistingEventPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void cancelEZNotifyEvent(SoftAssert softAssert, String eventName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Canceling the most recent ezNotify Event");
		
		//Focus on the list of Existing Events based on a specified Event Name
		setExistingEventButtonList(eventName);
		
		//Initialize Variable(s)
		int eventListCount = getExistingEventButtonListSize(eventName);
		
		//Check if any ezNotify Events exist
		if (eventListCount == 0) {
			//Assert the number of ezNotify Events
			softAssert.assertEquals(0, (existingEventSize + 1));
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No new ezNotify Event named '" + eventName + "' was created", "verifyExistingEZNotifyEvent");
			
			//Exit the Method
			return;
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
		
		//Open the Existing Event
		reportLoggerMethods.click(existingEventsButtonList.get(0), existingEventsButtonListName(0));
		
		//Click the 'Cancel ezNotify Event' Button
		reportLoggerMethods.click(cancelEventButton, cancelEventButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the Alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	// ~~~ Verify Current Page ~~~ //
	
	public void verifyEZNotifyEventStatus(SoftAssert softAssert, String eventName, String expectedStatus) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the ezNotify Event Status of '" + expectedStatus + "'");
		
		//Retrieve the ezNotify Event Status
		String[] eventStatusArray = existingEventStatusLabel(eventName).split("/");
		String eventStatus = eventStatusArray[eventStatusArray.length-1];
		
		//Assert the ezNotify Event Status
		softAssert.assertEquals(eventStatus, expectedStatus);
		
		//Check the ezNotify Event Status
		if (!eventStatus.equalsIgnoreCase(expectedStatus)) {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Actual ezNotify Event Status (" + eventStatus + ") does not match Expectations (" + expectedStatus + ")", "verifyEZNotifyEventStatus");
		} else {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The ezNotify Event Status matches Expectations");
		}
	}
	
	// ~~~ Verify Current Page ~~~ //
	
	public void getExistingEventSize(String eventName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Retrieving the Amount of Existing ezNotify Events");
		
		//Retrieve the Amount of Existing ezNotify Events
		existingEventSize = getExistingEventButtonListSize(eventName);
	}
	
	public String getEzNotifyEventRequestNumber() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Retrieving the Create ezNotify Event's Request Number");
		
		//Go to the ezNotify Event Page's base info link
		reportLoggerMethods.click(eventBaseInfoLink, eventBaseInfoLinkName);
		
		//Retrieve the 'Request Number'
		return reportLoggerMethods.getAttribute(requestNumberField, requestNumberFieldName, "value");
	}
	
	public void verifyResponse(SoftAssert softAssert, String eventName, String personResponding, String expectedResponse) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the ezNotify's Web Response");
		
		//Enter the 'Create a New ezNotify Event' Page
		reportLoggerMethods.click(createEventButton, createEventButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Refresh the Current Page
		reportLoggerMethods.refreshPage();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Focus on the list of Existing Events based on a specified Event Name
		setExistingEventButtonList(eventName);
		
		//Initialize Variable(s)
		int eventListCount = getExistingEventButtonListSize(eventName);
		
		//Check if any ezNotify Events exist
		if (eventListCount == 0) {
			//Assert the number of ezNotify Events
			softAssert.assertEquals(0, (existingEventSize + 1));
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No new ezNotify Event named '" + eventName + "' was created", "verifyResponse");
			
			//Exit the Method
			return;
		}
		
		//Assert the number of ezNotify Events
		softAssert.assertEquals(eventListCount, (existingEventSize + 1));
		
		//Check if the Amount of ezNotify Events match Expectations
		if (eventListCount != (existingEventSize + 1)) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("The newly created ezNotify was not created as expected. Expected the amount of ezNotify Events named '" + eventName + "' to go from " + existingEventSize + " to " + (existingEventSize + 1) + ", but found " + eventListCount + " events", "verifyResponse");
			
			//Exit the Method
			return;
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
		
		//Open the Existing Event
		reportLoggerMethods.click(existingEventsButtonList.get(0), existingEventsButtonListName(0));
		
		//Navigate to the 'Step 1' Section
		reportLoggerMethods.click(eventStep1Link, eventStep1LinkName);
		
		//Retrieve the Response Type from the specified person
		String requestNumber = personResponseLabel(personResponding);
		
		//Assert the ezNotify Event Recipient Response
		softAssert.assertEquals(requestNumber, expectedResponse);
		
		//Check the ezNotify Event Recipient Response
		if (requestNumber.equalsIgnoreCase(expectedResponse)) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The ezNotify Event received a successful '" + expectedResponse + "' Response");
		} else {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The ezNotify Event received a response of '" + requestNumber + "', but expected a response of '" + expectedResponse + "'", "verifyResponse");
		}
	}
	
	public void verifyExistingEZNotifyEvent(SoftAssert softAssert, String eventName, String eventMessage, String recipients, String addAdditionalOptions, String deviceEscalation, String recipientDeviceList, String procedureDeviceList) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying the Newly Created/Modified ezNotify Event Named '" + eventName + "'");
		
		//Focus on the list of Existing Events based on a specified Event Name
		setExistingEventButtonList(eventName);
		
		//Initialize Variable(s)
		int eventListCount = getExistingEventButtonListSize(eventName);
		
		String failureMessage = "Failure: ";
		boolean accurateEventInfo = true;
		
		//Check if any ezNotify Events exist
		if (eventListCount == 0) {
			//Assert the number of ezNotify Events
			softAssert.assertEquals(0, (existingEventSize + 1));
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No new ezNotify Event named '" + eventName + "' was created", "verifyExistingEZNotifyEvent");
			
			//Exit the Method
			return;
		}
		
		//Assert the number of ezNotify Events
		softAssert.assertEquals(eventListCount, (existingEventSize + 1));
		
		//Check if the Amount of ezNotify Events match Expectations
		if (eventListCount != (existingEventSize + 1)) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("The newly created ezNotify was not created as expected. Expected the amount of ezNotify Events named '" + eventName + "' to go from " + existingEventSize + " to " + (existingEventSize + 1) + ", but found " + eventListCount + " events. ", "verifyExistingEZNotifyEvent");
			
			//Exit the Method
			return;
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(9000);
		
		//Open the Existing Event
		reportLoggerMethods.click(existingEventsButtonList.get(0), existingEventsButtonListName(0));
		
		//Assert the ezNotify Event Info
		softAssert.assertEquals(reportLoggerMethods.getText(eventNameLabel, eventNameLabelName).trim(), "Event Name: " + eventName);
		softAssert.assertEquals(reportLoggerMethods.getText(eventTextMessageTextarea, eventTextMessageTextareaName).trim(), eventMessage);
		
		//Check the ezNotify Event Name
		if (!reportLoggerMethods.getText(eventNameLabel, eventNameLabelName).trim().equals("Event Name: " + eventName)) {
			//Report the assert status to the Extent Report & System
			failureMessage += "The Event Name (" + reportLoggerMethods.getText(eventNameLabel, eventNameLabelName).trim() + ") does not match expectations (Event Name: " + eventName + "). ";
			
			//Indicate that the ezNotify Event has an incorrect name
			accurateEventInfo = false;
		}
		
		//Check the ezNotify Event Message
		if (!reportLoggerMethods.getText(eventTextMessageTextarea, eventTextMessageTextareaName).trim().equals(eventMessage)) {
			//Report the assert status to the Extent Report & System
			failureMessage += "The Event Message (" + reportLoggerMethods.getText(eventTextMessageTextarea, eventTextMessageTextareaName).trim() + ") does not match expectations (" + eventMessage + "). ";
			
			//Indicate that the ezNotify Event has an incorrect message
			accurateEventInfo = false;
		}
		
		//Check if the Newly Created ezNotify Event Info matches expectations
		if (accurateEventInfo) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Newly Created ezNotify Event's details match Expectations");
		} else {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyExistingEZNotifyEvent");
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(2000);
		
		//Initialize Variable(s)
		failureMessage = "Failure: ";
		accurateEventInfo = true;
		genMethods.waitForMilliseconds(6000);
		
		//Navigate to the 'Step 1' Section
		reportLoggerMethods.click(eventStep1Link, eventStep1LinkName);
		
//		//Initialize Variable(s)
//		String[] recipientsList = recipients.split("/");
//		
//		//Retrieve the Number of Recipients Listed in the ezNotify Event
//		int recipientCount = 0;
//		if (reportLoggerMethods.isDisplayed(eventRecipientsNameLabelList.get(0), eventRecipientsNameLabelListName)) {
//			recipientCount = reportLoggerMethods.getSize(eventRecipientsNameLabelList, eventRecipientsNameLabelListName);
//		}
//		
//		//Assert the Number of Recipients
//		softAssert.assertEquals(recipientCount, recipientsList.length);
//		
//		//Check the Number of Recipients
//		if (recipientCount != recipientsList.length) {
//			//Report the assert status to the Extent Report & System
//			failureMessage += "The Number of Recipients Listed (" + recipientCount + ") does not match Expectations (" + recipientsList.length + "). ";
//			
//			//Indicate that the ezNotify Event has an incorrect number of recipients
//			accurateEventInfo = false;
//		}
//		
//		//Check if Recipient(s) match Expectations
//		for (int i = 0; i < recipientsList.length; i++) {
//			//Assert the #i Recipient
//			softAssert.assertEquals(reportLoggerMethods.getText(eventRecipientsNameLabelList.get(i), eventRecipientsNameLabelListName(i)).toLowerCase(), recipientsList[i].trim().toLowerCase());
//			
//			//Check if the #i Recipient Matches Expectations
//			if (!reportLoggerMethods.getText(eventRecipientsNameLabelList.get(i), eventRecipientsNameLabelListName(i)).equalsIgnoreCase(recipientsList[i].trim())) {
//				//Report the assert status to the Extent Report & System
//				failureMessage += "The #" + i + " recipient (" + reportLoggerMethods.getText(eventRecipientsNameLabelList.get(i), eventRecipientsNameLabelListName(i)) + ") does not match expectations (" + recipientsList[i].trim() + "). ";
//				
//				//Indicate that the ezNotify Event has an incorrect recipient info
//				accurateEventInfo = false;
//			}
//		}
		
		//Check if the Newly Created ezNotify Event Info matches expectations
		if (accurateEventInfo) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The Newly Created ezNotify Event's 'Step 1' details match Expectations");
		} else {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyExistingEZNotifyEvent");
		}
		
		//Verify the newly created ezNotify Event's 'Procedure 1' Tab
		if (addAdditionalOptions.equalsIgnoreCase("y") || addAdditionalOptions.equalsIgnoreCase("yes")) {
			verifyAdditionalOptions(softAssert, deviceEscalation, recipientDeviceList, procedureDeviceList);
		}
	}
	
	public void verifyAdditionalOptions(SoftAssert softAssert, String deviceEscalation, String recipientDeviceList, String procedureDeviceList) {
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Navigate to the 'Procedure 1' Section
		reportLoggerMethods.click(eventProcedure1Link, eventProcedure1LinkName);
		
		//Initialize Variable(s)
		String actualProcedureDeviceLiist = reportLoggerMethods.getText(procedureDeviceListLabel, procedureDeviceListLabelName);
		boolean actualDeviceEscalation = reportLoggerMethods.isSelected(notifyAllDevicesSimultaneouslyCheckbox, notifyAllDevicesSimultaneouslyCheckboxName);
		boolean actualRecipientDeviceList = reportLoggerMethods.isSelected(usedRecipientsDeviceListCheckbox, usedRecipientsDeviceListCheckboxName);
		
		String successMessage = "Success: The 'Procedure 1' (Advanced Options) info matches Expectations";
		String failureMessage = "Failure: ";
		boolean failure = false;
		
		//Assert the 'Procedure Device List' Status
		softAssert.assertEquals(procedureDeviceList, actualProcedureDeviceLiist);
		
		//Check the 'Procedure Device List' Status
		if (!procedureDeviceList.equalsIgnoreCase(actualProcedureDeviceLiist)) {
			//Add to the Failure Message
			failureMessage += "The Actual Procedure Device List info (" + actualProcedureDeviceLiist + ") does not match expectations (" + procedureDeviceList + "). ";
			
			//Set the 'Failure' status to 'true'
			failure = true;
		}
		
		//Check the Expected 'Device Escalation'
		if (deviceEscalation.equalsIgnoreCase("Use Device Escalation")) {
			//Assert the 'Device Escalation' Status
			softAssert.assertEquals(actualDeviceEscalation, false);
			
			//Check the 'Device Escalation' Status
			if (actualDeviceEscalation == true) {
				//Add to the Failure Message
				failureMessage += "The Actual Device Escalation checkbox is selected, but expected it to not be selected. ";
				
				//Set the 'Failure' status to 'true'
				failure = true;
			}
		} else if (deviceEscalation.equalsIgnoreCase("Notify all devices simultaneously")) {
			//Assert the 'Device Escalation' Status
			softAssert.assertEquals(actualDeviceEscalation, true);
			
			//Check the 'Device Escalation' Status
			if (actualDeviceEscalation == false) {
				//Add to the Failure Message
				failureMessage += "The Actual Device Escalation checkbox is not selected, but expected it to be selected. ";
				
				//Set the 'Failure' status to 'true'
				failure = true;
			}
		} else {
			//Assert the 'Device Escalation' Status
			softAssert.assertEquals("Device Escalation Expectation should be 'Deivce Escalation' or 'Notify all devices simultaneously'", "Deivce Escalation Expectation is set to " + deviceEscalation);
			
			//Add to the Failure Message
			failureMessage += "The Expected 'Device Escalation' should be set to 'Deivce Escalation' or 'Notify all devices simultaneously', not '" + deviceEscalation + "'. ";
			
			//Set the 'Failure' status to 'true'
			failure = true;
		}
		
		//Assert the 'Recipient Device List' Status
		if (recipientDeviceList.equalsIgnoreCase("y") || recipientDeviceList.equalsIgnoreCase("yes")) {
			softAssert.assertEquals(actualRecipientDeviceList, true);
			
			//Check the 'Recipient Device List' Status
			if (actualDeviceEscalation == false) {
				//Add to the Failure Message
				failureMessage += "The Actual Recipient Device List checkbox is not selected, but expected it to be selected. ";
				
				//Set the 'Failure' status to 'true'
				failure = true;
			}
		} else {
			softAssert.assertEquals(actualRecipientDeviceList, false);
			
			//Check the 'Recipient Device List' Status
			if (actualDeviceEscalation == true) {
				//Add to the Failure Message
				failureMessage += "The Actual Recipient Device List checkbox is selected, but expected it to be not selected. ";
				
				//Set the 'Failure' status to 'true'
				failure = true;
			}
		}
		
		//Output an Error Message, if Applicable
		if (failure) {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyAdditionalOptions");
		} else {
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
		}
	}
	
}