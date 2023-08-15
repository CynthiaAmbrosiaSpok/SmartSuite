package ssWeb.pageLocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class PersonalProfile_InboxLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PersonalProfile_InboxLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Navigate Tabs ~~~ //
	
	@FindBy(xpath="//span[@title='Click to view your current Active Messages']")
	protected WebElement activeMessagesLink;
	protected String activeMessagesLinkName = "'Active Messages' Link at the top-right corner when logged in";
	
	@FindBy(xpath="//span[text()='Go to Archive Message']")
	protected WebElement goToArchivedMessagesLink;
	protected String goToArchivedMessagesLinkName = "'Go to Archive Message' Link";
	
	@FindBy(xpath="//span[text()='Go to Archive Message']")
	protected WebElement goToArchivedMessagesLinkMessage;
	protected String goToArchivedMessagesLinkMessageName = "'Go to Archive Message' Link";
	
	@FindBy(xpath="//span[text()='Go to Active Message']")
	protected WebElement goToActiveMessagesLink;
	protected String goToActiveMessagesLinkName = "'Go to Active Message' Link";
	
	@FindBy(xpath="//span[text()='Go to Active Message']")
	protected WebElement goToActiveMessagesLinkMessage;
	protected String goToActiveMessagesLinkMessageName = "'Go to Active Message' Link";
	
	// ~~~ Active Messages - Search Fields ~~~ //
	
	@FindBy(xpath="(//span[contains(text(), 'Sender: ')]/parent::td/following-sibling::td/input)[1]")
	protected WebElement activeMessageSearchSenderField;
	protected String activeMessageSearchSenderFieldName = "Active Messages 'Sender' Search Field";
	
	@FindBy(xpath="//span[contains(text(), 'Message: ')]/parent::td/following-sibling::td/input")
	protected WebElement activeMessageSearchMessageField;
	protected String activeMessageSearchMessageFieldName = "Active Messages 'Message' Search Field";
	
	@FindBy(id="mainForm:Start_Date_input")
	protected WebElement activeMessageSearchStartDateField;
	protected String activeMessageSearchStartDateFieldName = "Active Messages 'Start Date' Search Field";
	
	@FindBy(id="mainForm:End_Date_input")
	protected WebElement activeMessageSearchEndDateField;
	protected String activeMessageSearchEndDateFieldName = "Active Messages 'End Date' Search Field";
	
	@FindBy(xpath="//span[text()='Search']")
	protected WebElement activeMessageSearchButton;
	protected String activeMessageSearchButtonName = "Active Messages 'Perform Search' Button";
	
	@FindBy(xpath="//span[text()='Reset']")
	protected WebElement activeMessageResetButton;
	protected String activeMessageResetButtonName = "Active Messages 'Reset' Button";
	
	// ~~~ Active Messages - Search Results ~~~ //
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][1]")
	protected WebElement activeMessageSearchResultSenderLabel;
	protected String activeMessageSearchResultSenderLabelName = "First 'Active Messages' Search Result's 'Sender' Label";
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][3]")
	protected WebElement activeMessageSearchResultMessageLabel;
	protected String activeMessageSearchResultMessageLabelName = "First 'Active Messages' Search Result's 'Message' Label";
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][4]")
	protected WebElement activeMessageSearchResultDateEnteredLabel;
	protected String activeMessageSearchResultDateEnteredLabelName = "First 'Active Messages' Search Result's 'Date Entered' Label";
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][1]")
	protected List<WebElement> activeMessagesSenderList;
	protected String activeMessagesSenderListName = "List of Search Results' 'Sender' Labels";
	protected String activeMessagesSenderListName(int i) {
		return "Search Result Row #" + i + "'s 'Sender' Label";
	}
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][2]")
	protected List<WebElement> activeMessagesNameList;
	protected String activeMessagesNameListName = "List of Search Results' 'Sender' Labels";
	protected String activeMessagesNameListName(int i) {
		return "Search Result Row #" + i + "'s 'Sender' Label";
	}
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][3]")
	protected List<WebElement> activeMessagesMessageList;
	protected String activeMessagesMessageListName = "List of Search Results' 'Message' Labels";
	protected String activeMessagesMessageListName(int i) {
		return "Search Result Row #" + i + "'s 'Message' Label";
	}
	
	public void clickArchiveCheckbox(int i) {
		eDriver.findElement(By.id("mainForm:List:" + i + ":chkSelected")).click();
	}
	
	@FindBy(xpath="//span[text()='Archive...']")
	protected WebElement archiveSelectedMessagesButton;
	protected String archiveSelectedMessagesButtonName = "'Archive (the Selected Active Messages)' Button";
	
	// ~~~ Archived Messages - Search Fields ~~~ //
	
	@FindBy(xpath="//span[contains(text(), 'Sender: ')]/parent::td/following-sibling::td[1]/input")
	protected WebElement archivedMessageSearchSenderField;
	protected String archivedMessageSearchSenderFieldName = "Archive Messages 'Sender' Search Field";
	
	@FindBy(xpath="//span[contains(text(), 'Message: ')]/parent::td/following-sibling::td/input")
	protected WebElement archivedMessageSearchMessageField;
	protected String archivedMessageSearchMessageFieldName = "Archive Messages 'Message' Search Field";
	
	@FindBy(id="mainForm:Start_Date_input")
	protected WebElement archivedMessageSearchStartDateField;
	protected String archivedMessageSearchStartDateFieldName = "Archive Messages 'Start Date' Search Field";
	
	@FindBy(id="mainForm:End_Date_input")
	protected WebElement archivedMessageSearchEndDateField;
	protected String archivedMessageSearchEndDateFieldName = "Archive Messages 'End Date' Search Field";
	
	@FindBy(xpath="//span[text()='Search']")
	protected WebElement archivedMessageSearchButton;
	protected String archivedMessageSearchButtonName = "Archive Messages 'Perform Search' Button";
	
	@FindBy(xpath="//span[text()='Reset']")
	protected WebElement archivedMessageResetButton;
	protected String archivedMessageResetButtonName = "Archive Messages 'Reset' Button";
	
	// ~~~ Archived Messages - Search Results ~~~ //
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][2]")
	protected WebElement archivedMessageSearchResultSenderLabel;
	protected String archivedMessageSearchResultSenderLabelName = "First 'Archive Messages' Search Result's 'Sender' Label";
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][4]")
	protected WebElement archivedMessageSearchResultMessageLabel;
	protected String archivedMessageSearchResultMessageLabelName = "First 'Archive Messages' Search Result's 'Message' Label";
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell a4jSkinTableCell'][6]")
	protected WebElement archivedMessageSearchResultDateArchivedLabel;
	protected String archivedMessageSearchResultDateArchivedLabelName = "First 'Archive Messages' Search Result's 'Date Archived' Label";
	
}