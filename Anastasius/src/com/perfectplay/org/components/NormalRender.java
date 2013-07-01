package com.perfectplay.org.components;

import com.artemis.Component;
import com.perfectplay.org.graphics.ISprite;

public class NormalRender extends Component {
	private ISprite sprite;
	
	public NormalRender(ISprite sprite) {
		this.sprite = sprite;
	}
	
	public ISprite getSprite() {
		return sprite;
	}
}
