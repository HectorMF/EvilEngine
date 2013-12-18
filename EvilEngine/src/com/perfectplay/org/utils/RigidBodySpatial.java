package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;

public class RigidBodySpatial implements Spatial {
	private Body body;
	private float z;
	private float width;
	private float height;
	private float depth;

	public RigidBodySpatial(Body body) {
		this.body = body;
		this.z = 0;
		this.width = 0;
		this.height = 0;
		this.depth = 0;
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
	public float getZ() {
		return z;
	}

	@Override
	public Vector3 getPosition() {
		return new Vector3(getX(), getY(), getZ());
	}

	@Override
	public RigidBodySpatial setPosition(float x, float y, float z) {
		this.z = z;
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
	public float getDepth() {
		return depth;
	}

	@Override
	public RigidBodySpatial setDimension(float width, float height, float depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		return this;
	}

	@Override
	public RigidBodySpatial setSpatial(Spatial spatial) {
		setPosition(spatial.getX(), spatial.getY(), spatial.getZ());
		setRotation(spatial.getRotation());
		setDimension(spatial.getWidth(), spatial.getHeight(), spatial.getDepth());
		return this;
	}

	@Override
	public RigidBodySpatial setX(float x) {
		setPosition(x,getY(),getZ());
		return this;
	}

	@Override
	public RigidBodySpatial setY(float y) {
		setPosition(getX(),y,getZ());
		return this;
	}

	@Override
	public RigidBodySpatial setZ(float z) {
		this.z = z;
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
	public RigidBodySpatial setDepth(float depth) {
		this.depth = depth;
		return this;
	}

	@Override
	public Vector3 getDimension() {
		return new Vector3(width,height,depth);
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
