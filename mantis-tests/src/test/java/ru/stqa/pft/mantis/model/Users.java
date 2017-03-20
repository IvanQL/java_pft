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

  public Users(Users users) {
    this.delegate = new HashSet<UserData> ( users.delegate );
  }

  public Users() {
    this.delegate = new HashSet <UserData> ();
  }

  public Users(Collection<UserData> contacts) {
    this.delegate = new HashSet <UserData> (contacts);

  }

  @Override
  protected Set <UserData> delegate() {
    return delegate;
  }


  public String getId() {
    return id;
  }
}
