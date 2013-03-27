package com.googlecode.objectify.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.client.rpc.RpcService;
import com.googlecode.objectify.shared.domain.Contact;
import com.googlecode.objectify.shared.domain.Person;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  static {
    ObjectifyService.register(Person.class);
    ObjectifyService.register(Contact.class);
  }
  
  @Override
  public Person getPerson() {
    Person person = null;

    try {
      person = new Person();
      person.setName("namePerson");
      
      Contact contact = new Contact();
      contact.setName("nameContact");
      ofy().save().entity(contact).now();
      
      Ref<Contact> refContact = Ref.create(contact);
      person.setContact(refContact);
       
      // save entity
      ofy().save().entity(person).now();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return person;
  }
  
  @Override
  public Person savePerson(Person person) {
    try {
      ofy().save().entity(person).now();
    } catch (Exception e) {
      person = null;
      e.printStackTrace();
    }
    return person;
  }

}
