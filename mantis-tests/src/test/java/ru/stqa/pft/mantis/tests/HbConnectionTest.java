package ru.stqa.pft.mantis.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

/**
 * Created by Administrator on 04.03.2017.
 */
public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass

  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder ()
            .configure () // configures settings from hibernate.cfg.xml
            .build ();
    try {
      sessionFactory = new MetadataSources ( registry ).buildMetadata ().buildSessionFactory ();
    } catch (Exception e) {
      e.printStackTrace ();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy ( registry );
    }
  }


  @Test
  public void testHbConnection() {

    Session session = sessionFactory.openSession ();
    session.beginTransaction ();
    List <UserData> result = session.createQuery ( "from UserData" ).list ();

    session.getTransaction ().commit ();
    session.close ();

    for (UserData users : result) {
      System.out.println ( users );

    }




  }
}
