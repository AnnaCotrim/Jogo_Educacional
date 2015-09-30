package janelas;

import javax.swing.JFrame;

import boladao.fase;

public class Janela extends JFrame {

	public Janela() {
		add(new fase());
		this.setTitle("Ola");
		this.setSize(1000, 550);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Janela();
	}

}
