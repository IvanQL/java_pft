package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.contact ().homePage ();
    if (app.contact (). list().size () == 0) {
      app.goTo ().groupPage ();
      if (app.group (). all().size () == 0) {
        app.group ().create ( new GroupData ().withName ( "test1" ) );
      }
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ()
              .withName ( "ivan" ).withLastname ( "bondar").withTelephone ( "0981234567" ).withEmail ( "test@mail.com" ).withGroup ( "test1" ), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }

  @Test
  public void testContactDeletion() {
    List <ContactData> before = app.contact ().list ();
    int index = before.size () - 1;
    app.contact ().delete ( index );
    List <ContactData> after = app.contact ().list ();
    Assert.assertEquals ( after.size (), before.size () - 1 );

    before.remove ( index );
    Assert.assertEquals ( before, after );

  }


}

