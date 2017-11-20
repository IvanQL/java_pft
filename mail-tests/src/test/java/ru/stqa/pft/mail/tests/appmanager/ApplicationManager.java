package ru.stqa.pft.mail.tests.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 19.11.2017.
 */
public class ApplicationManager {

  ChromeDriver wd;

  private SessionHelper sessionHelper;

  private MailHelper mailHelper;

  public void init() {
    wd = new ChromeDriver ();
    wd.manage ().timeouts ().implicitlyWait ( 60, TimeUnit.SECONDS );
    sessionHelper = new SessionHelper ( wd );
    mailHelper = new MailHelper ( wd );
    wd.get ( "http://www.i.ua/" );
    sessionHelper.login ( "for-tests", "21107abc" );
  }


  public void GoToSentPage() {
    wd.findElement ( By.xpath ( "//div[3]/div[3]/div[3]/div[2]/div[1]/div[3]/form/p/input" ) ).click ();  // в идеале нужно вынести в помошник NavigationHelper
  }

  public void stop() {
    wd.quit ();
  }

  public MailHelper getMailHelper() {
    return mailHelper;
  }
}
