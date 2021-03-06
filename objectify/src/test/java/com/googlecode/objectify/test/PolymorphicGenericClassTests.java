/*
 */

package com.googlecode.objectify.test;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.test.util.TestBase;

/**
 * Checking to make sure polymorphism works with generic base classes.
 * 
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public class PolymorphicGenericClassTests extends TestBase {
  /** */
  @SuppressWarnings("unused")
  private static Logger log = Logger.getLogger(PolymorphicGenericClassTests.class.getName());

  /** */
  @Entity
  public static class Vehicle<T> {
    @Id
    Long id;
    T name;
  }

  /** */
  @EntitySubclass(index = true)
  public static class Car extends Vehicle<String> {
    int numWheels;
  }

  /** */
  @Test
  public void testQuery() throws Exception {
    this.fact.register(Vehicle.class);
    this.fact.register(Car.class);

    Car car = new Car();
    car.name = "Fast";
    Car c2 = this.putClearGet(car);
    assert car.name.equals(c2.name);

    Objectify ofy = this.fact.begin();

    @SuppressWarnings("rawtypes")
    List<Vehicle> all = ofy.load().type(Vehicle.class).list();
    assert all.size() == 1;
    assert all.get(0).name.equals(car.name);
  }
}
