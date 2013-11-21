package com.perfectplay.org.systems;

import java.util.List;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.scripting.Script;
import com.perfectplay.org.scripting.ScriptManager;
import com.perfectplay.org.scripting.delegates.WorldDelegate;

public class ScriptSystem extends EntitySystem {
	@SuppressWarnings("unchecked")
	public ScriptSystem() {
		super(Aspect.getAspectForAll(Scripting.class));
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			List<Script> delegates = ScriptManager.getWorldDelegates(e);
			if(delegates != null){
				for(int j = 0; j < delegates.size(); j++){
					ScriptManager.setScriptEntity(e, delegates.get(j));
					((WorldDelegate) delegates.get(j)).onUpdate();
				}
			}
		}
	}

}
