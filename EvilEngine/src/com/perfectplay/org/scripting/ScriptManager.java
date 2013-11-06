package com.perfectplay.org.scripting;


import com.artemis.Entity;

public final class ScriptManager {
	public static void setScriptEntity(Entity entity, Script script){
		script.setEntity(entity);
	}
}
