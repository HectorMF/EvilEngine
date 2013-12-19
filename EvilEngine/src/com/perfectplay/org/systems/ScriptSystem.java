package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Scripts;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.ScriptManager;

public class ScriptSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Scripts> scripts;
	
	@SuppressWarnings("unchecked")
	public ScriptSystem() {
		super(Aspect.getAspectForAll(Scripts.class));
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			for(Script s : scripts.get(e).getScripts()){
				ScriptManager.setScriptEntity(e, s);
				s.onUpdate();
			}
		}
	}

}
