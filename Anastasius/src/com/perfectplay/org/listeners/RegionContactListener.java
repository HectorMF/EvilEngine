package com.perfectplay.org.listeners;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.perfectplay.org.events.CollisionEvent;

public class RegionContactListener implements ContactListener{

	public RegionContactListener(){
		
	}
	
	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		CollisionEvent collisionEvent;
		if(fixtureA.isSensor()){
			collisionEvent = (CollisionEvent) fixtureA.getUserData();
		}else{
			collisionEvent = (CollisionEvent) fixtureB.getUserData();
		}
		if(collisionEvent.occursOnCollide()){
			collisionEvent.Fire();
		}	
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		CollisionEvent collisionEvent;
		if(fixtureA.isSensor()){
			collisionEvent = (CollisionEvent) fixtureA.getUserData();
		}else{
			collisionEvent = (CollisionEvent) fixtureB.getUserData();
		}
		if(!collisionEvent.occursOnCollide()){
			collisionEvent.Fire();
		}	
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}
	

}
