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

public class PagingSearchLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ExcelMethods excelMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PagingSearchLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		excelMethods = new ExcelMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Paging Search Title/Tab ~~~ //
	
	@FindBy(id="mainForm:j_id_l_6_4")
	protected WebElement pagingSearchTitle;
	protected String pagingSearchTitleName = "'Paging' Tab";
	
	// ~~~ Perform Search ~~~ //
	
	@FindBy(id="mainForm:first_name")
	protected WebElement firstNameField;
	protected String firstNameFieldName = "Paging Tab's 'First Name' Search Field";
	
	@FindBy(id="mainForm:last_name")
	protected WebElement lastNameField;
	protected String lastNameFieldName = "Paging Tab's 'Last Name' Search Field";
	
	@FindBy(id="mainForm:department")
	protected WebElement departmentField;
	protected String departmentFieldName = "Paging Tab's 'Department' Search Field";
	
	@FindBy(id="mainForm:name")
	protected WebElement groupFunctionNameField;
	protected String groupFunctionNameFieldName = "Paging Tab's 'Group/Function Name' Search Field";
	
	@FindBy(id="mainForm:l_id")
	protected WebElement idField;
	protected String idFieldName = "Paging Tab's 'ID' Search Field";
	
	@FindBy(xpath="//span[contains(text(), 'Search')]")
	protected WebElement searchButton;
	protected String searchButtonName = "Paging Tab's 'Search' Search Button";
	
	// ~~~ Search Results ~~~ //
	
	@FindBy(xpath="//span[@class='captionText']")
	protected WebElement searchResultsCountLabel;
	protected String searchResultsCountLabelName = "Displaying Current Page of Search Results Label";
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']")
	protected List<WebElement> searchResultsRowList;
	protected String searchResultsRowListName = "List of Paging Search Results";
	protected String searchResultsRowListName(int i) {
		return "Paging Search Result Row #" + i;
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[2]//span")
	protected List<WebElement> searchResultsIDField;
	protected String searchResultsIDFieldName = "List of Paging Search Results' 'ID' Label";
	protected String searchResultsIDFieldName(int i) {
		return "Paging Search Result Row #" + i + "'s 'ID' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[3]//span")
	protected List<WebElement> searchResultsNameField;
	protected String searchResultsNameFieldName = "List of Paging Search Results' 'Name' Label";
	protected String searchResultsNameFieldName(int i) {
		return "Paging Search Result Row #" + i + "'s 'Name' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[4]//span")
	protected List<WebElement> searchResultsDepartmentField;
	protected String searchResultsDepartmentFieldName = "List of Paging Search Results' 'Department' Label";
	protected String searchResultsDepartmentFieldName(int i) {
		return "Paging Search Result Row #" + i + "'s 'Department' Label";
	}
	
	@FindBy(xpath="//tr[@class='dr-table-firstrow rich-table-firstrow row']//td[5]//span")
	protected List<WebElement> searchResultsStatusField;
	protected String searchResultsStatusFieldName = "List of Paging Search Results' 'Status' Label";
	protected String searchResultsStatusFieldName(int i) {
		return "Paging Search Result Row #" + i + "'s 'Status' Label";
	}
	
	@FindBy(xpath="//select[@id='mainForm:searchResultsTable:0:j_id_31']//option | //select[@id='mainForm:searchResultsTable:0:j_id_32']//option")
	protected List<WebElement> searchResultsTypeDropDownValue;
	protected String searchResultsTypeDropDownValueName = "List of Paging Search Results' 'c' Label";
	protected String searchResultsTypeDropDownValueName(int i) {
		return "Paging Search Result Row #" + i + "'s '/select[@id='mainForm:searchResultsTable:0:j_id_31']//option' Label";
	}
	
	// ~~~ Send a Page ~~~ //
	
	@FindBy(id="mainForm:messageText")
	protected WebElement pageMessageField;
	protected String pageMessageFieldName = "'Enter Text for a Page' Field";
	
	@FindBy(id="mainForm:sendMessageButton")
	protected WebElement sendPageButton;
	protected String sendPageButtonName = "'Send a Page' Button";
	
	// ~~~ Personal Group ~~~ //
	
	@FindBy(xpath="//img[@src='/smartweb/img/controls/addControl.png']")
	protected List<WebElement> addRecipientList;
	protected String addRecipientListName = "List of Paging Search Results' 'Add Recipient' Button";
	protected String addRecipientListName(int i) {
		return "Paging Search Result Row #" + i + "'s 'Add Recipient' Button";
	}
	
	@FindBy(xpath="//a[@id='mainForm:composeMessageButton']/span[contains(text(), 'Compose Message')]")
	protected WebElement composeMessageButton;
	protected String composeMessageButtonName = "'Compose Message' Button";
	
	@FindBy(id="mainForm:clearRecipientsButton")
	protected WebElement clearRecipientsButton;
	protected String clearRecipientsButtonName = "'Clear Recipients' Button";
	
	@FindBy(id="mainForm:pmgSaveAsName")
	protected WebElement personalMessageGroupField;
	protected String personalMessageGroupFieldName = "'Personal Message Group' Input Field";
	
	@FindBy(id="mainForm:linkSaveNewGroup")
	protected WebElement personalMessageGroupSaveButton;
	protected String personalMessageGroupSaveButtonName = "Save 'Personal Message Group' Button";
	
	@FindBy(xpath="//a[@id='mainForm:personalMsgGrpTree:0:0:j_id_80_h'] | //a[@id='mainForm:personalMsgGrpTree:0:0:j_id_80_h']")
	protected WebElement firstPersonalMessageListing;
	protected String firstPersonalMessageListingName = "First, Existing 'Personal Message Group'";
	
	@FindBy(xpath="//a[@id='mainForm:personalMsgGrpTree:0:0:j_id_80_8'] | //a[@id='mainForm:personalMsgGrpTree:0:0:j_id_81_8']")
	protected WebElement firstPersonalMessageDeleteButton;
	protected String firstPersonalMessageDeleteButtonName = "Delete First Existing 'Personal Message Group'";
	
}