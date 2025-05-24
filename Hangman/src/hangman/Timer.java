package hangman;

public class Timer extends Game{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Thread timerThread;
	protected boolean timerRunning;
	
	@Override
	/**
	 * starts game and timer
	 * @author aabert
	 */
	public void startGame() {
		super.startGame();
		startTimer();
	}
	/**
	 * starts a new timer thread
	 * @author aabert
	 */
	protected void startTimer() {
		TimerRunnable timer = new TimerRunnable(this);
		timer.start();
	}
	// TODO: Implement startTimer () to create and start the timer thread
}
