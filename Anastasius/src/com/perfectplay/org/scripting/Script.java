package com.perfectplay.org.scripting;

public abstract class Script{
	private static int uID = 0;
	protected final int id;
	
	public Script(){
		id = uID;
		uID++;
	}
	
	public int getID(){
		return id;
	}
}
