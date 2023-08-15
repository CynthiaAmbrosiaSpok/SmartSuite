package utilityClasses;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportLoggerMethods2 {
	
	//Initialize the report logger variables
	private ConstantVariables constantVariables;
	private ExtentTest reportLogger;
	private EventFiringWebDriver eDriver;
	
	int maxWaitTime = 3;
	int pollingEveryTimePeriod = 1;
	
	public ReportLoggerMethods2(EventFiringWebDriver eDriver, ExtentTest reportLogger) {
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
	
	public void reportSkippedCheckpoint(String reportMessage, String screenshotName) {
		//Output a message to the report & system
		System.out.println(reportMessage);
		reportLogger.log(LogStatus.SKIP, reportMessage);
		
		//Take a screenshot of the application to record the error
		getScreenshot(screenshotName);
	}
	
	public void tmp1(String webElementName) {
		System.out.println(webElementName);
	}
	
	public void click(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
//			//Set the timeout
//			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
//					.withTimeout(Duration.ofSeconds(maxWaitTime))
//					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
//					.ignoring(NoSuchElementException.class);
//			
//			//Locate the WebElement
//			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
//				public WebElement apply(EventFiringWebDriver eDriver) {
//					return webElement;
//				}
//			});
//			
//			WebDriverWait wait2 = new WebDriverWait(eDriver, maxWaitTime); 
//			wait2.until(ExpectedConditions.elementToBeClickable(webElement));
//			
//			//Click the WebElement
//			element.click();
			webElement.click();
			
//			reportSuccessfulCheckpoint("Successfully clicked -> " + webElementName);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be clicked
			reportFailedCheckpoint("Error: Unable to click the WebElement \"" + webElementName + "\"", "click");
			
			//Assert the status of the WebElement's clickability
			Assert.assertEquals("The WebElement \"" + webElementName + "\" is not clickable", "The WebElement \"" + webElementName + "\" was clicked");
		}
	}
	
	public void doubleClickPerform(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			WebDriverWait wait2 = new WebDriverWait(eDriver, maxWaitTime); 
			wait2.until(ExpectedConditions.elementToBeClickable(webElement));
			
			//Double-Click the WebElement
			Actions actions = new Actions(eDriver);
			actions.doubleClick(element).perform();
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be double-clicked
			reportFailedCheckpoint("Error: Unable to double-click the WebElement \"" + webElementName + "\"", "click");
			
			//Assert the status of the WebElement's (double) clickability
			Assert.assertEquals("The WebElement \"" + webElementName + "\" is not (double) clickable", "The WebElement \"" + webElementName + "\" was double-clicked");
		}
	}
	
	public void doubleClick(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			WebDriverWait wait2 = new WebDriverWait(eDriver, maxWaitTime); 
			wait2.until(ExpectedConditions.elementToBeClickable(webElement));
			
			//Double-Click the WebElement
			Actions actions = new Actions(eDriver);
			actions.doubleClick(element);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to be double-clicked
			reportFailedCheckpoint("Error: Unable to double-click the WebElement \"" + webElementName + "\"", "click");
			
			//Assert the status of the WebElement's (double) clickability
			Assert.assertEquals("The WebElement \"" + webElementName + "\" is not (double) clickable", "The WebElement \"" + webElementName + "\" was double-clicked");
		}
	}
	
	public boolean isAlertDisplayed() {
		try {
			tmp1("isAlertDisplayed");
			
			new WebDriverWait(eDriver, maxWaitTime)
				.ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());
			
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
			tmp1("acceptAlert");
			
			new WebDriverWait(eDriver, maxWaitTime)
				.ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());
			
			//Accept the Alert pop-up
			eDriver.switchTo().alert().accept();
		} catch (Exception e) {
			//Output an error about retrieving the Alert's text
			reportFailedCheckpoint("Error: Unable to accept an Alert pop-up", "acceptAlert");
			
			//Assert the status of retrieving the Alert's text
			Assert.assertEquals("Unable to accept an Alert pop-up", "Able to accept an Alert pop-up");
		}
	}
	
	public boolean isAlertDisplayedAndAccept() {
		try {
			tmp1("isAlertDisplayed");
			
//			new WebDriverWait(eDriver, maxWaitTime)
//				.ignoring(NoAlertPresentException.class)
//				.until(ExpectedConditions.alertIsPresent());
			tmp1("2");
			//Accept the Alert pop-up
//			eDriver.switchTo().alert().getText();
			System.out.println(eDriver.switchTo().alert().getText());
			System.out.println("inbetween");
			eDriver.switchTo().alert().accept();
			tmp1("3");
			//Return the 'Alert Display' Status
			return true;
		} catch (Exception e) {
			tmp1("failure");
			//Return the 'Alert Display' Status
			return false;
		}
	}
	
	public String getAlertText() {
		try {
			tmp1("getAlertText");
			
			new WebDriverWait(eDriver, maxWaitTime)
				.ignoring(NoAlertPresentException.class)
				.until(ExpectedConditions.alertIsPresent());
			
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
			tmp1("refreshPage");
			
//			new WebDriverWait(eDriver, maxWaitTime)
//				.ignoring(NoAlertPresentException.class)
//				.until(ExpectedConditions.alertIsPresent());
			
//			Alert al = eDriver.switchTo().alert();
//			al.accept();
			
			//Refresh the Current Page
			eDriver.navigate().refresh();
		} catch (Exception e) {
			//Output an error about Refreshing the Current Page
			reportFailedCheckpoint("Error: The Current Page did not refresh", "refreshPage");
			
			//Assert the status of Refreshing the Current Page
			Assert.assertEquals("The Current Page did not refresh", "The Current Page refreshed");
		}
	}
	
	public void clear(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Clear any value in the WebElement
			element.clear();
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
	
	public void sendKeys(final WebElement webElement, String webElementName, String sendText) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Enter a value into the WebElement
			element.sendKeys(sendText);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to receive input
			reportFailedCheckpoint("Error: Unable to send keys to the WebElement \"" + webElementName + "\"", "sendKeys");
			
			//Assert the status of the WebElement's ability to receive input
			Assert.assertEquals("The WebElement \"" + webElementName + "\" cannot receive any input", "The WebElement \"" + webElementName + "\" received input");
		}
	}
	
	public void selectDropDown(final WebElement webElement, String webElementName, String selectText) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Select Text from the DropDown
			Select selectPhoneNumberType = new Select(element);
			selectPhoneNumberType.selectByVisibleText(selectText);
		} catch (Exception e) {
			//Output an error about the specified WebElement being unable to receive input
			reportFailedCheckpoint("Error: Unable to select a drop-down option from the WebElement \"" + webElementName + "\"", "selectDropDown");
			
			//Assert the status of the WebElement's ability to receive input
			Assert.assertEquals("Unable to select a drop-down option from the WebElement \"" + webElementName + "\"", "Able to select a drop-down option from the WebElement \"" + webElementName + "\"");
		}
	}
	
	public String getAttribute(final WebElement webElement, String webElementName, String attribute) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Return the value of the specified attribute
			return element.getAttribute(attribute);
		} catch (Exception e) {
			//Output an error about the WebElement and/or attribute being located
			reportFailedCheckpoint("Error: Unable to get the attribute \"" + attribute + "\" from the WebElement \"" + webElementName + "\"", "getAttribute");
			
			//Assert the status of the WebElement's ability to return an attribute value
			Assert.assertEquals("Unable to get the attribute \"" + attribute + "\" from the WebElement \"" + webElementName + "\"", "Able to get the attribute \"" + attribute + "\" from the WebElement \"" + webElementName + "\"");
		}
		
		//Return nothing due to the WebElement and/or Attribute not being found
		return "";
	}
	
	public String getCssValue(final WebElement webElement, String webElementName, String cssValue) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Return the value of the specified CSS Value
			return element.getCssValue(cssValue);
		} catch (Exception e) {
			//Output an error about the WebElement and/or CSS Value being located
			reportFailedCheckpoint("Error: Unable to get the CSS Value \"" + cssValue + "\" from the WebElement \"" + webElementName + "\"", "getCssValue");
			
			//Assert the status of the WebElement's ability to return a CSS Value
			Assert.assertEquals("Unable to get the CSS Value \"" + cssValue + "\" from the WebElement \"" + webElementName + "\"", "Able to get the CSS Value \"" + cssValue + "\" from the WebElement \"" + webElementName + "\"");
		}
		
		//Return nothing due to the WebElement and/or Attribute not being found
		return "";
	}
	
	public String getText(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Retrieve the WebElement's text
			return element.getText();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the text of the specified WebElement
			reportFailedCheckpoint("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "getText");
			
			//Assert the status of the retrieving the WebElement's text
			Assert.assertEquals("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "Able to retrieve the text from the WebElement \"" + webElementName + "\"");
		}
		
		//Return no value, since the WebElement's text could not be returned properly
		return "";
	}
	
	public String getLowercaseText(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Retrieve the WebElement's text
			return element.getText().toLowerCase();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the text of the specified WebElement
			reportFailedCheckpoint("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "getLowercaseText");
			
			//Assert the status of the retrieving the WebElement's text
			Assert.assertEquals("Error: Unable to retrieve the text from the WebElement \"" + webElementName + "\"", "Able to retrieve the text from the WebElement \"" + webElementName + "\"");
		}
		
		//Return no value, since the WebElement's text could not be returned properly
		return "";
	}
	
	public boolean isDisplayed(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Output the boolean value regarding whether the WebElement is displayed or not
			return element.isDisplayed();
		} catch (Exception e) {
			//Return no value, since the WebElement's displayed status could not be returned properly
			return false;
		}
	}
	
	public boolean isSelected(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Output the boolean value regarding whether the WebElement is checked or not
			return element.isSelected();
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
			tmp1(webElementName);
			
			//Update the Extent Report
			reportInfo("Checking if the WebElement -> '" + webElementName + "' is Enabled");
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Output the boolean value regarding whether the WebElement is checked or not
			return element.isEnabled();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the status of the specified WebElement's checked status
			reportFailedCheckpoint("Error: Unable to verify if the WebElement \"" + webElementName + "\" is enabled or not", "isEnabled");
			
			//Assert the status of the WebElement's checked status
			Assert.assertEquals("Error: Unable to verify if the WebElement \"" + webElementName + "\" is enabled or not", "Able to verify if the WebElement \"" + webElementName + "\" is enabled or not");
			
			//Return no value, since the WebElement's checked status could not be returned properly
			return false;
		}
	}
	
	public int getSize(final List<WebElement> webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			List<WebElement> element = wait.until(new Function<EventFiringWebDriver, List<WebElement>>() {
				public List<WebElement> apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Retrieve the WebElement's size
			return element.size();
		} catch (Exception e) {
			//Output an error about being unable to retrieve the size of the specified WebElement
			reportFailedCheckpoint("Error: Unable to retrieve the size of the WebElement \"" + webElementName + "\"", "getSize");
			
			//Assert the status of the retrieving the WebElement's size
			Assert.assertEquals("Error: Unable to retrieve the size of the WebElement \"" + webElementName + "\"", "Able to retrieve the size of the WebElement \"" + webElementName + "\"");
		}
		
		//Return no value, since the WebElement's text could not be returned properly
		return 0;
	}
	
	public void highlightWebElement(final WebElement webElement, String webElementName) {
		try {
			tmp1(webElementName);
			
			//Set the timeout
			Wait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(eDriver)
					.withTimeout(Duration.ofSeconds(maxWaitTime))
					.pollingEvery(Duration.ofSeconds(pollingEveryTimePeriod))
					.ignoring(NoSuchElementException.class);
			
			//Locate the WebElement
			WebElement element = wait.until(new Function<EventFiringWebDriver, WebElement>() {
				public WebElement apply(EventFiringWebDriver eDriver) {
					return webElement;
				}
			});
			
			//Highlight the specified WebElement
			JavascriptExecutor js = (JavascriptExecutor) eDriver;
			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		} catch (Exception e) {
			//Output an error about being unable to highlight the specified WebElement
			reportFailedCheckpoint("Error: Unable to highlight the WebElement \"" + webElementName + "\"", "highlightWebElement");
			
			//Assert the status of the WebElement being able to be highlighted
			Assert.assertEquals("Error: Unable to highlight the WebElement \"" + webElementName + "\"", "Able to highlight the WebElement \"" + webElementName + "\"");
		}
	}
	
}