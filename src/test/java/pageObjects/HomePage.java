package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	@FindBy(xpath="//li[@class='Menu-Item'][1]") WebElement MegaMenu;
	@FindBy(xpath="//span[@aria-label='Items in cart']") WebElement CartQuantity;
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
	public void clickMegaMenu()
	{
		MegaMenu.click();
	}
	public void CategoryHoover (String categoryName)
	{
		Actions actions = new Actions(driver);
		WebElement Level1category =driver.findElement(By.xpath("//span[contains(text(),'"+categoryName+"')]"));
		actions.moveToElement(Level1category).perform();
	}
	public void CategoryClick(String categoryName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+categoryName+"')]")).click();
	}
	public void categorySelection(String category) throws InterruptedException
	{
	try 
	{
	String categories[] = category.split("//") ;
	String Maincategory = categories[0];
	String Subcategory = categories[1];
	System.out.println(Maincategory);
	System.out.println(Subcategory);
	CategoryHoover(Maincategory);
	Thread.sleep(2000);
	CategoryClick(Subcategory);
	}
	catch(ArrayIndexOutOfBoundsException e)
	{
		System.out.println("Vilas");
		CategoryClick(category);
	}
	}
	public String GetCartQuantity()
	{
		String cartquantity = CartQuantity.getText();
		return cartquantity;		
	}
}
	

