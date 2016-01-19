package controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Productions {
	
	private Map<String,List<Map.Entry<String,String>>> productions;

	public void setProductions(String[] tokens) {
		for (String prod : tokens){
				String first = prod.split("-")[0];
				String[] last= prod.split("-")[1].split(",");
				String term = last[0];
				if (last.length ==  2){
					String nonterm = last[1];
					productions.get(first).add(new AbstractMap.SimpleEntry(term,nonterm));
				} else{
					productions.get(first).add(new AbstractMap.SimpleEntry(term,""));
				}
		}
	}

	public Productions() {
		productions = new HashMap<String,List<Map.Entry<String,String>>>();
	}

	public void addNonTerm(String temp) {
		productions.put(temp, new ArrayList<Map.Entry<String,String>>());
	}

	private void addToNonTerm(String toNonterm, String term, String nonTerm){
		productions.get(toNonterm).add(new AbstractMap.SimpleEntry(term,nonTerm));
	}
	
	@Override
	public String toString() {
		String result = "";
		for (String key : productions.keySet()){
			result +="\n" + key + "->" + productions.get(key);
		}
		return result;
	}

	public Productions getProductionsOf(String nonTerm) {
		Productions prod = new Productions();
		prod.addNonTerm(nonTerm);
		if (productions.containsKey(nonTerm)){
			for (Map.Entry<String,String> temp : productions.get(nonTerm)){
				prod.addToNonTerm(nonTerm, temp.getKey(), temp.getValue());
			}
		}
		return prod;
		}

	public Set<String> getKeys() {
		return productions.keySet();
	}
	
	public List<Entry<String, String>> getValues(String key) {
		return productions.get(key);
		
	}

	public boolean isFinal(String symbol) {
		for (Map.Entry<String,String> rightSide : productions.get(symbol)){
			if (rightSide.getValue().equals("")){
				return true;
			}
		}
		return false;
	}

	public void addProduction(String nonterm, String alp, String nonterm2) {
		if (productions.containsKey(nonterm)){
			productions.get(nonterm).add( new AbstractMap.SimpleEntry(alp,nonterm2));
		} else {
			List<Map.Entry<String,String>> list = new ArrayList<Map.Entry<String,String>>();
			list.add( new AbstractMap.SimpleEntry(alp,nonterm2));
			productions.put(nonterm, list);
		}
	}

	public boolean rightSideContains(String nonTerm) {
		if (productions.containsKey(nonTerm)){
			for (Map.Entry<String,String> temp : productions.get(nonTerm)){
				if (nonTerm.equals(temp.getValue())){
					return true;
				}
			}
		}
		return false;
	}

}
	
	