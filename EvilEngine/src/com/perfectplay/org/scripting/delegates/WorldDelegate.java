package com.perfectplay.org.scripting.delegates;

import com.perfectplay.org.scripting.Delegate;

public interface WorldDelegate extends Delegate {
	void onUpdate();
	
	void onDisable();
	void onEnable();
	void onRemove();
	void onAdd();
}
