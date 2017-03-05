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
public class ContactModificationTests extends TestBase {





  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db ().contacts().size () ==0) {
      app.goTo ().groupPage ();
      if (app.db ().groups ().size () ==0) {
        app.group ().create ( new GroupData ().withName ( "test1" ) );
      }
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ()
              .withName ( String.format ( "name " ) )
              .withLastname ( String.format ( "lastname ") ).withHomePhone ( String.format ( "12345") )
              .withWorkPhone (String.format ( "12345")).withMobilePhone ( String.format ( "12345" ) )
              .withEmail ( String.format ( "email") ).withEmail2 (String.format ( "email2")  )
              .withEmail3 ( String.format ( "email3" ) ).withAddress ( String.format ( "address" )  ), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db ().contacts();
    ContactData modifiedContact = before.iterator ().next ();
    ContactData contact = new ContactData ()
            .withId ( modifiedContact.getId () ).withName ( String.format ( "name " ) )
            .withLastname ( String.format ( "lastname ") ).withHomePhone ( String.format ( "12345") )
            .withWorkPhone (String.format ( "12345")).withMobilePhone ( String.format ( "12345" ) )
            .withEmail ( String.format ( "email") ).withEmail2 (String.format ( "email2")  )
            .withEmail3 ( String.format ( "email3" ) ).withAddress ( String.format ( "address" )  );

    app.contact ().modify ( contact );
    assertThat ( app.contact ().count (), Matchers.equalTo ( before.size () ) );
    Contacts after = app.db ().contacts();
    assertThat ( after, equalTo ( before.without ( modifiedContact ).withAdded ( contact ) ) );


  }


}
