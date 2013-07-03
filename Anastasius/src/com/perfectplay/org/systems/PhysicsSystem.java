package com.perfectplay.org.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.perfectplay.org.components.NormalRender;
import com.perfectplay.org.components.Physics;
import com.perfectplay.org.components.Transform;

public class PhysicsSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<Physics> physics;
	
	private World world;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public PhysicsSystem(World world) {
		super(Aspect.getAspectForAll(Transform.class, Physics.class));
		this.world = world;
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		
		//transforms.get(e).
		
	}
	@Override
	protected void process(Entity e) {
		Transform t = transforms.get(e);
		//NormalRender s = sprites.get(e);
		//s.getSprite().update(world.delta*1000);

	//	s.getSprite().draw(batch, t.getX(), t.getY(), t.getOriginX(),t.getOriginY(), t.getWidth(), 
	//						t.getHeight(), t.getScaleX(), t.getScaleY(), t.getRotation(), t.getHorizontalFlip(), t.getVerticalFlip());
	}
}