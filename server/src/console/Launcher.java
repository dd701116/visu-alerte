package console;

import java.awt.EventQueue;
import java.io.IOException;

public class Launcher {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsoleIHM window = new ConsoleIHM();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
