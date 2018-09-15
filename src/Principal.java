import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import Despesas.Categoria;
import Pessoas.Aluno;

public class Principal {

	public static String nomeRepublica;
	
	private static List <Aluno> moradores = new LinkedList<Aluno> ();
	private static List <Categoria> cat = new LinkedList<Categoria> ();
	
	private static float valorTotalDespesas;
	private static float valorTotalRenda;

		
	public static void main (String[] args) {
		
		LerRepublica();
		
		if (nomeRepublica == null) {
			do {
				nomeRepublica = JOptionPane.showInputDialog("Insira o nome da Republica");
			}while (nomeRepublica == null || nomeRepublica.isEmpty());
			GravarRepublica();
		}
		else {
			boolean ler = LerMoradores();
			
			if (moradores.size()>0 && ler) {
				JOptionPane.showMessageDialog(null, "Moradores carregados com Sucesso");
			}
		}
		
		Object opcoesPossiveis[] = {"--- Selecione uma opção ---",
									"Cadastrar Morador",
									"Remover Morador",
									"Cadastrar Despesa",
									"Cadastrar Categoria",
									"Cadastrar Sub-Categoria",
									"Realizar distribuição de despesas",
									"Sair"};
		
		Object opcaoSelecionada;
		
		do {
			opcaoSelecionada = JOptionPane.showInputDialog(null, "Selecione a opção desejada",
					                                              "Republica " + nomeRepublica,
					                                              JOptionPane.QUESTION_MESSAGE, null,
					                                              opcoesPossiveis, opcoesPossiveis[0]);
			if(opcaoSelecionada == null)
				opcaoSelecionada = "Sair";

			switch (opcaoSelecionada.toString()) {
			case "Cadastrar Morador":
				CadastrarMorador ();
				break;
			case "Remover Morador":
				RemoverMorador();
				break;
			case "Cadastrar Despesa":
				CadastrarDespesa ();
				break;
			case "Cadastrar Categoria":
				CadastrarCategoria ();
				break;
			case "Cadastrar Sub-Categoria":
				CadastrarSubCategoria ();
				break;
			case "Realizar distribuição de despesas":
				DistribuicaoDespesas ();
				break;
			case "Sair":
				VerificacaoSaida();
			default:
				break;
			}
			
		} while (!opcaoSelecionada.toString().equalsIgnoreCase(opcoesPossiveis[7].toString()));
		
	}
	
		
	private static Categoria BuscaCategoria (String nomeCategoria) {
		Categoria c = null;
		
		Iterator <Categoria> it = cat.iterator();
		while (it.hasNext()) {
			c = it.next();
			if (c.getNome().equals(nomeCategoria))
				break;
		}
		
		return c;
	}
	
	
	
