package com.perfectplay.org.scripting.delegates;

import com.artemis.Entity;
import com.perfectplay.org.scripting.Delegate;

public interface GeneralDelegate extends Delegate {
	void onUpdate(Entity entity, float delta);
	void onRemove(Entity entity);
	void onAdd(Entity entity);
	void onDisable(Entity entity);
	void onEnable(Entity entity);

}
