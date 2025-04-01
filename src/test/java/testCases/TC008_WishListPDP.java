package testCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HomePage;
import pageObjects.PDPPage;
import pageObjects.PLPPage;

public class TC008_WishListPDP extends BaseClass {
	@Test(groups="Sanity", priority = 0)
	public void PDPAddToWishList() throws InterruptedException
	{
		logger.info("*** staring TC004 PDP Add To Cart testing ***");
		try {
	//	loadPropertyFile();
			String productInfo = p.getProperty("productinfo");
			System.out.println(productInfo);
		HomePage hp = new HomePage(driver);
		PDPPage pdp = new PDPPage(driver);
		PLPPage plp = new PLPPage(driver);
		hp.clickMegaMenu();
		hp.waitTime();
		String categoryDetails = getCategoryInfo(productInfo);
		hp.categorySelection(categoryDetails);
		String productDetails = getProductInfo(productInfo);
		String productname = getProductName(productDetails);
		plp.waitTime("3000");
		plp.ScrollDown();
		plp.waitElementload();
		int count=0;
		while(count==0)
		{
			String requiredproduct = productname;
	List<WebElement> AvailableProducts = plp.GetProducts();
	for(int i=0;i<AvailableProducts.size();i++)
	{		
		Thread.sleep(5000);
		String productname1 = plp.GetProductName(i);
		System.out.println(productname1);
		if(productname1.equalsIgnoreCase(requiredproduct))
		{
			System.out.println(productname1);
			Thread.sleep(2000);
			WebElement description=	AvailableProducts.get(i);
			Iterator<String> ab = plp.OpenProductInNewTab(description);
			String ParentId=ab.next();
			while(ab.hasNext())
			{
				plp.SwitchToPDP(ab);
				Thread.sleep(5000);
				System.out.println("aa");
				pdp.waitAddtoWishlistButtonLoad();
				pdp.clickAddToWishListButton();
				System.out.println("aa1");
				pdp.waitAddtoWishlistSuccessMsgLoad();
			    String successmsg = pdp.getAddToWishListSuccessMsg();
				System.out.println(successmsg);
				Assert.assertEquals(successmsg, "Product added to wish-list!");
				System.out.println("Vilas11");
				count++;
				}
			break;
			}
		}
	 System.out.println("Vilas");
	plp.waitTime("5000");
	if(count==1)
	{
		break;
	}
	else
	{
		driver.navigate().refresh();
    	Thread.sleep(5000);	
    	plp.ClickNextButton();
		Thread.sleep(10000);
}
		}
	}
	catch(Exception e)
	{
		logger.error("Test Failed");
		logger.debug("Debug Logs");
		Assert.fail();
	}
	logger.info("*** finished TC004 PDP Add To Cart testing ***");
}
	@Test(groups="Sanity",priority = 1)
	public void PDPRemoveFromWishList() throws InterruptedException
	{
		PDPPage pdp = new PDPPage(driver);
		pdp.clickRemoveFromWishListButton();
	    pdp.waitRemoveFromWishlistSuccessMsgLoad();
	    String successmsg = pdp.getRemoveFromWishListSuccessMsg();
		  System.out.println(successmsg);
		  Assert.assertEquals(successmsg, "Product has been removed from your Wish List!");
		  ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		    driver.close();
		    driver.switchTo().window(tabs.get(0));
		  
		//	driver.switchTo().window(ParentId);
		//	driver.navigate().refresh();
	}
	
}

		
