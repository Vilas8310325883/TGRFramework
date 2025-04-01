package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
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
import pageObjects.PDPPage;
import pageObjects.PLPPage;
public class BaseClass {
	public static Logger logger;
	public static WebDriver driver;
	public static Properties p = new Properties();
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger1;
	@BeforeSuite(groups= {"Sanity","Regression","Sanity1"})
	@Parameters({"os","browser"})
public void setup(String os, String br) throws InterruptedException, IOException
	//public void setup() throws InterruptedException, IOException
	{
		FileReader file = new FileReader("./src//test//resources//config.properties");
	//	p = new Properties();
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
			             
		case "edge":    System.setProperty("webdriver.edge.driver", "C:\\Webdriver\\msedgedriver.exe");
			            driver = new EdgeDriver(); break;
		case "firefox": System.setProperty("webdriver.gecko.driver", "C:\\Webdriver\\geckodriver.exe");
			            driver = new FirefoxDriver(); break;
		default: System.out.println("Invalid browser name.."); return;
		}
		}
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("URL"));
		System.out.println(p.getProperty("URL"));
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
  @AfterSuite(groups= {"Sanity","Regression","Sanity1"})
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
	public static int totalProductInfo(String TotalProduct) throws InterruptedException
	{
			String a1[] = TotalProduct.split(";");
			int numberofproducts =a1.length;
			return numberofproducts;
	}
	public String getCategoryInfo(String productinfo)
	{
		String productinformation[] = productinfo.split("#");
		String category = productinformation[0];
		String product = productinformation[1];
		return category;	
	}
	public String getProductInfo(String productinfo)
	{
		String productinformation[] = productinfo.split("#");
		String category = productinformation[0];
		String product = productinformation[1];
		System.out.println(category);
		return product;	
	}
	public String getProductName(String productinfo)
	{
		String products[] = productinfo.split("!") ;
	   String productname = products[0];
	   System.out.println(productname);
		return productname;	
	}
	public String getPLPProductType(String productinfo)
	{
		String products[] = productinfo.split("!") ;
		try {
		    String configtype =products[1]; 
		    System.out.println(configtype);
			return configtype;	
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				String New = "No Config";
				return New;
			}
	}
	public int getProductQuality(String productinfo)
	{
		String products[] = productinfo.split("!") ;
		String quantity = products[1];
		int Quantity1=Integer.parseInt(quantity); 
		return Quantity1;	
	}
	public String getConfigDetails(String productinfo)
	{
		String products[] = productinfo.split("!") ;
		try {
	    String configtype =products[2]; 
	    System.out.println(configtype);
		return configtype;	
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			String New = "No Config";
			return New;
		}
	}
	public void waitTime(String time) throws InterruptedException
	{
		int waitTime=Integer.parseInt(time); 
		Thread.sleep(waitTime);
	}
	public void loadPropertyFile()
	{
		try {
        	FileInputStream inputStream = new FileInputStream("C:\\Users\\codilar\\eclipse-workspace\\TGRFramework\\src\\test\\resources\\config.properties");
        	p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	public String addToCartFromPDP() throws InterruptedException
	{
		String productInfo = p.getProperty("productinfo");
		System.out.println(productInfo);
	//	int numberofproducts = totalProductInfo(productInfo);
	/*	for(int i=0;i<numberofproducts;i++)
		{
			String ai = a1[i];
		ProductAddToCart(ai,numberofproducts,sheet2,m,row);
		}*/
	HomePage hp = new HomePage(driver);
	PDPPage pdp = new PDPPage(driver);
	PLPPage plp = new PLPPage(driver);
	 String successmsg = null;
	hp.clickMegaMenu();
	System.out.println("vvv");
	hp.waitTime();
	String categoryDetails = getCategoryInfo(productInfo);
	hp.categorySelection(categoryDetails);
	String productDetails = getProductInfo(productInfo);
	String productname = getProductName(productDetails);
	int productquantity = getProductQuality(productDetails);
	String configtypevalue = getConfigDetails(productDetails);
	if(configtypevalue.equalsIgnoreCase("No Config"))
	{
	
	}
	System.out.println(configtypevalue);
	
	plp.waitTime("3000");
	plp.ScrollDown();
	plp.waitElementload();
	String next_attribute = plp.GetNextAttribute();
	while(!next_attribute.equalsIgnoreCase("PaginationLink PaginationLink_isArrow PaginationLink_isArrowInActive"))
	{
		String cartquantity1 = hp.GetCartQuantity();
		String requiredproduct = productname;
List<WebElement> AvailableProducts = plp.GetProducts();
for(int i=0;i<AvailableProducts.size();i++)
{		
	Thread.sleep(5000);
	String productname1 = plp.GetProductName(i);
	System.out.println(productname1);
	if(productname1.equalsIgnoreCase(requiredproduct))
	{
		System.out.println(productname1);
		Thread.sleep(2000);
		WebElement description=	AvailableProducts.get(i);
		Iterator<String> ab = plp.OpenProductInNewTab(description);
		String ParentId=ab.next();
		System.out.println(ab);
		while(ab.hasNext())
		{
			plp.SwitchToPDP(ab);
			try {
				pdp.ClickConfigOptionSelection();
				pdp.waitTime("2000");
				pdp.SelectConfigValue(configtypevalue);
				pdp.waitTime("2000");
				pdp.ClickCloseButton();
				int requiredquantity = productquantity;
				int PresentNumberQuatity = pdp.GetProductQuantity();
				if(requiredquantity==PresentNumberQuatity)
				{
					pdp.ClickAddBasketButton();
					pdp.waitTime("3000");
				}
				else
				{
					for(int l=1;l<requiredquantity;l++)
					{
						try {
							pdp.ClickPlusButton();;
						}
						catch(Exception e7)
						{
							pdp.ClickAddBasketButton();
							break;
						}
					}
				}
				pdp.ClickAddBasketButton();
				 successmsg =   pdp.GetSuccessMsg();
				  System.out.println(successmsg);
			    pdp.waitTime("3000");
			}
			catch(Exception e1)
			{
				int requiredquantity = productquantity;
				int PresentNumberQuatity = pdp.GetProductQuantity();
				if(requiredquantity==PresentNumberQuatity)
				{
					pdp.ClickAddBasketButton();
					pdp.waitTime("3000");
				}
				else
				{
					for(int l=1;l<requiredquantity;l++)
					{
						try {
							pdp.ClickPlusButton();;
						}
						catch(Exception e7)
						{
							pdp.ClickAddBasketButton();
							break;
						}
					}
				}
				pdp.ClickAddBasketButton();
			   successmsg =   pdp.GetSuccessMsg();
			  System.out.println(successmsg);
		//	  Assert.assertEquals(successmsg, "Product was added to cart!");
			}		
			driver.close();
			driver.switchTo().window(ParentId);
			driver.navigate().refresh();
			
		}
		break;
	}
}
plp.waitTime("5000");
String cartquantity2 = hp.GetCartQuantity();
System.out.println(cartquantity2);
if(!cartquantity1.equalsIgnoreCase(cartquantity2))
{
	break;
}
else
{
	driver.navigate().refresh();
	Thread.sleep(5000);	
	plp.ClickNextButton();
	Thread.sleep(10000);
}	
}
	return successmsg;	
	}
	
	public String addToCartPLP() throws InterruptedException
	{
		String productInfo = p.getProperty("PLPproductinformation");
		System.out.println(productInfo);
//		int numberofproducts = totalProductInfo(productInfo);
	/*	for(int i=0;i<numberofproducts;i++)
		{
			String ai = a1[i];
		ProductAddToCart(ai,numberofproducts,sheet2,m,row);
		}*/
	    HomePage hp = new HomePage(driver);
	    PDPPage pdp = new PDPPage(driver);
	    PLPPage plp = new PLPPage(driver);
	    String successmsg = null;
	    hp.waitTime();
	    hp.clickMegaMenu();
	    hp.waitTime();
	    String categoryDetails = getCategoryInfo(productInfo);
	    hp.categorySelection(categoryDetails);
	    String productDetails = getProductInfo(productInfo);
	    String productname = getProductName(productDetails);
	    String configtypevalue = getPLPProductType(productDetails);
	    if(configtypevalue.equalsIgnoreCase("No Config"))
		{
		
		}
		plp.waitTime("3000");
		plp.ScrollDown();
		plp.waitElementload();
		String next_attribute = plp.GetNextAttribute();
		while(!next_attribute.equalsIgnoreCase("PaginationLink PaginationLink_isArrow PaginationLink_isArrowInActive"))
		{
			String cartquantity1 = hp.GetCartQuantity();
			String requiredproduct = productname;
			List<WebElement> AvailableProducts = plp.GetProducts();
			for(int i=0;i<AvailableProducts.size();i++)
			{		
				plp.waitTime("2000");
				plp.ScrollUp();
				plp.waitTime("2000");
				String productname1 = plp.GetProductName(i);
				if(productname1.equalsIgnoreCase(requiredproduct))
				{
					plp.ScrollToElement(productname1);
					plp.waitTime("2000");
					plp.addToBasket(productname1);
			          try {
			        	    plp.selectConfigOption(configtypevalue);
			        		plp.ClickAddBasketButton();
			          }
			          catch(Exception e)
			          {
			          }  
							  successmsg =   pdp.GetSuccessMsg();
							//  Assert.assertEquals(successmsg, "Product was added to cart!");
							  break;
			          }
				}
			
			plp.waitTime("5000");
			String cartquantity2 = hp.GetCartQuantity();
			if(!cartquantity1.equalsIgnoreCase(cartquantity2))
			{
				break;
			}
			else
			{
				driver.navigate().refresh();
				plp.waitTime("5000");
				plp.ClickNextButton();
				plp.waitTime("10000");
		}	
			
			}
		return successmsg;
	}
	

}
//Stand alone server command
//java -jar selenium-server-4.28.1.jar standalone