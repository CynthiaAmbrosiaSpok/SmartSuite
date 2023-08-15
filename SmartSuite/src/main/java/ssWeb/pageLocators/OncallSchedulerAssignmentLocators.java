package ssWeb.pageLocators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class OncallSchedulerAssignmentLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Declare Variable(s)
//	protected String timeRemark = "0";
	protected String timeRemark ;
	protected String currentAssignmentShiftDate;
	protected String nextAssignmentShiftDate;
	
	//Constructor
	public OncallSchedulerAssignmentLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	// ~~~ Perform Search ~~~ //
	@FindBy(id="mainForm:groupName")
	protected WebElement groupNameField;
	protected String groupNameFieldName = "Scheduler Assignment's 'Group Name' Search Field";
	
	@FindBy(id="mainForm:groupId")
	protected WebElement groupIDField;
	protected String groupIDFieldName = "Scheduler Assignment's 'Group ID' Search Field";
	
	@FindBy(id="mainForm:submitButton")
	protected WebElement searchButton;
	protected String searchButtonName = "Scheduler Assignment's 'Submit' Search Button";
	
	@FindBy(id="mainForm:onCallGroupTree:0:0:mancrazy")
	protected WebElement searchResultFirstListing;
	protected String searchResultFirstListingName = "Scheduler Assignment's First Search Result's 'Name' Label";
	
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
	
	// ~~~ Assignment ~~~ //
	
	@FindBy(xpath="//label[text()='New']")
	protected WebElement newButton;
	protected String newButtonName = "'Create New Scheduler Assignment' Button";
		
		@FindBy(id="mainForm:newCreateAssignmentTable:0:messagingId")
		protected WebElement newMsgIDField;
		protected String newMsgIDFieldName = "'Msg ID' Field for Creating a Scheduler Assignment";
		
		@FindBy(id="mainForm:newCreateAssignmentTable:0:startDateTime")
		protected WebElement newStartDateField;
		protected String newStartDateFieldName = "'Start Time' Field for Creating a Scheduler Assignment";
		
		@FindBy(id="mainForm:newCreateAssignmentTable:0:endDateTime")
		protected WebElement newEndDateField;
		protected String newEndDateFieldName = "'End Time' Field for Creating a Scheduler Assignment";
		
		@FindBy(id="mainForm:newCreateAssignmentTable:0:remark")
		protected WebElement remarkField;
		protected String remarkFieldName = "'Remark' Field for Creating a Scheduler Assignment";
		
	@FindBy(xpath="//label[@title='Save']")
	protected WebElement saveButton;
	protected String saveButtonName = "'Save' Scheduler Assignment Button";
	
	@FindBy(xpath="//label[@title='Delete']")
	protected WebElement deleteButton;
	protected String deleteButtonName = "'Delete' Scheduler Assignment Button";
	
	@FindBy(xpath="//input[@id='mainForm:assignmentDataTable:0:j_id_o_b_23_6'] | //input[@id='mainForm:assignmentDataTable:0:j_id_p_b_23_6']")
	protected WebElement schedulerAssignmentCheckbox;
	protected String schedulerAssignmentCheckboxName = "'Checkbox' for the First Listed Scheduler Assignment";
	
	@FindBy(id="mainForm:assignmentDataTable:0:messagingId")
	protected WebElement schedulerAssignmentMsgIDField;
	protected String schedulerAssignmentMsgIDFieldName = "'Msg ID' Field for the First Listed Scheduler Assignment";
	
	@FindBy(id="mainForm:assignmentDataTable:0:remark")
	protected WebElement schedulerAssignmentRemarkField;
	protected String schedulerAssignmentRemarkFieldName = "'Remark' Field for the First Listed Scheduler Assignment";
	
	public WebElement existingCheckbox(int i) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:assignmentDataTable:" + i + ":j_id_p_b_23_6'] | //input[@id='mainForm:assignmentDataTable:" + i + ":j_id_o_b_23_6']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + existingCheckboxName(i) + "\" was not located", "existingCheckbox");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + existingCheckboxName(i) + "\" was not located", "The WebElement \"" + existingCheckboxName(i) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String existingCheckboxName = "'Checkbox' for an Existinng Scheduler Assignment";
	protected String existingCheckboxName(int i) {
		return "'Checkbox' for the Existing Scheduler Assignment #" + i;
	}
	
	public WebElement existingStartField(int i) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:assignmentDataTable:" + i + ":startDate']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + existingStartFieldName(i) + "\" was not located", "existingCheckbox");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + existingStartFieldName(i) + "\" was not located", "The WebElement \"" + existingStartFieldName(i) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String existingStartFieldName = "'Start' Field for an Existinng Scheduler Assignment";
	protected String existingStartFieldName(int i) {
		return "'Start' Field for the Existing Scheduler Assignment #" + i;
	}
	
	public WebElement existingRemarkField(int i) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//input[@id='mainForm:assignmentDataTable:" + i + ":remark']"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + existingRemarkFieldName(i) + "\" was not located", "existingRemarkField");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + existingRemarkFieldName(i) + "\" was not located", "The WebElement \"" + existingRemarkFieldName(i) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String existingRemarkFieldName = "'Remark' Field for an Existinng Scheduler Assignment";
	protected String existingRemarkFieldName(int i) {
		return "'Remark' Field for the Existing Scheduler Assignment #" + i;
	}
	
	// ~~~ Move Assignment ~~~ //
	
	@FindBy(xpath="//label[@title='Next Shift']")
	protected WebElement nextShiftButton;
	protected String nextShiftButtonName = "Scheduler Assignment's 'Next Shift' Button";
		
		@FindBy(id="mainForm:newCreateAssignmentTable:0:startDateTime")
		protected WebElement nextShiftStartDateField;
		protected String nextShiftStartDateFieldName = "Scheduler Assignment's 'Next Shift, Start Date' Field";
		
	@FindBy(xpath="//label[@title='Next Day']")
	protected WebElement nextDayButton;
	protected String nextDayButtonName = "Scheduler Assignment's 'Next Day' Button";
		
		@FindBy(id="mainForm:newCreateAssignmentTable:0:startDateTime")
		protected WebElement nextDayStartDateField;
		protected String nextDayStartDateFieldName = "Scheduler Assignment's 'Next Day, Start Date' Field";
		
	@FindBy(xpath="//img[@src='/smartweb/img/controls/arrow-next.gif']")
	protected WebElement nextPageButton;
	protected String nextPageButtonName = "Scheduler Assignment's 'Next Page' of Search Results Button";
	
	@FindBy(xpath="//div[@id='mainForm:scroll_2']//span[contains(text(), 'Displaying')]")
	protected WebElement searchCountMessageLabel;
	protected String searchCountMessageLabelName = "Displaying Current Page of Search Results Label";
	
	public int getSearchResultCount() {
		//Return the value listed in the 'Search Results' label that appears when multiple pages appear
		try {
			//Initialize Variable(s)
			String searchCountMessage = searchCountMessageLabel.getText(); //Retrieve the message indicating how many search results were returned
			String[] splitMessage = searchCountMessage.split(" ");
			
			return Integer.parseInt(splitMessage[splitMessage.length-1]);
		} catch (Exception e) {
			return 0;
		}
	}
	
	// ~~~ Instructions ~~~ //
	
	@FindBy(xpath="//label[text()='Instructions']")
	protected WebElement instructionsButton;
	protected String instructionsButtonName = "Scheduler Assignment's 'Create/Modify Instructions' Button";
	
	@FindBy(xpath="//td[text()='SMART WEB']//following-sibling::td/textarea")
	protected WebElement instructionsTextField;
	protected String instructionsTextFieldName = "'Instructions Text' Textarea from the 'Create/Modify Instructions' Pop-up";
	
	@FindBy(xpath="//input[@title='Save Instructions']")
	protected WebElement saveInstructionsButton;
	protected String saveInstructionsButtonName = "'Save Instructions' Button from the 'Create/Modify Instructions' Pop-up";
	
	@FindBy(xpath="//input[@title='Delete Instructions']")
	protected WebElement deleteInstructionsButton;
	protected String deleteInstructionsButtonName = "'Delete Instructions' Button from the 'Create/Modify Instructions' Pop-up";
	
	@FindBy(id="mainForm:instructionEditCloseBtn")
	protected WebElement closeInstructionsButton;
	protected String closeInstructionsButtonName = "'Close Instructions' Button from the 'Create/Modify Instructions' Pop-up";
	
	@FindBy(xpath="//img[@title='Click To Show Instructions']")
	protected WebElement existingInstructionsButton;
	protected String existingInstructionsButtonName = "Scheduler Assignment's 'Current Instructions' Button";
	
	@FindBy(xpath="//textarea[@name='j_id_y'] | //textarea[@name='j_id_z']")
	protected WebElement existingInstructionsField;
	protected String existingInstructionsFieldName = "'Instructions Text' Textarea from the 'Current Instructions' Pop-up";
	
	@FindBy(xpath="//img[@onclick='hideInstruction();']")
	protected WebElement closeExistingInstructionsField;
	protected String closeExistingInstructionsFieldName = "'Close Instructions' Button from the 'Current Instructions' Pop-up";
	
	// ~~~ Show Shifts ~~~ //
	
	@FindBy(xpath="//img[@title='Shifts']")
	protected WebElement shiftsButton;
	protected String shiftsButtonName = "Scheduler Assignment's 'Shifts' Button";
		
		@FindBy(id="mainForm:shiftlisttable:tb")
		protected WebElement shiftsTable;
		protected String shiftsTableName = "Scheduler Assignment's 'Shifts Table' from the 'Shifts' Button Pop-up";
		
	// ~~~ Show Lists ~~~ //
	
	@FindBy(xpath="//img[@title='List']")
	protected WebElement listButton;
	protected String listButtonName = "Scheduler Assignment's 'List' Button";
		
		@FindBy(xpath="//label[@id='mainForm:j_id_o_b_2y_1:groupmemberData:0:messagingId'] | //label[@id='mainForm:j_id_p_b_2y_1:groupmemberData:0:messagingId']")
		protected WebElement msgIDLabel;
		protected String msgIDLabelName = "The First 'Msg ID' Label from the Scheduler Assignment's 'List' Button Pop-up";
		
}