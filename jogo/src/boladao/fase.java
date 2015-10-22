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
	private int controle = 15;
	private int controle1 = 5;
	private int controle2 = 5;

	private int[][] triangulos = { { 2112, 551 }, { 2566, 171 }, { 1493, 87 }, { 3169, 138 }, { 2595, 213 },
			{ 2638, 101 }, { 1395, 265 }, { 3452, 31 }, { 2334, 155 }, { 3591, 609 }, { 3970, 413 }, { 3205, 152 },
			{ 3580, 545 }, { 3133, 323 }, { 2812, 572 }, { 3974, 393 }, { 2236, 47 }, { 3465, 589 }, { 3335, 553 },
			{ 3392, 28 }, { 2976, 267 }, { 2047, 332 }, { 3612, 327 }, { 1408, 500 }, { 3677, 237 }, { 1495, 321 },
			{ 3710, 527 }, { 3178, 496 }, { 2713, 432 }, { 3497, 134 }, { 1992, 574 }, { 3027, 280 }, { 1709, 312 },
			{ 1399, 20 }, { 1640, 19 }, { 3902, 70 }, { 3902, 70 }, { 3449, 228 }, { 3596, 269 }, { 1911, 379 },
			{ 1681, 490 }, { 2396, 35 }, { 3865, 527 }, { 2405, 546 }, { 2088, 453 }, { 2479, 504 }, { 3911, 54 },
			{ 2095, 54 }, { 2493, 613 }, { 3051, 585 }, };

	private int[][] circulos = { { 3535, 17 }, { 2951, 259 }, { 3900, 32 }, { 2057, 428 }, { 2748, 134 }, { 1378, 375 },
			{ 2265, 477 }, { 3106, 184 }, { 3639, 514 }, { 2329, 124 }, { 3701, 113 }, { 2553, 402 }, { 2423, 554 },
			{ 2968, 159 }, { 3717, 335 }, { 3760, 588 }, { 2977, 29 }, { 3378, 170 }, { 3198, 537 }, { 1915, 550 },
			{ 2636, 472 }, { 3648, 518 }, { 3066, 272 }, { 3860, 398 }, { 3826, 112 }, { 1805, 226 }, { 3056, 274 },
			{ 2619, 174 }, { 2099, 547 }, { 2047, 55 }, { 3636, 387 }, { 2189, 328 }, { 3968, 352 }, { 2705, 427 },
			{ 3319, 214 }, { 3264, 311 }, { 1561, 594 }, { 2094, 176 }, { 2835, 543 }, { 2788, 353 }, { 3722, 70 },
			{ 1575, 605 }, { 3276, 24 }, { 1566, 11 }, { 2412, 182 }, { 3847, 525 }, { 3211, 567 }, };

	private int[][] quadrado = { { 3222, 523 }, { 1645, 569 }, { 2941, 287 }, { 2795, 185 }, { 2426, 502 },
			{ 3197, 293 }, { 3152, 573 }, { 3786, 522 }, { 2811, 77 }, { 2078, 481 }, { 3479, 450 }, { 1531, 281 },
			{ 3360, 196 }, { 2222, 604 }, { 2791, 410 }, { 2997, 369 }, { 2174, 194 }, { 3111, 375 }, { 2513, 386 },
			{ 3683, 564 }, { 3595, 329 }, { 3894, 419 }, { 2848, 93 }, { 2755, 521 }, { 1698, 620 }, };

	private static boolean emJogo;
	private boolean passou;
	private List<Triangulo> inimigos;
	private List<Circulo> inimigos1;
	private List<Quadrado> inimigos2;
	private long init, fim, init1, fim1;
	private boolean teste, teste2, teste4, gameover;
	private boolean teste3 = true;
	private boolean nivel1 = true;
	private boolean nivel2 = false;
	private boolean nivel3 = false;
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
		ImageIcon referencia = new ImageIcon("res\\sky.gif");
		ImageIcon referencia1 = new ImageIcon("res\\nivel1.gif");
		ImageIcon referencia4 = new ImageIcon("res\\nivel2.gif");
		fundo = referencia.getImage();
		fundo1 = referencia1.getImage();
		inicio = referencia4.getImage();
		naves = new nave();
		inicializaInimigos();
		PlaySound p = new PlaySound();
		p.playSound("res\\space.wav");
		timer = new Timer(5, this);
		timer.start();

	}

	public void inicializaInimigos() {

		if (nivel <= 3) {
			inimigos = new ArrayList<Triangulo>();
			for (int i = 0; i <= 10; i++) {
				inimigos.add(new Triangulo(triangulos[i][0], triangulos[i][1]));

			}

		}

		inimigos1 = new ArrayList<Circulo>();
		for (int i = 0; i < controle1; i++) {
			inimigos1.add(new Circulo(circulos[i][0], circulos[i][1]));
		}

		if (nivel > 3) {
			inimigos2 = new ArrayList<Quadrado>();
			for (int i = 0; i < controle2; i++) {
				inimigos2.add(new Quadrado(quadrado[i][0], quadrado[i][1]));
			}

		}

	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;

		if (teste3 == true) {
			if (nivel1 == true) {
				
				graficos.drawImage(fundo1, 0, 0, null);
				nivel1 = false;
			}

			if (nivel2 == true) {
				setEmJogo(false);
				graficos.drawImage(inicio, 0, 0, null);
				nivel2 = false;

			}

			if (nivel3 == true) {
				setEmJogo(false);
				graficos.drawImage(fundo1, 0, 0, null);
				nivel3 = false;

			}

			if (teste4 == false) {
				init1 = System.currentTimeMillis();
				teste4 = true;
			}

			if (teste4 == true) {
				fim1 = System.currentTimeMillis();
				if (fim1 - init1 >= 5000.0) {
					g.dispose();
					setEmJogo(true);
					teste3 = false;
					teste4 = false;

				} else {
					fim1 = System.currentTimeMillis();
				}

			}
		}

		if (isEmJogo()) {
			tocarMusica();
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(naves.getImagem(), naves.getX(), naves.getY(), this);
			List<missel> misseis = naves.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				missel m = (missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			if (nivel <= 3) {
				for (int i = 0; i < inimigos.size(); i++) {
					Triangulo in = inimigos.get(i);
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}
			}

			for (int i = 0; i < inimigos1.size(); i++) {
				Circulo in1 = inimigos1.get(i);
				graficos.drawImage(in1.getImagem(), in1.getX(), in1.getY(), this);

			}

			if (nivel > 3) {
				for (int i = 0; i < inimigos2.size(); i++) {
					Quadrado in1 = inimigos2.get(i);
					graficos.drawImage(in1.getImagem(), in1.getX(), in1.getY(), this);

				}
			}

			graficos.setFont(new Font("DK Petit Four", Font.PLAIN, 60));
			graficos.setColor(Color.BLACK);
			graficos.drawString("PONTOS: " + pontos, 550, 50);
			graficos.setFont(new Font("DK Petit Four", Font.PLAIN, 150));

			/*
			 * if (passou == true) {
			 * 
			 * graficos.setColor(Color.RED); graficos.drawString("NIVEL " +
			 * nivel, 500, 350); teste = true; if (teste == true) {
			 * 
			 * if (teste2 == false) { init = System.currentTimeMillis(); teste2
			 * = true; }
			 * 
			 * if (teste2 == true) { fim = System.currentTimeMillis(); if (fim -
			 * init >= 3000.0) { g.dispose(); passou = false; teste = false;
			 * teste2 = false;
			 * 
			 * } else { fim = System.currentTimeMillis(); }
			 * 
			 * } }
			 * 
			 * }
			 */

		} else if (gameover) {

			// ImageIcon end = new ImageIcon("res\\gameover.jpg");
			// graficos.drawImage(end.getImage(), 0, 0, null);

			graficos.setFont(new Font("DK Petit Four", Font.PLAIN, 120));
			graficos.setColor(Color.RED);
			graficos.drawString("VOCE ACERTOU: " + pontos, 150, 300);

		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ((inimigos.size() == 0) && (nivel <= 3)) {

			nivel += 1;
			controle += 1;
			passou = true;
			inicializaInimigos();
		}
		if ((inimigos1.size() == 0) && (nivel > 3 || nivel < 6)) {

			nivel += 1;
			controle1 += 1;
			passou = true;
			inicializaInimigos();

		}

		if (nivel == 4) {
			nivel2 = true;
			teste3 = true;
			nivel += 1;
		}

		if (nivel == 6) {
			nivel3 = true;
			teste3 = true;
			nivel += 1;
		}

		if (nivel >= 6) {
			if (inimigos2.size() == 0) {
				nivel += 1;
				controle1 += 1;
				controle += 1;
				passou = true;
				inicializaInimigos();
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

		if (nivel <= 3) {
			for (int i = 0; i < inimigos.size(); i++) {
				Triangulo in = inimigos.get(i);
				if (in.isVisivel()) {
					in.mexer();
				} else {
					inimigos.remove(i);
				}

			}
		}

		for (int i = 0; i < inimigos1.size(); i++) {
			Circulo in1 = inimigos1.get(i);
			if (in1.isVisivel()) {
				in1.mover();
			} else {
				inimigos1.remove(i);
			}
		}

		if (nivel > 3) {
			for (int i = 0; i < inimigos2.size(); i++) {
				Quadrado in3 = inimigos2.get(i);
				if (in3.isVisivel()) {
					in3.mexer();
				} else {
					inimigos2.remove(i);
				}
			}

		}

		naves.mexer();
		checarColisoes();
		repaint();

	}

	public void tocarMusica() {

		PlaySound p = new PlaySound();
		// p.playSound("res\\space.wav");

		if (nave.tiro == true) {

			PlaySound c = new PlaySound();
			c.playSound("res\\laser.wav");
			nave.tiro = false;
		}

	}

	public void checarColisoes() {
		Rectangle formaNave = naves.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;
		Rectangle formaCirculo;
		Rectangle formaQuadrado;

		if (nivel <= 3) {
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

		if (nivel > 3) {
			for (int i = 0; i < inimigos2.size(); i++) {
				Quadrado tempInimigo = inimigos2.get(i);
				formaQuadrado = tempInimigo.getBounds();

				if (formaNave.intersects(formaQuadrado)) {
					naves.setVisivel(false);
					tempInimigo.setVisivel(false);
					setEmJogo(false);
					gameover = true;
				}
			}
		}

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

					if ((nivel > 3) && (nivel < 6)) {
						controle1 = 15;
						tempInimigo.setVisivel(false);
						tempMissel.setVisivel(false);

						pontos += 1;
					} else if (nivel <= 3) {
						tempInimigo.setVisivel(true);
						tempMissel.setVisivel(true);
					}

				}

			}

			if (nivel > 3) {
				for (int j = 0; j < inimigos2.size(); j++) {
					Quadrado tempInimigo = inimigos2.get(j);
					formaInimigo = tempInimigo.getBounds();
					if (formaMissel.intersects((formaInimigo))) {
						if (nivel >= 6) {
							controle2 = 15;
							controle1 = 5;
							tempInimigo.setVisivel(false);
							tempMissel.setVisivel(false);
							pontos += 1;

						} else if ((nivel > 3) && (nivel < 6)) {
							tempInimigo.setVisivel(true);
							tempMissel.setVisivel(true);

						}

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
