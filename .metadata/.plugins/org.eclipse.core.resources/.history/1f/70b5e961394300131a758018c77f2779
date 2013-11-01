package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.utils.ParallaxCamera;
import com.perfectplay.org.utils.SpatialGrid;

public class SpatialGridSystem extends EntitySystem {
	@Mapper
	ComponentMapper<SpatialComponent> spatialComponents;

	private SpatialGrid spatialGrid;
	
	private ParallaxCamera camera;
	
	@SuppressWarnings("unchecked")
	public SpatialGridSystem(SpatialGrid spatialGrid) {
		super(Aspect.getAspectForAll(SpatialComponent.class));
		this.spatialGrid = spatialGrid;
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		if (spatialComponents.get(e).getBuckets() == null)
			spatialGrid.insertEntity(e, spatialComponents.get(e));
	}

	@Override
	protected final void processEntities(ImmutableBag<Entity> entities) {
		updateSpatialGrid();
		for (int i = 0, s = entities.size(); s > i; i++) {
			process(entities.get(i));
		}
	}

	@Override
	protected void removed(Entity e) {
		super.removed(e);
		// spatialGrid.removeEntity(e);
	}
	
	public void setCamera(ParallaxCamera camera) {
		this.camera = camera;
	}
	
	public SpatialGrid getSpatialGrid(){
		return spatialGrid;
	}
	
	private void updateSpatialGrid() {
		spatialGrid.activateBucketsOnScreen(
				(int) (camera.position.x - (camera.viewportWidth / 2)),
				(int) (camera.position.y - (camera.viewportHeight / 2)),
				(int) camera.viewportWidth, (int) camera.viewportHeight);
	}
	
	protected void process(Entity e) {
		SpatialComponent SpatialComponent = spatialComponents.get(e);
		if (SpatialComponent.isDirty()) {
			if (SpatialComponent.hasDelegates()) {
				SpatialComponent.getDelegateForEntity(e).onMove(e,
						SpatialComponent);
			}
			spatialGrid.updateEntity(e, SpatialComponent);
			spatialComponents.get(e).setDirty(false);
		} else {
			if (SpatialComponent.hasDelegates()) {
				SpatialComponent.getDelegateForEntity(e).onStay(e,
						SpatialComponent);
			}
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
