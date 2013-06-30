
	package com.perfectplay.org.graphics;
	
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;

	public class Sprite implements ISprite{
		
		private int srcWidth, srcHeight;
		private int srcX, srcY;
		private Texture texture;
		
		public Sprite(Texture texture) {
			this(texture, 0, 0, texture.getWidth(), texture.getHeight());
		}
		public Sprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight){
			this.texture = texture;
			this.srcX = srcX;
			this.srcY = srcY;
			this.srcWidth = srcWidth;
			this.srcHeight = srcHeight;
		}

		public void draw(SpriteBatch batch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean flipX, boolean flipY) {
			batch.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		}
/*
		public void draw (SpriteBatch spriteBatch, float alphaModulation) {
			Color color = getColor();

			float oldAlpha = color.a;
			color.a *= alphaModulation;
			//setColor(color);
			//draw(spriteBatch);
			color.a = oldAlpha;
		//	setColor(color);
		}*/
		
		@Override
		public void update(float dt) {}
		
		@Override
		public void pause() {}
		
		@Override
		public void play() {}
		
		@Override
		public boolean isLooping() {
			return false;
		}
		
		@Override
		public boolean isAnimated() {
			return false;
		}
		
		@Override
		public boolean isPlaying() {
			return false;
		}
}
