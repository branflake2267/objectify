package com.googlecode.objectify.shared.domain;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.impl.ref.StdRef;

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

  public Ref<Contact> getContactRef() {
    return contact;
  }
  
  public Contact getContact() {
    Contact contact = null;
    if (this.contact != null) {
      contact = this.contact.getValue();
    }
    return contact;
  }

  public void setContact(Ref<Contact> contact) {
    this.contact = contact;
  }
  
  public void setContact(Contact value) {
    if (this.contact != null) {
      this.contact = Ref.<Contact>create(this.contact.getKey(), value);
    } else {
      this.contact = new StdRef<Contact>(value);
    }
  }
  
  @Override
  public String toString() {
    String s = "{ Person ";
    s += "name=" + name + " ";
    s += "contact=" + contact + " Person } ";
    return s;
  }
  
}
