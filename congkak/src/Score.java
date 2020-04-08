import java.util.ArrayList;
import java.util.Arrays;

public class Score {
	private int score;
	private int playerTurn;
	private int player1Score;
	private int player2Score;
	
	public Score() {
		
	}
	public Score(int score,int playerTurn) {
		this.score = score;
		this.playerTurn=playerTurn;
	}

	public void addScore(int score,int playerTurn) {
		if(playerTurn % 2 != 0) {
			player1Score += score;
			this.playerTurn = playerTurn;
			}
		if(playerTurn % 2 == 0) {
			player2Score += score;
			this.playerTurn = playerTurn;
			}
	}
	
	public int getScore1() {
			return player1Score;
	}
	public int getScore2() {
			return player2Score;
	}
	
	public int getTurn() {
		return playerTurn;
	}
	
	public void print() {
		System.out.println("Player 1:" + getScore1());
		System.out.println("Player 2:" + getScore2());
	}
}
