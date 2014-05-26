package com.perfectplay.org.level;

import com.artemis.Entity;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.Transform;

public class LayerNode implements Comparable<LayerNode> {
	private Renderable renderable;
	private Transform spatial;
	private Entity entity;
	
	public LayerNode(Entity entity) { 
		this.renderable = entity.getComponent(Renderable.class);
		this.spatial = entity.getComponent(Transform.class);
		this.entity = entity;
	}

	public Renderable getRenderableComponent() {
		return renderable;
	}

	public Transform getSpatialComponent() {
		return spatial;
	}
	
	public Entity getEntity(){
		return entity;
	}

	@Override
	public int compareTo(LayerNode arg0) {
		float val = renderable.getZIndex()
				- arg0.getRenderableComponent().getZIndex();
		if (val < 0)
			return -1;
		if (val > 0)
			return 1;
		return 0;
	}
}