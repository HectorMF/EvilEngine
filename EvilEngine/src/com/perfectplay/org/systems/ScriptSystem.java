package com.perfectplay.org.systems;

import java.util.ArrayList;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.ScriptManager;
import com.perfectplay.org.scripting.delegates.GeneralDelegate;

public class ScriptSystem extends EntitySystem {
	private ArrayList<Script> delegates;
	private ArrayList<Entity> entities;
	
	@SuppressWarnings("unchecked")
	public ScriptSystem() {
		super(Aspect.getAspectForAll(Scripting.class));
		delegates = new ArrayList<Script>();
		entities = new ArrayList<Entity>();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> arg0) {
		for (int i = 0; i < delegates.size(); i++){
			ScriptManager.setScriptEntity(entities.get(i), delegates.get(i));
			((GeneralDelegate) delegates.get(i)).onUpdate();
		}
	}
	
	@Override
	public void enabled(Entity e){
		super.enabled(e);
		
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i) == e){
				ScriptManager.setScriptEntity(entities.get(i), delegates.get(i));
				((GeneralDelegate) delegates.get(i)).onEnable();
			}
		}
	}
	
	@Override
	public void disabled(Entity e){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i) == e){
				ScriptManager.setScriptEntity(entities.get(i), delegates.get(i));
				((GeneralDelegate) delegates.get(i)).onDisable();
			}
		}
		super.disabled(e);
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		Scripting scripting = e.getComponent(Scripting.class);
		if(scripting != null){
			for (Script s : scripting.getScripts()) {
				if(s instanceof GeneralDelegate){
					delegates.add(s);
					entities.add(e);
				}
			}
		}
	}

	@Override
	protected void removed(Entity e) {
		super.removed(e);
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i) == e){
				delegates.remove(i);
				entities.remove(i);
			}
		}
	}
}
