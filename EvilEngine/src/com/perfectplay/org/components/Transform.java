package com.perfectplay.org.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.perfectplay.org.utils.Bucket;
import com.perfectplay.org.utils.Spatial;

public class Transform extends Component{
	private Spatial spatial;
	private ArrayList<Bucket> buckets;

	private boolean isDirty;

	public Transform(Spatial spatial) {
		super();
		this.spatial = spatial;
	}

	public Spatial getSpatial() {
		return spatial;
	}

	public void setSpatial(Spatial spatial) {
		this.spatial = spatial;
	}

	public ArrayList<Bucket> getBuckets() {
		return buckets;
	}

	public boolean isDirty() {
		return isDirty;
	}

	public void setDirty(boolean dirty) {
		this.isDirty = dirty;
	}

	public void setBuckets(ArrayList<Bucket> buckets) {
		this.buckets = buckets;
	}
}
