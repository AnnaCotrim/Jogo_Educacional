package boladao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
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
	private Image fundo2;
	private Image fundo3;
	private Image inicio;
	private nave naves;
	private int nivel = 1;
	private Timer timer;
	private int controle = 5;
	private int controle1 = 5;

	private int[][] triangulos = { { 1880, 239 }, { 1790, 259 }, { 1760, 150 }, { 1790, 150 }, { 1980, 209 },
			{ 1560, 470 }, { 1510, 70 }, { 1700, 330 }, { 1920, 300 }, { 1856, 328 }, { 1456, 320 }, { 1800, 480 },
			{ 3200, 421 }, { 2400, 320 }, };

	private int[][] circulos = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 1780, 109 }, { 1580, 139 }, { 1880, 239 },
			{ 1790, 259 }, { 1760, 150 }, { 1790, 150 }, { 1980, 209 }, { 1560, 500 }, { 1510, 70 }, { 1930, 159 },
			{ 1590, 80 }, { 1530, 60 }, { 1940, 259 }, { 1990, 430 }, { 1920, 200 }, { 1900, 259 }, };

	private static boolean emJogo;
	private boolean passou;
	private List<Inimigo> inimigos;
	private List<Circulo> inimigos1;
	private long init, fim, init1, fim1;
	private boolean teste, teste2, teste4, gameover;
	private boolean teste3 = true;
	int pontos = 0;

	public static boolean isEmJogo() {
		return emJogo;
	}

	public void setEmJogo(boolean emJogo) {
		this.emJogo = emJogo;
	}

	public fase() {
		setDoubleBuffered(true);
		setFocusable(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon referencia = new ImageIcon("res\\sky.png");
		ImageIcon referencia1 = new ImageIcon("res\\nivel1.png");
		ImageIcon referencia2 = new ImageIcon("res\\space.gif");
		ImageIcon referencia3 = new ImageIcon("res\\space.gif");
		ImageIcon referencia4 = new ImageIcon("res\\gato.png");
		fundo = referencia.getImage();
		fundo1 = referencia1.getImage();
		fundo2 = referencia2.getImage();
		fundo3 = referencia3.getImage();
		inicio = referencia4.getImage();
		naves = new nave();
		inicializaInimigos();
		timer = new Timer(5, this);
		timer.start();

	}

	public void inicializaInimigos() {
		inimigos = new ArrayList<Inimigo>();
		for (int i = 0; i < controle; i++) {
			inimigos.add(new Inimigo(triangulos[i][0], triangulos[i][1]));

		}

		inimigos1 = new ArrayList<Circulo>();
		for (int i = 0; i < controle1; i++) {
			inimigos1.add(new Circulo(circulos[i][0], circulos[i][1]));
		}

	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;

		if (teste3 == true) {
			graficos.drawImage(fundo1, 0, 0, null);
			if (teste4 == false) {
				init1 = System.currentTimeMillis();
				teste4 = true;
			}

			if (teste4 == true) {
				fim1 = System.currentTimeMillis();
				if (fim1 - init1 >= 4000.0) {
					g.dispose();
					teste3 = false;
					teste4 = false;
					setEmJogo(true);
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
				Inimigo in = inimigos.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

			}

			for (int i = 0; i < inimigos1.size(); i++) {
				Circulo in1 = inimigos1.get(i);
				graficos.drawImage(in1.getImagem(), in1.getX(), in1.getY(), this);

			}
			graficos.setFont(new Font("SPACEMAN", Font.PLAIN, 20));
			graficos.setColor(Color.RED);
			graficos.drawString("INIMIGOS: " + controle + controle1, 100, 20);
			graficos.setColor(Color.GREEN);
			graficos.drawString("PONTOS: " + pontos, 400, 20);
			graficos.setFont(new Font("SPACEMAN", Font.PLAIN, 100));

			if (passou == true) {

				graficos.setColor(Color.BLUE);
				graficos.drawString("NIVEL " + nivel, 300, 200);
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

			graficos.setFont(new Font("SPACEMAN", Font.PLAIN, 50));
			graficos.setColor(Color.RED);
			graficos.drawString("VOCE MATOU: " + pontos, 100, 300);

		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (inimigos.size() == 0) {

			nivel += 1;
			controle += 1;

			passou = true;
			inicializaInimigos();

			if (nivel >= 5) {
				Inimigo.VELOCIDADE += 1;
				Circulo.VELOCIDADE += 1;
			}
			if (controle >= inimigos.size()) {
				controle = inimigos.size();
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
			Inimigo in = inimigos.get(i);
			if (in.isVisivel()) {
				in.mexer();
			} else {
				inimigos.remove(i);
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

		naves.mexer();
		checarColisoes();
		repaint();

	}

	public void checarColisoes() {
		Rectangle formaNave = naves.getBounds();
		Rectangle formaInimigo;
		Rectangle formaMissel;
		Rectangle formaCirculo;

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimigo = inimigos.get(i);
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

		List<missel> misseis = naves.getMisseis();
		for (int i = 0; i < misseis.size(); i++) {
			missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				Inimigo tempInimigo = inimigos.get(j);
				formaInimigo = tempInimigo.getBounds();
				if (formaMissel.intersects((formaInimigo))) {

					tempInimigo.setVisivel(false);
					tempMissel.setVisivel(false);

					pontos += 1;
				}
			}

		}

	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			naves.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			naves.keyReleased(e);
		}

	}
}
