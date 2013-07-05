package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.Meter;
import com.perfectplay.org.utils.Pixel;

public class PhysicsSystem extends EntitySystem{
	protected static int PixelsPerMeter = 20;
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<RigidBody> physics;
	
	public static World world;
	
	@SuppressWarnings("unchecked")
	public PhysicsSystem(World world) {
		super(Aspect.getAspectForAll(Transform.class, RigidBody.class));
		PhysicsSystem.world = world;
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
			world.step(1/60f,6,2);
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
		Transform t = transforms.get(e);
		RigidBody p = physics.get(e);
		Body body= p.getBody();
		
		t.setX(Meter.toPixel(body.getPosition().x) - t.getWidth()/2);
		t.setY(Meter.toPixel(body.getPosition().y) - t.getHeight()/2);
		t.setRotation((float)Math.toDegrees(body.getAngle()));
		
	System.out.println(Meter.toPixel(body.getPosition().y) - t.getHeight()/2);
	}
}