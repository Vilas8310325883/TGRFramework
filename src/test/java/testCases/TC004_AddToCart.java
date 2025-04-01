package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PDPPage;
import pageObjects.PLPPage;
import pageObjects.RegisterPage;

public class TC004_AddToCart extends BaseClass{
	@Test(groups="Sanity")
	public void AddToCart() throws InterruptedException
	{
		logger.info("*** staring TC004 PDP Add To Cart testing ***");
		try {
	
		String confirmmsg = addToCartFromPDP();
			Assert.assertEquals(confirmmsg, "Product was added to cart!");
         }
		catch(Exception e)
		{
			logger.error("Test Failed");
			logger.debug("Debug Logs");
			Assert.fail();
		}
		logger.info("*** finished TC004 PDP Add To Cart testing ***");
}
}