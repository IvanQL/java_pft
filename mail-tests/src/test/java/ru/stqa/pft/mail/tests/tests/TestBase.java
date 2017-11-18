package ru.stqa.pft.mail.tests.tests;

        import org.testng.annotations.AfterMethod;
        import org.testng.annotations.BeforeMethod;
        import ru.stqa.pft.mail.tests.appmanager.ApplicationManager;


/**
 * Created by Administrator on 28.01.2017.
 */
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager ();

  @BeforeMethod
  public void setUp() throws Exception {
    app.init ();
  }

  @AfterMethod
  public void tearDown() {
    app.stop ();
  }

  public ApplicationManager getApp() {
    return app;
  }


  //protected static final ApplicationManager app
         // = new ApplicationManager ( System.getProperty ( "browser", BrowserType.CHROME ));


}
