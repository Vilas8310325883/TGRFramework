package pageObjects;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PLPPage extends BasePage {
	public PLPPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//div[@class='SelectCountry-SearchDropDown']") WebElement country_dropdown;
	@FindBy(xpath="//a[@aria-label='Next page']") WebElement next_button;
	@FindBy(xpath="//button[@class='Button AddToCart Button AddToCart_layout_grid ProductCard-AddToCart']") WebElement Add_Basket;
	public List<WebElement> products;
	
	public void waitTime(String time) throws InterruptedException
	{
	    int waitTime=Integer.parseInt(time); 
		Thread.sleep(waitTime);
	}
	public void ScrollDown() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,1500)");
	}
	public void ScrollUp() throws InterruptedException
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,-1500)");
	}
	public void ScrollToElement(String productname)
	{
		WebElement element = driver.findElement(By.xpath("//p[contains(text(),'"+productname+"')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	public void addToBasket(String productname)
	{
		System.out.println(productname);
		try {
			driver.findElement(By.xpath("//p[contains(text(),'"+productname+"')]/parent::a/parent::div/parent::div/parent::div/child::div[@class='ProductCard-productPriceAddToCart']/child::div[@class='Button AddToCartPopup ProductCard-AddToCart']")).click();
		}
		catch(Exception e1)
		{
			driver.findElement(By.xpath("//p[contains(text(),'"+productname+"')]/parent::a/parent::div/parent::div/parent::div/child::div[@class='ProductCard-productPriceAddToCart']/child::button[@class='Button AddToCart Button AddToCart_layout_grid ProductCard-AddToCart']")).click();
		}
		}
	public void selectConfigOption(String configType)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+configType+"')]")).click();
	}
	public void waitElementload()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(next_button));
	//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@aria-label='Next page']")));
	}
	public String GetNextAttribute()
	{
		String Text = next_button.getAttribute("class");
		return Text;
	}
	public List<WebElement> GetProducts()
	{
		this.products = driver.findElements(By.xpath("//p[@class='ProductCard-Name']"));
		return products;	
	}
	public String GetProductName(int i)
	{
		String productName = products.get(i).getText();
		return productName;
	}
	public void ClickAddBasketButton()
	{
		Add_Basket.click();
	}
	public void ClickNextButton()
	{
		next_button.click();
	}
	public Iterator<String> OpenProductInNewTab(WebElement description ) throws InterruptedException
	{
		Actions actions = new Actions(driver);
        Action action = actions.keyDown(Keys.CONTROL).click(description).keyUp(Keys.CONTROL).build();
        action.perform();
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> it =windows.iterator();
	//	String ParentId=it.next();
		return it;
	}
	public void SwitchToPDP(Iterator<String> it) throws InterruptedException
	{
		driver.switchTo().window(it.next());
		waitTime("5000");
		driver.navigate().refresh();
		waitTime("5000");
	}
}
