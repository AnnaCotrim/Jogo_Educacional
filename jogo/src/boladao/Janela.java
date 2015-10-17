package boladao;

import javax.swing.JFrame;

public class Janela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Janela() {
		add(new fase());
		this.setTitle("Formas");
		this.setSize(1366, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Janela();
	}

}
