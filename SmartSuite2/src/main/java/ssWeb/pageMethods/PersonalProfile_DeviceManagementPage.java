package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PersonalProfile_DeviceManagementLocators;

public class PersonalProfile_DeviceManagementPage extends PersonalProfile_DeviceManagementLocators {
	
	//Constructor
	public PersonalProfile_DeviceManagementPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Email Methods ~~~ //
	
	public int retrieveEmailRow(String order, String email) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the expected email is listed");
		
		//Initialize Variable(s)
		int existingEmailRow = 0;
		
		String orderValue = "";
		String emailAddressValue = "";
		
		//Loop through the email list & check for the Order and Email Address values
		while (existingEmailOrderFieldDisplay(existingEmailRow)) {
			//Retrieve the values for the current email listing
			orderValue = reportLoggerMethods.getAttribute(existingEmailOrderField(existingEmailRow), existingEmailOrderFieldName(existingEmailRow), "value");
			emailAddressValue = reportLoggerMethods.getAttribute(existingEmailAddressField(existingEmailRow), existingEmailAddressFieldName(existingEmailRow), "value");
			
			//Check if the current email row's info matches expectations
			if (orderValue.equalsIgnoreCase(order) && emailAddressValue.equalsIgnoreCase(email)) {
				//Return the number of the current row
				return existingEmailRow;
			}
			
			//Focus on the next existing email row
			existingEmailRow++;
		}
		
