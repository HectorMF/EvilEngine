package com.perfectplay.org.serialization;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class CircleSerializer extends Serializer<CircleShape> {

	@Override
	public CircleShape read(Kryo kryo, Input input, Class<CircleShape> type) {
		CircleShape shape = new CircleShape();
		shape.setRadius(input.readFloat());
		shape.setPosition(new Vector2(input.readFloat(), input.readFloat()));
		return shape;
	}

	@Override
	public void write(Kryo kryo, Output output, CircleShape object) {
		output.writeFloat(object.getRadius());
		output.writeFloat(object.getPosition().x);
		output.writeFloat(object.getPosition().y);
	}

}
