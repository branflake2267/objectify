package com.googlecode.objectify.test;

import org.testng.annotations.Test;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.test.util.TestBase;

/**
 * Basic tests for registering entities
 * 
 * @author Matt Quail http://madbean.com/
 */
public class RegisterTests extends TestBase
{
    @Entity
	@Cache
	public static class NonPublicConstructor
	{
		@Id
		Long id;

		private NonPublicConstructor() { }
	}

    @Entity
	@Cache
	public static class NoNoargConstructors
	{
		@Id
		Long id;

		public NoNoargConstructors(long id)
		{
			this.id = id;
		}
	}

    public static class BadStruct {
        int age;

        public BadStruct(int age) {
            this.age = age;
        }
    }
    
    @Entity
	@Cache
    public static class HasEmbedded {
        @Id
        Long id;
        @Embed
        BadStruct name;

    }

	@Test
	public void testNoArgConstructor()
	{
		assertRegisterSucceeds(NonPublicConstructor.class);
		assertRegisterFails(NoNoargConstructors.class, IllegalStateException.class);
		assertRegisterFails(HasEmbedded.class, IllegalStateException.class);
	}

	private void assertRegisterSucceeds(Class<?> entity)
	{
		try
		{
			this.fact.register(entity);
		}
		catch (Exception e)
		{
			assert false : "Unexpected exception of type " + e.getClass();
		}
	}

	private void assertRegisterFails(Class<?> entity, Class<? extends Exception> expectedException)
	{
		try
		{
			this.fact.register(entity);
			assert false : "Shouldn't be register " + entity.getName();
		}
		catch (Exception e)
		{
			assert expectedException.isInstance(e) : "Unexpected exception of type " + e.getClass();
		}
	}
}
