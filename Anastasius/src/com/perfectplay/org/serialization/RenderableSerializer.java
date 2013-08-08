package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.graphics.ISprite;

public class RenderableSerializer extends Serializer<Renderable>{
	
	@Override
	public Renderable read(Kryo kryo, Input input, Class<Renderable> type) {
		int layer = input.readInt();
		ISprite sprite = (ISprite)kryo.readClassAndObject(input);
		
		return new Renderable(sprite, layer);
	}

	@Override
	public void write(Kryo kryo, Output output, Renderable object) {
		output.writeInt(object.getSpriteLayer());
		kryo.writeClassAndObject(output, object.getSprite());
		
	}


}
