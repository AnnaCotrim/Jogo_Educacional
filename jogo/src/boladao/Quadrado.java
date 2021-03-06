package boladao;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Quadrado {

	private Image imagem;
	private Image imagem1;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA_TELA = 1200;
	public static int VELOCIDADE = 1;

	public Quadrado(int x, int y) {
		this.x = x;
		this.y = y;

		if (fase.nivel > 2) {
			ImageIcon referencia = new ImageIcon("res\\quadrado_vermelho.gif");
			imagem = referencia.getImage();
		} 
		if (fase.nivel > 4) {
			ImageIcon referencia = new ImageIcon("res\\quadrado_verde.gif");
			imagem = referencia.getImage();
		}

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		isVisivel = true;
	}

	public void mexer() {
		if (fase.isEmJogo()) {
			if (this.x < 0) {
				this.x = LARGURA_TELA;
			} else {
				this.x -= VELOCIDADE;
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

	public Image getImagem1() {
		return imagem1;
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
