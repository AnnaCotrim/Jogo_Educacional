package boladao;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class missel {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA_TELA = 1366;
	private static final int VELOCIDADE = 3;

	public missel(int x, int y) {
		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon("res\\bullet.gif");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		isVisivel = true;
	}

	public void mexer() {
		if (fase.isEmJogo()) {
			this.x += VELOCIDADE;
			if (this.x > LARGURA_TELA) {
				isVisivel = false;
			}
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
