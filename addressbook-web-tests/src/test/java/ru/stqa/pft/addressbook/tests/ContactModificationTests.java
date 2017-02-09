package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper ().gotoHomePage ();
    if (!app.getContactHelper ().isThereAContact ()) {
      app.getNavigationHelper ().gotoGroupPage ();
      if (!app.getGroupHelper ().isThereAGroup ()) {

        app.getGroupHelper ().createGroup ( new GroupData ( "test1", null, null ) );
      }
      app.getNavigationHelper ().gotoAddContactPage ();
      app.getContactHelper ().createContact ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com", "test1" ), true );
      app.getNavigationHelper ().gotoHomePage ();
    }
    app.getNavigationHelper ().gotoHomePage ();
    List<ContactData> before = app.getContactHelper ().getContactList();
    app.getContactHelper ().initContactModification (before.size () - 1);
    app.getContactHelper ().fillContactForm ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com", null ), false );
    app.getContactHelper ().submitContactModification ();
    app.getNavigationHelper ().gotoHomePage ();
    List<ContactData> after = app.getContactHelper ().getContactList();
    Assert.assertEquals (after.size (), before.size () );


  }
}
