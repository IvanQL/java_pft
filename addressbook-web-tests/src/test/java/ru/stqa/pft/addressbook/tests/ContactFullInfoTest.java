package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Administrator on 18.02.2017.
 */
public class ContactFullInfoTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.contact ().homePage ();
    File photo = new File ( "src/test/resources/stru.png" );
    if (app.contact ().all ().size () == 0) {
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ()
              .withName ( "ivan" ).withLastname ( "bondar" ).withHomePhone ( "0981234567" )
              .withAddress ( "address q" ).withEmail ( "12345" ).withWorkPhone ( "2323" ).withMobilePhone ( "ewrwr" ).withPhoto ( photo ), true );
      app.contact ().homePage ();
    }

  }

  @Test
  public void testContactFullInfo() {

    app.goTo ().homePage ();
    ContactData contact = app.contact ().all ().iterator ().next ();
    ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm ( contact );
    ContactData contactInfoFromFullInfoForm = app.contact ().infoFromFullInfoForm ( contact );

    ContactData newFullInfo = new ContactData ().withId ( contact.getId () ).
            withFullInfo ( mergeNames ( contactInfoFromEditForm ) + "\n" + "\n" +
                    contactInfoFromEditForm.getAddress () + "\n" + "\n" +
                    mergePhones ( contactInfoFromEditForm ) + "\n" + "\n" +
                    mergeEmails ( contactInfoFromEditForm ) );

    assertThat ( contactInfoFromFullInfoForm.getFullInfo (), equalTo ( newFullInfo.getFullInfo () ) );
  }

  private String mergeNames(ContactData contactInfoFromEditForm) {
    return Arrays.asList ( contactInfoFromEditForm.getName (),
            contactInfoFromEditForm.getLastname () )
            .stream ().filter ( (s) -> !s.equals ( "" ) )
            .collect ( Collectors.joining ( " " ) );
  }

  private String mergeEmails(ContactData contactInfoFromEditForm) {
    return Arrays.asList ( contactInfoFromEditForm.getEmail (), contactInfoFromEditForm.getEmail2 (), contactInfoFromEditForm.getEmail3 () )
            .stream ().filter ( (s) -> !s.equals ( "" ) )
            .map ( ContactFullInfoTest::cleaned )
            .collect ( Collectors.joining ( "\n" ) );

  }

  public static String cleaned(String email) {
    return email.replaceAll ( " ", "" );
  }

  public String mergePhones(ContactData contactInfoFromEditForm) {
    String home = "";
    if ((!contactInfoFromEditForm.getHomePhone  ().equals(""))) {
      home = "H: " + cleanedPhone ( contactInfoFromEditForm.getHomePhone () );
    }
    String mobile = "";
     if ((!contactInfoFromEditForm.getMobilePhone ().equals (""))) {
       mobile = "M: " + cleanedPhone ( contactInfoFromEditForm.getMobilePhone () );

    }
    String work = "";

    if ((!contactInfoFromEditForm.getWorkPhone ().equals (""))) {
      work = "W: " + cleanedPhone ( contactInfoFromEditForm.getWorkPhone () );
    }

    return Arrays.asList ( home, mobile, work )
            .stream ().filter ( (s) -> !s.equals ( "" ) )
            .collect ( Collectors.joining ( "\n" ) );
  }

  public static String cleanedPhone(String phone) {
    return phone.replaceAll ( "\\s", "" ).replaceAll ( "[-()]", "" ).replaceAll("\\s", "") ;

  }

}
