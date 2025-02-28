package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageObjects.HomePage;
public class BaseClass {
	public Logger logger;
	public static WebDriver driver;
	public Properties p;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger1;
	@BeforeClass(groups= {"Sanity","Regression","Sanity1"})
	@Parameters({"os","browser"})
public void setup(String os, String br) throws InterruptedException, IOException
	//public void setup() throws InterruptedException, IOException
	{
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		//	String huburl = "http://192.168.1.180:4444/wd/hub";
			DesiredCapabilities cap = new DesiredCapabilities();
			if(os.equalsIgnoreCase("Windows"))
			{
				cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.IOS);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			
			else
			{
				System.out.println("No matching OS");
				return;
			}

				switch(br.toLowerCase())
				{
				case  "chrome" : cap.setBrowserName("chrome"); break;
				case  "edge" : cap.setBrowserName("MicrosoftEdge"); break;
				case "firefox" : cap.setBrowserName("firefox"); break;
				default: System.out.println("No Matching browser");return;
				}
			//	driver = new RemoteWebDriver(new URL("http://192.168.29.115:4444/wd/hub"),cap);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{	
		switch(br.toLowerCase())
		{
		case  "chrome" : System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
			             driver = new ChromeDriver(); break;
			             
		case "edge":    System.setProperty("webdriver.edge.drive", "C:\\Webdriver\\msedgedriver.exe");
			            driver = new EdgeDriver(); break;
		case "firefox": System.setProperty("webdriver.gecko.driver", "C:\\Webdriver\\geckodriver.exe");
			            driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name.."); return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("URL"));
		driver.manage().window().maximize();	
		Thread.sleep(10000);
	//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage hp = new HomePage(driver);
		hp.clickDropdown();
		logger.info("Clicked Country Dropdown");
		hp.selectCountry();
		logger.info("Selected the Country");
		hp.selectCheckbox();
		logger.info("Clicked on the Checkbox");
		hp.clickAgreeEnter();
		hp.waitTime();
	}
/*	@BeforeTest
	public void beforeTestMethod()
	{
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ File.separator+"reports"+File.separator+"SDETADDA");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("HostName", "Vilas");
		extent.setSystemInfo("UserName", "root");
		sparkReporter.config().setDocumentTitle("Automation Reports");
		sparkReporter.config().setReportName("Automation Reports Login Reports");
				
	}*/
   @AfterClass(groups= {"Sanity","Regression","Sanity1"})
	public void teardown()
	{
	  driver.quit();
	}
   public String randomeString()
	{
		String GeneratedString = RandomStringUtils.randomAlphabetic(5);
		return GeneratedString;	
	}
	public String randomAlphanumeric()
	{
		String GeneratedString = RandomStringUtils.randomAlphabetic(7);
		String GenerateNumber = RandomStringUtils.randomNumeric(7);
		return GeneratedString+GenerateNumber;		
	}
	public static String getCellVal(XSSFSheet SheetName,int i,int j) {
		XSSFCell row = SheetName.getRow(i).getCell(j);
        return row.toString();
	}
	public String captureScreen(String tname) throws IOException {
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot  takesScreenshot =  (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+ tname + "_" + timestamp + ".png";
		File targetFile= new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
				
	}

}
//Stand alone server command
//java -jar selenium-server-4.28.1.jar standalone