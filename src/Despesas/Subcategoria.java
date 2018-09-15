package Despesas;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

class Subcategoria {

	List<Despesa> despesa = new LinkedList <Despesa> ();
	public String nomeRepublica;
	String nome;
	private float valorTotal;
	
	
	public Subcategoria (String n, String rep) {
		nome = n;
		nomeRepublica = rep;
	}
	
	public String getNome () {
		return nome;
	}
	
	
	public void cadastrarDespesa () {
		
		String descricao ;
		do {
			descricao = (String)JOptionPane.showInputDialog(null, "Qual a descrição da Despesa?",
															"Republica " + nomeRepublica,
															JOptionPane.QUESTION_MESSAGE);
		} while (descricao == null || descricao.isEmpty());
		
		float valor;
		String stringValor;
		do{
			stringValor = JOptionPane.showInputDialog(null, "Qual o valor da Despesa?",
													  "Republica " + nomeRepublica,
													  JOptionPane.QUESTION_MESSAGE);
		} while(stringValor == null || stringValor.isEmpty());
		valor = Float.parseFloat(stringValor);
		
		Despesa d = new Despesa(descricao, valor);
		
		despesa.add(d);
	}
	
	
	public float TotalDespesasSub () {
		valorTotal = 0;
		
		Iterator <Despesa> it = despesa.iterator();
		while (it.hasNext()) {
			Despesa d = it.next();
			valorTotal += d.getValor();
		}
		
		return valorTotal;
	}
	
}
