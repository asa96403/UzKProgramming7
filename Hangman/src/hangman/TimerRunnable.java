package hangman;

import javax.swing.SwingUtilities;

public class TimerRunnable extends Thread {
	private final Timer game;

	public TimerRunnable(Timer game) {
		this.game = game;
	}

	@Override
	public void run() {
	// TODO: Implement the countdown loop
		int time=game.timeLeft;
		for(int i=0; i<time; i++) {
			try {
				// - Decrease timeLeft every second
				Thread.sleep(1000);
				game.timeLeft--;
				// - Update the time label using SwingUtilities.invokeLater ()
				SwingUtilities.invokeLater(() -> game.updateDisplay());
				// - End the game if time runs out
				if(game.timeLeft==0) SwingUtilities.invokeLater(() -> game.endGame(false));
			} catch (InterruptedException e) {
				System.err.println("The timer failed. Ending game!");
				System.err.println(e.getMessage());
				SwingUtilities.invokeLater(() -> game.endGame(false));
				e.printStackTrace();
			}
		}
	}
}
