package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper ().gotoHomePage ();
    if (! app.getContactHelper ().isThereAContact ()) {
      app.getNavigationHelper ().gotoAddContactPage ();
      app.getContactHelper ().createContact ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com", "test1" ), true );
      app.getNavigationHelper ().gotoHomePage ();
    }
    app.getContactHelper ().selectContact ();
    app.getContactHelper ().deleteSelectedContacts ();
    app.getContactHelper ().submitDeletionContacts ();

  }
}
