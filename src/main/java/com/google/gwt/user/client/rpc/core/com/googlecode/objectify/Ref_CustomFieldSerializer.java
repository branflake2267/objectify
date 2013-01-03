package com.google.gwt.user.client.rpc.core.com.googlecode.objectify;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

public class Ref_CustomFieldSerializer extends CustomFieldSerializer<Ref> {

  @Override
  public void deserializeInstance(SerializationStreamReader streamReader, Ref instance)
      throws SerializationException {
    Key<?> key = (Key<?>) streamReader.readObject();
    Object value = streamReader.readObject();
    instance = new Ref(key, value);
  }

  @Override
  public void serializeInstance(SerializationStreamWriter streamWriter, Ref instance) throws SerializationException {
    streamWriter.writeObject(instance.getKey());
    streamWriter.writeObject(instance.getValue());
  }

  public static void deserialize(SerializationStreamReader streamReader, Ref instance) throws SerializationException {
    // already handled in instantiate
  }

  public static Ref instantiate(SerializationStreamReader streamReader) throws SerializationException {
    Key<?> key = (Key<?>) streamReader.readObject();
    Object value = streamReader.readObject();
    return new Ref(key, value);
  }

  public static void serialize(SerializationStreamWriter streamWriter, Ref instance) throws SerializationException {
    streamWriter.writeObject(instance.getKey());
    streamWriter.writeObject(instance.getValue());
  }

}
