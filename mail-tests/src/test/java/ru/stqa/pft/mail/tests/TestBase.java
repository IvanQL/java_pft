package ru.stqa.pft.mail.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.appmanager.ApplicationManager;

import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 28.01.2017.
 */
public class TestBase {
  ChromeDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
      wd = new ChromeDriver ();
      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
      wd.get("http://www.i.ua/");
      login ( "for-tests", "21107abc" );
  }

  private void login(String username, String password) {
      wd.findElement( By.name("login")).click();
      wd.findElement(By.name("login")).clear();
      wd.findElement(By.name("login")).sendKeys( username );
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys( password );
      wd.findElement(By.xpath("//div[3]/div[3]/div[3]/div[2]/div[2]/div[3]/h2")).click();
  }

  protected void sentMail(MailData mailData) {
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

  protected void initMailCreation() {
      wd.findElement( By.xpath("//p[@class='make_message']//a[.='Создать письмо']")).click();
  }

  protected void GoToSentPage() {
      wd.findElement( By.xpath("//div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/p/input")).click();
  }

  @AfterMethod
  public void tearDown() {
      wd.quit();
  }


  //protected static final ApplicationManager app
         // = new ApplicationManager ( System.getProperty ( "browser", BrowserType.CHROME ));


}
