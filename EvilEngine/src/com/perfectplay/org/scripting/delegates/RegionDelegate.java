package com.perfectplay.org.scripting.delegates;

import com.perfectplay.org.scripting.Delegate;

public interface RegionDelegate extends Delegate{
	void onRegionEnter();
	void onRegionLeave();
}
