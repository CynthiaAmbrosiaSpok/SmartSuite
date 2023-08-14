package ezNotify.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.ExcelMethods;
import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class EZ_LoginLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public EZ_LoginLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Verify Current Page Title ~~~ //
	
	@FindBy(id="username_txt")
	protected WebElement useridField;
	protected String useridFieldName = "Enter 'Username' Field for Login";
	
	@FindBy(id="password_txt")
	protected WebElement passwordField;
	protected String passwordFieldName = "Enter 'Password' Field for Login";
	
	@FindBy(xpath="//img[@title='Log In']")
	protected WebElement loginButton;
	protected String loginButtonName = "'Login' button";
	
	@FindBy(id="logout_link")
	protected WebElement logoutLink;
	protected String logoutLinkName = "Enter 'Password' Field for Login";
	
}