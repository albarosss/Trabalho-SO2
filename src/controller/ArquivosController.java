package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ArquivosController implements IArquivosController{
	
	public ArquivosController() {
		super();
	}

	@Override
	public void verificaDirTemp() throws IOException {
		File dir = new File("D:\\TEMP");
		File arq = new File("D:\\TEMP\\Cadastro.csv");
		if(!dir.exists()) {
			dir.mkdir();
			System.out.println("Diretório criado");
		}else {
			System.out.println("Diretório já existente");
		}
		if(!arq.exists()) {
			FileWriter fileWriter = new FileWriter(arq, false);
			fileWriter.close();
			System.out.println("Arquivo csv criado");
		}else {
			System.out.println("Arquivo já existente");
		}
	}

	@Override
	public boolean verificaRegistro(String arquivo, int codigo) throws IOException {
		File arq = new File(arquivo);
		if(arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);//Abre o arquivo
			InputStreamReader leitor = new InputStreamReader(fluxo);//Converte e lê o arquivo
			BufferedReader buffer = new BufferedReader(leitor);//Colocava o conteudo dentro do buffer
			String linha = buffer.readLine();
			while (linha != null) { //Procurando a lnha vazia (EOF - End of File)
				if(linha.contains(Integer.toString(codigo))) {
					buffer.close();//Fecha o buffer
					leitor.close();//Fecha o leitor
					fluxo.close();//Fecha o arquivo
					return true;
				}
				linha = buffer.readLine();
			}
			buffer.close();//Fecha o buffer
			leitor.close();//Fecha o leitor
			fluxo.close();//Fecha o arquivo
		}else {
			throw new IOException("Arquivos Inválido");
		}
		return false;
	}

	@Override
	public void imprimeCadastro(String arquivo, int codigo) throws IOException {
		if(!verificaRegistro(arquivo, codigo)) {
			System.out.println("O código não foi encontrado no registro");
		}else {
			File arq = new File(arquivo);
			if(arq.exists() && arq.isFile()) {
				FileInputStream fluxo = new FileInputStream(arq);//Abre o arquivo
				InputStreamReader leitor = new InputStreamReader(fluxo);//Converte e lê o arquivo
				BufferedReader buffer = new BufferedReader(leitor);//Colocava o conteudo dentro do buffer
				String linha = buffer.readLine();
				while (linha != null) { //Procurando a lnha vazia (EOF - End of File)
					if(linha.contains(Integer.toString(codigo))) {
						String [] strLinha = new String [3];
						strLinha = linha.split(";");
						System.out.println("Código: " + strLinha[0] + "\nNome: " + strLinha[1] + "\nEmail: " + strLinha[2]); 
						break;
					}
					linha = buffer.readLine();
				}
				buffer.close();//Fecha o buffer
				leitor.close();//Fecha o leitor
				fluxo.close();//Fecha o arquivo
			}else {
				throw new IOException("Arquivos Inválido");
			}
		}
	}

	@Override
	public void insereCadastro(String arquivo, int codigo, String nome, String email) throws IOException {
		File arq = new File(arquivo);
		if(!verificaRegistro(arquivo, codigo)) {
			String conteudo = ("\r\n" + Integer.toString(codigo) + ";" + nome + ";" + email);
			FileWriter fileWriter = new FileWriter(arq, true);//Abre o arquivo e define se vai fazer append ou write
			PrintWriter print = new PrintWriter(fileWriter);//Inicializa a variavel que realizará a escrita
			print.write(conteudo);//Escreve o conteudo no arquivo
			print.flush();//Finaliza a escrita
			print.close();//Fecha a escrita
			fileWriter.close();//Fecha o arquivo
		}else {
			System.out.println("O individuo já se encontra no registro.");
		}
	}
}