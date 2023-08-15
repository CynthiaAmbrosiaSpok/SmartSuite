package ezNotify.pageLocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.ExcelMethods;
import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class EZ_ActivateEventLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public EZ_ActivateEventLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Navigate Pages ~~~ //
	
	@FindBy(xpath="//img[@title='Activate existing event']")
	protected WebElement activateEventButton;
	protected String activateEventButtonName = "'Activate Existing Event' Button";
	
	// ~~~ Search Event Templates ~~~ //
	
	@FindBy(id="search")
	protected WebElement searchField;
	protected String searchFieldName = "Select an Event's 'Search' Field";
	
	@FindBy(xpath="//select[@id='bodyForm:TaskEventList']//option")
	protected List<WebElement> eventTemplatesList;
	protected String eventTemplatesListName = "Select an Event's 'Search Results' List";
	protected String eventTemplatesListName(int i) {
		return "Select an Event's 'Search Result #" + i + "' Entry";
	}
	
	@FindBy(xpath="//select[@id='bodyForm:TaskEventList']//option[@style='display: list-item;']")
	protected List<WebElement> eventTemplateSearchResultsList;
	protected String eventTemplateSearchResultsListName = "Select an Event's 'Search Results' List";
	protected String eventTemplateSearchResultsListName(int i) {
		return "Select an Event's 'Search Result #" + i + "' Entry";
	}
	
	// ~~~ Event Template Fields~~~ //
	
	@FindBy(id="bodyForm:messageText")
	protected WebElement editMessageField;
	protected String editMessageFieldName = "'Enter/Edit Message' Field";
	
	@FindBy(id="bodyForm:shortMessageText")
	protected WebElement editShortMessageField;
	protected String editShortMessageFieldName = "'Enter/Edit Short Message' Field";
	
	@FindBy(id="bodyForm:eventremarkemp")
	protected WebElement eventRemarkField;
	protected String eventRemarkFieldName = "'Event Remark' Field";
	
	@FindBy(id="bodyForm:ActivateExistingButton")
	protected WebElement activateNewEventButton;
	protected String activateNewEventButtonName = "Activate Existing Event Button";
	
	// ~~~ Event Template Fields~~~ //
	
	@FindBy(id="bodyForm:OptionsExistingButton")
	protected WebElement additionalOptionsButton;
	protected String additionalOptionsButtonName = "'Additional Options' Button";
	
	// Procedure 1 //
	
	@FindBy(xpath="//a[text()='Procedure 1']")
	protected WebElement procedure1Button;
	protected String procedure1ButtonName = "'Procedure 1' Button";
	
	@FindBy(id="bodyForm:eventDuration")
	protected WebElement eventDurationField;
	protected String eventDurationFieldName = "'Event Duration' Field";
	
	@FindBy(id="bodyForm:j_id_47_5_8_2:0")
	protected WebElement deviceEscalationRadioButton;
	protected String deviceEscalationRadioButtonName = "'Use Device Escalation' Radio Button";
	
	@FindBy(id="bodyForm:j_id_47_5_8_2:1")
	protected WebElement notifyAllDevicesSimultaneouslyRadioButton;
	protected String notifyAllDevicesSimultaneouslyRadioButtonName = "'Notify all devices simultaneously' Radio Button";
	
	@FindBy(id="bodyForm:j_id_47_5_9_2:0")
	protected WebElement useRecipientsDeviceListCheckbox;
	protected String useRecipientsDeviceListCheckboxName = "'Use recipient's device list (if available)' Checkbox";
	
	@FindBy(id="bodyForm:selectStatus")
	protected WebElement procedureDeviceDropDown;
	protected String procedureDeviceDropDownName = "'Select Procedure Device List' Drop-down";
	
	@FindBy(xpath="//select[@id='bodyForm:selectStatus']//option[@selected='selected']")
	protected WebElement procedureDeviceSelectedDropDown;
	protected String procedureDeviceSelectedDropDownName = "'Select Procedure Device List' Drop-down";
	
	@FindBy(id="bodyForm:j_id_47_5_c_8")
	protected WebElement viewProcedureDevicesButton;
	protected String viewProcedureDevicesButtonName = "'View Devices for the selected Procedure Device List' Button";
	
	@FindBy(xpath="(//input[@value='Cancel and Close'])[2]")
	protected WebElement closeViewProcedureDevicesPopupButton;
	protected String closeViewProcedureDevicesPopupButtonName = "'View Devices for the selected Procedure Device List' Button";
	
	@FindBy(id="bodyForm:ProcedureExistingSaveButton")
	protected WebElement saveProcedureInfoButton;
	protected String saveProcedureInfoButtonName = "'Save Procedure Information' Button";
	
	@FindBy(id="bodyForm:ProcedureExistingCancelButton")
	protected WebElement cancelProcedureInfoButton;
	protected String cancelProcedureInfoButtonName = "'Save Procedure Information' Button";
	
	@FindBy(id="bodyForm:OptionsExistingBackButton")
	protected WebElement exitProcedureInfoPageButton;
	protected String exitProcedureInfoPageButtonName = "'Navigate out of the 'Pocedure Info Page'' Button";
	
	// Step 1 //
	
	@FindBy(xpath="//a[text()='Step 1']")
	protected WebElement step1Button;
	protected String step1ButtonName = "'Step 1' Button";
	
	@FindBy(xpath="//input[@value='NONE']")
	protected WebElement responseTypeNoneRadioButton;
	protected String responseTypeNoneRadioButtonName = "Response Type's 'None' Button";
	
	@FindBy(xpath="//input[@value='CONFIRMATION']")
	protected WebElement responseTypeConfirmationRadioButton;
	protected String responseTypeConfirmationRadioButtonName = "Response Type's 'Confirmation' Button";
	
	@FindBy(xpath="//input[@value='YESNO']")
	protected WebElement responseTypeYesNoQuestionRadioButton;
	protected String responseTypeYesNoQuestionRadioButtonName = "Response Type's 'Yes/No Question' Button";
	
	@FindBy(xpath="//textarea[@id='bodyForm:QueryText']")
	protected WebElement responseTypeYesNoQuestionTextarea;
	protected String responseTypeYesNoQuestionTextareaName = "Response Type's 'Yes/No Question' Textarea";
	
}