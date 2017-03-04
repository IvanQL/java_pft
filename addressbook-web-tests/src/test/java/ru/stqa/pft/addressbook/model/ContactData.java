package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
@Entity
@Table (name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String name;


  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Transient
  private String telephone;

  @Expose
  @Transient
  @Column(name = "email")
  @Type ( type = "text")
  private String email;

  @Transient
  private String group;

  @Column(name = "home")
  @Type( type = "text")
  private String homePhone;

  @Column(name = "mobile")
  @Type ( type = "text")
  private String mobilePhone;

  @Column(name = "work")
  @Type ( type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Column(name = "email2")
  @Type ( type = "text")
  private String email2;

  @Column(name = "email3")
  @Type ( type = "text")
  private String email3;

  @Expose
  @Column(name = "address")
  @Type ( type = "text")
  private String address;

  @Transient
  private String fullInfo;

  @Column(name = "photo")
  @Type ( type = "text")
  private String photo;

  public File getPhoto() {
    return new File ( photo );
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath ();
    return this;
  }

  public String getFullInfo() {
    return fullInfo;
  }

  public ContactData withFullInfo(String fullInfo) {
    this.fullInfo = fullInfo;
    return this;
  }


  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }


  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }


  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;

  }


  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
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

  public int getId() {
    return id;
  }


  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withTelephone(String telephone) {
    this.telephone = telephone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }


  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass () != o.getClass ()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (name != null ? !name.equals ( that.name ) : that.name != null) return false;
    if (lastname != null ? !lastname.equals ( that.lastname ) : that.lastname != null) return false;
    if (telephone != null ? !telephone.equals ( that.telephone ) : that.telephone != null) return false;
    if (email != null ? !email.equals ( that.email ) : that.email != null) return false;
    if (group != null ? !group.equals ( that.group ) : that.group != null) return false;
    if (homePhone != null ? !homePhone.equals ( that.homePhone ) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals ( that.mobilePhone ) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals ( that.workPhone ) : that.workPhone != null) return false;
    if (allPhones != null ? !allPhones.equals ( that.allPhones ) : that.allPhones != null) return false;
    if (allEmails != null ? !allEmails.equals ( that.allEmails ) : that.allEmails != null) return false;
    if (email2 != null ? !email2.equals ( that.email2 ) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals ( that.email3 ) : that.email3 != null) return false;
    if (address != null ? !address.equals ( that.address ) : that.address != null) return false;
    return fullInfo != null ? fullInfo.equals ( that.fullInfo ) : that.fullInfo == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode () : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode () : 0);
    result = 31 * result + (telephone != null ? telephone.hashCode () : 0);
    result = 31 * result + (email != null ? email.hashCode () : 0);
    result = 31 * result + (group != null ? group.hashCode () : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode () : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode () : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode () : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode () : 0);
    result = 31 * result + (allEmails != null ? allEmails.hashCode () : 0);
    result = 31 * result + (email2 != null ? email2.hashCode () : 0);
    result = 31 * result + (email3 != null ? email3.hashCode () : 0);
    result = 31 * result + (address != null ? address.hashCode () : 0);
    result = 31 * result + (fullInfo != null ? fullInfo.hashCode () : 0);
    return result;
  }
}
