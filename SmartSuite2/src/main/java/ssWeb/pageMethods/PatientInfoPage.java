package ssWeb.pageMethods;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;

import ssWeb.pageLocators.PatientInfoLocators;

public class PatientInfoPage extends PatientInfoLocators {
	
	//Constructor
	public PatientInfoPage(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		super(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	public void performSearch(String searchCriteria) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Searching for Patient Info");
		
		//Enter the search criteria
		reportLoggerMethods.sendKeys(searchPatientNameField, searchPatientNameFieldName, searchCriteria);
		
		//Click 'Search'
		reportLoggerMethods.click(searchButton, searchButtonName);
	}
	
	public void verifyPatientSearch(SoftAssert softAssert, String expectedName) {
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Checking if the search result(s) matches expectations");
		
		//Bring up the detail page for the first search result
		reportLoggerMethods.click(firstSearchResultDetailButton, firstSearchResultDetailButtonName);
		
		//Assert the expected search result(s)
		softAssert.assertEquals(reportLoggerMethods.getAttribute(patientDetailPageNameField, patientDetailPageNameFieldName, "value"), expectedName);
		
		//Report the assert status to the Extent Report & System
		if (reportLoggerMethods.getAttribute(patientDetailPageNameField, patientDetailPageNameFieldName, "value").equalsIgnoreCase(expectedName)) {
			reportLoggerMethods.reportSuccessfulCheckpoint("Success: The expected search result was found");
		} else {
			reportLoggerMethods.reportFailedCheckpoint("Failure: The Patient name found (" + patientDetailPageNameField.getAttribute("value") + ") does not match the expected Patient name (" + expectedName + ")", expectedName);
		}
	}
	
}