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
import com.perfectplay.org.components.SpatialComponent;
import com.perfectplay.org.graphics.SpriteLayer;

public class RenderSystem extends EntitySystem {
	@Mapper ComponentMapper<SpatialComponent> spatialComponents;
	@Mapper ComponentMapper<Renderable> renderables;
	
	private ArrayList<SpriteLayer> layers;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public RenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(SpatialComponent.class, Renderable.class));
		this.batch = batch;
		this.layers = new ArrayList<SpriteLayer>();
	}
	
	public void addLayer(int pos, SpriteLayer layer){
		layers.add(pos, layer);
	}
	
	public void addLayer(SpriteLayer layer){
		layers.add(layer.getID(), layer);
	}
	
	public SpriteLayer getLayer(int id){
		return layers.get(id);
	}
	
	public int getLayerCount(){
		return layers.size();
	}
	
	public void setSpriteBatch(SpriteBatch batch){
		this.batch = batch;
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		getLayer(renderables.get(e).getSpriteLayer()).add(renderables.get(e), spatialComponents.get(e));
	}
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		getLayer(renderables.get(e).getSpriteLayer()).remove(renderables.get(e), spatialComponents.get(e));
	}
	
	@Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
		for(SpriteLayer layer: layers){
			layer.update(world.getDelta());
			layer.render(batch);
		}
    }
    
	protected void process(Entity e){
		renderables.get(e).getSprite().update(world.delta*1000);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
