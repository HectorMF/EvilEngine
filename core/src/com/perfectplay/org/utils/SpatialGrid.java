package com.perfectplay.org.utils;

import java.util.ArrayList;

import com.artemis.Entity;
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

	public SpatialGrid(int width, int height, int bucketSize) {

		this.columns = width / bucketSize;
		this.rows = height / bucketSize;
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

	public void clear() {
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < columns; col++)
				buckets[row][col].clear();
	}

	public void insertEntity(Entity entity, Transform transform) {
		SpatialNode node = new SpatialNode(entity, transform);

		ArrayList<Bucket> bList = new ArrayList<Bucket>();

		// calculate the area which holds the entity
		float screenX = transform.getX();
		float screenY = transform.getY();

		int row = (int) (screenY / bucketSize);
		int column = (int) (screenX / bucketSize);
		int row2 = (int) ((screenY - 1 + transform.getHeight()) / bucketSize);
		int column2 = (int) ((screenX - 1 + transform.getWidth()) / bucketSize);

		// add the buckets to the list of buckets which hold the entity
		for (int y = row; y <= row2; ++y)
			for (int x = column; x <= column2; ++x)
				if (x >= 0 && y >= 0)
					if (x < columns && y < rows) {
						bList.add(buckets[y][x]);
					}

		transform.setBuckets(bList);
		boolean isEnabled = false;
		for (Bucket b : bList) {
			b.insertNode(node);
			if (b.isEnabled())
				isEnabled = true;
		}

		if (!isEnabled) {
		//	entity.disable();
		}
	}

	public void removeEntity(Entity entity, Transform transform) {
		for (Bucket bucket : transform.getBuckets())
			bucket.removeNodesContainingEntity(entity);
	}

	public void updateEntity(Entity entity, Transform transform) {
		removeEntity(entity, transform);
		insertEntity(entity, transform);
	}

	/*
	 * public ArrayList<Entity> retrieveNearbyEntities(Entity entity) {
	 * ArrayList<Entity> entities = new ArrayList<Entity>(); for(Bucket bucket :
	 * (entity.getComponent(Transform.class).getBuckets())) { for(Entity e :
	 * bucket.getEntities()) if(entity != e) entities.add(entity); } return
	 * entities; }
	 */
	public void activateBucketsOnScreen(int x, int y, int width, int height) {

		// int x= x1 + 100;
		// int y = y1 +100;
		// int width = 100;
		// int height = 100;
		// int testval = 000;
		int row1 = (int) (y / bucketSize);
		int row2 = (int) ((y - 1 + height) / bucketSize);
		int column1 = (int) (x / bucketSize);
		int column2 = (int) ((x - 1 + width) / bucketSize);

		// set all buckets in previous frame to inactive
		for (int r = activeRow1; r <= activeRow2; r++) {
			for (int c = activeCol1; c <= activeCol2; c++) {
				if (c >= 0 && r >= 0) {
					if (c < columns && r < rows) {
						buckets[r][c].setActive(false);
					}
				}
			}
		}

		// calculate overlapping area
		int startRow = 0, endRow = 0;
		int startCol = 0, endCol = 0;
		if (activeRow1 <= row2 && activeRow2 >= row2) {
			startRow = activeRow1;
			endRow = row2;
		}
		if (row1 <= activeRow2 && row2 >= activeRow2) {
			startRow = row1;
			endRow = activeRow2;
		}

		if (activeCol1 <= column2 && activeCol2 >= column2) {
			startCol = activeCol1;
			endCol = column2;
		}
		if (column1 <= activeCol2 && column2 >= activeCol2) {
			startCol = column1;
			endCol = activeCol2;
		}
		// System.out.println("r: " + startRow + " : " + endRow);
		// System.out.println("c: " + startCol + " : " + endCol);
		// set overlap entities to active
		for (int r = startRow; r <= endRow; r++)
			for (int c = startCol; c <= endCol; c++)
				if (c >= 0 && r >= 0)
					if (c < columns && r < rows)
						buckets[r][c].setActive(true);

		// disable non-overlap entities
		for (int r = activeRow1; r <= activeRow2; r++)
			for (int c = activeCol1; c <= activeCol2; c++)
				if (c >= 0 && r >= 0)
					if (c < columns && r < rows)
						if (buckets[r][c].isEnabled())
							buckets[r][c].disableInactives();

		// enable buckets in new area
		for (int r = row1; r <= row2; r++) {
			for (int c = column1; c <= column2; c++) {
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

	public ArrayList<Bucket> getBucketsInsideRectangle(int x, int y, int width, int height) {
		int row = (int) (y / bucketSize);
		int row2 = (int) ((y - 1 + height) / bucketSize);
		int column = (int) (x / bucketSize);
		int column2 = (int) ((x - 1 + width) / bucketSize);

		ArrayList<Bucket> bucketList = new ArrayList<Bucket>();
		for (int r = row; r <= row2; ++r)
			for (int c = column; c <= column2; ++c)
				if (c >= 0 && r >= 0)
					if (c < columns && r < rows)
						bucketList.add(buckets[r][c]);
		return bucketList;
	}
	
	public void debugRender(ShapeRenderer shapeRenderer) {
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 0, 1);

		for (int r = activeRow1; r <= activeRow2; r++) {
			for (int c = activeCol1; c <= activeCol2; c++) {
				if (c >= 0 && r >= 0)
					if (c < columns && r < rows)
						if(buckets[r][c].size() > 0)
						shapeRenderer.rect(c * bucketSize, r * bucketSize,
								bucketSize, bucketSize);
			}
		}

		shapeRenderer.end();
	}

}
