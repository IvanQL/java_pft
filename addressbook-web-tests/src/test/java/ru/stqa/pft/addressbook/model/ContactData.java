package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String telephone;
  private final String email;
  private String group;

  public ContactData(String name, String lastname, String telephone, String email, String group) {
    this.name = name;
    this.lastname = lastname;
    this.telephone = telephone;
    this.email = email;
    this.group = group;
  }

  public String getName() {
    return name;
  }

  public String getLastname() {
    return lastname;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
