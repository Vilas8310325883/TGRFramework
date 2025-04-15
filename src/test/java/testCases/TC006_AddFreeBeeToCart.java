package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.MiniCartPage;

public class TC006_AddFreeBeeToCart extends BaseClass {
	
	@Test(groups="Sanity",priority=0)
	public void addFreeBeeValidation() throws InterruptedException
	{
		logger.info("*** staring TC006 Add Free Bee testing ***");
		try {
			String addToCart = addToCartFromPDP();
	   	MiniCartPage mc = new MiniCartPage(driver);
			mc.clickCartButton();
			waitTime("2000");
			mc.clickProceedToCartButton();
			waitTime("5000");
			CartPage cp = new CartPage(driver);
		//	cp.clickSelectButton();
		//	cp.selectOption("No Delivery Insurance (we will not resend your order) £0.00");
			String freebee = p.getProperty("freeBee");
			cp.addFreeBeeToCart(freebee);
			String confirmmsg =cp.GetSuccessMsg();	
			Assert.assertEquals(confirmmsg, "Product was added to cart!");
			}
			catch(Exception e)
			{
				logger.error("Test Failed");
				logger.debug("Debug Logs");
				Assert.fail();
			}
		logger.info("*** finished TC006 Add Free Bee testing ***");
	}
	@Test(groups="Sanity",priority=1)
	public void removeFreeBeeValidation() throws InterruptedException
	{
		logger.info("*** staring TC006 Remove Free Bee testing ***");
		try {
			CartPage cp = new CartPage(driver);
			int cartQuantity = cp.GetCartQuantity();
			waitTime("2000");
			String freebee = p.getProperty("freeBee");
			cp.removeFreeBeeFromCart(freebee);
			waitTime("5000");
			cp.clickRemoveProductButton();
			waitTime("5000");
			int presentCartQuantity = cp.GetCartQuantity();		
			if(cartQuantity!=presentCartQuantity)
			{
				Assert.assertNotEquals(cartQuantity,presentCartQuantity);
			}
			}
			catch(Exception e)
			{
				logger.error("Test Failed");
				logger.debug("Debug Logs");
				Assert.fail();
			}
		logger.info("*** finished TC006 Remove Free Bee testing ***");
	}
	@Test(groups="Sanity",priority=6)
	public void addCouponCodeInCartPageValidation() throws InterruptedException
	{
		logger.info("*** staring TC006 Apply Coupon Code In Cart Page testing ***");
		try {
			CartPage cp = new CartPage(driver);
	/*		MiniCartPage mc = new MiniCartPage(driver);
			mc.clickCartButton();
			waitTime("2000");
			mc.clickProceedToCartButton();*/
			cp.moveToPromoCodeField();
			waitTime("2000");
			cp.clickPromoCodeButton();
			cp.enterPromoCode("TESTCOUPON");	
			cp.clickApplyButton();
			String confirmmsg =cp.getCouponCodeSuccessMsg();	
			System.out.println(confirmmsg);
			Assert.assertEquals(confirmmsg, "Discount code applied");
			}
			catch(Exception e)
			{
				logger.error("Test Failed");
		     	logger.debug("Debug Logs");
				Assert.fail();
			}
		logger.info("*** finished TC006 Apply Coupon Code In Cart Page testing ***");
	}
	@Test(groups="Sanity", priority=7)
	public void removeCouponCodeInCartPageValidation() throws InterruptedException
	{
		logger.info("*** staring TC006 Remove Coupon Code In Cart Page testing ***");
		try {
			CartPage cp = new CartPage(driver);
	        cp.clickRemoveCouponCodeButton();
	        cp.waitRemoveCouponCodeSuccessMsgLoad();
	        String confirmmsg =cp.getRemoveCouponCodeSuccessMsg();		
		    Assert.assertEquals(confirmmsg, "Coupon was removed!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC006 Remove Coupon Code In Cart Page Carttesting ***");
		
		
	}
	@Test(groups="Sanity",priority=2)
	public void addToWishListInCartPage () throws InterruptedException
	{
		logger.info("*** staring TC006 Add To Wishlist From Cart Page testing ***");
		try {
			MiniCartPage mc = new MiniCartPage(driver);
		CartPage cp = new CartPage(driver);
	//	mc.clickCartButton();
	//	waitTime("2000");
	//	mc.clickProceedToCartButton();
		waitTime("5000");
		cp.clickAddToWishListButton();
		System.out.println("Vilas");
		cp.waitAddtoWishlistSuccessMsgLoad();
		String successmsg = cp.getAddToWishListSuccessMsg();
		System.out.println(successmsg);
		Assert.assertEquals(successmsg, "Product added to wish-list!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished Add To Wishlist From Cart Page testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}

////p[contains(text(),'Papaya Feminised Seeds - 5')]/parent::div/parent::a/parent::div/parent::div/parent::div/child::div/div[@class='wishlistButton']
	@Test(groups="Sanity",priority=3)
	public void removeFromWishListInCartPage () throws InterruptedException
	{
		logger.info("*** staring TC006 Remove From The Wislist From Cart Page testing ***");
		try {
		CartPage cp = new CartPage(driver);
		cp.waitRemoveFromWishListButtonLoad();
		cp.clickRemoveFromWishListButton();
	    cp.waitRemoveFromWishlistSuccessMsgLoad();
	    String successmsg = cp.getRemoveFromWishListSuccessMsg();
		  System.out.println(successmsg);
		  Assert.assertEquals(successmsg, "Product has been removed from your Wish List!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC006 Remove From The Wislist From Cart Page testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}
	@Test(groups="Sanity",priority=4)
	public void increaseProductQuanityInCartPage () throws InterruptedException
	{
		logger.info("*** staring TC006 Increase Product Quantity In Cart Page testing ***");
		try {
		CartPage cp = new CartPage(driver);
		waitTime("3000");
		int presentquantity = cp.getPresentProductQuantity();
		int requiredquantity = presentquantity+2;
		for(int i=presentquantity;i<requiredquantity;i++)
		{
			cp.clickPlusButton();
			Thread.sleep(5000);
		}
		Thread.sleep(2000);
		int quantity = cp.getPresentProductQuantity();
		Thread.sleep(2000);
		 Assert.assertEquals(quantity,requiredquantity);	
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC006 Increase Product Quantity In Cart Page testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']		
	}
	@Test(groups="Sanity", priority=5)
	public void decreaseProductQuanityInCartPage () throws InterruptedException
	{
		logger.info("*** staring TC006 Decrease Product Quantity In Cart Page testing ***");
		try {
	    CartPage cp = new CartPage(driver);
		waitTime("3000");
		int presentquantity = cp.getPresentProductQuantity();
		int requiredquantity = presentquantity-2;
		for(int i=presentquantity;i>requiredquantity;i--)
		{
			cp.clickMinusButton();
			Thread.sleep(5000);
		}
		Thread.sleep(2000);
		int quantity = cp.getPresentProductQuantity();
		 Assert.assertEquals(quantity,requiredquantity);	
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Decrease Product Quantity In Cart Page testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']		
	}
	@Test(groups="Sanity", priority=8)
	public void removeProductFromCart() throws InterruptedException
	{
		logger.info("*** staring TC006 Remove Product From Cart testing ***");
		try {
		CartPage cp = new CartPage(driver);
		String productInfo = p.getProperty("productinfo");
		String productDetails = getProductInfo(productInfo);
		String productname = getProductName(productDetails);
		System.out.println(productname);
		Thread.sleep(1000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-100)");
        Thread.sleep(1000);
		cp.clickRemoveButton(productname);
		waitTime("2000");
		cp.clickRemoveProductButton();
		cp.waitRemoveProductSuccessMsgLoad();
		String confirmmsg =  cp.getRemoveProductSuccessMsg();
		System.out.println(confirmmsg);
		Assert.assertEquals(confirmmsg, "Your Cart Is Empty");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC006 Remove Product From Cart testing ***");	
		
	}
}
