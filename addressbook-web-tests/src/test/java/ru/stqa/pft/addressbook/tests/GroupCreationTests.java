package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator <Object[]> validGroups() {
    List <Object[]> list = new ArrayList <Object[]> ();
    list.add ( new Object[]{new GroupData ().withName ( "test1" ).withHeader ( "header 1" ).withFooter ( "footer 1" )} );
    list.add ( new Object[]{new GroupData ().withName ( "test2" ).withHeader ( "header 2" ).withFooter ( "footer 2" )} );
    list.add ( new Object[]{new GroupData ().withName ( "test3" ).withHeader ( "header 3" ).withFooter ( "footer 3" )} );
    return list.iterator ();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
    app.goTo ().groupPage ();
    Groups before = (Groups) app.group ().all ();
    app.group ().create ( group );
    assertThat ( app.group ().count (), equalTo ( before.size () + 1 ) );
    Groups after = (Groups) app.group ().all ();
    assertThat ( after, equalTo (
            before.withAdded ( group.withId ( after.stream ().mapToInt ( (g) -> g.getId () ).max ().getAsInt () ) ) ) );

  }

  @Test
  public void testBadGroupCreation() {

    app.goTo ().groupPage ();
    Groups before = (Groups) app.group ().all ();
    GroupData group = new GroupData ().withName ( "test4'" );
    app.group ().create ( group );
    assertThat ( app.group ().count (), equalTo ( before.size () ) );
    Groups after = (Groups) app.group ().all ();

    assertThat ( after, equalTo ( before ) );

  }

}
