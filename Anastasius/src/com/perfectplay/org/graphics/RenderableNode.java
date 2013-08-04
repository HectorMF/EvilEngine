package com.perfectplay.org.graphics;

import com.perfectplay.org.components.Renderable;
import com.perfectplay.org.components.SpatialComponent;

public class RenderableNode implements Comparable<RenderableNode>{
	private Renderable renderable;
	private SpatialComponent spatialComponent;
	
	public RenderableNode(Renderable renderable, SpatialComponent spatialComponent){
		this.renderable = renderable;
		this.spatialComponent = spatialComponent;
	}
	
	public Renderable getRenderable(){
		return renderable;
	}
	
	public SpatialComponent getSpatialComponent(){
		return spatialComponent;
	}
	
	@Override
	public int compareTo(RenderableNode arg0) {
		float val = spatialComponent.getSpatial().getZ() - arg0.getSpatialComponent().getSpatial().getZ();
		if(val < 0) return -1;
		if(val > 0) return 1;
		return 0;
	}
}