package com.perfectplay.org.utils;

import java.util.ArrayList;

import com.artemis.Entity;

public class Bucket {

	private ArrayList<SpatialNode> nodeList;

	private boolean isEnabled;
	private boolean isOverlapping;

	public Bucket() {
		nodeList = new ArrayList<SpatialNode>();
		isEnabled = false;
	}

	public void setOverlapping(boolean overlap) {
		this.isOverlapping = overlap;
	}

	public boolean isOverlapping() {
		return isOverlapping;
	}

	public void insertNode(SpatialNode node) {
		nodeList.add(node);
	}

	public void setActive(boolean active) {
		for (SpatialNode node : nodeList) {
			node.setActive(active);
		}
	}

	public void enable() {
		for (SpatialNode node : nodeList) {
			if (!node.isEnabled()) {
				node.enable();
			}
		}
		isEnabled = true;
	}

	public void disable() {
		for (SpatialNode node : nodeList) {
			if (node.isEnabled())
				node.disable();
		}

		isEnabled = false;
	}

	public void disableSome(ArrayList<Entity> list) {
		for (SpatialNode node : nodeList) {
			if (!list.contains(node))
				if (node.isEnabled())
					node.disable();
		}
		isEnabled = false;
	}

	public void disableInactives() {
		for (SpatialNode node : nodeList) {
			if (!node.isActive())
				node.disable();
		}

		isEnabled = false;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void clear() {
		nodeList.clear();
	}

	public void removeNodesContainingEntity(Entity entity) {
		for (int i = nodeList.size() - 1; i >= 0; i--)
			if (entity.getId() == nodeList.get(i).getEntity().getId()) {
				nodeList.remove(i);
			}
	}

	public int size() {
		return nodeList.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Entity entity) {
		return nodeList.contains(entity);
	}

	public ArrayList<SpatialNode> getNodes() {
		return nodeList;
	}

}
