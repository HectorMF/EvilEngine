package com.perfectplay.org.serialization;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class FixtureDefSerializer extends Serializer<FixtureDef> {

	@Override
	public FixtureDef read(Kryo kryo, Input input, Class<FixtureDef> type) {
		FixtureDef def = new FixtureDef();
		def.density = input.readFloat();
		def.friction = input.readFloat();
		def.restitution = input.readFloat();
		def.isSensor = input.readBoolean();
		def.filter.categoryBits = input.readShort();
		def.filter.groupIndex = input.readShort();
		def.filter.maskBits = input.readShort();
		def.shape = (Shape) kryo.readClassAndObject(input);
		return def;
	}

	@Override
	public void write(Kryo kryo, Output output, FixtureDef object) {
		output.writeFloat(object.density);
		output.writeFloat(object.friction);
		output.writeFloat(object.restitution);
		output.writeBoolean(object.isSensor);
		output.writeShort(object.filter.categoryBits);
		output.writeShort(object.filter.groupIndex);
		output.writeShort(object.filter.maskBits);
		kryo.writeClassAndObject(output, object.shape);
	}
}
