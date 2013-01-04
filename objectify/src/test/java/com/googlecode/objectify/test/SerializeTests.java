package com.googlecode.objectify.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Serialize;
import com.googlecode.objectify.test.util.TestBase;
import com.googlecode.objectify.test.util.TestObjectify;
import com.googlecode.objectify.test.util.TestObjectifyFactory;

/**
 * Tests of the {@code @Serialize} annotation
 */
public class SerializeTests extends TestBase
{
	@Entity
	@Cache
	public static class HasSerialize
	{
		@Id public Long id;
		@Serialize public Map<Long, Long> numbers = new HashMap<Long, Long>();
	}

	@Test
	public void testSimpleSerialize() throws Exception
	{
		fact.register(HasSerialize.class);
		
		HasSerialize hs = new HasSerialize();
		hs.numbers.put(1L, 2L);
		hs.numbers.put(3L, 4L);
		
		HasSerialize fetched = this.putClearGet(hs);
		assert fetched.numbers.equals(hs.numbers);
	}

	/** */
	public static class HasLongs
	{
		@Serialize long[] longs;
	}

	@Entity
	@Cache
	public static class EmbedSerialize
	{
		@Id public Long id;
		@Embed public HasLongs simple;
	}
	
	@Test
	public void testEmbedSerialize() throws Exception
	{
		fact.register(EmbedSerialize.class);
		
		EmbedSerialize es = new EmbedSerialize();
		es.simple = new HasLongs();
		es.simple.longs = new long[] { 1L, 2L, 3L };
		
		EmbedSerialize fetched = this.putClearGet(es);
		assert Arrays.equals(es.simple.longs, fetched.simple.longs);
	}

	@Entity(name="HasSerialize")
	@Cache
	public static class HasSerializeZip
	{
		@Id public Long id;
		@Serialize(zip=true) public Map<Long, Long> numbers = new HashMap<Long, Long>();
	}

	@Test
	public void testSerializeZip() throws Exception
	{
		fact.register(HasSerializeZip.class);
		
		HasSerializeZip hs = new HasSerializeZip();
		hs.numbers.put(1L, 2L);
		hs.numbers.put(3L, 4L);
		
		HasSerializeZip fetched = this.putClearGet(hs);
		assert fetched.numbers.equals(hs.numbers);
	}

	@Test
	public void testSerializeZipButReadUnzip() throws Exception
	{
		fact.register(HasSerializeZip.class);
		
		HasSerializeZip hs = new HasSerializeZip();
		hs.numbers.put(1L, 2L);
		hs.numbers.put(3L, 4L);
		
		TestObjectify ofy = fact.begin();
		ofy.put(hs);
		
		// Now we need to read it using the non-zip annotation
		TestObjectifyFactory fact2 = new TestObjectifyFactory();
		fact2.register(HasSerialize.class);
		
		HasSerialize fetched = fact2.begin().load().type(HasSerialize.class).id(hs.id).get();
		assert fetched.numbers.equals(hs.numbers);
	}

	@Test
	public void testSerializeUnzipButReadZip() throws Exception
	{
		fact.register(HasSerialize.class);
		
		HasSerialize hs = new HasSerialize();
		hs.numbers.put(1L, 2L);
		hs.numbers.put(3L, 4L);
		
		TestObjectify ofy = fact.begin();
		ofy.put(hs);
		
		// Now we need to read it using the zip annotation
		TestObjectifyFactory fact2 = new TestObjectifyFactory();
		fact2.register(HasSerializeZip.class);
		
		HasSerializeZip fetched = fact2.begin().load().type(HasSerializeZip.class).id(hs.id).get();
		assert fetched.numbers.equals(hs.numbers);
	}
}
