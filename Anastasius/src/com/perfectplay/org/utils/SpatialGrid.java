package com.perfectplay.org.utils;

import java.util.ArrayList;

import com.artemis.Entity;
import com.perfectplay.org.components.Transform;

public class SpatialGrid {
	private Bucket[][] buckets;
	
	private int columns;
	private int rows;
	private int bucketSize;
	
	public SpatialGrid(int width, int height, int bucketSize){
		this.columns = width/bucketSize;
		this.rows = height/bucketSize;
		this.bucketSize = bucketSize;
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

        for (int y = row; y <= row2; ++y)
            for (int x = column; x <= column2; ++x)
                if (x >= 0 && y >= 0)
                    if (x < columns && y < rows)
                        buckets[y][x].insertEntity(entity);
    }
    
    public void removeEntity(Entity entity)
    {
        for(Bucket bucket : getBucketsContainingEntity(entity))
            bucket.removeEntity(entity);
    }

    public ArrayList<Bucket> getBucketsContainingEntity(Entity entity)
    {
        Transform transform = entity.getComponent(Transform.class);

        if (transform == null) return null;
        
        ArrayList<Bucket> bucketList = new ArrayList<Bucket>();
        
        int row = (int)(transform.getY() / bucketSize);
        int column = (int)(transform.getX() / bucketSize);
        int row2 = (int)((transform.getY() - 1 + transform.getHeight()) / bucketSize);
        int column2 = (int)((transform.getX() - 1 + transform.getWidth()) /bucketSize);

        for (int y = row; y <= row2; ++y)
            for (int x = column; x <= column2; ++x)
                if (x >= 0 && y >= 0)
                    if (x < columns && y < rows)
                        if (buckets[y][x].contains(entity))
                            bucketList.add(buckets[y][x]);
        return bucketList;
    }
    
    public ArrayList<Entity> retrieveNearbyEntities(Entity entity)
    {
        ArrayList<Entity> entities = new ArrayList<Entity>();
        for(Bucket bucket : getBucketsContainingEntity(entity))
        {
            for(Entity e : bucket.getEntities())
                if(entity != e)
                    entities.add(entity);
        }
        return entities;
    }
    
    public ArrayList<Bucket> getBucketsInsideRectangle(int x, int y, int width, int height)
    {
        int row = (int)(y / bucketSize);
        int column = (int)(x / bucketSize);
        int row2 = (int)((y- 1 + height) / bucketSize);
        int column2 = (int)((x - 1 + width) / bucketSize);
        
        ArrayList<Bucket> bucketList = new ArrayList<Bucket>();
        for (int r = row; r <= row2; ++r)
            for (int c = column; c <= column2; ++c)
                if (c >= 0 && r >= 0)
                    if (c < columns && r < rows)
                            bucketList.add(buckets[r][c]);
        return bucketList;
    }
    
    
}
