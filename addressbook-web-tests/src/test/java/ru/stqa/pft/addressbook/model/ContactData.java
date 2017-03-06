package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String name;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Expose
  @Transient
  private String telephone;

  @Expose
  @Column(name = "email")
  @Type ( type = "text")
  private String email;



  @Expose
  @Column(name = "home")
  @Type( type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type ( type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type ( type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Transient
  private String allEmails;

  @Expose
  @Column(name = "email2")
  @Type ( type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type ( type = "text")
  private String email3;

  @Expose
  @Column(name = "address")
  @Type ( type = "text")
  private String address;


  @Transient
  private String fullInfo;


  @Transient
  @Column(name = "photo")
  @Type ( type = "text")
  private String photo;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable (name = "address_in_groups",
          joinColumns = @JoinColumn (name = "id"), inverseJoinColumns = @JoinColumn (name = "group_id"))
  private Set<GroupData> groups = new HashSet <GroupData> (  );


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

  public Groups getGroups() {
    return new Groups ( groups ) ;
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
            "id=" + id +
            ", name='" + name + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", address='" + address + '\'' +
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
    if (email != null ? !email.equals ( that.email ) : that.email != null) return false;
    if (homePhone != null ? !homePhone.equals ( that.homePhone ) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals ( that.mobilePhone ) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals ( that.workPhone ) : that.workPhone != null) return false;
    if (email2 != null ? !email2.equals ( that.email2 ) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals ( that.email3 ) : that.email3 != null) return false;
    return address != null ? address.equals ( that.address ) : that.address == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode () : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode () : 0);
    result = 31 * result + (email != null ? email.hashCode () : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode () : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode () : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode () : 0);
    result = 31 * result + (email2 != null ? email2.hashCode () : 0);
    result = 31 * result + (email3 != null ? email3.hashCode () : 0);
    result = 31 * result + (address != null ? address.hashCode () : 0);
    return result;
  }

  public ContactData inGroup(GroupData group) {
    groups.add ( group );
    return this;
  }
}
