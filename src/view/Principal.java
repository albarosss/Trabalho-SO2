package view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import controller.*;

public class Principal {

	public static void main(String[] args) 
	{
		IArquivosController cArquivos = new ArquivosController();
		String arquivo = "D:\\TEMP\\Cadastro.csv";
		int opc = 0;
		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção:   \n \n 1 - Verificar se diretório existe\n" +
																						" 2 - Verificar registro\n" +
																						" 3 - Mostra cadastro\n" +
																						" 4 - Inserir cadastro\n" +
																						" 9 - Sair"));
			switch(opc) {
				case 1: 
					try {
						cArquivos.verificaDirTemp();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
				case 2:	
					try {
						if(cArquivos.verificaRegistro(arquivo, Integer.parseInt(JOptionPane.showInputDialog("Digite o código a ser procurado")))) {
							System.out.println("O código está cadastrado no registro");
						}else {
							System.out.println("O código não se encontra no registro");
						}
					} catch (NumberFormatException | HeadlessException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 3:
					try {
						cArquivos.imprimeCadastro(arquivo, 
								Integer.parseInt(JOptionPane.showInputDialog("Digite o código a ser impresso")));
					} catch (NumberFormatException | HeadlessException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						cArquivos.insereCadastro(arquivo, 
								Integer.parseInt(JOptionPane.showInputDialog("Digite o código do individuo")),
								JOptionPane.showInputDialog("Digite o nome do individuo "),
								JOptionPane.showInputDialog("Digite o email do individuo"));
					} catch (NumberFormatException | HeadlessException | IOException e) {
						e.printStackTrace();
					}
					break;
				case 9:
					JOptionPane.showMessageDialog(null, "Encerrando");
					System.exit(0);
				default: 
					JOptionPane.showMessageDialog(null, "Valor inválido");
			}
		}
	}
}