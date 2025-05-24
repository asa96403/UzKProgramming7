package hangman;

import javax.swing.SwingUtilities;

public class TimerRunnable implements Runnable {
	private final Timer game;

	public TimerRunnable(Timer game) {
		this.game = game;
	}

	@Override
	public void run() {
// TODO: Implement the countdown loop
// - Decrease timeLeft every second
// - Update the time label using SwingUtilities.invokeLater ()
// - End the game if time runs out
	}
}
