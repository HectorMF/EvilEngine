package com.perfectplay.org;

import com.badlogic.gdx.Game;
import com.perfectplay.org.screens.MainMenuScreen;

public class AnastasiusGame extends Game{

	MainMenuScreen mainMenu;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		mainMenu = new MainMenuScreen(this);
		setScreen(mainMenu);
	}

}
