package com.perfectplay.org.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.artemis.Component;
import com.perfectplay.org.scripting.Delegate;
import com.perfectplay.org.scripting.DelegateManager;
import com.perfectplay.org.scripting.Script;

public class Scripts extends Component {
	private HashMap<Class<? extends Script>, Script> scripts;
	private HashMap<Integer, List<Script>> scriptsByDelegate;
	
	public Scripts() {
		this.scripts = new HashMap<Class<? extends Script>,Script>();
		this.scriptsByDelegate = new HashMap<Integer, List<Script>>();
	}
	
	private void addScriptByDelegates(Script script, Class<? extends Script> classScript){
		Class<?>[] delegates = classScript.getInterfaces();
		for(int i = 0; i < delegates.length; i++){
			if(Delegate.class.isAssignableFrom(delegates[i])){
				@SuppressWarnings("unchecked")
				int id = DelegateManager.getDelegateId((Class<? extends Delegate>)delegates[i]);
				List<Script> list = scriptsByDelegate.get(id);
				if(list == null){
					list = new ArrayList<Script>();
				}
				list.add(script);
				System.out.println("Script added: " + script + " IDS : " + id);
				scriptsByDelegate.put(id, list);
			}
		}
	}
	
	private void removeScriptByDelegates(Class<? extends Script> classScript){
		Class<?>[] delegates = classScript.getInterfaces();
		for(int i = 0; i < delegates.length; i++){
			if(Delegate.class.isAssignableFrom(delegates[i])){
				@SuppressWarnings("unchecked")
				int id = DelegateManager.getDelegateId((Class<? extends Delegate>)delegates[i]);
				List<Script> list = scriptsByDelegate.get(id);
				for(int j = list.size()-1; j >= 0 ; j--){
					if(classScript.isInstance(list.get(j))){
						System.out.println("Script removed: " + list.get(j) + " IDS : " + id);
						list.remove(j);
					}
				}
				scriptsByDelegate.put(id, list);
			}
		}
	}
	
	public Scripts add(Class<? extends Script> classScript) {
		try {
			Script script = classScript.newInstance();
			if(this.scripts.get(classScript) != null)
				this.removeScriptByDelegates(classScript);
			this.scripts.put(classScript, script);
			this.addScriptByDelegates(script, classScript);
			return this;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Scripts remove(Class<? extends Script> classScript) {
		this.scripts.remove(classScript);
		this.removeScriptByDelegates(classScript);
		return this;
	}
	
	public int size(){
		return scripts.size();
	}
	
	public Script get(Class<? extends Script> classScript){
		return scripts.get(classScript);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Delegate> List<T> getDelegates(Class<T> delegateClass){
		List<Script> list = scriptsByDelegate.get(DelegateManager.getDelegateId(delegateClass));
		if(list == null)
			return new ArrayList<T>();
		else
			return (List<T>)list;
	}
	
	public Collection<Script> getScripts(){
		return scripts.values();
	}
}
