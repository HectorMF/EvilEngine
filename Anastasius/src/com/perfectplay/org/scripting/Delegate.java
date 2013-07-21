package com.perfectplay.org.scripting;

import com.perfectplay.org.components.ScriptableComponent;

public class Delegate<T extends ScriptableComponent>{
	//private T component;
	
	private Class<T> type;
	
	public Delegate(Class<T> type)
    {
        this.type = type;
    }
	/*
	public void setComponent(T component){
		this.component = component; 
	}
	
	public T getComponent(){
		return component;
	}*/
	
	public Class<T> getType(){
		return type;
	}
}
