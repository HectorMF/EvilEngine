package com.perfectplay.org;

import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.systems.BackgroundRenderSystem;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.systems.RegionSystem;
import com.perfectplay.org.systems.SpatialGridSystem;
import com.perfectplay.org.systems.SpriteRenderSystem;
import com.perfectplay.org.utils.SpatialGrid;

public class Level extends World {
	
	//sprite render systems
	private SpriteRenderSystem renderSystem;
	private BackgroundRenderSystem bgRender;
	private SpatialGridSystem spatialGridSystem;
	
	private int width,height;
	
	private SpatialGrid spatialGrid;
	
	public Level(int width, int height, int bucketSize, SpriteBatch batch, Vector2 gravity, boolean doSleep){
		
		this.width = width;
		this.height = height;
		this.spatialGrid = new SpatialGrid(width, height, bucketSize);
		//can grab these systems if later needed
		setSystem(new PhysicsSystem(new com.badlogic.gdx.physics.box2d.World(gravity, doSleep)), false);
		setSystem(new RegionSystem(), false);
		renderSystem = setSystem(new SpriteRenderSystem(batch), true);
		bgRender = setSystem(new BackgroundRenderSystem(batch), true);
		spatialGridSystem = setSystem(new SpatialGridSystem(spatialGrid), false);
	}
	
	@Override
	public void initialize(){
		super.initialize();
	}
	
	public static Level loadLevel(){
		return null;
	}
	
	public static void saveLevel(Level level){
		
	}
	
	public void render(){
		bgRender.process();
		renderSystem.process();
	}
	
	@Override
	public void process(){
		super.process();
		
		
	}

}
