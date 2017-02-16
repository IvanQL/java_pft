package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Administrator on 16.02.2017.
 */
public class ContactPhoneTests extends TestBase {


  @Test
  public void testContactPhones () {
    app.goTo ().homePage ();
    ContactData contact = app.contact ().all ().iterator ().next ();
    ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm (contact);



  }

}
