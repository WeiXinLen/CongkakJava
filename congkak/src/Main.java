import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void generateGame(int n, int s, int modeAI) {
		ArrayList<Integer> seed = new ArrayList<Integer>();
		for (int i=0;i<(n*2);i++) {
			seed.add(s);
		}
		int playerTurn = 0;
		int countMove1=0;
		int countMove2=0;
		Board one = new Board(n,seed);
		Score sc = new Score();
		boolean a = true;
		while(a) {
			playerTurn+=1;
			if (modeAI == 1) {
				if (playerTurn % 2 != 0) {
					System.out.println( );
					System.out.println("Player1 turn :");
					countMove1++;
					System.out.println("Number of Moves:" +countMove1);
					computerPlayer checkbot = new computerPlayer();
					one.printfunction(sc.getTurn());
					System.out.println( );
					checkbot.predictMoves(one.getRowUser(),one.getColumnUser(),one.getSeed());
					//System.out.println(one.getSeed());
				}else {
					System.out.println( );
					System.out.println("\nBot turn: ");
					one.printBoard();
					countMove2++;
					System.out.println("\nNumber of Moves:" +countMove2);
					computerPlayer bot = new computerPlayer(one.getSeed());
					bot.predictionMoveAI();
					bot.predictionScore();
					one.choose(bot.getRow(), bot.getColumn(), one.getSeed(), sc.getTurn());
					System.out.println( );
					if(one.getBotError() == true) {
						bot.fixError(one.getSeed());
						one.choose(bot.getRow(), bot.getColumn(), one.getSeed(), sc.getTurn());
					}
					System.out.println("The Bot has selected: ");
					System.out.println("Col: " + bot.getColumn());
					System.out.println("Row: " + bot.getRow());
				}
			}else {
				if (playerTurn % 2 != 0) {
					System.out.println("Player1 turn :");
					countMove1++;
					System.out.println("Number of Moves:" +countMove1);
				}else {
					System.out.println("Player2 turn: ");
					countMove2++;
					System.out.println("Number of Moves:" +countMove2);
				}
				one.printfunction(sc.getTurn());
			}
			//System.out.println(one.getScore());
			sc.addScore(one.getScore(),playerTurn);
			System.out.println( );
			System.out.println("Players Scores: ");
			sc.print();
			one.CheckBoard(seed);
			Display z = new Display();
			//System.out.println(one.getoneCount());
			//System.out.println(one.gettwoCount());
			if (one.getoneCount() == n || one.gettwoCount() == n) {
				if(sc.getScore1()>sc.getScore2()) {
					if(modeAI == 1) {
						z.displayPlayerWin();
					System.out.println("Player 1 has won the game, git gud bot");
					
					}
				}else if (sc.getScore1() == sc.getScore2()){
					if(modeAI == 1) {
						z.displayDraw();
					System.out.println("Its a draw");
					}
				}else{
					if(modeAI == 1) {
						System.out.println("Perfectly Balanced as all things should be.");
						z.displayBotWin();
						
					}else {
						System.out.println("Player 2 has won the game, git gud player1");
					}
					
				}
				a = false;
			}
		}
	}
	
	public static void main(String [] args) {
		
		Scanner x = new Scanner(System.in);
		Display d = new Display();
		d.displayGameName();
		d.displayLevels();
		
		System.out.print("\nChoose the level: ");
		int n;
		int s;
		int input = x.nextInt();
		switch(input){
			case (1):
				n=7;
				s=4;
				generateGame(n,s, 0);
				break;
			
			case (2):
				System.out.println("Enter the number of columns");
				n = x.nextInt();
				System.out.println("Enter the number of seeds");
				s = x.nextInt();
				generateGame(n,s, 0);
				break;
				
			case (3):
				System.out.println("Enter the number of columns");
				n = x.nextInt();
				System.out.println("Enter the number of seeds");
				s = x.nextInt();
				generateGame(n, s, 1);
				break;
		}
		
	}
}
