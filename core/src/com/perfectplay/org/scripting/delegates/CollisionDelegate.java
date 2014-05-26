package com.perfectplay.org.scripting.delegates;

import com.perfectplay.org.scripting.Delegate;

public interface CollisionDelegate extends Delegate{
	void onBeginCollision();

	void onEndCollision();
}
