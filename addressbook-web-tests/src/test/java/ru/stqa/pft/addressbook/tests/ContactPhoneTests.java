package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Administrator on 16.02.2017.
 */
public class ContactPhoneTests extends TestBase {


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
              .withName ( "ivan" ).withLastname ( "bondar" ).withTelephone ( "0981234567" ).withEmail ( "test@mail.com" ), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }


  @Test
  public void testContactPhones() {
    app.goTo ().homePage ();
    ContactData contact = app.contact ().all1 ().iterator ().next ();
    ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm ( contact );

    assertThat ( contact.getAllPhones (), equalTo ( mergePhones ( contactInfoFromEditForm ) ) );

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList ( contact.getHomePhone (), contact.getMobilePhone (), contact.getWorkPhone () )
            .stream ().filter ( (s) -> !s.equals ( "" ) )
            .map ( ContactPhoneTests::cleaned )
            .collect ( Collectors.joining ( "\n" ) );

  }

  public static String cleaned(String phone) {
    return phone.replaceAll ( "\\s", "" ).replaceAll ( "[-()]", "" );


  }

}
