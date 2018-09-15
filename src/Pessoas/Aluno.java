package Pessoas;

public class Aluno {
	
	private String nome;
	private String email;
	private float renda;
	float distribuicaoIgualitaria;
	float distribuicaoProporcional;
	
	public Aluno (String n, String e, float r) {
		nome = n;
		email = e;
		renda = r;
	}
	
	
	public float getRenda() {
		return renda;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public String getEmail () {
		return email;
	}
	
	
	public String RegraProporcional(float totalRenda, float totalDespesas) {
		String resposta = "";
		
		float percContribuicao = renda/totalRenda;
		float valorContribuicao = totalDespesas*percContribuicao;
		
		resposta += nome + ": R$ " + valorContribuicao;
		return resposta;
	}
	
}
