package utilityClasses;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	private static ConstantVariables constantVariables = new ConstantVariables();
	private static String path = constantVariables.extentFactoryReportLocation;
	
	public static ExtentReports getInstance(String environment, String browser, String browserVersion) {
		ExtentReports extent;
		extent = new ExtentReports(path, false);
		extent.addSystemInfo("User Name", "Jenkins - CCS Automation Team");
		extent.addSystemInfo("Host Name", "Jenkins Remote Server (10.50.130.138)");
		
		extent.addSystemInfo("Product Environment", environment);
		extent.addSystemInfo("Product Version", "amcom_smartweb-8.0-0.40.x86_64");
//		extent.addSystemInfo("Product Version", "amcom_eznotify-8.0-1.3.x86_64");
//		extent.addSystemInfo("View", "Desktop");
		extent.addSystemInfo("Browser", browser);
		extent.addSystemInfo("Browser Version", browserVersion);
		
		return extent;
	}
	
	public static void deleteExtentReport() {
		File file = new File(path);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(path);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete file, located at: " + path);
		}
	}
}