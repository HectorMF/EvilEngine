package com.perfectplay.org.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.utils.BodylessSpatial;
import com.perfectplay.org.utils.Spatial;

public class SpatialSerializer extends Serializer<SpatialComponent> {

	@Override
	public SpatialComponent read(Kryo kryo, Input input,
			Class<SpatialComponent> type) {
		Spatial spatial = new BodylessSpatial();
		spatial.setPosition(input.readFloat(), input.readFloat(),
				input.readFloat());
		spatial.setRotation(input.readFloat());
		spatial.setSize(input.readFloat(), input.readFloat(), input.readFloat());
		return new SpatialComponent(spatial);
	}

	@Override
	public void write(Kryo kryo, Output output, SpatialComponent object) {
		// get objects inner spatial
		Spatial spatial = object.getSpatial();
		output.writeFloat(spatial.getX());
		output.writeFloat(spatial.getY());
		output.writeFloat(spatial.getZ());
		output.writeFloat(spatial.getRotation());
		output.writeFloat(spatial.getWidth());
		output.writeFloat(spatial.getHeight());
		output.writeFloat(spatial.getDepth());

	}

}
