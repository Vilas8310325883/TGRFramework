package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@id='points']") WebElement loyality_points_field;
	@FindBy(xpath="//button[contains(text(),'Apply')]") WebElement apply_button;
	@FindBy(xpath="//button[@class='CheckoutAddressBook-Button']") WebElement add_new_address_button;
	@FindBy(xpath="//input[@name='firstname']") WebElement first_name;
	@FindBy(xpath="//input[@name='lastname']") WebElement last_name;
	@FindBy(xpath="//input[@name='telephone']") WebElement telephone;
	@FindBy(xpath="//input[@name='postcode']") WebElement postcode;
	@FindBy(xpath="//*[@id=\"address-country-id_wrapper\"]/div") WebElement select_country_button;
	@FindBy(xpath="//input[@name='street']") WebElement street;
	@FindBy(xpath="//input[@name='street_0']") WebElement street1;
	@FindBy(xpath="//input[@name='city']") WebElement city;
	@FindBy(xpath="//input[@name='alternate_phone_number']") WebElement alternate_number;
	@FindBy(xpath="//input[@name='default_billing']") WebElement default_billing;
	@FindBy(xpath="//input[@name='default_shipping']") WebElement default_shipping;
	@FindBy(xpath="//input[@name='default_address_concent']") WebElement confirm_delivery_address_button;
	@FindBy(xpath="//button[@class='Button Save']") WebElement save_button;
	@FindBy(xpath="//p[contains(text(),'You saved the address')]") WebElement new_address_success_msg;
	@FindBy(xpath="//div[@class='ApplyRewards-successLabel']") WebElement used_loyalty_point;
	@FindBy(xpath="//button[@class='Button CheckoutShipping-ContinueButton']") WebElement continue_button;
	@FindBy(xpath="//button[@class='Button CheckoutShipping-Button']") WebElement continue_shipping_button;
	@FindBy(xpath="//input[@name='confirm_payment']") WebElement confirm_payment_button;
	@FindBy(xpath="//button[@class='Button CheckoutPayment-Button']") WebElement accept_pay_button;
	@FindBy(xpath="//button[contains(text(),'Continue')]") WebElement continue_crypto_button;
	@FindBy(xpath="//input[@name='email']") WebElement email_field;
	@FindBy(xpath="//button[contains(text(),'Mark as PAID')]") WebElement mark_as_paid_field;
	@FindBy(xpath="//button[contains(text(),'Return to Merchant Page')]") WebElement return_to_merchant_page_button;
	@FindBy(xpath="//h3[@class='CheckoutSuccess-Heading']") WebElement thankyou_msg_field;
	@FindBy(xpath="//button[@class='Button Button_isHollow Button_isWithoutBorder']") WebElement edit_address_button;
	@FindBy(xpath="//button[@class='Button MyAccount-Button']") WebElement edit_address_save_button;
	@FindBy(xpath="//p[contains(text(),'You edited the address')]") WebElement edit_address_success_msg;
	@FindBy(xpath="//li[@class='CheckoutOrderSummary-SummaryItem CheckoutOrderSummary-SummaryItem_isTotal']/div") WebElement order_total;
	@FindBy(xpath="//button[@class='ApplyRewards-Button ApplyRewards-Button_isHollow']") WebElement loyality_points_remove_button;
	@FindBy(xpath="//button[@class='Button CheckoutShipping-ContinueButton']") WebElement continue_delivery_button;
	@FindBy(xpath="//div[@class='Image Image_imageStatus_IMAGE_LOADED Logo']") WebElement checkout_logo_button;
	
	
		
	public void addLoyalityPoints(String points)
	  {
		loyality_points_field.sendKeys(points);
	  }
	public void clickApplyButton()
	  {
		apply_button.click();
	  }
	public void clickCheckoutLogo()
	  {
		checkout_logo_button.click();
	  }
	public String usedLoyaltyPoints()
	{
		String loyaltyApplied = used_loyalty_point.getText();
		System.out.println(loyaltyApplied);
	      String loyaltypointscombined[] = loyaltyApplied.split(" ");
	      String loyaltypoints = loyaltypointscombined[0];
	        System.out.println(loyaltypoints);
		return loyaltypoints;
		
	}
	public String getApplyLoyalitySuccessMsg()
	{
	//	String loyalty = usedLoyaltyPoints();	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	//	WebElement loyalitySuccessMsg= driver.findElement(By.xpath("//p[normalize-space()='"+loyalty+" loyalty points spent']"));
	//	WebElement loyalitySuccessMsg= driver.findElement(By.xpath("//div[@class='ApplyRewards-successLabel']"));
		wait.until(ExpectedConditions.visibilityOf(used_loyalty_point));
		String successmsg = used_loyalty_point.getText();
		 System.out.println(successmsg);
		return successmsg;		
	}
	public double getOrderTotal() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,100)");
        Thread.sleep(1000);
		String orderTotal = order_total.getText();
		System.out.println(orderTotal);
		String Values[] = orderTotal.split("€");
		String amount = Values[1];
		double orderAmount = Double.parseDouble(amount);
		return orderAmount;	
	}
	public void waitToLoadRemoveButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(loyality_points_remove_button));
	}
	public void clickLoyaltyPointRemoveButton()
	  {
		loyality_points_remove_button.click();
	  }
	public void waitToLoadAddNewAddressButton()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(add_new_address_button));
	}
	
	public void clickContinueDeliveryButton()
	  {
		continue_delivery_button.click();
	  }
	public void clickAddNewAddressButton()
	  {
		add_new_address_button.click();
	  }
	public void setFirstName(String firstname)
	  {
		first_name.sendKeys(firstname);
	  }
	public void removeFirstName()
	  {
		first_name.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	  }
	public void setLastName(String lastname)
	  {
		last_name.sendKeys(lastname);
	  }
	public void removeLastName()
	  {
		last_name.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	  }
	public void setTelephone(String telephoneno)
	  {
		telephone.sendKeys(telephoneno);
	  }
	public void removeTelephone()
	  {
		telephone.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	  }
	public void setPostcode(String postcodeno)
	  {
		postcode.sendKeys(postcodeno);
	  }
	public void removePostcode()
	  {
		postcode.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	  }
	public void clickSelectCountryButton()
	  {
		select_country_button.click();
	  }
	public void selectRequiredCountry(String requiredcountry)
	{
		List<WebElement> country = driver.findElements(By.xpath("//li[@class='FieldSelect-Option FieldSelect-Option_isExpanded FieldSelect-Option_isHovered_undefined']"));
		for(int i=0;i<country.size();i++)
		{
			String country1 = country.get(i).getText();
			if(country1.equalsIgnoreCase(requiredcountry))
			{
				country.get(i).click();
				break;
			}
		}
	}
	public void setStreet(String streetaddress)
	  {
		try {
		street.sendKeys(streetaddress);
		}
		catch(Exception e1)
		{
			street1.sendKeys(streetaddress);
		}
	  }
	public void removeStreet()
	  {
		try {
			street.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			}
			catch(Exception e1)
			{
				street1.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
			}
		
	  }
	
	public void setCity(String cityName)
	  {
		city.sendKeys(cityName);
	  }
	public void removeCity()
	  {
		city.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	  }

	public void setAlternateNumber(String alternateNumber)
	  {
		alternate_number.sendKeys(alternateNumber);
	  }
	public void removeAlternateNumber()
	  {
		alternate_number.sendKeys(Keys.CONTROL,"a",Keys.DELETE);
	  }
	public void setAddressType(String addresstype)
	  {
		driver.findElement(By.xpath("//input[@id='address-"+addresstype+"']")).click();
	  }
	public void setDefaultBillingAddress()
	  {
		default_billing.click();
	  }
	public void setDefaultShippingAddress()
	  {
		default_shipping.click();
	  }
	public void confirmAddress()
	  {
		confirm_delivery_address_button.click();
	  }
	public void clickSaveButton()
	{
		save_button.click();
	}
	public void waitToLoadNewAddressSuccessMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(new_address_success_msg));
	}
	public String captureAddNewAddressSuccessMsg()
	{
		String successmsg = new_address_success_msg.getText();
		return successmsg;		
	}
	public void waitToLoadEditNewAddressSuccessMsg()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(edit_address_success_msg));
	}
	public String captureEditNewAddressSuccessMsg()
	{
		String successmsg = edit_address_success_msg.getText();
		return successmsg;		
	}
	public void scrollAndClickContinueButton()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,400)");
        continue_button.click();
	}
	public void selectDeliveryMethod(String deliveryMethod) throws InterruptedException
	{
		List<WebElement> deliveryOptions = driver.findElements(By.xpath("//button[@class='CheckoutDeliveryOption-Button']"));
	        String requiredDeliveryMethod = deliveryMethod;
	        System.out.println(requiredDeliveryMethod);
	        for(int k=0;k<deliveryOptions.size();k++)
	        {
	        	String actualdelivery = deliveryOptions.get(k).getText();
	        	System.out.println(actualdelivery);
	        	if(actualdelivery.contains(requiredDeliveryMethod))
	        	{
	        		deliveryOptions.get(k).click();
	        	}	
	}
	}
	 public void scrollAndClickContinueShippingButton()
     {
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("window.scrollBy(0,400)");
		 continue_shipping_button.click();
     }
	 public String scrollAndSetThePaymentMethod(String requiredPaymentMethod) throws InterruptedException
     {
		 String successmsg = null;
		 List<WebElement> payments = driver.findElements(By.xpath("//li[@class='CheckoutPayment']"));
			String requiredPayment = requiredPaymentMethod;
			System.out.println(requiredPayment);
			for(int i=0;i<payments.size();i++)
			{
			String actualPayment = payments.get(i).getText();
			System.out.println(actualPayment);
			if(actualPayment.equalsIgnoreCase(requiredPayment))
			{
				payments.get(i).click();
		        WebElement element = payments.get(i);
		        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
			}
			Thread.sleep(2000);
			if(requiredPayment.equalsIgnoreCase("Bitcoin and Altcoins via CoinGate"))
			{
				confirm_payment_button.click();
				accept_pay_button.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				wait.until(ExpectedConditions.visibilityOf(continue_crypto_button));
				continue_crypto_button.click();
				Thread.sleep(2000);
				List<WebElement> paymentsoptions = driver.findElements(By.xpath("//label[@class='MuiFormControlLabel-root MuiFormControlLabel-labelPlacementEnd  mui-1y59g1s']"));
				for(int i=0;i<paymentsoptions.size();i++)
				{
				String actualPayment = paymentsoptions.get(i).getText();
				String requiredpayementoption = "Bitcoin";
				System.out.println(actualPayment);
				if(actualPayment.equalsIgnoreCase(requiredpayementoption))
				{
					paymentsoptions.get(i).click();
				}
				}
				Thread.sleep(2000);
				continue_crypto_button.click();	
				Thread.sleep(2000);
				email_field.sendKeys("aa@gmail.com");
				Thread.sleep(2000);
				continue_crypto_button.click();	
		Thread.sleep(4000);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("window.scrollBy(0,200)");
		mark_as_paid_field.click();
		Thread.sleep(4000);	
		return_to_merchant_page_button.click();
			//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(thankyou_msg_field));
		 successmsg = thankyou_msg_field.getText();
	//	String ordeId = driver.findElement(By.xpath("//span[@class='OrderNumber']/span")).getText();
	//	System.out.println("Order placed Successfully Your Order ID is "+ordeId+"");
	//	String success = "Order Placed Successfully with the "+requiredPayment+" and the order id will be "+ordeId+"";
	//	driver.findElement(By.xpath("//a[@class=' Header-LogoWrapper']")).click();
	//	Thread.sleep(3000);
			}
			if(requiredPayment.equalsIgnoreCase("Pay by Cash"))
			{
				confirm_payment_button.click();
				accept_pay_button.click();
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
				wait.until(ExpectedConditions.visibilityOf(thankyou_msg_field));
				 successmsg = thankyou_msg_field.getText();
			}
			return successmsg;
     }
	 public void waitToLoadEditAddressButton()
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(edit_address_button));
		}
	 public void clickEditAddressButton()
	  {
		 edit_address_button.click();
	  }
	 public void clickSaveEditAddressButton()
	  {
		 edit_address_save_button.click();
	  }
	
}
