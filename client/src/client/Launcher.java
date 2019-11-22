package client;

import java.awt.EventQueue;

public class Launcher {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientIHM window = new ClientIHM();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
