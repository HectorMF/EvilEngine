package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.perfectplay.org.systems.PhysicsSystem;

public class CollisionRegions extends Component {
	private Body regions;
	public CollisionRegions(){
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		Body bod  = PhysicsSystem.createBody(bDef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1f, 0.1f);
		bod.createFixture(shape,0.1f);

		shape.setAsBox(0.1f, 1f,new Vector2(-1f + 0.1f, 1f), 0);
		bod.createFixture(shape, 0.1f);
		
		shape.setAsBox(0.1f, 1f, new Vector2(1f - 0.1f, 1f), 0);
		bod.createFixture(shape, 0.1f);
	}
	
	public static void AddPolygonRegion(Vector2[] vertices){
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		//fixtureDef.filter.
	}
	
	public static void AddCircleRegion(float radius, Vector2 position){
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		shape.setPosition(position);
	}
	
	public static void AddBoxRegion(float width, float height, Vector2 position, float angle){
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height, position, angle);
	}
	
	public Body getBody(){
		return regions;
	}
}
