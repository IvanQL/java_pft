package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super ( wd );
  }

  public void gotoHomePage() {
    if (isElementPresent ( By.id ( "maintable" ) )) {
      return;
    }
    click ( By.linkText ( "home" ) );
  }

  public void enterNewContact() {
    click ( By.xpath ( "//div[@id='content']/form/input[21]" ) );
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type ( By.name ( "firstname" ), contactData.getName () );
    type ( By.name ( "lastname" ), contactData.getLastname () );
    type ( By.name ( "home" ), contactData.getTelephone () );
    type ( By.name ( "email" ), contactData.getEmail () );
    if (creation) {
      new Select ( wd.findElement ( By.name ( "new_group" ) ) ).selectByVisibleText ( contactData.getGroup () );
    } else {
      Assert.assertFalse ( isElementPresent ( By.name ( "new_group" ) ) );
    }

  }

  public void modifyContact(List <ContactData> before, ContactData contact) {
    initContactModification ( before.size () - 1 );
    fillContactForm ( contact, false );
    submitContactModification ();
    gotoHomePage ();
  }

  public void initContactModification(int index) {
    wd.findElements ( By.cssSelector ( "img[alt=\"Edit\"]" ) ).get ( index ).click ();
  }

  public void submitContactModification() {
    click ( By.name ( "update" ) );
  }

  public void selectContact(int index) {
    wd.findElements ( By.name ( "selected[]" ) ).get ( index ).click ();

  }

  public void deleteSelectedContacts() {
    click ( By.xpath ( "//div[@id='content']/form[2]/div[2]/input" ) );
  }

  public void submitDeletionContacts() {
    wd.switchTo ().alert ().accept ();

  }

  public void createContact(ContactData contact, boolean b) {

    fillContactForm ( (contact), true );
    enterNewContact ();
  }

  public boolean isThereAContact() {
    return isElementPresent ( By.name ( "selected[]" ) );
  }

  public int getContactCount() {
    return wd.findElements ( By.name ( "selected[]" ) ).size ();

  }

  public List <ContactData> getContactList() {
    List <ContactData> contacts = new ArrayList <ContactData> ();
    List <WebElement> elements = wd.findElements ( By.xpath ( "//tr[@name='entry']" ) );
    for (WebElement element : elements) {
      String name = element.findElements ( By.tagName ( "td" ) ).get ( 2 ).getText ();
      String lastname = element.findElements ( By.tagName ( "td" ) ).get ( 1 ).getText ();
      int id = Integer.parseInt ( element.findElement ( By.tagName ( "input" ) ).getAttribute ( "id" ) );
      ContactData contact = new ContactData ( id, name, lastname, null, null, null );
      contacts.add ( contact );
    }

    return contacts;
  }

}
