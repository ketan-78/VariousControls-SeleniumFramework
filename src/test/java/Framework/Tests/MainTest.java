package Framework.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.TestComponents.BaseTest;

public class MainTest extends BaseTest{

	public int RadioIndex=2;
	public int OptionIndex=2;
	@Test(dataProvider="data")
	public void funtion(HashMap<String,String> content) throws InterruptedException 
	{
		ctrl.FirstRow(content.get("RadioOPtion"),RadioIndex, content.get("CountryInitials"), content.get("Country"), content.get("dropdownOP"),content.get("CheckboxOP"),OptionIndex);
		ctrl.NewWindow();
		ctrl.NewTab();
		ctrl.AlertsOptions(content.get("Name"));
		ctrl.ThirdRow(content.get("tableScroll"), content.get("DisplayText"));
	}
	@DataProvider
	@Test 
	public Object[][] data() throws IOException
	{
		List<HashMap<String,String>> data=getData(System.getProperty("user.dir")+"\\src\\test\\java\\Framework\\Data\\Data.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}

