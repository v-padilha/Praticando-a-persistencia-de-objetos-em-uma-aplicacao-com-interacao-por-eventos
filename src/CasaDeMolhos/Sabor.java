package CasaDeMolhos;

import java.io.Serializable;

public abstract class Sabor implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String marca;
	private   int peso;
	private   String validade;
	protected String molho;
	
	public Sabor(String marca, int peso, String validade) {
		this.marca = marca;
		this.peso = peso;
		this.validade = validade;
	}
	public String toString() {
		String retorno = "";
		retorno += "Molho: "  + this.molho  + "\n";
		retorno += "Marca: "     + this.marca     + "\n";
		retorno += "Peso: "    + this.peso    + " gramas\n";
		retorno += "Validade: "     + this.validade     + " meses\n";
		retorno += "Indicado para uso com: "  + usar()        + "\n";
		return retorno;
	}
	public abstract String usar();
}
