package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.graphics.UnsortedSpriteLayer;

public class USSLSerializer extends Serializer<UnsortedSpriteLayer> {

	@Override
	public UnsortedSpriteLayer read(Kryo kryo, Input input,
			Class<UnsortedSpriteLayer> type) {
		return new UnsortedSpriteLayer(input.readInt());
	}

	@Override
	public void write(Kryo kryo, Output output, UnsortedSpriteLayer object) {
		output.writeInt(object.getID());
	}

}
