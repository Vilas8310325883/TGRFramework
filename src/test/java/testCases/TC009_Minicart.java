package testCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MiniCartPage;
import pageObjects.PDPPage;

public class TC009_Minicart extends BaseClass {
	@Test(groups="Sanity",priority=0)
	public void addToWishListInMiniCartPage () throws InterruptedException
	{
		logger.info("*** staring TC009 Add To Wishlist From Minicart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
		mc.clickCartButton();
		mc.clickAddToWishListButton();
		System.out.println("Vilas");
		mc.waitAddtoWishlistSuccessMsgLoad();
		String successmsg = mc.getAddToWishListSuccessMsg();
		System.out.println(successmsg);
		Assert.assertEquals(successmsg, "Product added to wish-list!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Add To Wishlist From Minicart testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}

////p[contains(text(),'Papaya Feminised Seeds - 5')]/parent::div/parent::a/parent::div/parent::div/parent::div/child::div/div[@class='wishlistButton']
	@Test(groups="Sanity",priority=1)
	public void removeFromWishListInCartPage () throws InterruptedException
	{
		logger.info("*** staring TC009 Remove From The Wislist From Minicart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
		mc.waitRemoveFromWishListButtonLoad();
		mc.clickRemoveFromWishListButton();
	    mc.waitRemoveFromWishlistSuccessMsgLoad();
	    String successmsg = mc.getRemoveFromWishListSuccessMsg();
		  System.out.println(successmsg);
		  Assert.assertEquals(successmsg, "Product has been removed from your Wish List!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Remove From The Wislist From Minicart testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']	
		
		
	}
	@Test(groups="Sanity",priority=2)
	public void increaseProductQuanityInMiniCart () throws InterruptedException
	{
		logger.info("*** staring TC009 Increase Product Quantity In Minicart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
		waitTime("3000");
		int presentquantity = mc.getPresentProductQuantity();
		int requiredquantity = presentquantity+2;
		for(int i=presentquantity;i<requiredquantity;i++)
		{
			mc.clickPlusButton();
			Thread.sleep(5000);
			int currentquantity = mc.getPresentProductQuantity();
		}
		Thread.sleep(2000);
		int quantity = mc.getPresentProductQuantity();
		Thread.sleep(2000);
		 Assert.assertEquals(quantity,requiredquantity);	
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Increase Product Quantity In Minicart testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']		
	}
	@Test(groups="Sanity", priority=3)
	public void decreaseProductQuanityInMiniCart () throws InterruptedException
	{
		logger.info("*** staring TC009 Decrease Product Quantity In Minicart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
		waitTime("3000");
		int presentquantity = mc.getPresentProductQuantity();
		int requiredquantity = presentquantity-2;
		for(int i=presentquantity;i>requiredquantity;i--)
		{
			mc.clickMinusButton();
			Thread.sleep(5000);
		}
		int quantity = mc.getPresentProductQuantity();
		 Assert.assertEquals(quantity,requiredquantity);	
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Decrease Product Quantity In Minicart testing ***");
	////button[@class='ProductWishlistButton-Button Button MiniCartItem-WishListButto2n']		
	}
    @Test(groups="Sanity", priority=4)
	public void applyCouponCodeValidation() throws InterruptedException
	{
		logger.info("*** staring TC009 Apply Coupon Code In Mini Cart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
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
		logger.info("*** finished TC009 Apply Coupon Code In Mini Carttesting ***");
		
		
	}
	@Test(groups="Sanity", priority=5)
	public void removeCouponCodeValidation() throws InterruptedException
	{
		logger.info("*** staring TC009 Remove Coupon Code In Mini Cart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
	    mc.clickRemoveCouponCodeButton();
	    mc.waitRemoveCouponCodeSuccessMsgLoad();
	    String confirmmsg =mc.getRemoveCouponCodeSuccessMsg();		
		Assert.assertEquals(confirmmsg, "Coupon was removed!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Apply Coupon Code In Mini Carttesting ***");
		
		
	}
	@Test(groups="Sanity", priority=6)
	public void removeProductFromMiniCart() throws InterruptedException
	{
		logger.info("*** staring TC009 Remove Coupon Code In Mini Cart testing ***");
		try {
		MiniCartPage mc = new MiniCartPage(driver);
		mc.clickRemoveButton();
		mc.waitRemoveProductSuccessMsgLoad();
		String confirmmsg =mc.getRemoveProductsuccessMsg();		
		Assert.assertEquals(confirmmsg, "You have no items in your shopping cart.");
		mc.clickCartCloseButton();
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC009 Apply Coupon Code In Mini Carttesting ***");
		
		
	}

}
