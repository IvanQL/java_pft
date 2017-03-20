package ru.stqa.pft.mantis.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Created by Administrator on 17.03.2017.
 */
@XStreamAlias("users")
@Entity
@Table (name = "mantis_user_table")
public class UserData {


  @XStreamOmitField
  @Id
  @Column (name = "id")
  private int id = Integer.MAX_VALUE;

  @Column (name = "username")
  private  String name;




  public int getId() {

    return id;
  }

  public  UserData withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public UserData withName(String name) {
    this.name = name;
    return this;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass () != o.getClass ()) return false;

    UserData userData = (UserData) o;

    if (id != userData.id) return false;
    return name != null ? name.equals ( userData.name ) : userData.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode () : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }




  }

