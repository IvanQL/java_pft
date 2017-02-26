package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser)  {
    this.browser = browser;
    properties = new Properties (  );
  }

  public void init() throws IOException {
    String target = System.getProperty ( "target", "local" );
    properties.load ( new FileReader ( new File ( String.format ( "src/test/resources/%s.properties", target ) ) ) );

    if (Objects.equals ( browser, BrowserType.FIREFOX )) {
      wd = new FirefoxDriver ();
    } else if (Objects.equals ( browser, BrowserType.CHROME )) {
      wd = new ChromeDriver ();
    } else if (Objects.equals ( browser, BrowserType.IE )) {
      wd = new InternetExplorerDriver ();
    }

    wd.manage ().timeouts ().implicitlyWait ( 0, TimeUnit.SECONDS );
    wd.get (properties.getProperty ( "web.baseUrl" ));
    groupHelper = new GroupHelper ( wd );
    navigationHelper = new NavigationHelper ( wd );
    sessionHelper = new SessionHelper ( wd );
    contactHelper = new ContactHelper ( wd );
    sessionHelper.login (properties.getProperty ( "web.AdminLogin" ),properties.getProperty ( "web.AdminPassword" )  );
  }


  public void stop() {
    wd.quit ();
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }
}
