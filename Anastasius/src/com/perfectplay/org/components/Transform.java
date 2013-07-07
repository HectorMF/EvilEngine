package com.perfectplay.org.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Transform extends Component {
	
	private float x;
	private float y;
	private float z;
	
	private float originX;
	private float originY;

	private float width;
	private float height;
	private float depth;

	private float scaleX;
	private float scaleY;

	private float rotation;

	private boolean horizontalFlip;
	private boolean verticalFlip;

	public Transform() {
		this(0f,0f,0f,0f, 0f, 10, 10, 1f, 1f, 0f, false, false);
	}

	public Transform(float x, float y, float z, float width, float height, float rotation) {
		this(x, y, z, width/2, height/2, width, height, 1f, 1f, rotation, false, false);
	}

	public Transform(float x, float y, float z, float originX, float originY, float width, float height,
					float scaleX, float scaleY, float rotation, boolean horizontalFlip,
					boolean verticalFlip) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.originX = originX;
		this.originY = originY;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.horizontalFlip = horizontalFlip;
		this.verticalFlip = verticalFlip;
		this.depth = 10;
	}
	
	public Vector2 getScreenPosition(){
		return new Vector2(x,(float)(y - Math.sin(Math.toRadians(15))*z));
	}
	
	public float getScreenX(){
		return x;
	}
	
	public float getScreenY(){
		return (y - z);
	}
	
	public Vector3 getPosition() {
		return new Vector3(x,y,z);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public float getOriginX() {
		return originX;
	}

	public float getOriginY() {
		return originY;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public float getDepth(){
		return depth;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getRotation() {
		return rotation;
	}

	public boolean getHorizontalFlip() {
		return horizontalFlip;
	}

	public boolean getVerticalFlip() {
		return verticalFlip;
	}

	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setZ(float z){
		this.z = z;
	}
	
	public void setPosition(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void addX(float x){
		this.x += x;
	}
	
	public void addY(float y){
		this.y += y;
	}
	
	public void addZ(float z){
		this.z += z;
	}
	
	public void setOriginX(float x) {
		this.originX = x;
	}

	public void setOriginY(float y) {
		this.originY = y;
	}

	public void setOrigin(float x, float y) {
		setOriginX(x);
		setOriginY(y);
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}
	
	public void setDimension(float width, float height) {
		setWidth(width);
		setHeight(height);
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void rotate(float rotation) {
		this.rotation += rotation;
	}

	public void setHorizontalFlip(boolean horizontalFlip) {
		this.horizontalFlip = horizontalFlip;
	}

	public void setVerticalFlip(boolean verticalFlip) {
		this.verticalFlip = verticalFlip;
	}
}
