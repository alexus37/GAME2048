import java.util.Random;

public class Grid {
	private int[][] gameGrid;
	private int iRound;
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public Grid(){
		Random rn = new Random();  
		this.gameGrid = new int[4][4];
		int indexX = rn.nextInt() % 4;
		int indexY = rn.nextInt() % 4;
		int value = 2 + (2 * (Math.abs(rn.nextInt() % 2)));
		gameGrid[Math.abs(indexY)][Math.abs(indexX)]  = value;
		indexX = rn.nextInt() % 4;
		indexY = rn.nextInt() % 4;
		if(value == 4) {
			value = 2;
		} else {
			value = 2 + (2 * Math.abs((rn.nextInt() % 2)));
		}
		gameGrid[Math.abs(indexY)][Math.abs(indexX)]  = value;
		iRound = 0;
		
	}
	
	private void insertRandom() {
		if (numFreeFields() > 0) {
			// Okay just print a random number
			Random rn = new Random();
			int index = rn.nextInt(numFreeFields());

			// Just add twos for the beginning..change that so that 4's are
			// generated too
			for (int y = 0; y < 4; ++y) {
				for (int x = 0; x < 4; ++x) { 
					if (gameGrid[y][x] == 0) {
						if (index == 0) {
							gameGrid[y][x] = 2+ (2 * Math.abs((rn.nextInt() % 2)));
						}
						--index;
					}
				}
			}
		}
	}
	
