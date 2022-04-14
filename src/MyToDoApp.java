import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.failsafe.internal.util.Assert;

public class MyToDoApp {

	public static void main(String[] args) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://mdn.github.io/todo-react-build");
		
		//TC_002
		driver.findElement(By.id("new-todo-input")).sendKeys("Workout @ 12PM");
		driver.navigate().refresh();
		//TC_003
		String inputData = "Grocery";
		driver.findElement(By.id("new-todo-input")).sendKeys(inputData);
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		String newTask = "";
		List<WebElement> tasks = driver.findElements(By.xpath("//ul/li/div/div/label"));
		for (WebElement task:tasks) {
			if(task.getText().equals(inputData)) {
				newTask = task.getText();
			}
		}
		Assert.isTrue(true, newTask, inputData);
		
		//TC_006
		List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		for(WebElement checkbox:checkboxes) {
			if(!checkbox.isSelected())
				checkbox.click();
		}
		//TC_007
		WebElement sleep = driver.findElement(By.xpath("//li[@class='todo']/div/div/label[contains(text(),'Sleep')]"));
		sleep.findElement(By.xpath("parent::div/input[@type='checkbox']")).click();
		//TC_009
		driver.findElement(By.xpath("//ul/li[2]/div/div/button[1]")).click();
		driver.findElement(By.id("todo-1")).sendKeys("Nap");
		driver.findElement(By.xpath("//div/button[@type='submit']")).click();
		//TC_010
		driver.findElement(By.xpath("//ul/li[2]/div/div/button[2]")).click();
		
		Thread.sleep(3000);
		driver.close();
	}

}
