package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Administrator on 18.02.2017.
 */
public class ContactAddressTests extends TestBase {

  @Test
  public void testContactEmail() {
    app.goTo ().homePage ();
    ContactData contact = app.contact ().all1 ().iterator ().next ();
    ContactData contactInfoFromEditForm = app.contact ().infoFromEditForm ( contact );

    assertThat ( contact.getAddress (), equalTo ( mergeAddress ( contactInfoFromEditForm ) ) );

  }

  private String mergeAddress(ContactData contact) {
    return Arrays.asList ( contact.getAddress () )
            .stream ().filter ( (s) -> !s.equals ( "" ) )
            .collect ( Collectors.joining ( "\n" ) );
  }
}
