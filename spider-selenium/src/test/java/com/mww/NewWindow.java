package com.mww;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class NewWindow {
  
    private static WebDriver driver;
      
    @BeforeClass
    public static void before(){
        System.setProperty("webdriver.chrome.driver", Test1.class.getClassLoader().getResource("chromedriver.exe").getPath());
        driver=new ChromeDriver();
        driver.get("https://www.baidu.com");  
        driver.manage().window().maximize();  
        System.out.println("there are " + driver.getWindowHandles().size() + " window");  
    }  
    @AfterClass
    public static void after(){  
//        driver.quit();
    }  
      
    @Test
    public void getWindowMethod1() throws Exception{
        String url = "http://www.sina.com.cn";
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.open('" + url + "')");
//        switchToWindow("新标签页", driver);
//        driver.get("http://www.sina.com.cn");
        executor.executeScript("window.open('https://www.cnblogs.com/xinxin1994/p/7289570.html')");
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            Thread.sleep(2000);
            System.out.println(driver.getTitle());
        }
        System.out.println("there are " + windowHandles.size() + " windows");
    }
      
    @Test
    public void getWindowMethod2(){  
        JavascriptExecutor oJavaScriptExecutor = (JavascriptExecutor)driver;
        oJavaScriptExecutor.executeScript("window.open();");  
        System.out.println("there are " + driver.getWindowHandles().size() + " windows");  
    }  
      
    @Test  
    public void getWindowMethod3(){  
        String href = driver.findElement(By.partialLinkText("新闻")).getAttribute("href");
        JavascriptExecutor oJavaScriptExecutor = (JavascriptExecutor)driver;  
        oJavaScriptExecutor.executeScript("window.open('" + href + "')");  
        System.out.println("there are " + driver.getWindowHandles().size() + " windows");  
    }  
      
    public static boolean switchToWindow(String windowTitle,WebDriver dr){    
        boolean flag = false;    
        try {   
            //将页面上所有的windowshandle放在入set集合当中  
            String currentHandle = dr.getWindowHandle();    
            Set<String> handles = dr.getWindowHandles();
            for (String s : handles) {    
                if (s.equals(currentHandle))    
                    continue;    
                else {    
                    dr.switchTo().window(s);  
            //和当前的窗口进行比较如果相同就切换到windowhandle  
            //判断title是否和handles当前的窗口相同  
                    if (dr.getTitle().contains(windowTitle)) {    
                        flag = true;    
                        System.out.println("Switch to window: "    
                                + windowTitle + " successfully!");    
                        break;    
                    } else    
                        continue;    
                }    
            }    
        } catch (Exception e) {    
            System.out.printf("Window: " + windowTitle    
                    + " cound not found!", e.fillInStackTrace());    
            flag = false;    
        }    
        return flag;    
    }   
}  