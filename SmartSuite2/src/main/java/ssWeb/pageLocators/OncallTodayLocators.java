package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallTodayLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallTodayLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:j_id_t_1_input")
	protected WebElement searchDateField;
	protected String searchDateFieldName = "Oncall Today Page's 'Date' Search Field";
	
	@FindBy(id="mainForm:topGrpList")
	protected WebElement selectGroupDropDown;
	protected String selectGroupDropDownName = "Oncall Today Page's 'Group' Search Drop-Down";
	
	@FindBy(id="mainForm:searchid")
	protected WebElement searchField;
	protected String searchFieldName = "Oncall Today Page's General Search Field";
	
	@FindBy(xpath="//span[text()='Search']")
	protected WebElement searchButton;
	protected String searchButtonName = "Oncall Today Page's 'Search' Search Button";
	
	@FindBy(xpath="//input[@id='mainForm:current_only_cbox']")
	protected WebElement showCurrentAssignmentsOnlyCheckbox;
	protected String showCurrentAssignmentsOnlyCheckboxName = "'Show Current Assignments Only' Checkbox for Search Criteria";
	
	@FindBy(xpath="//a[@title='Click to view directory/current on call information for this person']")
	protected WebElement firstSearchResult;
	protected String firstSearchResultName = "Oncall Today Page's First Search Result (Name & Msg ID Label)";
	
}