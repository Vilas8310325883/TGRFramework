package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

	public RegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(id="myAccount") WebElement myAccountbutton;
	@FindBy(xpath="//p[contains(text(),'CREATE AN ACCOUNT')]") WebElement create_account_button;
	@FindBy(id="firstname") WebElement firstname;
	@FindBy(id="lastname") WebElement lastname;
	@FindBy(id="email") WebElement email;
	@FindBy(id="date_of_birth") WebElement date_of_birth;
	@FindBy(id="password") WebElement password;
	@FindBy(id="confirm_password") WebElement confirm_password;
	@FindBy(xpath="//input[@id='is_subscribed']") WebElement subscription;
	@FindBy(id="allow_remote_shopping_assistance") WebElement remote_shopping_assistance;
	@FindBy(xpath="//button[@type='submit']") WebElement submit;
	@FindBy(xpath="//p[contains(text(),'You are successfully logged in!')]") WebElement confirmation_msg;
	public void clickmyAccountbutton()
	  {
		myAccountbutton.click();
	  }
	public void clickcreate_account_button()
	  {
		create_account_button.click();
	  }
	
	 public void setfirstname(String firstname_value)
	  {
		 firstname.sendKeys(firstname_value);
	  }
	 public void setlastname(String lastname_value)
	  {
		 lastname.sendKeys(lastname_value);
	  }
	 public void setemail(String email_value)
	  {
		 email.sendKeys(email_value);
	  }
	 public void setDOB(String DOB_value)
	  {
		 date_of_birth.sendKeys(DOB_value);
	  }
	 public void setpassword(String password_value)
	  {
		 password.sendKeys(password_value);
	  }
	 public void setconfirmpassword(String confirmpassword_value)
	  {
		 confirm_password.sendKeys(confirmpassword_value);
	  }
	  public void clicksubscription()
	  {
		  subscription.click();
	  }
	  public void clickremote_shopping_assistance()
	  {
		  remote_shopping_assistance.click();
	  }
	  public void clicksubmit()
	  {
		  submit.click();
	  }
	  public String getConfirmationMsg()
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
	
	

}
