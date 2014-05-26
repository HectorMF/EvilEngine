package com.perfectplay.org.serialization;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class PolygonSerializer extends Serializer<PolygonShape> {

	@Override
	public PolygonShape read(Kryo kryo, Input input, Class<PolygonShape> type) {
		int count = input.readInt();
		Vector2[] vertices = new Vector2[count];
		for (int i = 0; i < count; i++) {
			vertices[i] = new Vector2();
			vertices[i].x = input.readFloat();
			vertices[i].y = input.readFloat();
		}
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		return shape;
	}

	@Override
	public void write(Kryo kryo, Output output, PolygonShape object) {
		int count = object.getVertexCount();
		output.writeInt(count);
		for (int i = 0; i < count; i++) {
			Vector2 vertex = new Vector2();
			object.getVertex(i, vertex);
			output.writeFloat(vertex.x);
			output.writeFloat(vertex.y);
		}
	}

}