		//Return an invalid number to indicate that the expected email was not found
		return -1;
	}
	
	public void deleteEmail(String order, String email) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting any existing email that contains the same info as the email being added/modified");
		
		//Initialize Variable(s)
		int emailRow = retrieveEmailRow(order, email);
		
		//Delete any existing email that contains the same info as the email being added/modified
		if (emailRow != -1) {
			//Select the 'Delete' checkbox for the desired email entry
			reportLoggerMethods.click(existingEmailCheckbox(emailRow), existingEmailCheckboxName(emailRow));
			
			//Delete the selected email entry
			reportLoggerMethods.click(deleteExistingEmailEntryButton, deleteExistingEmailEntryButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		}
	}
	
	public void addNewEmail(String order, String email) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Adding a new email");
		
		//Click the 'New (email)' button
		reportLoggerMethods.click(newEmailButton, newEmailButtonName);
		
		//Enter the 'Order' value for the new email
		reportLoggerMethods.clear(newEmailOrderField, newEmailOrderFieldName);
		reportLoggerMethods.sendKeys(newEmailOrderField, newEmailOrderFieldName, order);
		
		//Enter the new email address
		reportLoggerMethods.clear(newEmailAddressField, newEmailAddressFieldName);
		reportLoggerMethods.sendKeys(newEmailAddressField, newEmailAddressFieldName, email);
		
		//Click 'Save' to create the new email
		reportLoggerMethods.click(newEmailSaveButton, newEmailSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void modifyEmail(SoftAssert softAssert, String originalOrder, String originalEmailAddress, String modifyOrder, String modifyEmailAddress) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Modifying an existing email");
		
		//Initialize Variable(s)
		int emailRow = retrieveEmailRow(originalOrder, originalEmailAddress);
		
		//Delete any existing email that contains the same info as the email being added/modified
		if (emailRow != -1) {
			//Enter the new (modified) order
			reportLoggerMethods.clear(existingEmailOrderField(emailRow), existingEmailOrderFieldName(emailRow));
			reportLoggerMethods.sendKeys(existingEmailOrderField(emailRow), existingEmailOrderFieldName(emailRow), modifyOrder);
			
			//Enter the new (modified) email address
			reportLoggerMethods.clear(existingEmailAddressField(emailRow), existingEmailAddressFieldName(emailRow));
			reportLoggerMethods.sendKeys(existingEmailAddressField(emailRow), existingEmailAddressFieldName(emailRow), modifyEmailAddress);
			
			//Click the 'Save' button to modify the email info
			reportLoggerMethods.click(saveModifiedEmailEntryButton, saveModifiedEmailEntryButtonName);
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
			
			//Accept the alert
			reportLoggerMethods.acceptAlert();
			
			//Pause the script for a bit
			genMethods.waitForMilliseconds(1000);
		} else {
			//Assert the values of the existing email row
			softAssert.assertEquals("Email match not found", originalOrder);
			softAssert.assertEquals("Email match not found", originalEmailAddress);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The expected email was not found (with Order = '" + originalOrder + "', and Email Address = '" + originalEmailAddress + "')", "verifyEmailInfo");
		}
	}
	
	public void verifyEmailInfo(SoftAssert softAssert, String order, String email) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the expected email's info matches expectations");
		
		//Initialize Variable(s)
		int emailRow = retrieveEmailRow(order, email);
		
		//Determine if the desired email exists (!= -1), or send an error message)
		if (emailRow != -1) {
			//Initialize Variable(s)
			String successMessage = "Success: The expected email was found";
			String failureMessage = "Failure: ";
			
			String orderValue = reportLoggerMethods.getAttribute(existingEmailOrderField(emailRow), existingEmailOrderFieldName(emailRow), "value");
			String emailAddressValue = reportLoggerMethods.getAttribute(existingEmailAddressField(emailRow), existingEmailAddressFieldName(emailRow), "value");
			
			//Assert the status of the 'Add New Email' section
			softAssert.assertEquals(reportLoggerMethods.isDisplayed(newEmailSaveButton, newEmailSaveButtonName), false);
			
			//Check if the 'Add New Email' section is no longer being displayed
			if (reportLoggerMethods.isDisplayed(newEmailSaveButton, newEmailSaveButtonName)) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The new email was not added (possibly due to a previously existing email with identical info)", "verifyEmailInfo");
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Assert the values of the existing email row
			softAssert.assertEquals(orderValue, order);
			softAssert.assertEquals(emailAddressValue, email);
			
			//Check if the email info matches expectations
			if (orderValue.equalsIgnoreCase(order) && emailAddressValue.equalsIgnoreCase(email)) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint(successMessage);
				
				//Finish the method with an Extent Report status
				return;
			}
			
			//Check if the email order matches expectations
			if (!orderValue.equalsIgnoreCase(order)) {
				failureMessage += "The order (" + orderValue + ") does not match expectations (" + order + "). ";
			}
			
			//Check if the email address matches expectations
			if (!emailAddressValue.equalsIgnoreCase(email)) {
				failureMessage += "The email address (" + emailAddressValue + ") does not match expectations (" + email + "). ";
			}
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint(failureMessage, "verifyEmailInfo");
		} else {
			//Assert the values of the existing email row
			softAssert.assertEquals("Email match not found", order);
			softAssert.assertEquals("Email match not found", email);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The expected email was not found (with Order = '" + order + "', and Email Address = '" + email + "')", "verifyEmailInfo");
		}
	}
	
	public void verifyDeletedEmailEntry(SoftAssert softAssert, String modifyOrder, String modifyEmailAddress) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired email was deleted");
		
		//Initialize Variable(s)
		int emailRow = retrieveEmailRow(modifyOrder, modifyEmailAddress);
		
		//Delete any existing email that contains the same info as the email being added/modified
		if (emailRow != -1) {
			//Assert the values of the existing email row
			softAssert.assertEquals("The email entry was not deleted (found at row #" + (emailRow + 1) + ")", "The email entry was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The expected email was found at row #" + (emailRow + 1) + " (with Order = '" + modifyOrder + "', and Email Address = '" + modifyEmailAddress + "')", "verifyEmailInfo");
		} else {
			//Assert the values of the existing email row
			softAssert.assertEquals("The email entry was deleted", "The email entry was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Sucess: The email entry was deleted");
		}
	}
	
	// ~~~ 'Send to all devices' Methods ~~~ //
	
	public void checkSendToAllDevicesCheckbox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking the 'Send to all devices' checkbox");
		
		//Check the 'Send to all devices' checkbox
		if (!reportLoggerMethods.isSelected(sendToAllDevicesCheckbox, sendToAllDevicesCheckboxName)) {
			reportLoggerMethods.click(sendToAllDevicesCheckbox, sendToAllDevicesCheckboxName);
		}
		
		//Click 'save' to enable the 'Send to all devices' option
		reportLoggerMethods.click(sendToAllDevicesSaveButton, sendToAllDevicesSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
	public void uncheckSendToAllDevicesCheckbox() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking the 'Send to all devices' checkbox");
		
		//Uncheck the 'Send to all devices' checkbox
		if (reportLoggerMethods.isSelected(sendToAllDevicesCheckbox, sendToAllDevicesCheckboxName)) {
			reportLoggerMethods.click(sendToAllDevicesCheckbox, sendToAllDevicesCheckboxName);
		}
		
		//Click 'save' to enable the 'Send to all devices' option
		reportLoggerMethods.click(sendToAllDevicesSaveButton, sendToAllDevicesSaveButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert
		reportLoggerMethods.acceptAlert();
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
	}
	
}