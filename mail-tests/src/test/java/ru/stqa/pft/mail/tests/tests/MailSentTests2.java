package ru.stqa.pft.mail.tests.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mail.tests.model.MailData;


public class MailSentTests2 extends TestBase {

  @Test
  public void testSentMail2() {

    // Сделал для сайта  "http://www.i.ua/" , так как из-за не работающего на выходных VPN не смог норм авторизоваться на меил.ру

    // Проверку добавить не успел, но опишу как я вижу ее реализацию.
    // Вот тут мы получаем колличество писем, которые есть в папке Отправлено до начала теста

    app.GoToSentPage ();
    app.getMailHelper ().initMailCreation ();
    app.getMailHelper ().sentMail ( new MailData ( "vendal9891@gmail.com", "TEST", "This is test mail" ) );

    // Вот тут нужно добавить проверку assertThat ( колличество писем после отправки, equalTo ( before.size () + 1 ) );


  }


}
