package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.Spatial;

public class SpatialSerializer extends Serializer<Transform> {

	@Override
	public Transform read(Kryo kryo, Input input,
			Class<Transform> type) {
		Transform temp = new Transform();
		temp.setPosition(input.readFloat(), input.readFloat());
		temp.setRotation(input.readFloat());
		temp.setDimension(input.readFloat(), input.readFloat());
		return temp;
	}

	@Override
	public void write(Kryo kryo, Output output, Transform object) {
		// get objects inner spatial
		Spatial spatial = object;
		output.writeFloat(spatial.getX());
		output.writeFloat(spatial.getY());
		output.writeFloat(spatial.getRotation());
		output.writeFloat(spatial.getWidth());
		output.writeFloat(spatial.getHeight());

	}

}
