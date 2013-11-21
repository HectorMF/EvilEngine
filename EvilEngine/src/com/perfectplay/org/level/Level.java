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
import com.perfectplay.org.utils.ParallaxCamera;
import com.perfectplay.org.utils.SpatialGrid;

public class Level extends World {

	// World Systems to be used in the level
	private RenderSystem renderSystem;
	private SpatialGridSystem spatialGridSystem;
	private PhysicsSystem physicsSystem;
	private RegionSystem regionSystem;
	private ScriptSystem scriptSystem;

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
				true);
		regionSystem = setSystem(new RegionSystem(), true);
		renderSystem = setSystem(new RenderSystem(null), true);
		SpatialGrid grid = new SpatialGrid(width, height, bucketSize);
		spatialGridSystem = setSystem(new SpatialGridSystem(grid), true);
		scriptSystem = setSystem(new ScriptSystem(), false);
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

	@Override
	public void process() {
		super.process();
		spatialGridSystem.process();
		physicsSystem.process();
		regionSystem.process();
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

	public RenderSystem getRenderSystem() {
		return renderSystem;
	}

	public PhysicsSystem getPhysicsSystem() {
		return physicsSystem;
	}

	public RegionSystem getRegionSystem() {
		return regionSystem;
	}

	public ScriptSystem getScriptSystem() {
		return scriptSystem;
	}

	public SpatialGridSystem getSpatialSystem() {
		return spatialGridSystem;
	}
	
	public void setSpriteBatch(SpriteBatch batch){
		renderSystem.setSpriteBatch(batch);
	}
	
	public void dispose(){
		this.dispose();
	}
}
