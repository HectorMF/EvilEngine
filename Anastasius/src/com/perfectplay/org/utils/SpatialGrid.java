package com.perfectplay.org.utils;

import java.util.ArrayList;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.perfectplay.org.components.Transform;

public class SpatialGrid {
	private Bucket[][] buckets;
	
	private int columns;
	private int rows;
	private int bucketSize;
	
	private int activeRow1, activeRow2;
	private int activeCol1, activeCol2;
	
	public SpatialGrid(int width, int height, int bucketSize){
		this.columns = width/bucketSize;
		this.rows = height/bucketSize;
		this.bucketSize = bucketSize;
		this.activeRow1 = 0;
		this.activeRow2 = 0;
		this.activeCol1 = 0;
		this.activeCol2 = 0;
		this.buckets = new Bucket[rows][columns];
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < columns; c++)
                buckets[r][c] = new Bucket();
       
	}
	
	public void clear(){
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < columns; col++)
               	buckets[row][col].clear();
	}
	
    public void insertEntity(Entity entity)
    {
    	
        Transform transform = entity.getComponent(Transform.class);

        if (transform == null) return;

        int row = (int)(transform.getY() / bucketSize);
        int column = (int)(transform.getX() / bucketSize);
        int row2 = (int)((transform.getY() - 1 + transform.getHeight()) / bucketSize);
        int column2 = (int)((transform.getX() - 1 + transform.getWidth()) /bucketSize);
        
        ArrayList<Bucket> bList = new ArrayList<Bucket>();
        
        for (int y = row; y <= row2; ++y)
            for (int x = column; x <= column2; ++x)
                if (x >= 0 && y >= 0)
                    if (x < columns && y < rows){
                    	bList.add(buckets[y][x]);
                    }
        transform.setBuckets(bList);
        entity.disable();
        for(Bucket b : bList){
        	b.insertEntity(entity);
        }
        
        //ARGH THIS IS WRONG BUT IM LEAVING IT FOR NOW
        
    }
    
    public void removeEntity(Entity entity)
    {
    	Transform transform = entity.getComponent(Transform.class);

        if (transform == null) return;

        for(Bucket bucket : transform.getBuckets())
            bucket.removeEntity(entity);
    }
    
    public void updateEntity(Entity entity){
    	removeEntity(entity);
    	insertEntity(entity);
    }
    
    public ArrayList<Entity> retrieveNearbyEntities(Entity entity)
    {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for(Bucket bucket : (entity.getComponent(Transform.class).getBuckets()))
        {
            for(Entity e : bucket.getEntities())
                if(entity != e)
                    entities.add(entity);
        }
        return entities;
    }
    
    
    public void activateBucketsOnScreen(int x, int y, int width, int height){
    	int testval = 00;
        int row1 = (int)((y+testval)/ bucketSize);
        int row2 = (int)(((y-testval)- 1 + height) / bucketSize);
        int column1 = (int)((x+testval) / bucketSize);
        int column2 = (int)(((x-testval) - 1 + width) / bucketSize);
        //optimize this later
        for(int r = activeRow1; r < activeRow2; r++ ){
            for(int c = activeCol1; c < activeCol2; c++ ){
            	if (c >= 0 && r >= 0){
                    if (c < columns && r < rows){
                    	if(r <= row2 && r >= row1 && c <= column2 && c >= column1){
                    	//	System.out.println("r: " + r + " c: "+c);
                    	}
                    	else{
                    		System.out.println("r: " + r + " c: "+c);
                        	if(buckets[r][c].isEnabled())
                        	{
                        		//System.out.println("r: " + r + " c: "+c);
                        		//buckets[r][c].disable();
                        	}	
                    	}
                    }
            	}

            }
        }
        
        for(int r = row1; r < row2; r++ ){
            for(int c = column1; c < column2; c++ ){
            	if (c >= 0 && r >= 0)
                    if (c < columns && r < rows)
                    		buckets[r][c].enable();
            }
        }
        
        activeRow1 = row1;
        activeRow2 = row2;
        activeCol1 = column1;
        activeCol2 = column2;    	

    }
    
    public ArrayList<Bucket> getBucketsInsideRectangle(int x, int y, int width, int height)
    {
        int row = (int)(y / bucketSize);
        int row2 = (int)((y- 1 + height) / bucketSize);
        int column = (int)(x / bucketSize);
        int column2 = (int)((x - 1 + width) / bucketSize);
        
        ArrayList<Bucket> bucketList = new ArrayList<Bucket>();
        for (int r = row; r <= row2; ++r)
            for (int c = column; c <= column2; ++c)
                if (c >= 0 && r >= 0)
                    if (c < columns && r < rows)
                            bucketList.add(buckets[r][c]);
        return bucketList;
    }
    
    public void debugRender(ShapeRenderer shapeRenderer){
    	 shapeRenderer.begin(ShapeType.Rectangle);
    	 shapeRenderer.setColor(1, 1, 0, 1);
    	 
         for(int r = activeRow1; r < activeRow2; r++ ){
             for(int c = activeCol1; c < activeCol2; c++ ){
             	if (c >= 0 && r >= 0)
                     if (c < columns && r < rows)
                    	 shapeRenderer.rect(c*bucketSize, r*bucketSize, bucketSize, bucketSize);
             }
         }
    	 
    	 
    	 shapeRenderer.end();
    }
  
}
