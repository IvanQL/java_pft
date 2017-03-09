package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by Administrator on 10.03.2017.
 */
public class RegistrationTests extends TestBase {


  @Test
  public void testRegistration () {
    app.registration().start("user1", "user1@localhost.localdomain");



  }

}
