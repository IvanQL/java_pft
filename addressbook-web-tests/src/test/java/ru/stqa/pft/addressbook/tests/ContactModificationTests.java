package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper ().gotoHomePage ();
    app.getContactHelper ().initContactModification ();
    app.getContactHelper ().fillContactForm ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com" ) );
    app.getContactHelper ().submitContactModification ();


  }
}