public class Hex implements BoardGame {

	private int[][] board; // 2D Board. 0 - empty, 1 - Player 1, 2 - Player 2

	private int n1, n2; // height and width of board

	private WeightedQuickUnionUF wqu; // Union Find data structure to keep track
										// of unions and calculate winner

	private int currentPlayer; // Current player in the game, initialised to 1

	public Hex(int n1, int n2) // create N-by-N grid, with all sites blocked
	{
		this.n1 = n1;
		this.n2 = n2;
		currentPlayer = 1;

		BoardGame bg = null;// TODO: Create instance of board
		wqu = new WeightedQuickUnionUF(n1*n1);// TODO: Create instance
												// WeightedQuickUnionUF class

		board = new int[n1][n2];

		printBoard(n1);

	}

	/**
	 * printBoard() - Prints a readable version of the board to screen
	 * 
	 */
	public void printBoard(int n1) {

		for (int i = 0; i < n1; i++) {

			StdOut.print("[ ");
			for (int j = 0; j < n1; j++) {

				StdOut.print(board[i][j] + " ");
			}
			StdOut.print("]\n");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BoardGame#takeTurn(int, int)
	 */
	@Override
	public void takeTurn(int x, int y) {

		printBoard(n1);

		int nowPlaying = currentPlayer;
		// TODO: check coordinates are valid
		if (x < n1 && y < n2) {
		} else {
			StdOut.print(" Those are invalid coordinates!!! Please try again \n");
			StdOut.print(" \n");
			StdOut.print(" Enter x and y location \n");

			int newX = StdIn.readInt();
			int newY = StdIn.readInt();

			takeTurn(newX, newY);
		}

		// TODO: check if location is free and set to player's value(1 or 2).
		if (board[x][y] == 0) {

			board[x][y] = nowPlaying;
		} else {
			StdOut.print(" That location is not free \n");
			StdOut.print(" Choose another location \n");

			StdOut.print(" Enter x and y location \n");

			int newX = StdIn.readInt();
			int newY = StdIn.readInt();

			takeTurn(newX, newY);
		}
		
		checkNeighbours(x,y,nowPlaying);
		
//		if(wqu.connected(board[0][1], board[1][1]))
//		{StdOut.print(" Yes Boy \n");}

		// TODO: calculate location and neighbours location in
		// WeightedQuickUnionUF data structure

		// TODO: create unions to neighbour sites in WeightedQuickUnionUF that
		// also contain current players value

		// TODO: if no winner get the next player

		nextPlayer();
	}

	public void checkNeighbours(int x, int y, int nowPlaying) {
		
		int playerNumber = nowPlaying;
		
		int neighbour = 0;
		
		int playerPosition = (n1 * y) + x;

		if (y > 0 && (board[x][y - 1] == playerNumber)) {
			
			neighbour = (n1 * x) + y - 1;
			
			wqu.union(playerPosition, neighbour);

		}
		if (x > 0 && y < n2 - 1 && (board[x - 1][y + 1] == playerNumber)) {
			
			neighbour = (n1 * (x - 1)) + y + 1;
			
			wqu.union(playerPosition, neighbour);

		}
		if (y < n2 - 1 && (board[x][y + 1] == playerNumber)) {
			
			neighbour = (n1 * x) + y + 1;
			
			wqu.union(playerPosition, neighbour);

		}
		if (x < n1 - 1 && y <= n2 && (board[x + 1][y] == playerNumber)) {
			
			neighbour = (n1 * (x + 1)) + y;
			
			wqu.union(playerPosition, neighbour);

		}
		if (x < n1 - 1 && y > 1 && (board[x + 1][y - 1] == playerNumber)) {
			
			neighbour = (n1 * (x + 1)) + y - 1;
			
			wqu.union(playerPosition, neighbour);

		}
		if (x > 0 && (board[x - 1][y] == playerNumber)) {
			
			neighbour = (n1 * (x - 1)) + y;
			
			wqu.union(playerPosition, neighbour);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BoardGame#getCurrentPlayer()
	 */
	@Override
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BoardGame#getBoard()
	 */
	@Override
	public int[][] getBoard() {
		return board;
	}

	private void nextPlayer() {
		if (currentPlayer == 1)
			currentPlayer = 2;
		else
			currentPlayer = 1;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BoardGame#isWinner()
	 */
	@Override
	public boolean isWinner() {

		// TODO:check if there is a connection between either side of the board.
		// You can do this by using the 'virtual site' approach in the
		// percolation test.
		return false;
	}

	/**
	 * THIS IS OPTIONAL: Modify the main method if you wish to suit your
	 * implementation. This is just an example of a test implementation. For
	 * example you may want to display the board after each turn.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		BoardGame hexGame = new Hex(11, 11);

		while (!hexGame.isWinner()) {
			System.out.println("It's player " + hexGame.getCurrentPlayer()
					+ "'s turn");
			System.out.println("Enter x and y location:");
			int x = StdIn.readInt();
			int y = StdIn.readInt();

			hexGame.takeTurn(x, y);

		}

		System.out.println("It's over. Player " + hexGame.getCurrentPlayer()
				+ " wins!");

	}

	/**
	 * pickStartPLayer() - Randomly selects the player that will start the game
	 * 
	 */
	public void pickStartPlayer() {
		int playerSelect = (int) (Math.random() * 2) + 1;
		currentPlayer = playerSelect;
	}

}
