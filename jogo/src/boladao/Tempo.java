package boladao;

import java.awt.Graphics;

public class Tempo {

	public static double init =0;
	public static double fim =0;
	public static double tempo;
	public static boolean teste = true;
	public static boolean teste1;
	public static boolean passar;

	public static void rodar(double tempo) {
		if (teste == true) {

			if (teste1 == false) {
				init = System.currentTimeMillis();
				teste1 = true;
			}

			if (teste1 == true) {
				fim = System.currentTimeMillis();
				if (fim - init >= tempo) {

					passar = true;
					teste = false;

				}

			} else {
				fim = System.currentTimeMillis();
			}

		}
	}
}
