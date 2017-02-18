package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Administrator on 17.02.2017.
 */
public class ContactEmailTests extends TestBase {


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
  public void testContactEmail() {
    app.goTo ().homePage ();
    ContactData contact = app.contact ().all1 ().iterator ().next ();
    ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm ( contact );

    assertThat ( contact.getAllEmails (), equalTo ( mergeEmails ( contactInfoFromEditForm ) ) );

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList ( contact.getEmail1 (), contact.getEmail2 (), contact.getEmail3 () )
            .stream ().filter ( (s) -> !s.equals ( "" ) )
            .map(ContactEmailTests::cleaned)
            .collect ( Collectors.joining ( "\n" ) );
  }


  public static String cleaned (String email) {
    return email.replaceAll(" ","");
  }




}


