package ru.stqa.pft.mail.tests.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mail.tests.model.MailData;


public class MailSentTests2 extends TestBase {

    @Test
    public void testSentMail2 () {

        app.GoToSentPage ();
        app.getMailHelper ().initMailCreation ();
        app.getMailHelper ().sentMail ( new MailData ( "vendal9891@gmail.com", "TEST", "This is test mail" ) );
    }

    //public static boolean isAlertPresent(FirefoxDriver wd) {
       // try {
       //     wd.switchTo().alert();
        //    return true;
        //} catch (NoAlertPresentException e) {
        //    return false;
       // }
    //}
}
