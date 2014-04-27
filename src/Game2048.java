import java.io.IOException;
import java.util.Random;

import jline.ConsoleReader;

public class Game2048 {

	public static void main(String[] args) {
		ConsoleReader reader;
		Grid gameGrid = new Grid();
		int iChar = 0;
		int fin = 0;
		if (args.length > 0) {
			if (args[0].equals("Random")) {
				Random rn = new Random();
				
					do {
						fin = gameGrid.getFinishedCode();
						gameGrid.update(iChar);
						// draw it
						gameGrid.ToString();
						// get Char
						iChar = rn.nextInt(4);
						switch (iChar) {
						case 0:
							iChar = 2;
							break;
						case 1:
							iChar = 6;
							break;
						case 2:
							iChar = 16;
							break;
						case 3:
							iChar = 14;
							break;

						default:
							break;
						}
					} while (fin == 0);
					// winning code
					if (fin == 2) {
						System.out.println("You win!");
					}
					// loosing code
					else if (fin == 3) {
						System.out.println("You lose!");
					}
				} else if (args[0].equals("Bot")) {
					Bot bot = new Bot();
					do {

						fin = gameGrid.getFinishedCode();
						gameGrid.update(iChar);
						// draw it
						gameGrid.ToString();
						// get Char
						iChar = bot.nextStep(gameGrid.gameGrid);
						try {
							Thread.sleep(300);
						} catch (Exception e) {
							// TODO: handle exception
						}
					} while (fin == 0);
					// winning code
					if (fin == 2) {
						System.out.println("You win!");
					}
					// loosing code
					else if (fin == 3) {
						System.out.println("You lose!");
					}
				}
				
		}

		try {
			reader = new ConsoleReader();
			do {

				fin = gameGrid.getFinishedCode();
				gameGrid.update(iChar);
				// draw it
				gameGrid.ToString();
				// get Char
				iChar = reader.readVirtualKey();
			} while (fin == 0);
			// winning code
			if (fin == 2) {
				System.out.println("You win!");
			}
			// loosing code
			else if (fin == 3) {
				System.out.println("You lose!");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
