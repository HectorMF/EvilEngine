package com.perfectplay.org.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryo.KryoSerializable;

public interface ISprite extends KryoSerializable {
	
	void update(float dt);

	void draw(SpriteBatch batch, float x, float y, float scaleX, float scaleY,
			float rotation, boolean flipX, boolean flipY,
			Color color, float alpha);
	
	// over-parameterized draw calls ftw.
	void draw(SpriteBatch batch, float x, float y, float originX,
			float originY, float width, float height, float scaleX,
			float scaleY, float rotation, boolean flipX, boolean flipY,
			Color color, float alpha);

	// pauses the animation on the currently displayed frame
	void pause();

	// plays the sprite's animation sequence
	void play();

	boolean isLooping();

	boolean isAnimated();

	boolean isPlaying();
	
	ISprite clone();
	
}
