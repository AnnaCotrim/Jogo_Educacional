package boladao;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class nave {

	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private Image imagem;
	private List<missel> misseis;
	private boolean isVisivel;
	public static int VELOCIDADE = 5;
	public static boolean tiro;

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	
	public void atira() {
		this.misseis.add(new missel(x + largura, y + altura / 2));

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void mexer() {

		x += dx; // -3, 376
		y += dy; // -1, 383

		if (this.x < -3) {
			x = -3;
		}

		if (this.x > 1100) {
			x = 1100;
		}

		if (this.y < -1) {
			y = -1;
		}

		if (this.y > 620) {
			y = 620;
		}

	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if (fase.isEmJogo()) {

			if (codigo == KeyEvent.VK_UP) {
				dy = -VELOCIDADE;
			}

			if (codigo == KeyEvent.VK_DOWN) {
				dy = VELOCIDADE;
			}
			if (codigo == KeyEvent.VK_LEFT) {
				dx = -VELOCIDADE;
			}

			if (codigo == KeyEvent.VK_RIGHT) {
				dx = VELOCIDADE;
			}

			if (codigo == KeyEvent.VK_SPACE) {
				atira();
				tiro = true;
			}
		}

	}

	public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (fase.isEmJogo()) {
			if (codigo == KeyEvent.VK_UP) {
				dy = 0;
			}

			if (codigo == KeyEvent.VK_DOWN) {
				dy = 0;
			}
			if ((codigo == KeyEvent.VK_LEFT)) {
				dx = 0;
			}

			if (codigo == KeyEvent.VK_RIGHT) {
				dx = 0;
			}
		}

	}

	public nave() {
		ImageIcon referencia1 = new ImageIcon("res\\airplane.gif");
		imagem = referencia1.getImage();
		misseis = new ArrayList<missel>();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		this.x = 100;
		this.y = 100;
	}

	public List<missel> getMisseis() {
		return misseis;
	}

}
