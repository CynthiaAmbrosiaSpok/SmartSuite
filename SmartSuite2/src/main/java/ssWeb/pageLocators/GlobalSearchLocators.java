package ssWeb.pageLocators;

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

public class GlobalSearchLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public GlobalSearchLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Global Search Tab's Advanced Search Links ~~~ //
	
	@FindBy(xpath="//a[text()='Advanced Directory Search']")
	protected WebElement advancedDirectorySearchLink;
	protected String advancedDirectorySearchLinkName = "'Advanced Directory Search' Link";
	
	@FindBy(xpath="//a[text()='Advanced Paging Search']")
	protected WebElement advancedPagingSearchLink;
	protected String advancedPagingSearchLinkName = "'Advanced Paging Search' Link";
	
	@FindBy(xpath="//a[text()='Advanced Oncall Search']")
	protected WebElement advancedOncallSearchLink;
	protected String advancedOncallSearchLinkName = "'Advanced Oncall Search' Link";
	
	// ~~~ Perform Search & Search Checkboxes ~~~ //
	
	@FindBy(id="mainForm:searchid")
	protected WebElement searchCriteriaField;
	protected String searchCriteriaFieldName = "'Search Criteria' Field";
	
	@FindBy(xpath="//span[contains(text(), 'Search')]")
	protected WebElement searchButton;
	protected String searchButtonName = "'Search' Button";
	
	@FindBy(xpath="//span[contains(text(), 'Max rows')]/parent::td/following-sibling::td/input")
	protected WebElement setMaxSearchRowsField;
	protected String setMaxSearchRowsFieldName = "'Set Max Rows Per Search' Field";
	
	@FindBy(id="mainForm:chkSelected1")
	protected WebElement profileSearchCriteriaCheckbox;
	protected String profileSearchCriteriaCheckboxName = "'Profile' Search Checkbox";
	
	@FindBy(id="mainForm:chkSelected2")
	protected WebElement onCallSearchCriteriaCheckbox;
	protected String onCallSearchCriteriaCheckboxName = "'On Call' Search Checkbox";
	
	@FindBy(id="mainForm:chkSelected3")
	protected WebElement directorySearchCriteriaCheckbox;
	protected String directorySearchCriteriaCheckboxName = "'Directory' Search Checkbox";
	
	// ~~~ Verify Searches ~~~ //
	
	@FindBy(xpath="//div[@id='mainForm:resultsPanel:scroll_3']/span")
	protected WebElement searchResultsCountLabel;
	protected String searchResultsCountLabelName = "Displaying Current Page of Search Results Label";
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']")
	protected List<WebElement> searchResultsRowList;
	protected String searchResultsRowListName = "List of Rows for the Search Results";
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][2]")
	protected List<WebElement> searchResultsNameField;
	protected String searchResultsNameFieldName = "List of Search Results' 'Name' Label";
	protected String searchResultsNameFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Name' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][2]//a")
	protected List<WebElement> searchResultsNameLink;
	protected String searchResultsNameLinkName = "List of Search Results' 'Name' Link";
	protected String searchResultsNameLinkName(int i) {
		return "Search Result Row #" + i + "'s 'Name' Link";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][3]")
	protected List<WebElement> searchResultsTypeField;
	protected String searchResultsTypeFieldName = "List of Search Results' 'Type' Label";
	protected String searchResultsTypeFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Type' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][4]")
	protected List<WebElement> searchResultsTitleField;
	protected String searchResultsTitleFieldName = "List of Search Results' 'Title' Label";
	protected String searchResultsTitleFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Title' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][5]")
	protected List<WebElement> searchResultsDepartmentField;
	protected String searchResultsDepartmentFieldName = "List of Search Results' 'Department' Label";
	protected String searchResultsDepartmentFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Department' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][6]")
	protected List<WebElement> searchResultsSpecialtyField;
	protected String searchResultsSpecialtyFieldName = "List of Search Results' 'Specialty' Label";
	protected String searchResultsSpecialtyFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Specialty' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:resultsPanel:tbody_element']//td[@class='dr-table-cell rich-table-cell'][7]")
	protected List<WebElement> searchResultsAliasField;
	protected String searchResultsAliasFieldName = "List of Search Results' 'Alias' Label";
	protected String searchResultsAliasFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Alias' Label";
	}
	
	// ~~~ Verify Search Result's Pop-up/Detail-Page ~~~ //
	
	@FindBy(xpath="//div[@id='mainForm:personDetailsList']//td[contains(text(), 'Msg ID:')]//following-sibling::td[1]")
	protected WebElement messageIDLabel;
	protected String messageIDLabelName = "'Msg ID' in search result's detail page";
	
	@FindBy(xpath="//textarea[@name='mainForm:instructiontable:0:j_id_8e'] | //textarea[@name='mainForm:instructiontable:0:j_id_8f']")
	protected WebElement instructionsTextArea;
	protected String instructionsTextAreaName = "'Instructions' Textarea in search result's detail page";
	
	@FindBy(xpath="//td[@id='mainForm:j_id_8i:0:j_id_8w'] | //td[@id='mainForm:j_id_8j:0:j_id_8x']")
	protected WebElement searchedResultPhoneNumber;
	protected String searchedResultPhoneNumberName = "'Phone Number' Label in search result's detail page";
	
	@FindBy(xpath="//input[@id='mainForm:j_id_a2'] | //input[@id='mainForm:j_id_a3']")
	protected WebElement closeSearchResultPopup;
	protected String closeSearchResultPopupName = "'Close' Button for the search result's detail page";
	
	// ~~~ Send a Page ~~~ //
	public WebElement pageButton(int pageButtonRowNumber) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//a[@id='mainForm:resultsPanel:" + pageButtonRowNumber + ":j_id_29'] | //a[@id='mainForm:resultsPanel:" + pageButtonRowNumber + ":j_id_2a']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + pageButtonName(pageButtonRowNumber) + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + pageButtonName(pageButtonRowNumber) + "\" was not located", "The WebElement \"" + pageButtonName(pageButtonRowNumber) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String pageButtonName = "Bring up the 'Send a Page Form' Button";
	protected String pageButtonName(int i) {
		return "Search Result Row #" + i + "'s 'Page' Button";
	}
	
	@FindBy(id="mainForm:messageText")
	protected WebElement pageMessageField;
	protected String pageMessageFieldName = "'Page Message' Textarea to Send as a Page";
	
	@FindBy(id="mainForm:sendMessageButton")
	protected WebElement sendPageButton;
	protected String sendPageButtonName = "'Send Page' Button";
	
}