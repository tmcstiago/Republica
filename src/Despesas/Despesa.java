package Despesas;

class Despesa {

	private String descricao;
	private float valor;
	
	public Despesa (String desc, float v) {
		descricao = desc;
		valor = v;
	}
	
	public float getValor () {
		return valor;
	}
	
}
