package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.scripting.Component;
import com.perfectplay.org.scripting.Delegate;
import com.perfectplay.org.scripting.Script;

public class ScriptSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Scripting> scripts;

	@SuppressWarnings("unchecked")
	public ScriptSystem() {
		super(Aspect.getAspectForAll(Scripting.class));
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> arg0) {
		for(int i = 0; i < arg0.size(); i++){
			process(arg0.get(i));
		}
	}
	
	private void process(Entity entity){
		Scripting sc = scripts.get(entity);
		sc.getScripts();
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		// System.out.println(e.getId());

		for (Script s : scripts.get(e).getScripts()) {
			for (Class<? extends Delegate> type : Component.getDelegateMapping().keySet()) {
				if (type.isInstance(s)) {
					Component<? extends Delegate> sc = e
							.getComponent(Component
									.getDelegateMapping().get(type));
					if (sc != null) {
						sc.addDelegate(e, s);
					}
				}
			}
		}
		/*
		 * Hashtable<Class<? extends ScriptableComponent>, Delegate<? extends
		 * ScriptableComponent>> table = scripts.get(e).getTable();
		 * ScriptableComponent component; for(Class<? extends
		 * ScriptableComponent> type : table.keySet()){ component =
		 * e.getComponent(type); if(component != null)
		 * component.setDelegate(table.get(type)); }
		 */
	}

	@Override
	protected void removed(Entity e) {
		super.removed(e);
	}
}
