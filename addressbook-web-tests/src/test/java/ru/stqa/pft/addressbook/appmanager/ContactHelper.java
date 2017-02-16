package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super ( wd );
  }

  public void homePage() {
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

  public void modify(ContactData contact) {
    initContactModificationById ( contact.getId () );
    fillContactForm ( contact, false );
    submitContactModification ();
    contactCashe = null;
    homePage ();
  }

  public void delete(ContactData сontact) {
    selectContactById ( сontact.getId () );
    deleteSelectedContacts ();
    submitDeletionContacts ();
    contactCashe = null;
    homePage ();
  }


  public void initContactModificationById(int id) {
    wd.findElement ( By.xpath ( "//table[@id='maintable']/tbody/tr['" + id + "']/td[8]/a/img" ) ).click ();
  }

  public void submitContactModification() {
    click ( By.name ( "update" ) );
  }


  public void selectContactById(int id) {
    wd.findElement ( By.cssSelector ( "input[value='" + id + "']" ) ).click ();

  }

  public void deleteSelectedContacts() {
    click ( By.xpath ( "//div[@id='content']/form[2]/div[2]/input" ) );
  }

  public void submitDeletionContacts() {
    wd.switchTo ().alert ().accept ();

  }

  public void create(ContactData contact, boolean b) {

    fillContactForm ( (contact), true );
    enterNewContact ();
    contactCashe = null;
  }

  public boolean isThereAContact() {
    return isElementPresent ( By.name ( "selected[]" ) );
  }

  public int count() {
    return wd.findElements ( By.name ( "selected[]" ) ).size ();

  }

  private Contacts contactCashe = null;


  public Contacts all() {
    if (contactCashe != null) {
      return new Contacts ( contactCashe );
    }

    contactCashe = new Contacts ();
    List <WebElement> elements = wd.findElements ( By.xpath ( "//tr[@name='entry']" ) );
    for (WebElement element : elements) {
      String name = element.findElements ( By.tagName ( "td" ) ).get ( 2 ).getText ();
      String lastname = element.findElements ( By.tagName ( "td" ) ).get ( 1 ).getText ();
      int id = Integer.parseInt ( element.findElement ( By.tagName ( "input" ) ).getAttribute ( "id" ) );
      contactCashe.add ( new ContactData ().withId ( id ).withName ( name ).withLastname ( lastname ) );
    }

    return new Contacts ( contactCashe );
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById ( contact.getId () );
    String name = wd.findElement ( By.name ( "firstname" )).getAttribute ("value");
    String lastname = wd.findElement ( By.name ( "lastname" )).getAttribute ("value");
    String home = wd.findElement ( By.name ( "home" )).getAttribute ("value");
    String mobile = wd.findElement ( By.name ( "mobile" )).getAttribute ("value");
    String work = wd.findElement ( By.name ( "work" )).getAttribute ("value");
    wd.navigate ().back ();
    return new ContactData ().withId ( contact.getId () ).withName (name).withLastname(lastname)
            .withHomePhone (home).withMobilePhone (mobile).withWorkPhone (work);

  }
}
