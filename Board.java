import java.util.Arrays;
public class Board {
	private char[][] board;
	private int rows;
	private int columns;
	private char player1 = '$';
	private char player2 = '@';
	
	public static void main(String[] args) {
		Board connect4 = new Board(7,8);

		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
		System.out.println(connect4.play(2,7));
	}
	

	//Constructors
	public Board() {
		this(6,7);
	}

	
	public Board(int row, int col) {
		if (row < 4 || col < 4) {
			row = 6;
			col = 7;
		}

 		board = new char[row][col];
		rows = row;
		columns = col;

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				board[x][y] = ' ';
			}
		}
	}
	

	//Getter for number of rows
	public int getNumRows() {
		return rows;
	}
	//Getter for number of columns
	public int getNumCols() {
		return columns;
	}
	//Getter for character representing player 1
	public char getPlayerOne() {
		return player1;
	}
	//Getter for character representing player 2
	public char getPlayerTwo() {
		return player2;
	}
	//Setter for character representing player 1 
	public void setPlayerOne(char o) {
		player1 = o;
	}
	//Setter for character representing player 2
	public void setPlayerTwo(char t) {
		player2 = t;
	}

	public char getToken(int row, int col) {
		if ((row < 0) || (row > rows) || (col < 0) || (col > columns))  {
			return '\0';
		}

		return board[row][col];
	}

	public boolean canPlay() {
		boolean value = false;

		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				if (board[x][y] == ' ') {
					return true;
				}
			}
		}
		return value;
	}

	public boolean play(int p, int c) {
		boolean value = false;
		if (((p != 1) && (p != 2)) || ((c < 0) || (c >= columns))) {
			return value;
		}

		//places the appropriate token for player p in column c. returns true if successful, false otherwise.
		for (int x = board.length-1; x >= 0; x--) {
			if (board[x][c] != ' ') {
				continue;
			} else {
				if (p == 1) {
					board[x][c] = player1;
				} else {
					board[x][c] = player2;
				}
				return true;
			}
		}
		return value;
	}

	public int isFinished() {
		//returns either the ID of the player who has won (1 or 2) OR 0 if the game has ended in a tie OR -1 if nobody has won yet
		int horizontal = horizontalWin();
		int vertical = verticalWin();
		int diagonal = diagonalWin();
		boolean isDraw = !(canPlay());

		System.out.println(isDraw);

		if (horizontal == 1 || horizontal == 2) {
			return horizontal;
		} else if (vertical == 1 || vertical == 2) {
			return vertical;
		} else if (diagonal == 1 || diagonal == 2) {
			return diagonal;
		} else if (isDraw) {
			return 0;
		} else {
			return -1;
		}
	}

	public int horizontalWin() {
		for (int x = board.length-1; x >= 0; x--) {
			int count = 1;
			for (int y = 1; y < board[x].length; y++) {
				if ((board[x][y] == board[x][y-1]) && (board[x][y] != ' ')) {
					count++;
					if (count == 4) {
						if (board[x][y] == player1) {
							return 1;
						} else {
							return 2;
						}
					}
				} else {
					count = 1;
				}
			}
		}
		return 0;
	}

	public int verticalWin() {
		for (int x = 0; x < board[0].length; x++) {
			int count = 1;
			for (int y = board.length-2; y >= 0; y--) {
				if ((board[y][x] == board[y+1][x]) && (board[y][x] != ' ')) {
					count++;
					if (count == 4) {
						if (board[y][x] == player1) {
							return 1;
						} else {
							return 2;
						}
					}
				} else {
					count = 1;
				}
			}
		}
		return 0;
	}

	public int diagonalWin() {
		//checking diagonal from left to right
		for (int x = board.length-1; x > 2; x--) {
			for (int y = 0; y < board[x].length-3; y++) {
				if ((board[x][y] == board[x-1][y+1]) && (board[x-1][y+1] == board[x-2][y+2]) && (board[x-2][y+2] == board[x-3][y+3]) && (board[x][y] != ' ')) {
					if (board[x][y] == player1) {
						return 1;
					} else {
						return 2;
					}
				}
			}
		}
		//check diagnonal
		for (int x = board.length-1; x > 2; x--) {
			for (int y = board[x].length-1; y > 2; y--) {
				if ((board[x][y] == board[x-1][y-1]) && (board[x-1][y-1] == board[x-2][y-2]) && (board[x-2][y-2] == board[x-3][y-3]) && (board[x][y] != ' ')) {
					if (board[x][y] == player1) {
						return 1;
					} else {
						return 2;
					}
				}
			}
		}
		return 0;
	}
}