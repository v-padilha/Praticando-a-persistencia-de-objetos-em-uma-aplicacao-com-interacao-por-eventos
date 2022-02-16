package CasaDeMolhos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class CasaDeMolhos {
	private ArrayList<Sabor> sabores;

	public CasaDeMolhos() {
		this.sabores = new ArrayList<Sabor>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Marinara leMarinara (){

		String [] valores = new String [3];
		String [] nomeVal = {"Marca", "Peso", "Validade"};
		valores = leValores (nomeVal);

		int peso = this.retornaInteiro(valores[1]);

		Marinara marinara = new Marinara (valores[0],peso,valores[2]);
		return marinara;
	}

	public Chimichurri leChimichurri (){

		String [] valores = new String [3];
		String [] nomeVal = {"Marca", "Peso", "Validade"};
		valores = leValores (nomeVal);

		int idade = this.retornaInteiro(valores[1]);

		Chimichurri chimichurri = new Chimichurri (valores[0],idade,valores[2]);
		return chimichurri;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaSabores (ArrayList<Sabor> sabores){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\casadeMolhos.dados"));
			for (int i=0; i < sabores.size(); i++)
				outputStream.writeObject(sabores.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Sabor> recuperaSabores (){
		ArrayList<Sabor> saboresTemp = new ArrayList<Sabor>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\casadeMolhos.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Sabor) {
					saboresTemp.add((Sabor) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com molhos NÃO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return saboresTemp;
		}
	}

	public void menuCasaDeMolhos (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Casa De Molhos\n" +
					"Opções:\n" + 
					"1. Entrada de Molhos\n" +
					"2. Exibir Molhos\n" +
					"3. Limpar Molhos\n" +
					"4. Gravar Molhos\n" +
					"5. Recuperar Molhos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Molhos\n" +
						"Opções:\n" + 
						"1. Chimichurri\n" +
						"2. Marinara\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: sabores.add((Sabor)leChimichurri());
				break;
				case 2: sabores.add((Sabor)leMarinara());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Tipo de Molho para entrada NÃO escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (sabores.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com um tipo de molho primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < sabores.size(); i++)	{
					dados += sabores.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (sabores.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com um tipo de molho primeiramente");
					break;
				}
				sabores.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (sabores.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com um tipo de molho primeiramente");
					break;
				}
				salvaSabores(sabores);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				sabores = recuperaSabores();
				if (sabores.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Aplicativo Encerrado com Sucesso");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		CasaDeMolhos pet = new CasaDeMolhos ();
		pet.menuCasaDeMolhos();

	}

}
