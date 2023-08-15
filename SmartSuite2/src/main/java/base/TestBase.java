package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ssWeb.pageMethods.DirectorySearchPage;
import ssWeb.pageMethods.GlobalSearchPage;
import ssWeb.pageMethods.MainPage;
import ssWeb.pageMethods.Oncall_BlockAssignmentPage;
import ssWeb.pageMethods.Oncall_CalenderAssignmentPage;
import ssWeb.pageMethods.Oncall_HistoryPage;
import ssWeb.pageMethods.Oncall_PersonAssignmentPage;
import ssWeb.pageMethods.Oncall_QuickViewPage;
import ssWeb.pageMethods.Oncall_SchedulerAssignmentPage;
import ssWeb.pageMethods.Oncall_SearchPage;
import ssWeb.pageMethods.Oncall_TodayPage;
import ssWeb.pageMethods.PagingSearchPage;
import ssWeb.pageMethods.PatientInfoPage;
import ssWeb.pageMethods.PersonalProfile_DeviceManagementPage;
import ssWeb.pageMethods.PersonalProfile_InboxPage;
import ssWeb.pageMethods.PersonalProfile_PictureUploadPage;
import ssWeb.pageMethods.PersonalProfile_ProfilePage;
import ssWeb.pageMethods.PersonalProfile_QuickViewSettingsPage;
import ssWeb.pageMethods.PersonalProfile_SentItemsPage;
import utilityClasses.ConstantVariables;
import utilityClasses.EncryptionMethods;
import utilityClasses.EventHandler;
import utilityClasses.ExcelMethods;
import utilityClasses.ExtentFactory;
import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import ezNotify.pageMethods.EZ_ActivateEventPage;
import ezNotify.pageMethods.EZ_CreateEventPage;
import ezNotify.pageMethods.EZ_ExistingEventPage;
import ezNotify.pageMethods.EZ_LoginPage;
import ezNotify.pageMethods.EZ_WebResponsePage;

public class TestBase {
	//Declare Variable(s) for accessing general classes
	protected static Properties prop;
	protected static ConstantVariables constantVariables;
	
	//Declare Variable(s) for accessing the Excel File(s)
	protected ExcelMethods excelMethods;
	protected GeneralMethods genMethods;
	protected EncryptionMethods encryptionMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	protected String path;
	protected String sheetName;
	protected int iteration;
	
	//Declare Variable(s) for performing 'retries' in the test suite
	protected int retryCount = 0;
	protected int maxRetryCount = 1;
	
	//Declare Variable(s) for initializing the WebDriver
	protected static DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
	private static WebDriver driver; //used to setup a connection to one or more browsers for testing
	private static EventHandler eHandler;  //used in conjunction with the EventFiringWebDriver
	protected static EventFiringWebDriver eDriver; //used in conjunction with the WebDriver
	
	protected static String activeWebsite;
	protected static String activeBrowser;
	protected static String activeBrowserVersion;
	
	protected static String smartwebUsername;
	protected static String smartwebPassword;
	
	//Declare the Report Logger
	protected ExtentReports report; //used to setup a report that will hold the testing info of the script(s)
	protected ExtentTest reportLogger; //used to store testing details in the report
	
	//Declare PageFactories
	protected DirectorySearchPage directorySearchPage;
	protected GlobalSearchPage globalSearchPage;
	protected MainPage mainPage;
	
	protected Oncall_BlockAssignmentPage oncall_BlockAssignmentPage;
	protected Oncall_CalenderAssignmentPage oncall_CalenderAssignmentPage;
	protected Oncall_HistoryPage oncall_HistoryPage;
	protected Oncall_PersonAssignmentPage oncall_PersonAssignmentPage;
	protected Oncall_QuickViewPage oncall_QuickViewPage;
	protected Oncall_SchedulerAssignmentPage oncall_SchedulerAssignmentPage;
	protected Oncall_SearchPage oncall_SearchPage;
	protected Oncall_TodayPage oncall_TodayPage;
	
	protected PagingSearchPage pagingSearchPage;
	protected PatientInfoPage patientInfoPage;
	
	protected PersonalProfile_DeviceManagementPage personalProfile_DeviceManagementPage;
	protected PersonalProfile_InboxPage personalProfileInboxPage;
	protected PersonalProfile_PictureUploadPage personalProfilePictureUploadPage;
	protected PersonalProfile_ProfilePage personalProfile_ProfilePage;
	protected PersonalProfile_QuickViewSettingsPage personalProfile_QuickViewSettingsPage;
	protected PersonalProfile_SentItemsPage personalProfile_SentItemsPage;
	
	protected EZ_ActivateEventPage ezActivateEventPage;
	protected EZ_CreateEventPage ezCreateEventPage;
	protected EZ_ExistingEventPage ezExistingEventPage;
	protected EZ_LoginPage ezLoginPage;
	
	protected EZ_WebResponsePage ezWebResponsePage;
	
