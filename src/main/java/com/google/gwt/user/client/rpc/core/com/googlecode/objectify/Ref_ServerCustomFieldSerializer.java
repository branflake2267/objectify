package com.google.gwt.user.client.rpc.core.com.googlecode.objectify;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.server.rpc.ServerCustomFieldSerializer;
import com.google.gwt.user.server.rpc.impl.DequeMap;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.impl.ref.StdRef;

public class Ref_ServerCustomFieldSerializer extends ServerCustomFieldSerializer<Ref> {

  @Override
  public void deserializeInstance(ServerSerializationStreamReader streamReader, Ref instance,
      Type[] expectedParameterTypes, DequeMap<TypeVariable<?>, Type> resolvedTypes) throws SerializationException {
    Key<?> key = (Key<?>) streamReader.readObject();
    Object value = streamReader.readObject();
    instance = new StdRef(key, value);
  }

  @Override
  public void deserializeInstance(SerializationStreamReader streamReader, Ref instance)
      throws SerializationException {
    Key<?> key = (Key<?>) streamReader.readObject();
    Object value = streamReader.readObject();
    instance = new StdRef(key, value);
  }

  @Override
  public void serializeInstance(SerializationStreamWriter streamWriter, Ref instance) throws SerializationException {
    streamWriter.writeObject(instance.getKey());
    streamWriter.writeObject(instance.getValue());
  }
}
