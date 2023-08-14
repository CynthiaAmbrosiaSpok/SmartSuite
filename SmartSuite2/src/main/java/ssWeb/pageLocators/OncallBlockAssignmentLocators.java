package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallBlockAssignmentLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public OncallBlockAssignmentLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
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
	protected String groupNameSearchFieldName = "Search Field for Block Assignment Groups";
	
	@FindBy(xpath="//td[@id='hideTreeColumn']//span[contains(text(), 'Search')]")
	protected WebElement groupNameSearchButton;
	protected String groupNameSearchButtonName = "Search Button for Block Assignment Groups";
	
	@FindBy(xpath="//img[@src='/smartweb/img/icons/smallGroupIcon.gif']/following-sibling::span/span")
	protected WebElement groupNameFirstSearchResult;
	protected String groupNameFirstSearchResultName = "First Result of a Block Assignment Group Search";
	
	@FindBy(xpath="//label[@class='assignmentData']")
	protected WebElement firstGroupMemberMsgIDLabel;
	protected String firstGroupMemberMsgIDLabelName = "First Group Member's 'Msg ID' Label under the 'Group Members' section";
	
	// ~~~ Non-Member Search ~~~ //
	
	@FindBy(xpath="//input[@value='Non-Member Search']")
	protected WebElement nonMemberSearchTab;
	protected String nonMemberSearchTabName = "'Non-Member Search' Tab";
	
	@FindBy(xpath="//input[@title='Enter either Name or Messaging Id or Pager Id to Search']")
	protected WebElement nonMemberSearchField;
	protected String nonMemberSearchFieldName = "'Non-Member Search' Tab's 'Search' Field";
	
	@FindBy(xpath="(//span[text()='Search'])[2]")
	protected WebElement nonMemberSearchButton;
	protected String nonMemberSearchButtonName = "'Non-Member Search' Tab's 'Search' Button";
	
	@FindBy(xpath="//label[@id='mainForm:j_id_3z_1:nonMemberData:0:messagingId'] | //label[@id='mainForm:j_id_40_1:nonMemberData:0:messagingId']")
	protected WebElement nonMemberMsgIDResult;
	protected String nonMemberMsgIDResultName = "First Search Result's 'Msg ID' for the 'Non-Member Search' Search";
	
	@FindBy(xpath="//label[@id='mainForm:j_id_3z_1:nonMemberData:0:name'] | //label[@id='mainForm:j_id_40_1:nonMemberData:0:name']")
	protected WebElement nonMemberNameResult;
	protected String nonMemberNameResultName = "First Search Result's 'Name' for the 'Non-Member Search' Search";
	
	// ~~~ Create Assignment ~~~ //
	
	@FindBy(id="MessagingIdValue")
	protected WebElement assignmentDetailsMsgIDField;
	protected String assignmentDetailsMsgIDFieldName = "'Msg ID' Search Field under 'Assignment Details' Section";
	
	@FindBy(id="NameValue")
	protected WebElement assignmentDetailsNameField;
	protected String assignmentDetailsNameFieldName = "'Name' Search Field under 'Assignment Details' Section";
	
	@FindBy(id="newStartDateValue")
	protected WebElement assignmentDetailsStartField;
	protected String assignmentDetailsStartFieldName = "'Start Time' Search Field under 'Assignment Details' Section";
	
	@FindBy(id="newEndDateValue")
	protected WebElement assignmentDetailsEndField;
	protected String assignmentDetailsEndFieldName = "'End Time' Search Field under 'Assignment Details' Section";
	
	@FindBy(xpath="//span[text()='Save']")
	protected WebElement assignmentDetailsSaveButton;
	protected String assignmentDetailsSaveButtonName = "'Save' Button under 'Assignment Details' Section";
	
	// ~~~ Existing Assignment ~~~ //
	
	@FindBy(id="mainForm:editAss")
	protected WebElement editBlockAssignmentButton;
	protected String editBlockAssignmentButtonName = "'Edit' Block Assignment Button";
	
	@FindBy(id="mainForm:delbutton")
	protected WebElement deleteBlockAssignmentButton;
	protected String deleteBlockAssignmentButtonName = "'Delete' Block Assignment Button";
	
	@FindBy(id="mainForm:dayAssignmentDetailTable:0:chkSelected")
	protected WebElement firstBlockAssignmentCheckbox;
	protected String firstBlockAssignmentCheckboxName = "First Block Assignment's 'Checkbox'";
	
	@FindBy(id="mainForm:dayAssignmentDetailTable:0:messagingId_txt")
	protected WebElement blockAssignmentMsgIDLabel;
	protected String blockAssignmentMsgIDLabelName = "First Block Assignment's 'Msg ID' Label";
	
	@FindBy(xpath="//td[@class='rf-dt-c dr-table-cell rich-table-cell'][3]//a")
	protected WebElement blockAssignmentNameLabel;
	protected String blockAssignmentNameLabelName = "First Block Assignment's 'Name' Label";
	
	@FindBy(id="mainForm:dayAssignmentDetailTable:0:startDateTime_txt")
	protected WebElement blockAssignmentStartLabel;
	protected String blockAssignmentStartLabelName = "First Block Assignment's 'Start Date/Time' Label";
	
	@FindBy(id="mainForm:dayAssignmentDetailTable:0:endDateTime_txt")
	protected WebElement blockAssignmentEndLabel;
	protected String blockAssignmentEndLabelName = "First Block Assignment's 'End Date/Time' Label";
	
	// ~~~ Tree ~~~ //
	
	@FindBy(id="mainForm:hidebut1")
	protected WebElement hideTreeButton;
	protected String hideTreeButtonName = "'Hide Tree' Button";
	
	@FindBy(id="mainForm:hidbut2")
	protected WebElement showTreeButton;
	protected String showTreeButtonName = "'Show Tree' Button";
	
	@FindBy(id="hideTreeColumn")
	protected WebElement treeColumn;
	protected String treeColumnName = "'Tree' Column/Section";
	
	// ~~~ Calender ~~~ //
	
	@FindBy(xpath="//div[@id='calendarSpace']/table/tbody/tr[4]/td[6]")
	protected WebElement calendarDayTenCell;
	protected String calendarDayTenCellName = "Block Assignment's Calennder Cell (Date)";
	
//	@FindBy(xpath="//img[@src='/smartweb/javascript/oncall/calendar/rewind.png']")
	@FindBy(xpath="//img[@class='backAMonth']")
	protected WebElement previousMonthButton;
	protected String previousMonthButtonName = "'Previous Calender Month' Button";
	
	@FindBy(xpath="//img[@src='/smartweb/javascript/oncall/calendar/forward.png']")
	protected WebElement nextMonthButton;
	protected String nextMonthButtonName = "'Next Calender Month' Button";
	
	@FindBy(xpath="//span[@class='year']")
	protected WebElement currentYearLabel;
	protected String currentYearLabelName = "'Current Calender Year' Label";
	
	@FindBy(xpath="//span[@class='month']")
	protected WebElement currentMonthLabel;
	protected String currentMonthLabelName = "'Current Calender Month' Label";
	
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