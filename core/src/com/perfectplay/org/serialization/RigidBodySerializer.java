package com.perfectplay.org.serialization;

import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.RigidBody;

public class RigidBodySerializer extends Serializer<RigidBody> {

	@Override
	public RigidBody read(Kryo kryo, Input input, Class<RigidBody> type) {
		BodyType bType = kryo.readObject(input, BodyType.class);
		RigidBody body = new RigidBody(LevelSerializer.currentEntity, bType);

		int size = input.readInt();
		for (int i = 0; i < size; i++) {
			FixtureDef def = (FixtureDef) kryo.readClassAndObject(input);
			body.addFixture(def);
		}
		return body;
	}

	@Override
	public void write(Kryo kryo, Output output, RigidBody object) {
		kryo.writeObject(output, object.getBody().getType());
		output.writeInt(object.getFixtures().size());
		for (FixtureDef fixture : object.getFixtures()) {
			kryo.writeClassAndObject(output, fixture);
		}
	}

}
