package com.perfectplay.org.components;

import java.util.ArrayList;

import com.perfectplay.org.scripting.Component;
import com.perfectplay.org.scripting.delegates.TransformDelegate;
import com.perfectplay.org.utils.Bucket;
import com.perfectplay.org.utils.Spatial;

public class Transform extends Component<TransformDelegate> {
	private Spatial spatial;
	private ArrayList<Bucket> buckets;

	private boolean isDirty;

	public Transform(Spatial spatial) {
		super(Transform.class, TransformDelegate.class);
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
