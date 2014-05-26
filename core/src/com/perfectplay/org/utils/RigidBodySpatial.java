package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class RigidBodySpatial implements Spatial {
	private Body body;
	
	private float width;
	private float height;

	public RigidBodySpatial(Body body) {
		this.body = body;
		this.width = 0;
		this.height = 0;
	}

	@Override
	public float getX() {
		return Meter.toPixel(body.getTransform().getPosition().x);
	}

	@Override
	public float getY() {
		return Meter.toPixel(body.getTransform().getPosition().y);
	}

	@Override
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	@Override
	public RigidBodySpatial setPosition(float x, float y) {
		body.setTransform(Pixel.toMeter(x), Pixel.toMeter(y), (float)Math.toRadians(getRotation()));
		return this;
	}

	@Override
	public float getRotation() {
		return (float) Math.toDegrees(body.getAngle());
	}

	@Override
	public RigidBodySpatial setRotation(float rotation) {
		body.setTransform(body.getPosition().x, body.getPosition().y, (float) Math.toRadians(rotation));
		return this;
	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public RigidBodySpatial setDimension(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public RigidBodySpatial setSpatial(Spatial spatial) {
		setPosition(spatial.getX(), spatial.getY());
		setRotation(spatial.getRotation());
		setDimension(spatial.getWidth(), spatial.getHeight());
		return this;
	}

	@Override
	public RigidBodySpatial setX(float x) {
		setPosition(x, getY());
		return this;
	}

	@Override
	public RigidBodySpatial setY(float y) {
		setPosition(getX(), y);
		return this;
	}

	@Override
	public RigidBodySpatial setWidth(float width) {
		this.width = width;
		return this;
	}

	@Override
	public RigidBodySpatial setHeight(float height) {
		this.height = height;
		return this;
	}

	@Override
	public Vector2 getDimension() {
		return new Vector2(width,height);
	}
	
	@Override
	public boolean equals(Spatial spatial) {
		if(getPosition().equals(spatial.getPosition()))
			if(getDimension().equals(spatial.getDimension()))
				if(getRotation() == spatial.getRotation())
					return true;
		return false;
	}
}
