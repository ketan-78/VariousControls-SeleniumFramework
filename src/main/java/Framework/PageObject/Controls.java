package Framework.PageObject;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Framework.ReusableComponents.wait;

public class Controls extends wait {
	WebDriver driver;
	public Controls(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[@id='radio-btn-example']/fieldset/label")
	List<WebElement> Radio;
	By RadioLink=By.xpath("//input[@class='radioButton']");
	
	@FindBy(id="autocomplete")
	WebElement suggestion;
	@FindBy(css="[class='ui-menu-item-wrapper']")
	List<WebElement> CountriesList;
	@FindBy(id="dropdown-class-example")
	WebElement drop;
	@FindBy(xpath="//div[@id='checkbox-example']/fieldset/label")
	List<WebElement> checkboxes;
	By OptionLink=By.xpath("//input[@type='checkbox']");
	
	public void FirstRow(String RadioOPtion,int RadioIndex,String CountryInitials,String Country,String dropdownOP,String CheckboxOP,int optionIndex)
	{
		Radio.stream().filter(s->s.getText().contains(RadioOPtion)).forEach(s->s.findElements(RadioLink).get(RadioIndex).click());
		suggestion.sendKeys(CountryInitials);
		
		CountriesList.stream().filter(s->s.getText().trim().equalsIgnoreCase(Country)).forEach(s->s.click());
		Select dropdown=new Select(drop);
		dropdown.selectByVisibleText(dropdownOP);
		
		checkboxes.stream().filter(s->s.getText().trim().contains(CheckboxOP))
		.forEach(s->s.findElements(OptionLink).get(optionIndex).click()); 
	}
	
	@FindBy(id="openwindow")
	WebElement newWindow;
	By NewWindowLocator=By.cssSelector("[data-animation='bounceInLeft']");
	public void NewWindow()
	{
		newWindow.click();
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> ides=windows.iterator();
		String parentID=ides.next();
		String childID=ides.next();
		driver.switchTo().window(childID);
		driver.manage().window().maximize();
		NewWindowWait(NewWindowLocator);
		driver.close();
		driver.switchTo().window(parentID);
	}
	@FindBy(id="opentab")
	WebElement newTab;
	By NewTabLocator=By.cssSelector("[data-animation='bounceInLeft']");
	public void NewTab()
	{
		newTab.click();
		Set<String> tab=driver.getWindowHandles();
		Iterator<String> tabs=tab.iterator();
		String parentTab=tabs.next();
		String childTab=tabs.next();
		driver.switchTo().window(childTab);
		NewTabWait(NewTabLocator);
		driver.close();
		driver.switchTo().window(parentTab);
	}
	@FindBy(id="name")
	WebElement name;
	@FindBy(id="alertbtn")
	WebElement AlertBtn;
	@FindBy(id="confirmbtn")
	WebElement ConfirmBtn;
	
	public void AlertsOptions(String Name)
	{
		name.sendKeys(Name);
		AlertBtn.click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		name.sendKeys(Name);
		ConfirmBtn.click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
	}
	By scrollLocator=By.cssSelector("[name='courses'] tr:nth-child(11)");
	By TableScrollLocator=By.cssSelector("[class='tableFixHead'] tr:nth-child(9)");
	@FindBy(css="#product tr:nth-child(4)")
	WebElement TableElement;
	@FindBy(id="displayed-text")
	WebElement Text;
	@FindBy(id="hide-textbox")
	WebElement Hide;
	@FindBy(id="show-textbox")
	WebElement Show; 
	boolean value1,value2;
	@FindBy(css="[class='tableFixHead'] tr:nth-child(9)")
	WebElement TableScrollElement;
	
	public void ThirdRow(String tableScroll,String DisplayText) throws InterruptedException 
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,600)");	
		scrollWait(scrollLocator);
		System.out.println(TableElement.getText());
		js.executeScript(tableScroll);
		TableScrollWait(TableScrollLocator);
		System.out.println(TableScrollElement.getText());
		Text.sendKeys(DisplayText);
		HideAndShowWait();
		Hide.click();
		HideAndShowWait();
		value1=Text.isDisplayed();
		if(value1)
			System.out.println("Is displayed");
		else
			System.out.println("Not displayed");
		Show.click();
		HideAndShowWait();
		value2=Text.isDisplayed();
		if(value2)
			System.out.println("Is displayed");
		else
			System.out.println("Not displayed");
			
	}
}
