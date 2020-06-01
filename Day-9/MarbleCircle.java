/*
 * Represent the circle of marbles as a doubly linked list 
 */
class MarbleCircle {
	
	/*
	 * represent each Marble in the circle as a node in a doubly linked list
	 */
	class Marble {
		
		/*
		 * next represents clockwise, prev represents counter clockwise
		 */
		private Marble next;
		private Marble prev;
		
		private int value;
		
		/*
		 * construct a marble with a given value
		 */
		public Marble(int value) {
			this.value = value;
		}
		
		/*
		 * construct a marble with a given value and prev/next marbles
		 */
		public Marble(int value, Marble prev, Marble next) {
			this.value = value;
			// link this marble with its neighbors
			this.prev = prev;
			this.next = next;
			// link its neighbors with this marble
			prev.next = this;
			next.prev = this;
		}
	}
	
	/*
	 * the header of the list, representing the current marble
	 */
	private Marble current;
	
	/*
	 * store the scores of all the players
	 */
	int[] scores;
	
	/*
	 * current turn of the game
	 */
	int turnNum;
	
	/*
	 * initialize the circle with the first marble 0
	 */
	public MarbleCircle() {
		// insert first marble 0
		current = new Marble(0);
		current.next = current;
		current.prev = current;
	}
	
	/*
	 * start the game with the number of players and turns/max marble value
	 */
	public void start(int numPlayers, int numTurns) {
		turnNum = 0;
		// set up scoreboard
		scores = new int[numPlayers];
		// place one marble for each turn
		while (turnNum < numTurns) {
			placeMarble();
		}
	}
	
	/*
	 * places a Marble, representing a single turn in the game
	 * 
	 * updates the current marble and scores based on rule parameters:
	 *   insert marble between the first and second marbles clockwise
	 *     inserted marble becomes current marble in the circle
	 *   however, if value of inserted marble is multiple of 23, then:
	 *     do not insert marble, and remove marble 7th counterclockwise
	 *     value of uninserted and removed marble adds to current player score
	 *     marble clockwise of removed marble becomes current marble	      
	 */
	private void placeMarble() {
		// increment turn number, which corresponds to marble value
		turnNum++;
		// normal case, marble isn't multiple of 23
		if (turnNum % 23 != 0) {
			// access first and second marbles clockwise, insert between them
			Marble first = current.next;
			Marble second = current.next.next;
			// link with other Marble references and update current
			current = new Marble(turnNum, first, second);
		}
		// special case: update score for player
		else {
			// access 7th marble counterclockwise
			Marble toRemove = current;
			for (int i = 0; i < 7; i++) {
				toRemove = toRemove.prev;
			}
			// remove marble and update scores
			int score = removeMarble(toRemove) + turnNum;
			// calculate index of current player using modulus
			scores[(turnNum - 1) % scores.length] += score;
		}
	}
	
	/*
	 * helper function to remove the 7th marble counterclockwise, and set the
	 * immediate marble clockwise of the removed marble to the current
	 * 
	 * returns value of removed marble
	 */
	private int removeMarble(Marble toRemove) {
		// access neighbors of marble to remove
		Marble prev = toRemove.prev;
		Marble next = toRemove.next;
		// update references of neighbors to mark for garbage collection
		prev.next = next;
		next.prev = prev;
		// update current marble, as per rules
		current = next;
		return toRemove.value;
	}
	
	/*
	 * returns the highest score, for part 1
	 */
	public int getHighScore() {
		int max = Integer.MIN_VALUE;
		for (int score : scores) {
			if (score > max) {
				max = score;
			}
		}
		return max;
	}
}
