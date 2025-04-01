package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MiniCartPage;
import pageObjects.RegisterPage;

public class TC005_CouponCodeTest extends BaseClass{
	@Test(groups="Sanity")
	public void couponCodeValidation() throws InterruptedException
	{
		logger.info("*** staring TC005 Coupon Code testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
		mc.clickCartButton();
		mc.clickApplyDiscountButton();
		mc.enterPromoCode("TESTCOUPON");
		mc.clickApplyButton();
		String confirmmsg =mc.getCouponCodeSuccessMsg();	
		Assert.assertEquals(confirmmsg, "Discount code applied");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC005 Coupon Code testing ***");
		
		
	}

}
