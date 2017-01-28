package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ApplicationManager extends NavigationHelper {

  FirefoxDriver wd;

  private  GroupHelper groupHelper;

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo ().alert ();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void init() {
    wd = new FirefoxDriver ();
    wd.manage ().timeouts ().implicitlyWait ( 60, TimeUnit.SECONDS );
    wd.get ( "http://localhost/addressbook/" );
    groupHelper = new GroupHelper (wd);
    login ( "admin", "secret" );
  }

  private void login(String username, String password) {
    wd.findElement ( By.name ( "user" ) ).click ();
    wd.findElement ( By.name ( "user" ) ).clear ();
    wd.findElement ( By.name ( "user" ) ).sendKeys ( username );
    wd.findElement ( By.name ( "pass" ) ).click ();
    wd.findElement ( By.name ( "pass" ) ).clear ();
    wd.findElement ( By.name ( "pass" ) ).sendKeys ( password );
    wd.findElement ( By.xpath ( "//form[@id='LoginForm']/input[3]" ) ).click ();
  }

  public void gotoGroupPage() {
    wd.findElement ( By.linkText ( "groups" ) ).click ();
  }

  public void stop() {
    wd.quit ();
  }

  public void enterNewContact() {
    wd.findElement ( By.xpath ( "//div[@id='content']/form/input[21]" ) ).click ();
  }

  public void fillContactForm(ContactData contactData) {
    wd.findElement ( By.name ( "firstname" ) ).click ();
    wd.findElement ( By.name ( "firstname" ) ).clear ();
    wd.findElement ( By.name ( "firstname" ) ).sendKeys ( contactData.getName () );
    wd.findElement ( By.name ( "lastname" ) ).click ();
    wd.findElement ( By.name ( "lastname" ) ).clear ();
    wd.findElement ( By.name ( "lastname" ) ).sendKeys ( contactData.getLastname () );
    wd.findElement ( By.name ( "home" ) ).click ();
    wd.findElement ( By.name ( "home" ) ).clear ();
    wd.findElement ( By.name ( "home" ) ).sendKeys ( contactData.getTelephone () );
    wd.findElement ( By.name ( "email" ) ).click ();
    wd.findElement ( By.name ( "email" ) ).clear ();
    wd.findElement ( By.name ( "email" ) ).sendKeys ( contactData.getEmail () );
  }

  public void gotoAddContactPage() {
    wd.findElement ( By.linkText ( "add new" ) ).click ();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }
}
