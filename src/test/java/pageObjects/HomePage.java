package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {   
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//div[@class='SelectCountry-SearchDropDown']") WebElement country_dropdown;
	@FindBy(xpath="//div[contains(text(),'United Kingdom')]") WebElement country;
	@FindBy(xpath="//input[@name='confirm_country']") WebElement confirm_checkbox;
	@FindBy(xpath="//button[contains(text(),'Agree & Enter')]") WebElement agree_enter;
	@FindBy(how=How.ID, using="myAccount") WebElement txt_account;
	@FindBy(xpath="//div[contains(text(),'Sign-out')]") WebElement Signout;
	public void clickDropdown()
	{
		country_dropdown.click();
	}
	public void waitTime() throws InterruptedException
	{
		Thread.sleep(5000);
	}
	public void selectCountry()
	{
		country.click();
	}
	public void selectCheckbox()
	{
		confirm_checkbox.click();
	}
	public void clickAgreeEnter()
	{
		agree_enter.click();
	}
	public void clickSignout()
	{
		Signout.click();
	}
	
}
