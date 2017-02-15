package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

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
    Set<ContactData> before = app.contact ().all ();
    app.goTo ().addContactPage ();
    ContactData contact = new ContactData ()
            .withName ( "ivan" ).withLastname ( "bondar").withTelephone ( "0981234567" ).withEmail ( "test@mail.com" ).withGroup ( "test1" );
    app.contact ().create ( contact, true );
    app.contact ().homePage ();
    Set <ContactData> after = app.contact ().all ();
    Assert.assertEquals ( after.size (), before.size () + 1 );


    contact.withId ( after.stream ().mapToInt ((g ) -> g.getId ()).max ().getAsInt() );
    before.add ( contact );
    Assert.assertEquals ( before, after );

  }


}
