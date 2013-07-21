package com.perfectplay.org.components;

import com.artemis.Component;
import com.perfectplay.org.scripting.Delegate;

public abstract class ScriptableComponent extends Component{
	protected Delegate<? extends ScriptableComponent> delegate = null;
	protected boolean hasDelegate = false;
	
	public void setDelegate(Delegate<? extends ScriptableComponent> delegate){
		this.delegate = delegate;
		this.hasDelegate = true;
	}
	
	public void removeDelegate(){
		delegate = null;
	}
	
	public Delegate<? extends ScriptableComponent> getDelegate(){
		return delegate;
	}
	
	public boolean hasDelegate(){
		return hasDelegate;
	}
	
}
