package com.perfectplay.org.utils;

import com.artemis.Component;
import com.artemis.Entity;

public class EntityComponentContainer<T extends Component> {
	private Entity entity;
	private T component;

	public EntityComponentContainer(Entity entity, T component) {
		this.entity = entity;
		this.component = component;
	}

	public Entity getEntity() {
		return entity;
	}

	public T getComponent() {
		return component;
	}

}
