package brokenLink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class BrokenLinkClass {
	
	WebDriver driver;
	
	
	@Given("Web page is opened")
	
	public void web_page_is_opened() {
		
		System.setProperty("webdriver.chrome.driver","C:/Users/Indumathy. B/Downloads/Selenium/Chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://www.google.co.in/");
		
	    }

	@When("Get all the links in web page")
	public void get_all_the_links_in_web_page() {
		
		List<WebElement> links=driver.findElements(By.tagName("a"));
		
		System.out.println("Total links are "+links.size());
		
		for(int i=0;i<links.size();i++)
		{
			
			WebElement ele= links.get(i);
			
			String url=ele.getAttribute("href");
			
			findBrokenLinks(url);		
		}
	}

	@Then("Verify all the links is broken or working")
	public void verify_all_the_links_is_broken_or_working() {
		
		System.out.println("Results are displayed");
		driver.quit();
	}
	
		public static void findBrokenLinks(String linkUrl)
		{
	        try 
	        {
	           URL url = new URL(linkUrl);
	           
	           HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
	           
	           httpURLConnect.setConnectTimeout(3000);
	           
	           httpURLConnect.connect();
	           
	           if(httpURLConnect.getResponseCode()==200)
	           {
	               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
	            }
	          if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
	           {
	               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	            }
	        } catch (Exception e) {
	           
	        }
	    }
		
}
