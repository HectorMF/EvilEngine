package com.perfectplay.org.scripting;

import java.util.HashMap;

import com.artemis.Component;
import com.artemis.Entity;

public abstract class ScriptableComponent<T extends Delegate> extends Component{
	
	protected static HashMap<Class<? extends Delegate>, Class<? extends ScriptableComponent<? extends Delegate>>> delegateMapping 
			   = new HashMap<Class<? extends Delegate>, Class<? extends ScriptableComponent<? extends Delegate>>>();
	
	protected HashMap<Integer,Script> delegates = new HashMap<Integer,Script>();
	protected boolean hasDelegates = false;
	protected Class<T> delegateType;
	
	public static HashMap<Class<? extends Delegate>, Class<? extends ScriptableComponent<? extends Delegate>>> getDelegateMapping(){
		return delegateMapping;
	}
	
	public ScriptableComponent( Class<? extends ScriptableComponent<T>> componentType, Class<T> delegateType)
    {
        this.delegateType = delegateType;
        delegateMapping.put(delegateType, componentType);
    }
	
	public Class<T> getDelegateType(){
		return delegateType;
	}
	
	public void addDelegate(Entity e, Script val){
		this.delegates.put(e.getId(), val);
		this.hasDelegates = true;
	}
	
	public void removeEntityDelegate(Entity e){
		delegates.remove(e.getId());
		if(delegates.size() == 0) 
			hasDelegates = false;
	}
	
	public T getDelegateForEntity(Entity e){
		return getDelegateType().cast(delegates.get(e.getId()));
	}
	
	public boolean hasDelegates(){
		return hasDelegates;
	}
	
	public boolean hasDelegateForEntity(Entity e){
		return delegates.containsKey(e.getId());
	}
	
}
