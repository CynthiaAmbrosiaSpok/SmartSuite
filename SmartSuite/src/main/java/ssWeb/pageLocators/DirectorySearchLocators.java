package ssWeb.pageLocators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.ExcelMethods;
import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class DirectorySearchLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public DirectorySearchLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Search Fields ~~~ //
	
	@FindBy(id="mainForm:title")
	protected WebElement titleField;
	protected String titleFieldName = "'Title' Search Field";
	
	@FindBy(id="mainForm:firstName")
	protected WebElement firstNameField;
	protected String firstNameFieldName = "'First Name' Search Field";
	
	@FindBy(id="mainForm:lastName")
	protected WebElement lastNameField;
	protected String lastNameFieldName = "'Last Name' Search Field";
	
	@FindBy(id="mainForm:department")
	protected WebElement departmentField;
	protected String departmentFieldName = "'Department' Search Field";
	
	@FindBy(id="mainForm:phoneNumber")
	protected WebElement phoneNumberField;
	protected String phoneNumberFieldName = "'Phone Number' Search Field";
	
	@FindBy(id="mainForm:phoneNumberType")
	protected WebElement phoneNumberTypeDropDown;
	protected String phoneNumberTypeDropDownName = "'Phone Number Type' Drop Down";
	
	@FindBy(id="mainForm:listingalias")
	protected WebElement listingAliasField;
	protected String listingAliasFieldName = "'Listing Alias' Search Field";
	
	@FindBy(xpath="//span[contains(text(), 'Search')]")
	protected WebElement searchButton;
	protected String searchButtonName = "'Perform Search' Button";
	
	@FindBy(xpath="//a[@id='mainForm:j_id_2u']")
	protected WebElement searchButton2;
	protected String searchButton2Name = "'Perform Search' Button";
	
	@FindBy(xpath="//a[@id='mainForm:j_id_3_1_2z']//span[contains(text(), 'Reset')]")
	protected WebElement resetButton;
	protected String resetButtonName = "'Reset Search Criteria' Button";
	
	@FindBy(id="mainForm:deptTree:0:0:j_id_3e_1_f")
	protected WebElement searchDepartmentsListing;
	protected String searchDepartmentsListingName = "Directory Search Results' First 'Department' Listing";
	
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//tr[@class='dr-table-firstrow rich-table-firstrow row']")
	protected List<WebElement> searchResultsRowList;
	protected String searchResultsRowListName = "List of Rows for the Search Results";
	
	// ~~~ Send a Page ~~~ //
	
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//td[@class='dr-table-cell rich-table-cell'][1]//img[@src='/smartweb/img/icons/pagerIcon.png']")
	protected List<WebElement> pageButton;
	protected String pageButtonName = "Bring up the 'Send a Page Form' Button";
	protected String pageButtonName(int i) {
		return "Search Result Row #" + i + "'s 'Page' Button";
	}
		
		@FindBy(id="mainForm:messageText")
		protected WebElement pageMessageField;
		protected String pageMessageFieldName = "'Enter Text for a Page' Field";
		
		@FindBy(id="mainForm:sendMessageButton")
		protected WebElement sendPageButton;
		protected String sendPageButtonName = "'Send a Page' Button";
		
	// ~~~ Search Results ~~~ //
	
	@FindBy(xpath="//span[@class='captionText']")
	protected WebElement searchResultsCountLabel;
	protected String searchResultsCountLabelName = "Displaying Current Page of Search Results Label";
	
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//td[@class='dr-table-cell rich-table-cell'][2]")
	protected List<WebElement> searchResultsNameField;
	protected String searchResultsNameFieldName = "List of Search Results' 'Name' Label";
	protected String searchResultsNameFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Name' Label";
	}
		
		@FindBy(xpath="//a[@title='Click to view more details on this directory record']")
		protected List<WebElement> searchResultsNameLink;
		protected String searchResultsNameLinkName(int i) {
			return "Search Result Row #" + i + "'s 'Name' Link";
		}
		
		@FindBy(xpath="//img[@src='/smartweb/img/icons/pagerIcon.png']")
		protected List<WebElement> sendPageDetailViewButton;
		protected String sendPageDetailViewButtonName = "'Send a Page' via Search Result's Detail Page Button";
		
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//td[@class='dr-table-cell rich-table-cell'][3]")
	protected List<WebElement> searchResultsPhoneNumberTypeField;
	protected String searchResultsPhoneNumberTypeFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Phone Number Type' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//td[@class='dr-table-cell rich-table-cell'][4]")
	protected List<WebElement> searchResultsTitleField;
	protected String searchResultsTitleFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Title' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//td[@class='dr-table-cell rich-table-cell'][5]")
	protected List<WebElement> searchResultsDepartmentField;
	protected String searchResultsDepartmentFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Department' Label";
	}
	
	@FindBy(xpath="//tbody[@id='mainForm:peopleresultsTable:tbody_element']//td[@class='dr-table-cell rich-table-cell'][6]")
	protected List<WebElement> searchResultsAliasField;
	protected String searchResultsAliasFieldName(int i) {
		return "Search Result Row #" + i + "'s 'Alias' Label";
	}
	
	// ~~~ Navigate Directory Pages ~~~ //
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-next.gif']")
	protected WebElement nextPageOfSearchResultsButton;
	protected String nextPageOfSearchResultsButtonName = "'Next Page of Search Results' Button";
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-ff.gif']")
	protected WebElement fastForwardPageOfSearchResultsButton;
	protected String fastForwardPageOfSearchResultsButtonName = "'Fast Forward Page of Search Results' Button";
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-last.gif']")
	protected WebElement lastPageOfSearchResultsButton;
	protected String lastPageOfSearchResultsButtonName = "'Last Page of Search Results' Button";
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-previous.gif']")
	protected WebElement previousPageOfSearchResultsButton;
	protected String previousPageOfSearchResultsButtonName = "'Previous Page of Search Results' Button";
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-fr.gif']")
	protected WebElement fastRewindPageOfSearchResultsButton;
	protected String fastRewindPageOfSearchResultsButtonName = "'Fast Forward Page of Search Results' Button";
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-first.gif']")
	protected WebElement firstPageOfSearchResultsButton;
	protected String firstPageOfSearchResultsButtonName = "'First Page of Search Results' Button";
	
	// ~~~ Show 'Send to All Devices' WebElements ~~~ //
	
	@FindBy(xpath="//img[@src='/smartweb/img/sendtoalldevices.png']")
	protected WebElement sendToAllDevicesIcon;
	protected String sendToAllDevicesIconName = "'Send to All Devices' Icon/Button";
	
}