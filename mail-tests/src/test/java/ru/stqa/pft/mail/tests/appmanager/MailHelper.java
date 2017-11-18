package ru.stqa.pft.mail.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.mail.tests.model.MailData;

/**
 * Created by Administrator on 19.11.2017.
 */
public class MailHelper {
  private ChromeDriver wd;

  public MailHelper(ChromeDriver wd) {
    this.wd = wd;

  }

  public void sentMail(MailData mailData) {
      wd.findElement( By.id("to")).click();
      wd.findElement(By.id("to")).clear();
      wd.findElement(By.id("to")).sendKeys( mailData.getTo () );
      wd.findElement(By.name("subject")).click();
      wd.findElement(By.name("subject")).clear();
      wd.findElement(By.name("subject")).sendKeys( mailData.getTitle () );
      wd.findElement(By.id("text")).click();
      wd.findElement(By.id("text")).clear();
      wd.findElement(By.id("text")).sendKeys( mailData.getText () );
      wd.findElement(By.name("send")).click();
  }

  public void initMailCreation() {
      wd.findElement( By.xpath("//p[@class='make_message']//a[.='Создать письмо']")).click();
  }
}
