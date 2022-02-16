package CasaDeMolhos;

public class Marinara extends Sabor {

	private static final long serialVersionUID = 1L;

	public String usar() {
		return "Massas e Petiscos";
	}
	public Marinara(String marca, int peso, String validade) {
		super(marca, peso, validade);
		this.molho = "Marinara";
	}
}
