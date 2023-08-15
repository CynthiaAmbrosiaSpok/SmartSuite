package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallQuickViewLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallQuickViewLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:messagingId")
	protected WebElement messagingIDSearchField;
	protected String messagingIDSearchFieldName = "Oncall Quick View's 'Messaging ID' Search Field";
	
	@FindBy(id="mainForm:submitButton")
	protected WebElement searchButton;
	protected String searchButtonName = "Oncall Quick View's 'Submit' Search Button";
	
	@FindBy(id="mainForm:onCallGroupTree1:0:0:mancrazy")
	protected WebElement firstOncallGroupListing;
	protected String firstOncallGroupListingName = "Oncall Quick View's First 'Group' Search Result Listing";
	
	@FindBy(xpath="//span[@id='mainForm:data:0:messagingId_txt']")
	protected WebElement firstSearchResultMsgIDLabel;
	protected String firstSearchResultMsgIDLabelName = "'Msg ID' Label for the First Oncall Quick View Search Result";
	
	// ~~~ Tree ~~~ //
	
	@FindBy(xpath="//label[text()='Hide Tree ']")
	protected WebElement hideTreeButton;
	protected String hideTreeButtonName = "'Hide Tree' Button";
	
	@FindBy(xpath="//label[text()='Show Tree ']")
	protected WebElement showTreeButton;
	protected String showTreeButtonName = "'Show Tree' Button";
	
	@FindBy(id="hideoncallTreeColumn")
	protected WebElement treeColumn;
	protected String treeColumnName = "'Tree' Column/Section";
	
}