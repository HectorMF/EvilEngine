package com.perfectplay.org.listeners;

import com.artemis.ComponentType;
import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.perfectplay.org.components.Scripts;
import com.perfectplay.org.scripting.Delegate;
import com.perfectplay.org.scripting.delegates.CollisionDelegate;
import com.perfectplay.org.scripting.delegates.RegionDelegate;

public class RegionContactListener implements ContactListener {
	private ZContactFilter dynamicFilter;

	public RegionContactListener() {
		dynamicFilter = new ZContactFilter();
	}

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		Entity entityA = (Entity) fixtureA.getBody().getUserData();
		Entity entityB = (Entity) fixtureB.getBody().getUserData();

		if (entityA == entityB)
			return;
		
		Delegate script = null;
		if(fixtureA.isSensor()){
			script = (Delegate) fixtureA.getUserData();
			if (script != null)
				((RegionDelegate) script).onRegionEnter();
		}else{
			Scripts sc = (Scripts) entityA.getComponent(ComponentType.getTypeFor(Scripts.class));
			if(sc != null){
				for(CollisionDelegate s : sc.getDelegates(CollisionDelegate.class)){
					s.onBeginCollision();
				}
			}
		}
		
		
		if(fixtureB.isSensor()){
			script = (Delegate) fixtureA.getUserData();
			if (script != null)
				((RegionDelegate) script).onRegionEnter();
		}else{
			Scripts sc = (Scripts) entityB.getComponent(ComponentType.getTypeFor(Scripts.class));
			if(sc != null){
				for(CollisionDelegate s : sc.getDelegates(CollisionDelegate.class)){
					s.onBeginCollision();
				}
			}
		}
		
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		if (!fixtureA.isSensor() & !fixtureB.isSensor())
			return;

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	//	Fixture fixtureA = contact.getFixtureA();
	//	Fixture fixtureB = contact.getFixtureB();
		//Entity entityA = (Entity) fixtureA.getBody().getUserData();
		//Entity entityB = (Entity) fixtureB.getBody().getUserData();
		boolean contactOccured = dynamicFilter.shouldCollide(
				contact.getFixtureA(), contact.getFixtureB());
		contact.setEnabled(contactOccured);
		if (contactOccured) {
			// float velocity =
			// entityA.getComponent(RigidBody.class).getZVelocity();
			// float velocity2 =
			// entityB.getComponent(RigidBody.class).getZVelocity();

			// fixtureA.get
		}


	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
