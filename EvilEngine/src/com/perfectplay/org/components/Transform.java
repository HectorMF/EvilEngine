package com.perfectplay.org.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;
import com.perfectplay.org.utils.BodylessSpatial;
import com.perfectplay.org.utils.Bucket;
import com.perfectplay.org.utils.Spatial;

public class Transform extends Component implements Spatial{
	private Spatial spatial;
	private ArrayList<Bucket> buckets;
	private boolean isDirty;

	public Transform(){
		this(new BodylessSpatial());
	}
	
	public Transform(Spatial spatial) {
		super();
		this.spatial = spatial;
	}
	
	public Transform setSpatial(Spatial spatial) {
		this.spatial = spatial;
		return this;
	}
	
	public Spatial getSpatial(){
		return spatial;
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

	@Override
	public float getX() {
		return spatial.getX();
	}

	@Override
	public float getY() {
		return spatial.getY();
	}

	@Override
	public float getZ() {
		return spatial.getZ();
	}

	@Override
	public Transform setX(float x) {
		spatial.setX(x);
		return this;
	}

	@Override
	public Transform setY(float y) {
		spatial.setY(y);
		return this;
	}

	@Override
	public Transform setZ(float z) {
		spatial.setZ(z);
		return this;
	}

	@Override
	public Vector3 getPosition() {
		return spatial.getPosition();
	}

	@Override
	public Transform setPosition(float x, float y, float z) {
		spatial.setPosition(x, y, z);
		return this;
	}

	@Override
	public float getRotation() {
		return spatial.getRotation();
	}

	@Override
	public Transform setRotation(float rotation) {
		spatial.setRotation(rotation);
		return this;
	}

	@Override
	public float getWidth() {
		return spatial.getWidth();
	}

	@Override
	public float getHeight() {
		return spatial.getHeight();
	}

	@Override
	public float getDepth() {
		return spatial.getDepth();
	}

	@Override
	public Transform setWidth(float width) {
		spatial.setWidth(width);
		return this;
	}

	@Override
	public Transform setHeight(float height) {
		spatial.setHeight(height);
		return this;
	}

	@Override
	public Transform setDepth(float depth) {
		spatial.setDepth(depth);
		return this;
	}

	@Override
	public Vector3 getDimension() {
		return spatial.getDimension();
	}

	@Override
	public Transform setDimension(float width, float height, float depth) {
		spatial.setDimension(width, height, depth);
		return this;
	}
}
