package com.perfectplay.org.components;

import java.util.Collection;
import java.util.HashMap;

import aurelienribon.tweenengine.BaseTween;

import com.artemis.Component;

public class Tweens extends Component {
	private HashMap<String,BaseTween<?>> tweens;
	private HashMap<String,BaseTween<?>> active;
	
	public Tweens(){
		tweens = new HashMap<String,BaseTween<?>>();
		active = new HashMap<String,BaseTween<?>>(); 
	}
	
	public BaseTween<?> get(String name){
		return tweens.get(name);
	}
	
	public Tweens add(String name, BaseTween<?> tween){
		tweens.put(name, tween);
		return this;
	}
	
	public Tweens remove(String name){
		tweens.remove(name);
		return this;
	}
	
	public Tweens start(String name){
		BaseTween<?> temp = tweens.get(name);
		if(temp != null){
			temp.start();
			active.put(name, temp);
		}
		return this;
	}
	
	public Tweens stop(String name){
		BaseTween<?> temp = active.remove(name);
		if(temp != null)
			temp.kill();
		return this;
	}
	
	public Tweens pause(String name){
		BaseTween<?> temp = active.get(name);
		if(temp != null)
			temp.pause();
		return this;
	}
	
	public Tweens resume(String name){
		BaseTween<?> temp = active.get(name);
		if(temp != null)
			temp.resume();
		return this;
	}

	public Collection<BaseTween<?>> getActiveTweens(){
		return active.values();
	}
}
