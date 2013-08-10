package com.perfectplay.org.utils;

import com.badlogic.gdx.math.Vector2;

public class Pixel {
	public static float toMeter(float pixels) {
		return (float) pixels * Meter.METERS_PER_PIXEL;
	}

	public static Vector2 toMeter(Vector2 vecPixel) {
		return new Vector2(Pixel.toMeter(vecPixel.x), Pixel.toMeter(vecPixel.y));
	}
}
