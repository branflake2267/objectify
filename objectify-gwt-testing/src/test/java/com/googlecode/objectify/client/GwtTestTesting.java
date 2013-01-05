package com.googlecode.objectify.client;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.objectify.client.rpc.RpcService;
import com.googlecode.objectify.client.rpc.RpcServiceAsync;
import com.googlecode.objectify.shared.domain.Person;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase. Using
 * <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to navigate
 * with your browser to a specific url given by Maven. See
 * http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html for
 * details.
 */
public class GwtTestTesting extends GWTTestCase {

  // TODO why isn't this working during test, source is missing, ugh!
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
  
  public String getModuleName() {
    return "com.googlecode.objectify.Testing";
  }

  public void testIfWorks() {
    assertTrue(true);
  }

  public void testGetPerson() {
    RpcServiceAsync rpc = GWT.create(RpcService.class);
    ServiceDefTarget target = (ServiceDefTarget) rpc;
    target.setServiceEntryPoint(GWT.getModuleBaseURL() + "testing/rpc");
    delayTestFinish(10000);

    rpc.getPerson(new AsyncCallback<Person>() {
      public void onFailure(Throwable caught) {
        fail("Request failure: " + caught.getMessage());
      }

      public void onSuccess(Person result) {
        assertNotNull(result.getName());
        assertNotNull(result.getContact().getValue().getName());
        finishTest();
      }
    });
  }

}
