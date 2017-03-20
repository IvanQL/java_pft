package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 10.03.2017.
 */
public class RegistrationTests extends TestBase {


  @BeforeMethod
  public void startMailServer() {
    app.mail ().start ();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis ();
    String email = String.format("user%s@localhost.localdomain",now);
    String user = String.format("user%s",now);
    String password = "password";
    app.registration ().start ( user, email );
    List <MailMessage> mailMessages = app.mail ().waitForMail ( 2, 10000 );
    String confirmationLink = findConfirmationLink ( mailMessages, email );
    app.registration ().finish (confirmationLink, password );
    Assert.assertTrue (app.newSession ().login (  user, password));



  }

  private String findConfirmationLink(List <MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream ().filter ( (m) -> m.to.equals ( email ) ).findFirst ().get ();
    VerbalExpression regex = VerbalExpression.regex ().find ( "http://" ).nonSpace ().oneOrMore ().build ();
    regex.getText ( mailMessage.text );
    return regex.getText ( mailMessage.text );

  }



  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail ().stop ();
  }

}
