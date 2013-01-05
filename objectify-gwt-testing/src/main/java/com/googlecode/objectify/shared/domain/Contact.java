package com.googlecode.objectify.shared.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Index
@Entity
public class Contact extends BaseEntity {
  private String name;

  public Contact() {
    name = "";
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
