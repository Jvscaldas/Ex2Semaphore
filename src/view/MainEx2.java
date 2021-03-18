package view;

import java.util.concurrent.Semaphore;

import controller.ExSemaforo2;

public class MainEx2 {

	public static void main(String[] args) {
		int permissao = 1;
		Semaphore semaforo = new Semaphore(permissao);

		for (int idPrato = 0; idPrato < 5; idPrato++) {
			Thread t = new ExSemaforo2(idPrato, semaforo);
			t.start();
		}
	}

}
