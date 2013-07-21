package com.perfectplay.org.systems;

import java.util.Hashtable;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Script;
import com.perfectplay.org.components.ScriptableComponent;
import com.perfectplay.org.scripting.Delegate;

public class ScriptSystem extends EntitySystem{
	@Mapper ComponentMapper<Script> scripts;
	
	@SuppressWarnings("unchecked")
	public ScriptSystem() {
		super(Aspect.getAspectForAll(Script.class));
	}
	
	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> arg0) {
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		 Hashtable<Class<? extends ScriptableComponent>, Delegate<? extends ScriptableComponent>> table = scripts.get(e).getTable();
		 ScriptableComponent component;
		 for(Class<? extends ScriptableComponent> type : table.keySet()){
			 component = e.getComponent(type);
			 if(component != null)
				 component.setDelegate(table.get(type));
		 }
	}
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
	}
}
