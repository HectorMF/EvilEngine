package com.perfectplay.org.level;

import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.scripting.ScriptManager;
import com.perfectplay.org.systems.PhysicsSystem;
import com.perfectplay.org.systems.RegionSystem;
import com.perfectplay.org.systems.ScriptSystem;
import com.perfectplay.org.systems.SpatialGridSystem;
import com.perfectplay.org.systems.RenderSystem;
import com.perfectplay.org.systems.TweenSystem;
import com.perfectplay.org.utils.ParallaxCamera;
import com.perfectplay.org.utils.SpatialGrid;

public class Level extends World {

	// World Systems to be used in the level
	public final RenderSystem renderSystem;
	public final SpatialGridSystem spatialGridSystem;
	public final PhysicsSystem physicsSystem;
	public final RegionSystem regionSystem;
	public final ScriptSystem scriptSystem;
	public final TweenSystem tweenSystem;

	//parameters
	private int width, height;
	private boolean doSleep;
	private Vector2 gravity;
	private int bucketSize;
	
	public Level(int width, int height, int bucketSize, Vector2 gravity,
			boolean doSleep) {
		this.width = width;
		this.height = height;
		this.gravity = gravity;
		this.doSleep = doSleep;
		this.bucketSize = bucketSize;
		// can grab these systems if needed later
		physicsSystem = setSystem(new PhysicsSystem(
				new com.badlogic.gdx.physics.box2d.World(gravity, doSleep)),
				false);
		regionSystem = setSystem(new RegionSystem(), false);
		renderSystem = setSystem(new RenderSystem(null), false);
		SpatialGrid grid = new SpatialGrid(width, height, bucketSize);
		spatialGridSystem = setSystem(new SpatialGridSystem(grid), false);
		scriptSystem = setSystem(new ScriptSystem(), false);
		tweenSystem = setSystem(new TweenSystem(), false);
		this.setManager(new ScriptManager());
	}

	@Override
	public void initialize() {
		super.initialize();
	}

	public void setCamera(ParallaxCamera camera) {
		this.renderSystem.setCamera(camera);
		this.spatialGridSystem.setCamera(camera);
	}

	public void render() {
		renderSystem.process();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getBucketSize() {
		return bucketSize;
	}

	public Vector2 getGravity() {
		return gravity;
	}

	public boolean isActivelySimulating() {
		return doSleep;
	}
	
	public void addLayer(int index, SpriteLayer layer) {
		renderSystem.addLayer(index, layer);
	}

	public void addLayer(SpriteLayer layer) {
		renderSystem.addLayer(layer);
	}
	
	public void removeLayer(int index){
		renderSystem.removeLayer(index);
	}
	
	public SpriteLayer getLayer(int index){
		return renderSystem.getLayer(index);
	}
	
	public int getLayerCount() {
		return renderSystem.getLayerCount();
	}
	
	public void setSpriteBatch(SpriteBatch batch){
		renderSystem.setSpriteBatch(batch);
	}
	
	public void dispose(){
		this.dispose();
	}
}
