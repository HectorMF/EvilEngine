package com.perfectplay.org.utils;

public class Meter {
	public static final float METERS_PER_PIXEL = 0.01f;

	public static float toPixel(float meter) {
		return (float) ((float) meter / METERS_PER_PIXEL);
	}
}
