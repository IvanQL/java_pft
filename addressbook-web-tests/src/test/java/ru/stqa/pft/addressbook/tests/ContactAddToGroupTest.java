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
public class ContactAddToGroupTest extends TestBase{


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
              .withName ( "ivan" ).withLastname ( "bondar" ).withTelephone ( "0981234567" ).withEmail ( "test@mail.com" ).inGroup(groups.iterator ().next ()), true );
      app.contact ().homePage ();
    }
    app.contact ().homePage ();
  }


  @Test
  public void testContactAddToGroup() {
    Groups groupsBefore = app.db().groups();
    Contacts contactsBefore = app.db().contacts();

    ContactData selectedContact = contactsBefore.iterator().next();
    Groups groupsSelectedContact = selectedContact.getGroups();

    GroupData selectedGroup;
    Iterator<ContactData> iteratorContacts = contactsBefore.iterator();

    while (iteratorContacts.hasNext()) {
      if (groupsSelectedContact.equals(groupsBefore)) {
        selectedContact = iteratorContacts.next();
        groupsSelectedContact = selectedContact.getGroups();
      } else {
        break;
      }
    }
    if (groupsSelectedContact.equals(groupsBefore)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 0"));
    }
    groupsBefore = app.db().groups();
    groupsSelectedContact = selectedContact.getGroups();
    groupsBefore.removeAll(groupsSelectedContact);

    if (groupsBefore.size() > 0) {
      selectedGroup = groupsBefore.iterator().next();
    } else {
      throw new RuntimeException("no groups");
    }

    app.goTo().homePage ();
    app.contact().selectJoinContactById(selectedContact.getId());

    app.contact().addingInGroupById(selectedGroup.getId());
    app.goTo().pageSelectedGroup(selectedGroup.getId());

    ContactData contactAfter = app.db().contactById(selectedContact.getId()).iterator().next();
    Groups groupsContactAfter = contactAfter.getGroups();

    assertThat(groupsContactAfter, equalTo(
            groupsSelectedContact.withAdded(selectedGroup)));
  }
}











