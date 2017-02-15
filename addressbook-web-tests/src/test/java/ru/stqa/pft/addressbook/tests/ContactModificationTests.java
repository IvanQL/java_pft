package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.Set;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact ().homePage ();
    if (app.contact (). all().size () == 0) {
      app.goTo ().groupPage ();
      if (app.group (). all().size () == 0) {
        app.group ().create ( new GroupData (  ).withName ( "test1" ));
      }
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ()
              .withName ( "ivan" ).withLastname ( "bondar").withTelephone ( "0981234567" ).withEmail ( "test@mail.com" ).withGroup ( "test1" ), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact ().all ();
    ContactData modifiedContact = before.iterator ().next ();
    ContactData contact = new ContactData ()
            .withId ( modifiedContact.getId ()).withName ( "ivan" ).withLastname ( "bondar").withTelephone ( "0981234567" ).withEmail ( "test@mail.com" );
    app.contact ().modify (contact);
    Set<ContactData> after = app.contact ().all ();
    Assert.assertEquals (after.size (), before.size () );

    before.remove ( modifiedContact );
    before.add ( contact );
    Assert.assertEquals(before, after);






  }


}
