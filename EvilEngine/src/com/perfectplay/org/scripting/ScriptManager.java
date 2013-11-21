package com.perfectplay.org.scripting;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.artemis.Component;
import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.Manager;
import com.artemis.utils.Bag;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.scripting.delegates.WorldDelegate;
import com.perfectplay.org.utils.ComponentBag;

public class ScriptManager extends Manager{
	private static HashMap<Integer, ComponentBag> componentsByEntity; 
	
	private static HashMap<Integer, List<Script>> worldDelegatesByEntity;

	private static ComponentType scriptType = ComponentType.getTypeFor(Scripting.class);
	
	public static void setScriptEntity(Entity entity, Script script){
		if(componentsByEntity.containsKey(entity.getId())){
			script.setEntity(entity, componentsByEntity.get(entity.getId()));
		}else{
			System.out.println("Error: Script does not belong to entity");
		}
	}
	
	public static List<Script> getWorldDelegates(Entity e){
		return worldDelegatesByEntity.get(e.getId());
	}

	public ScriptManager(){
		componentsByEntity = new HashMap<Integer,ComponentBag>();
		worldDelegatesByEntity = new HashMap<Integer, List<Script>>();
	}
	
	@Override
	protected void initialize() {}
	
	@Override
	public void added(Entity e) {
		Scripting component = (Scripting) e.getComponent(scriptType);
		if(component != null){
			componentsByEntity.put(e.getId(), new ComponentBag(e,e.getComponents(new Bag<Component>())));
			for (Script script : component.getScripts()) {
				List<Script> delegates = new ArrayList<Script>();
				if(script instanceof WorldDelegate){
					delegates.add(script);
				    setScriptEntity(e,script);
					((WorldDelegate) script).onAdd();
				}
				worldDelegatesByEntity.put(e.getId(), delegates);
			}
		}
	}
	
	@Override
	public void changed(Entity e) {
		Scripting component = (Scripting) e.getComponent(scriptType);
		if(component != null){
			componentsByEntity.put(e.getId(), new ComponentBag(e,e.getComponents(new Bag<Component>())));
			for (Script script : component.getScripts()) {
				List<Script> delegates = new ArrayList<Script>();
				if(script instanceof WorldDelegate){
					delegates.add(script);
				}
				worldDelegatesByEntity.put(e.getId(), delegates);
			}
		}else{
			if(componentsByEntity.containsKey(e.getId())){
				componentsByEntity.remove(e.getId());
				worldDelegatesByEntity.remove(e.getId());
			}
		}
	}
	
	@Override
	public void deleted(Entity e) {
		if(componentsByEntity.containsKey(e.getId())){
			for(Script script : worldDelegatesByEntity.get(e.getId()) )
			{
				setScriptEntity(e,script);
				((WorldDelegate) script).onRemove();
			}
			componentsByEntity.remove(e.getId());
			worldDelegatesByEntity.remove(e.getId());
		}
	}
	
	@Override
	public void disabled(Entity e) {
		if(componentsByEntity.containsKey(e.getId())){
			for(Script script : worldDelegatesByEntity.get(e.getId()) )
			{
				setScriptEntity(e,script);	
				((WorldDelegate) script).onDisable();
			}
		}
	}
	
	@Override
	public void enabled(Entity e) {
		if(componentsByEntity.containsKey(e.getId())){
			for(Script script : worldDelegatesByEntity.get(e.getId()) )
			{
				setScriptEntity(e,script);
				((WorldDelegate) script).onEnable();
			}
		}
	}
}
