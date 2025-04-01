package testCases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class TC001_AccountRegistrationTest extends BaseClass {
	@Test(groups="Regression")
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("***** Starting TC001_AccountRegistrationTest ******");
		try {
		HomePage hp = new HomePage(driver);
		RegisterPage rp = new RegisterPage(driver);
		rp.clickmyAccountbutton();
		logger.info("Clicked on My account button");
		rp.clickcreate_account_button();
		logger.info("Providing customer details");
		rp.setfirstname(randomeString().toUpperCase());
		rp.setlastname(randomeString().toUpperCase());
		String email = randomeString()+"@gmail.com";
		System.out.println(email);
		rp.setemail(email);
		rp.setDOB("03-11-1997");
		String password = randomAlphanumeric();
		rp.setpassword(password);
		rp.setconfirmpassword(password);
		rp.clicksubscription();
		rp.clickremote_shopping_assistance();
		rp.clicksubmit();	
		try
		{  
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\codilar\\eclipse-workspace\\TGRFramework\\src\\test\\resources\\config.properties");
		    p.setProperty("Email", email);
		    p.setProperty("Password", password);
		    p.store(outputStream, null);
		} catch (IOException e) {
		    e.printStackTrace();
		} 
		logger.info("Validating expected message");
		String confirmmsg = rp.getConfirmationMsg();
		hp.clickSignout();
		Assert.assertEquals(confirmmsg, "You are successfully logged in!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("***** Finished TC001_AccountRegistrationTest ******");
	}
		

}
