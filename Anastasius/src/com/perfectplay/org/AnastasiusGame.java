package com.perfectplay.org;

import com.badlogic.gdx.Game;
import com.perfectplay.org.screens.GameScreen;
import com.perfectplay.org.screens.MainMenuScreen;

public class AnastasiusGame extends Game{

	MainMenuScreen mainMenu;
	GameScreen gameScreen;
	@Override
	public void create() {
		// TODO Auto-generated method stub
		mainMenu = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

}
