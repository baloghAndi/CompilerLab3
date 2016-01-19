package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;
import controller.FiniteAutomata;
import controller.Grammar;
import controller.ReadInput;

public class StartApp {

	private JFrame frame;
	private JTextField readGR;
	private JTextField readFA;
	private JTextField textFieldAlphabet;
	private JTextField textFieldNonTerm;
	private JTextField textFieldStart;
	private JTextField textFieldProd;
	private JTextField textFieldFinal;
	private JTextField textFieldGetProd;
	private JButton btnReadGr,btnReadFa,btnReadGrammar,btnReadFiniteAutomate,btnProductionsOf,btnShowAlphabetGR,btnShowNonTerm,btnProductions,btnFinalStates;
	private JTextArea textArea;
	private Controller ctrl;
	private JButton btnAlpahetfa;
	private JButton btnStates;
	private JButton btnTransitions;
	private JLabel lblGrammar;
	private JLabel lblFiniteAutomata;
	private JButton btnCheckRegularGr;
	private JButton btnFaOfGr;
	private JButton btnGrOfFa;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartApp window = new StartApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public StartApp() {
		initialize();
		ctrl = new Controller();
		setListeners();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 845, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		readGR = new JTextField();
		readGR.setBounds(10, 60, 86, 20);
		frame.getContentPane().add(readGR);
		readGR.setColumns(10);
		
		btnReadGr = new JButton("Read gr");
		
		btnReadGr.setBounds(121, 59, 89, 23);
		frame.getContentPane().add(btnReadGr);
		
		btnReadFa = new JButton("Read FA");
		
		btnReadFa.setBounds(121, 90, 89, 23);
		frame.getContentPane().add(btnReadFa);
		
		readFA = new JTextField();
		readFA.setBounds(10, 91, 86, 20);
		frame.getContentPane().add(readFA);
		readFA.setColumns(10);
		
		JLabel lblAlpabet = new JLabel("Alpabet:");
		lblAlpabet.setBounds(10, 143, 46, 14);
		frame.getContentPane().add(lblAlpabet);
		
		textFieldAlphabet = new JTextField();
		textFieldAlphabet.setBounds(205, 140, 177, 20);
		frame.getContentPane().add(textFieldAlphabet);
		textFieldAlphabet.setColumns(10);
		
		JLabel lblNonterminals = new JLabel("Non-terminals(Q)/States(N):");
		lblNonterminals.setBounds(10, 179, 185, 14);
		frame.getContentPane().add(lblNonterminals);
		
		textFieldNonTerm = new JTextField();
		textFieldNonTerm.setBounds(205, 176, 177, 20);
		frame.getContentPane().add(textFieldNonTerm);
		textFieldNonTerm.setColumns(10);
		
		JLabel lblStartingSymbolstate = new JLabel("Starting symbol/state(q0/S):");
		lblStartingSymbolstate.setBounds(10, 208, 185, 18);
		frame.getContentPane().add(lblStartingSymbolstate);
		
		textFieldStart = new JTextField();
		textFieldStart.setBounds(205, 207, 177, 20);
		frame.getContentPane().add(textFieldStart);
		textFieldStart.setColumns(10);
		
		textFieldProd = new JTextField();
		textFieldProd.setBounds(205, 243, 177, 20);
		frame.getContentPane().add(textFieldProd);
		textFieldProd.setColumns(10);
		
		JLabel lblProductions = new JLabel("Productions(P)/Transitions(delta):");
		lblProductions.setBounds(10, 246, 194, 14);
		frame.getContentPane().add(lblProductions);
		
		JLabel lblFinalStatesfolny = new JLabel("Final states(F)  -only FA-");
		lblFinalStatesfolny.setBounds(10, 277, 185, 14);
		frame.getContentPane().add(lblFinalStatesfolny);
		
		textFieldFinal = new JTextField();
		textFieldFinal.setBounds(205, 274, 177, 20);
		frame.getContentPane().add(textFieldFinal);
		textFieldFinal.setColumns(10);
		
		JLabel lblFileName = new JLabel("File name");
		lblFileName.setBounds(10, 23, 152, 14);
		frame.getContentPane().add(lblFileName);
		
		btnReadGrammar = new JButton("Read Grammar");
		
		btnReadGrammar.setBounds(10, 315, 152, 23);
		frame.getContentPane().add(btnReadGrammar);
		
		btnReadFiniteAutomate = new JButton("Read Finite Automata");
		
		btnReadFiniteAutomate.setBounds(205, 315, 177, 23);
		frame.getContentPane().add(btnReadFiniteAutomate);
		
		btnShowAlphabetGR = new JButton("Alphabet(gr)");
		
		btnShowAlphabetGR.setBounds(408, 49, 152, 23);
		frame.getContentPane().add(btnShowAlphabetGR);
		
		btnShowNonTerm = new JButton("Non-terminals");
		
		btnShowNonTerm.setBounds(408, 83, 152, 23);
		frame.getContentPane().add(btnShowNonTerm);
		
		btnProductions = new JButton("Productions");
		
		btnProductions.setBounds(408, 117, 152, 23);
		frame.getContentPane().add(btnProductions);
		
		btnFinalStates = new JButton("Final states");
		
		btnFinalStates.setBounds(611, 153, 142, 23);
		frame.getContentPane().add(btnFinalStates);
		
		btnProductionsOf = new JButton("Productions of :");
		btnProductionsOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnProductionsOf.setBounds(408, 151, 152, 23);
		frame.getContentPane().add(btnProductionsOf);
		
		textFieldGetProd = new JTextField();
		textFieldGetProd.setBounds(408, 176, 76, 20);
		frame.getContentPane().add(textFieldGetProd);
		textFieldGetProd.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(406, 208, 264, 166);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnAlpahetfa = new JButton("Alpahet(fa)");
		
		btnAlpahetfa.setBounds(614, 49, 139, 23);
		frame.getContentPane().add(btnAlpahetfa);
		
		btnStates = new JButton("States");
		
		btnStates.setBounds(614, 83, 139, 23);
		frame.getContentPane().add(btnStates);
		
		btnTransitions = new JButton("Transitions");
		
		btnTransitions.setBounds(614, 117, 139, 23);
		frame.getContentPane().add(btnTransitions);
		
		lblGrammar = new JLabel("Grammar");
		lblGrammar.setBounds(408, 11, 152, 14);
		frame.getContentPane().add(lblGrammar);
		
		lblFiniteAutomata = new JLabel("Finite Automata");
		lblFiniteAutomata.setBounds(612, 11, 141, 14);
		frame.getContentPane().add(lblFiniteAutomata);
		
		btnCheckRegularGr = new JButton("Regular gr?");
		
		btnCheckRegularGr.setBounds(697, 219, 122, 23);
		frame.getContentPane().add(btnCheckRegularGr);
		
		btnFaOfGr = new JButton("FA of Gr");
		
		btnFaOfGr.setBounds(697, 256, 89, 23);
		frame.getContentPane().add(btnFaOfGr);
		
		btnGrOfFa = new JButton("Gr of FA");
		
		btnGrOfFa.setBounds(697, 290, 89, 23);
		frame.getContentPane().add(btnGrOfFa);
	}
	
