package com.perfectplay.org.serialization;

import java.util.Collection;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.Scripts;
import com.perfectplay.org.scripting.Script;

public class ScriptSerializer  extends Serializer<Scripts> {

	@SuppressWarnings("unchecked")
	@Override
	public Scripts read(Kryo kryo, Input input, Class<Scripts> type) {
		int size = input.readInt();
		Scripts scripts = new Scripts();
		for(int i = 0; i < size; i++){
			scripts.add(kryo.readClass(input).getType());
		}
		return scripts;
	}

	@Override
	public void write(Kryo kryo, Output output, Scripts object) {
		Collection<Script> scripts = object.getScripts();
		output.writeInt(scripts.size());
		for(Script script : scripts){
			kryo.writeClass(output, script.getClass());
		}
	}

}
