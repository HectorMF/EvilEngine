package com.perfectplay.org;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.perfectplay.org.Anastasius;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Anastasius";
		cfg.useGL20 = false;
		cfg.width = 800;
		cfg.height = 480;
		
		new LwjglApplication(new Anastasius(), cfg);
	}
}
