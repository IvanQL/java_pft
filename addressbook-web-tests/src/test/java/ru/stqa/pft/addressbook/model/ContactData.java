package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String id;
  private final String name;
  private final String lastname;
  private final String telephone;
  private final String email;
  private String group;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass () != o.getClass ()) return false;

    ContactData that = (ContactData) o;

    if (id != null ? !id.equals ( that.id ) : that.id != null) return false;
    if (name != null ? !name.equals ( that.name ) : that.name != null) return false;
    return lastname != null ? lastname.equals ( that.lastname ) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode () : 0;
    result = 31 * result + (name != null ? name.hashCode () : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode () : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  public ContactData(String id, String name, String lastname, String telephone, String email, String group) {
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.telephone = telephone;
    this.email = email;
    this.group = group;
  }

  public ContactData( String name, String lastname, String telephone, String email, String group) {
    this.id = null;
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

  public String getId() {
    return id;
  }

}
