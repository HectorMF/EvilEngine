package com.perfectplay.org;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.perfectplay.org.EvilEngine;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Evil Engine v.01";
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		//cfg.useCPUSynch = false;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new EvilEngine(), cfg);
	}
}
