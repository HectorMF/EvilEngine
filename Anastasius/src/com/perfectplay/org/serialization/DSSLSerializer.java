package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.graphics.DepthSortedSpriteLayer;

public class DSSLSerializer extends Serializer<DepthSortedSpriteLayer>{

	@Override
	public DepthSortedSpriteLayer read(Kryo kryo, Input input, Class<DepthSortedSpriteLayer> type) {
		return new DepthSortedSpriteLayer(input.readInt());
	}

	@Override
	public void write(Kryo kryo, Output output, DepthSortedSpriteLayer object) {
		output.writeInt(object.getID());
	}

}
