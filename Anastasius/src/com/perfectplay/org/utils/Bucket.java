package com.perfectplay.org.utils;

import java.util.ArrayList;

import com.artemis.Entity;

public class Bucket {
	private ArrayList<Entity> entityList;
	
	private boolean isEnabled;
	private boolean isOverlapping;
	public Bucket(){
		entityList = new ArrayList<Entity>();
		isEnabled = false;
	}
	
	public void setOverlapping(boolean overlap){
		this.isOverlapping = overlap;
	}
	
	public boolean isOverlapping(){
		return isOverlapping;
	}
	
	public void insertEntity(Entity entity){
		entityList.add(entity);
	}
	
	public void enable(){
		for(Entity e : entityList){
			if(!e.isEnabled()){
				e.enable();
			}
		}
		isEnabled = true;
	}
	
	public void disable(){
		for(Entity e : entityList){
			if(e.isEnabled())
				e.disable();
		}
		
		isEnabled = false;
	}
	
	public void disableSome(ArrayList<Entity> list){
		for(Entity e : entityList){
			if(!list.contains(e))
				if(e.isEnabled())
					e.disable();
		}
		isEnabled = false;
	}
	
	public boolean isEnabled(){
		return isEnabled;
	}
	
	public void clear(){
		entityList.clear();
	}
	
	public void removeEntity(Entity entity){
		entityList.remove(entity);
	}
	
	public int size(){
		return entityList.size();
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean contains(Entity entity){
		return entityList.contains(entity);
	}
	
	public ArrayList<Entity> getEntities(){
		return entityList;
	}
	
	
	
}
