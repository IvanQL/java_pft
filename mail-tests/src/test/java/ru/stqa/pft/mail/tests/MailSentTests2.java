package ru.stqa.pft.mail.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class MailSentTests2 {
    ChromeDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new ChromeDriver ();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void MailSentTests2() {
        wd.get("http://www.i.ua/");
        wd.findElement(By.name("login")).click();
        wd.findElement(By.name("login")).clear();
        wd.findElement(By.name("login")).sendKeys("for-tests");
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("21107abc");
        wd.findElement(By.xpath("//div[3]/div[3]/div[3]/div[2]/div[2]/div[3]/h2")).click();
        wd.findElement(By.xpath("//div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/p/input")).click();
        wd.findElement(By.xpath("//p[@class='make_message']//a[.='Создать письмо']")).click();
        wd.findElement(By.id("to")).click();
        wd.findElement(By.id("to")).clear();
        wd.findElement(By.id("to")).sendKeys("vendel9891@gmail.com");
        wd.findElement(By.name("subject")).click();
        wd.findElement(By.name("subject")).clear();
        wd.findElement(By.name("subject")).sendKeys("TEST");
        wd.findElement(By.id("text")).click();
        wd.findElement(By.id("text")).clear();
        wd.findElement(By.id("text")).sendKeys("\nThis is test mail");
        wd.findElement(By.name("send")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
