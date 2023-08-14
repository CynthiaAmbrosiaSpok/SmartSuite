package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.MainPageLocators;

public class MainPage extends MainPageLocators {
	//Constructor
	public MainPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Access Specified Website ~~~ //
	
	public void accessWebsite(String website) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the website -> " + website);
		
		//Access the specified website
		for (int i = 0; i < 3; i++) {
			try {
				eDriver.get(website);
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	// ~~~ Navigate Tabs ~~~ //
	
	public void clickDirectorySearchTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Directory Search' tab");
		
		//Navigate to the 'Directory Search' tab
		if (reportLoggerMethods.isDisplayed(directorySearchTab, directorySearchTabName)) {
			reportLoggerMethods.click(directorySearchTab, directorySearchTabName);
		} else {
			reportLoggerMethods.click(directorySearchTab2, directorySearchTab2Name);
		}
	}
	
	public void clickGlobalSearchTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Global Search' tab");
		
		//Navigate to the 'Global Search' tab
		reportLoggerMethods.click(globalSearchTab, globalSearchTabName);
	}
	
	public void clickGlobalSearchTab2() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Global Search' tab");
		
		//Navigate to the 'Global Search' tab
		reportLoggerMethods.click(globalSearchTab2, globalSearchTab2Name);
	}
	
	public void refreshGlobalSearchTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Refreshing the 'Global Search' tab");
		
		//Refresh the 'Global Search' tab
		reportLoggerMethods.click(globalSearchTab2, globalSearchTab2Name);
	}
	
	public void clickOncallTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall' tab");
		
		//Navigate to the 'Oncall' tab
		if (reportLoggerMethods.isDisplayed(oncallTab, oncallTabName)) {
			reportLoggerMethods.click(oncallTab, oncallTabName);
		} else {
			reportLoggerMethods.click(oncallTab2, oncallTab2Name);
		}
	}
	
	public void clickBlockAssignmentTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall Block Assignment' tab");
		
		//Navigate to the 'Block Assignment' tab
		reportLoggerMethods.click(blockAssignmentTab, blockAssignmentTabName);
	}
	
	public void clickCalenderAssignmentTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall Calender Assignment' tab");
		
		//Navigate to the 'Calender Assignment' tab
		reportLoggerMethods.click(calenderAssignmentTab, calenderAssignmentTabName);
	}
	
	public void clickHistoryTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall History' tab");
		
		//Navigate to the 'History' tab
		reportLoggerMethods.click(historyTab, historyTabName);
	}
	
	public void clickPersonAssignmentTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Person Assignment' tab");
		
		//Navigate to the 'Person Assignment' tab
		reportLoggerMethods.click(personAssignmentTab, personAssignmentTabName);
	}
	
	public void clickQuickViewTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall Quick View' tab");
		
		//Navigate to the 'Quick View' tab
		reportLoggerMethods.click(quickViewTab, quickViewTabName);
	}
	
	public void clickSchedulerAssignmentTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall Scheduler Assignment' tab");
		
		//Navigate to the 'Scheduler Assignment' tab
		reportLoggerMethods.click(schedulerAssignmentTab, schedulerAssignmentTabName);
	}
	
	public void refreshSchedulerAssignmentPage() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Refreshing the 'Scheduler Assignment' page");
		
		//Refresh the current page
		reportLoggerMethods.refreshPage();
		
		//Re-navigate to the 'Scheduler Assignment' page
		reportLoggerMethods.click(schedulerAssignmentTab, schedulerAssignmentTabName);
	}
	
	public void clickSearchTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall Search' tab");
		
		//Navigate to the 'Oncall Search' tab
		reportLoggerMethods.click(searchTab, searchTabName);
	}
	
	public void clickTodayTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Oncall Today' tab");
		
		//Navigate to the 'Oncall Today' tab
		reportLoggerMethods.click(todayTab, todayTabName);
	}
	
	public void clickPagingTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Paging' tab");
		
		//Navigate to the 'Paging' tab
		reportLoggerMethods.click(pagingTab, pagingTabName);
	}
	
	public void clickPatientInfoTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Patient Info' tab");
		
		//Navigate to the 'Patient Info' tab
		reportLoggerMethods.click(patientInfoTab, patientInfoTabName);
	}
	
	public void clickPersonalProfileTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Personal Profile' tab");
		
		//Navigate to the 'Personal Profile' tab
		if (reportLoggerMethods.isDisplayed(personalProfileTab, personalProfileTabName)) {
			reportLoggerMethods.click(personalProfileTab, personalProfileTabName);
		} else {
			reportLoggerMethods.click(personalProfileTabBackup, personalProfileTabBackupName);
		}
	}
	
	public void clickDeviceManagementTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Device Management' tab");
		
		//Navigate to the 'Device Management' tab
		reportLoggerMethods.click(deviceManagementTab, deviceManagementTabName);
	}
	
	public void clickInboxTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Inbox' tab");
		
		//Navigate to the 'Inbox' tab
		reportLoggerMethods.click(inboxTab, inboxTabName);
	}
	
	public void clickPictureUploadTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Picture Upload' tab");
		
		//Navigate to the 'Picture Upload' tab
		reportLoggerMethods.click(pictureUploadTab, pictureUploadTabName);
	}
	
	public void clickProfileTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Profile' tab");
		
		//Navigate to the 'Profile' tab
		reportLoggerMethods.click(profileTab, profileTabName);
	}
	
	public void clickQuickViewSettingsTab() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Navigating to the 'Quick View Settings' tab");
		
		//Navigate to the 'Quick View Settings' tab
		reportLoggerMethods.click(quickViewSettingsTab, quickViewSettingsTabName);
	}
	
	// ~~~ Verify Current Page ~~~ //
	
	public void verifyActiveDirectoryPage(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the 'Directory Page' is the current page");
		
		//Check that the 'Directory Page' is the current page
		try {
			//Assert the status of the current page and Output the assert status to the Extent Report & System
			if (reportLoggerMethods.getAttribute(directorySearchTab, directorySearchTabName, "style").contains("color: white;")) {
				softAssert.assertEquals("The 'Directory Search' page is currently active", "The 'Directory Search' page is currently active");
				reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Advanced Directory Search' navigated to the 'Directory Search' page");
			} else {
				softAssert.assertEquals("The 'Directory Search' page is not currently active", "The 'Directory Search' page is currently active");
				reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Directory Search' page is not currently active", "verifyActiveDirectoryPage");
			}
		} catch (Exception e) {
			//Assert the status of the current page and Output the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Directory Search' page is not currently active", "The 'Directory Search' page is currently active");
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Directory Search' page is not currently active", "verifyActiveDirectoryPage");
		}
	}
	
	public void verifyActivePagingPage(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the 'Paging Search' page is the current page");
		
		//Check that the 'Paging Search' page is the current page
		if (reportLoggerMethods.getAttribute(pagingTab, pagingTabName, "style").contains("color: white;")) {
			//Report the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Paging Search' page is currently active", "The 'Paging Search' page is currently active");
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Paging Search' page is currently active");
		} else {
			//Report the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Paging Search' page is not currently active", "The 'Paging Search' page is currently active");
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Paging Search' page is not currently active", "verifyActivePagingPage");
		}
	}
	
	public void verifyActiveOncallPage(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking that the 'Oncall Page' is the current page");
		
		//Check if the 'Oncall Search' tab is active
		if (reportLoggerMethods.getAttribute(oncallTab, oncallTabName, "style").contains("color: white;")) {
			//Output the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Oncall Search' page is currently active", "The 'Oncall Search' page is currently active");
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The 'Oncall Search' page is currently active");
		} else {
			//Output the assert status to the Extent Report & System
			softAssert.assertEquals("The 'Oncall Search' page is not currently active", "The 'Oncall Search' page is currently active");
			reportLoggerMethods.reportFailedCheckpoint("Failure: The 'Oncall Search' page is not currently active", "verifyActiveOncallPage");
		}
	}
	
	// ~~~ Login & Logout ~~~ //
	
	public void logout() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Logging out of Smart Suite");
		
		//Attempt to logout of Smart Suite
		reportLoggerMethods.click(logoutLink, logoutLinkName);
	}
	
	public void logoutIfAble() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Logging out of Smart Suite");
		
		//Check if a user is currently logged in
		if (reportLoggerMethods.isDisplayed(logoutLink, logoutLinkName)) {
			//Attempt to logout of Smart Suite
			reportLoggerMethods.click(logoutLink, logoutLinkName);
		}
	}
	
	public void login(String userid, String password) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Logging in to Smart Suite");
		
		//Check if the login button exists
		try {
			//Click the 'Login' link to go to the 'Login' page
			loginLink.click();
		} catch (Exception e) {
			try {
				//Check if the user is, already, logged in
				if (reportLoggerMethods.isDisplayed(logoutLink, logoutLinkName)) {
					//Report the assert status to the Extent Report & System
					reportLoggerMethods.reportInfo("The user is already logged in");
					
					//Exit the method due to being logged in previously
					return;
				}
			} catch (Exception f) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: Cannot login due to no login nor logout buttons existing", "login");
				
				//Finish the method with a failed status
				return;
			}
		}
		
		//Enter the user id
		reportLoggerMethods.sendKeys(useridField, useridFieldName, userid);
		
		//Enter the password
		reportLoggerMethods.sendKeys(passwordField, passwordFieldName, password);
		
		//Click 'Login' to attempt to login
		reportLoggerMethods.click(loginButton, loginButtonName);
	}
	
	public void login2(String userid, String password) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Logging in to Smart Suite");
		
		try {
			//Click the 'Login' link to go to the 'Login' page
			loginLink2.click();
		} catch (Exception e) {
			try {
				//Check if the user is, already, logged in
				if (reportLoggerMethods.isDisplayed(logoutLink2, logoutLink2Name)) {
					//Report the assert status to the Extent Report & System
					reportLoggerMethods.reportInfo("The user is already logged in");
					
					//Exit the method due to being logged in previously
					return;
				}
			} catch (Exception f) {
				//Report the assert status to the Extent Report & System
				reportLoggerMethods.reportFailedCheckpoint("Failure: Cannot login due to no login nor logout buttons existing", "login2");
				
				//Finish the method with a failed status
				return;
			}
		}
		
		//Enter the user id
		reportLoggerMethods.sendKeys(useridField, useridFieldName, userid);
		
		//Enter the password
		reportLoggerMethods.sendKeys(passwordField, passwordFieldName, password);
		
		//Click the 'Login' button
		reportLoggerMethods.click(loginButton, loginButtonName);
	}
	
	public void verifyLoginStatus(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying if the user is logged in");
		
		//Verify if the user is logged in
		if (reportLoggerMethods.isDisplayed(logoutLink, logoutLinkName)) {
			//Assert if the user is logged in
			softAssert.assertEquals("The user successfully logged in", "The user successfully logged in");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The user successfully logged in");
		} else {
			//Assert if the user is logged in
			softAssert.assertEquals("The user failed  to log in", "The user successfully logged in");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failed: The user failed  to log in", "verifyAliasSearchResults");
		}
	}
	
	public void verifyLogoutStatus(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying if the user is logged out");
		
		//Verify if the user is logged out
		if (reportLoggerMethods.isDisplayed(loginLink, loginLinkName)) {
			//Assert if the user is logged out
			softAssert.assertEquals("The user is logged out", "The user is logged out");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The user is logged out");
		} else {
			//Assert if the user is logged out
			softAssert.assertEquals("The user is logged in", "The user is logged out");
			
			//Report the assert status to the Extent Report & System
			reportLoggerMethods.reportFailedCheckpoint("Failure: The user is logged in", "verifyLogoutStatus");
		}
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String searchCriteria) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Performing a search with the value: " + searchCriteria);
		
		//Enter search criteria
		reportLoggerMethods.sendKeys(genericSearchField, genericSearchFieldName, searchCriteria);
		
		//Click 'Search' to perform the search
		reportLoggerMethods.click(genericSearchButton, genericSearchButtonName);
	}
	
	// ~~~ Send a Page ~~~ //
	
	public void clickQuickPageLink() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Clicking the 'Quick Page' link to open up the 'Send a Page' pop-up");
		
		//Click the 'Quick Page' link to open up the 'Send a Page' pop-up
		reportLoggerMethods.click(quickPageLink, quickPageLinkName);
	}
	
	public void sendPage(String recipient, String message) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Sending a Page");
		
		//Enter the recipient name
		reportLoggerMethods.sendKeys(recipientIDField, recipientIDFieldName, recipient);
		
		//Focus on the message field
		reportLoggerMethods.click(pageMessageField, pageMessageFieldName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Enter the message
		reportLoggerMethods.sendKeys(pageMessageField, pageMessageFieldName, message);
		
		//Submit the message
		reportLoggerMethods.click(sendPageButton, sendPageButtonName);
		
		//Pause the script for a short bit
		genMethods.waitForMilliseconds(1000);
		
		//Accept the alert/pop-up
		reportLoggerMethods.acceptAlert();
	}
	
}