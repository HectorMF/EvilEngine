package com.perfectplay.org;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.RigidBody;

public class RigidBodySerializer  extends Serializer<RigidBody>{

	@Override
	public RigidBody read(Kryo kryo, Input input, Class<RigidBody> type) {
		return null;
	}

	@Override
	public void write(Kryo kryo, Output output, RigidBody object) {

		Body body = object.getBody();
		output.writeInt(body.getFixtureList().size());
		for(Fixture fixture : body.getFixtureList()){
			//fixture.
		}
	}

}
