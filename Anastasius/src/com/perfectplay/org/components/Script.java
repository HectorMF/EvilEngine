package com.perfectplay.org.components;

import java.util.Hashtable;

import com.artemis.Component;
import com.perfectplay.org.scripting.Delegate;

public class Script extends Component {
	private Hashtable<Class<? extends ScriptableComponent>, Delegate<? extends ScriptableComponent>> delegates;
	
	public Script(){
		delegates = new Hashtable<Class<? extends ScriptableComponent>, Delegate<? extends ScriptableComponent>>();
	}
	
	public void addDelegate(Delegate<? extends ScriptableComponent> delegate){
		delegates.put(delegate.getType(),delegate);
	}
	
	public void removeDelegate(Class<? extends ScriptableComponent> type){
		delegates.remove(type);
	}
	
	public Hashtable<Class<? extends ScriptableComponent>, Delegate<? extends ScriptableComponent>> getTable(){
		return delegates;
	}
}
