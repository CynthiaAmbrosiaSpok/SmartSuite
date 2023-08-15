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

public class PersonalProfile_SentItemsLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PersonalProfile_SentItemsLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Page Log Search ~~~ //
	
	@FindBy(xpath="//input[@id='mainForm:j_id_4k'] | //input[@id='mainForm:j_id_4l']")
	protected WebElement messageField;
	protected String messageFieldName = "Personal Profile's Sent Item's 'Message' Search Field";
	
	@FindBy(id="mainForm:Start_Date.year")
	protected WebElement startDateYearField;
	protected String startDateYearFieldName = "Personal Profile's Sent Item's 'Start Date - Year' Search Field";
	
	@FindBy(id="mainForm:Start_Date.month")
	protected WebElement startDateMonthField;
	protected String startDateMonthFieldName = "Personal Profile's Sent Item's 'Start Date - Month' Search Field";
	
	@FindBy(id="mainForm:Start_Date.day")
	protected WebElement startDateDayField;
	protected String startDateDayFieldName = "Personal Profile's Sent Item's 'Start Date - Day' Search Field";
	
	@FindBy(id="mainForm:End_Date.year")
	protected WebElement endDateYearField;
	protected String endDateYearFieldName = "Personal Profile's Sent Item's 'End Date - Year' Search Field";
	
	@FindBy(id="mainForm:End_Date.month")
	protected WebElement endDateMonthField;
	protected String endDateMonthFieldName = "Personal Profile's Sent Item's 'End Date - Month' Search Field";
	
	@FindBy(id="mainForm:End_Date.day")
	protected WebElement endDateDayField;
	protected String endDateDayFieldName = "Personal Profile's Sent Item's 'End Date - Day' Search Field";
	
	@FindBy(xpath="//span[text()='Search']")
	protected WebElement searchButton;
	protected String searchButtonName = "Personal Profile's Sent Item's 'Perform Search' Button";
	
	// ~~~ Search Results ~~~ //
	
	@FindBy(id="mainForm:dispaglog")
	protected WebElement searchCountMessageLabel;
	protected String searchCountMessageLabelName = "Displaying Current Page of Search Results Label";
	
	@FindBy(id="mainForm:List:0")
	protected WebElement searchResultsFirstRow;
	protected String searchResultsFirstRowName = "Personal Profile's Sent Item's First Search Result";
	
	@FindBy(xpath="//tr[@class='rf-dt-r dr-table-firstrow rich-table-firstrow row']")
	protected List<WebElement> searchResultsRowList;
	protected String searchResultsRowListName = "List of Search Results";
	protected String searchResultsRowListName(int i) {
		return "Search Result Row #" + i;
	}
	
	@FindBy(id="mainForm:j_id_7o")
	protected WebElement nextPageButton;
	protected String nextPageButtonName = "'Next Page of Search Results' Button";
	
	//Search Results' Message Labels
	public WebElement searchResultMessageLabel(int pageButtonRowNumber) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("(//tr[@id='mainForm:List:" + pageButtonRowNumber + "']//span[@style='color:black;text-align:left;'])[4]"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + searchResultMessageLabelName(pageButtonRowNumber) + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + searchResultMessageLabelName(pageButtonRowNumber) + "\" was not located", "The WebElement \"" + searchResultMessageLabelName(pageButtonRowNumber) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String searchResultMessageLabelName = "Personal Profile's Sent Item's Message Label";
	protected String searchResultMessageLabelName(int i) {
		return "Personal Profile's Sent Item's Search Result Row #" + i + "'s 'Message' Label";
	}
	
}