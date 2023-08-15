package personalProfile.Inbox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.PersonalProfile_InboxPage;
import utilityClasses.ExcelMethods;

public class SearchArchivedMessagesTest extends TestBase {
	
	//Define Variable(s)
	SoftAssert checkpoint;
	
	//Constructor
	public SearchArchivedMessagesTest() {
		super();
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Inherit the method from the parent class
		super.performSetup(reportTitle, scriptName);
		
		//Setup PageFactories
		mainPage = new MainPage(eDriver, reportLogger);
		personalProfileInboxPage = new PersonalProfile_InboxPage(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Inherit the method from the parent class
		super.beforeClass();
		
		//Setup the DataTable from Excel
		excelMethods.setDataTablePath(constantVariables.smartSuiteDataTablePath + prop.getProperty("personalProfileInboxDataTable"));
		excelMethods.setSheetName("Search Archived Messages");
	}
	
	@Test(dataProvider="inputs", dataProviderClass=ExcelMethods.class)
	public void testcases_personalProfile_Inbox_SearchArchivedMessagesTest(String active, String reportTitle, String recipient, String pageMessage, String searchSender, String searchMessage, String searchDate, String expectedSender, String expectedMessage, String expectedDate, String dataRow) {
		//Initialize Variable(s)
		checkpoint = new SoftAssert(); //SoftAssert Setup (for identifying checkpoints)
		iteration = Integer.valueOf(dataRow); //Indicates which row of Excel data the @Test is reading & which row to output the results
		
		//Set the 'searchDate' to today's date, if performing a search with the 'Date' value
		if (searchDate.equalsIgnoreCase("y")) {
			DateTimeFormatter searchDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
			LocalDateTime currentTime = LocalDateTime.now();
			searchDate = searchDateFormatter.format(currentTime); //Search Archive Messages with today's date
		} else {
			searchDate = "";
		}
		
		//Set the 'expectedDate' to today's date, if performing a search with the 'Date' value
		if (expectedDate.equalsIgnoreCase("y")) {
//			DateTimeFormatter expectedArchiveDateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm a");
			DateTimeFormatter expectedArchiveDateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			LocalDateTime currentTime2 = LocalDateTime.now();
			expectedDate = expectedArchiveDateFormatter.format(currentTime2);
		} else {
			expectedDate = "";
		}
		
		//If the current row is not an active test row, skip it
		if (active.equalsIgnoreCase("y") || active.equalsIgnoreCase("yes")) {
			//Setup the report & PageFactories
			performSetup(reportTitle, "testcases_PersonalProfile_Inbox_SearchArchivedMessagesTest()");
			
			//Go to the desired website
			mainPage.accessWebsite(activeWebsite);
			
			//Attempt to login to Smart Suite
			mainPage.login(smartwebUsername, encryptionMethods.decryptText(smartwebPassword));
			
			//If the 'recipient' and 'pageMessage' variables have values, then create a new message
			if (!recipient.equalsIgnoreCase("") && !pageMessage.equalsIgnoreCase("")) {
				//Click the 'Quick Page' link to open up the 'Send a Page' pop-up
				mainPage.clickQuickPageLink();
				
				//Pause the script for a short bit
				genMethods.waitForMilliseconds(1000);
				
				//Send a Page
				mainPage.sendPage(recipient, pageMessage);
			}
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(1000);
			
			//Navigate to the 'Personal Profile' tab
			mainPage.clickPersonalProfileTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(3000);
			
			//Navigate to the 'Inbox' tab
			mainPage.clickInboxTab();
			
			//Pause the script for a short bit
			genMethods.waitForMilliseconds(3000);
			
			//If a new message was created, verify its existence & move it to the 'Archive Messages'
			if (!recipient.equalsIgnoreCase("") && !pageMessage.equalsIgnoreCase("")) {
				//Check for the page in the 'Personal Profile -> Inbox' tab
				personalProfileInboxPage.verifyActiveMessage(checkpoint, pageMessage);
			}
			
			//Navigate to the 'Archived Messages' page
			personalProfileInboxPage.goToArchivedMessages();
			
			//Perform a search on the Archived Messages
			personalProfileInboxPage.searchArchivedMessages(searchSender, searchMessage, searchDate, searchDate);
			
			//If 'Sender' search criteria was used, verify the 'Sender' info from search result(s) matches expectations
			if (!searchSender.equals("")) {
				//Check if the search result's 'Sender' info matches expectation(s)
				personalProfileInboxPage.verifyArchivedMessageSearchResultSenderLabel(checkpoint, expectedSender);
			}
			
			//If 'Message' search criteria was used, verify the 'Message' info from search result(s) matches expectations
			if (!searchMessage.equals("")) {
				//Checking if the search result's 'Message' info matches expectation(s)
				personalProfileInboxPage.verifyArchivedMessageSearchResultMessageLabel(checkpoint, expectedMessage);
			}
			
			//If 'Date Archived' search criteria was used, verify the 'Date Archived' info from search result(s) matches expectations
			if (!searchDate.equals("")) {
				//Checking if the search result's 'Date Archived' info matches expectation(s)
				personalProfileInboxPage.verifyArchivedMessageSearchResultArchivedDateLabel(checkpoint, expectedDate);
			}
			
			//Assert all Checkpoints
			checkpoint.assertAll();
		} else {
			System.out.println("Skipped row #" + iteration + " because it is not an active testing row.");
		}
	}
}