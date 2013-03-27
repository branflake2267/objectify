package com.googlecode.objectify.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.shared.domain.Person;

/**
 * The async counterpart of <code>RpcService</code>.
 */
public interface RpcServiceAsync {
  void getPerson(AsyncCallback<Person> callback);

  void savePerson(Person person, AsyncCallback<Person> callback);
}
