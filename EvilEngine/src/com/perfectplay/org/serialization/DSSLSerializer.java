package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.level.DepthSortedSpriteLayer;

public class DSSLSerializer extends Serializer<DepthSortedSpriteLayer> {

	@Override
	public DepthSortedSpriteLayer read(Kryo kryo, Input input, Class<DepthSortedSpriteLayer> type) {
		int id = input.readInt();
		DepthSortedSpriteLayer layer = new DepthSortedSpriteLayer(input.readFloat(), input.readFloat());
		layer.setID(id);
		return layer;
	}

	@Override
	public void write(Kryo kryo, Output output, DepthSortedSpriteLayer object) {
		output.writeInt(object.getID());
		output.writeFloat(object.getParallaxSpeedX());
		output.writeFloat(object.getParallaxSpeedY());
	}

}
