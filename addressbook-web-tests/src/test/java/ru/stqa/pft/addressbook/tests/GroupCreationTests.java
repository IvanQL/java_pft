package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator <Object[]> validGroups() throws IOException {
    List <Object[]> list = new ArrayList <Object[]> ();
    BufferedReader resder = new BufferedReader ( new FileReader  (new File ("src/test/resources/groups.csv") ) );
    String line = resder.readLine ();
    while (line != null) {
      String[] split = line.split ( ";" );
      list.add ( new Object[] {new GroupData( ).withName ( split[0] ).withHeader ( split[1] ).withFooter ( split[2] )});
      line = resder.readLine ();
    }
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
