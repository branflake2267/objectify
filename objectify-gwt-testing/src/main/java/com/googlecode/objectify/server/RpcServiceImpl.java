package com.googlecode.objectify.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.client.RpcService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RpcServiceImpl extends RemoteServiceServlet implements RpcService {

  public String greetServer(String input) throws IllegalArgumentException {
    return null;
  }

}