	public TestBase() {
		try {
			//Initialize an object for the 'Constant Variables' class to access its values
			constantVariables = new ConstantVariables();
			
			//Initialize an object for the 'Properties' file to access its values
			prop = new Properties();
			FileInputStream ip = new FileInputStream(constantVariables.propertiesFileLocation);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String encryptText(String text) {
		byte[] encodedString = Base64.encodeBase64(text.getBytes());
		
		return(new String(encodedString));
	}
	
	public String decryptText(String text) {
		byte[] decodedString = Base64.decodeBase64(text);
		return(new String(decodedString));
	}
	
	public void performSetup(String reportTitle, String scriptName) {
		//Setup the Report
		report = ExtentFactory.getInstance(activeWebsite, activeBrowser, activeBrowserVersion);
		reportLogger = report.startTest(reportTitle);
		
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
		
		//Output the name of the test case
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		reportLoggerMethods.reportInfo("Performing the testcase -> " + scriptName);
		
		//Output an info status to the Extent Report & System
		reportLoggerMethods.reportInfo("Initializing the script's PageFactories");
	}
	
	public static void initializeDriver() {
		//Initialize the relevant browser driver
		if (activeBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (activeBrowser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver", constantVariables.firefoxDriverPath);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (activeBrowser.equalsIgnoreCase("chrome")) {
			System.out.println("2asdf");
			WebDriverManager.chromedriver().setup();
			System.out.println("3asdf");
			System.out.println("3asdf");
//			WebDriverManager.chromedriver().browserVersion("105.0.5195.125").setup();
			try {
				driver = new ChromeDriver();
			} catch (Exception e) {
				System.out.println(e);
			}
			
			System.out.println("4asdf");
		} else {
			System.out.println("Cannot setup the driver due to invalid input");
			driver.quit();
		}
		System.out.println("5asdf");
		//Retrieve the browser version
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		activeBrowserVersion = caps.getVersion();
		
		//Setup the Event Driver
		eDriver = new EventFiringWebDriver(driver);
		eHandler = new EventHandler();
		eDriver.register(eHandler);
		
		//Configure the testing browser
		eDriver.manage().window().maximize();
		eDriver.manage().deleteAllCookies();
		eDriver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
		eDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@BeforeTest
	@Parameters({"website", "browser", "userid"})
	public void beforeTest(String website, String browser, String userid) {
		System.out.println("Performing the script's setups (@BeforeSuite)");
		
		//Delete the previous Extent Report
		ExtentFactory.deleteExtentReport();
		
		//Set the Environment value for the Extent Report
		activeWebsite = website;
		
		//Set the active Browser value for the Extent Report
		activeBrowser = browser;
		
		//Set the active SmartSuite Userid value for the Extent Report
		smartwebUsername = userid;
		
		//Initialize the testing browser via WebDriver, configured with the Listeners
		initializeDriver();
		
		//Initialize an object for the 'Report Logger Methods' class to access its methods
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	@BeforeClass
	public void beforeClass() {
		//Setup objects for general methods
		excelMethods = new ExcelMethods();
		genMethods = new GeneralMethods();
		encryptionMethods = new EncryptionMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	@AfterSuite
	public void afterSuite() {
		//Close the testing browser and its WebDriver & Listeners
		eDriver.quit();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		try {
			if(result.getStatus() == ITestResult.FAILURE) {
				//Increase the retryCount by 1
				retryCount++;
				
				//If the retryCount does not match the maxRetryCount, then skip sending out an error message since it will perform a retry
				if (retryCount != maxRetryCount) {
					return;
				}
				
				//Output the FAIL status to the Report & Excel
				reportLoggerMethods.reportFailedMessage("The Test Case that failed is: " + result.getName());
				reportLoggerMethods.reportFailedMessage("The Test Case that failed is: " + result.getThrowable());
			} else if (result.getStatus() == ITestResult.SKIP) {
				//Increase the retryCount by 1
				retryCount++;
				
				//If the retryCount does not match the maxRetryCount, then skip sending out an error message since it will perform a retry
				if (retryCount != maxRetryCount) {
					return;
				}
				
				//Output the SKIP status to the Report & Excel
				reportLoggerMethods.reportSkippedMessage("The Test Case that was skipped is: " + result.getName());
				reportLoggerMethods.reportSkippedMessage("The Test Case that was skipped is: " + result.getThrowable());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				//Output the SUCCESS status to the Report
				reportLoggerMethods.reportSuccessfulCheckpoint("The Test Case that passed is: " + result.getName());
			} else {
				//Increase the retryCount by 1
				retryCount++;
				
				//If the retryCount does not match the maxRetryCount, then skip sending out an error message since it will perform a retry
				if (retryCount != maxRetryCount) {
					return;
				}
				
				//Output the Script status to the Report & Excel
				reportLoggerMethods.reportUnknownMessage("Unknown error upon completion of a @Test -> the @Test neither passed, failed, or was skipped");
			}
			
			//Reset the retry counter to zero
			retryCount = 0;
			
			//End/Tie-up the Report
			report.endTest(reportLogger);
			report.flush();
		} catch (Exception e) {
			System.out.print("");
		}
	}
}