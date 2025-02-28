package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy (id="email") WebElement email;
	@FindBy (id="password") WebElement password;
	@FindBy (xpath="//button[contains(text(),'Login')]") WebElement login;
	@FindBy(xpath="//p[contains(text(),'You are successfully logged in!')]") WebElement confirmation_msg;
	@FindBy(xpath="//div[contains(text(),'This field is required!')]") WebElement field_required;
	@FindBy(xpath="//div[contains(text(),'Incorrect email format!')]") WebElement inavlid_email;
	@FindBy(xpath="//p[contains(text(),'The customer does not exist in the website.')]") WebElement invalid_customer;
	@FindBy(xpath="//p[contains(text(),'The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.')]") WebElement disabled_account;
	@FindBy(xpath="//button[@class='Popup-CloseBtn']") WebElement pop_up;
	@FindBy(xpath="//div[contains(text(),'Sign-out')]") WebElement Signout;
	public void setEmail(String email_value)
	{
		email.sendKeys(email_value);
	}
	public void setPassword(String password_value)
	{
		password.sendKeys(password_value);
	}
	public void clickLogin()
	{
	 login.click();
	}
	public String getConfirmationMsg1() throws InterruptedException
	  {
		try {
			  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(confirmation_msg));
			  return(confirmation_msg.getText());
		  }
		  catch(Exception e)
		  {
			  return(e.getMessage());
		  }
	  }
	 public String getConfirmationMsg(String expectedmessage) throws InterruptedException
	  {
		/*try {
			  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(confirmation_msg));
			  return(confirmation_msg.getText());
		  }
		  catch(Exception e)
		  {
			  return(e.getMessage());
		  }		*/
		  
		  String RequiredValidationMessage =expectedmessage;
			if(RequiredValidationMessage.equalsIgnoreCase("You are successfully logged in!"))
			{
				 WebElement guru99seleniumlink;
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
					wait.until(ExpectedConditions.visibilityOf(confirmation_msg));
			}
			else
			{
				Thread.sleep(2000);
			}
			try {
				String MandatoryMissingError = field_required.getText();
				pop_up.click();
					return MandatoryMissingError;
			}
			catch(Exception e)
			{
				try {
				String EmailValidationText = inavlid_email.getText();
				pop_up.click();
					return EmailValidationText;
				}
				catch(Exception e1)
				{
					try {
					String CustomerValidationText = invalid_customer.getText();
					pop_up.click();
						return CustomerValidationText;
					}
					catch(Exception e2)
					{
						try {
						String DisabledAccountValidationText = disabled_account.getText();
						pop_up.click();
							return DisabledAccountValidationText;
						}
						catch(Exception e3)
						{
							try {
							String SuccessMsg = confirmation_msg.getText();
								return SuccessMsg;
							}
							catch(Exception e11)
							{
								return(e.getMessage());
								
							}
						}
					}
				}
			}
			
	  }
}
			
