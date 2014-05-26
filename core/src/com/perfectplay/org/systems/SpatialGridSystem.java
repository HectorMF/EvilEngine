package com.perfectplay.org.systems;

import java.util.HashMap;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Camera;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.BodylessSpatial;
import com.perfectplay.org.utils.SpatialGrid;

public class SpatialGridSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Transform> transforms;

	private HashMap<Entity, BodylessSpatial> oldTransforms;
	private SpatialGrid spatialGrid;
	
	private Camera camera;
	
	@SuppressWarnings("unchecked")
	public SpatialGridSystem(SpatialGrid spatialGrid) {
		super(Aspect.getAspectForAll(Transform.class));
		this.spatialGrid = spatialGrid;
		this.oldTransforms = new HashMap<Entity, BodylessSpatial>();
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		oldTransforms.put(e, new BodylessSpatial().setSpatial(transforms.get(e)));
		if (transforms.get(e).getBuckets() == null)
			spatialGrid.insertEntity(e, transforms.get(e));
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
		oldTransforms.remove(e);
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	
	public SpatialGrid getSpatialGrid(){
		return spatialGrid;
	}
	
	private void updateSpatialGrid() {
	/*	spatialGrid.activateBucketsOnScreen(
				(int) (camera.position.x - (camera.viewportWidth / 2)),
				(int) (camera.position.y - (camera.viewportHeight / 2)),
				(int) camera.viewportWidth, (int) camera.viewportHeight);*/
	}
	
	protected void process(Entity e) {
		/*Transform transform = transforms.get(e);
		if (!oldTransforms.get(e).equals(transform)) {
			
			//System.out.println("Entity:" + e + "-" + oldTransforms.get(e).getPosition() + ":" + transform.getPosition());
			oldTransforms.put(e, new BodylessSpatial().setSpatial(transform));
			spatialGrid.updateEntity(e, transform);
		}*/
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
