package hangman;

public class Timer extends Game{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Thread timerThread;
	protected boolean timerRunning;
	// TODO: Override startGame () to also start the timer
	@Override
	public void startGame() {
		super.startGame();
		startTimer();
	}
	protected void startTimer() {
		TimerRunnable timer = new TimerRunnable(this);
		timer.start();
	}
	// TODO: Implement startTimer () to create and start the timer thread
}
