package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectGroup(int index) {
    wd.findElements ( By.name ( "selected[]" ) ).get ( index ).click ();

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
  public void modify(int index, GroupData group) {
    selectGroup (index);
    initGroupModification ();
    fillGroupForm ( group );
    submitGroupModification ();
    returnToGroupPage ();
  }

  public void delete(int index) {
    selectGroup ( index );
    deleteSelectedGroups ();
    returnToGroupPage ();
  }


  public boolean isThereAGroup() {
    return isElementPresent ( By.name ( "selected[]" ) );
  }

  public int getGroupCount() {
    return wd.findElements ( By.name ( "selected[]" ) ).size ();
  }

  public List <GroupData> list() {
    List <GroupData> groups = new ArrayList <> ();
    List <WebElement> elements = wd.findElements ( By.cssSelector ( "span.group" ) );
    for (WebElement element : elements) {
      String name = element.getText ();
      int id = Integer.parseInt (element.findElement ( By.tagName ( "input" )).getAttribute ( "value" ));
      groups.add(new GroupData ( ).withId ( id).withName ( name ));
    }

    return groups;
  }
}
