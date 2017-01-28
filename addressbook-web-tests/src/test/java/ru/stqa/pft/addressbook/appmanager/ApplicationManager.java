package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ApplicationManager {
  FirefoxDriver wd;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    wd = new FirefoxDriver ();
    wd.manage ().timeouts ().implicitlyWait ( 60, TimeUnit.SECONDS );
    wd.get ( "http://localhost/addressbook/" );
    groupHelper = new GroupHelper ( wd );
    navigationHelper = new NavigationHelper ( wd );
    sessionHelper = new SessionHelper ( wd );
    contactHelper = new ContactHelper ( wd );
    sessionHelper.login ( "admin", "secret" );
  }


  public void stop() {
    wd.quit ();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
