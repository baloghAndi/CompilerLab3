package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadInput {
	
	public static FiniteAutomata readFromFileFa(String file) throws IOException{ //A,1=A A,0=B B,0=B B,0=K S,0=A S,1=B transition
		FiniteAutomata fa = new FiniteAutomata();
		Transitions transition = new Transitions();
		BufferedReader br = new BufferedReader(new FileReader("src\\"+file+".txt"));
		String[] tokens;
		try {
			String line = br.readLine();
			tokens = line.split(" "); //fisrt row  = non terminals
			for (String temp : tokens ){
				fa.addState(temp);
			}
			line = br.readLine();
			tokens = line.split(" "); //second row  = alpabet
			for (String temp : tokens ){
				fa.addAlphabet(temp);
			}
			line = br.readLine();
			tokens = line.split(" ");
			fa.setStartingState(tokens[0]);
			line = br.readLine();
			tokens = line.split(" ");
			for (String temp : tokens ){
				fa.addFinalStates(temp);
			}
			line = br.readLine();
			tokens = line.split(" ");
			transition.setTransitions(tokens);
			fa.setTransitions(transition);
		} finally {
			br.close();
		}
		return fa;
		
	}

	public static Grammar readFromFileGr(String file) throws IOException{
		Grammar gr = new Grammar();
		Productions prod = new Productions();
		BufferedReader br = new BufferedReader(new FileReader("src\\"+file+".txt"));
		String[] tokens;
		try {
			String line = br.readLine();
			tokens = line.split(" "); //fisrt row  = non terminals
			for (String temp : tokens ){
				gr.addNonTerm(temp);
				prod.addNonTerm(temp);
			}
			line = br.readLine();
			tokens = line.split(" "); //second row  = alpabet
			for (String temp : tokens ){
				gr.addAlphabet(temp);
			}
			line = br.readLine();
			tokens = line.split(" ");
			gr.setStartingSymbol(tokens[0]);
			line = br.readLine();
			tokens = line.split(" ");
			prod.setProductions(tokens);
			gr.setProductions(prod);
		} finally {
			br.close();
		}
		return gr;
	}
	

	public static FiniteAutomata readFa( String alpabet, String states, String stratState,  String finalStates, String transitions ){
		FiniteAutomata fa = new FiniteAutomata();
		Transitions transition = new Transitions();
		String[] tokens;
		
		tokens = states.split(" "); 
		for (String temp : tokens ){
			fa.addState(temp);
		}
		tokens = alpabet.split(" "); 
		for (String temp : tokens ){
			fa.addAlphabet(temp);
		}
		fa.setStartingState(stratState);
	
		tokens = finalStates.split(" ");
		for (String temp : tokens ){
			fa.addFinalStates(temp);
		}
		
		tokens = transitions.split(" ");
		transition.setTransitions(tokens);
		fa.setTransitions(transition);
		
		return fa;
	}

	public static Grammar readGr(String alpabet, String nonTerm,  String stratSymbol, String productions ){
		Grammar gr = new Grammar();
		Productions prod = new Productions();
		String[] tokens;
		tokens = alpabet.split(" "); // alpabet
		for (String temp : tokens ){
			gr.addAlphabet(temp);
		}
		tokens = nonTerm.split(" "); //non terminals
		for (String temp : tokens ){
			gr.addNonTerm(temp);
			prod.addNonTerm(temp);
		}
		gr.setStartingSymbol(stratSymbol);  //staring symbol
		tokens = productions.split(" "); //productions
		prod.setProductions(tokens);
		gr.setProductions(prod);
		return gr;
	
	}

}
