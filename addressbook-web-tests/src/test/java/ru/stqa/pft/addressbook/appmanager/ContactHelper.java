package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    type ( By.name ( "email2" ), contactData.getEmail2 () );
    type ( By.name ( "email3" ), contactData.getEmail3 () );
    type ( By.name ( "address" ), contactData.getAddress () );
    type ( By.name ( "work" ), contactData.getWorkPhone () );
    type ( By.name ( "home" ), contactData.getHomePhone () );
    type ( By.name ( "mobile" ), contactData.getMobilePhone () );
    //attach ( By.name ( "photo" ), contactData.getPhoto () );


 if (creation) {
   if (contactData.getGroups ().size () > 0) {
     Assert.assertTrue (contactData.getGroups ().size() == 1);
     new Select ( wd.findElement ( By.name ( "new_group" ) ) ).selectByVisibleText ( contactData.getGroups ().iterator ().next ().getName () );
   }

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

    //wd.findElement ( By.cssSelector ( String.format ( "a[href='edit.php?id=%s']", id ) ) ).click ();
    //wd.findElement (By.xpath (String.format ("//input[value='%s']/../../td[8]/a",id))).click();
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List <WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
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

  public void initFullInfoContactById(int id) {
    wd.findElement ( By.cssSelector ( String.format ( "a[href='view.php?id=%s']", id ) ) ).click ();
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

  public Set <ContactData> all1() {
    Set <ContactData> contacts = new HashSet <ContactData> ();
    List <WebElement> rows = wd.findElements ( By.name ( "entry" ) );
    for (WebElement row : rows) {
      List <WebElement> cells = row.findElements ( By.tagName ( "td" ) );
      int id = Integer.parseInt ( cells.get ( 0 ).findElement ( By.tagName ( "input" ) ).getAttribute ( "value" ) );
      String lastname = cells.get ( 1 ).getText ();
      String name = cells.get ( 2 ).getText ();
      String allPhones = cells.get ( 5 ).getText ();
      String allEmails = cells.get ( 4 ).getText ();
      String address = cells.get ( 3 ).getText ();
      contacts.add ( new ContactData ().withId ( id ).withName ( name ).withLastname ( lastname )
              .withAllPhones ( allPhones ).withAllEmails ( allEmails ).withAddress ( address ) );
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById ( contact.getId () );
    String name = wd.findElement ( By.name ( "firstname" ) ).getAttribute ( "value" );
    String lastname = wd.findElement ( By.name ( "lastname" ) ).getAttribute ( "value" );
    String home = wd.findElement ( By.name ( "home" ) ).getAttribute ( "value" );
    String mobile = wd.findElement ( By.name ( "mobile" ) ).getAttribute ( "value" );
    String work = wd.findElement ( By.name ( "work" ) ).getAttribute ( "value" );
    String email = wd.findElement ( By.name ( "email" ) ).getAttribute ( "value" );
    String email2 = wd.findElement ( By.name ( "email2" ) ).getAttribute ( "value" );
    String email3 = wd.findElement ( By.name ( "email3" ) ).getAttribute ( "value" );
    String address = wd.findElement ( By.name ( "address" ) ).getAttribute ( "value" );
    wd.navigate ().back ();
    return new ContactData ().withId ( contact.getId () ).withName ( name ).withLastname ( lastname )
            .withHomePhone ( home ).withMobilePhone ( mobile )
            .withWorkPhone ( work ).withEmail ( email ).withEmail2 ( email2 ).withEmail3 ( email3 ).withAddress ( address );

  }


  public ContactData infoFromFullInfoForm(ContactData contact) {
    initFullInfoContactById ( contact.getId () );
    String allInfo = wd.findElement ( By.id ( "content" ) ).getText ();

    return new ContactData ().withId ( contact.getId () ).withFullInfo ( allInfo );
  }

  public void selectJoinContactById(int id) {

    wd.findElement(By.cssSelector("input[id ='" + id + "']")).click();
  }
  public void addingInGroupById(int id) {
    click(By.cssSelector("select[name='to_group']"));
    click(By.cssSelector(".right>select>option[value='" + id + "']"));
    click(By.name("add"));
  }

}
