package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector3;

public class BodylessSpatial implements Spatial{
	private float x, y, z;
	private float width, height, depth;
	private float rotation;

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getZ() {
		return z;
	}

	@Override
	public Vector3 getPosition() {
		return new Vector3(x, y, z);
	}

	@Override
	public BodylessSpatial setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public BodylessSpatial setRotation(float rotation) {
		this.rotation = rotation;
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
	public BodylessSpatial setDimension(float width, float height, float depth) {
		this.width = width;
		this.height = height;
		this.depth = depth;
		return this;
	}

	@Override
	public BodylessSpatial setSpatial(Spatial spatial) {
		setPosition(spatial.getX(), spatial.getY(), spatial.getZ());
		setRotation(spatial.getRotation());
		setDimension(spatial.getWidth(), spatial.getHeight(), spatial.getDepth());
		return this;
	}

	@Override
	public BodylessSpatial setX(float x) {
		this.x = x;
		return this;
	}

	@Override
	public BodylessSpatial setY(float y) {
		this.y = y;
		return this;
	}

	@Override
	public BodylessSpatial setZ(float z) {
		this.z = z;
		return this;
	}

	@Override
	public BodylessSpatial setWidth(float width) {
		this.width = width;
		return this;
	}

	@Override
	public BodylessSpatial setHeight(float height) {
		this.height = height;
		return this;
	}

	@Override
	public BodylessSpatial setDepth(float depth) {
		this.depth = depth;
		return this;
	}

	@Override
	public Vector3 getDimension() {
		return new Vector3(width,height,depth);
	}
}
