package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    app.getNavigationHelper ().gotoAddContactPage ();
    app.getContactHelper ().createContact ( new ContactData( "ivan", "bondar", "0981234567", "test@mail.com", "test1"), true );

  }


}
