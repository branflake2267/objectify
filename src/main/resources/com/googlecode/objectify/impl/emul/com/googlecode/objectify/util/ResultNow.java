package com.googlecode.objectify.util;

import java.io.Serializable;

import com.googlecode.objectify.Result;

/**
 * Simplistic result that holds a constant value.
 *
 * @author Jeff Schnitzer <jeff@infohazard.org>
 */
public class ResultNow<T> implements Serializable
{
	private static final long serialVersionUID = 1L;

	private T value;
	
	public ResultNow() {
	}

	public ResultNow(T value) {
		this.value = value;
	}

	public T now() {
		return value;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "(" + value + ")";
	}
}