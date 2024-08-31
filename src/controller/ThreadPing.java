package controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ThreadPing extends Thread {

	String servidor;
	String nomeServidor;
	
	public ThreadPing(String servidor, String nomeServidor) {
		this.servidor = servidor;
		this.nomeServidor = nomeServidor;
	}
	
	@Override
	public void run() {
		String osName = os().toLowerCase();
		if (osName.contains("linux")) {
			try {
				StringBuilder comando = new StringBuilder("ping -4 -c 10 ").append(servidor);
				Process p = Runtime.getRuntime().exec(comando.toString().split(" "));
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					if (linha.contains("time=")) {
						String[] linhaArr = linha.split("time=");
						System.out.println(nomeServidor + ": " + linhaArr[1]);
					} else if (linha.contains("avg" )) {
						String[] linhaArr = linha.split("/");
						System.out.println("FIM --> Tempo médio do " + nomeServidor + ": " + linhaArr[4]);
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else {
			System.out.println("Sistema operacional inválido");
		}
	}
	
	private String os() {
		return System.getProperty("os.name");
	}
}
