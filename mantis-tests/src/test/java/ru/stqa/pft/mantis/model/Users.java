package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 17.03.2017.
 */
public class Users extends ForwardingSet <UserData> {

  private Set<UserData> delegate;
  private String id;
  private String name;
  private String email;

  public Users(Users users) {
    this.delegate = new HashSet<UserData> ( users.delegate );
  }

  public Users() {
    this.delegate = new HashSet <UserData> ();
  }


  @Override
  protected Set <UserData> delegate() {
    return delegate;
  }


  public String getId() {
    return id;
  }

  public String getName() {
     return name;
  }

  public String getEmail() {
    return email;
  }
}
