package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by Administrator on 28.01.2017.
 */
public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper ().gotoGroupPage ();
    app.getGroupHelper ().selectGroup ();
    app.getGroupHelper ().initGroupModification ();
    app.getGroupHelper ().fillGroupForm ( new GroupData ( "test1", "test2", "test3" ) );
    app.getGroupHelper ().submitGroupModification ();
    app.getGroupHelper ().returnToGroupPage ();

  }

}
