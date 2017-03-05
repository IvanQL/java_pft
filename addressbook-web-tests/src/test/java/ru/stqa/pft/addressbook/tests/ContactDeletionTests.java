package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db ().contacts().size () ==0) {
      app.goTo ().groupPage ();
      if (app.db ().groups ().size () ==0) {
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
    Contacts before = app.db ().contacts();
    ContactData deletedContact = before.iterator ().next ();
    app.contact ().delete ( deletedContact );
    assertThat ( app.contact ().count (), Matchers.equalTo ( before.size () - 1 ) );
    Contacts after = app.db ().contacts();
    assertThat ( after, equalTo ( before.without ( deletedContact ) ) );


  }


}

