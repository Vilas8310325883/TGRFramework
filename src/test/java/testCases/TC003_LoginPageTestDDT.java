package testCases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;
import utilities.DataProviders;
public class TC003_LoginPageTestDDT extends BaseClass {
  @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups="Sanity1")
	//@Test
	public void LoginTestDDT(String no, String description, String email, String pwd, String exp) throws InterruptedException
	{
		logger.info("*** staring TC003 login testing DDT***");
try {
			HomePage hp = new HomePage(driver);
		RegisterPage rp = new RegisterPage(driver);
		BaseClass bc = new BaseClass();
		rp.clickmyAccountbutton();
		LoginPage lp = new LoginPage(driver);
		logger.info("Setting Login Details");
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
	//	lp.getConfirmationMsg();
		String confirmmsg = lp.getConfirmationMsg(exp);
		if(confirmmsg.equals(exp))
		{
			Assert.assertTrue(true);
		}
			else
			{
				Assert.assertTrue(false);
			}
		Thread.sleep(5000);
		logger.info("*** finished TC003 login testing DDT ***");
		}
		catch(ElementClickInterceptedException e1)
		{
     	driver.findElement(By.xpath("//div[@class='ins-element-content ins-selectable-element']")).click();
		}
	/*	catch(Exception e)
		{
			System.out.println("vv7");
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}*/
		
	}	
	//}
}
