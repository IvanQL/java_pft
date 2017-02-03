package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper ().gotoGroupPage ();
    if (!app.getGroupHelper ().isThereAGroup ()) {
      app.getGroupHelper ().createGroup ( new GroupData ( "test1", null, null ) );
    }
    app.getNavigationHelper ().gotoAddContactPage ();
    app.getContactHelper ().createContact ( new ContactData( "ivan", "bondar", "0981234567", "test@mail.com", "test1"), true );

  }


}
