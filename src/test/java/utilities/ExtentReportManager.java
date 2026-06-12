package utilities;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentReportManager {
	public static ExtentReports extent;
	public static ExtentReports getReport() {
		if(extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
			spark.config().setReportName("SauceDemoAutomation Report");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Tester", "Ashish");
			extent.setSystemInfo("Environment", "QA");
		}
		return extent;
	}

}
