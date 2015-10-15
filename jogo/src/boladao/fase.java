package boladao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class fase extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fundo;
	private Image fundo1;
	private Image inicio;
	private nave naves;
	static int nivel = 1;
	private Timer timer;
	private int controle = 5;
	private int controle1 = 5;

	private int[][] triangulos = { { 1880, 239 }, { 1790, 259 }, { 1760, 150 }, { 1790, 150 }, { 1980, 209 },
			{ 1560, 390 }, { 1510, 70 }, { 1700, 330 }, { 1920, 300 }, { 1856, 328 }, { 1456, 320 }, { 1800, 400 },
			{ 3200, 221 }, { 2400, 320 }, };

	private int[][] circulos = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 1780, 109 }, { 1580, 139 }, { 1880, 239 },
			{ 1790, 259 }, { 1760, 150 }, { 1790, 150 }, { 1980, 209 }, { 1560, 500 }, { 1510, 70 }, { 1930, 159 },
			{ 1590, 80 }, { 1530, 60 }, { 1940, 259 }, { 1990, 430 }, { 1920, 200 }, { 1900, 259 }, };

	/*
	 * private int[][] quadrado = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, {
	 * 1780, 109 }, { 1580, 139 }, { 1880, 239 }, { 1790, 259 }, { 1760, 150 },
	 * { 1790, 150 }, { 1980, 209 }, { 1560, 500 }, { 1510, 70 }, { 1930, 159 },
	 * { 1590, 80 }, { 1530, 60 }, { 1940, 259 }, { 1990, 430 }, { 1920, 200 },
	 * { 1900, 259 }, };
	 */

	private static boolean emJogo;
	private boolean passou;
	private List<Triangulo> inimigos;
	private List<Circulo> inimigos1;
	// private List<Quadrado> inimigos2;
	private long init, fim, init1, fim1;
	private boolean teste, teste2, teste4, gameover;
	private boolean teste3 = true;
	private boolean nivel2;
	int pontos = 0;

	public static boolean isEmJogo() {
		return emJogo;
	}

	public static void setEmJogo(boolean emJogo) {
		fase.emJogo = emJogo;
	}

	public fase() {
		setDoubleBuffered(true);
		setFocusable(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon referencia = new ImageIcon("res\\sky.png");
		ImageIcon referencia1 = new ImageIcon("res\\nivel1.png");
		ImageIcon referencia4 = new ImageIcon("res\\nivel2.png");
		fundo = referencia.getImage();
		fundo1 = referencia1.getImage();
		inicio = referencia4.getImage();
		naves = new nave();
		inicializaInimigos();
		timer = new Timer(5, this);
		timer.start();

	}

	public void inicializaInimigos() {
		inimigos = new ArrayList<Triangulo>();
		for (int i = 0; i < controle; i++) {
			inimigos.add(new Triangulo(triangulos[i][0], triangulos[i][1]));

		}

		inimigos1 = new ArrayList<Circulo>();
		for (int i = 0; i < controle1; i++) {
			inimigos1.add(new Circulo(circulos[i][0], circulos[i][1]));
		}

		/*
		 * inimigos2 = new ArrayList<Quadrado>(); for (int i = 0; i < controle1;
		 * i++) { inimigos2.add(new Quadrado(quadrado[i][0], quadrado[i][1])); }
		 */

	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;

		if (teste3 == true) {
			if (nivel2 == false)
				graficos.drawImage(fundo1, 0, 0, null);
			if (nivel2 == true) {
				graficos.drawImage(inicio, 0, 0, null);

			}

			if (teste4 == false) {
				init1 = System.currentTimeMillis();
				teste4 = true;
			}

			if (teste4 == true) {
				fim1 = System.currentTimeMillis();
				if (fim1 - init1 >= 3000.0) {
					g.dispose();
					teste3 = false;
					teste4 = false;
					if (nivel2 == false) {
						setEmJogo(true);
					}
					if (nivel2 == true) {
						g.dispose();
						setEmJogo(false);
					}
				} else {
					fim1 = System.currentTimeMillis();
				}

			}
		}

		if (isEmJogo()) {

			graficos.drawImage(fundo, 0, 0, null);
			// graficos.drawImage(fundo1, 498, 0, null);
			// graficos.drawImage(fundo2, 0, 574, null);
			// graficos.drawImage(fundo3, 498, 574, null);
			graficos.drawImage(naves.getImagem(), naves.getX(), naves.getY(), this);
			List<missel> misseis = naves.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				missel m = (missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < inimigos.size(); i++) {
				Triangulo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}

			for (int i = 0; i < inimigos1.size(); i++) {
				Circulo in1 = inimigos1.get(i);
				graficos.drawImage(in1.getImagem(), in1.getX(), in1.getY(), this);

			}

			/*
			 * for (int i = 0; i < inimigos2.size(); i++) { Quadrado in1 =
			 * inimigos2.get(i); graficos.drawImage(in1.getImagem(), in1.getX(),
			 * in1.getY(), this);
			 * 
			 * }
			 */
			graficos.setFont(new Font("DK Petit Four", Font.PLAIN, 40));
			graficos.setColor(Color.BLACK);
			graficos.drawString("PONTOS: " + pontos, 400, 40);
			graficos.setFont(new Font("DK Petit Four", Font.PLAIN, 100));

			if (passou == true) {

				graficos.setColor(Color.DARK_GRAY);
				graficos.drawString("BATERIA " + nivel, 300, 200);
				teste = true;
				if (teste == true) {
					if (teste2 == false) {
						init = System.currentTimeMillis();
						teste2 = true;
					}

					if (teste2 == true) {
						fim = System.currentTimeMillis();
						if (fim - init >= 3000.0) {
							g.dispose();
							passou = false;
							teste = false;
							teste2 = false;
						} else {
							fim = System.currentTimeMillis();
						}

					}
				}

			}

		} else if (gameover) {

			// ImageIcon end = new ImageIcon("res\\gameover.jpg");
			// graficos.drawImage(end.getImage(), 0, 0, null);

			graficos.setFont(new Font("DK Petit Four", Font.PLAIN, 100));
			graficos.setColor(Color.BLUE);
			graficos.drawString("VOCE ACERTOU: " + pontos, 150, 300);

		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ((inimigos.size() == 0) || (inimigos1.size() == 0)) {

			nivel += 1;
			controle += 1;
			passou = true;
			inicializaInimigos();

			if (nivel == 5) {
				// nivel2 = true;
				// teste3 = true;
				Triangulo.VELOCIDADE += 1;
				Circulo.VELOCIDADE += 1;

			}

			if (nivel == 10) {
				// nivel2 = true;
				// teste3 = true;
				Triangulo.VELOCIDADE += 1;
				Circulo.VELOCIDADE += 1;

			}
			if (controle >= 13) {
				controle = 13;
			}

			if (controle1 >= inimigos1.size()) {
				controle1 = inimigos1.size();
			}

		}

		List<missel> misseis = naves.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			missel m = (missel) misseis.get(i);
			if (m.isVisivel()) {
				m.mexer();
			} else {
				misseis.remove(i);
			}

		}

		for (int i = 0; i < inimigos.size(); i++) {
			Triangulo in = inimigos.get(i);
			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
			}

		}

		/*
		 * for (int i = 0; i < inimigos2.size(); i++) { Quadrado in =
		 * inimigos2.get(i); if (in.isVisivel()) { in.mexer(); } else {
		 * inimigos2.remove(i); }
		 * 
		 * }
		 */

		for (int i = 0; i < inimigos1.size(); i++) {
			Circulo in1 = inimigos1.get(i);
			if (in1.isVisivel()) {
				in1.mover();
			} else {
				inimigos1.remove(i);
			}
		}

		naves.mexer();
		checarColisoes();
		repaint();

	}

	public void checarColisoes() {
		Rectangle formaNave = naves.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;
		Rectangle formaCirculo;
		// Rectangle formaQuadrado;

		for (int i = 0; i < inimigos.size(); i++) {
			Triangulo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaNave.intersects(formaInimigo)) {
				naves.setVisivel(false);
				tempInimigo.setVisivel(false);
				setEmJogo(false);
				gameover = true;
			}
		}

		for (int i = 0; i < inimigos1.size(); i++) {
			Circulo tempInimigo = inimigos1.get(i);
			formaCirculo = tempInimigo.getBounds();

			if (formaNave.intersects(formaCirculo)) {
				naves.setVisivel(false);
				tempInimigo.setVisivel(false);
				setEmJogo(false);
				gameover = true;
			}
		}

		/*
		 * for (int i = 0; i < inimigos2.size(); i++) { Quadrado tempInimigo =
		 * inimigos2.get(i); formaQuadrado = tempInimigo.getBounds();
		 * 
		 * if (formaNave.intersects(formaQuadrado)) { naves.setVisivel(false);
		 * tempInimigo.setVisivel(false); setEmJogo(false); gameover = true; } }
		 */

		List<missel> misseis = naves.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				Triangulo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();
				if (formaMissel.intersects((formaInimigo))) {
					if (nivel <= 3) {
						tempInimigo.setVisivel(false);
						tempMissel.setVisivel(false);

						pontos += 1;
					} else if (nivel > 3) {
						tempInimigo.setVisivel(true);
						tempMissel.setVisivel(true);
					}

				}
			}

			for (int j = 0; j < inimigos1.size(); j++) {
				Circulo tempInimigo = inimigos1.get(j);
				formaInimigo = tempInimigo.getBounds();
				if (formaMissel.intersects((formaInimigo))) {

					if (nivel > 3) {
						tempInimigo.setVisivel(false);
						tempMissel.setVisivel(false);

						pontos += 1;
					} else if (nivel < 3) {
						tempInimigo.setVisivel(true);
						tempMissel.setVisivel(true);
					}

				}

			}
		}

	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			naves.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			naves.keyReleased(e);
		}

	}
}
