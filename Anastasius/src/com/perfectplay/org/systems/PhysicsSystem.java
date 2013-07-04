package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.components.NormalRender;
import com.perfectplay.org.components.Physics;
import com.perfectplay.org.components.Transform;

public class PhysicsSystem extends EntitySystem{
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<Physics> physics;
	
	private World world;
	
	@SuppressWarnings("unchecked")
	public PhysicsSystem(World world) {
		super(Aspect.getAspectForAll(Transform.class, Physics.class));
		this.world = world;
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		Body physicsBody = physics.get(e).getBody();
		physicsBody.setUserData(e);
		float rotation = transforms.get(e).getRotation();
		physicsBody.setTransform(transforms.get(e).getPosition(), (float)Math.toRadians(rotation));
		physicsBody.setActive(true);
		//physicsBody.applyTorque(1f);
	}
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		physics.get(e).getBody().setActive(false);
	}
	
	public World getWorld(){
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
    
	public Body createBody(BodyDef definition){
		return world.createBody(definition);
	}
	
	protected void process(Entity e) {
		Transform t = transforms.get(e);
		Physics p = physics.get(e);
		Body body= p.getBody();
		t.setX(body.getPosition().x);
		t.setY(body.getPosition().y);
		t.setRotation((float)Math.toDegrees(body.getAngle()));
		
		System.out.println(p.getBody().getPosition());
	}
}