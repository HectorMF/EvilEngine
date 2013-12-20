package com.perfectplay.org.graphics;

import java.util.HashSet;
import java.util.Iterator;

public class SpriteManager {
	private static HashSet<AnimatedSprite> sprites = new HashSet<AnimatedSprite>(); 
	
	public static void add(AnimatedSprite sprite){		
		sprites.add(sprite);
	}
	
	public static void remove(AnimatedSprite sprite){
		sprites.remove(sprite);
	}
	
	public static AnimatedSprite[] getSprites(){
		return sprites.toArray(new AnimatedSprite[sprites.size()]);
	}
	
	public static void update(float dt){
		Iterator<AnimatedSprite> iterator = sprites.iterator();
		while(iterator.hasNext()){
			AnimatedSprite sprite = iterator.next();
			sprite.update(dt);
		}
	}
}
