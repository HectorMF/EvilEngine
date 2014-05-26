package com.perfectplay.org.utils;

import java.util.HashMap;
import java.util.Map;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Body;

public class EntityBodyMapper {
	private static Map<Entity, Body> map;
	private static EntityBodyMapper instance = new EntityBodyMapper();

	private EntityBodyMapper() {
		map = new HashMap<Entity, Body>();
	}

	public static EntityBodyMapper getInstance() {
		return instance;
	}

	public Body getBody(Entity entity) {
		return map.get(entity);
	}

	public boolean hasBody(Entity entity) {
		return map.containsKey(entity);
	}

	public void registerBody(Entity entity, Body body) {
		map.put(entity, body);
	}

	public void removeEntity(Entity entity) {
		map.remove(entity);
	}

}
