package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class ContactCreationTests {
  FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    wd = new FirefoxDriver ();
    wd.manage ().timeouts ().implicitlyWait ( 60, TimeUnit.SECONDS );
    wd.get ( "http://localhost/addressbook/" );
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

  @Test
  public void testContactCreation() {

    gotoAddContactPage ();
    fillContactForm ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com" ) );
    enterNewContact ();
  }

  private void enterNewContact() {
    wd.findElement ( By.xpath ( "//div[@id='content']/form/input[21]" ) ).click ();
  }

  private void fillContactForm(ContactData contactData) {
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

  private void gotoAddContactPage() {
    wd.findElement ( By.linkText ( "add new" ) ).click ();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit ();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo ().alert ();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
