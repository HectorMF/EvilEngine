package com.perfectplay.org.systems;

import java.util.ArrayList;
import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.graphics.SpriteManager;
import com.perfectplay.org.level.SpriteLayer;
import com.perfectplay.org.utils.ParallaxCamera;

public class RenderSystem extends EntitySystem {
	@Mapper
	ComponentMapper<Renderable> renderables;

	private ArrayList<SpriteLayer> layers;

	private SpriteBatch batch;
	
	private ParallaxCamera camera;
	
	@SuppressWarnings("unchecked")
	public RenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(Transform.class, Renderable.class));
		this.batch = batch;
		this.layers = new ArrayList<SpriteLayer>();
	}

	public void addLayer(int index, SpriteLayer layer) {
		layers.add(index, layer);
	}

	public void addLayer(SpriteLayer layer) {
		layers.add(layer);
	}
	
	public void removeLayer(int index){
		layers.remove(index);
	}
	
	public SpriteLayer getLayer(int index){
		return layers.get(index);
	}
	
	public SpriteLayer findLayer(int id){
		for(int i = 0; i < layers.size(); i++){
			if(layers.get(i).getID() == id)
				return layers.get(i);
		}
		return null;
	}

	public int getLayerCount() {
		return layers.size();
	}

	public void setCamera(ParallaxCamera camera) {
		this.camera = camera;
	}
	
	public void setSpriteBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		SpriteLayer layer = findLayer(renderables.get(e).getLayer());
		if(layer != null)
			layer.add(e);
	}

	@Override
	protected void removed(Entity e) {
		super.removed(e);
		SpriteLayer layer = findLayer(renderables.get(e).getLayer());
		if(layer != null)
			layer.remove(e);
	}

	@Override
	protected final void processEntities(ImmutableBag<Entity> entities) {
		SpriteManager.update(world.getDelta());
		for (SpriteLayer layer : layers) {
			layer.update(world.getDelta());
			batch.setProjectionMatrix(camera.calculateParallaxMatrix(layer.getParallaxSpeedX(), layer.getParallaxSpeedY()));
			batch.begin();
			layer.render(batch);
			batch.end();
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
