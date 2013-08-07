package com.perfectplay.org.components;

import java.util.ArrayList;

import com.perfectplay.org.scripting.ScriptableComponent;
import com.perfectplay.org.scripting.delegates.SpatialDelegate;
import com.perfectplay.org.utils.Bucket;
import com.perfectplay.org.utils.Spatial;

public class SpatialComponent extends ScriptableComponent<SpatialDelegate>{
	private Spatial spatial;
	private ArrayList<Bucket> buckets;
	
	private boolean isDirty;
	
	public SpatialComponent(Spatial spatial){
		super(SpatialComponent.class, SpatialDelegate.class);
		this.spatial = spatial;
	}
	
	public Spatial getSpatial(){
		return spatial;
	}
	
	public void setSpatial(Spatial spatial){
		this.spatial = spatial;
	}
	
	public ArrayList<Bucket> getBuckets(){
		return buckets;
	}
	
	public boolean isDirty() {
		return isDirty;
	}
	
	public void setDirty(boolean dirty){
		this.isDirty = dirty;
	}
	
	public void setBuckets(ArrayList<Bucket> buckets){
		this.buckets = buckets;
	}
}
