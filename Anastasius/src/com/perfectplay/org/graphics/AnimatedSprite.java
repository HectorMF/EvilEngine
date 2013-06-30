package com.perfectplay.org.graphics;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatedSprite implements ISprite{
	private List<Sprite> frames;
	private int interval;
	private int counter;
	private boolean playing;
	private boolean looping;
	private int currentFrame;

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
		counter += dt;
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
	public void draw(SpriteBatch batch, float x, float y, float originX,
					float originY, float width, float height, float scaleX,
					float scaleY, float rotation, boolean flipX, boolean flipY) {
		frames.get(currentFrame).draw(batch, x, y, originX, originY, width,
				height, scaleX, scaleY, rotation, flipX, flipY);
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
}
