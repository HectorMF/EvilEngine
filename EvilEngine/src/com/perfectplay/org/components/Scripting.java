package com.perfectplay.org.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.artemis.Component;
import com.perfectplay.org.scripting.Delegate;
import com.perfectplay.org.scripting.Script;

public class Scripting extends Component {
	private HashMap<Class<? extends Script>, Script> scripts;
	
	public Scripting() {
		this.scripts = new HashMap<Class<? extends Script>,Script>();
	}

	public void addScript(Class<? extends Script> classScript) {
		try {
			Script script = classScript.newInstance();
			script.initialize(); 
			this.scripts.put(classScript, script);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void removeScript(Class<? extends Script> classScript) {
		this.scripts.remove(classScript);
	}
	
	public int size(){
		return scripts.size();
	}
	
	public Script getScript(Class<? extends Script> classScript){
		return scripts.get(classScript);
	}
	
	public Collection<Script> getDelegates(Class<? extends Delegate> classDelegate){
		Collection<Script> temp = new ArrayList<Script>();
		for(Script s : scripts.values()){
			if(classDelegate.isInstance(s)){
				temp.add(s);
			}
		}
		return temp;
	}
	
	public Collection<Script> getScripts(){
		return  scripts.values();
	}
}
