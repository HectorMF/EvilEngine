package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.listeners.ZContactFilter;
import com.perfectplay.org.utils.Meter;
import com.perfectplay.org.utils.Pixel;
import com.perfectplay.org.scripting.*;

public class PhysicsSystem extends EntitySystem{
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<RigidBody> physics;
	
	protected static World world;
	
	@SuppressWarnings("unchecked")
	public PhysicsSystem(World world) {
		super(Aspect.getAspectForAll(Transform.class, RigidBody.class));
		PhysicsSystem.world = world;
		PhysicsSystem.world.setContactFilter(new ZContactFilter());
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		Body physicsBody = physics.get(e).getBody();
		physicsBody.setUserData(e);

		Transform transform = transforms.get(e);
		float rotation = transform.getRotation();
		Vector2 position = new Vector2(transform.getX() + transform.getWidth()/2, 
									   transform.getY() + transform.getHeight()/2);
		
		physicsBody.setTransform(Pixel.toMeter(position), (float)Math.toRadians(rotation));
		physicsBody.setActive(true);
	}
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		physics.get(e).getBody().setActive(false);
	}
	
	public static World getWorld(){
		return world;
	}
	
	@Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
		
		world.step(Gdx.graphics.getDeltaTime(),6,2);
		
		for (int i = 0, s = entities.size(); s > i; i++) {
        	process(entities.get(i));
    	}
    }
    
    @Override
    protected boolean checkProcessing() {
    	return true;
    }
    
	public static Body createBody(BodyDef definition){
		return world.createBody(definition);
	}
	
	protected void process(Entity e) {
		Transform transform = transforms.get(e);
		RigidBody rBody = physics.get(e);
		Body body = rBody.getBody();
		
		transform.setX(Meter.toPixel(body.getPosition().x) - transform.getWidth()/2);
		transform.setY(Meter.toPixel(body.getPosition().y) - transform.getHeight()/2);
		transform.setRotation((float)Math.toDegrees(body.getAngle()));
		
		if(transform.isDirty() && transform.hasDelegate())
			((TransformDelegate)transform.getDelegate()).onMove(e, transform);
		
	}
}