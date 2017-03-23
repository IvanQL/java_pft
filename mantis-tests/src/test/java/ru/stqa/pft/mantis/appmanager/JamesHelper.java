package ru.stqa.pft.mantis.appmanager;


import org.apache.commons.net.telnet.TelnetClient;
import ru.stqa.pft.mantis.model.MailMessage;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import javax.mail.*;
import java.net.SocketException;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;




/**
 * Created by Administrator on 23.03.2017.
 */
public class JamesHelper {
  private ApplicationManager app;
  private TelnetClient telnet;


  private String mailServer;
  private InputStream in;
  private PrintStream out;
  private Session mailSession;
  private Store store;

  public JamesHelper(ApplicationManager app) {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }


  public boolean doesUserExist(String name) {
    initTelnetSession();
    write("verify" + name);
    String result = readUntil("exist");
    closeTelnetSession();
    return result.trim().equals("User " + name + "exist");
  }

  public void createUser(String name, String password) {
    initTelnetSession();
    write("adduser " + name + " " + password);
    String result = readUntil("User " + name + "added");
    closeTelnetSession();
  }

  public void deleteUser(String name) {
    initTelnetSession();
    write("deluser" + name);
    String result = readUntil("User " + name + "deleted");
    closeTelnetSession();

  }

  private void closeTelnetSession() {
    write("quit");
  }

  private void initTelnetSession() {
    mailServer = app.getProperty("mailserver.host");
    int port = Integer.parseInt(app.getProperty("mailserver.port"));
    String login = app.getProperty("mailserver.adminLogin");
    String password = app.getProperty("mailserver.adminPassword");

    try {
      telnet.connect(mailServer, port);
      in = telnet.getInputStream();
      out = new PrintStream(telnet.getOutputStream());

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
//Don't know why it doesn't allow login at the first attempt
    readUntil("Login id:");
    write("");
    readUntil("Password:");
    write("");
//Second login atempt, must  be successfully
    readUntil("Login id:");
    write(login);
    readUntil("Password:");
    write(password);
// Resd welcom message
    readUntil("Welcom " + login + ". HELP for a list of commands");

  }

  private void write(String value) {
    try {
      out.println(value);
      out.flush();
      System.out.println(value);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String readUntil(String pattern) {
    try {
      char lastChar = pattern.charAt(pattern.length() - 1);
      StringBuffer sb = new StringBuffer();
      char ch = (char) in.read();
      while (true) {
        System.out.println(ch);
        sb.append(ch);
        if (ch == lastChar) {
          if (sb.toString().endsWith(pattern)) {
            return sb.toString();
          }
        }
        ch = (char) in.read();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<MailMessage> waitForMail(String username, String password, long timeout) throws InterruptedException, MessagingException {
    long now = System.currentTimeMillis();
    while (System.currentTimeMillis() < now + timeout) {
      List<MailMessage> allMail = getAllMail(username, password);
      if (allMail.size() > 0) {
        return allMail;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    throw new Error("No mail :((");

  }

  private List<MailMessage> getAllMail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username, password);
    List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect( Collectors.toList());
    closeFolder(inbox);
    return messages;
  }

  private void closeFolder(Folder folder) throws MessagingException {
    folder.close(true);
    store.close();
  }

  private Folder openInbox(String username, String password) throws MessagingException {
    store = mailSession.getStore("pop3");
    store.connect(mailServer, username, password);
    Folder folder = store.getDefaultFolder().getFolder("INBOX");
    folder.open(Folder.READ_WRITE);
    return folder;
  }

  public void drainEmail(String username, String password) throws MessagingException {
    Folder inbox = openInbox(username, password);
    for (Message message : inbox.getMessages()) {
      message.setFlag( Flags.Flag.DELETED, true);
    }
    closeFolder(inbox);
  }

  private static MailMessage toModelMail(Message m) {
    try {
      return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}