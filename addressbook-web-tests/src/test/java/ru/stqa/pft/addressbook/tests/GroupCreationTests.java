package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper ().gotoGroupPage ();
    List <GroupData> before = app.getGroupHelper ().getGroupList ();
    GroupData group = new GroupData ( "test3", null, null );
    app.getGroupHelper ().createGroup ( group );
    List <GroupData> after = app.getGroupHelper ().getGroupList ();
    Assert.assertEquals ( after.size (), before.size () + 1 );

    group.setId ( after.stream ().max ( Comparator.comparingInt ( GroupData::getId ) ).get ().getId () );
    before.add ( group );
    Assert.assertEquals ( new HashSet <Object> ( before ), new HashSet <Object> ( after ) );

  }

}
