package com.perfectplay.org.scripting;


import java.util.HashMap;
import java.util.List;

import com.artemis.Component;
import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.Manager;
import com.artemis.utils.Bag;
import com.perfectplay.org.components.Scripts;
import com.perfectplay.org.scripting.delegates.WorldDelegate;
import com.perfectplay.org.utils.ComponentBag;

public class ScriptManager extends Manager{
	private static HashMap<Integer, ComponentBag> componentsByEntity; 
	
	private static ComponentType scriptType = ComponentType.getTypeFor(Scripts.class);
	
	public static void setScriptEntity(Entity entity, Script script){
		if(componentsByEntity.containsKey(entity.getId())){
			script.setEntity(entity, componentsByEntity.get(entity.getId()));
		}else{
			System.out.println("Error: Script does not belong to entity");
		}
	}
	
	public ScriptManager(){
		componentsByEntity = new HashMap<Integer,ComponentBag>();
	}
	
	@Override
	protected void initialize() {}
	
	@Override
	public void added(Entity e) {
		Scripts component = null;
		try{
			component = (Scripts) e.getComponent(scriptType);
		}catch(ArrayIndexOutOfBoundsException exc){
		}
		if(component != null){
			//This entity has a scripting component. Add it's components to the bag
			componentsByEntity.put(e.getId(), new ComponentBag(e,e.getComponents(new Bag<Component>())));
			//for all world delegates, call onAdd();
			List<WorldDelegate> delegates = component.getDelegates(WorldDelegate.class);
			for(int j = 0; j < delegates.size(); j++){
				ScriptManager.setScriptEntity(e, (Script)delegates.get(j));
				delegates.get(j).onAdd();
			}
		}
	}
	
	@Override
	public void changed(Entity e) {
		Scripts component = (Scripts) e.getComponent(scriptType);
		if(component != null){
			//update the mapped entity component pair
			componentsByEntity.put(e.getId(), new ComponentBag(e,e.getComponents(new Bag<Component>())));
		}else{
			//the scripting component has been removed, remove the hashmap pairing
			if(componentsByEntity.containsKey(e.getId())){
				componentsByEntity.remove(e.getId());
			}
		}
	}
	
	@Override
	public void deleted(Entity e) {
		if(componentsByEntity.containsKey(e.getId())){
			//for all world delegates, call onRemove();
			Scripts component = (Scripts) e.getComponent(scriptType);
			if(component != null){
				List<WorldDelegate> delegates = component.getDelegates(WorldDelegate.class);
				for(int j = 0; j < delegates.size(); j++){
					ScriptManager.setScriptEntity(e, (Script)delegates.get(j));
					delegates.get(j).onRemove();
				}
			}
			componentsByEntity.remove(e.getId());
		}
	}
	
	@Override
	public void disabled(Entity e) {
		if(componentsByEntity.containsKey(e.getId())){
			//for all world delegates, call onDisable();
			Scripts component = (Scripts) e.getComponent(scriptType);
			if(component != null){
				List<WorldDelegate> delegates = component.getDelegates(WorldDelegate.class);
				for(int j = 0; j < delegates.size(); j++){
					ScriptManager.setScriptEntity(e, (Script)delegates.get(j));
					delegates.get(j).onDisable();
				}
			}
		}
	}
	
	@Override
	public void enabled(Entity e) {
		if(componentsByEntity.containsKey(e.getId())){
			//for all world delegates, call onEnable();
			Scripts component = (Scripts) e.getComponent(scriptType);
			if(component != null){
				List<WorldDelegate> delegates = component.getDelegates(WorldDelegate.class);
				for(int j = 0; j < delegates.size(); j++){
					ScriptManager.setScriptEntity(e, (Script)delegates.get(j));
					delegates.get(j).onEnable();
				}
			}
		}
	}
}
