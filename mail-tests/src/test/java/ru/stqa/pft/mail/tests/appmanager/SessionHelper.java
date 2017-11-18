package ru.stqa.pft.mail.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Administrator on 19.11.2017.
 */
public class SessionHelper {
  private ChromeDriver wd;

  public SessionHelper(ChromeDriver wd) {

    this.wd = wd;
  }

  public void login(String username, String password) {
    wd.findElement( By.name("login")).click();
    wd.findElement(By.name("login")).clear();
    wd.findElement(By.name("login")).sendKeys( username );
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys( password );
    wd.findElement(By.xpath("//div[3]/div[3]/div[3]/div[2]/div[2]/div[3]/h2")).click();
  }
}
