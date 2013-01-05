package com.googlecode.objectify.shared.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Index
@Entity
public class Person extends BaseEntity {
  
  private String name;

  @Load
  private Ref<Contact> contact;
  
  public Person() {
    name = "";
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Ref<Contact> getContact() {
    return contact;
  }

  public void setContact(Ref<Contact> contact) {
    this.contact = contact;
  }
  
  @Override
  public String toString() {
    String s = "{ Person ";
    s += "name=" + name + " ";
    s += "contact=" + contact + " Person } ";
    return s;
  }
  
}
