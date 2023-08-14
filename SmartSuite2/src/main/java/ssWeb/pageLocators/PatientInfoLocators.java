package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class PatientInfoLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PatientInfoLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:patientId")
	protected WebElement searchPatientIDField;
	protected String searchPatientIDFieldName = "'Patient ID' Search Field";
	
	@FindBy(id="mainForm:patientName")
	protected WebElement searchPatientNameField;
	protected String searchPatientNameFieldName = "'Patient Name' Search Field";
	
	@FindBy(id="mainForm:patientDOB")
	protected WebElement searchPatientDateOfBirthField;
	protected String searchPatientDateOfBirthFieldName = "'Date of Birth' Search Field";
	
	@FindBy(xpath="//span[contains(text(), 'Search')]")
	protected WebElement searchButton;
	protected String searchButtonName = "'Perform Search' Button";
	
	// ~~~ Search Results ~~~ //
	
	@FindBy(xpath="(//td[@class='dr-table-cell'])[1]//a")
	protected WebElement firstSearchResultDetailButton;
	protected String firstSearchResultDetailButtonName = "First 'Patient Info' Search Result's 'Patient Name' Label";
	
	@FindBy(xpath="//*[text()='Name:']/parent::td/following-sibling::td[1]/input")
	protected WebElement patientDetailPageNameField;
	protected String patientDetailPageNameFieldName = "'Patient Info' Search Result's 'Patient Name' Field in the Detailed Page";
	
	@FindBy(xpath="(//input[@value='Close'])[2]")
	protected WebElement closeSearchResultDetailPopupButton;
	protected String closeSearchResultDetailPopupButtonName = "'Patient Info' Search Result's 'Close Pop-up' Button in the Detailed Page";
	
}