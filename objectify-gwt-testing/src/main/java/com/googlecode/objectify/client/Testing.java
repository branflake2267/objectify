package com.googlecode.objectify.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.objectify.client.rpc.RpcService;
import com.googlecode.objectify.client.rpc.RpcServiceAsync;
import com.googlecode.objectify.shared.domain.Contact;
import com.googlecode.objectify.shared.domain.Person;

public class Testing implements EntryPoint {

  private final RpcServiceAsync rpcService = GWT.create(RpcService.class);

  @Override
  public void onModuleLoad() {
    RootPanel.get().add(new HTML("GWT is loaded"));

    getPersonTest();
    savePersonTest();
  }

  private void getPersonTest() {
    rpcService.getPerson(new AsyncCallback<Person>() {
      @Override
      public void onSuccess(Person result) {
        RootPanel.get().add(new HTML("Person loaded. person = " + result));
      }
      @Override
      public void onFailure(Throwable caught) {
        caught.printStackTrace();
      }
    });
  }

  private void savePersonTest() {
    Person person = new Person();
    person.setName("savePerson");
    
    Contact contact = new Contact();
    contact.setName("saveContact");
    person.setContact(contact);
    
    rpcService.savePerson(person, new AsyncCallback<Person>() {
      @Override
      public void onSuccess(Person result) {
        RootPanel.get().add(new HTML("Person saved. person = " + result));
      }
      @Override
      public void onFailure(Throwable caught) {
        caught.printStackTrace();
      }
    });
  }
  
}
