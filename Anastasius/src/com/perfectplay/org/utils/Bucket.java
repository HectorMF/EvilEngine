package com.perfectplay.org.utils;

import java.util.ArrayList;

import com.artemis.Entity;

public class Bucket {
	private ArrayList<Entity> entityList;
	
	public Bucket(){
		entityList = new ArrayList<Entity>();
	}
	
	public void insertEntity(Entity entity){
		entityList.add(entity);
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
