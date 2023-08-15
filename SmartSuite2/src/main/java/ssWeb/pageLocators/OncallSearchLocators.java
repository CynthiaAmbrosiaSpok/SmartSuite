package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallSearchLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallSearchLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:groupName")
	protected WebElement groupNameSearchField;
	protected String groupNameSearchFieldName = "Oncall Search Page's 'Group Name' Search Field";
	
	@FindBy(id="mainForm:groupId")
	protected WebElement groupIDField;
	protected String groupIDFieldName = "Oncall Search Page's 'Group ID' Search Field";
	
	@FindBy(id="mainForm:submitButton")
	protected WebElement searchButton;
	protected String searchButtonName = "Oncall Search Page's 'Search' Search Button";
	
	@FindBy(id="mainForm:onCallGroupTree1:0:0:mancrazy")
	protected WebElement groupSearchResultFirstListing;
	protected String groupSearchResultFirstListingName = "Oncall Search Page's First 'Group' Search Result Listing";
	
}