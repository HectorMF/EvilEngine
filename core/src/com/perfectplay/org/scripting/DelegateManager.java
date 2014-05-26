package com.perfectplay.org.scripting;

import java.util.HashMap;

public class DelegateManager {
	private static HashMap<Class<? extends Delegate>,Integer> delegateMapping = new HashMap<Class<? extends Delegate>,Integer>();
	private static int ID = 0;
	
	public static int getDelegateId(Class<? extends Delegate> delegateClass){
		Integer val = delegateMapping.get(delegateClass);
		if(val == null){
			val = ID;
			ID++;
			delegateMapping.put(delegateClass, val);
		}
		return val;
	}
}
