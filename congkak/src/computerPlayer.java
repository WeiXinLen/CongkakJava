import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class computerPlayer {
	private ArrayList<Integer> seed = new ArrayList<Integer>();
	private ArrayList<Integer> tempseed =  new ArrayList<Integer>();
	private ArrayList<Integer> evaluateScore = new ArrayList<Integer>();
	private int column, row, smallest;
	
	
	public computerPlayer (ArrayList<Integer> _seed) {
		this.seed = _seed;
	}
	public computerPlayer() {
		
	}
	
	public void setColumn (int value) {
		this.column = value;
	}
	
	public void setRow (int value) {
		this.row = value;
	}
	public void setSmallest(int smallest) {
		this.smallest = smallest;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	public int getSmallest() {
		return smallest;
	}
	public ArrayList<Integer>getEvaluateScore(){
		return evaluateScore;
	}
	public void predictMoves(int c, int r, ArrayList<Integer> s) {
		//calculate the best possible moves for the person
		//compare it with person moves
		//if same print best
		//if lesser print u suck
		//print out words to describe the moves
		this.seed=s;
		predictionMoveAI();
		predictionScore();
		int n=getColumn();
		int z=getRow();
		makeMoveFromScore(getSmallest());
		if(n == c && z == r) {
			Random rand = new Random();
			int x = rand.nextInt(2);
			if(x==0) {
				System.out.println("Congrats,your IQ is 200!!!");
			}else if(x==1) {
				System.out.println("GOOD STUFF!!!");
			}else {
				System.out.println("Wise Choice, LETS SEE IF YOU CAN KEEP UP!!!!");
			}
		}else if(getColumn()==c && getRow()==r){
			Random rand = new Random();
			int x = rand.nextInt(2);
			if(x==0) {
				System.out.println("What a useless move, play properly please ");
			}else if(x==1) {
				System.out.println("Are you sure you are playing the game correctly?");
			}else {
				System.out.println("Horrible Choice, MY VICTORY AWIATS");
			}
		}else {
			System.out.println("Interesting choice, meh~");
		}
		
		
	}

	public void predictionMoveAI() {
		int x=seed.size()/2;
		for(int d = 0; d<1; d++) {
			tempseed.addAll(seed);
		}
		for(int i = seed.size()/2 ; i<seed.size();i++) {
			
			ArrayList<Integer> tempseed2 = new ArrayList<Integer>();
			for(int a= 0 ; a< seed.size(); a++) {
				tempseed2.add(tempseed.get(a));
			}
			
			int temp = seed.get(x);
			int position = x;
			
			if( temp == 0) {
				evaluateScore.add(0);
			}else {
				predictionAI(temp, position, tempseed2);
			}
			x++;
		}
	}	
	
	public void predictionAI(int _temp, int _position, ArrayList<Integer> _seed) {
			int temp2 = 0;
			int column = _seed.size()/2;
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
				} else if (_position >= column) {
					_position+=1;
					temp2 = _seed.get(_position);
					temp2+=1;
					_seed.set(_position,  temp2);
			}
			}
			
			int temp3=0;
			int temp4=0;
			int newPosition = 0;
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
			//-------------------------------------------------
			if(temp3 != 0) {
				_seed.set(newPosition, 0);
				predictionAI(temp3,newPosition,_seed);
				
			}else {
				if (newPosition > 0 && newPosition < column) {
					temp4 = _seed.get(newPosition-1);
				}
				else if(newPosition == 0) {
						temp4 = _seed.get(column);
					
					}
				else if (newPosition >= column && newPosition < (column*2-1)) {
					temp4 = _seed.get(newPosition+1);
				
				}
				else if (newPosition == (column*2-1)) {
					temp4 = _seed.get(column-1);
			
				}
				evaluateScore.add(temp4);
			}
			
			
}
		
		public void predictionScore() {
			int biggest = -9999;
			int smallest = 9999;
			for (int a = 0; a < evaluateScore.size(); a++) {
				int temp = evaluateScore.get(a);
				if (temp > biggest) {
					biggest = temp;
				}
				if (temp < smallest) {
					smallest = temp;
					setSmallest(smallest);
				}
			} 
			makeMoveFromScore(biggest);
		}
		
		public void makeMoveFromScore(int value) {
			int index = evaluateScore.indexOf(value); 
			int maxColumn = seed.size()/2;
			int column;
			int row;
			row = 1;
			column = (index - row*maxColumn) + maxColumn;
			
			setColumn(column);
			setRow(row);
		}
		
		public void fixError(ArrayList<Integer> _seed) {
			int biggest = -9999;
			int index = 0;
			for (int a = _seed.size()/2; a < _seed.size(); a++) {
				int temp = _seed.get(a);
				if (temp > biggest) {
					biggest = temp;
					index = a;
				}
			}
			int maxColumn = _seed.size()/2;
			int column;
			int row;
			
			row = 1;
			column = (index - row*maxColumn);
			
			setColumn(column);
			setRow(row);
		}
}
