package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector3;

public interface Spatial{

	public abstract float getX();

	public abstract float getY();

	public abstract float getZ();
	
	public abstract void setX(float x);

	public abstract void setY(float y);

	public abstract void setZ(float z);

	public abstract Vector3 getPosition();

	public abstract void setPosition(float x, float y, float z);

	public abstract float getRotation();

	public abstract void setRotation(float rotation);

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract float getDepth();
	
	public abstract void setWidth(float width);

	public abstract void setHeight(float height);

	public abstract void setDepth(float depth);
	
	public abstract Vector3 getDimension();

	public abstract void setDimension(float width, float height, float depth);

	public abstract void setSpatial(Spatial spatial);
}
