package ssWeb.pageLocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallCalenderAssignmentLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallCalenderAssignmentLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Group Search ~~~ //
	
	@FindBy(id="mainForm:groupName")
	protected WebElement groupNameSearchField;
	protected String groupNameSearchFieldName = "Search Field for Calender Assignment Groups";
	
	@FindBy(id="mainForm:submitButton")
	protected WebElement groupNameSearchButton;
	protected String groupNameSearchButtonName = "Search Button for Calender Assignment Groups";
	
	@FindBy(id="mainForm:onCallGroupTree:0:0:mancrazy")
	protected WebElement groupNameFirstSearchResult;
	protected String groupNameFirstSearchResultName = "First Result of a Calender Assignment Group Search";
	
	// ~~~ Assignment ~~~ //
	
	@FindBy(xpath="//label[text()='New']")
	protected WebElement newButton;
	protected String newButtonName = "'Create New Calender Assignment' Button";
	
	@FindBy(xpath="//label[text()='Edit']")
	protected WebElement editButton;
	protected String editButtonName = "'Edit Calender Assignment' Button";
	
	@FindBy(xpath="//label[text()='Delete']")
	protected WebElement deleteButton;
	protected String deleteButtonName = "'Delete Calender Assignment' Button";
	
	// ~~~ Tree ~~~ //
	
	@FindBy(xpath="//label[text()='Hide Tree']")
	protected WebElement hideTreeButton;
	protected String hideTreeButtonName = "'Hide Tree' Button";
	
	@FindBy(xpath="//label[text()='Show Tree']")
	protected WebElement showTreeButton;
	protected String showTreeButtonName = "'Show Tree' Button";
	
	@FindBy(id="hideoncallTreeColumn")
	protected WebElement treeColumn;
	protected String treeColumnName = "'Tree' Column/Section";
	
	// ~~~ Navigate Calender ~~~ //
	
	@FindBy(xpath="//td[@class='header']")
	protected List<WebElement> calenderCellHeaderList;
	protected String calenderCellHeaderListName = "List of Calender Cells' 'Header' Section (Contains the 'Day' Value)";
	protected String calenderCellHeaderListName(int i) {
		return "Calender Cell #" + i + "'s 'Header' Section (Contains the 'Day' Value)";
	}
	
	@FindBy(xpath="//div[contains(@name, 'mainForm:calendar_entry_')]")
	protected List<WebElement> calenderCellBodyList;
	protected String calenderCellBodyListName = "List of 'Calender Assignments' listed the Current Calender";
	protected String calenderCellBodyListName(int i) {
		return "Calender Assignment #" + i + ", listed the Current Calender";
	}
	
	@FindBy(xpath="//input[@value='Previous Month']")
	protected WebElement previousMonthButton;
	protected String previousMonthButtonName = "'Previous Calender Month' Button";
	
	@FindBy(xpath="//input[@value='Next Month']")
	protected WebElement nextMonthButton;
	protected String nextMonthButtonName = "'Next Calender Month' Button";
	
	// ~~~ Create Calender ~~~ //
	
	@FindBy(id="newMessagingIdValue")
	protected WebElement createMsgIDField;
	protected String createMsgIDFieldName = "'Msg ID' Field for Creating a Calender Assignment";
	
	@FindBy(id="newNameValue")
	protected WebElement createNameField;
	protected String createNameFieldName = "'Name' Field for Creating a Calender Assignment";
	
	@FindBy(id="newStartDateValue")
	protected WebElement createStartTimeField;
	protected String createStartTimeFieldName = "'Start Time' Field for Creating a Calender Assignment";
	
	@FindBy(id="newEndDateValue")
	protected WebElement createEndTimeField;
	protected String createEndTimeFieldName = "'End Time' Field for Creating a Calender Assignment";
	
	@FindBy(id="newTimeZoneSelectOneMenu")
	protected WebElement createTimeZoneDropDown;
	protected String createTimeZoneDropDownName = "'Time Zone' Drop-Down for Creating a Calender Assignment";
	
	@FindBy(xpath="//input[@id='mainForm:j_id_o_b_2x'] | //input[@id='mainForm:j_id_o_b_2x']")
	protected WebElement createSaveButton;
	protected String createSaveButtonName = "'Save' Button for Creating a Calender Assignment";
	
	// ~~~ Modify Calender ~~~ //
	
	@FindBy(id="onCallAssignmentMessagingIdValue")
	protected WebElement modifyMsgIDField;
	protected String modifyMsgIDFieldName = "'Msg ID' Field for Modifying a Calender Assignment";
	
	@FindBy(id="onCallAssignmentNameValue")
	protected WebElement modifyNameField;
	protected String modifyNameFieldName = "Name' Field for Modifying a Calender Assignment";
	
	@FindBy(id="startDateValue")
	protected WebElement modifyStartTimeField;
	protected String modifyStartTimeFieldName = "'Start Time' Field for Modifying a Calender Assignment";
	
	@FindBy(id="endDateValue")
	protected WebElement modifyEndTimeField;
	protected String modifyEndTimeFieldName = "'End Time' Field for Modifying a Calender Assignment";
	
	@FindBy(id="timeZoneSelectOneMenu")
	protected WebElement modifyTimeZoneDropDown;
	protected String modifyTimeZoneDropDownName = "'Time Zone' Drop-Down for Modifying a Calender Assignment";
	
	@FindBy(xpath="//input[@id='mainForm:j_id_o_b_1d'] | //input[@id='mainForm:j_id_p_b_1d']")
	protected WebElement modifySaveButton;
	protected String modifySaveButtonName = "'Save' Button for Modifying a Calender Assignment";
	
	@FindBy(xpath="//input[@id='mainForm:j_id_o_b_1e'] | //input[@id='mainForm:j_id_p_b_1e']")
	protected WebElement modifyCancelButton;
	protected String modifyCancelButtonName = "'Cancel' Button for Modifying a Calender Assignment";
	
	// ~~~ Misc. Calender ~~~ //
	
	@FindBy(xpath="//div[@class='schedule-compact-md-header-default']")
	protected WebElement currentDateLabel;
	protected String currentDateLabelName = "Label that Displays Current Calender (Month, Year)";
	
	protected int getMonthNumber(String month) {
		if (month.equalsIgnoreCase("january")) {
			return 1;
		} else if (month.equalsIgnoreCase("february")) {
			return 2;
		} else if (month.equalsIgnoreCase("march")) {
			return 3;
		} else if (month.equalsIgnoreCase("april")) {
			return 4;
		} else if (month.equalsIgnoreCase("may")) {
			return 5;
		} else if (month.equalsIgnoreCase("june")) {
			return 6;
		} else if (month.equalsIgnoreCase("july")) {
			return 7;
		} else if (month.equalsIgnoreCase("august")) {
			return 8;
		} else if (month.equalsIgnoreCase("september")) {
			return 9;
		} else if (month.equalsIgnoreCase("october")) {
			return 10;
		} else if (month.equalsIgnoreCase("november")) {
			return 11;
		} else if (month.equalsIgnoreCase("december")) {
			return 12;
		} else {
			return 0;
		}
	}
	
	protected String getMonthName(int month) {
		//Initialize Variable(s)
		if (month == 1) {
			return "January";
		} else if (month == 2) {
			return "February";
		} else if (month == 3) {
			return "March";
		} else if (month == 4) {
			return "April";
		} else if (month == 5) {
			return "May";
		} else if (month == 6) {
			return "June";
		} else if (month == 7) {
			return "July";
		} else if (month == 8) {
			return "August";
		} else if (month == 9) {
			return "September";
		} else if (month == 10) {
			return "October";
		} else if (month == 11) {
			return "November";
		} else if (month == 0) {
			return "December";
		} else {
			return "";
		}
	}
	
}