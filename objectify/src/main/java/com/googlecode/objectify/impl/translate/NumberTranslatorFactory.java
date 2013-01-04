package com.googlecode.objectify.impl.translate;

import java.lang.reflect.Type;

import com.googlecode.objectify.impl.Path;
import com.googlecode.objectify.impl.Property;
import com.googlecode.objectify.impl.TypeUtils;
import com.googlecode.objectify.repackaged.gentyref.GenericTypeReflector;

/**
 * <p>Numbers are funky in the datastore.  You can save numbers of any size, but they always retrieve as Long.
 * For the hell of it, we also handle String in the datastore by trying to parse it.</p>
 * 
 * <p>Not a ValueTranslatorFactory because Numbers are not assignable to primitives.  Java lame.</p>
 * 
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public class NumberTranslatorFactory implements TranslatorFactory<Number>
{
	@Override
	public ValueTranslator<Number, Object> create(Path path, Property property, Type type, CreateContext ctx)
	{
		final Class<?> clazz = GenericTypeReflector.erase(type);
		
		if (!TypeUtils.isAssignableFrom(Number.class, clazz))
			return null;
		
		return new ValueTranslator<Number, Object>(path, Object.class) {
			@Override
			protected Number loadValue(Object value, LoadContext ctx) {
				if (value instanceof String) {
					try {
						return coerceNumber(Long.valueOf((String)value), clazz);
					} catch (NumberFormatException ex) {} // just pass through
				}
				else if (value instanceof Number) {
					return coerceNumber((Number)value, clazz);
				}
				
				path.throwIllegalState("Don't know how to translate " + value + " to a number");
				return null;	// never gets here
			}
			
			@Override
			protected Object saveValue(Number value, SaveContext ctx) {
				return value;
			}
		};
	}

	/**
	 * Coerces the value to be a number of the specified type; needed because
	 * all numbers come back from the datastore as Long and this screws up
	 * any type that expects something smaller.  Also does toString just for the
	 * hell of it.
	 */
	private Number coerceNumber(Number value, Class<?> type)
	{
		if ((type == Byte.class) || (type == Byte.TYPE)) return value.byteValue();
		else if ((type == Short.class) || (type == Short.TYPE)) return value.shortValue();
		else if ((type == Integer.class) || (type == Integer.TYPE)) return value.intValue();
		else if ((type == Long.class) || (type == Long.TYPE)) return value.longValue();
		else if ((type == Float.class) || (type == Float.TYPE)) return value.floatValue();
		else if ((type == Double.class) || (type == Double.TYPE)) return value.doubleValue();
		else throw new IllegalArgumentException();	// should be impossible
	}
}