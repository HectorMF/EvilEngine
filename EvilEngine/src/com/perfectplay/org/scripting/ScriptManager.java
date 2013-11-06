package com.perfectplay.org.scripting;


import com.artemis.Entity;

public final class ScriptManager {
	public static Script createScript(Entity e, Class<? extends Script> cScript){
			Script s;
			try {
				s = cScript.newInstance();
				s.setEntity(e);
				return s;
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
			return null;
	}
}
