package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo ().groupPage ();
    if (!app.group ().isThereAGroup ()) {
      app.group ().create ( new GroupData ().withName ( "test1" ) );
    }
    app.contact ().homePage ();

  }

  @Test
  public void testContactCreation() {
    Contacts before = (Contacts) app.contact ().all ();
    app.goTo ().addContactPage ();
    ContactData contact = new ContactData ()
            .withName ( "ivan" ).withLastname ( "bondar" ).withTelephone ( "0981234567" ).withEmail ( "test@mail.com" )
            .withAddress ( "address q" );
    app.contact ().create ( contact, true );
    app.contact ().homePage ();
    assertThat ( app.contact ().count (), equalTo ( before.size () + 1 ) );
    Contacts after = (Contacts) app.contact ().all ();
    assertThat ( after, equalTo (
            before.withAdded ( contact.withId ( after.stream ().mapToInt ( (g) -> g.getId () ).max ().getAsInt () ) ) ) );

  }


}
