public class Bot {
	public final static int LEFT = 2;
	public final static int RIGHT = 6;
	public final static int DOWN = 14;
	public final static int UP = 16;
	private int iLastMove;
	private int iErrorCol;
	private boolean bHasChanged;
	private boolean bLeftRightShift;
	private int[][] oldGrid;

	public boolean getHasChanged() {
		return bHasChanged;
	}

	public Bot() {
		this.oldGrid = new int[4][4];
		this.bHasChanged = false;
		this.iErrorCol = -2;
	}

	private boolean hasChanged(int[][] gameGrid) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameGrid[i][j] != oldGrid[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	private void copyArray(int[][] gameGrid) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				oldGrid[i][j] = gameGrid[i][j];
	}

	/*
	 * charcode for: -> Left = 2 -> Right = 6 -> Up = 16 -> Down = 14
	 */
	private void printStatus() {
		System.out.println("Has Changed: " + bHasChanged);
		System.out.println("Index of Problem Element in the bottom Row "
				+ iErrorCol);
	}

	public int nextStep(int[][] gameGrid) {
		// check if anything has changed
		bHasChanged = hasChanged(gameGrid);

		// store the last grid
		copyArray(gameGrid);

		printStatus();
		// fill the bottom line
		for (int i = 0; i < 4; i++) {
			if (gameGrid[3][i] == 0) {
				if (bHasChanged) {
					iLastMove = RIGHT;
					return RIGHT;
				} else {
					if (iLastMove == DOWN) {
						// Didn't change with DOWN AND RIGHT. Go Left
						iLastMove = LEFT;
						return LEFT;
					} else {
						iLastMove = DOWN;
						return DOWN;
					}
				}

			}
		}
		// try to merge in the bottom line
		for (int i = 0; i < 3; i++) {
			if (gameGrid[3][i] == gameGrid[3][i + 1]) {
				iLastMove = RIGHT;
				return RIGHT;
			}

		}

		// check if the bottom line is in right order
		iErrorCol = -1;
		for (int i = 0; i < 3; i++) {
			if (gameGrid[3][2 - i] > gameGrid[3][3 - i]) {
				iErrorCol = 3 - i;
				break;
			}
		}
		// bottom line is in right order
		if (iErrorCol == -1) {
			if (bHasChanged) {
				iLastMove = LEFT;
				return LEFT;
			} else {
				if (iLastMove == DOWN) {
					// Didn't change with DOWN AND RIGHT. Go Left
					iLastMove = RIGHT;
					return RIGHT;
				} else {
					iLastMove = DOWN;
					return DOWN;
				}
			}
		} else {
			// directly above
			for (int i = 2; i >= 0; i--) {
				if (gameGrid[i][iErrorCol] == gameGrid[3][iErrorCol]) {
					iLastMove = DOWN;
					return DOWN;
				} else if (gameGrid[i][iErrorCol] != 0) {
					break;
				}
			}
			
			if (iErrorCol == 0) {
				if (bHasChanged) {
					iLastMove = RIGHT;
					return RIGHT;
				} else {
					iLastMove = DOWN;
					return DOWN;
				}
			} else if (iErrorCol == 3) {
				if (bHasChanged) {
					iLastMove = LEFT;
					return LEFT;
				} else {
					iLastMove = DOWN;
					return DOWN;
				}
			}
		}

		return 0;
	}

}
