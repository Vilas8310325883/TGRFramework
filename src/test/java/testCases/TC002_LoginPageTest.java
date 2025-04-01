package testCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.RegisterPage;

public class TC002_LoginPageTest extends BaseClass{
	@Test(groups="Sanity")
	public void verifyLogin() throws InterruptedException
	{
	logger.info("*** staring TC002 login testing ***");
		try {
		HomePage hp = new HomePage(driver);
		RegisterPage rp = new RegisterPage(driver);
		Thread.sleep(3000);
		rp.clickmyAccountbutton();
		LoginPage lp = new LoginPage(driver);
		logger.info("Setting Login Details");
	//	loadPropertyFile();
	        Thread.sleep(3000);
		lp.setEmail(p.getProperty("Email"));
		System.out.println(p.getProperty("Email"));
		lp.setPassword(p.getProperty("Password"));
		lp.clickLogin();
	//	lp.getConfirmationMsg("aa");
		String confirmmsg = lp.getConfirmationMsg1();
		Assert.assertEquals(confirmmsg, "You are successfully logged in!");
	//	hp.clickSignout();
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC002 login testing ***");
		
		
	}

}
