package com.googlecode.objectify.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.client.rpc.RpcService;
import com.googlecode.objectify.impl.ref.StdRef;
import com.googlecode.objectify.shared.domain.Contact;
import com.googlecode.objectify.shared.domain.Person;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

//  static {
//    ObjectifyService.register(Person.class);
//    ObjectifyService.register(Contact.class);
//  }
  
  public Person getPerson() {
    Person person = new Person();
    person.setName("name");
    
    Contact contact = new Contact();
    contact.setId(1l);
    contact.setName("name");
    
    Key<Contact> keyContact = Key.create(Contact.class, 1);
    Ref<Contact> refContact = StdRef.create(keyContact, contact);
    person.setContact(refContact);
     
    return person;
  }

}
