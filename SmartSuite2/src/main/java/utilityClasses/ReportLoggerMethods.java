package utilityClasses;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportLoggerMethods {
	
	//Initialize the report logger variables
	private ConstantVariables constantVariables;
	private ExtentTest reportLogger;
	private EventFiringWebDriver eDriver;
	
	int maxWaitTime = 3;
	int pollingEveryTimePeriod = 1;
	
	public ReportLoggerMethods(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
		this.reportLogger = reportLogger;
		this.eDriver = eDriver;
		
		constantVariables = new ConstantVariables();
	}
	
	public void getScreenshot(String screenshotName) {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) eDriver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			
			String destination = constantVariables.screenshotLocation + screenshotName + dateName + ".png";
//			String destination2 = constantVariables.screenshotLocation2 + screenshotName + dateName + ".png";
			File finalDestination = new File(destination);
			//FileUtils.copyFile(source, finalDestination);
			FileHandler.copy(source, finalDestination);
			reportLogger.log(LogStatus.FAIL,  reportLogger.addScreenCapture(destination)); //adds screenshot to ExtentReport
//			reportLogger.log(LogStatus.FAIL, reportLogger.addScreenCapture(destination) + reportLogger.addScreenCapture(destination2)); //adds screenshot to ExtentReport
		} catch (Exception e) {
			System.out.println("Unable to properly take a screenshot and save it to the report");
			System.out.println(e);
		}
	}
	
	public void reportInfo(String reportMessage) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.INFO, reportMessage);
	}
	
	public void reportSuccessfulCheckpoint(String reportMessage) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.PASS, reportMessage);
	}
	
	public void reportFailedCheckpoint(String reportMessage, String screenshotName) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.FAIL, reportMessage);
		
		//Take a screenshot of the application to record the error
		getScreenshot(screenshotName);
	}
	
	public void reportFailedMessage(String reportMessage) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.FAIL, reportMessage);
	}
	
	public void reportSkippedCheckpoint(String reportMessage, String screenshotName) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.SKIP, reportMessage);
		
		//Take a screenshot of the application to record the error
		getScreenshot(screenshotName);
	}
	
	public void reportSkippedMessage(String reportMessage) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.SKIP, reportMessage);
	}
	
	public void reportUnknownMessage(String reportMessage) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.UNKNOWN, reportMessage);
	}
	
	public void printWebElementName(String webElementName) {
//		System.out.println(webElementName);
//		reportInfo(webElementName);
	}
	
	public void click(WebElement webElement, String webElementName) {
		try {
			printWebElementName("Click -> " + webElementName + " - " + webElement);
			
			//Click the WebElement
			webElement.click();
			
//			reportSuccessfulCheckpoint("Successfully clicked -> " + webElementName);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be clicked
			reportFailedCheckpoint("Error: Unable to click the WebElement \"" + webElementName + "\"", "click");
			
			//Assert the status of the WebElement's clickability
			Assert.assertEquals("The WebElement \"" + webElementName + "\" is not clickable", "The WebElement \"" + webElementName + "\" was clicked");
		}
	}
	
	public void doubleClickPerform(WebElement webElement, String webElementName) {
		try {
			printWebElementName("Double-Click -> " + webElementName + " - " + webElement);
			
			//Double-Click the WebElement
			Actions actions = new Actions(eDriver);
			actions.doubleClick(webElement).perform();
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be double-clicked
			reportFailedCheckpoint("Error: Unable to double-click the WebElement \"" + webElementName + "\"", "click");
			
			//Assert the status of the WebElement's (double) clickability
			Assert.assertEquals("The WebElement \"" + webElementName + "\" is not (double) clickable", "The WebElement \"" + webElementName + "\" was double-clicked");
		}
	}
	
	public void doubleClick(WebElement webElement, String webElementName) {
		try {
			printWebElementName("Double-Click -> " + webElementName + " - " + webElement);
			
			//Double-Click the WebElement
			Actions actions = new Actions(eDriver);
			actions.doubleClick(webElement);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be double-clicked
			reportFailedCheckpoint("Error: Unable to double-click the WebElement \"" + webElementName + "\"", "click");
			
			//Assert the status of the WebElement's (double) clickability
			Assert.assertEquals("The WebElement \"" + webElementName + "\" is not (double) clickable", "The WebElement \"" + webElementName + "\" was double-clicked");
		}
	}
	
	public boolean isAlertDisplayed() {
		try {
			printWebElementName("isAlertDisplayed");
			
			//Check if the Alert Pop-Up is Displayed
			eDriver.switchTo().alert();
			
//			eDriver.switchTo().defaultContent();
			
			//Return the 'Alert Display' Status
			return true;
		} catch (Exception e) {
			//Return the 'Alert Display' Status
			return false;
		}
	}
	
	public void acceptAlert() {
		try {
			printWebElementName("acceptAlert");
			
			//Pause the script for a bit
			Thread.sleep(1000);
			
			//Accept the Alert pop-up
			eDriver.switchTo().alert().accept();
//			eDriver.switchTo().alert().
		} catch (Exception e) {
			//Output an error about retrieving the Alert's text
			reportFailedCheckpoint("Error: Unable to accept an Alert pop-up", "acceptAlert");
			
			//Assert the status of retrieving the Alert's text
			Assert.assertEquals("Unable to accept an Alert pop-up", "Able to accept an Alert pop-up");
		}
	}
	
	public boolean isAlertDisplayedAndAccept() {
		try {
			printWebElementName("isAlertDisplayedAndAccept");
			
			//Pause the script for a bit
			Thread.sleep(1000);
			
//			//Accept the Alert pop-up
//			eDriver.switchTo().alert().getText();
			
			//Accept the Alert pop-up
			eDriver.switchTo().alert().accept();
			
			//Return the 'Alert Display' Status
			return true;
		} catch (Exception e) {
			//Return the 'Alert Display' Status
			return false;
		}
	}
	
	public String getAlertText() {
		try {
			printWebElementName("getAlertText");
			
			//Return the Alert text
			return eDriver.switchTo().alert().getText();
		} catch (Exception e) {
			//Output an error about retrieving the Alert's text
			reportFailedCheckpoint("Error: Unable to get text from an Alert pop-up", "getAlertText");
			
			//Assert the status of retrieving the Alert's text
			Assert.assertEquals("Unable to get text from an Alert pop-up", "Able to get text from the Alert pop-up");
			
			//Return nothing, since the Alert was not found
			return "";
		}
	}
	
	public void refreshPage() {
		try {
			printWebElementName("refreshPage");
			
			//Refresh the Current Page
			eDriver.navigate().refresh();
		} catch (Exception e) {
			//Output an error about Refreshing the Current Page
			reportFailedCheckpoint("Error: The Current Page ", "refreshPage");
			
			//Assert the status of Refreshing the Current Page
			Assert.assertEquals("Unable to get text from an Alert pop-up", "Able to get text from the Alert pop-up");
		}
	}
	
	public void clear(WebElement webElement, String webElementName) {
		try {
			printWebElementName("Clear Contents -> " + webElementName + " - " + webElement);
			
			//Clear any value in the WebElement
			webElement.clear();
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to receive input
			reportFailedCheckpoint("Error: Unable to clear the value in the WebElement \"" + webElementName + "\"", "clear");
			
			//Assert the status of the WebElement's ability to clear their value
			Assert.assertEquals("The WebElement \"" + webElementName + "\" cannot clear their value", "The WebElement \"" + webElementName + "\" can clear their value");
		}
	}
	
	public void sendKeys(final WebElement webElement, String webElementName, int sendText) {
		sendKeys(webElement, webElementName, String.valueOf(sendText));
	}
	
	public void sendKeys(WebElement webElement, String webElementName, String sendText) {
		try {
			printWebElementName("Send Keys (" + sendText + ") -> " + webElementName + " - " + webElement);
			
			//Enter a value into the WebElement
			webElement.sendKeys(sendText);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to receive input
			reportFailedCheckpoint("Error: Unable to send keys to the WebElement \"" + webElementName + "\"", "sendKeys");
			
			//Assert the status of the WebElement's ability to receive input
			Assert.assertEquals("The WebElement \"" + webElementName + "\" cannot receive any input", "The WebElement \"" + webElementName + "\" received input");
		}
	}
	
	public void selectDropDown(WebElement webElement, String webElementName, String selectText) {
		try {
			printWebElementName("Select Drop-Down (" + selectText + ") -> " + webElementName + " - " + webElement);
			
			//Select Text from the DropDown
			Select selectPhoneNumberType = new Select(webElement);
			selectPhoneNumberType.selectByVisibleText(selectText);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to receive input
			reportFailedCheckpoint("Error: Unable to select a drop-down option from the WebElement \"" + webElementName + "\"", "selectDropDown");
			
			//Assert the status of the WebElement's ability to receive input
			Assert.assertEquals("Unable to select a drop-down option from the WebElement \"" + webElementName + "\"", "Able to select a drop-down option from the WebElement \"" + webElementName + "\"");
		}
	}
	
	public String getAttribute(WebElement webElement, String webElementName, String attribute) {
		try {
			printWebElementName("Get Attribute (" + attribute + ") -> " + webElementName + " - " + webElement);
			
			//Return the value of the specified attribute
			return webElement.getAttribute(attribute);
		} catch (Exception e) {
			//Output an error about the WebElement and/or attribute being located
			reportFailedCheckpoint("Error: Unable to get the attribute \"" + attribute + "\" from the WebElement \"" + webElementName + "\"", "getAttribute");
			
			//Assert the status of the WebElement's ability to return an attribute value
			Assert.assertEquals("Unable to get the attribute \"" + attribute + "\" from the WebElement \"" + webElementName + "\"", "Able to get the attribute \"" + attribute + "\" from the WebElement \"" + webElementName + "\"");
		}
		
		//Return nothing due to the WebElement and/or Attribute not being found
		return "";
	}
	
	public String getCssValue(WebElement webElement, String webElementName, String cssValue) {
		try {
			printWebElementName("Get CSS Value (" + cssValue + ") -> " + webElementName + " - " + webElement);
			
			//Return the value of the specified CSS Value
			return webElement.getCssValue(cssValue);
		} catch (Exception e) {
			//Output an error about the WebElement and/or CSS Value being located
			reportFailedCheckpoint("Error: Unable to get the CSS Value \"" + cssValue + "\" from the WebElement \"" + webElementName + "\"", "getCssValue");
			
			//Assert the status of the WebElement's ability to return a CSS Value
			Assert.assertEquals("Unable to get the CSS Value \"" + cssValue + "\" from the WebElement \"" + webElementName + "\"", "Able to get the CSS Value \"" + cssValue + "\" from the WebElement \"" + webElementName + "\"");
		}
		
		//Return nothing due to the WebElement and/or Attribute not being found
		return "";
	}
	
	public String getText(WebElement webElement, String webElementName) {
		try {
			printWebElementName("Get Text -> " + webElementName + " - " + webElement);
			
			//Retrieve the WebElement's text
			return webElement.getText();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the text of the specified WebElement
			reportFailedCheckpoint("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "getText");
			
			//Assert the status of the retrieving the WebElement's text
			Assert.assertEquals("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "Able to retrieve the text from the WebElement \"" + webElementName + "\"");
		}
		
		//Return no value, since the WebElement's text could not be returned properly
		return "";
	}
	
	public String getLowercaseText(WebElement webElement, String webElementName) {
		try {
			printWebElementName("Get Lower-Case Text -> " + webElementName + " - " + webElement);
			
			//Retrieve the WebElement's text
			return webElement.getText().toLowerCase();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the text of the specified WebElement
			reportFailedCheckpoint("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "getLowercaseText");
			
			//Assert the status of the retrieving the WebElement's text
			Assert.assertEquals("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "Able to retrieve the text from the WebElement \"" + webElementName + "\"");
		}
		
		//Return no value, since the WebElement's text could not be returned properly
		return "";
	}
	
	public boolean isDisplayed(WebElement webElement, String webElementName) {
		try {
			printWebElementName("isDisplayed -> " + webElementName + " - " + webElement);
			
			//Output the boolean value regarding whether the WebElement is displayed or not
			return webElement.isDisplayed();
		} catch (Exception e) {
			//Return no value, since the WebElement's displayed status could not be returned properly
			return false;
		}
	}
	
	public boolean isSelected(WebElement webElement, String webElementName) {
		try {
			printWebElementName("isSelected -> " + webElementName + " - " + webElement);
			
			//Output the boolean value regarding whether the WebElement is checked or not
			return webElement.isSelected();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the status of the specified WebElement's checked status
			reportFailedCheckpoint("Error: Unable to verify if the WebElement \"" + webElementName + "\" is checked or not", "isChecked");
			
			//Assert the status of the WebElement's checked status
			Assert.assertEquals("Error: Unable to verify if the WebElement \"" + webElementName + "\" is checked or not", "Able to verify if the WebElement \"" + webElementName + "\" is checked or not");
			
			//Return no value, since the WebElement's checked status could not be returned properly
			return false;
		}
	}
	
	public boolean isEnabled(final WebElement webElement, String webElementName) {
		try {
			printWebElementName("isEnabled -> " + webElementName + " - " + webElement);
			
			//Output the boolean value regarding whether the WebElement is checked or not
			return webElement.isEnabled();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the status of the specified WebElement's checked status
			reportFailedCheckpoint("Error: Unable to verify if the WebElement \"" + webElementName + "\" is enabled or not", "isEnabled");
			
			//Assert the status of the WebElement's checked status
			Assert.assertEquals("Error: Unable to verify if the WebElement \"" + webElementName + "\" is enabled or not", "Able to verify if the WebElement \"" + webElementName + "\" is enabled or not");
			
			//Return no value, since the WebElement's checked status could not be returned properly
			return false;
		}
	}
	
	public int getSize(List<WebElement> webElement, String webElementName) {
		try {
			printWebElementName("getSize -> " + webElementName + " - " + webElement);
			
			//Retrieve the WebElement's size
			return webElement.size();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the size of the specified WebElement
			reportFailedCheckpoint("Error: Unable to retrieve the size of the WebElement \"" + webElementName + "\"", "getSize");
			
			//Assert the status of the retrieving the WebElement's size
			Assert.assertEquals("Error: Unable to retrieve the size of the WebElement \"" + webElementName + "\"", "Able to retrieve the size of the WebElement \"" + webElementName + "\"");
		}
		
		//Return no value, since the WebElement's text could not be returned properly
		return 0;
	}
	
	public void highlightWebElement(WebElement webElement, String webElementName) {
		try {
			printWebElementName("highlightWebElement -> " + webElementName + " - " + webElement);
			
			//Highlight the specified WebElement
			JavascriptExecutor js = (JavascriptExecutor) eDriver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", webElement);
		} catch (Exception e) {
			//Output an error about being unable to highlight the specified WebElement
			reportFailedCheckpoint("Error: Unable to highlight the WebElement \"" + webElementName + "\"", "highlightWebElement");
			
			//Assert the status of the WebElement being able to be highlighted
			Assert.assertEquals("Error: Unable to highlight the WebElement \"" + webElementName + "\"", "Able to highlight the WebElement \"" + webElementName + "\"");
		}
	}
	
}