	public void moveLeft() {
		boolean hasChanged = false;
		boolean anythingMoved = false;
		boolean[][] hasPaired = new boolean[4][4];
		
		// Init the pairing array to false
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				hasPaired[y][x] = false;
			}
		}
		
		do {
			hasChanged = false;
			for(int y = 0; y < 4; ++y) {
				for(int x = 1; x < 4; ++x) {
					if(gameGrid[y][x] != 0 && gameGrid[y][x-1] != 0) {
						// Both are not 0
						
						// Check if both have the same number and have not paired yet
						if(gameGrid[y][x] == gameGrid[y][x-1] &&
							!hasPaired[y][x] && !hasPaired[y][x-1]) {
							
							gameGrid[y][x] = 0;
							gameGrid[y][x-1] *= 2;
						
							hasPaired[y][x-1] = true;
							hasChanged = true;
							anythingMoved = true;
						}
					} else if(gameGrid[y][x] != 0 && gameGrid[y][x-1] == 0) {
						gameGrid[y][x-1] = gameGrid[y][x];
						gameGrid[y][x] = 0;
						
						hasPaired[y][x-1] = hasPaired[y][x];
						hasPaired[y][x] = false;
						
						hasChanged = true;
						anythingMoved = true;
					}
				}
			}
			
		} while(hasChanged);
		
		if(anythingMoved) {
			insertRandom();
			iRound++;
		}
		
		
	}
	public void moveRight() {
		boolean hasChanged = false;
		boolean anythingMoved = false;
		boolean[][] hasPaired = new boolean[4][4];
		
		// Init the pairing array to false
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				hasPaired[y][x] = false;
			}
		}
		
		do {
			hasChanged = false;
			for(int y = 0; y < 4; ++y) {
				for(int x = 0; x < 3; ++x) {
					if(gameGrid[y][x] != 0 && gameGrid[y][x+1] != 0) {
						// Both are not 0
						
						// Check if both have the same number and have not paired yet
						if(gameGrid[y][x] == gameGrid[y][x+1] &&
							!hasPaired[y][x] && !hasPaired[y][x+1]) {
							
							gameGrid[y][x] = 0;
							gameGrid[y][x+1] *= 2;
						
							hasPaired[y][x+1] = true;
							hasChanged = true;
							anythingMoved = true;
						}
					} else if(gameGrid[y][x] != 0 && gameGrid[y][x+1] == 0) {
						gameGrid[y][x+1] = gameGrid[y][x];
						gameGrid[y][x] = 0;
						
						hasPaired[y][x+1] = hasPaired[y][x];
						hasPaired[y][x] = false;
						
						hasChanged = true;
						anythingMoved = true;
					}
				}
			}
			
		} while(hasChanged);
		
		if(anythingMoved) {
			insertRandom();
			iRound++;
		}
		
		
	}
	public void moveUp() {
		boolean hasChanged = false;
		boolean anythingMoved = false;
		boolean[][] hasPaired = new boolean[4][4];
		
		// Init the pairing array to false
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				hasPaired[y][x] = false;
			}
		}
		
		do {
			hasChanged = false;
			for(int y = 1; y < 4; ++y) {
				for(int x = 0; x < 4; ++x) {
					if(gameGrid[y][x] != 0 && gameGrid[y-1][x] != 0) {
						// Both are not 0
						
						// Check if both have the same number and have not paired yet
						if(gameGrid[y][x] == gameGrid[y-1][x] &&
							!hasPaired[y][x] && !hasPaired[y-1][x]) {
							
							gameGrid[y][x] = 0;
							gameGrid[y-1][x] *= 2;
						
							hasPaired[y-1][x] = true;
							hasChanged = true;
							anythingMoved = true;
						}
					} else if(gameGrid[y][x] != 0 && gameGrid[y-1][x] == 0) {
						gameGrid[y-1][x] = gameGrid[y][x];
						gameGrid[y][x] = 0;
						
						hasPaired[y-1][x] = hasPaired[y][x];
						hasPaired[y][x] = false;
						
						hasChanged = true;
						anythingMoved = true;
					}
				}
			}
			
		} while(hasChanged);
		
		if(anythingMoved) {
			insertRandom();
			iRound++;
		}
		
		
	}
	public void moveDown() {
		boolean hasChanged = false;
		boolean anythingMoved = false;
		boolean[][] hasPaired = new boolean[4][4];
		
		// Init the pairing array to false
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				hasPaired[y][x] = false;
			}
		}
		
		do {
			hasChanged = false;
			for(int y = 0; y < 3; ++y) {
				for(int x = 0; x < 4; ++x) {
					if(gameGrid[y][x] != 0 && gameGrid[y+1][x] != 0) {
						// Both are not 0
						
						// Check if both have the same number and have not paired yet
						if(gameGrid[y][x] == gameGrid[y+1][x] &&
							!hasPaired[y][x] && !hasPaired[y+1][x]) {
							
							gameGrid[y][x] = 0;
							gameGrid[y+1][x] *= 2;
						
							hasPaired[y+1][x] = true;
							hasChanged = true;
							anythingMoved = true;
						}
					} else if(gameGrid[y][x] != 0 && gameGrid[y+1][x] == 0) {
						gameGrid[y+1][x] = gameGrid[y][x];
						gameGrid[y][x] = 0;
						
						hasPaired[y+1][x] = hasPaired[y][x];
						hasPaired[y][x] = false;
						
						hasChanged = true;
						anythingMoved = true;
					}
				}
			}
			
		} while(hasChanged);
		
		if(anythingMoved) {
			insertRandom();
			iRound++;
		}
		
		
	}
	
	private boolean containsHighest() {
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				if(gameGrid[y][x] == 2048) {
						return true;
				}
			}
		}
		
		return false;
	}
	
	private int numFreeFields() {
		int numFreeFields = 0;
		
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				if(gameGrid[y][x] == 0) {
					++numFreeFields;
				}
			}
		}
		
		return numFreeFields;
	}
	
	private boolean anyMovePossible() {
		for(int y = 0; y < 4; ++y) {
			for(int x = 0; x < 4; ++x) {
				// Skip if the field is zero
				if(gameGrid[y][x] == 0) {
					continue;
				}
				
				// Check if we can move this field up and merge it with any other field
				for(int i = y - 1; i >= 0; i--) {
					if(gameGrid[y][x] == gameGrid[i][x]) {
						return true;
					} else if((gameGrid[y][x] != gameGrid[i][x]) && gameGrid[i][x] != 0) {
						// There is a block in our way.. no chance here
						break;
					}
				}
				
				// Check if we can move this field down and merge it with any other field
				for(int i = y + 1; i < 4; i++) {
					if(gameGrid[y][x] == gameGrid[i][x]) {
						return true;
					} else if((gameGrid[y][x] != gameGrid[i][x]) && gameGrid[i][x] != 0) {
						// There is a block in our way.. no chance here
						break;
					}
				}
				
				// Check if we can move this field right and merge it with any other field
				for(int i = x + 1; i < 4; i++) {
					if(gameGrid[y][x] == gameGrid[y][i]) {
						return true;
					} else if((gameGrid[y][x] != gameGrid[y][i]) && gameGrid[y][i] != 0) {
						// There is a block in our way.. no chance here
						break;
					}
				}
				// Check if we can move this field left and merge it with any other field
				for(int i = x - 1; i >= 0; i--) {
					if(gameGrid[y][x] == gameGrid[y][i]) {
						return true;
					} else if((gameGrid[y][x] != gameGrid[y][i]) && gameGrid[y][i] != 0) {
						// There is a block in our way.. no chance here
						break;
					}
				}
			}
		}
		
		return false;
	}
	
	public int getFinishedCode() {
		if(numFreeFields() == 0 && !anyMovePossible()) {
			return 3;
		} 
		
		if(containsHighest()) {
				return 2;
		}
		
		return 0;
	}
	
	public void ToString(){
		String ScurLine = "";
		String ScurNumber = "";
		System.out.println("------------------------------------------------------");
		System.out.println("Runde: " + iRound);
		System.out.println();
		System.out.println(" ---- ---- ---- ---- ");

		

		System.out.println( "|"+getNrRep(gameGrid[0][0])+"|"
							   +getNrRep(gameGrid[0][1])+"|"
							   +getNrRep(gameGrid[0][2])+"|"
							   +getNrRep(gameGrid[0][3])+"|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println( "|"+getNrRep(gameGrid[1][0])+"|"
							   +getNrRep(gameGrid[1][1])+"|"
							   +getNrRep(gameGrid[1][2])+"|"
							   +getNrRep(gameGrid[1][3])+"|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println( "|"+getNrRep(gameGrid[2][0])+"|"
							   +getNrRep(gameGrid[2][1])+"|"
							   +getNrRep(gameGrid[2][2])+"|"
							   +getNrRep(gameGrid[2][3])+"|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println( "|"+getNrRep(gameGrid[3][0])+"|"
							   +getNrRep(gameGrid[3][1])+"|"
							   +getNrRep(gameGrid[3][2])+"|"
							   +getNrRep(gameGrid[3][3])+"|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println();
		System.out.println("------------------------------------------------------");
	}
	
	private String getNrRep(int x){
		String ScurNumber = String.valueOf(x);
		String result = "";
		switch (ScurNumber.length()) {
		case 1:
			if(x == 0) {
				result = "    ";
			} else {
				result = "   " +ScurNumber;
			}
			break;
		case 2:
			result = "  " +ScurNumber;
			break;
		case 3:
			result = " " +ScurNumber;
			break;
		case 4:
			result = ScurNumber;
			break;
		default:
			break;
		}
		switch (x) {
		case 0:
			break;
		case 2:
			result = ANSI_BLUE+result+ANSI_BLUE+ANSI_RESET;
			break;
		case 4:
			result = ANSI_RED+result+ANSI_RED+ANSI_RESET;
			break;
		case 8:
			result = ANSI_CYAN+result+ANSI_CYAN+ANSI_RESET;
			break;
		case 16:
			result = ANSI_GREEN+result+ANSI_GREEN+ANSI_RESET;
			break;
		case 32:
			result = ANSI_PURPLE+result+ANSI_PURPLE+ANSI_RESET;
			break;
		case 64:
			result = ANSI_WHITE+result+ANSI_WHITE+ANSI_RESET;
			break;
		case 128:
			result = ANSI_YELLOW+result+ANSI_YELLOW+ANSI_RESET;
			break;
		case 256:
			result = ANSI_BLUE+result+ANSI_BLUE+ANSI_RESET;
			break;
		case 512:
			result = ANSI_RED+result+ANSI_RED+ANSI_RESET;
			break;
		case 1024:
			result = ANSI_CYAN+result+ANSI_CYAN+ANSI_RESET;
			break;
		case 2048:
			result = ANSI_GREEN+result+ANSI_GREEN+ANSI_RESET;
			break;
		default:
			break;
		}
		return result;
	}
	public void update(int iChar) {
		switch (iChar) {
		//move left
		case 2:
			moveLeft();
			break;
		//move right
		case 6:
			moveRight();
		break;
		//move up
		case 16:
			moveUp();
		break;
		//move down
		case 14:
			moveDown();
		break;
		default:
			break;
		}
	}

}
