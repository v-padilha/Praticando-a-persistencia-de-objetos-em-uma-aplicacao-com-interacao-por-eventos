package CasaDeMolhos;

public class Chimichurri extends Sabor {

	private static final long serialVersionUID = 1L;

	public String usar() {
		return "Carne Vermelha e Aves";
	}
	public Chimichurri(String marca, int peso, String validade) {
		super(marca, peso, validade);
		this.molho = "Chimichurri";
	}
}
