package com.perfectplay.org.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.perfectplay.org.utils.BodylessSpatial;
import com.perfectplay.org.utils.Bucket;
import com.perfectplay.org.utils.Spatial;

public class Transform extends Component implements Spatial{
	private Spatial spatial;
	private ArrayList<Bucket> buckets;

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
	public Vector2 getPosition() {
		return spatial.getPosition();
	}

	@Override
	public Transform setPosition(float x, float y) {
		spatial.setPosition(x, y);
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
	public Vector2 getDimension() {
		return spatial.getDimension();
	}

	@Override
	public Transform setDimension(float width, float height) {
		spatial.setDimension(width, height);
		return this;
	}
	
	@Override
	public boolean equals(Spatial spatial) {
		if(getPosition().equals(spatial.getPosition()))
			if(getDimension().equals(spatial.getDimension()))
				if(getRotation() == spatial.getRotation())
					return true;
		return false;
	}
}
