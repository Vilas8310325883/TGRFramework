package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
 	@FindBy (xpath="//div[@class='FieldSelect-Clickable']") List<WebElement> selection;
	@FindBy (xpath="//div[@class='FieldSelect-OptionsWrapper FieldSelect-OptionsWrapper_isExpanded']/li") List<WebElement> options;
	@FindBy (xpath="//button[@class='CartPage-CheckoutButton Button']") WebElement go_to_checkout;
	@FindBy(xpath="//p[contains(text(),'Product was added to cart!')]") WebElement Success_Msg;
	@FindBy (xpath="//div[@class='ExpandableContent-Heading CartPage-Coupon-ExpandableContentHeading']") WebElement promo_code_button;
	@FindBy (xpath="//input[@id='couponCode']") WebElement promocode_field;	
	@FindBy (xpath="//button[@class='CartCoupon-Button CartCoupon-Button_isHollow']") WebElement apply_button;
	@FindBy (xpath="//p[contains(text(),'Discount code applied')]") WebElement Coupon_code_success_msg;
	@FindBy(xpath="//button[@class='CartCoupon-Button CartCoupon-Button_isHollow']") WebElement remove_coupon_code_button;	
	@FindBy(xpath="//p[contains(text(),'Coupon was removed!')]") WebElement Remove_coupon_code_Success_Msg;
	@FindBy(xpath="//button[@title='Add to Wishlist']") WebElement Add_To_Wishlist;
	@FindBy(xpath="//p[contains(text(),'Product added to wish-list!')]") WebElement Add_To_Wishlist_Success_Msg;
	@FindBy(xpath="//button[@title='Remove from Wishlist']") WebElement Remove_Frorm_Wishlist;
	@FindBy(xpath="//p[contains(text(),'Product has been removed from your Wish List!')]") WebElement Remove_Frorm_Wishlist_Success_Msg;
	@FindBy(xpath="//div[@class='Field Field_type_number CartItem-Qty']/input") WebElement present_product_qunatity;
	@FindBy(xpath="//button[@aria-label='Add']") WebElement plus_button;	
	@FindBy(xpath="//button[@aria-label='Subtract']") WebElement minus_button;	
	@FindBy(xpath="//span[@aria-label='Items in cart']") WebElement CartQuantity;
	@FindBy(xpath="//button[contains(text(),'Remove')]") WebElement remove_product_button;
	@FindBy(xpath="//h3[contains(text(),'Your Cart Is Empty')]") WebElement remove_product_success_msg;
	@FindBy(xpath="//button[@class='CartPage-CheckoutButton Button']") WebElement proceed_to_checkout_button;
	public void clickSelectButton()
	{
		selection.get(1).click();
	}
	public void selectOption(String requiredOption)
	{
		for(int k=0;k<options.size();k++)
		{
		String AvailableOptions = 	options.get(k).getText();
		if(AvailableOptions.equalsIgnoreCase(requiredOption))
		{
			options.get(k).click();
		}
		}
	}
	public void addFreeBeeToCart(String freebeename)
	{
		driver.findElement(By.xpath("//div[@class='FreebiesCard-Details']/span[contains(text(),'"+freebeename+"')]/parent::div[@class='FreebiesCard-Details']/child::div/div/button")).click();
	}
	public void removeFreeBeeFromCart(String freebeename)
	{
		driver.findElement(By.xpath("//p[contains(text(),'"+freebeename+"')]/parent::span/parent::div/parent::div/parent::div/parent::div/child::div/div/div/button")).click();
	}
	public String GetSuccessMsg()
	{
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
		 wait1.until(ExpectedConditions.visibilityOf(Success_Msg));
		String SuccessMsg = Success_Msg.getText();
		return SuccessMsg;
	}
	public void clickGoToCheckout()
	{
		go_to_checkout.click();
	}
	public void clickPromoCodeButton()
	{
		promo_code_button.click();
	}
	public void enterPromoCode(String promocode)
	{
		promocode_field.sendKeys(promocode);
	}
	public void clickApplyButton()
	{
		apply_button.click();
	}
	public String getCouponCodeSuccessMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Coupon_code_success_msg));
		String successmsg = Coupon_code_success_msg.getText();		
		return successmsg;		
	}
	public void clickRemoveCouponCodeButton()
	{
		remove_coupon_code_button.click();
	}
	public void waitRemoveCouponCodeSuccessMsgLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  wait.until(ExpectedConditions.visibilityOf(Remove_coupon_code_Success_Msg));
	}
	public String getRemoveCouponCodeSuccessMsg()
	{
		String successmsg = Remove_coupon_code_Success_Msg.getText();
		return successmsg;
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
	public void waitRemoveFromWishListButtonLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  wait.until(ExpectedConditions.visibilityOf(Remove_Frorm_Wishlist));
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
	public int getPresentProductQuantity()
	{
		String quantity = present_product_qunatity.getAttribute("value");
		System.out.println(quantity);
		 int presentquantity = Integer.parseInt(quantity);
		return presentquantity;
	}
	public void clickPlusButton()
	{
		plus_button.click();
	}
	public void clickMinusButton()
	{
		minus_button.click();
	}
	public int GetCartQuantity()
	{
		String cartquantity = CartQuantity.getText();
		int cartquantityvalue = Integer.parseInt(cartquantity);
		return cartquantityvalue;		
	}
	public void clickRemoveButton(String productname)
	{
		driver.findElement(By.xpath("//p[contains(text(),'"+productname+"')]/parent::a/parent::div/parent::div/parent::div/parent::div/child::div/div/div/button[@id='RemoveItem']")).click();
	}
	public void clickRemoveProductButton()
	{
		remove_product_button.click();
	}
	public void waitRemoveProductSuccessMsgLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		  wait.until(ExpectedConditions.visibilityOf(remove_product_success_msg));
	}
	public String getRemoveProductSuccessMsg()
	{
		String successmsg = remove_product_success_msg.getText();
		return successmsg;
	}
	public void moveToPromoCodeField()
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(promo_code_button).perform();
	}
	public void setDeliveryInsurance(String insuranceValue) throws InterruptedException
	{
		List<WebElement>selection = driver.findElements(By.xpath("//div[@class='FieldSelect-Clickable']"));
		selection.get(1).click();
		Thread.sleep(3000);
		List<WebElement> options = driver.findElements(By.xpath("//div[@class='FieldSelect-OptionsWrapper FieldSelect-OptionsWrapper_isExpanded']/li"));
		for(int k=0;k<options.size();k++)
		{
		String AvailableOptions = 	options.get(k).getText();
		String ExpectedOption = insuranceValue;
		if(AvailableOptions.equalsIgnoreCase(ExpectedOption))
		{
			options.get(k).click();
		}
		}
	}
	public void clickProceedToCheckoutButton()
	{
		proceed_to_checkout_button.click();
	}
}
