package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MiniCartPage;
import pageObjects.PDPPage;
import pageObjects.PLPPage;

public class TC007_AddToCartPLPTest extends BaseClass {
	@Test(groups="Sanity")
	public void PLPAddToCart() throws InterruptedException
	{
		logger.info("*** staring TC007 PLP Add To Cart testing ***");
		try {
			
			String confirmmsg = addToCartPLP();
			Assert.assertEquals(confirmmsg, "Product was added to cart!");
	}
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		finally
		{
			MiniCartPage mc = new MiniCartPage(driver);
			mc.clickCartButton();
			mc.clickRemoveButton();
			mc.waitRemoveProductSuccessMsgLoad();
			mc.clickCartCloseButton();
			
		}
		logger.info("*** finished TC004 PLP Add To Cart testing ***");
	}
}

