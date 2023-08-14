package ssWeb.pageLocators;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class PersonalProfile_ProfileLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PersonalProfile_ProfileLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Create Exceptions ~~~ //
	
	@FindBy(id="mainForm:ExpTypeRadio:0")
	protected WebElement coverageCheckbox;
	protected String coverageCheckboxName = "Add New Exception's 'Coverage' Radio Button";
	
	@FindBy(id="mainForm:ExpTypeRadio:1")
	protected WebElement referralCheckbox;
	protected String referralCheckboxName = "Add New Exception's 'Referral' Radio Button";
	
	@FindBy(id="mainForm:ExpTypeRadio:2")
	protected WebElement pageBlockCheckbox;
	protected String pageBlockCheckboxName = "Add New Exception's 'Page Block' Radio Button";
	
	@FindBy(xpath="(//span[text()='New'])[1]")
	protected WebElement newExceptionButton;
	protected String newExceptionButtonName = "'New Exception' Button";
	
	@FindBy(id="messagingId")
	protected WebElement coverageExceptionMessageIDField;
	protected String coverageExceptionMessageIDFieldName = "New Coverage Exception's 'Message ID' Field";
	
	@FindBy(id="InputphoneEdit")
	protected WebElement referralExceptionMessageIDField;
	protected String referralExceptionMessageIDFieldName = "New Referral Exception's 'Message ID' Field";
	
	@FindBy(id="InputphoneEdit")
	protected WebElement pageBlockExceptionMessageIDField;
	protected String pageBlockExceptionMessageIDFieldName = "New Page Block Exception's 'Message ID' Field";
	
	@FindBy(id="Start_Date.day")
	protected WebElement exceptionStartDateDayField;
	protected String exceptionStartDateDayFieldName = "New Exception's 'Start Date Day' Field";
	
	@FindBy(id="Start_Date.month")
	protected WebElement exceptionStartDateMonthDropDown;
	protected String exceptionStartDateMonthDropDownName = "New Exception's 'Start Date Month' Drop-Down";
	
	@FindBy(id="Start_Date.year")
	protected WebElement exceptionStartDateYearField;
	protected String exceptionStartDateYearFieldName = "New Exception's 'Start Date Year' Field";
	
	@FindBy(id="Start_Date.hours")
	protected WebElement exceptionStartDateHourField;
	protected String exceptionStartDateHourFieldName = "New Exception's 'Start Date Hour' Field";
	
	@FindBy(id="Start_Date.minutes")
	protected WebElement exceptionStartDateMinutesField;
	protected String exceptionStartDateMinutesFieldName = "New Exception's 'Start Date Minutes' Field";
	
	@FindBy(id="Start_Date.ampm")
	protected WebElement exceptionStartDateAMPMDropDown;
	protected String exceptionStartDateAMPMDropDownName = "New Exception's 'Start Date AM or PM' Drop-Down";
	
	@FindBy(id="End_Date.day")
	protected WebElement exceptionEndDateDayField;
	protected String exceptionEndDateDayFieldName = "New Exception's 'End Date Day' Field";
	
	@FindBy(id="End_Date.month")
	protected WebElement exceptionEndDateMonthDropDown;
	protected String exceptionEndDateMonthDropDownName = "New Exception's 'End Date Month' Drop-Down";
	
	@FindBy(id="End_Date.year")
	protected WebElement exceptionEndDateYearField;
	protected String exceptionEndDateYearFieldName = "New Exception's 'End Date Year' Field";
	
	@FindBy(id="End_Date.hours")
	protected WebElement exceptionEndDateHourField;
	protected String exceptionEndDateHourFieldName = "New Exception's 'End Date Hour' Field";
	
	@FindBy(id="End_Date.minutes")
	protected WebElement exceptionEndDateMinutesField;
	protected String exceptionEndDateMinutesFieldName = "New Exception's 'End Date Minutes' Field";
	
	@FindBy(id="End_Date.ampm")
	protected WebElement exceptionEndDateAMPMDropDown;
	protected String exceptionEndDateAMPMDropDownName = "New Exception's 'End Date AM or PM' Drop-Down";
	
	@FindBy(id="mainForm:timeZone")
	protected WebElement exceptionTimeZoneDropDown;
	protected String exceptionTimeZoneDropDownName = "New Exception's 'Time Zone' Drop-Down";
	
	@FindBy(id="mainForm:InputremarkEdit")
	protected WebElement exceptionRemarkField;
	protected String exceptionRemarkFieldName = "New Exception's 'Remark' Field";
	
	@FindBy(xpath="//span[@id='mainForm:newException']/following-sibling::table//span[text()='Save']")
	protected WebElement exceptionSaveButton;
	protected String exceptionSaveButtonName = "New Exception's 'Save' Button";
	
	// ~~~ Existing Current Exceptions ~~~ //
	
	@FindBy(xpath="//table[@id='mainForm:CurrentExpList']//td[contains(text(), 'COVERAGE')]")
	protected WebElement existingCoverageExceptionLabel;
	protected String existingCoverageExceptionLabelName = "Existing, Active Exception's Type (COVERAGE)";
	
	@FindBy(xpath="//table[@id='mainForm:CurrentExpList']//td[contains(text(), 'REFERRAL')]")
	protected WebElement existingReferralExceptionLabel;
	protected String existingReferralExceptionLabelName = "Existing, Active Exception's Type (REFERRAL)";
	
	@FindBy(xpath="//table[@id='mainForm:CurrentExpList']//td[contains(text(), 'PAGEBLOCK')]")
	protected WebElement existingPageBlockExceptionLabel;
	protected String existingPageBlockExceptionLabelName = "Existing, Active Exception's Type (PAGEBLOCK)";
	
	@FindBy(xpath="//a[@id='mainForm:CurrentExpList:0:j_id_o_x']/span[contains(text(), 'Delete')]")
	protected WebElement exceptionDeleteButton;
	protected String exceptionDeleteButtonName = "Existing, Active Exception's 'Delete' Button";
	
	@FindBy(xpath="//tbody[@id='mainForm:CurrentExpList:tbody_element']/tr/td[2]")
	protected WebElement existingExceptionMessageIDLabel;
	protected String existingExceptionMessageIDLabelName = "Existing, Active Exception's 'Covered By' Label";
	
	// ~~~ Existing Future Exceptions ~~~ //
	
	@FindBy(xpath="//input[@id='mainForm:j_id_o_1w:0:ExpSelcheck']")
	protected WebElement checkFirstExistingFutureExceptionCheckbox;
	protected String checkFirstExistingFutureExceptionCheckboxName = "'Delete First Future Exception' Button";
	
	@FindBy(xpath="//a[@id='mainForm:deleteExp']")
	protected WebElement deleteExceptionButton;
	protected String deleteExceptionButtonName = "'Delete Exception' Button";
	
	@FindBy(xpath="//tbody[@id='mainForm:j_id_o_1w:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[2]")
	protected List<WebElement> existingFutureReferralExceptionLabelList;
	protected String existingFutureReferralExceptionLabelListName = "List of Future Exceptions' 'Type' Labels";
	protected String existingFutureReferralExceptionLabelListName(int i) {
		return "Future Exception Row #" + i + "'s 'Type' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:j_id_o_1w:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[3]")
	protected List<WebElement> existingFutureExceptionMessageLabelList;
	protected String existingFutureExceptionMessageLabelListName = "List of Future Exceptions' 'Message' Labels";
	protected String existingFutureExceptionMessageLabelListName(int i) {
		return "Future Exception Row #" + i + "'s 'Message' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:j_id_o_1w:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[4]")
	protected List<WebElement> existingFutureExceptionStartDateList;
	protected String existingFutureExceptionStartDateListName = "List of Future Exceptions' 'Start' Labels";
	protected String existingFutureExceptionStartDateListName(int i) {
		return "Future Exception Row #" + i + "'s 'Start' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:j_id_o_1w:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[5]")
	protected List<WebElement> existingFutureExceptionEndDateList;
	protected String existingFutureExceptionEndDateListName = "List of Future Exceptions' 'End' Labels";
	protected String existingFutureExceptionEndDateListName(int i) {
		return "Future Exception Row #" + i + "'s 'End' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:j_id_o_1w:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[6]")
	protected List<WebElement> existingFutureExceptionTimeZoneList;
	protected String existingFutureExceptionTimeZoneListName = "List of Future Exceptions' 'Time Zone' Labels";
	protected String existingFutureExceptionTimeZoneListName(int i) {
		return "Future Exception Row #" + i + "'s 'Time Zone' Label";
	}
	
	// ~~~ Instructions ~~~ //
	
	@FindBy(xpath="//textarea[@name='mainForm:j_id_u_2:0:j_id_u_8'] | //textarea[@name='mainForm:j_id_v_2:0:j_id_v_8']")
	protected WebElement instructionsTextArea;
	protected String instructionsTextAreaName = "Create 'Instructions' Textarea";
	
	@FindBy(xpath="//img[@title='Save Instructions']//following-sibling::span[text()='Save']")
	protected WebElement saveInstructionsButton;
	protected String saveInstructionsButtonName = "'Save' Instructions Button";
	
	@FindBy(xpath="//img[@title='Delete Instructions']//following-sibling::span[text()='Delete']")
	protected WebElement deleteInstructionsButton;
	protected String deleteInstructionsButtonName = "'Delete' Instructions Button";
	
	// ~~~ Create Directory ~~~ //
	
	@FindBy(xpath="//span[@id='mainForm:DirList']/following-sibling::table//span[text()='New']")
	protected WebElement newDirectoryButton;
	protected String newDirectoryButtonName = "'New' Directory Button";
	
	@FindBy(id="mainForm:department")
	protected WebElement newDirectoryDepartmentDropDown;
	protected String newDirectoryDepartmentDropDownName = "New/Modify Directory's 'Department' Drop-Down";
	
	@FindBy(id="mainForm:phone")
	protected WebElement newDirectoryPhoneNumberField;
	protected String newDirectoryPhoneNumberFieldName = "New/Modify Directory's 'Phone Number' Field";
	
	@FindBy(id="mainForm:phone_type")
	protected WebElement newDirectoryPhoneNumberTypeDropDown;
	protected String newDirectoryPhoneNumberTypeDropDownName = "New/Modify Directory's 'Phone Number Type' Drop-Down";
	
	@FindBy(xpath="//div[@id='editDir']//span[text()='Save']")
	protected WebElement directorySaveButton;
	protected String directorySaveButtonName = "New/Modify Directory's 'Save' Button";
	
	// ~~~ Existing Directory ~~~ //
	
	public WebElement directoryCheckbox(int directoryRow) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:j_id_1n_w:" + directoryRow + ":chkSelected'] | //input[@id='mainForm:j_id_1o_w:" + directoryRow + ":chkSelected']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + directoryCheckboxName(directoryRow) + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + directoryCheckboxName(directoryRow) + "\" was not located", "The WebElement \"" + directoryCheckboxName(directoryRow) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String directoryCheckboxName = "List of existing Directory's Checkboxes";
	protected String directoryCheckboxName(int i) {
		return "Existing Directory Row #" + i + "'s Checkbox";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[3]")
	protected List<WebElement> existingDirectoryPhoneNumberLabelList;
	protected String existingDirectoryPhoneNumberLabelListName = "List of existing Directory's 'Phone Number' Labels";
	protected String existingDirectoryPhoneNumberLabelListName(int i) {
		return "Existing Directory Row #" + i + "'s 'Phone Number' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[4]")
	protected List<WebElement> existingDirectoryPhoneNumberTypeLabelList;
	protected String existingDirectoryPhoneNumberTypeLabelListName = "List of existing Directory's 'Phone Type' Labels";
	protected String existingDirectoryPhoneNumberTypeLabelListName(int i) {
		return "Existing Directory Row #" + i + "'s 'Phone Type' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[5]")
	protected List<WebElement> existingDirectoryDepartmentLabelList;
	protected String existingDirectoryDepartmentLabelListName = "List of existing Directory's 'Department' Labels";
	protected String existingDirectoryDepartmentLabelListName(int i) {
		return "Existing Directory Row #" + i + "'s 'Department' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[8]")
	protected List<WebElement> existingDirectoryAddressLabelList;
	protected String existingDirectoryAddressLabelListName = "List of existing Directory's 'Address' Labels";
	protected String existingDirectoryAddressLabelListName(int i) {
		return "Existing Directory Row #" + i + "'s 'Address' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[9]")
	protected List<WebElement> existingDirectoryAddressTypeLabelList;
	protected String existingDirectoryAddressTypeLabelListName = "List of existing Directory's 'Address Type' Labels";
	protected String existingDirectoryAddressTypeLabelListName(int i) {
		return "Existing Directory Row #" + i + "'s 'Address Type' Label";
	}
	
	// ~~~ Modify Directory ~~~ //
	
	@FindBy(xpath="//a[@id='mainForm:j_id_1n_a'] | //a[@id='mainForm:j_id_1o_a']")
	protected WebElement editDirectoryButton;
	protected String editDirectoryButtonName = "'Edit Directory' Button";
	
	@FindBy(id="mainForm:repeater")
	protected WebElement editDirectoryAddressTypeDropDown;
	protected String editDirectoryAddressTypeDropDownName = "New/Modify Directory's 'Address Type' Drop-Down";
	
	@FindBy(id="mainForm:street1")
	protected WebElement editDirectoryStreet1Field;
	protected String editDirectoryStreet1FieldName = "New/Modify Directory's 'Street 1' Field";
	
	@FindBy(id="mainForm:street2")
	protected WebElement editDirectoryStreet2Field;
	protected String editDirectoryStreet2FieldName = "New/Modify Directory's 'Street 2' Field";
	
	@FindBy(id="mainForm:city")
	protected WebElement editDirectoryCityField;
	protected String editDirectoryCityFieldName = "New/Modify Directory's 'City' Field";
	
	@FindBy(id="mainForm:state")
	protected WebElement editDirectoryStateDropDown;
	protected String editDirectoryStateDropDownName = "New/Modify Directory's 'State' Field";
	
	@FindBy(id="mainForm:postal_code")
	protected WebElement editDirectoryPostalCodeField;
	protected String editDirectoryPostalCodeFieldName = "New/Modify Directory's 'Postal Code' Field";
	
	@FindBy(xpath="//span[@id='mainForm:DirList']/following-sibling::table//span[text()='Delete']")
	protected WebElement deleteDirectoryButton;
	protected String deleteDirectoryButtonName = "'Delete Directory' Button";
	
	// ~~~ User Status ~~~ //
	
	@FindBy(xpath="//select[@id='mainForm:selectStatus']")
	protected WebElement userStatusDropDown;
	protected String userStatusDropDownName = "User 'Status' Field";
	
	@FindBy(xpath="//span[text()='Continue']")
	protected WebElement continueStatusUpdateButton;
	protected String continueStatusUpdateButtonName = "Confirm 'Status' Update";
	
	@FindBy(xpath="//span[text()='User Defined Status: ']/following-sibling::input")
	protected WebElement userDefinedStatusField;
	protected String userDefinedStatusFieldName = "User Defined Status Field when Updating Status";
	
	@FindBy(xpath="//span[@id='mainForm:actionbuts']//span[text()='Save']")
	protected WebElement saveUpdatedStatusButton;
	protected String saveUpdatedStatusButtonName = "'Save Updated Status' Button";
	
	@FindBy(xpath="//div[@id='StatusEditDiv']//following-sibling::span[text()='Save']")
	protected WebElement saveUserDefinedUpdatedStatusButton;
	protected String saveUserDefinedUpdatedStatusButtonName = "'Save Updated User Define Status' Button";
	
	@FindBy(id="mainForm:current_exception_status_code")
	protected WebElement currentStatusCodeLabel;
	protected String currentStatusCodeLabelName = "Current User Status' Numeric Value";
	
	@FindBy(id="mainForm:current_exception_status_value")
	protected WebElement currentStatusReasonLabel;
	protected String currentStatusReasonLabelName = "Current User Status' String Value";
	
}