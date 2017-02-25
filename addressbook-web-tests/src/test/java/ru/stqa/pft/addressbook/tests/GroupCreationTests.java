package ru.stqa.pft.addressbook.tests;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator <Object[]> validGroupsFromXml() throws IOException {
    BufferedReader resder = new BufferedReader ( new FileReader ( new File ( "src/test/resources/groups.xml" ) ) );
    String xml = "";
    String line = resder.readLine ();
    while (line != null) {
      xml += line;
      line = resder.readLine ();
    }
    XStream xstream = new XStream ();
    xstream.processAnnotations ( GroupData.class );
    List <GroupData> groups = (List <GroupData>) xstream.fromXML ( xml );
    return groups.stream ().map ( (g) -> new Object[]{g} ).collect ( Collectors.toList () ).iterator ();

  }

  @DataProvider
  public Iterator <Object[]> validGroupsFromJson() throws IOException {
    BufferedReader resder = new BufferedReader ( new FileReader ( new File ( "src/test/resources/groups.json" ) ) );
    String json = "";
    String line = resder.readLine ();
    while (line != null) {
      json += line;
      line = resder.readLine ();
    }
    Gson gson = new Gson ();
    List <GroupData> groups = gson.fromJson ( json, new TypeToken <List <GroupData>> () {
    }.getType () );
    return groups.stream ().map ( (g) -> new Object[]{g} ).collect ( Collectors.toList () ).iterator ();

  }


  @Test(dataProvider = "validGroupsFromJson")
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
