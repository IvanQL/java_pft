package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact ().homePage ();
    if (app.contact ().all ().size () == 0) {
      app.goTo ().groupPage ();
      if (app.group ().all ().size () == 0) {
        app.group ().create ( new GroupData ().withName ( "test1" ) );
      }
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ()
              .withName ( "ivan" ).withLastname ( "bondar" ).withTelephone ( "0981234567" ).withEmail ( "test@mail.com" ).withGroup ( "test1" ), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }

  @Test
  public void testContactDeletion() {
    Contacts before = app.contact ().all ();
    ContactData deletedContact = before.iterator ().next ();
    app.contact ().delete ( deletedContact );
    Contacts after = app.contact ().all ();
    assertEquals ( after.size (), before.size () - 1 );
    assertThat ( after, equalTo ( before.without ( deletedContact ) ) );


  }


}

