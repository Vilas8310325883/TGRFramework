package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.MiniCartPage;

public class TC010_Checkoutpage extends BaseClass{
	@Test(groups="Sanity",priority=0)
	public void applyLoyaltyPointToCart () throws InterruptedException
	{
		logger.info("*** staring TC010 Apply Loyalty Points For Cart testing ***");
		try {
			String addToCart = addToCartFromPDP();
		   	MiniCartPage mc = new MiniCartPage(driver);
		   	CartPage cp = new CartPage(driver);
		   	CheckoutPage chp = new CheckoutPage(driver);
				mc.clickCartButton();
				waitTime("2000");
				mc.clickProceedToCartButton();
				waitTime("5000");		
				cp.setDeliveryInsurance("No Delivery Insurance (we will not resend your order) €0.00");
				String freebee = p.getProperty("freeBee");
				cp.addFreeBeeToCart(freebee);
				String freebeeaddmsg =cp.GetSuccessMsg();	
				cp.clickProceedToCheckoutButton();
				waitTime("3000");
				String points = "10";
				chp.addLoyalityPoints(points);
				waitTime("3000");
				chp.clickApplyButton();
				String confirmmsg = chp.getApplyLoyalitySuccessMsg();
				Assert.assertEquals(confirmmsg, ""+points+" loyalty points spent");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC010 Apply Loyalty Points For Cart testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}
	@Test(groups="Sanity",priority=1)
	public void removeLoyaltyPointToCart () throws InterruptedException
	{
		logger.info("*** staring TC010 Remove Loyalty Points For Cart testing ***");
		try {
			waitTime("5000");
		   	CheckoutPage chp = new CheckoutPage(driver);
		   	chp.waitToLoadRemoveButton();
		   	System.out.println("aa");
			double initialAmount = chp.getOrderTotal();
			System.out.println(initialAmount);
			chp.clickLoyaltyPointRemoveButton();
			waitTime("5000");
			double finalAmount = chp.getOrderTotal();
				assert(finalAmount>initialAmount);
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC010 Remove Loyalty Points For Cart testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}
	@Test(groups="Sanity",priority=2)
	public void addNewAddress () throws InterruptedException
	{
		logger.info("*** staring TC010 Add New Address testing ***");
		try {
		   	CheckoutPage chp = new CheckoutPage(driver);
		//   	chp.clickAddNewAddressButton();
		   	waitTime("3000");
		   	chp.setFirstName("aa");
		   	chp.setLastName("bb");
		   	chp.setTelephone("1234567890");
		   	chp.setPostcode("123456");
		   	chp.clickSelectCountryButton();
		   	chp.selectRequiredCountry("Albania");
		   	chp.setStreet("vvv");
		   	chp.setCity("ttt");
		   	chp.setAlternateNumber("2345678901");
		 //  	chp.setAddressType("Home");
		   	chp.setDefaultBillingAddress();
		   	chp.setDefaultShippingAddress();
		  // 	chp.confirmAddress();
		   	chp.clickSaveButton();
		   	chp.waitToLoadNewAddressSuccessMsg();
		   	String confirmmsg  = chp.captureAddNewAddressSuccessMsg();
				Assert.assertEquals(confirmmsg, "You saved the address");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC010 Add New Address testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
	}
	@Test(groups="Sanity",priority=3)
	public void editNewAddress () throws InterruptedException
	{
		logger.info("*** staring TC010 Edit New Address testing ***");
		try {
		   	CheckoutPage chp = new CheckoutPage(driver);
		   	chp.waitToLoadEditAddressButton();
		    chp.clickEditAddressButton();
		   	waitTime("3000");
		   	chp.removeFirstName();
		   	chp.setFirstName("aabb");
		   	chp.removeLastName();
		   	chp.setLastName("bbcc");
		   	chp.removeTelephone();
		   	chp.setTelephone("2222222222");
		   	chp.removePostcode();
		   	chp.setPostcode("333333");
		   	chp.clickSelectCountryButton();
		   	chp.selectRequiredCountry("Algeria");
		   	chp.removeStreet();
		   	chp.setStreet("vt");
		   	chp.removeCity();
		   	chp.setCity("tv");
		   	chp.removeAlternateNumber();
		   	chp.setAlternateNumber("3333333333");
		    chp.setAddressType("Home");
		   	chp.setDefaultBillingAddress();
		   	chp.setDefaultShippingAddress();
		    chp.confirmAddress();
		   	chp.clickSaveEditAddressButton();
		   	chp.waitToLoadEditNewAddressSuccessMsg();
		   	String confirmmsg  = chp.captureEditNewAddressSuccessMsg();
				Assert.assertEquals(confirmmsg, "You edited the address");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC010 Edit New Address testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
	}
	@Test(groups="Sanity",priority=4)
	public void addAlternateNewAddress () throws InterruptedException
	{
		logger.info("*** staring TC010 Add Alternative New Address testing ***");
		try {
		   	CheckoutPage chp = new CheckoutPage(driver);
		   	chp.waitToLoadAddNewAddressButton();
		    chp.clickAddNewAddressButton();
		   	waitTime("3000");
		   	chp.setFirstName("test");
		   	chp.setLastName("test");
		   	chp.setTelephone("9999999999");
		   	chp.setPostcode("555555");
		   	chp.clickSelectCountryButton();
		   	chp.selectRequiredCountry("Angola");
		   	chp.setStreet("test");
		   	chp.setCity("test");
		   	chp.setAlternateNumber("7777777777");
		    chp.setAddressType("Home");
		   	chp.setDefaultBillingAddress();
		   	chp.setDefaultShippingAddress();
		    chp.confirmAddress();
		   	chp.clickSaveEditAddressButton();
		 	chp.waitToLoadNewAddressSuccessMsg();
		   	String confirmmsg  = chp.captureAddNewAddressSuccessMsg();
				Assert.assertEquals(confirmmsg, "You saved the address");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		finally
		{
			waitTime("3000");
			CheckoutPage chp = new CheckoutPage(driver);
			chp.clickCheckoutLogo();
			waitTime("3000");
			MiniCartPage mc = new MiniCartPage(driver);
			mc.clickCartButton();
			mc.clickRemoveButton();
			mc.waitRemoveProductSuccessMsgLoad();
			mc.clickCartCloseButton();		
		}
		logger.info("*** finished TC010 Add Alternative New Address testing***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
	}
/*	@Test(groups="Sanity",priority=3)
	public void setPaymentMethodAndPlaceOrder() throws InterruptedException
	{
		logger.info("*** staring TC010 Set Payment Method testing ***");
		try {
		   	CheckoutPage chp = new CheckoutPage(driver);
		   	waitTime("3000");
		   	chp.scrollAndClickContinueButton();
		   	waitTime("3000");
		   	chp.selectDeliveryMethod("International Tracked");
		  	chp.scrollAndClickContinueShippingButton();
		   	waitTime("10000");
		   	String paymentmethod = p.getProperty("paymentmethod");
		   	String confirmmsg = chp.scrollAndSetThePaymentMethod(paymentmethod);
		   	Assert.assertEquals(confirmmsg, "Thanks For Your Order");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC010 Set Payment Method testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
	}	*/
}
