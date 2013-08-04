package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public interface Spatial {
	
	float getX();

	float getY();

	float getZ();
	
	Vector2 getScreenPosition();
	
	Vector3 getPosition();

	void setPosition(float x, float y, float z);

	float getRotation();

	void setRotation(float rotation);

	float getWidth();

	float getHeight();
	
	float getDepth();

	void setSize(float width, float height, float depth);
	
	void setSpatial(Spatial spatial);

}
