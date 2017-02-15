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
    app.contact ().homePage ();
    if (app.contact (). list().size () == 0) {
      app.goTo ().groupPage ();
      if (app.group (). list().size () == 0) {
        app.group ().create ( new GroupData ( "test1", null, null ) );
      }
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com", "test1" ), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact ().list ();
    int index = before.size () - 1;
    ContactData contact = new ContactData ( before.get ( before.size () - 1).getId (),"ivan", "bondar", "0981234567", "test@mail.com", null );
    app.contact ().modifyContact ( index, contact );
    List<ContactData> after = app.contact ().list ();
    Assert.assertEquals (after.size (), before.size () );

    before.remove ( index );
    before.add ( contact );
    Comparator <? super ContactData> byId = Comparator.comparingInt ( ContactData::getId );
    before.sort ( byId );
    after.sort ( byId );
    Assert.assertEquals(before, after);






  }


}
