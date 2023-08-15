package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PersonalProfile_InboxLocators;

public class PersonalProfile_InboxPage extends PersonalProfile_InboxLocators {
	
	//Constructor
	public PersonalProfile_InboxPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	public void clickActiveMessagesLink() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Active Messages' link to go to the 'Messages' page");
		
		//Click the 'Active Messages' link to go to the 'Messages' page
		reportLoggerMethods.click(activeMessagesLink, activeMessagesLinkName);
	}
	
	public void goToArchivedMessages() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Archived Messages' page");
		
		//Navigate to the 'Archived Messages' page if not, currently, on the 'Archived Messages' page
		if (reportLoggerMethods.isDisplayed(goToArchivedMessagesLinkMessage, goToArchivedMessagesLinkMessageName)) {
			reportLoggerMethods.click(goToArchivedMessagesLink, goToArchivedMessagesLinkName);
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Reset any search criteria
		reportLoggerMethods.click(activeMessageResetButton, activeMessageResetButtonName);
	}
	
	public void goToActiveMessages() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Active Messages' page");
		
		//Navigate to the 'Active Messages' page if not, currently, on the 'Active Messages' page
		if (reportLoggerMethods.isDisplayed(goToActiveMessagesLinkMessage, goToActiveMessagesLinkMessageName)) {
			reportLoggerMethods.click(goToActiveMessagesLink, goToActiveMessagesLinkName);
		}
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Reset any search criteria
		reportLoggerMethods.click(activeMessageResetButton, activeMessageResetButtonName);
	}
	
	// ~~~ Active Messages ~~~ //
	
	public void searchActiveMessages(String sender, String message, String startDate, String endDate) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a search on the Active Messages");
		
		//Click to reset the search criteria fields
		reportLoggerMethods.click(activeMessageResetButton, activeMessageResetButtonName);
		
		//Enter the 'Sender' search criteria
		reportLoggerMethods.sendKeys(activeMessageSearchSenderField, activeMessageSearchSenderFieldName, sender);
		
		//Enter the 'Message' search criteria
		reportLoggerMethods.sendKeys(activeMessageSearchMessageField, activeMessageSearchMessageFieldName, message);
		
		//Enter the 'Start Date' search criteria
		reportLoggerMethods.sendKeys(activeMessageSearchStartDateField, activeMessageSearchStartDateFieldName, startDate);
		
		//Enter the 'End Date' search criteria
		reportLoggerMethods.sendKeys(activeMessageSearchEndDateField, activeMessageSearchEndDateFieldName, endDate);
		
		//Click the 'Search' button
		reportLoggerMethods.click(activeMessageSearchButton, activeMessageSearchButtonName);
	}
	
	public void verifyActiveMessageSearchResultSenderLabel(SoftAssert softAssert, String expectedSender) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result's 'Sender' info matches expectation(s)");
		
		//Check if Search Results are present
		if (reportLoggerMethods.isDisplayed(activeMessageSearchResultSenderLabel, activeMessageSearchResultSenderLabelName)) {
			//Initialize Variable(s)
			String searchResultSender = reportLoggerMethods.getText(activeMessageSearchResultSenderLabel, activeMessageSearchResultSenderLabelName);
			
			//Assert the expected search result's sender
			softAssert.assertEquals(searchResultSender, expectedSender);
			
			//Report the assert status to the Extent Report & System
			if (searchResultSender.equalsIgnoreCase(expectedSender)) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result's 'Sender' info matches expectations");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The actual 'Sender' info (" + searchResultSender + ") does not match expectations (" + expectedSender + ")", "verifyActiveMessageSearchResultSenderLabel");
			}
		} else {
			//Assert the expected search result's sender
			softAssert.assertEquals("There are no search result(s)", "Expected at least one search result");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are no search result(s)", "verifyActiveMessageSearchResultSenderLabel");
		}
	}
	
	public void verifyActiveMessageSearchResultMessageLabel(SoftAssert softAssert, String expectedMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result's 'Message' info matches expectation(s)");
		
		//Check if Search Results are present
		if (reportLoggerMethods.isDisplayed(activeMessageSearchResultMessageLabel, activeMessageSearchResultMessageLabelName)) {
			//Initialize Variable(s)
			String searchResultMessage = reportLoggerMethods.getText(activeMessageSearchResultMessageLabel, activeMessageSearchResultMessageLabelName);
			
			//Checking if the search result's 'Message' info matches expectation(s)
			if (searchResultMessage.contains(expectedMessage)) {
				//Assert the expected search result's message
				softAssert.assertEquals("The search result's 'Message' info matches expectation(s)", "The search result's 'Message' info matches expectation(s)");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result's 'Message' info matches expectation(s)");
			} else {
				//Assert the expected search result's message
				softAssert.assertEquals(searchResultMessage, expectedMessage);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The search result's 'Message' info (" + searchResultMessage + ") does not match expectation(s) (" + expectedMessage + ")", "verifyActiveMessageSearchResultMessageLabel");
			}
		} else {
			//Assert the expected search result's sender
			softAssert.assertEquals("There are no search result(s)", "Expected at least one search result");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are no search result(s)", "verifyActiveMessageSearchResultMessageLabel");
		}
	}
	
	public void verifyActiveMessageSearchResultEnteredDateLabel(SoftAssert softAssert, String expectedEnteredDate) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result's 'Date Entered' info matches expectation(s)");
		
		if (reportLoggerMethods.isDisplayed(activeMessageSearchResultDateEnteredLabel, activeMessageSearchResultDateEnteredLabelName)) {
			//Initialize Variable(s)
			String searchResultDateEntered = reportLoggerMethods.getText(activeMessageSearchResultDateEnteredLabel, activeMessageSearchResultDateEnteredLabelName);
			
			//Checking if the search result's 'Date Archived' info matches expectation(s)
			if (searchResultDateEntered.contains(expectedEnteredDate)) {
				//Assert the expected search result's message
				softAssert.assertEquals("The search result's 'Date Entered' info matches expectation(s)", "The search result's 'Date Entered' info matches expectation(s)");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result's 'Date Entered' info matches expectation(s)");
			} else {
				//Assert the expected search result's message
				softAssert.assertEquals(searchResultDateEntered, expectedEnteredDate);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The actual search result's 'Date Entered' info (" + searchResultDateEntered + ") does not match expectation(s) (" + expectedEnteredDate + ")", "verifyActiveMessageSearchResultEnteredDateLabel");
			}
		} else {
			//Assert the expected search result's sender
			softAssert.assertEquals("There are no search result(s)", "Expected at least one search result");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are no search result(s)", "verifyActiveMessageSearchResultEnteredDateLabel");
		}
	}
	
	// ~~~ Other Active Messages ~~~ //
	
	public void verifyActiveMessage(SoftAssert softAssert, String sender, String name, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the relevant active message in located in the 'Active Messages' inbox");
		
		//Initialize Variable(s)
		String activeMessage = "";
		String activeMessage2 = "";
		String activeMessage3 = "";
		
		//Loop through all the active messages
		for (int i = 0; i < reportLoggerMethods.getSize(activeMessagesNameList, activeMessagesNameListName); i++) {
			//Initialize Variable(s)
			String activeMessagesMessageStr = reportLoggerMethods.getText(activeMessagesMessageList.get(i), activeMessagesMessageListName(i));
			
			//Retrieve the current Active Message's message
			activeMessage = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length()-12);
			activeMessage2 = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length()-13);
			activeMessage3 = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length()-14);
			
			//Check if the relevant active message in located in the 'Active Messages' inbox
			if (reportLoggerMethods.getText(activeMessagesSenderList.get(i), activeMessagesSenderListName(i)).equalsIgnoreCase(sender) && reportLoggerMethods.getText(activeMessagesNameList.get(i), activeMessagesNameListName(i)).equalsIgnoreCase(name) && (activeMessage.equalsIgnoreCase(message) || activeMessage2.equalsIgnoreCase(message) || activeMessage3.equalsIgnoreCase(message))) {
				//Check that the name & sender of the page is correct
				softAssert.assertEquals(reportLoggerMethods.getText(activeMessagesSenderList.get(i), activeMessagesSenderListName(i)), sender);
				softAssert.assertEquals(reportLoggerMethods.getText(activeMessagesNameList.get(i), activeMessagesNameListName(i)), name);
				
				//Assert the status of the Active Message
				if (activeMessage.equalsIgnoreCase(message)) {
					softAssert.assertEquals(activeMessage, message);
				} else if (activeMessage2.equalsIgnoreCase(message)) {
					softAssert.assertEquals(activeMessage2, message);
				} else if (activeMessage3.equalsIgnoreCase(message)) {
					softAssert.assertEquals(activeMessage3, message);
				} else {
					softAssert.assertEquals(activeMessagesMessageStr, message);
				}
				
				//Select the active message to be archived
				clickArchiveCheckbox(i);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(2000);
				
				//Archive the selected message
				reportLoggerMethods.click(archiveSelectedMessagesButton, archiveSelectedMessagesButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Accept the alert/pop-up
				reportLoggerMethods.acceptAlert();
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The relevant active message has been found");
				
				//Finish the method with an Extent Report status
				return;
			}
		}
		
		//Report the status to the Extent Report & System
		if (reportLoggerMethods.getSize(activeMessagesNameList, activeMessagesNameListName) == 0) {
			//Check that the name & sender of the page is correct
			softAssert.assertEquals("No active message(s)", "Active Message with sender = '" + sender + "', name = '" + name + "', and message = '" + message + "'");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No Active Messages in the inbox", "verifyActiveMessage");
		}  else {
			//Check that the name & sender of the page is correct
			softAssert.assertEquals("No Active Message with sender = '" + sender + "', name = '" + name + "', and message = '" + message + "'", "Found Active Message with sender = '" + sender + "', name = '" + name + "', and message = '" + message + "'");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No Active Message with sender = '" + sender + "', name = '" + name + "', and message = '" + message + "'", "verifyActiveMessage");
		}
	}
	
	public void verifyActiveMessage(SoftAssert softAssert, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verify & Move the relevant 'Active Message' to the 'Archived Messages'");
		
		//Initialize Variable(s)
		String activeMessage = "";
		String activeMessage2 = "";
		String activeMessage3 = "";
		
		//Loop through all the active messages
		for (int i = 0; i < reportLoggerMethods.getSize(activeMessagesNameList, activeMessagesNameListName); i++) {
			//Initialize Variable(s)
			String activeMessagesMessageStr = reportLoggerMethods.getText(activeMessagesMessageList.get(i), activeMessagesMessageListName(i));
			
			//Retrieve the current Active Message's message
			activeMessage = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length()-12);
			activeMessage2 = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length()-13);
			activeMessage3 = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length()-14);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Check if the relevant active message in located in the 'Active Messages' inbox
			if (activeMessage.equalsIgnoreCase(message) || activeMessage2.equalsIgnoreCase(message) || activeMessage3.equalsIgnoreCase(message)) {
				//Select the active message to be archived
				clickArchiveCheckbox(i);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(2000);
				
				//Archive the selected message
				reportLoggerMethods.click(archiveSelectedMessagesButton, archiveSelectedMessagesButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Accept the alert/pop-up
				reportLoggerMethods.acceptAlert();
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The relevant active message has been found");
				
				//Finish the method with an Extent Report status
				return;
			}
		}
		
		//Report the status to the Extent Report & System
		if (reportLoggerMethods.getSize(activeMessagesNameList, activeMessagesNameListName) == 0) {
			//Check that the name & sender of the page is correct
			softAssert.assertEquals("No active message(s)", "Active Message with message = '" + message + "'");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No Active Messages in the inbox", "verifyActiveMessage");
		}  else {
			//Check that the name & sender of the page is correct
			softAssert.assertEquals("No Active Message with message = '" + message + "'", "Found Active Message with message = '" + message + "'");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No Active Message with message = '" + message + "'", "verifyActiveMessage");
		}
	}
	
	public void verifyActiveExternalMessage(SoftAssert softAssert, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the relevant active message in located in the 'Active Messages' inbox");
		
		//Initialize Variable(s)
		String activeMessage = "";
		
		//Loop through all the active messages
		for (int i = 0; i < reportLoggerMethods.getSize(activeMessagesNameList, activeMessagesNameListName); i++) {
			//Initialize Variable(s)
			String activeMessagesMessageStr = reportLoggerMethods.getText(activeMessagesMessageList.get(i), activeMessagesMessageListName(i));
			
			//Retrieve the current Active Message's message
			activeMessage = activeMessagesMessageStr.substring(0, activeMessagesMessageStr.length());
			
			//Check if the relevant active message in located in the 'Active Messages' inbox
			if (activeMessage.equalsIgnoreCase(message)) {
				//Check that the name & sender of the page is correct
				softAssert.assertEquals(activeMessage, message);
				
				//Select the active message to be archived
				clickArchiveCheckbox(i);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(2000);
				
				//Archive the selected message
				reportLoggerMethods.click(archiveSelectedMessagesButton, archiveSelectedMessagesButtonName);
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Accept the alert/pop-up
				reportLoggerMethods.acceptAlert();
				
				//Pause the script for a bit
				genMethods.waitForMilliseconds(1000);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The relevant active message has been found");
				
				//Finish the method with an Extent Report status
				return;
			}
		}
		
		//Check the status of the Active Messages when the expected Active Message was not found
		if (reportLoggerMethods.getSize(activeMessagesNameList, activeMessagesNameListName) == 0) {
			//Check that the name & sender of the page is correct
			softAssert.assertEquals("No active message(s)", "Active Message with message = '" + message + "'");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No Active Messages in the inbox", "verifyActiveExternalMessage");
		}  else {
			//Check that the name & sender of the page is correct
			softAssert.assertEquals("No Active Message with message = '" + message + "'", "Found Active Message with message = '" + message + "'");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No Active Message with message = '" + message + "'", "verifyActiveExternalMessage");
		}
	}
	
	// ~~~ Archived Messages ~~~ //
	
	public void searchArchivedMessages(String sender, String message, String startDate, String endDate) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a search on the Archived Messages");
		
		//Click to reset the search criteria fields
		reportLoggerMethods.click(archivedMessageResetButton, archivedMessageResetButtonName);
		
		//Enter the 'Sender' search criteria
		reportLoggerMethods.sendKeys(archivedMessageSearchSenderField, archivedMessageSearchSenderFieldName, sender);
		
		//Enter the 'Message' search criteria
		reportLoggerMethods.sendKeys(archivedMessageSearchMessageField, archivedMessageSearchMessageFieldName, message);
		
		//Enter the 'Start Date' search criteria
		reportLoggerMethods.sendKeys(archivedMessageSearchStartDateField, archivedMessageSearchStartDateFieldName, startDate);
		
		//Enter the 'End Date' search criteria
		reportLoggerMethods.sendKeys(archivedMessageSearchEndDateField, archivedMessageSearchEndDateFieldName, endDate);
		
		//Click the 'Search' button
		reportLoggerMethods.click(archivedMessageSearchButton, archivedMessageSearchButtonName);
	}
	
	public void verifyArchivedMessageSearchResultSenderLabel(SoftAssert softAssert, String expectedSender) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result's 'Sender' info matches expectation(s)");
		
		//Check if Search Results are present
		if (reportLoggerMethods.isDisplayed(activeMessageResetButton, expectedSender)) {
			//Initialize Variable(s)
			String searchResultSender = reportLoggerMethods.getText(archivedMessageSearchResultSenderLabel, archivedMessageSearchResultSenderLabelName);
			
			//Assert the expected search result's sender
			softAssert.assertEquals(searchResultSender, expectedSender);
			
			//Report the assert status to the Extent Report & System
			if (searchResultSender.equalsIgnoreCase(expectedSender)) {
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result's 'Sender' info matches expectations");
			} else {
				reportLoggerMethods.reportFailedCheckpoint("Failure: The actual 'Sender' info (" + searchResultSender + ") does not match expectations (" + expectedSender + ")", "verifyArchivedMessageSearchResultSenderLabel");
			}
		} else {
			//Assert the expected search result's sender
			softAssert.assertEquals("There are no search result(s)", "Expected at least one search result");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are no search result(s)", "verifyArchivedMessageSearchResultSenderLabel");
		}
	}
	
	public void verifyArchivedMessageSearchResultMessageLabel(SoftAssert softAssert, String expectedMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result's 'Message' info matches expectation(s)");
		
		//Check if Search Results are present
		if (reportLoggerMethods.isDisplayed(archivedMessageSearchResultMessageLabel, archivedMessageSearchResultMessageLabelName)) {
			//Initialize Variable(s)
			String searchResultMessage = reportLoggerMethods.getText(archivedMessageSearchResultMessageLabel, archivedMessageSearchResultMessageLabelName);
			
			//Checking if the search result's 'Message' info matches expectation(s)
			if (searchResultMessage.contains(expectedMessage)) {
				//Assert the expected search result's message
				softAssert.assertEquals("The search result's 'Message' info matches expectation(s)", "The search result's 'Message' info matches expectation(s)");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result's 'Message' info matches expectation(s)");
			} else {
				//Assert the expected search result's message
				softAssert.assertEquals(searchResultMessage, expectedMessage);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The search result's 'Message' info (" + searchResultMessage + ") does not match expectation(s) (" + expectedMessage + ")", "verifyArchivedMessageSearchResultMessageLabel");
			}
		} else {
			//Assert the expected search result's sender
			softAssert.assertEquals("There are no search result(s)", "Expected at least one search result");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are no search result(s)", "verifyArchivedMessageSearchResultMessageLabel");
		}
	}
	
	public void verifyArchivedMessageSearchResultArchivedDateLabel(SoftAssert softAssert, String expectedArchivedDate) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result's 'Date Archived' info matches expectation(s)");
		
		//Check if Search Results are present
		if (reportLoggerMethods.isDisplayed(archivedMessageSearchResultDateArchivedLabel, archivedMessageSearchResultDateArchivedLabelName)) {
			//Initialize Variable(s)
			String searchResultDateArchived = reportLoggerMethods.getText(archivedMessageSearchResultDateArchivedLabel, archivedMessageSearchResultDateArchivedLabelName);
			
			//Checking if the search result's 'Date Archived' info matches expectation(s)
			if (searchResultDateArchived.contains(expectedArchivedDate)) {
				//Assert the expected search result's message
				softAssert.assertEquals("The search result's 'Date Archived' info matches expectation(s)", "The search result's 'Date Archived' info matches expectation(s)");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The search result's 'Date Archived' info matches expectation(s)");
			} else {
				//Assert the expected search result's message
				softAssert.assertEquals(searchResultDateArchived, expectedArchivedDate);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The actual search result's 'Date Archived' info (" + searchResultDateArchived + ") does not match expectation(s) (" + expectedArchivedDate + ")", "verifyArchivedMessageSearchResultArchivedDateLabel");
			}
		} else {
			//Assert the expected search result's sender
			softAssert.assertEquals("There are no search result(s)", "Expected at least one search result");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: There are no search result(s)", "verifyArchivedMessageSearchResultArchivedDateLabel");
		}
	}
	
}