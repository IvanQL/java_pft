package ru.stqa.pft.mantis.appmanager;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * Created by Administrator on 17.03.2017.
 */

  public class ChangePasswordHelper extends  HelperBase{

    private CloseableHttpClient httpclient;

    public ChangePasswordHelper(ApplicationManager app) {
      super (app);
    }


  public void login() {

    wd.get ( app.getProperty("web.baseUrl") +  "/login.php" );
    type ( By.name ( "username" ) , app.getProperty ( "web.AdminLogin" )  );
    type ( By.name ( "password" ), app.getProperty ( "web.AdminPassword" ) );
    click ( By.cssSelector ( "input[value='Войти'" ));

  }

  public void resetPassword (Users user) {

    wd.get (app.getProperty("web.baseUrl") + "/manage_user_edit_page.php?user_id= " + user.iterator().next().getId());

    //type ( By.cssSelector ( "input[value='Сбросить пароль']" ) );
    click (By.cssSelector("input[value='Сбросить пароль']"));


  }

 // private void type(By by) {
  //}

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click (By.cssSelector("input[value='Update User']"));
  }



}