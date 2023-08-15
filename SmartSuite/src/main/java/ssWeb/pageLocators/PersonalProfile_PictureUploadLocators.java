package ssWeb.pageLocators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utilityClasses.GeneralMethods;
import utilityClasses.ReportLoggerMethods;

public class PersonalProfile_PictureUploadLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PersonalProfile_PictureUploadLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	/* Profile Tab */
	
	@FindBy(id="uploadForm:file")
	protected WebElement uploadFileButton;
	protected String uploadFileButtonName = "'Upload a File' Button";
	
	@FindBy(xpath="//form[@id='uploadForm']//span[contains(text(), 'Save')]")
	protected WebElement saveButton;
	protected String saveButtonName = "'Save' Uploaded File";
	
	@FindBy(xpath="//form[@id='uploadForm']//span[contains(text(), 'Delete')]")
	protected WebElement deleteButton;
	protected String deleteButtonName = "'Delete' Uploaded File";
	
	@FindBy(xpath="//form[@id='uploadForm']/span")
	protected WebElement pictureUploadResponseMessageLabel;
	protected String pictureUploadResponseMessageLabelName = "Uploaded File Error Message";
	
}