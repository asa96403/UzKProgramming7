package hangmanFinal;

import javax.swing.SwingUtilities;

public class TimerRunnable extends Thread {
	private final HangmanGame game;

	public TimerRunnable(HangmanGame game) {
		this.game = game;
	}

	@Override
	public void run() {
		int time=game.timeLeft;
		long timeControl = 0;
		for(int i=0; i<time; i++) {
			try {
				// - Decrease timeLeft every second
				Thread.sleep(1000-timeControl);
				timeControl = System.currentTimeMillis();
				game.timeLeft--;
				// - Update the time label using SwingUtilities.invokeLater ()
				SwingUtilities.invokeLater(() -> game.updateDisplay());
				// - End the game if time runs out
				if(game.timeLeft==0) SwingUtilities.invokeLater(() -> game.endGame(false));
				timeControl = System.currentTimeMillis() - timeControl;
			} catch (InterruptedException e) {
				System.err.println("The timer failed. Ending game!");
				System.err.println(e.getMessage());
				SwingUtilities.invokeLater(() -> game.endGame(false));
				e.printStackTrace(); 
			}
		}
	}
}
