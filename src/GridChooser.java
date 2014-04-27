import java.io.IOException;
import java.util.Random;

import jline.ConsoleReader;

public class GridChooser {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	private int iIndexX = 0;
	private int iIndexY = 0;
	int iCurVal;

	int iRound = 0;
	int[][] gameGrid = new int[4][4];

	public static void main(String[] args) {
		GridChooser gc = new GridChooser();
		int iChar = 0;
		int fin;
		try {
			ConsoleReader reader = new ConsoleReader();
			do {
				// get Char
				
				fin = gc.update(iChar);
				gc.runTest();
				if (fin == 0) {
					iChar = reader.readVirtualKey();
					reader.clearScreen();
				}
			} while (fin == 0);
			// winning code
			if (fin == 2) {
				System.out.println("You win!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void runTest() {
		System.out
				.println("------------------------------------------------------");
		System.out.println("Runde: " + iRound);
		iRound++;
		System.out.println("Positioniere den Wert: " + ANSI_RED + iCurVal
				+ ANSI_RED + ANSI_RESET + " in ein freies Feld!");
		System.out.println("Momentane Position = (" + iIndexX + ", " + iIndexY
				+ ")");
		System.out.println();
		System.out.println(" ---- ---- ---- ---- ");

		System.out.println("|" + getNrRep(gameGrid[0][0]) + "|"
				+ getNrRep(gameGrid[0][1]) + "|" + getNrRep(gameGrid[0][2])
				+ "|" + getNrRep(gameGrid[0][3]) + "|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println("|" + getNrRep(gameGrid[1][0]) + "|"
				+ getNrRep(gameGrid[1][1]) + "|" + getNrRep(gameGrid[1][2])
				+ "|" + getNrRep(gameGrid[1][3]) + "|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println("|" + getNrRep(gameGrid[2][0]) + "|"
				+ getNrRep(gameGrid[2][1]) + "|" + getNrRep(gameGrid[2][2])
				+ "|" + getNrRep(gameGrid[2][3]) + "|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println("|" + getNrRep(gameGrid[3][0]) + "|"
				+ getNrRep(gameGrid[3][1]) + "|" + getNrRep(gameGrid[3][2])
				+ "|" + getNrRep(gameGrid[3][3]) + "|");
		System.out.println(" ---- ---- ---- ---- ");
		System.out.println();
		System.out
				.println("------------------------------------------------------");

	}

	public int update(int iChar) {
		Random rn = new Random();
		int fin = 0;
		
		switch (iChar) {
		//init
		case 0:
			iCurVal = 2 + (2 * (Math.abs(rn.nextInt() % 2)));
			float fPos = insertRandom(iCurVal);
			iIndexY = (int) fPos;
			iIndexX = (int) ((fPos - (float) iIndexY) * 10);
			if(iIndexY == -1) {
				fin = 2;
				return fin;
			}
		break;
		// move left
		case 2:
			moveLeft();
			break;
		// move right
		case 6:
			moveRight();
			break;
		// move up
		case 16:
			moveUp();
			break;
		// move down
		case 14:
			moveDown();
			break;
		// enter and accept
		case 10:
			iCurVal = 2 + (2 * (Math.abs(rn.nextInt() % 2)));
			fPos = insertRandom(iCurVal);
			iIndexY = (int) fPos;
			iIndexX = (int) ((fPos - (float) iIndexY) * 10);
			if(iIndexY == -1) {
				fin = 2;
				return fin;
			}
			break;	
		default:
			break;
		}
		return fin;
	}

	private void moveDown() {
		int iNewIndX = iIndexX;
		int iNewIndY = iIndexY+1 ;
		if (iNewIndY > 3)
			iNewIndY = 3;
		if (gameGrid[iNewIndY][iNewIndX] == 0) {
			gameGrid[iNewIndY][iNewIndX] = iCurVal;
			gameGrid[iIndexY][iIndexX] = 0;
			iIndexX = iNewIndX;
			iIndexY = iNewIndY;
		}
	}

	private void moveUp() {
		int iNewIndX = iIndexX;
		int iNewIndY = iIndexY - 1;
		if (iNewIndY < 0)
			iNewIndY = 0;
		if (gameGrid[iNewIndY][iNewIndX] == 0) {
			gameGrid[iNewIndY][iNewIndX] = iCurVal;
			gameGrid[iIndexY][iIndexX] = 0;
			iIndexX = iNewIndX;
			iIndexY = iNewIndY;
		}

	}

	private void moveRight() {
		int iNewIndX = iIndexX + 1;
		int iNewIndY = iIndexY;
		if (iNewIndX > 3)
			iNewIndX = 3;
		if (gameGrid[iNewIndY][iNewIndX] == 0) {
			gameGrid[iNewIndY][iNewIndX] = iCurVal;
			gameGrid[iIndexY][iIndexX] = 0;
			iIndexX = iNewIndX;
			iIndexY = iNewIndY;

		}

	}

	private void moveLeft() {
		int iNewIndX = iIndexX - 1;
		int iNewIndY = iIndexY;
		if (iNewIndX < 0)
			iNewIndX = 0;
		if (gameGrid[iNewIndY][iNewIndX] == 0) {
			gameGrid[iNewIndY][iNewIndX] = iCurVal;
			gameGrid[iIndexY][iIndexX] = 0;
			iIndexX = iNewIndX;
			iIndexY = iNewIndY;

		}

	}

	private int numFreeFields() {
		int numFreeFields = 0;

		for (int y = 0; y < 4; ++y) {
			for (int x = 0; x < 4; ++x) {
				if (gameGrid[y][x] == 0) {
					++numFreeFields;
				}
			}
		}

		return numFreeFields;
	}

	private float insertRandom(int value) {
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
							gameGrid[y][x] = value;
							return (float) y + 0.1f * (float) x;
						}
						--index;
					}
				}
			}
		}
		return -1f;
	}

	private String getNrRep(int x) {
		String ScurNumber = String.valueOf(x);
		String result = "";
		switch (ScurNumber.length()) {
		case 1:
			if (x == 0) {
				result = "    ";
			} else {
				result = "   " + ScurNumber;
			}
			break;
		case 2:
			result = "  " + ScurNumber;
			break;
		case 3:
			result = " " + ScurNumber;
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
			result = ANSI_BLUE + result + ANSI_BLUE + ANSI_RESET;
			break;
		case 4:
			result = ANSI_WHITE + result + ANSI_WHITE + ANSI_RESET;
			break;
		case 8:
			result = ANSI_CYAN + result + ANSI_CYAN + ANSI_RESET;
			break;
		case 16:
			result = ANSI_GREEN + result + ANSI_GREEN + ANSI_RESET;
			break;
		case 32:
			result = ANSI_PURPLE + result + ANSI_PURPLE + ANSI_RESET;
			break;
		case 64:
			result = ANSI_WHITE + result + ANSI_WHITE + ANSI_RESET;
			break;
		case 128:
			result = ANSI_YELLOW + result + ANSI_YELLOW + ANSI_RESET;
			break;
		case 256:
			result = ANSI_BLUE + result + ANSI_BLUE + ANSI_RESET;
			break;
		case 512:
			result = ANSI_PURPLE + result + ANSI_PURPLE + ANSI_RESET;
			break;
		case 1024:
			result = ANSI_CYAN + result + ANSI_CYAN + ANSI_RESET;
			break;
		case 2048:
			result = ANSI_GREEN + result + ANSI_GREEN + ANSI_RESET;
			break;
		default:
			break;
		}
		return result;
	}

}
