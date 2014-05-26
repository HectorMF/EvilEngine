package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector2;

public class BodylessSpatial implements Spatial{
	private float x, y;
	private float width, height;
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
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}

	@Override
	public BodylessSpatial setPosition(float x, float y) {
		this.x = x;
		this.y = y;
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
	public BodylessSpatial setDimension(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public BodylessSpatial setSpatial(Spatial spatial) {
		setPosition(spatial.getX(), spatial.getY());
		setRotation(spatial.getRotation());
		setDimension(spatial.getWidth(), spatial.getHeight());
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
	public Vector2 getDimension() {
		return new Vector2(width, height);
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
