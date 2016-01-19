package controller;

public class Controller {
	
	private Grammar gr;
	private FiniteAutomata fa;
	

	
	public Controller() {
		gr = new Grammar();
		fa = new FiniteAutomata();
	}
	
	/**
	 * @return the gr
	 */
	public Grammar getGr() {
		return gr;
	}
	/**
	 * @param gr the gr to set
	 */
	public void setGr(Grammar gr) {
		this.gr = gr;
	}
	/**
	 * @return the fa
	 */
	public FiniteAutomata getFa() {
		return fa;
	}
	/**
	 * @param fa the fa to set
	 */
	public void setFa(FiniteAutomata fa) {
		this.fa = fa;
	}

	public String getProductionsOfSymbol(String symbol) {
		return gr.getProductionOf(symbol).toString();
	}

	public String getGrAlphabet() {
		return gr.getAlphabet().toString();
	}

	public String getGrNonTerminals() {
		return gr.getNonTerm().toString();
	}

	public String getGrProductions() {
		return gr.getProductions().toString();
	}

	public String getFaAlphabet() {
		return fa.getAlpahbet().toString();
	}

	public String getFaStates() {
		return fa.getStates().toString();
	}

	public String getFaTransitions() {
		return fa.getTransitions().toString();
	}

	public String getFaFinalStates() {
		return fa.getFinalStates().toString();
	}
	
	public Boolean checkIfRegularGr() {
		// 	Producţiile unei gramatici regulare G = (N, Σ, P, S) diferite de   sunt numai de forma A->aB,  A->b.
		if (gr.checkProductions()){
			return true;
		}
		return false;
	} 

	
}
