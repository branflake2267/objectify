package com.googlecode.objectify.condition;

import java.lang.reflect.Field;

import com.googlecode.objectify.ObjectifyFactory;


/**
 * <p>The opposite of IfDefault</p>
 * 
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public class IfNotDefault extends ValueIf<Object> implements InitializeIf
{
	IfDefault opposite = new IfDefault();
	
	@Override
	public void init(ObjectifyFactory fact, Class<?> concreteClass, Field field) {
		opposite.init(fact, concreteClass, field);
	}
	
	@Override
	public boolean matchesValue(Object value) {
		return !opposite.matchesValue(value);
	}
}