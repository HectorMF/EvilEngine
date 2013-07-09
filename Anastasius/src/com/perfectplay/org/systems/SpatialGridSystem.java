package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.SpatialGrid;

public class SpatialGridSystem extends EntitySystem{
	//@Mapper ComponentMapper<Transform> transforms;
	
	private SpatialGrid spatialGrid;
	@SuppressWarnings("unchecked")
	public SpatialGridSystem(SpatialGrid spatialGrid) {
		super(Aspect.getAspectForAll(Transform.class));
		this.spatialGrid = spatialGrid;
	}

	@Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
		spatialGrid.clear();
		for (int i = 0, s = entities.size(); s > i; i++) {
        	process(entities.get(i));
    	}
    }
	
	protected void process(Entity e) {
		spatialGrid.insertEntity(e);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
