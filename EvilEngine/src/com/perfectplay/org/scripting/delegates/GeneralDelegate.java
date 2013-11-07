package com.perfectplay.org.scripting.delegates;

import com.perfectplay.org.scripting.Delegate;

public interface GeneralDelegate extends Delegate {
	void onUpdate();

	void onDisable();
	void onEnable();
}
