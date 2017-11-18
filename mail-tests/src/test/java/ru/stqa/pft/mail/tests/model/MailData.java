package ru.stqa.pft.mail.tests.model;

public class MailData {
  private final String to;
  private final String title;
  private final String text;

  public MailData(String to, String title, String text) {
    this.to = to;
    this.title = title;
    this.text = text;
  }

  public String getTo() {
    return to;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }
}
