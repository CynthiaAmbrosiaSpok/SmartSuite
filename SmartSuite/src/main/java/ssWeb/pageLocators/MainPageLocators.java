package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class MainPageLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public MainPageLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Navigate Tabs ~~~ //
	
	//Directory Search Tab
	@FindBy(xpath="//a[text()='Directory Search']")
	protected WebElement directorySearchTab;
	protected String directorySearchTabName = "'Directory Search' Tab";
	
	@FindBy(xpath="//a[text()='Directory Search']")
	protected WebElement directorySearchTab2;
	protected String directorySearchTab2Name = "'Directory Search' Tab";
	
	//Oncall Tabs
	@FindBy(xpath="//a[text()='On Call']")
	protected WebElement oncallTab;
	protected String oncallTabName = "'On Call' Tab";
	
	@FindBy(xpath="//a[text()='On Call']")
	protected WebElement oncallTab2;
	protected String oncallTab2Name = "'On Call' Tab";
	
	@FindBy(xpath="//a[text()='Today']")
	protected WebElement todayTab;
	protected String todayTabName = "On Call's 'Today' Tab";
	
	@FindBy(xpath="//a[text()='Search']")
	protected WebElement searchTab;
	protected String searchTabName = "On Call's 'Search' Tab";
	
	@FindBy(xpath="//a[text()='Block Assignment']")
	protected WebElement blockAssignmentTab;
	protected String blockAssignmentTabName = "On Call's 'Block Assignment' Tab";
	
	@FindBy(xpath="//a[text()='Scheduler Assignment']")
	protected WebElement schedulerAssignmentTab;
	protected String schedulerAssignmentTabName = "On Call's 'Scheduler Assignment' Tab";
	
	@FindBy(xpath="//a[text()='Person Assignment']")
	protected WebElement personAssignmentTab;
	protected String personAssignmentTabName = "On Call's 'Person Assignment' Tab";
	
	@FindBy(xpath="//a[text()='Calendar Assignment']")
	protected WebElement calenderAssignmentTab;
	protected String calenderAssignmentTabName = "On Call's 'Calender Assignment' Tab";
	
	@FindBy(xpath="//a[text()='History']")
	protected WebElement historyTab;
	protected String historyTabName = "On Call's 'History' Tab";
	
	@FindBy(xpath="//a[text()='Quick View']")
	protected WebElement quickViewTab;
	protected String quickViewTabName = "On Call's 'Quick View' Tab";
	
	//Paging Tabs
	@FindBy(xpath="//a[text()='Paging']")
	protected WebElement pagingTab;
	protected String pagingTabName = "'Paging' Tab";
	
	//Personal Profile Tabs
	@FindBy(xpath="//a[text()='Personal Profile']")
	protected WebElement personalProfileTab;
	protected String personalProfileTabName = "'Personal Profile' Tab";
	
	@FindBy(xpath="//a[text()='Personal Profile']")
	protected WebElement personalProfileTabBackup;
	protected String personalProfileTabBackupName = "'Personal Profile' Tab";
	
	@FindBy(xpath="//a[text()='Inbox']")
	protected WebElement inboxTab;
	protected String inboxTabName = "Personal Profile's 'Inbox' Tab";
	
	@FindBy(xpath="//a[text()='Device Mgmt']")
	protected WebElement deviceManagementTab;
	protected String deviceManagementTabName = "Personal Profile's 'Device Mgmt' Tab";
	
	@FindBy(xpath="//a[text()='Profile	']")
	protected WebElement profileTab;
	protected String profileTabName = "Personal Profile's 'Profile' Tab";
	
	@FindBy(xpath="//a[text()='Picture Upload']")
	protected WebElement pictureUploadTab;
	protected String pictureUploadTabName = "Personal Profile's 'Picture Upload' Tab";
	
	@FindBy(xpath="//a[text()='Quick View Settings']")
	protected WebElement quickViewSettingsTab;
	protected String quickViewSettingsTabName = "Personal Profile's 'Quick View Settings' Tab";
	
	//Patient Info Tabs
	@FindBy(xpath="//a[text()='Patient Info']")
	protected WebElement patientInfoTab;
	protected String patientInfoTabName = "'Patient Info' Tab";
	
	//Global Search Tab
	@FindBy(xpath="//a[text()='Global Search']")
	protected WebElement globalSearchTab;
	protected String globalSearchTabName = "'Global Search' Tab";
	
	@FindBy(xpath="//a[text()='Global Search']")
	protected WebElement globalSearchTab2;
	protected String globalSearchTab2Name = "'Global Search' Tab";
	
	// ~~~ Login & Logout ~~~ //
	
	@FindBy(xpath="//span[text()='Login']")
	protected WebElement loginLink;
	protected String loginLinkName = "'Login' Link to bring up the 'Login' screen";
	
	@FindBy(xpath="//span[text()='Login']")
	protected WebElement loginLink2;
	protected String loginLink2Name = "'Login' Link to bring up the 'Login' screen";
	
	@FindBy(xpath="//a[@id='logout_link']/span[contains(text(), 'Logout')]")
	protected WebElement logoutLink;
	protected String logoutLinkName = "'Logout' Link";
	
	@FindBy(id="logout_link")
	protected WebElement logoutLink2;
	protected String logoutLink2Name = "'Logout' Link";
	
	@FindBy(id="username_txt")
	protected WebElement useridField;
	protected String useridFieldName = "Enter 'Username' Field for Login";
	
	@FindBy(id="password_txt")
	protected WebElement passwordField;
	protected String passwordFieldName = "Enter 'Password' Field for Login";
	
	@FindBy(id="clickme")
	protected WebElement loginButton;
	protected String loginButtonName = "'Login' button";
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:searchnavid")
	protected WebElement genericSearchField;
	protected String genericSearchFieldName = "Enter Search Criteria Field (at top-right corner of SmartSuite)";
	
	@FindBy(xpath="//img[@src='/smartweb/img/search.png']")
	protected WebElement genericSearchButton;
	protected String genericSearchButtonName = "Perform Search Button (at top-right corner of SmartSuite)";
	
	// ~~~ Send a Page ~~~ //
	
	@FindBy(xpath="//span[@title='Click to send a quick message']")
	protected WebElement quickPageLink;
	protected String quickPageLinkName = "'Quick Page' Link (if logged in)";
	
	@FindBy(id="mainForm:msgIdQP")
	protected WebElement recipientIDField;
	protected String recipientIDFieldName = "Quick Page's 'Recipient ID' Field";
	
	@FindBy(id="mainForm:messageTextQP")
	protected WebElement pageMessageField;
	protected String pageMessageFieldName = "Quick Page's 'Page Text' Textarea";
	
	@FindBy(id="mainForm:sendQuickMessageButton")
	protected WebElement sendPageButton;
	protected String sendPageButtonName = "Quick Page's 'Send Page' Button";
	
}