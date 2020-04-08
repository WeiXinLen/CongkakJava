import java.util.ArrayList;
import java.util.Scanner;

public class Board {
	private int column;
	private int columnUser;
	private int rowUser;
	private static final int row=2;
	private ArrayList<Integer> seed = new ArrayList<Integer>();
	private int score;
	private int count1;
	private int count2;
	private boolean botError = false;
	
	Scanner x = new Scanner(System.in);
	public Board(int col, ArrayList<Integer> _seed ) {
		this.column = col;
		this.seed = _seed;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score=score;
	}
	public void setoneCount(int count) {
		this.count1=count;
	}
	public int getoneCount() {
		return count1;
	}
	public void settwoCount(int count) {
		this.count2=count;
	}
	public int gettwoCount() {
		return count2;
	}
	public void setSeed(ArrayList<Integer> _seed){
		this.seed=_seed;
	}
	public ArrayList<Integer> getSeed(){
		return seed;
	}
	public int getColumnUser() {
		return columnUser;
	}
	public void setColumnUser(int c) {
		this.columnUser=c;
	}
	public int getRowUser() {
		return rowUser;
	}
	public void setRowUser(int r) {
		this.rowUser=r;
	}
	public boolean getBotError() {
		return botError;
	}
	
	public void printBoard() {
		System.out.print("Columns->");
		for(int n=0;n<column;n++){
			System.out.print("\t");
		    System.out.print(n);
		    System.out.print("\t");
		}
		System.out.print("\nRow      ");
		for(int i=0;i<=column;i++) {
			System.out.print("--------------");
		}
		System.out.print("\n0\t|");
		for(int i=0;i<column;i++){
			System.out.print("\t");
		    System.out.print(seed.get(i));
		    System.out.print("\t");
		} 
		System.out.print("\n1\t|");
		for(int i=column;i<column*2;i++){
			System.out.print("\t");
		    System.out.print(seed.get(i));
		    System.out.print("\t");
		}
		
	}
	public void choose(int rows, int columns, ArrayList<Integer> _seed, int _playerTurn) {
		int index;
		index=rows*column+columns;
		int temp=0; 
		temp=_seed.get(index);
		int position = index;
		_seed.set(index,0);
		movingBeads(temp, position, _seed, _playerTurn);
	}
	
	public void movingBeads(int _temp, int _position, ArrayList<Integer> _seed, int _playerTurn) {
		int temp2=0;
		if(_temp==0) {
			System.out.println("That is an invalid move");
			System.out.println("Choose again please, you are given another choice.");
			if (_playerTurn % 2 != 0) {
				validate();
			}
			else {
				printfunction(_playerTurn);
			}
			
		}
		for(int i = 0; i<_temp;i++){
			if (_position==(_seed.size()-1)) {
				_position=column;
				_position-=1;
				temp2 = _seed.get(_position);
				temp2+=1;
				_seed.set(_position, temp2);
			}
			else if (_position==0) {
				_position=column-1;
				_position+=1;
				temp2 = _seed.get(_position);
				temp2+=1;
				_seed.set(_position,  temp2);
			}else if (_position < column) {
				_position-=1;
				temp2 = _seed.get(_position);
				temp2+=1;
				_seed.set(_position, temp2);
			}else if (_position >= column) {
				_position+=1;
				temp2 = _seed.get(_position);
				temp2+=1;
				_seed.set(_position,  temp2);
		}
		}
		
		int temp3=0;
		int temp4;
		int newPosition=0;
		if (_position > 0 && _position < column) {
			newPosition=_position-1;
			temp3 = _seed.get(newPosition);
		}
		else if(_position == 0) {
				newPosition = column;
				temp3 = _seed.get(newPosition);
			}
		else if (_position >= column && _position < (column*2-1)) {
			newPosition = _position+1;
			temp3 = _seed.get(newPosition);
		}
		else if (_position ==  (column*2-1)) {
			newPosition = column-1;
			temp3 = _seed.get(newPosition);
		}
		
		
		if(temp3 != 0) {
			_seed.set(newPosition, 0);
			movingBeads(temp3,newPosition,_seed, _playerTurn);
		}else {
			//playerScore(_temp);
			if (newPosition > 0 && newPosition < column) {
				temp4 = _seed.get(newPosition-1);
				setScore(temp4);
				_seed.set(newPosition-1, 0);
			}
			else if(newPosition == 0) {
					temp4 = _seed.get(column);
					setScore(temp4);
					_seed.set(column, 0);
				}
			else if (newPosition >= column && newPosition < (column*2-1)) {
				temp4 = _seed.get(newPosition+1);
				setScore(temp4);
				_seed.set(newPosition+1, 0);
			}
			else if (newPosition ==  (column*2-1)) {
				temp4 = _seed.get(column-1);
				setScore(temp4);
				_seed.set(column-1, 0);
			}
			
		}
			
	}
	public void CheckBoard(ArrayList<Integer> _seed) {
		int length = _seed.size()/2;
		int onecount = 0;
		int twocount = 0;
		ArrayList<Integer> P1 = new ArrayList<Integer>();
		ArrayList<Integer> P2 = new ArrayList<Integer>();
		for(int n=0;n<length;n++){
			P1.add(_seed.get(n));
		}
		for(int k=(column*2-1);k>=length;k--) {
			P2.add(_seed.get(k));
		}
		
		for(int i=0; i<P1.size(); i++)
		{
		  if(P1.get(i) == 0)
		  {
			  onecount+=1;
			  
		  }
		  setoneCount(onecount);
		}
		
		for(int i=0; i<P2.size(); i++)
		{
		  if(P2.get(i) == 0)
		  {
			  twocount+=1;
		  }
		  settwoCount(twocount);
		
		}
	
	}
	public void printfunction(int _playerTurn) {
		System.out.println();
		printBoard();
		System.out.println("\n\nEnter Column:");
		int c = x.nextInt();
		while(c>column-1) {
			printBoard();
			System.out.println("\nColumn cannot be found, please enter again");
			System.out.println("\n\nEnter Column:");
			c = x.nextInt();
		}
		System.out.println("Enter Row:");
		int r = x.nextInt();
		while(r>row-1) {
			printBoard();
			System.out.println("\nRow cannot be found, please enter again");
			System.out.println("Enter Row:");
			r = x.nextInt();
		}
		if (_playerTurn % 2 == 0 && r==1) { //player1
			System.out.println("you have entered the opponent side player2, it is an invalid move");
			printfunction(_playerTurn);
		}
		
		if (_playerTurn % 2 != 0 && r==0) {
			System.out.println("you have entered the opponent side, it is an invalid move");
			printfunction(_playerTurn);
		}
		if (_playerTurn % 2 == 0 && r==0) {
			setColumnUser(c);
			setRowUser(r);
			choose(r, c, seed, _playerTurn);
		}
		if (_playerTurn % 2 != 0 && r==1) {
			setColumnUser(c);
			setRowUser(r);
			choose(r, c, seed, _playerTurn);
		}

	}
	
	boolean validate() {
		botError = true;
		return true;
	}
	
}
