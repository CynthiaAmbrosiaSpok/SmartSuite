package ssWeb.pageLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class PersonalProfile_DeviceManagementLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PersonalProfile_DeviceManagementLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Email Methods ~~~ //
	
	@FindBy(xpath="(//span[text()='Save'])[3]")
	protected WebElement saveModifiedEmailEntryButton;
	protected String saveModifiedEmailEntryButtonName = "'Save Email' Button";
	
	@FindBy(xpath="//span[text()='Delete']")
	protected WebElement deleteExistingEmailEntryButton;
	protected String deleteExistingEmailEntryButtonName = "'Delete Email' Button";
	
	//Existing Emails
	public WebElement existingEmailCheckbox(int row) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:j_id_u_r:" + row + ":chkSelected'] | //input[@id='mainForm:j_id_v_r:" + row + ":chkSelected']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + existingEmailCheckboxName(row) + "\" was not located", "existingEmailCheckbox");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + existingEmailCheckboxName(row) + "\" was not located", "The WebElement \"" + existingEmailCheckboxName(row) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String existingEmailCheckboxName = "Existing Email's 'Checkbox'";
	protected String existingEmailCheckboxName(int i) {
		return "Existing Email Row #" + i + "'s 'Checkbox'";
	}
	
	public WebElement existingEmailOrderField(int row) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:j_id_u_r:" + row + ":OAdd'] | //input[@id='mainForm:j_id_v_r:" + row + ":OAdd']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + existingEmailOrderFieldName(row) + "\" was not located", "existingEmailCheckbox");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + existingEmailOrderFieldName(row) + "\" was not located", "The WebElement \"" + existingEmailOrderFieldName(row) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	public boolean existingEmailOrderFieldDisplay(int row) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:j_id_u_r:" + row + ":OAdd'] | //input[@id='mainForm:j_id_v_r:" + row + ":OAdd']")).isDisplayed();
		} catch (Exception e) {
			//Return null, since no WebElement was located
			return false;
		}
	}
	protected String existingEmailOrderFieldName = "Existing Email's 'Order' Field";
	protected String existingEmailOrderFieldName(int i) {
		return "Existing Email Row #" + i + "'s 'Order' Field";
	}
	
	public WebElement existingEmailAddressField(int row) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:j_id_u_r:" + row + ":EAdd'] | //input[@id='mainForm:j_id_v_r:" + row + ":EAdd']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + existingEmailCheckboxName(row) + "\" was not located", "existingEmailCheckbox");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + existingEmailCheckboxName(row) + "\" was not located", "The WebElement \"" + existingEmailCheckboxName(row) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String existingEmailAddressFieldName = "Existing Email's 'Email Address' Field";
	protected String existingEmailAddressFieldName(int i) {
		return "Existing Email Row #" + i + "'s 'Email Address' Field";
	}
	
	//Create new Email
	@FindBy(xpath="//span[contains(text(), 'New')]")
	protected WebElement newEmailButton;
	protected String newEmailButtonName = "'New Email' Button";
		
		@FindBy(id="mainForm:Order")
		protected WebElement newEmailOrderField;
		protected String newEmailOrderFieldName = "Creating New Email 'Order' Field";
		
		@FindBy(id="mainForm:InpE")
		protected WebElement newEmailAddressField;
		protected String newEmailAddressFieldName = "Creating New Email 'Email Address' Field";
		
		@FindBy(xpath="//a[@id='mainForm:j_id_u_1p'] | //a[@id='mainForm:j_id_v_1p']")
		protected WebElement newEmailSaveButton;
		protected String newEmailSaveButtonName = "Creating New Email 'Save' Button";
		
	// ~~~ 'Send to all devices' Methods ~~~ //
	
	@FindBy(xpath="//input[@id='mainForm:j_id_s_8'] | //input[@id='mainForm:j_id_t_8']")
	protected WebElement sendToAllDevicesCheckbox;
	protected String sendToAllDevicesCheckboxName = "'Send to All Devices' Checkbox";
	
	@FindBy(xpath="//a[@id='mainForm:j_id_s_b'] | //a[@id='mainForm:j_id_t_b']")
	protected WebElement sendToAllDevicesSaveButton;
	protected String sendToAllDevicesSaveButtonName = "'Send to All Devices' Save Button";
	
}