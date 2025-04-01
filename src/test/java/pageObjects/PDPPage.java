package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PDPPage extends BasePage{

	public PDPPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//div[@class='ProductConfigurableAttributes-ChoiceWrapper']") WebElement config_option_selection;
	@FindBy(xpath="//button[@class='SideOverlay-CloseButtonButton']") WebElement close_button;
	@FindBy(xpath="//input[@name='item_qty']") WebElement product_quantity;
	@FindBy(xpath="//button[@aria-label='Add']") WebElement plus_button;
	@FindBy(xpath="//button[@class='Button AddToCart Button AddToCart_layout_grid ProductActions-AddToCart']") WebElement Add_Basket;
	@FindBy(xpath="//p[contains(text(),'Product was added to cart!')]") WebElement Success_Msg;
	@FindBy(xpath="//button[@title='Add to Wishlist']") WebElement Add_To_Wishlist;
	@FindBy(xpath="//p[contains(text(),'Product added to wish-list!')]") WebElement Add_To_Wishlist_Success_Msg;
	@FindBy(xpath="//button[@title='Remove from Wishlist']") WebElement Remove_Frorm_Wishlist;
	@FindBy(xpath="//p[contains(text(),'Product has been removed from your Wish List!')]") WebElement Remove_Frorm_Wishlist_Success_Msg;
	public List<WebElement> products;
	
	public void ClickConfigOptionSelection() throws InterruptedException
	{
		config_option_selection.click();
	}
	public void waitTime(String time) throws InterruptedException
	{
	    int waitTime=Integer.parseInt(time); 
		Thread.sleep(waitTime);
	}
	public void SelectConfigValue(String configtypevalue)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+configtypevalue+"')]")).click();
	}
	public void ClickCloseButton()
	{
		close_button.click();
	}
	public int GetProductQuantity()
	{
		String quantity = product_quantity.getAttribute("value");
		int PresentNumberQuatity=Integer.parseInt(quantity); 
		return PresentNumberQuatity;
	}
	public void ClickPlusButton()
	{
		plus_button.click();
	}
	public void ClickAddBasketButton()
	{
		Add_Basket.click();
	}
	public String GetSuccessMsg()
	{
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		 wait1.until(ExpectedConditions.visibilityOf(Success_Msg));
		String SuccessMsg = Success_Msg.getText();
		return SuccessMsg;
	}
	public void waitAddtoWishlistButtonLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  wait.until(ExpectedConditions.visibilityOf(Add_To_Wishlist));
	}
	public void clickAddToWishListButton()
	{
		Add_To_Wishlist.click();
	}
	public void waitAddtoWishlistSuccessMsgLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  wait.until(ExpectedConditions.visibilityOf(Add_To_Wishlist_Success_Msg));
	}
	public String getAddToWishListSuccessMsg()
	{
		String successmsg = Add_To_Wishlist_Success_Msg.getText();
		return successmsg;
	}
	public void clickRemoveFromWishListButton()
	{
		Remove_Frorm_Wishlist.click();
	}
	public void waitRemoveFromWishlistSuccessMsgLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  wait.until(ExpectedConditions.visibilityOf(Remove_Frorm_Wishlist_Success_Msg));
	}
	public String getRemoveFromWishListSuccessMsg()
	{
		String successmsg = Remove_Frorm_Wishlist_Success_Msg.getText();
		return successmsg;
	}
	
	

}
