package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo ().groupPage ();
    if (!app.group ().isThereAGroup ()) {
      app.group ().create ( new GroupData ( ).withName ( "test1" ) );
    }
    app.contact ().homePage ();

  }

  @Test
  public void testContactCreation() {
    List <ContactData> before = app.contact ().list ();
    app.goTo ().addContactPage ();
    ContactData contact = new ContactData ( "ivan1", "bondar", "0981234567", "test@mail.com", "test1" );
    app.contact ().create ( contact, true );
    app.contact ().homePage ();
    List <ContactData> after = app.contact ().list ();
    Assert.assertEquals ( after.size (), before.size () + 1 );

    before.add ( contact );
    Comparator <? super ContactData> byId = Comparator.comparingInt ( ContactData::getId );
    before.sort ( byId );
    after.sort ( byId );


  }


}
