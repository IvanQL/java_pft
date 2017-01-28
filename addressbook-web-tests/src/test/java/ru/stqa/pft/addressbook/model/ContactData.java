package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String lastname;
  private final String telephone;
  private final String email;

  public ContactData(String name, String lastname, String telephone, String email) {
    this.name = name;
    this.lastname = lastname;
    this.telephone = telephone;
    this.email = email;
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
}
