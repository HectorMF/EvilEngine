package com.perfectplay.org.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class AnimatedSprite implements ISprite {
	private List<Sprite> frames;
	private int interval;
	private int counter;
	private boolean playing;
	private boolean looping;
	private int currentFrame;

	public AnimatedSprite() {
		this(new ArrayList<Sprite>(), 0, false, false);
	}

	public AnimatedSprite(List<Sprite> frames, int interval) {
		this(frames, interval, true, true);
	}

	public AnimatedSprite(List<Sprite> frames, int interval, boolean playing, boolean looping) {
		this.frames = frames;
		this.interval = interval;
		this.looping = looping;
		this.playing = playing;
		this.currentFrame = 0;
	}

	@Override
	public void update(float dt) {
		if (!playing)
			return;
		if (interval < 0)
			return;
		// add the elapsed time since the last update
		counter += dt * 1000;
		// check to see if the frame delay has been reached

		if (interval <= counter) {
			step();
			// Subtract the interval from the counter, instead of setting
			// counter to 0
			// This creates more accurate frame delays, as delays between
			// updates vary.
			counter -= interval;
		}
	}

	private void step() {
		currentFrame += 1;

		if (currentFrame >= frames.size()) {
			if (looping)
				currentFrame = 0;
			else {
				playing = false;
				currentFrame -= 1;
			}
		}
	}

	@Override
	public void pause() {
		playing = false;
	}

	@Override
	public void play() {
		playing = true;
	}
	
	@Override
	public ISprite clone() {
		return new AnimatedSprite(frames,interval,playing,looping);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y, float scaleX,
			float scaleY, float rotation, boolean flipX, boolean flipY,
			Color color, float alpha) {
		frames.get(currentFrame).draw(batch, x, y, scaleX, scaleY, rotation, flipX, flipY, color, alpha);
	}
	
	@Override
	public void draw(SpriteBatch batch, float x, float y, float originX,
			float originY, float width, float height, float scaleX,
			float scaleY, float rotation, boolean flipX, boolean flipY,
			Color color, float alpha) {

		frames.get(currentFrame).draw(batch, x, y, originX, originY, width,
				height, scaleX, scaleY, rotation, flipX, flipY, color, alpha);
	}

	@Override
	public boolean isPlaying() {
		return playing;
	}

	@Override
	public boolean isLooping() {
		return looping;
	}

	@Override
	public boolean isAnimated() {
		return true;
	}

	@Override
	public void read(Kryo kryo, Input input) {
		int size = input.readInt();
		for (int i = 0; i < size; i++) {
			frames.add(kryo.readObject(input, Sprite.class));
		}
		interval = input.readInt();
		playing = input.readBoolean();
		looping = input.readBoolean();
	}

	@Override
	public void write(Kryo kryo, Output output) {
		output.writeInt(frames.size());
		for (int i = 0; i < frames.size(); i++) {
			kryo.writeObject(output, frames.get(i));
		}
		output.writeInt(interval);
		output.writeBoolean(playing);
		output.writeBoolean(looping);

	}
}
