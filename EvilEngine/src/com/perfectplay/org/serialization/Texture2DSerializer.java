package com.perfectplay.org.serialization;

import com.badlogic.gdx.Files.FileType;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.perfectplay.org.graphics.Texture2D;

public class Texture2DSerializer extends Serializer<Texture2D> {

	@Override
	public Texture2D read(Kryo kryo, Input input, Class<Texture2D> type) {
		FileType fType = kryo.readObject(input, FileType.class);
		String path = input.readString();
		return new Texture2D(Texture2D.getFileHandle(path, fType));
	}

	@Override
	public void write(Kryo kryo, Output output, Texture2D object) {
		kryo.writeObject(output, object.getType());
		output.writeString(object.getPath());
	}

}
