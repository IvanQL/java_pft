package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

  public void selectGroup() {
    click ( By.name ( "selected[]" ) );
  }

  public void initGroupModification() {
    click ( By.name ( "edit" ) );
  }

  public void submitGroupModification() {
    click ( By.name ( "update" ) );
  }
}
