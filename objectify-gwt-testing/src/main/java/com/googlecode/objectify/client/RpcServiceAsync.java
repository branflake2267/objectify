package com.googlecode.objectify.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>RpcService</code>.
 */
public interface RpcServiceAsync {
  void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
