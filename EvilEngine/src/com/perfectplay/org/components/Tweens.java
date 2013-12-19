package com.perfectplay.org.components;

import java.util.ArrayList;
import java.util.HashMap;

import aurelienribon.tweenengine.BaseTween;

import com.artemis.Component;

public class Tweens extends Component {
	private HashMap<String,BaseTween<?>> tweens;
	private BaseTween<?> currentTween;
	
	public Tweens(){
		tweens = new HashMap<String,BaseTween<?>>();
	}
	
	public Tweens addTween(String name, BaseTween<?> tween){
		tweens.put(name, tween);
		return this;
	}
	
	public Tweens removeTween(String name){
		tweens.remove(name);
		return this;
	}
	
	public BaseTween<?> getCurrentTween(){
		return currentTween;
	}
	
	public Tweens setCurrentTween(String name){
		currentTween = tweens.get(name);
		return this;
	}
	
	public ArrayList<BaseTween<?>> getTweens(){
		return new ArrayList<BaseTween<?>>(tweens.values());
	}
}
