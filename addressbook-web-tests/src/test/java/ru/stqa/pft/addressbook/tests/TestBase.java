package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.omg.CORBA.Object;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Administrator on 28.01.2017.
 */
public class TestBase {

  Logger logger = LoggerFactory.getLogger ( TestBase.class );

  protected static final ApplicationManager app
          = new ApplicationManager ( System.getProperty ( "browser", BrowserType.CHROME ));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init ();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    app.stop ();
  }

  @BeforeMethod
  public void logTestStart(Method m, java.lang.Object[] p){
    logger.info("Start test "+m.getName()+"with parameters "+ Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test "+m.getName());
  }


  public void verifyGroupListInUi() {
    if (Boolean.getBoolean ( "verifyUI" )) {

      Groups dbGroups = app.db ().groups ();
      Groups uiGroups = app.group ().all ();
      assertThat ( uiGroups, equalTo ( dbGroups.stream ().map (( g ) -> new GroupData ().withId (g.getId ()  ).withName ( g.getName () )  )
              .collect ( Collectors.toSet () ) ));
    }



  }

  public void verifyContactListInUi() {
    if (Boolean.getBoolean ( "verifyUI" )) {

      Contacts dbContacts = app.db ().contacts ();
      Contacts uiContacts = app.contact ().all ();

      assertThat ( uiContacts, equalTo ( dbContacts.stream ().map (( g ) -> new ContactData ().withId (g.getId ()  ).withLastname ( g.getLastname () )
              .withName ( g.getName () )).collect ( Collectors.toSet () ) ));
    }


  }

}
