package com.googlecode.objectify.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.googlecode.objectify.shared.domain.Person;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("rpc")
public interface RpcService extends RemoteService {
  Person getPerson();
  
  Person savePerson(Person person);
}
