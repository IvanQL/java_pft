package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {

    gotoAddContactPage ();
    fillContactForm ( new ContactData ( "ivan", "bondar", "0981234567", "test@mail.com" ) );
    enterNewContact ();
  }


}
