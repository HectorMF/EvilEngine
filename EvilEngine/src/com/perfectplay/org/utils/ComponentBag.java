package com.perfectplay.org.utils;

import java.util.HashMap;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;

public class ComponentBag {
	private HashMap<Class<? extends Component>, Component> components;
	private Entity entity;
	
	public ComponentBag(Entity entity, Bag<Component> bag){
		this.entity = entity;
		components = new HashMap<Class<? extends Component>, Component>();
		for(int i = 0; i < bag.size(); i++){
			components.put(bag.get(i).getClass(), bag.get(i));
		}
	}
	
	public <T extends Component> T get(Class<T> type){
		return type.cast(components.get(type));
	}
	
	public int size(){
		return components.size();
	}
	
	public <T extends Component> void add(T component){
		components.put(component.getClass(), component);
		entity.addComponent(component);
		entity.changedInWorld();
	}
	
	public <T extends Component> void remove(T component){
		this.remove(component.getClass());
	}
	
	public <T extends Component> void remove(Class<T> componentType){
		components.remove(componentType);
		entity.removeComponent(componentType);
		entity.changedInWorld();
	}
	
	@SuppressWarnings("unchecked")
	public void removeAll(){
		Object[] keys = components.keySet().toArray();
		for(int i = 0; i < keys.length; i++){
			remove((Class<? extends Component>)keys[i]);
		}
	}
	
}