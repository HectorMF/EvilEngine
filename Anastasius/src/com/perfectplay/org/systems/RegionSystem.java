package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.components.CollisionRegions;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.Pixel;

public class RegionSystem extends EntitySystem{
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<CollisionRegions> regions;
	
	@SuppressWarnings("unchecked")
	public RegionSystem(World world) {
		super(Aspect.getAspectForAll(Transform.class, CollisionRegions.class));
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		Body body = regions.get(e).getBody();
		Transform transform = transforms.get(e);
		body.setUserData(e);

		float rotation = transform.getRotation();
		Vector2 position = new Vector2(transform.getX() + transform.getWidth()/2, 
									   transform.getY() + transform.getHeight()/2);
		body.setTransform(Pixel.toMeter(position), (float)Math.toRadians(rotation));
		body.setActive(true);
	}
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		regions.get(e).getBody().setActive(false);
	}
	
	@Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
    	for (int i = 0, s = entities.size(); s > i; i++) {
        	process(entities.get(i));
        }
    }
    
    @Override
    protected boolean checkProcessing() {
            return true;
    }
    
	protected void process(Entity e) {
		Transform transform = transforms.get(e);
		CollisionRegions region = regions.get(e);
		Body body = region.getBody();
		
		float rotation = transform.getRotation();
		Vector2 position = new Vector2(transform.getX() + transform.getWidth()/2, 
									   transform.getY() + transform.getHeight()/2);
		
		body.setTransform(Pixel.toMeter(position), (float)Math.toRadians(rotation));
	}
}