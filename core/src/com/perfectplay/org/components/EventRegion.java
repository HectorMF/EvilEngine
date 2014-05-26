package com.perfectplay.org.components;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.perfectplay.org.scripting.delegates.RegionDelegate;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.utils.EntityBodyMapper;

public class EventRegion extends Component {
	private Body regions;
	//private ArrayList<FixtureDef> fixtures;

	public EventRegion(Entity entity) {
		super();
		//fixtures = new ArrayList<FixtureDef>();
		BodyDef bodDef = new BodyDef();
		bodDef.type = BodyType.KinematicBody;
		if (EntityBodyMapper.getInstance().hasBody(entity)) {
			regions = EntityBodyMapper.getInstance().getBody(entity);
		} else {
			regions = PhysicsSystem.createBody(bodDef);
			EntityBodyMapper.getInstance().registerBody(entity, regions);
		}
		regions.setUserData(entity);
	}

	public void addRegion(FixtureDef region, RegionDelegate event) {
		Fixture fixture = regions.createFixture(region);
		fixture.setUserData(event);
		// fixtures.add(fixture);
	}

	public static FixtureDef createPolygonRegion(Vector2[] vertices,
			short category, short group, short mask) {
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.groupIndex = group;
		fixtureDef.filter.maskBits = mask;
		return fixtureDef;
	}

	public static FixtureDef createCircleRegion(float radius, Vector2 position,
			short category, short group, short mask) {
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		shape.setPosition(position);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.groupIndex = group;
		fixtureDef.filter.maskBits = mask;
		return fixtureDef;
	}

	public static FixtureDef createBoxRegion(float width, float height,
			Vector2 position, float angle, short category, short group,
			short mask) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height, position, angle);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.isSensor = true;
		fixtureDef.filter.categoryBits = category;
		fixtureDef.filter.groupIndex = group;
		fixtureDef.filter.maskBits = mask;
		return fixtureDef;
	}

	public Body getBody() {
		return regions;
	}
}
