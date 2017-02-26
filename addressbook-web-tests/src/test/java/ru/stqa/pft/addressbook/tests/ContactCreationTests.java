package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {


  @DataProvider
  public Iterator <Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader ( new FileReader ( new File ( "src/test/resources/contacts.json" ) ) )) {

      String json = "";
      String line = reader.readLine ();
      while (line != null) {
        json += line;
        line = reader.readLine ();
      }
      Gson gson = new Gson ();
      List <ContactData> contacts = gson.fromJson ( json, new TypeToken <List <ContactData>> () {
      }.getType () );
      return contacts.stream ().map ( (g) -> new Object[]{g} ).collect ( Collectors.toList () ).iterator ();
    }


  }

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo ().groupPage ();
    if (!app.group ().isThereAGroup ()) {
      app.group ().create ( new GroupData ().withName ( "test1" ) );
    }
    app.contact ().homePage ();

  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact) {
    Contacts before = (Contacts) app.contact ().all ();
    app.goTo ().addContactPage ();
    File photo = new File ( "src/test/resources/stru.png" );
    app.contact ().create ( contact.withPhoto ( photo ), true );
    app.contact ().homePage ();
    assertThat ( app.contact ().count (), equalTo ( before.size () + 1 ) );
    Contacts after = (Contacts) app.contact ().all ();
    assertThat ( after, equalTo (
            before.withAdded ( contact.withId ( after.stream ().mapToInt ( (g) -> g.getId () ).max ().getAsInt () ) ) ) );

  }


}
