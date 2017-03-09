package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Administrator on 09.03.2017.
 */
public class ContactDeletionFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {


    Groups groups = app.db ().groups();

    if (app.db ().contacts().size () ==0) {
      app.goTo ().groupPage ();
      if (app.db ().groups ().size () ==0) {
        app.group ().create ( new GroupData ().withName ( "test1" ) );
      }
      app.goTo ().addContactPage ();
      app.contact ().create ( new ContactData ()
              .withName ( "ivan" ).withLastname ( "bondar" ).withTelephone ( "0981234567" ).withEmail ( "test@mail.com" )
              .inGroup(groups.iterator ().next ()), true );

      ContactData contact = app.db().contacts().iterator().next();
      GroupData group = app.db().groups().iterator().next();
      app.goTo().homePage();
      app.contact().selectJoinContactById(contact.getId());
      app.contact().addingInGroupById(group.getId());
      app.goTo().pageSelectedGroup(group.getId());

    }

  }

  @Test
  public void testDelFromGroup() {
    Contacts contacts = app.db().contacts();
    Iterator<ContactData> iteratorContacts = contacts.iterator();
    ContactData contact = iteratorContacts.next();
    GroupData group = contact.getGroups().iterator().next();

    app.goTo().homePage();

    while (iteratorContacts.hasNext()) {
      if (contact.getGroups().size() > 0) {
        group = contact.getGroups().iterator().next();
        app.contact().filterGroupsById(group.getId());
        break;
      } else {
        contact = iteratorContacts.next();
      }
    }

    app.contact().selectJoinContactById(contact.getId());

    app.contact().removeFromGroup();
    app.goTo().pageSelectedGroup(group.getId());

    Groups groupsContactAfter = app.db().contactById(contact.getId()).iterator().next().getGroups();

    assertThat(groupsContactAfter, equalTo(
            contact.getGroups().without(group)));
  }




}
