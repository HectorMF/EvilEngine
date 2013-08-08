package com.perfectplay.org.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.perfectplay.org.scripting.Script;

public class Scriptable extends Component {
	private ArrayList<Script> scripts;

	public Scriptable() {
		this.scripts = new ArrayList<Script>();
	}

	public void addScript(Script script) {
		scripts.add(script);
	}

	public ArrayList<Script> getScripts() {
		return scripts;
	}
}
