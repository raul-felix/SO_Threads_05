package view;

import controller.ThreadPing;

public class Main {
	
	public static void main(String[] args) {
	
		String[] servidores = { "www.uol.com.br", "www.terra.com.br", "www.google.com.br" };
		String[] nomeServidores = { "UOL", "Terra", "Google" };
		int tamanho = servidores.length;
		
		for (int i = 0; i < tamanho; i++) {
			ThreadPing threadPing = new ThreadPing(servidores[i], nomeServidores[i]);
			threadPing.start();
		}
	}
}

