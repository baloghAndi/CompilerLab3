package controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Transitions {

	private Map<Map.Entry<String,String>,List<String>> transition; // //A,1=A A,0=B B,0=B B,0=K S,0=A S,1=B transition

	public Transitions() {
		transition = new HashMap<Map.Entry<String,String>, List<String>>();
	}

	public void setTransitions(String[] tokens) {
		for (String tem : tokens){
			String equalTo = tem.split("=")[1];
			String[] delta = tem.split("=")[0].split(",");
			String alphabetSymbol = delta[1];
			String state = delta[0];
			addTransition(state, alphabetSymbol, equalTo);
		}
		
	}
	
	public void addTransition(String state1, String alphabetSymbol , String state2){
		Map.Entry<String,String> key = new AbstractMap.SimpleEntry(state1,alphabetSymbol);
		if (transition.containsKey(key)){
			transition.get(key).add(state2);
		} else {
			List<String> list = new ArrayList<>();
			list.add(state2);
			transition.put(key,list);
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (Map.Entry<String,String> key : transition.keySet()){
			result +="\n" + "( "+ key.getKey()+","+key.getValue()+" )" + "=" + transition.get(key);
		}
		return result;
	}
	
	public Set<Entry<String, String>> getKeys(){
		return transition.keySet();
	}

	public List<String> getValue(Entry<String, String> leftSide) {
		return transition.get(leftSide);
	}
	
}
