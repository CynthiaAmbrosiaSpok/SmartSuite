package ssWeb.pageMethods;

import java.io.File;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PersonalProfile_PictureUploadLocators;

public class PersonalProfile_PictureUploadPage extends PersonalProfile_PictureUploadLocators {
	
	//Constructor
	public PersonalProfile_PictureUploadPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	/* Profile Tab */
	
	public void deleteUploadedPicture() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting any existing, uploaded picture");
		
		//Delete any existing, uploaded picture
		reportLoggerMethods.click(deleteButton, deleteButtonName);
	}
	
	public void uploadFile(String filePath) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Uploading a file under the 'Personal Profile -> Picture Upload' tab");
		
		//Initialize Variable(s
		String formattedFilePath = System.getProperty("user.dir");
		
		//Add a separator at the beginning, if one does not already exist
		if (filePath.charAt(0) != '/' && filePath.charAt(0) != '\\') {
			formattedFilePath += File.separator;
		}
		
		//Reformat the file path with proper / or \ values
		for (int i = 0; i < filePath.length(); i++) {
			if (filePath.charAt(i) == '/' || filePath.charAt(i) == '\\') {
				formattedFilePath += File.separator;
			} else {
				formattedFilePath += filePath.charAt(i);
			}
		}
		
		//Send a file to the 'Picture Upload' button
		reportLoggerMethods.sendKeys(uploadFileButton, uploadFileButtonName, formattedFilePath);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Click the 'Save' button to upload the file
		reportLoggerMethods.click(saveButton, saveButtonName);
	}
	
	public void verifyPictureUploadMessage(SoftAssert softAssert, String pictureUploadMessage) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the desired 'Picture Upload' message appeared");
		
		//Check if the Upload Message is present
		if (reportLoggerMethods.isDisplayed(pictureUploadResponseMessageLabel, pictureUploadResponseMessageLabelName)) {
			//Initialize Variable(s)
			String pictureUploadResponseMessage = reportLoggerMethods.getText(pictureUploadResponseMessageLabel, pictureUploadResponseMessageLabelName);
			
			//Check if the expected message is being displayed
			if (pictureUploadResponseMessage.equalsIgnoreCase(pictureUploadMessage)) {
				//Assert the status of the displayed error message
				softAssert.assertEquals("Expected message appeared", "Expected message appeared");
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The expected message appeared (" + pictureUploadResponseMessage + ")");
			} else {
				//Assert the status of the displayed message
				softAssert.assertEquals(pictureUploadResponseMessage, pictureUploadMessage);
				
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: The message that appeared (" + pictureUploadResponseMessage + ") does not match expectations (" + pictureUploadMessage + ")", "verifyPictureUploadMessage");
			}
		} else {
			//Assert the status of the displayed error message
			softAssert.assertEquals("No message appeared", pictureUploadMessage);
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: No message appeared, but expected '" + pictureUploadMessage + "'", "verifyPictureUploadMessage");
		}
	}
	
}