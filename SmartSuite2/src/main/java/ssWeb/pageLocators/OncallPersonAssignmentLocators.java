package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallPersonAssignmentLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallPersonAssignmentLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:personId")
	protected WebElement messagingIDSearchField;
	protected String messagingIDSearchFieldName = "Person Assignment's 'Messaging ID' Search Field";
	
	@FindBy(id="mainForm:submitButton")
	protected WebElement searchButton;
	protected String searchButtonName = "Person Assignment's 'Search' Button";
	
	@FindBy(id="mainForm:assignmentListTable:0:messagingId")
	protected WebElement firstSearchMsgIDResult;
	protected String firstSearchMsgIDResultName = "Person Assignment's First Search Result's 'Msg ID' Label";
	
}