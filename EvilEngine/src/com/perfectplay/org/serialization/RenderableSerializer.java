package com.perfectplay.org.serialization;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.graphics.ISprite;

public class RenderableSerializer extends Serializer<Renderable> {

	@Override
	public Renderable read(Kryo kryo, Input input, Class<Renderable> type) {
		int layer = input.readInt();
		ISprite sprite = (ISprite) kryo.readClassAndObject(input);
		Renderable render = new Renderable(sprite);
		render.setLayer(layer);
		render.setWidth(input.readFloat());
		render.setHeight(input.readFloat());
		render.setScaleX(input.readFloat());
		render.setScaleY(input.readFloat());
		render.setOffset(new Vector2(input.readFloat(), input.readFloat()));

		render.setColor(new Color(input.readFloat(), input.readFloat(), input
				.readFloat(), input.readFloat()));

		render.setAlpha(input.readFloat());

		render.setOrigin(new Vector2(input.readFloat(), input.readFloat()));

		render.setHorizontalFlip(input.readBoolean());
		render.setVerticalFlip(input.readBoolean());

		return render;
	}

	@Override
	public void write(Kryo kryo, Output output, Renderable object) {
		output.writeInt(object.getLayer());
		kryo.writeClassAndObject(output, object.getSprite());
		output.writeFloat(object.getWidth());
		output.writeFloat(object.getHeight());
		output.writeFloat(object.getScaleX());
		output.writeFloat(object.getScaleY());
		output.writeFloat(object.getOffset().x);
		output.writeFloat(object.getOffset().y);

		output.writeFloat(object.getColor().r);
		output.writeFloat(object.getColor().g);
		output.writeFloat(object.getColor().b);
		output.writeFloat(object.getColor().a);

		output.writeFloat(object.getAlpha());

		output.writeFloat(object.getOrigin().x);
		output.writeFloat(object.getOrigin().y);

		output.writeBoolean(object.getHorizontalFlip());
		output.writeBoolean(object.getVerticalFlip());

	}

}