	private static boolean CadastrarMorador () {
		boolean resposta = false;
		
		String nome = null;
		do {
			nome= JOptionPane.showInputDialog(null, "Insira o nome do Aluno",
											  "Republica " + nomeRepublica,
											  JOptionPane.QUESTION_MESSAGE);
		}while (nome == null || nome.isEmpty());
		
		
		String email = null;
		do {
			email = JOptionPane.showInputDialog(null,"Insira o e-mail do Aluno",
												"Republica " + nomeRepublica,
												JOptionPane.QUESTION_MESSAGE);
		}while (email == null || email.isEmpty());
		
		
		float renda = 0.0f;
		String stringRenda = null;
		do{
			stringRenda = JOptionPane.showInputDialog(null,"Insira a renda do Aluno",
													  "Republica " + nomeRepublica,
													  JOptionPane.QUESTION_MESSAGE);
		} while (stringRenda == null || stringRenda.isEmpty());
		renda = Float.parseFloat(stringRenda);
		
		
		Aluno a = new Aluno(nome, email, renda);
		moradores.add(a);
				
		return resposta;
	}

	
	private static void RemoverMorador () {
		boolean resposta = false;
		
		String[] nomeAlunos = new String[moradores.size()+1];
		nomeAlunos[0] = "--- Selecione uma opção ---";
		
		int i = 0;
		Iterator<Aluno> it = moradores.iterator();
		while (it.hasNext()) {
			i++;
			Aluno a = it.next();
			nomeAlunos[i] = a.getNome();
		}
		
		String alunoSelecionado;
		
		do {
			alunoSelecionado = (String) JOptionPane.showInputDialog(null, "Selecione o Morador que deseja remover", 
																		"Republica "+ nomeRepublica,
																		JOptionPane.QUESTION_MESSAGE, null,
																		nomeAlunos, nomeAlunos[0]);
		}while (alunoSelecionado == null || alunoSelecionado.equals(nomeAlunos[0]));
		
		it = moradores.iterator();
		Aluno a = null;
		while (it.hasNext()) {
			i++;
			a = it.next();
			if (alunoSelecionado.equals(a.getNome()))
				break;
		}
		
		if (moradores.contains(a)) 
			resposta = moradores.remove(a);
		
		if (resposta)
			JOptionPane.showMessageDialog(null, "Morador removido com Sucesso",
												"Republica " + nomeRepublica,
												JOptionPane.YES_NO_CANCEL_OPTION);
		else
			JOptionPane.showMessageDialog(null, "Falha na remoção do morador",
										  "Republica " + nomeRepublica,
										  JOptionPane.OK_CANCEL_OPTION);
	}

	
	private static boolean CadastrarDespesa () {
		boolean resposta = false;
		
		if (cat.size() == 0) {
			JOptionPane.showMessageDialog(null, "Nenhuma Categoria cadastrada",
										  "Republica " + nomeRepublica, JOptionPane.OK_CANCEL_OPTION);
			return resposta;
		}
	
		String[] catPossiveis = new String[cat.size()+1];
		catPossiveis[0] = "--- Selecione uma opção ---";
		
		int i = 1;		
		Iterator <Categoria> it = cat.iterator();
		while (it.hasNext()) {
			Categoria c = it.next();
			catPossiveis[i] = c.getNome();
			i++;
		}
		
		String nomeCatSelecionada;
		do {
			nomeCatSelecionada = (String) JOptionPane.showInputDialog(null, "Selecione a Categoria", 
																"Republica "+ nomeRepublica,
																JOptionPane.QUESTION_MESSAGE, null,
																catPossiveis, catPossiveis[0]);
		}while (nomeCatSelecionada == null || nomeCatSelecionada.equals(catPossiveis[0]));
		
		Categoria catSelecionada = BuscaCategoria(nomeCatSelecionada);
		Categoria catRemovida = catSelecionada;
		
		catSelecionada.nomeSub(nomeRepublica);
		
		if (cat.contains(catRemovida)) {
			cat.remove(catRemovida);
			cat.add(catSelecionada);
		}
		
		return resposta;
	}
	
	
	private static boolean CadastrarCategoria () {
		boolean resposta = false;
		
		String nomeCategoria;
		do {
			nomeCategoria = JOptionPane.showInputDialog(null, "Insira o nome da Categoria",
														"Republica " + nomeRepublica,
														JOptionPane.QUESTION_MESSAGE);
		} while (nomeCategoria == null || nomeCategoria.isEmpty());
		
		Categoria c = new Categoria(nomeCategoria, nomeRepublica);
		
		cat.add(c);
		
		
		return resposta;
	}
	
	
	private static boolean CadastrarSubCategoria () {
		boolean resposta = false;
		
		String[] catPossiveis = new String[cat.size()+1];
		catPossiveis[0] = "--- Selecione uma opção ---";
		
		int i = 1;		
		Iterator <Categoria> it = cat.iterator();
		
		while (it.hasNext()) {
			Categoria c = it.next();
			catPossiveis[i] = c.getNome();
			i++;
		}
		
		String mensagem = "Selecione a Categoria em que você deseja vincular a subcategoria";
		String nomeCatSelecionada;
		
		do {
			nomeCatSelecionada = (String) JOptionPane.showInputDialog(null, mensagem, 
																"Republica "+ nomeRepublica,
																JOptionPane.QUESTION_MESSAGE, null,
																catPossiveis, catPossiveis[0]);
		} while(nomeCatSelecionada == null || nomeCatSelecionada.equals(catPossiveis[0]));
		
		Categoria catSelecionada = BuscaCategoria (nomeCatSelecionada);
		Categoria catRemovida = catSelecionada;
		
		String nomeSub;
		do {
			nomeSub = (String) JOptionPane.showInputDialog(null, "Insira o nome da nova Sub-Categoria",
														   "Republica " + nomeRepublica,
														   JOptionPane.QUESTION_MESSAGE);
		} while (nomeSub == null || nomeSub.isEmpty());
		
		catSelecionada.CadastroSubcategoria(nomeSub);
		
		if(cat.equals(catRemovida)) {
			cat.remove(catRemovida);
			cat.add(catSelecionada);
		}
		
		return resposta;
	}
	
	
	private static boolean DistribuicaoDespesas () {
		boolean resposta = false;
		
		TotalDespesas();
		Object distribuicoesPossiveis[] = {"--- Selecione uma opção ---",
										 "Distribuição igualitária",
										 "Distribuição proporcional"};
		
		String distribuicaoSelecionada;
		do {
			distribuicaoSelecionada = (String) JOptionPane.showInputDialog(null, "Qual a forma de distruibição?",
																			  "Republica " + nomeRepublica,
																			  JOptionPane.QUESTION_MESSAGE,
																			  null, distribuicoesPossiveis,
																			  distribuicoesPossiveis[0]);
		} while(distribuicaoSelecionada == null || distribuicaoSelecionada.equals(distribuicoesPossiveis[0]));
			
		switch (distribuicaoSelecionada) {
		case "Distribuição igualitária":
			DistribuicaoIgualitaria();
			break;
		case "Distribuição proporcional":
			DistribuicaoProporcional();
		}
		
		return resposta;
	}
	
	
	private static float TotalDespesas () {
		valorTotalDespesas = 0;
		
		Iterator <Categoria> it = cat.iterator();
		while (it.hasNext()) {
			Categoria c = it.next();
			valorTotalDespesas += c.TotalDespesasCat();
		}
		
		return valorTotalDespesas;
	}
	
	
	private static float TotalRenda() {
		valorTotalRenda = 0;
		
		Iterator <Aluno> it = moradores.iterator();
		while(it.hasNext()) {
			Aluno a = it.next();
			valorTotalRenda += a.getRenda();
		}
		
		return valorTotalRenda;
	}
	
	
	private static boolean DistribuicaoIgualitaria() {
		boolean resposta = false;
		
		int qntMoradores = moradores.size();
		String distribuicao = "";
		
		int i = 0;
		Iterator <Aluno> it = moradores.iterator();
		while (it.hasNext()) {
			i++;
			Aluno a = it.next();
			distribuicao += a.getNome() + ": R$ " + valorTotalDespesas/qntMoradores;
			if (i<moradores.size()) 
				distribuicao += "\n";
		}

		JOptionPane.showMessageDialog(null, distribuicao, "Republica " + nomeRepublica,
									  JOptionPane.YES_NO_CANCEL_OPTION);
		
		return resposta;
	}
	
	
	public static boolean DistribuicaoProporcional() {
		boolean resposta = false;
		
		TotalRenda();
		String distribuicao = "";
		
		int i = 0;
		Iterator <Aluno> it = moradores.iterator();
		while (it.hasNext()) {
			i++;
			Aluno a = it.next();
			distribuicao += a.RegraProporcional(valorTotalRenda, valorTotalDespesas);
			
			if(i<moradores.size())
				distribuicao += "\n";
		}
		
		JOptionPane.showMessageDialog(null, distribuicao, "Republica " + nomeRepublica,
									  JOptionPane.YES_NO_CANCEL_OPTION);
		
		return resposta;
	}
	
	
	public static void VerificacaoSaida() {
		boolean gravar = GravarMoradores ();
		if (moradores.size()>0) {
			if (gravar)
				JOptionPane.showMessageDialog(null, "Moradores armazenados com Sucesso", "Republica " + nomeRepublica,
											  JOptionPane.YES_NO_CANCEL_OPTION);
		}
	}
	
	
	
