package com.googlecode.objectify.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.condition.If;
import com.googlecode.objectify.condition.InitializeIf;
import com.googlecode.objectify.repackaged.gentyref.GenericTypeReflector;

/** 
 * Utility that makes it easy to generate If conditions. 
 */
public class IfConditionGenerator
{
	ObjectifyFactory fact;
	Class<?> examinedClass;
	
	/**
	 * @param examinedClass is the actual top level concrete class we are examining; the field might
	 * be declared on a superclass of this class so it's not the same as field.getDeclaringClass()
	 */
	public IfConditionGenerator(ObjectifyFactory fact, Class<?> examinedClass) {
		this.fact = fact;
		this.examinedClass = examinedClass;
	}
	
	/** */
	public If<?, ?>[] generateIfConditions(Class<? extends If<?, ?>>[] ifClasses, Field field) {
		If<?, ?>[] result = new If<?, ?>[ifClasses.length];
		
		for (int i=0; i<ifClasses.length; i++) {
			
			Class<? extends If<?, ?>> ifClass = ifClasses[i];
			result[i] = this.createIf(ifClass, field);

			// Sanity check the generic If class types to ensure that they match the actual types of the field & entity.
			
			Type valueType = GenericTypeReflector.getTypeParameter(ifClass, If.class.getTypeParameters()[0]);
			Class<?> valueClass = GenericTypeReflector.erase(valueType);
			
			Type pojoType = GenericTypeReflector.getTypeParameter(ifClass, If.class.getTypeParameters()[1]);
			Class<?> pojoClass = GenericTypeReflector.erase(pojoType);
			
			if (!TypeUtils.isAssignableFrom(valueClass, field.getType()))
				throw new IllegalStateException("Cannot use If class " + ifClass.getName() + " on " + field
						+ " because you cannot assign " + field.getType().getName() + " to " + valueClass.getName());
			
			if (!TypeUtils.isAssignableFrom(pojoClass, examinedClass))
				throw new IllegalStateException("Cannot use If class " + ifClass.getName() + " on " + field
						+ " because the containing class " + examinedClass.getName() + " is not compatible with " + pojoClass.getName());
		}
		
		return result;
	}
	
	/** */
	public If<?, ?> createIf(Class<? extends If<?, ?>> ifClass, Field field)
	{
		If<?, ?> created = fact.construct(ifClass);
		
		if (created instanceof InitializeIf)
			((InitializeIf)created).init(fact, examinedClass, field);
		
		return created;
	}
}