package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Administrator on 28.01.2017.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {
    click ( By.linkText ( "groups" ) );
  }

  public void gotoAddContactPage() {
    wd.findElement ( By.linkText ( "add new" ) ).click ();
  }
}
