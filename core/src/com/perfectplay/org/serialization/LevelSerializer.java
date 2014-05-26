package com.perfectplay.org.serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.RigidBody;
import com.perfectplay.org.components.Scripts;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.graphics.Texture2D;
import com.perfectplay.org.level.DepthSortedSpriteLayer;
import com.perfectplay.org.level.Level;
import com.perfectplay.org.level.SpriteLayer;
import com.perfectplay.org.level.UnsortedSpriteLayer;

public class LevelSerializer extends Serializer<Level> {
	public static Entity currentEntity;
	private Kryo kryo;

	public LevelSerializer() {
		super();
		kryo = new Kryo();
		kryo.register(Level.class, this);
		// components and there respective serializers
		kryo.register(Renderable.class, new RenderableSerializer());
		kryo.register(RigidBody.class, new RigidBodySerializer());
		kryo.register(Transform.class, new SpatialSerializer());

		// utility serialization
		kryo.register(Texture2D.class, new Texture2DSerializer());
		kryo.register(DepthSortedSpriteLayer.class, new DSSLSerializer());
		kryo.register(UnsortedSpriteLayer.class, new USSLSerializer());
		kryo.register(FixtureDef.class, new FixtureDefSerializer());
		kryo.register(PolygonShape.class, new PolygonSerializer());
		kryo.register(CircleShape.class, new CircleSerializer());
		kryo.register(Scripts.class, new ScriptSerializer());
	}

	public void WriteLevel(Level level, String path) {
		try {
			Output output = new Output(new FileOutputStream(path));
			kryo.writeClassAndObject(output, level);
			output.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Level read(Kryo kryo, Input input, Class<Level> type) {
		float delta = input.readFloat();
		int width = input.readInt();
		int height = input.readInt();
		boolean doSleep = input.readBoolean();
		int bSize = input.readInt();
		float gravX = input.readFloat();
		float gravY = input.readFloat();
		Level level = new Level(width, height, bSize,
				new Vector2(gravX, gravY), doSleep);
		level.setDelta(delta);

		int layers = input.readInt();
		for (int k = 0; k < layers; k++) {
			level.addLayer((SpriteLayer) kryo.readClassAndObject(input));
		}

		long count = input.readLong();
		for (int i = 0; i < count; i++) {
			Entity e = level.createEntity();
			currentEntity = e;
			int components = input.readInt();
			for (int j = 0; j < components; j++) {
				Component c = (Component) kryo.readClassAndObject(input);
				if (c != null){
					e.addComponent(c);
				}
			}
			e.addToWorld();
		}
		return level;
	}

	@Override
	public void write(Kryo kryo, Output output, Level object) {

		output.writeFloat(object.getDelta());
		output.writeInt(object.getWidth());
		output.writeInt(object.getHeight());
		output.writeBoolean(object.isActivelySimulating());
		output.writeInt(object.getBucketSize());
		output.writeFloat(object.getGravity().x);
		output.writeFloat(object.getGravity().y);

		int layers = object.getLayerCount();
		output.writeInt(layers);
		for (int k = 0; k < layers; k++) {
			kryo.writeClassAndObject(output,
					object.getLayer(k));
		}
		long count = object.getEntityManager().getTotalCreated();
		ArrayList<Integer> activeEntities = new ArrayList<Integer>();

		for (int i = 0; i < count; i++) {
			if (object.getEntityManager().isActive(i)) {
				activeEntities.add(i);
			}
		}
		// output the number of entities
		// System.out.println(object.getEntityManager().getTotalAdded());
		output.writeLong(activeEntities.size());
		for (int i = 0; i < activeEntities.size(); i++) {
			Bag<Component> bag = new Bag<Component>();
			object.getEntity(i).getComponents(bag);
			// output the number of components this entity has
			output.writeInt(bag.size());
			for (int j = 0; j < bag.size(); j++) {
				// output the compononents
				kryo.writeClassAndObject(output, bag.get(j));
			}
		}

	}

	public Level ReadLevel(FileHandle internal) {
		Input input = new Input(internal.read());
		Level level = (Level) kryo.readClassAndObject(input);
		input.close();
		return level;
	}

}
