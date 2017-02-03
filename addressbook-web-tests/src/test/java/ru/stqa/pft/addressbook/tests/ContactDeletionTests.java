package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Administrator on 28.01.2017.
 */
public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {

    app.getNavigationHelper ().gotoHomePage ();
    app.getContactHelper ().selectContact ();
    app.getContactHelper ().deleteSelectedContacts ();
    app.getContactHelper ().submitDeletionContacts ();

  }
}
