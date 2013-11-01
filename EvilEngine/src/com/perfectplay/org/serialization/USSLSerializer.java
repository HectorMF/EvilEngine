package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.level.UnsortedSpriteLayer;

public class USSLSerializer extends Serializer<UnsortedSpriteLayer> {

	@Override
	public UnsortedSpriteLayer read(Kryo kryo, Input input, Class<UnsortedSpriteLayer> type) {
		int id = input.readInt();
		UnsortedSpriteLayer layer = new UnsortedSpriteLayer(input.readFloat(), input.readFloat());
		layer.setID(id);
		return layer;
	}

	@Override
	public void write(Kryo kryo, Output output, UnsortedSpriteLayer object) {
		output.writeInt(object.getID());
		output.writeFloat(object.getParallaxSpeedX());
		output.writeFloat(object.getParallaxSpeedY());
	}

}
