package com.googlecode.objectify.impl.translate;

import java.lang.reflect.Type;

import com.googlecode.objectify.impl.Path;
import com.googlecode.objectify.impl.Property;

/**
 * <p>A translator knows how to load an entity Node subgraph into a POJO object.  When an entity class is registered,
 * the known TranslatorFactories are queried to produce Translator objects for each field.  These translators are
 * permanently associated with the entity metadata so they do not need to be discovered at normal runtime.</p>
 * 
 * <p>Translators are composed of other translators; through a chain of these a whole entity
 * object is assembled or disassembled.  Factories can use {@code CreateContext.getFactory().getTranslators()}
 * to create these nested translator instances for component types.</p>
 * 
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public interface TranslatorFactory<T>
{
	/**
	 * @param path current path to this part of the tree, important for logging and exceptions
	 * @param property is the property we are inspecting to create a translator for
	 * @param type is the generic type of the field (or field component).  For example, examining a field of type
	 *  List<String> will have a Type of List<String>; the TranslatorFactory which recognizes List may then ask
	 *  for a translator of its component type String.
	 * @return null if this factory does not know how to deal with that situation. 
	 */
	Translator<T> create(Path path, Property property, Type type, CreateContext ctx);
}
