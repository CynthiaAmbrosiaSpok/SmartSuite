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

public class EZ_CreateEventLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public EZ_CreateEventLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Base Page(s) ~~~ //
	
	@FindBy(id="bodyForm:mainPanel_body")
	protected WebElement baseEZNotifyPage;
	protected String baseEZNotifyPageName = "ezNotify's Base Page";
	
	@FindBy(xpath="//div[@id='bodyForm:mainPanel_body']//*")
	protected List<WebElement> baseEZNotifyPageChildWebElementsList;
	protected String baseEZNotifyPageChildWebElementsListName = "ezNotify's Base Page";
	
	// ~~~ Create Event Page ~~~ //
	
	@FindBy(xpath="//img[@title='Create new event']")
	protected WebElement createEventButton;
	protected String createEventButtonName = "'Create New Event' Button";
	
	@FindBy(id="bodyForm:eventName")
	protected WebElement eventNameField;
	protected String eventNameFieldName = "'Event Name' Field";
	
	@FindBy(id="bodyForm:messageText")
	protected WebElement eventMessageField;
	protected String eventMessageFieldName = "'Event Message' Field";
	
	@FindBy(id="bodyForm:shortMessageText")
	protected WebElement eventShortMessageField;
	protected String eventShortMessageFieldName = "'Short Message' Field";
	
	@FindBy(xpath="//input[@value='NONE']")
	protected WebElement responseTypeNoneRadioButton;
	protected String responseTypeNoneRadioButtonName = "Response Type's 'None' Radio Button";
	
	@FindBy(xpath="//input[@value='CONFIRMATION']")
	protected WebElement responseTypeConfirmationRadioButton;
	protected String responseTypeConfirmationRadioButtonName = "Response Type's 'Confirmation' Radio Button";
	
	@FindBy(xpath="//input[@value='YESNO']")
	protected WebElement responseTypeYesNoQuestionRadioButton;
	protected String responseTypeYesNoQuestionRadioButtonName = "Response Type's 'Yes/No Question' Radio Button";
	
	@FindBy(xpath="//textarea[@id='bodyForm:QueryText']")
	protected WebElement responseTypeYesNoQuestionTextarea;
	protected String responseTypeYesNoQuestionTextareaName = "Response Type's 'Yes/No Question' Radio Button";
	
	@FindBy(id="bodyForm:searchTypesCheckbox:0")
	protected List<WebElement> recipientsCheckboxes;
	protected String recipientsIndividualsCheckboxName = "Select Recipients' 'Individuals' Checkbox";
	protected String recipientsMessageGroupsCheckboxName = "Select Recipients' 'Message Groups' Checkbox";
	protected String recipientsOnCallGroupsCheckboxName = "Select Recipients' 'OnCall Groups' Checkbox";
	
	@FindBy(id="bodyForm:peopleSearchField")
	protected WebElement recipientsSearchField;
	protected String recipientsSearchFieldName = "Select Recipients' Search Field";
	
	@FindBy(id="bodyForm:emptyRecipientListWarning")
	protected WebElement noRecipientsLabel;
	protected String noRecipientsLabelName = "Select Recipients' Search Field";
	
	@FindBy(id="bodyForm:searchResults:0:j_id_4x")
	protected WebElement firstRecipientSearchResult;
	protected String firstRecipientSearchResultName = "First 'Recipient' Search Result";
	
	@FindBy(id="bodyForm:searchResults:0:j_id_51")
	protected WebElement firstRecipientSearchResultViewDirectoryButton;
	protected String firstRecipientSearchResultViewDirectoryButtonName = "First 'Searched Individuals Recipient' 'View Directory' Button";
		
		@FindBy(xpath="//tbody[@id='bodyForm:j_id_6c:tb']//td[@class='rf-dt-c'][1]")
		protected List<WebElement> directoryTitleLabel;
		protected String directoryTitleLabelName = "'Directory Title' Label List";
		protected String directoryTitleLabelName(int i) {
			return "'Directory Title' Row #" + i + "'s Label";
		}
		
		public WebElement directoryTitleRow(int directoryInfoRowNumber) {
			try {
				//Return the located WebElement
				return eDriver.findElement(By.id("bodyForm:j_id_6c:" + directoryInfoRowNumber + ":j_id_6i"));
			} catch (Exception e) {
				//Output an error about the specified WebElement being unable to be located
				reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + directoryTitleRowName(directoryInfoRowNumber) + "\" was not located", "GlobalSearchPageButton");
				
				//Assert the status of the WebElement's existence
				Assert.assertEquals("The WebElement \"" + directoryTitleRowName(directoryInfoRowNumber) + "\" was not located", "The WebElement \"" + directoryTitleRowName(directoryInfoRowNumber) + "\" was located");
				
				//Return null, since no WebElement was located
				return null;
			}
		}
		protected String directoryTitleRowName = "Directory Info Row 'Title' Label";
		protected String directoryTitleRowName(int i) {
			return "Directory Info Row #" + i + "'s 'Title' Label";
		}
		
		@FindBy(xpath="//tbody[@id='bodyForm:j_id_6c:tb']//td[@class='rf-dt-c'][2]")
		protected List<WebElement> directoryDepartmentLabel;
		protected String directoryDepartmentLabelName = "'Directory Department' Label List";
		protected String directoryDepartmentLabelName(int i) {
			return "'Directory Department' Row #" + i + "'s Label";
		}
		
		public WebElement directoryDepartmentRow(int directoryInfoRowNumber) {
			try {
				//Return the located WebElement
				return eDriver.findElement(By.id("bodyForm:j_id_6c:" + directoryInfoRowNumber + ":j_id_6k"));
			} catch (Exception e) {
				//Output an error about the specified WebElement being unable to be located
				reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + directoryDepartmentRowName(directoryInfoRowNumber) + "\" was not located", "GlobalSearchPageButton");
				
				//Assert the status of the WebElement's existence
				Assert.assertEquals("The WebElement \"" + directoryDepartmentRowName(directoryInfoRowNumber) + "\" was not located", "The WebElement \"" + directoryDepartmentRowName(directoryInfoRowNumber) + "\" was located");
				
				//Return null, since no WebElement was located
				return null;
			}
		}
		protected String directoryDepartmentRowName = "Directory Info Row 'Department' Label";
		protected String directoryDepartmentRowName(int i) {
			return "Directory Info Row #" + i + "'s 'Department' Label";
		}
		
	@FindBy(id="bodyForm:searchResults:0:j_id_54")
	protected WebElement firstOnCallGroupsSearchResultViewDirectoryButton;
	protected String firstOnCallGroupsSearchResultViewDirectoryButtonName = "First 'Searched OnCall Groups Recipient' 'View Directory' Button";
	
	@FindBy(id="bodyForm:j_id_5a_4:0:j_id_5a_n")
	protected WebElement firstRecipientViewDirectoryButton;
	protected String firstRecipientViewDirectoryButtonName = "First 'Recipient' Search Result's 'View Directory' Button";
	
	@FindBy(id="bodyForm:j_id_5a_4:0:j_id_5a_k")
	protected WebElement firstOnCallGroupViewDirectoryButton;
	protected String firstOnCallGroupViewDirectoryButtonName = "First, Added OnCall Group's 'View Directory' Button";
	
	//Group Directory Info
	
	@FindBy(xpath="(//table[@id='bodyForm:messageGroupRecipients']//td)[1]")
	protected WebElement groupDirectoryMemberCountLabel;
	protected String groupDirectoryMemberCountLabelName = "Group Directory Info's 'Member Count' Label";
	
	@FindBy(xpath="//a[@name='bodyForm:j_id_6x']")
	protected WebElement groupDirectoryViewMembersLink;
	protected String groupDirectoryViewMembersLinkName = "Group Directory Info's 'List of Members Page' Link";
	
	@FindBy(xpath="//tbody[@id='bodyForm:unwoundGroupRecipients:tb']//span")
	protected List<WebElement> groupDirectoryMembersList;
	protected String groupDirectoryMembersListName = "Group Directory Info's 'Member Label' List";
	protected String groupDirectoryMembersListName(int i) {
		return "Group Directory Info Member #" + i + "'s Label";
	}
	
	//Delete/Remove Recipients from 'Added List'
	
	@FindBy(xpath="//img[@title='Remove this recipient']")
	protected List<WebElement> removeRecipientButtonList;
	protected String removeRecipientButtonListName = "'Remove Recipient' Button List";
	protected String removeRecipientButtonListName(int i) {
		return "'Remove Recipient' #" + i + " Button";
	}
	
	@FindBy(xpath="//img[@title='Clear the recipients list']")
	protected WebElement removeAllRecipientsButton;
	protected String removeAllRecipientsButtonName = "'Remove All Recipients' Button";
	
	//Exit Directory Pages
	
	@FindBy(id="bodyForm:j_id_63")
	protected WebElement exitRecipientViewDirectoryPopup;
	protected String exitRecipientViewDirectoryPopupName = "'Exit Oncall Group's Directory' Button";
	
	@FindBy(id="bodyForm:j_id_6r")
	protected WebElement exitOncallGroupViewDirectoryPopup;
	protected String exitOncallGroupViewDirectoryPopupName = "'Exit Oncall Group's Directory' Button";
	
	//Activate Event
	
	@FindBy(id="bodyForm:ActivateNewButton")
	protected WebElement activateNewEventButton;
	protected String activateNewEventButtonName = "Activate New Event Button";
	
	@FindBy(id="bodyForm:SaveNewButton")
	protected WebElement createNewTemplateButton;
	protected String createNewTemplateButtonName = "Create New Template Button";
	
	// ~~~ Create Event Additional Options Page ~~~ //
	
	@FindBy(id="bodyForm:OptionsNewButton")
	protected WebElement additionalOptionsButton;
	protected String additionalOptionsButtonName = "'Go To Additional Options Page' Button";
	
	@FindBy(id="bodyForm:eventDuration")
	protected WebElement eventDurationField;
	protected String eventDurationFieldName = "'Event Duration' Field";
	
	@FindBy(id="bodyForm:j_id_3q_2:0")
	protected WebElement deviceEscalationRadioButton;
	protected String deviceEscalationRadioButtonName = "'Use Device Escalation' Radio Button";
	
	@FindBy(id="bodyForm:j_id_3q_2:1")
	protected WebElement notifyAllDevicesSimultaneouslyRadioButton;
	protected String notifyAllDevicesSimultaneouslyRadioButtonName = "'Notify all devices simultaneously' Radio Button";
	
	@FindBy(id="bodyForm:j_id_3r_2:0")
	protected WebElement useRecipientsDeviceListCheckbox;
	protected String useRecipientsDeviceListCheckboxName = "'Use recipient's device list (if available)' Checkbox";
	
	@FindBy(id="bodyForm:selectStatus")
	protected WebElement procedureDeviceDropDown;
	protected String procedureDeviceDropDownName = "'Select Procedure Device List' Drop-down";
	
	@FindBy(xpath="//select[@id='bodyForm:selectStatus']//option[@selected='selected']")
	protected WebElement procedureDeviceSelectedOptionLabel;
	protected String procedureDeviceSelectedOptionLabelName = "'Select Procedure Device List' Drop-down";
	
	// ~~~ Procedure Device List ~~~ //
	
	@FindBy(id="bodyForm:j_id_3t_7")
	protected WebElement viewProcedureDevicesButton;
	protected String viewProcedureDevicesButtonName = "'View Devices for the selected Procedure Device List' Button";
		
		@FindBy(xpath="(//div[@id='prodDeviceInfoPanel_content_scroller']//input)[1]")
		protected WebElement deviceListSelection;
		protected String deviceListSelectionName = "Procedure Devices' Pop-up 'Default Device List' Field";
		
		@FindBy(xpath="//tbody[@id='j_id_6t:tb']//td[1]")
		protected List<WebElement> deviceListOrderList;
		protected String deviceListOrderListName = "Procedure Devices' Pop-up 'Order' Label List";
		protected String deviceListOrderListName(int i) {
			return "Procedure Devices' Pop-up 'Order' #" + i + " Label";
		}
		
		@FindBy(xpath="//tbody[@id='j_id_6t:tb']//td[2]")
		protected List<WebElement> deviceListDeviceTypeList;
		protected String deviceListDeviceTypeListName = "Procedure Devices' Pop-up 'Device Type' Label List";
		protected String deviceListDeviceTypeListName(int i) {
			return "Procedure Devices' Pop-up 'Device Type' #" + i + " Label";
		}
		
		@FindBy(xpath="//tbody[@id='j_id_6t:tb']//td[3]")
		protected List<WebElement> deviceListPhoneTypeList;
		protected String deviceListPhoneTypeListName = "Procedure Devices' Pop-up 'Phone Type' Label List";
		protected String deviceListPhoneTypeListName(int i) {
			return "Procedure Devices' Pop-up 'Phone Type' #" + i + " Label";
		}
		
		@FindBy(xpath="//tbody[@id='j_id_6t:tb']//td[4]")
		protected List<WebElement> deviceListPageRouteList;
		protected String deviceListPageRouteListName = "Procedure Devices' Pop-up 'Page Route' Label List";
		protected String deviceListPageRouteListName(int i) {
			return "Procedure Devices' Pop-up 'Page Route' #" + i + " Label";
		}
		
		@FindBy(xpath="(//input[@value='Cancel and Close'])[2]")
		protected WebElement closeViewProcedureDevicesPopupButton;
		protected String closeViewProcedureDevicesPopupButtonName = "'View Devices for the selected Procedure Device List' Button";
		
	@FindBy(id="bodyForm:OptionsNewBackButton")
	protected WebElement saveAdditionalOptionsButton;
	protected String saveAdditionalOptionsButtonName = "'Save Additional Options' Button";
	
	@FindBy(id="bodyForm:OptionsNewCancelButton")
	protected WebElement cancelAdditionalOptionsButton;
	protected String cancelAdditionalOptionsButtonName = "'Cancel Additional Options' Button";
	
}