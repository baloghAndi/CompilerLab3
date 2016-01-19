package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FiniteAutomata {
	List<String> states;
	List<String> alphabet;
	Transitions transitions;
	String startingState;
	List<String> finalStates;
	
	
	
	public FiniteAutomata() {
		states = new ArrayList<String>();
		alphabet = new ArrayList<String>();
		finalStates = new ArrayList<String>();
	}

	public FiniteAutomata(List<String> states, List<String> alpahbet,
			Transitions transitions, String startingSymbol,
			List<String> finalStates) {
		this.states = states;
		this.alphabet = alpahbet;
		this.transitions = transitions;
		this.startingState = startingSymbol;
		this.finalStates = finalStates;
	}
	
	
	public FiniteAutomata(Grammar gr) {
		this.states = gr.getNonTerm();
		addState("K"); //should not be in gr.nonterm
		this.alphabet = gr.getAlphabet();
		this.startingState = gr.getStartingSymbol();
		this.finalStates  = new ArrayList<String>();
		addFinalStates("K");
		if (gr.isStartingSymbolFinal()){ 
			addFinalStates(startingState);
		}
		this.transitions =  gr.transformProdIntoTrans();
	}

	public void addState(String state) {
		this.states.add(state);
	}
	
	public void addAlphabet(String alphabet) {
		this.alphabet.add(alphabet);
	}
	
	public void addFinalStates(String temp) {
		this.finalStates.add(temp);
	}
	
	
	/**
	 * @return the states
	 */
	public List<String> getStates() {
		return states;
	}
	/**
	 * @param states the states to set
	 */
	public void setStates(List<String> states) {
		this.states = states;
	}
	/**
	 * @return the alpahbet
	 */
	public List<String> getAlpahbet() {
		return alphabet;
	}
	/**
	 * @param alpahbet the alpahbet to set
	 */
	public void setAlpahbet(List<String> alpahbet) {
		this.alphabet = alpahbet;
	}
	/**
	 * @return the transitions
	 */
	public Transitions getTransitions() {
		return transitions;
	}
	/**
	 * @param transitions the transitions to set
	 */
	public void setTransitions(Transitions transitions) {
		this.transitions = transitions;
	}
	/**
	 * @return the finalStates
	 */
	public List<String> getFinalStates() {
		return finalStates;
	}
	/**
	 * @param finalStates the finalStates to set
	 */
	public void setFinalStates(List<String> finalStates) {
		this.finalStates = finalStates;
	}
	/**
	 * @param startingSymbol the startingSymbol to set
	 */
	public void setStartingState(String startingSymbol) {
		this.startingState = startingSymbol;
	}

	@Override
	public String toString() {
		return "FiniteAutomata \n states=" + states + "\n alphabet=" + alphabet
				+ "\n transitions=" + transitions.toString() + "\n startingSymbol="
				+ startingState + "\n finalStates=" + finalStates + "\n";
	}

	public String getStartingState() {
		return this.startingState;
	}

	public Productions transformTransToProd() {
		Productions prod = new Productions();
		for (Map.Entry<String,String> leftSide : transitions.getKeys()){
			String nonterm = leftSide.getKey();
			String alp = leftSide.getValue();
			List<String> nontermList = transitions.getValue(leftSide);
			for (String nonterm2 : nontermList){
				prod.addProduction(nonterm,alp,nonterm2);
				if (finalStates.contains(nonterm2)){
					prod.addProduction(nonterm,alp,"");
				}
			}
		}
		return prod;
	}

	public boolean isStartingSymbolFinal() {
		return finalStates.contains(startingState);
	}

	
	
}
