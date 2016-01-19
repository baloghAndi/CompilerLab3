package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grammar {
	List<String> nonTerm;
	List<String> symbols;
	Productions productions;
	String startingSymbol;

	public Grammar() {
		nonTerm = new ArrayList<String>();
		symbols = new ArrayList<String>();
	}
	
	public Grammar(List<String> nonTerm, List<String> symbols, Productions productions, String startingSymbol) {
		this.nonTerm = nonTerm;
		this.symbols = symbols;
		this.productions = productions;
		this.startingSymbol = startingSymbol;
	}
	
	public Grammar(FiniteAutomata fa) {
		this.nonTerm = fa.getStates();
		this.symbols = fa.getAlpahbet();
		this.startingSymbol = fa.getStartingState();
		this.productions = fa.transformTransToProd();
		if (fa.isStartingSymbolFinal()){
			productions.addProduction(startingSymbol,"E","");
		}
	}

	public void addNonTerm(String nonTerm) {
		this.nonTerm.add(nonTerm);
	}
	
	public void addAlphabet(String symbols) {
		this.symbols.add(symbols);
	}
	
	/**
	 * @return the nonTerm
	 */
	public List<String> getNonTerm() {
		return nonTerm;
	}
	/**
	 * @param nonTerm the nonTerm to set
	 */
	public void setNonTerm(List<String> nonTerm) {
		this.nonTerm = nonTerm;
	}
	
	/**
	 * @return the productions
	 */
	public Productions getProductions() {
		return productions;
	}
	/**
	 * @param productions the productions to set
	 */
	public void setProductions(Productions productions) {
		this.productions = productions;
	}
	/**
	 * @param alphabet the alphabet to set
	 */
	public void setAlphabet(List<String> alphabet) {
		this.symbols = alphabet;
	}
	/**
	 * @param startingSymbol the startingSymbol to set
	 */
	public void setStartingSymbol(String startingSymbol) {
		this.startingSymbol = startingSymbol;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grammar : \n nonTerm=" + nonTerm + " \n alphabet=" + symbols
				+ "\n productions=" + productions.toString() + "\n startingSymbol="
				+ startingSymbol + "\n";
	}

	public Productions getProductionOf(String symbol) {
		return productions.getProductionsOf(symbol);
	}

	public List<String> getAlphabet() {
		return symbols;
	}

	public boolean checkProductions() {
		//	Producţiile unei gramatici regulare G = (N, Σ, P, S) diferite de   sunt numai de forma A->aB,  A->b.
		for (String key : productions.getKeys()){
			if (!nonTerm.contains(key)){
				return false;
			}
			for (Map.Entry<String,String> rightSide : productions.getValues(key)){
				if (rightSide.getKey().equals("E") && productions.rightSideContains(key)){
					return false;
				}
				if (!symbols.contains(rightSide.getKey()) && !rightSide.getKey().equals("E")){ //right hand side is symbol or epsilon?
					return false;
				}
				if (!nonTerm.contains(rightSide.getValue()) && !rightSide.getValue().equals("")){ // righthand side second term is nonterm or nothing?
					return false;
				}
			}
		}
		return true;
	}

	public String getStartingSymbol() {
		return this.startingSymbol;
	}

	public boolean isStartingSymbolFinal() {
		return productions.isFinal(startingSymbol);
	}

	public Transitions transformProdIntoTrans() {
		Transitions trans = new Transitions();
		for (String nonterm1 : productions.getKeys()){
			for (Map.Entry<String,String> rightSide : productions.getValues(nonterm1)){
				if (rightSide.getKey().equals("E") ){
					continue;
				}
				if (rightSide.getValue().equals("")){
					trans.addTransition(nonterm1, rightSide.getKey(),"K");
				}
				trans.addTransition(nonterm1, rightSide.getKey(),rightSide.getValue());
			}
		}
		return trans;
	}

	
	
	

}
