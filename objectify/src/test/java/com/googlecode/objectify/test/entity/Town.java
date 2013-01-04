package com.googlecode.objectify.test.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Embed;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 */
@Entity
@Cache
public class Town
{
	@Id
	public Long id;

	public String name;
	
	@Embed
	public Someone mayor;

	@Embed
	public Someone[] folk;
}
