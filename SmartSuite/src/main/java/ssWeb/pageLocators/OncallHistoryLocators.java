package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallHistoryLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallHistoryLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:groupName")
	protected WebElement groupNameField;
	protected String groupNameFieldName = "Oncall History's 'Group Name' Search Field";
	
	@FindBy(id="mainForm:groupId")
	protected WebElement groupIDField;
	protected String groupIDFieldName = "Oncall History's 'Group ID' Search Field";
	
	@FindBy(id="mainForm:startDate.year")
	protected WebElement startDateYearField;
	protected String startDateYearFieldName = "Oncall History's 'Start Year' Search Field";
	
	@FindBy(id="mainForm:submitButton")
	protected WebElement searchButton;
	protected String searchButtonName = "Oncall History's 'Submit' Search Button";
	
	@FindBy(id="mainForm:onCallGroupTree1:0:0:mancrazy")
	protected WebElement searchResultFirstListing;
	protected String searchResultFirstListingName = "Oncall History's First 'Group' Search Result Listing";
	
	@FindBy(id="mainForm:data:0:startDateTime_txt")
	protected WebElement startDateFirstSearchResult;
	protected String startDateFirstSearchResultName = "'Start Date' Label for the First Oncall History Search Result";
	
	// ~~~ Tree ~~~ //
	
	@FindBy(xpath="//label[text()='Hide Tree']")
	protected WebElement hideTreeButton;
	protected String hideTreeButtonName = "'Hide Tree' Button";
	
	@FindBy(xpath="//label[text()='Show Tree']")
	protected WebElement showTreeButton;
	protected String showTreeButtonName = "'Show Tree' Button";
	
	@FindBy(id="hideoncallTreeColumn")
	protected WebElement treeColumn;
	protected String treeColumnName = "'Tree' Column/Section";
	
}