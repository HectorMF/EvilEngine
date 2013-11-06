package com.perfectplay.org.scripting.delegates;

import com.perfectplay.org.scripting.Delegate;

public interface TransformDelegate extends Delegate {

	/*
	 * Called when the entity is moved. Passes in the spatial as well as the
	 * entity object.
	 */
	public abstract void onMove();

	/*
	 * Called when the entity rotates. Passes in the spatial as well as the
	 * entity object.
	 */
	public abstract void onRotate();

	/*
	 * Called when the entity doesn't move. Passes in the spatial as well as the
	 * entity object.
	 */
	public abstract void onStay();

}
