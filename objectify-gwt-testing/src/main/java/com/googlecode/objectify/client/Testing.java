package com.googlecode.objectify.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Testing implements EntryPoint {

  private final RpcServiceAsync greetingService = GWT.create(RpcService.class);

  @Override
  public void onModuleLoad() {
    
  }

}
