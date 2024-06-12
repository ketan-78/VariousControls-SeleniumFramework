package Framework.ReusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wait {
	WebDriver driver;			 
	public wait(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void NewWindowWait(By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void NewTabWait(By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void scrollWait(By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void TableScrollWait(By locator)
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(4));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public void HideAndShowWait() throws InterruptedException
	{
		Thread.sleep(1000);
	}
	
	
}
