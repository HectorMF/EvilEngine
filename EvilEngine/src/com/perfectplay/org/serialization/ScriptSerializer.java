package com.perfectplay.org.serialization;

import java.util.Collection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.Scripting;
import com.perfectplay.org.scripting.Script;

public class ScriptSerializer  extends Serializer<Scripting> {

	@SuppressWarnings("unchecked")
	@Override
	public Scripting read(Kryo kryo, Input input, Class<Scripting> type) {
		int size = input.readInt();
		Scripting scripts = new Scripting();
		for(int i = 0; i < size; i++){
			scripts.addScript(kryo.readClass(input).getType());
		}
		return scripts;
	}

	@Override
	public void write(Kryo kryo, Output output, Scripting object) {
		Collection<Script> scripts = object.getScripts();
		output.writeInt(scripts.size());
		for(Script script : scripts){
			kryo.writeClass(output, script.getClass());
		}
	}

}
