package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper ().gotoHomePage ();
    if (! app.getContactHelper ().isThereAContact ()) {
      app.getNavigationHelper ().gotoGroupPage ();
      if (!app.getGroupHelper ().isThereAGroup ()) {

        app.getGroupHelper ().createGroup ( new GroupData ( "test1", null, null ) );
      }

      app.getNavigationHelper ().gotoAddContactPage ();
      app.getContactHelper ().createContact ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com", "test1" ), true );
      app.getNavigationHelper ().gotoHomePage ();
    }
    app.getNavigationHelper ().gotoHomePage ();
    int before = app.getContactHelper ().getContactCount();
    app.getContactHelper ().selectContact (before - 1);
    app.getContactHelper ().deleteSelectedContacts ();
    app.getContactHelper ().submitDeletionContacts ();
    app.getNavigationHelper ().gotoHomePage ();
    int after = app.getContactHelper ().getContactCount();
    Assert.assertEquals (after, before - 1);

  }
}
