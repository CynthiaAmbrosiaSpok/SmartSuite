package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PersonalProfile_QuickViewSettingsLocators;

public class PersonalProfile_QuickViewSettingsPage extends PersonalProfile_QuickViewSettingsLocators {
	
	//Constructor
	public PersonalProfile_QuickViewSettingsPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	/* Oncall Group */
	
	public void deleteAllOncallGroup() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Deleting all Oncall Groups");
		
		//Delete all Oncall Groups
		while (reportLoggerMethods.isDisplayed(firstOncallGroupListingCheckbox, firstOncallGroupListingCheckboxName)) {
			//Select the first Oncall Groups on the list
			reportLoggerMethods.click(firstOncallGroupListingCheckbox, firstOncallGroupListingCheckboxName);
			
			//Click the 'Remove' button
			reportLoggerMethods.click(removeOncallGroup, removeOncallGroupName);
		}
	}
	
	public void createAndSaveOncallGroup(String groupName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Creating an Oncall Group for " + groupName);
		
		//Check the 'Group Name' checkbox
		reportLoggerMethods.click(firstOncallGroupListingCheckbox(groupName), firstOncallGroupListingCheckboxName(groupName));
		
		//Click to add the new Oncall Group
		reportLoggerMethods.click(addOncallGroup, addOncallGroupName);
		
		//Click to save the new Oncall Group
		reportLoggerMethods.click(saveOncallGroup, saveOncallGroupName);
	}
	
	public void verifyDeletedOncallGroup(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if all Oncall Groups were deleted");
		
		//Check if all Oncall Groups were deleted
		if (reportLoggerMethods.isDisplayed(firstOncallGroupListingCheckbox, firstOncallGroupListingCheckboxName)) {
			//Assert the status of the deleted Oncall Group
			softAssert.assertEquals("Oncall Group was not deleted", "Oncall Group was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Oncall Group was not deleted", "verifyDeletedOncallGroup");
		} else {
			//Assert the status of the deleted Oncall Group
			softAssert.assertEquals("Oncall Group was deleted", "Oncall Group was deleted");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Successful: The Oncall Group was deleted");
		}
	}
	
}