package Despesas;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

public class Categoria {

	List<Subcategoria> sub = new LinkedList <Subcategoria> ();
	public String nomeRepublica;
	public String nome;
	private float valorTotal;
	
	
	public Categoria (String n, String rep) {
		nome = n;
		nomeRepublica = rep;
	}
	
	public String getNome () {
		return nome;
	}
	
	
		
	public void nomeSub (String nomeRepublica) {
		if (sub.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma Sub-Categoria cadastrada para esta Categoria",
										  "Republica " + nomeRepublica, JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		String[] subPossiveis = new String[sub.size()+1];
		subPossiveis[0] = "--- Selecione uma opção ---";
		
		Iterator<Subcategoria> it = sub.iterator();
		int i = 1;
		while (it.hasNext()) {
			Subcategoria s = it.next();
			
			subPossiveis[i] = s.getNome();
			i++;
		}
		String nomeSubSelecionada;
		do {
			nomeSubSelecionada = (String) JOptionPane.showInputDialog(null, "Selecione a Categoria", 
																		"Republica "+ nomeRepublica, 
																		JOptionPane.QUESTION_MESSAGE, null,
																		subPossiveis, subPossiveis[0]);
		} while (nomeSubSelecionada == null || nomeSubSelecionada.equals(subPossiveis));
		
		Subcategoria subSelecionada = null;
		
		it = sub.iterator();
		while (it.hasNext()) {
			subSelecionada = it.next();
			if (subSelecionada.getNome().equals(nomeSubSelecionada)) {
				break;
			}
				
		}
		Subcategoria subRemovida = subSelecionada;
		
		subSelecionada.cadastrarDespesa();
		
		if (sub.contains(subRemovida)) {
			sub.remove(subRemovida);
			sub.add(subSelecionada);
		}
	}
	
	
	public void CadastroSubcategoria (String nomeSub) {
		Subcategoria s = new Subcategoria (nomeSub, nomeRepublica);
		
		sub.add(s);
	}
	
	
	public float TotalDespesasCat () {
		valorTotal = 0;
		
		Iterator <Subcategoria> it = sub.iterator();
		while (it.hasNext()) {
			Subcategoria c = it.next();
			valorTotal += c.TotalDespesasSub();
		}
		
		return valorTotal;
	}
	
	
	
}
