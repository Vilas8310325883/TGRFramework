package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.MiniCartPage;
import utilities.DataProviders;

public class TC011_OrderPlace extends BaseClass {
	@Test(dataProvider = "OrderPlace", dataProviderClass = DataProviders.class, groups="Sanity")
	public void orderPlacing (String no, String description, String payment) throws InterruptedException
	{
		logger.info("*** staring TC011 orderPlacing testing ***");
		try {
			String addToCart = addToCartFromPDP();
		   	MiniCartPage mc = new MiniCartPage(driver);
		   	CartPage cp = new CartPage(driver);
		   	CheckoutPage chp = new CheckoutPage(driver);
				mc.clickCartButton();
				waitTime("2000");
				mc.clickApplyDiscountButton();
				mc.enterPromoCode("TESTCOUPON");
				mc.clickApplyButton();
				mc.getCouponCodeSuccessMsg();
				mc.clickProceedToCartButton();
				waitTime("5000");		
				cp.setDeliveryInsurance("No Delivery Insurance (we will not resend your order) €0.00");
				String freebee = p.getProperty("freeBee");
				cp.addFreeBeeToCart(freebee);
				String freebeeaddmsg =cp.GetSuccessMsg();	
				cp.clickProceedToCheckoutButton();
				waitTime("5000");
				chp.clickContinueDeliveryButton();
			 	waitTime("5000");
			   	chp.selectDeliveryMethod("International Tracked");
			  	chp.scrollAndClickContinueShippingButton();
			  	waitTime("10000");
			 //  	String paymentmethod = p.getProperty("paymentmethod");
				String paymentmethod = payment;
			   	String confirmmsg = chp.scrollAndSetThePaymentMethod(paymentmethod);
			   	Assert.assertEquals(confirmmsg, "Thanks For Your Order");
			   	waitTime("3000");
				chp.clickCheckoutLogo();
				waitTime("3000");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished  TC011 orderPlacing testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}

}
