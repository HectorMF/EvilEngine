package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Camera;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.SpatialGrid;

public class SpatialGridSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Transform> spatialComponents;

	private SpatialGrid spatialGrid;
	
	private Camera camera;
	
	@SuppressWarnings("unchecked")
	public SpatialGridSystem(SpatialGrid spatialGrid) {
		super(Aspect.getAspectForAll(Transform.class));
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
	
	public void setCamera(Camera camera) {
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
		Transform SpatialComponent = spatialComponents.get(e);
		if (SpatialComponent.isDirty()) {
			spatialGrid.updateEntity(e, SpatialComponent);
			spatialComponents.get(e).setDirty(false);
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
