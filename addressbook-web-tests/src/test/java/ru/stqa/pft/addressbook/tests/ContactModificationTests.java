package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getContactHelper ().gotoHomePage ();
    if (!app.getContactHelper ().isThereAContact ()) {
      app.getNavigationHelper ().gotoGroupPage ();
      if (!app.getGroupHelper ().isThereAGroup ()) {

        app.getGroupHelper ().createGroup ( new GroupData ( "test1", null, null ) );
      }
      app.getNavigationHelper ().gotoAddContactPage ();
      app.getContactHelper ().createContact ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com", "test1" ), true );
      app.getContactHelper ().gotoHomePage ();
    }
    app.getContactHelper ().gotoHomePage ();
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.getContactHelper ().getContactList();
    ContactData contact = new ContactData ( before.get ( before.size () - 1).getId (),"ivan", "bondar", "0981234567", "test@mail.com", null );
    app.getContactHelper ().modifyContact ( before, contact );
    List<ContactData> after = app.getContactHelper ().getContactList();
    Assert.assertEquals (after.size (), before.size () );

    before.remove ( before.size () - 1 );
    before.add ( contact );
    Comparator <? super ContactData> byId = Comparator.comparingInt ( ContactData::getId );
    before.sort ( byId );
    after.sort ( byId );
    Assert.assertEquals(before, after);






  }


}
