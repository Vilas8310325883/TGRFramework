package testCases;

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
		rp.setemail(randomeString()+"@gmail.com");
		rp.setDOB("03-11-1997");
		String password = randomAlphanumeric();
		rp.setpassword(password);
		rp.setconfirmpassword(password);
		rp.clicksubscription();
		rp.clickremote_shopping_assistance();
		rp.clicksubmit();	
		logger.info("Validating expected message");
		String confirmmsg = rp.getConfirmationMsg();
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
