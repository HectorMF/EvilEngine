package com.perfectplay.org.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Sprite implements ISprite {

	private int srcWidth, srcHeight;
	private int srcX, srcY;
	private Texture2D texture;

	public Sprite() {
		this(null, 0, 0, 0, 0);
	}

	public Sprite(Texture2D texture) {
		this(texture, 0, 0, texture.getWidth(), texture.getHeight());
	}

	public Sprite(Texture2D texture, int srcX, int srcY, int srcWidth,
			int srcHeight) {
		this.texture = texture;
		this.srcX = srcX;
		this.srcY = srcY;
		this.srcWidth = srcWidth;
		this.srcHeight = srcHeight;
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y, float scaleX,
			float scaleY, float rotation, boolean flipX, boolean flipY,
			Color color, float alpha) {
		Color previousColor = batch.getColor();
		color.a = alpha;
		batch.setColor(color);
		batch.draw(texture, x - srcWidth/2, y - srcHeight/2, srcWidth/2, srcWidth/2, srcWidth, srcHeight, scaleX, scaleY, 
				rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		batch.setColor(previousColor);
		
	}
	
	@Override
	public void draw(SpriteBatch batch, float x, float y, float originX,
			float originY, float width, float height, float scaleX,
			float scaleY, float rotation, boolean flipX, boolean flipY,
			Color color, float alpha) {

		Color previousColor = batch.getColor();
		color.a = alpha;
		batch.setColor(color);
		batch.draw(texture, x - width/2, y - height/2, originX, originY, width, height, scaleX,
				scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		batch.setColor(previousColor);
	}

	@Override
	public void update(float dt) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void play() {
	}

	@Override
	public boolean isLooping() {
		return false;
	}

	@Override
	public boolean isAnimated() {
		return false;
	}

	@Override
	public boolean isPlaying() {
		return false;
	}

	@Override
	public ISprite clone() {
		return this;
	}

	@Override
	public void read(Kryo kryo, Input input) {
		this.srcX = input.readInt();
		this.srcY = input.readInt();
		this.srcWidth = input.readInt();
		this.srcHeight = input.readInt();
		this.texture = kryo.readObject(input, Texture2D.class);
	}

	@Override
	public void write(Kryo kryo, Output output) {
		output.writeInt(srcX);
		output.writeInt(srcY);
		output.writeInt(srcWidth);
		output.writeInt(srcHeight);
		kryo.writeObject(output, texture);

	}
}
