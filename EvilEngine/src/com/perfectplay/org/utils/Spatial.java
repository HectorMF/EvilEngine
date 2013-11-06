package com.perfectplay.org.utils;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.math.Vector3;

public abstract class Spatial implements TweenAccessor<Spatial>{

    public static final int X = 1;
    public static final int Y = 2;
    public static final int Z = 3;
    public static final int POSITION = 4;
    public static final int ROTATION = 5;
    public static final int WIDTH = 6;
    public static final int HEIGHT = 7;
    public static final int DEPTH = 8;
    public static final int DIMENSION = 9;

	public abstract float getX();

	public abstract float getY();

	public abstract float getZ();
	
	public abstract void setX(float x);

	public abstract void setY(float y);

	public abstract void setZ(float z);

	public abstract Vector3 getPosition();

	public abstract void setPosition(float x, float y, float z);

	public abstract float getRotation();

	public abstract void setRotation(float rotation);

	public abstract float getWidth();

	public abstract float getHeight();

	public abstract float getDepth();
	
	public abstract void setWidth(float width);

	public abstract void setHeight(float height);

	public abstract void setDepth(float depth);
	
	public abstract Vector3 getDimension();

	public abstract void setDimension(float width, float height, float depth);

	public abstract void setSpatial(Spatial spatial);
	
	@Override
	public int getValues(Spatial target, int tweenType, float[] returnValues) {
		switch (tweenType) {
        	case X: 
        		returnValues[0] = target.getX(); 
        	return 1;
        	case Y: 
        		returnValues[0] = target.getY(); 
        	return 1;
        	case Z: 
        		returnValues[0] = target.getZ(); 
        	return 1;
        	case POSITION:
        		returnValues[0] = target.getX();
        		returnValues[1] = target.getY();
        		returnValues[2] = target.getZ();
        	return 3;
        	case ROTATION: 
        		returnValues[0] = target.getRotation(); 
        	return 1;
        	case WIDTH: 
        		returnValues[0] = target.getWidth(); 
        	return 1;
        	case HEIGHT: 
        		returnValues[0] = target.getHeight(); 
        	return 1;
        	case DEPTH: 
        		returnValues[0] = target.getDepth(); 
        	return 1;       		
        	case DIMENSION:
        		returnValues[0] = target.getWidth();
        		returnValues[1] = target.getHeight();
        		returnValues[2] = target.getDepth();
        	return 3;     
           	default: assert false; return -1;
       }
	}

	@Override
	public void setValues(Spatial target, int tweenType, float[] newValues) {
		switch (tweenType) {
        	case X: 
        		target.setX(newValues[0]); 
        	break;
        	case Y: 
        		target.setY(newValues[0]); 
        	break;
        	case Z: 
        		target.setZ(newValues[0]); 
        	break;
        	case POSITION:
        		target.setX(newValues[0]);
        		target.setY(newValues[1]);
        		target.setZ(newValues[2]);
            break;
        	case ROTATION: 
        		target.setRotation(newValues[0]); 
        	break;
        	case WIDTH: 
        		target.setWidth(newValues[0]); 
        	break;
        	case HEIGHT: 
        		target.setHeight(newValues[0]); 
        	break;
        	case DEPTH: 
        		target.setDepth(newValues[0]); 
        	break;      		
        	case DIMENSION:
        		target.setWidth(newValues[0]);
        		target.setHeight(newValues[1]);
        		target.setDepth(newValues[2]);
        	break;  
        	default: assert false; break;
		}
	}
}
