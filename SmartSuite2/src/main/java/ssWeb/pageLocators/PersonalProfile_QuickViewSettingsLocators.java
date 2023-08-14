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

public class PersonalProfile_QuickViewSettingsLocators {
	
	//Initialize Variable(s)
	protected ExtentTest reportLogger;
	protected EventFiringWebDriver eDriver;
	protected GeneralMethods genMethods;
	protected ReportLoggerMethods reportLoggerMethods;
	
	//Constructor
	public PersonalProfile_QuickViewSettingsLocators(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		PageFactory.initElements(eDriver, this);
		
		//initialize objects
		genMethods = new GeneralMethods();
		reportLoggerMethods = new ReportLoggerMethods(eDriver, reportLogger);
	}
	
	/* Quick View Settings Tab */
	
	@FindBy(xpath="//span[text()='Add On Call Groups']")
	protected WebElement addOncallGroup;
	protected String addOncallGroupName = "'Add On Call Groups' Button";
	
	@FindBy(xpath="//span[text()='Save Settings']")
	protected WebElement saveOncallGroup;
	protected String saveOncallGroupName = "'Save Settings' Button";
	
	@FindBy(xpath="//span[text()='Remove On Call Groups']")
	protected WebElement removeOncallGroup;
	protected String removeOncallGroupName = "'Remove On Call Groups' Button";
	
	@FindBy(id="mainForm:List:0:chkSelected")
	protected WebElement firstOncallGroupListingCheckbox;
	public WebElement firstOncallGroupListingCheckbox(String xpathText) {
		try {
			//Return the located WebElement
			return eDriver.findElement(By.xpath("//span[contains(text(), '" + xpathText + "')]/..//following-sibling::td//*[contains(@id, 'chkSelected')]"));
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be located
			reportLoggerMethods.reportFailedCheckpoint("Failure: The WebElement \"" + firstOncallGroupListingCheckboxName(xpathText) + "\" was not located", "GlobalSearchPageButton");
			
			//Assert the status of the WebElement's existence
			Assert.assertEquals("The WebElement \"" + firstOncallGroupListingCheckboxName(xpathText) + "\" was not located", "The WebElement \"" + firstOncallGroupListingCheckboxName(xpathText) + "\" was located");
			
			//Return null, since no WebElement was located
			return null;
		}
	}
	protected String firstOncallGroupListingCheckboxName = "First Result's 'Checkbox'";
	protected String firstOncallGroupListingCheckboxName(String oncallGroupName) {
		return "'Checkbox' for the Group Name, '" + oncallGroupName + "' under the 'Quick View Settings' column (on the left side)";
	}
	
}