package ru.stqa.pft.mail.tests;

import org.testng.annotations.Test;


public class MailSentTests2 extends TestBase {

    @Test
    public void testSentMail2 () {

        GoToSentPage ();
        initMailCreation ();
        sentMail ( new MailData ( "vendal9891@gmail.com", "TEST", "This is test mail" ) );
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
