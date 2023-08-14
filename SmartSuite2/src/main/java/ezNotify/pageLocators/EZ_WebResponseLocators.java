package ezNotify.pageLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.ExcelMethods;
import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class EZ_WebResponseLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public EZ_WebResponseLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Login WebElements ~~~ //
	
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
	
	// ~~~ Login WebElements ~~~ //
	public void clickEZNotifyResponseEntry(String requestNumber) {
		try {
			System.out.println("//option[@value='" + requestNumber + "']");
			Thread.sleep(999999999);
			//Selecting the ezNotify Event to respond to
			eDriver.findElement(By.xpath("//option[@value='" + requestNumber + "']")).click();
		} catch (Exception e) {
			//Set the located WebElement's Name
			String ezNotifyEventResponseEntry = "ezNotify Event Request Number #'" + requestNumber + "' Response Entry";
			
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + ezNotifyEventResponseEntry + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + ezNotifyEventResponseEntry + "\" was not located", "The WebElement \"" + ezNotifyEventResponseEntry + "\" was located");
		}
	}
	
	@FindBy(xpath="//span[text()='Next']")
	protected WebElement continueResponseButton;
	protected String continueResponseButtonName = "'Next' to continue with the Web Response, Button";
	
	@FindBy(id="bodyForm:j_id_1z:0")
	protected WebElement respndYesButton;
	protected String respndYesButtonName = "ezNotify Event's 'Yes' Response Radio Button";
	
}