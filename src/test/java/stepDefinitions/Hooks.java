package stepDefinitions;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Base.BaseUtils;
import Base.ExtentReportUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseUtils{
	
	ExtentReportUtils extentReportUtils = new ExtentReportUtils();
	
//	private BaseUtils base;
//	
//	public Hooks(BaseUtils base)
//	{
//		this.base = base;
//	}
	
	@Before
	public void InitializeTest(Scenario scenario)
	{
		scenarioDef = features.createNode(scenario.getName());
		File chromedriver = new File(System.getProperty("user.dir") + "//WebDrivers//chromedriver.exe");
		System.setProperty("webdriver.chrome.driver",chromedriver.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized","--disable-infobars");
		driver = new ChromeDriver(options);  
		System.out.println("Hooks-------------->Initializing the Browser");
	}
	
	@After
	public void TeradownTest(Scenario scenario) throws IOException
	{
		System.out.println("Scenario name :"+scenario.getName());
		if(scenario.isFailed())
		{
			System.out.println("Scenario failed, taking screenshot");
			extentReportUtils.ExtentReportScreenshot();
		}
		System.out.println("Hooks-------------->Test teardown completed");
		driver.close();
	}
}
