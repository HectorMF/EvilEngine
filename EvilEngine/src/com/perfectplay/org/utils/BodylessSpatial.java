package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector3;

public class BodylessSpatial extends Spatial{
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
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(float rotation) {
		this.rotation = rotation;
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
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
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
