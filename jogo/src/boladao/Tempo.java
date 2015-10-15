package boladao;

public class Tempo {

	public static double init;
	public static double fim;
	public static double tempo;
	public static boolean teste = true;
	public static boolean teste1;

	public static void rodar(double tempo) {
		if (teste == true) {

			if (teste1 == false) {
				init = System.currentTimeMillis();
				teste1 = true;
			}

			if (teste1 == true) {
				fim = System.currentTimeMillis();
				if (fim - init >= Tempo.tempo) {

					teste = false;
					teste1 = false;
					if (fase.nivel > 5) {
						fase.setEmJogo(false);
						
					}

				} else {
					fim = System.currentTimeMillis();
				}

			}
		}
	}

}
