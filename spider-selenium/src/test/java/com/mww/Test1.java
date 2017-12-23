package com.mww;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2017/12/23.
 */
public class Test1 {
    WebDriver driver;

    @Before
    public void beforeMethod() {
        //Use IE. u need to set drive path and capabilities
/*          System.setProperty("webdriver.ie.driver", "D:/drive/IEDriverServer.exe");
            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver
                            .INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            WebDriver driver = new InternetExplorerDriver();*/

        //Use Firefox.need set property and install path;
/*          System.setProperty("webdriver.gecko.driver", "D:/drive/geckodriver.exe");
            System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            WebDriver driver = new FirefoxDriver();*/

        //Use Chrome ,but support 32-bit only
        System.setProperty("webdriver.chrome.driver", Test1.class.getClassLoader().getResource("chromedriver.exe").getPath());
    }

    @After
    public void afterMethod() {

        System.out.println("Page title is: " + driver.getTitle());
        driver.manage().window().maximize();

//        driver.quit();
//        for (int i = 0; i < 100; i++) {
//            js.executeScript("alert('自动执行的js')");
//        }
    }

    @Test
    public void test_case3() throws Exception {

        driver = new ChromeDriver();

        driver.get("http://www.baidu.com");

        //get input box
        WebElement keyword = driver.findElement(By.id("kw"));
        //enter keyword
        keyword.sendKeys("selenium");
        //get search button and click it
        WebElement searchBtn = driver.findElement(By.id("su"));
        searchBtn.click();
        Thread.sleep(2000);//waiting for the result
        //get result and print out cyclical--搜索结果是h3标签class=“t”，故以此为条件
        List<WebElement> titles = driver.findElements(By.cssSelector("h3.t"));
        for (WebElement title : titles) {
            WebElement webTitle = title.findElement(By.tagName("a"));
            System.err.println("webTitle:" + webTitle + ":" + webTitle.getText());
        }

    }

    @Test
    public void name() throws Exception {
        driver = new ChromeDriver();
        driver.get("http://www.sina.com");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
        String title=driver.getTitle();
        System.out.println("title="+title);

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String current = driver.getWindowHandle();
        System.out.println("current="+current);
        Actions action = new Actions(driver);
        action.sendKeys(Keys.CONTROL + "t").perform();
//        switchToWindow("新标签页", driver);
//        driver.get("http://www.sina.com.cn");
        Thread.sleep(2000);
        driver.get("http://www.baidu.com");
        Set<String> otherWins = driver.getWindowHandles();
        for(String winId : otherWins)
        {
            System.out.println("winId="+winId);
            if(winId.equals(current)){
                System.out.println("true");
                continue;
            }
            else{
                System.out.println("false");
                driver.switchTo().window(winId).close();//close()不能被替换为quit()
            }

        }
        driver.switchTo().window(current);
        System.out.println("title="+driver.getTitle());
        driver.quit();
    }
}