	public static boolean GravarMoradores() {
		boolean resposta = false;		
		FileWriter arquivo = null;
		
		try {
			arquivo = new FileWriter ("C:\\Users\\USER\\eclipse-workspace\\Republica\\src\\aluno.txt");
		} catch(IOException e) {
			e.printStackTrace ();
		}
		
		BufferedWriter buffer = new BufferedWriter(arquivo);
		
		Iterator <Aluno> it = moradores.iterator();
		while (it.hasNext()) {
			Aluno a = it.next();
			String str = "";
			
			str += a.getNome() + ";";
			str += a.getEmail() + ";";
			str += a.getRenda() + ";";
			
			try {
				buffer.write(str);
				buffer.newLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			buffer.close();
			resposta = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	
	
	public static boolean LerMoradores() {
		boolean resposta = false;
		
		FileReader arquivo = null;
		BufferedReader buffer;
		
		try {
			arquivo = new FileReader("C:\\Users\\USER\\eclipse-workspace\\Republica\\src\\aluno.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		buffer = new BufferedReader(arquivo);
		
		String line = "";
		try {
			line = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String teste = line;
		
		if (teste != null) {
			do {
				String[] campos = line.split(";");
				
				float renda = Float.parseFloat(campos[2]);
				Aluno a = new Aluno(campos[0], campos[1], renda);
				
				resposta = moradores.add(a);
				
				try {
					line = buffer.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (line != null);
		}
		
		return resposta;
	}
	
	
	
	private static void LerRepublica() {
		FileReader arquivo = null;
		BufferedReader buffer;
		
		try {
			arquivo = new FileReader("C:\\Users\\USER\\eclipse-workspace\\Republica\\src\\republica.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		buffer = new BufferedReader(arquivo);
		
		String line = "";
		try {
			line = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String inf = line;
		nomeRepublica = inf;
	}
	

	private static void GravarRepublica() {
		FileWriter arquivo = null;
		
		try {
			arquivo = new FileWriter ("C:\\Users\\USER\\eclipse-workspace\\Republica\\src\\republica.txt");
		} catch(IOException e) {
			e.printStackTrace ();
		}
		
		BufferedWriter buffer = new BufferedWriter(arquivo);
		
		String str = "";
		str += nomeRepublica;
			
		try {
			buffer.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		try {
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
