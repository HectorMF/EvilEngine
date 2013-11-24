package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector2;
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
	public void setPosition(float x, float y, float z) {
		this.z = z;
		body.setTransform(Pixel.toMeter(x), Pixel.toMeter(y), getRotation());
	}

	@Override
	public float getRotation() {
		return (float) Math.toDegrees(body.getAngle());
	}

	@Override
	public void setRotation(float rotation) {
		body.setTransform(body.getPosition().x, body.getPosition().y,
				(float) Math.toRadians(rotation));
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
	public void setDimension(float width, float height, float depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
	}

	@Override
	public void setSpatial(Spatial spatial) {
		setPosition(spatial.getX(), spatial.getY(), spatial.getZ());
		setRotation(spatial.getRotation());
		setDimension(spatial.getWidth(), spatial.getHeight(), spatial.getDepth());
	}

	@Override
	public void setX(float x) {
		body.getTransform().setPosition(new Vector2(Pixel.toMeter(x),body.getTransform().getPosition().y));
	}

	@Override
	public void setY(float y) {
		body.getTransform().setPosition(new Vector2(body.getTransform().getPosition().x,Pixel.toMeter(y)));
	}

	@Override
	public void setZ(float z) {
		this.z = z;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public void setDepth(float depth) {
		this.depth = depth;
	}

	@Override
	public Vector3 getDimension() {
		return new Vector3(width,height,depth);
	}
}
