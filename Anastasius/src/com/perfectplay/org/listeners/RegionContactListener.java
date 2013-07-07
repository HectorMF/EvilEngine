package com.perfectplay.org.listeners;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.events.CollisionEvent;

public class RegionContactListener implements ContactListener{
	private ZContactFilter dynamicFilter;
	public RegionContactListener(){
		dynamicFilter = new ZContactFilter();
	}
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		if(!fixtureA.isSensor() & !fixtureB.isSensor())
			return;
		
		Entity entityA = (Entity)fixtureA.getBody().getUserData();
		Entity entityB = (Entity)fixtureB.getBody().getUserData();
		
		if(entityA  == entityB)
			return;
			CollisionEvent collisionEvent = null;
			
			if(fixtureA.isSensor()){
				collisionEvent = (CollisionEvent) fixtureA.getUserData();
			}else{
				collisionEvent = (CollisionEvent) fixtureB.getUserData();
			}
			
			if(collisionEvent != null){
				collisionEvent.beginCollision((Entity)fixtureA.getBody().getUserData(), (Entity)fixtureB.getBody().getUserData());
			}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		if(!fixtureA.isSensor() & !fixtureB.isSensor())
			return;
		
		CollisionEvent collisionEvent = null;
		
		if(fixtureA.isSensor()){
			collisionEvent = (CollisionEvent) fixtureA.getUserData();
		}else{
			collisionEvent = (CollisionEvent) fixtureB.getUserData();
		}
		
		if(collisionEvent != null){
			collisionEvent.endCollision((Entity)fixtureA.getBody().getUserData(), (Entity)fixtureB.getBody().getUserData());
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		Entity entityA = (Entity)fixtureA.getBody().getUserData();
		Entity entityB = (Entity)fixtureB.getBody().getUserData();
		contact.setEnabled(dynamicFilter.shouldCollide(contact.getFixtureA(), contact.getFixtureB()));
		if(entityA.getComponent(Transform.class).isDirty() || entityB.getComponent(Transform.class).isDirty()){

		}
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}
	

}
