package com.perfectplay.org.systems;

import java.util.ArrayList;
import java.util.HashMap;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.perfectplay.org.components.SpriteRender;
import com.perfectplay.org.components.Transform;
import com.perfectplay.org.utils.EntityComponentContainer;
import com.perfectplay.org.utils.Pixel;

public class SpriteRenderSystem extends EntitySystem {
	@Mapper ComponentMapper<Transform> transforms;
	@Mapper ComponentMapper<SpriteRender> sprites;
	
	private ArrayList<EntityComponentContainer<Transform>> entityContainer;
	
	private SpriteBatch batch;
	@SuppressWarnings("unchecked")
	public SpriteRenderSystem(SpriteBatch batch) {
		super(Aspect.getAspectForAll(Transform.class, SpriteRender.class));
		this.batch = batch;
		entityContainer = new ArrayList<EntityComponentContainer<Transform>>();
	}
	
	@Override
	protected void inserted(Entity e) {
		super.inserted(e);
		
		int i = 0;
		while(i < entityContainer.size() && entityContainer.get(i).getComponent().getZ() > transforms.get(e).getZ())
			i++;
		entityContainer.add(i, new EntityComponentContainer<Transform>(e,transforms.get(e)));
	}
	
	@Override
	protected void removed(Entity e) {
		super.removed(e);
		
		int i = 0;
		while(i < entityContainer.size() && entityContainer.get(i).getEntity() != e)
			i++;
		entityContainer.remove(i);
	}
	
	@Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
		for(int i = 1; i < entityContainer.size(); i++){
			EntityComponentContainer<Transform> ecContainer = entityContainer.get(i);
			float z = ecContainer.getComponent().getZ();
			int pos = i;
			
			while( pos > 0 && z < entityContainer.get(pos-1).getComponent().getZ()){
				entityContainer.set(pos, entityContainer.get(pos-1));
				pos--;
			}
			entityContainer.set(pos, ecContainer);
		}
		
    	for (int i = 0, s = entityContainer.size(); s > i; i++) {
    		process(entityContainer.get(i).getEntity());
        }
    }
    
	protected void process(Entity e) {
		Transform t = transforms.get(e);
		SpriteRender s = sprites.get(e);
		
		s.getSprite().update(world.delta*1000);

		s.getSprite().draw(batch, t.getScreenX() + s.getOffset().x, t.getScreenY() + s.getOffset().y, t.getOriginX(),t.getOriginY(), t.getWidth(), 
							t.getHeight(), t.getScaleX(), t.getScaleY(), t.getRotation(), t.getHorizontalFlip(), t.getVerticalFlip());
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}
}
