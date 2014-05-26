package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector2;

public interface Spatial{

	public abstract float getX();

	public abstract float getY();
	
	public abstract Spatial setX(float x);

	public abstract Spatial setY(float y);

	public abstract Vector2 getPosition();

	public abstract Spatial setPosition(float x, float y);

	public abstract float getRotation();

	public abstract Spatial setRotation(float rotation);

	public abstract float getWidth();

	public abstract float getHeight();
	
	public abstract Spatial setWidth(float width);

	public abstract Spatial setHeight(float height);
	
	public abstract Vector2 getDimension();

	public abstract Spatial setDimension(float width, float height);

	public abstract Spatial setSpatial(Spatial spatial);
	
	public abstract boolean equals(Spatial spatial);
}
