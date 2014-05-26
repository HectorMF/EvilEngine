package com.perfectplay.org.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Texture2D extends Texture {
	private String path;
	private FileType type;

	public Texture2D(FileHandle file) {
		super(file);
		this.path = file.path();
		this.type = file.type();
	}

	public static FileHandle getFileHandle(String path, FileType type) {
		return Gdx.files.getFileHandle(path, type);
	}

	public String getPath() {
		return path;
	}

	public FileType getType() {
		return type;
	}
}
