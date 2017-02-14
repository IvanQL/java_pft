package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper ().gotoGroupPage ();
    if (!app.getGroupHelper ().isThereAGroup ()) {
      app.getGroupHelper ().createGroup ( new GroupData ( "test1", null, null ) );
    }
    app.getNavigationHelper ().gotoHomePage ();
    List <ContactData> before = app.getContactHelper ().getContactList ();
    app.getNavigationHelper ().gotoAddContactPage ();
    ContactData contact = new ContactData ( "ivan1", "bondar", "0981234567", "test@mail.com", "test1" );
    app.getContactHelper ().createContact ( contact, true );
    app.getNavigationHelper ().gotoHomePage ();
    List <ContactData> after = app.getContactHelper ().getContactList ();
    Assert.assertEquals ( after.size (), before.size () + 1 );

    before.add ( contact );
    Comparator <? super ContactData> byId = Comparator.comparingInt ( ContactData::getId );
    before.sort ( byId );
    after.sort ( byId );


  }


}