	private void setListeners(){
		btnReadGr.addActionListener(new ActionListener() { //from file
			public void actionPerformed(ActionEvent e) {
				try {
					Grammar gr = ReadInput.readFromFileGr(readGR.getText());
					ctrl.setGr(gr);
					textArea.setText(textArea.getText()+"\n"+gr.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnReadGrammar.addActionListener(new ActionListener() { //read gr from text fields
			public void actionPerformed(ActionEvent e) {
				Grammar gr = ReadInput.readGr(textFieldAlphabet.getText(),textFieldNonTerm.getText(),textFieldStart.getText(),textFieldProd.getText());
				ctrl.setGr(gr);
				textArea.setText(textArea.getText()+"\n"+gr.toString());
			}
		});
		
		btnReadFa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FiniteAutomata fa = ReadInput.readFromFileFa(readFA.getText());
					ctrl.setFa(fa);
					textArea.setText(textArea.getText()+"\n"+fa.toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnReadFiniteAutomate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FiniteAutomata fa = ReadInput.readFa(textFieldAlphabet.getText(),textFieldNonTerm.getText(),textFieldStart.getText(),textFieldFinal.getText(), textFieldProd.getText());
				ctrl.setFa(fa);
				textArea.setText(textArea.getText()+"\n"+fa.toString());
			}
		});
		
		btnProductionsOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String symbol = textFieldGetProd.getText();
				String res = ctrl.getProductionsOfSymbol(symbol);
				textArea.setText(textArea.getText()+"\n"+res);
			}
		});
		
		btnShowAlphabetGR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getGrAlphabet());
			}
		});
		
		btnShowNonTerm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getGrNonTerminals());
			}
		});
		
		btnProductions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getGrProductions());
			}
		});
		
		btnAlpahetfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getFaAlphabet());
			}
		});
		
		btnStates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getFaStates());
			}
		});
		
		btnTransitions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getFaTransitions());
			}
		});
		
		btnFinalStates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(textArea.getText()+"\n"+ctrl.getFaFinalStates());
			}
		});
		
		btnCheckRegularGr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean regular = ctrl.checkIfRegularGr();
				textArea.setText(textArea.getText()+"\n"+"Grammar is regular: "+regular);
			}
		});
		
		btnFaOfGr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //generate FA
				FiniteAutomata fa = new FiniteAutomata(ctrl.getGr());
				textArea.setText(textArea.getText()+"\n"+fa.toString());
			}
		});
		
		btnGrOfFa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grammar gr = new Grammar(ctrl.getFa());
				textArea.setText(textArea.getText()+"\n"+gr.toString());
			}
		});
	}
}
