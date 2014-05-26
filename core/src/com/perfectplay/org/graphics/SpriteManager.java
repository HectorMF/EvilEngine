package com.perfectplay.org.graphics;

import java.util.HashMap;
import java.util.Iterator;

public class SpriteManager {
	private static HashMap<String,ISprite> sprites = new HashMap<String,ISprite>(); 
	
	public static void register(String name, ISprite sprite){		
		sprites.put(name, sprite);
	}
	
	public static void remove(String name){
		sprites.remove(name);
	}
	
	public static AnimatedSprite[] getSprites(){
		return sprites.values().toArray(new AnimatedSprite[sprites.size()]);
	}
	
	public static ISprite get(String name){
		return sprites.get(name);
	}
	
	public static void update(float dt){
		Iterator<ISprite> iterator = sprites.values().iterator();
		while(iterator.hasNext()){
			ISprite sprite = iterator.next();
			sprite.update(dt);
		}
	}
}
