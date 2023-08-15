package ezNotify.pageLocators;

import java.util.List;

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

public class EZ_ExistingEventLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public EZ_ExistingEventLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	@FindBy(xpath="//img[@title='Create new event']")
	protected WebElement createEventButton;
	protected String createEventButtonName = "'Create New Event' Button";
	
	// ~~~ Existing Event ~~~ //
	
	protected List<WebElement> existingEventsButtonList = null;
	protected String existingEventsName = "";
	protected String existingEventsButtonListName = "Existing List of ezNotify Events Named '" + existingEventsName + "' Button";
	protected String existingEventsButtonListName(int eventNameListNumber) {
		return "Existing Event Named '" + existingEventsName + "' Entry #" + eventNameListNumber + " Button";
	}
	
	public void setExistingEventButtonList(String eventName) {
		try {
			//Set the Name for the List of Existing Events being Located
			existingEventsName = eventName;
			
			//Set the located WebElement
			existingEventsButtonList = eDriver.findElements(By.xpath("//a[text()='" + eventName + "']"));
		} catch (Exception e) {
			//Set the located WebElement's Name
			String setExistingEventButtonList = "List of Existing ezNotify Events Named '" + eventName + "' Button";
			
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + setExistingEventButtonList + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + setExistingEventButtonList + "\" was not located", "The WebElement \"" + setExistingEventButtonList + "\" was located");
		}
	}
	public int getExistingEventButtonListSize(String eventName) {
		try {
			//Return the Specific ezNotify Events List Size
			return eDriver.findElements(By.xpath("//a[text()='" + eventName + "']")).size();
		} catch (Exception e) {
			//Return the Specific ezNotify Events List Size
			return 0;
		}
	}
	
	public String existingEventStatusLabel(String eventName) {
		try {
			//Set the located WebElement
			return eDriver.findElements(By.xpath("//a[text()='" + eventName + "']//parent::td//preceding-sibling::td//img")).get(0).getAttribute("src");
		} catch (Exception e) {
			//Set the located WebElement's Name
			String setExistingEventButtonList = "List of Existing ezNotify Event 'Status' Label for Events Named '" + eventName + "'";
			
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + setExistingEventButtonList + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + setExistingEventButtonList + "\" was not located", "The WebElement \"" + setExistingEventButtonList + "\" was located");
		}
		
		return "";
	}
	
	// ~~~ Existing Event Details Page ~~~ //
	
	@FindBy(id="bodyForm:j_id_4f")
	protected WebElement eventBaseInfoLink;
	protected String eventBaseInfoLinkName = "Event Details Page's 'Base Info' Link";
	
	@FindBy(id="bodyForm:j_id_51_c_5")
	protected WebElement requestNumberField;
	protected String requestNumberFieldName = "ezNotify Event's 'Request Number' Field";
	
	@FindBy(xpath="//td[contains(text(), 'Event Name:')]")
	protected WebElement eventNameLabel;
	protected String eventNameLabelName = "Event Details Page's 'Event Name' Label";
	
	@FindBy(xpath="//td[contains(text(), 'Event Status:')]")
	protected WebElement eventStatusLabel;
	protected String eventStatusLabelName = "Event Details Page's 'Event Status' Label";
	
	@FindBy(xpath="//td[contains(text(), 'Last Refresh:')]")
	protected WebElement eventLastRefreshDateLabel;
	protected String eventLastRefreshDateLabelName = "Event Details Page's 'Last Refresh Date' Label";
	
	@FindBy(xpath="//td[contains(text(), 'Activated At:')]")
	protected WebElement eventActivatedAtLabel;
	protected String eventActivatedAtLabelName = "Event Details Page's 'Activated At' Label";
	
	@FindBy(xpath="//div[contains(text(), 'Text Message:')]/textarea")
	protected WebElement eventTextMessageTextarea;
	protected String eventTextMessageTextareaName = "Event Details Page's 'Text Message' Textarea";
	
	@FindBy(xpath="//img[@title='Cancel Event']")
	protected WebElement cancelEventButton;
	protected String cancelEventButtonName = "Cancel Existing ezNotify Event Button";
	
	// ~~~ Existing Event Details Page - Procedure 1 Section ~~~ //
	
	@FindBy(xpath="//a[text()='Procedure 1']")
	protected WebElement eventProcedure1Link;
	protected String eventProcedure1LinkName = "Event Details Page's 'Procedure 1' Link";
	
	@FindBy(xpath="//div[@id='bodyForm:j_id_51_6_6_body']/span[2]")
	protected WebElement procedureDeviceListLabel;
	protected String procedureDeviceListLabelName = "Event Details Page's 'Procedure Device List' Label";
	
	@FindBy(id="bodyForm:j_id_51_6_9")
	protected WebElement notifyAllDevicesSimultaneouslyCheckbox;
	protected String notifyAllDevicesSimultaneouslyCheckboxName = "Event Details Page's 'Notified all devices simultaneously' Checkbox";
	
	@FindBy(id="bodyForm:j_id_51_6_d")
	protected WebElement usedRecipientsDeviceListCheckbox;
	protected String usedRecipientsDeviceListCheckboxName = "Event Details Page's 'Used recipient's device list (if available)' Checkbox";
	
	// ~~~ Existing Event Details Page - Step 1 Section ~~~ //
	
	@FindBy(xpath="//a[text()='Step 1']")
	protected WebElement eventStep1Link;
	protected String eventStep1LinkName = "Event Details Page's 'Step 1' Link";
	
	@FindBy(xpath="//td[@class='rf-dt-c tabcolClas'][1]")
	protected List<WebElement> eventRecipientsNameLabelList;
	protected String eventRecipientsNameLabelListName = "Event Details Page's 'Recipient's Name' Label List";
	protected String eventRecipientsNameLabelListName(int eventNameListNumber) {
		return "Event Details Page's #" + eventNameListNumber + " 'Recipient's Name' Label";
	}
	
	public String personResponseLabel(String personName) {
		try {
			//Return the 'Yes' or 'No' for the Person's Response Indicator Label
			return eDriver.findElement(By.xpath("//td[text()='" + personName + "']//following-sibling::td[@id='bodyForm:j_id_51_9_e:0:j_id_51_9_w']")).getText();
		} catch (Exception e) {
			//Set the located WebElement's Name
			String personResponse = "Person named '" + personName + "' Response Indicator (Yes/No) Label";
			
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + personResponse + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + personResponse + "\" was not located", "The WebElement \"" + personResponse + "\" was located");
			
			//Return null, since the WebElement was not found
			return null;
		}
	}
}