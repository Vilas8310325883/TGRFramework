package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.description.modifier.Visibility;

public class MiniCartPage extends BasePage{
public MiniCartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
		@FindBy (xpath="//button[@class='Header-MinicartButtonWrapper']") WebElement mini_cart;
		@FindBy (xpath="//div[@data-content='Apply discount']") WebElement apply_discount_button;
		@FindBy (xpath="//input[@id='couponCode']") WebElement promocode_field;	
		@FindBy (xpath="//button[@class='CartCoupon-Button CartCoupon-Button_isHollow']") WebElement apply_button;
		@FindBy (xpath="//p[contains(text(),' Discount code applied')]") WebElement success_msg;
		@FindBy (xpath="//a[@class=' CartOverlay-CartButton Button']") WebElement proceed_to_cart;
		@FindBy(xpath="//button[@title='Add to Wishlist']") WebElement Add_To_Wishlist;
		@FindBy(xpath="//p[contains(text(),'Product added to wish-list!')]") WebElement Add_To_Wishlist_Success_Msg;
		@FindBy(xpath="//button[@title='Remove from Wishlist']") WebElement Remove_Frorm_Wishlist;
		@FindBy(xpath="//p[contains(text(),'Product has been removed from your Wish List!')]") WebElement Remove_Frorm_Wishlist_Success_Msg;
		@FindBy(xpath="//div[@class='Field Field_type_number MiniCartItem-Qty']/input") WebElement present_product_qunatity;
		@FindBy(xpath="//button[@aria-label='Add']") WebElement plus_button;	
		@FindBy(xpath="//button[@aria-label='Subtract']") WebElement minus_button;	
		@FindBy(xpath="//button[@class='CartCoupon-Button CartCoupon-Button_isHollow']") WebElement remove_coupon_code_button;	
		@FindBy(xpath="//p[contains(text(),'Coupon was removed!')]") WebElement Remove_coupon_code_Success_Msg;
		@FindBy(xpath="//button[@id='RemoveItem']") WebElement remove_button;
		@FindBy(xpath="//p[@class='CartOverlay-Empty']") WebElement remove_product_success_msg;	
		@FindBy(xpath="//div[@class='Cart-Header']/button") WebElement mini_cart_close_button;	
		public void clickCartButton()
		{
			mini_cart.click();
		}
		public void clickApplyDiscountButton()
		{
			apply_discount_button.click();
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
			wait.until(ExpectedConditions.visibilityOf(success_msg));
			String successmsg = success_msg.getText();		
			return successmsg;		
		}
		public void clickProceedToCartButton()
		{
			proceed_to_cart.click();
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
		public void clickRemoveButton()
		{
			remove_button.click();
		}
		public void waitRemoveProductSuccessMsgLoad()
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			  wait.until(ExpectedConditions.visibilityOf(remove_product_success_msg));
		}
		public String getRemoveProductsuccessMsg()
		{
			String successmsg = remove_product_success_msg.getText();
			return successmsg;
		}
		public void clickCartCloseButton()
		{
			mini_cart_close_button.click();
		}
		

}
