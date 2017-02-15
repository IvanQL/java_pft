package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 28.01.2017.
 */
public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super ( wd );

  }

  public void returnToGroupPage() {
    click ( By.linkText ( "group page" ) );
  }

  public void submitGroupCreation() {
    click ( By.name ( "submit" ) );
  }

  public void fillGroupForm(GroupData groupData) {
    type ( By.name ( "group_name" ), groupData.getName () );
    type ( By.name ( "group_header" ), groupData.getHeader () );
    type ( By.name ( "group_footer" ), groupData.getFooter () );
  }

  public void initGroupCreation() {
    click ( By.name ( "new" ) );
  }

  public void deleteSelectedGroups() {
    click ( By.xpath ( "//div[@id='content']/form/input[5]" ) );
  }


  public void selectGroupById(int id) {
    wd.findElement (By.cssSelector ( "input[value='" + id + "']" ) ).click ();

  }

  public void initGroupModification() {
    click ( By.name ( "edit" ) );
  }

  public void submitGroupModification() {
    click ( By.name ( "update" ) );
  }

  public void create(GroupData group) {

    initGroupCreation ();
    fillGroupForm ( group );
    submitGroupCreation ();
    returnToGroupPage ();
  }
  public void modify( GroupData group) {
    selectGroupById (group.getId ());
    initGroupModification ();
    fillGroupForm ( group );
    submitGroupModification ();
    returnToGroupPage ();
  }


  public void delete(GroupData group) {
    selectGroupById( group.getId () );
    deleteSelectedGroups ();
    returnToGroupPage ();
  }

  public boolean isThereAGroup() {
    return isElementPresent ( By.name ( "selected[]" ) );
  }

  public int getGroupCount() {
    return wd.findElements ( By.name ( "selected[]" ) ).size ();
  }



  public Set <GroupData> all() {
    Set<GroupData> groups = new HashSet<GroupData> ();
    List <WebElement> elements = wd.findElements ( By.cssSelector ( "span.group" ) );
    for (WebElement element : elements) {
      String name = element.getText ();
      int id = Integer.parseInt (element.findElement ( By.tagName ( "input" )).getAttribute ( "value" ));
      groups.add(new GroupData ( ).withId ( id).withName ( name ));
    }

    return groups;
  }


}
