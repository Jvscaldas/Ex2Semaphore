package controller;

import java.util.concurrent.Semaphore;

public class ExSemaforo2 extends Thread {

	private String nomePrato;
	private int idPrato, min, max;
	private static int posSaida;

	private Semaphore semaforo;

	public ExSemaforo2(int idPrato, Semaphore semaforo) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
		this.nomePrato = "";
		this.min = 0;
		this.max = 0;
	}

	@Override
	public void run() {
		prato();
		cozimento();
		try {
			semaforo.acquire();
			entrega();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally { // Finally => Linhas que ocorrem ao término do TRY OU do CATCH.
			semaforo.release();
			// ---FIM SEÇÃO CRITICA---
		}
	}

	private void prato() {
		if (idPrato % 2 == 0) {

			this.nomePrato = "Lasanha a bolonhesa";
			this.min = 600;
			this.max = 1200;

		} else {

			this.nomePrato = "Sopa de Cebola";
			this.min = 500;
			this.max = 800;

		}

	}

	private void cozimento() {

		System.out.println("Prato #" + idPrato + ": " + nomePrato + " iniciou o cozimento.");

		int tempoDec = 0, perc = 0;

		while (perc < 100) {
			tempoDec += (int) (Math.random() * (max - min) + min);
			perc = tempoDec / 100;

			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println((perc > 100) ? "Prato #" + idPrato + ": " + nomePrato + " já cozinhou " + 100 + "%."
					: "Prato #" + idPrato + ": " + nomePrato + " já cozinhou " + perc + "%.");

		}
		System.out.println("Prato #" + idPrato + ": " + nomePrato + " está pronto!");
	}

	private void entrega() {
		int tempo = 500;
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		posSaida++;
		System.out.println("Prato #" + idPrato + ": " + nomePrato + " foi entregue!");
	}

}
