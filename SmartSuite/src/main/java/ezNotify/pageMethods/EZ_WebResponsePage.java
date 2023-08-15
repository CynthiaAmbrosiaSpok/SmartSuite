package ezNotify.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ezNotify.pageLocators.EZ_WebResponseLocators;

public class EZ_WebResponsePage extends EZ_WebResponseLocators {
	
	//Constructor
	public EZ_WebResponsePage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
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
	
	// ~~~ Login & Logout ~~~ //
	
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
		
		//Enter the user id
		reportLoggerMethods.sendKeys(useridField, useridFieldName, userid);
		
		//Enter the password
		reportLoggerMethods.sendKeys(passwordField, passwordFieldName, password);
		
		//Click 'Login' to attempt to login
		reportLoggerMethods.click(loginButton, loginButtonName);
	}
	
	public void loginIfAble(String userid, String password) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Logging in to Smart Suite");
		
		//Login the Uuser if not already logged in
		if (reportLoggerMethods.isDisplayed(useridField, useridFieldName)) {
			//Enter the user id
			reportLoggerMethods.sendKeys(useridField, useridFieldName, userid);
			
			//Enter the password
			reportLoggerMethods.sendKeys(passwordField, passwordFieldName, password);
			
			//Click 'Login' to attempt to login
			reportLoggerMethods.click(loginButton, loginButtonName);
		}
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
			reportLoggerMethods.reportFailedCheckpoint("Failed: The user failed  to log in", "verifyLoginStatus");
		}
	}
	
	public void verifyLogoutStatus(SoftAssert softAssert) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Verifying if the user is logged out");
		
		//Verify if the user is logged out
		if (reportLoggerMethods.isDisplayed(loginButton, loginButtonName)) {
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
	
	// ~~~ Login & Logout ~~~ //
	
	public void selectWebResponse(String requestNumber) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Selecting the ezNotify Event to respond to");
		
		//Select the ezNotify Event to respond to
		clickEZNotifyResponseEntry(requestNumber);
	}
	
	public void submitWebResponse() {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Submiting an ezNotify Event Response");
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(3000);
		
		//Click 'Next' to go to the next page of 'Web Response' process
		reportLoggerMethods.click(continueResponseButton, continueResponseButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click 'Next' to go to the next page of 'Web Response' process
		reportLoggerMethods.click(continueResponseButton, continueResponseButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click 'Next' to go to the next page of 'Web Response' process
		reportLoggerMethods.click(respndYesButton, respndYesButtonName);
		
		//Pause the script for a bit
		genMethods.waitForMilliseconds(1000);
		
		//Click 'Next' to go to the next page of 'Web Response' process
		reportLoggerMethods.click(continueResponseButton, continueResponseButtonName);
	}
}