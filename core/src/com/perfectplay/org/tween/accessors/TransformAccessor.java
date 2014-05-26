package com.perfectplay.org.tween.accessors;

import aurelienribon.tweenengine.TweenAccessor;

import com.perfectplay.org.components.Transform;

public class TransformAccessor implements TweenAccessor<Transform>{
    public static final int X = 1;
    public static final int Y = 2;
    public static final int POSITION = 4;
    public static final int ROTATION = 5;
    public static final int WIDTH = 6;
    public static final int HEIGHT = 7;
    public static final int DIMENSION = 9;
    
	@Override
	public int getValues(Transform target, int tweenType, float[] returnValues) {
		switch (tweenType) {
        	case X: 
        		returnValues[0] = target.getX(); 
        	return 1;
        	case Y: 
        		returnValues[0] = target.getY(); 
        	return 1;
        	case POSITION:
        		returnValues[0] = target.getX();
        		returnValues[1] = target.getY();
        	return 2;
        	case ROTATION: 
        		returnValues[0] = target.getRotation(); 
        	return 1;
        	case WIDTH: 
        		returnValues[0] = target.getWidth(); 
        	return 1;
        	case HEIGHT: 
        		returnValues[0] = target.getHeight(); 
        	return 1;     		
        	case DIMENSION:
        		returnValues[0] = target.getWidth();
        		returnValues[1] = target.getHeight();
        	return 2;     
           	default: assert false; return -1;
       }
	}

	@Override
	public void setValues(Transform target, int tweenType, float[] newValues) {
		switch (tweenType) {
        	case X: 
        		target.setX(newValues[0]); 
        	break;
        	case Y: 
        		target.setY(newValues[0]); 
        	break;
        	case POSITION:
        		target.setX(newValues[0]);
        		target.setY(newValues[1]);
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
        	case DIMENSION:
        		target.setWidth(newValues[0]);
        		target.setHeight(newValues[1]);
        	break;  
        	default: assert false; break;
		}
	}
}
