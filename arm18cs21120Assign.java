import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class arm18cs21120Assign {

	private Scanner in;

	/**
	 * Print menu and using specified option which are choose by users 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public void menu() throws FileNotFoundException, UnsupportedEncodingException {
		boolean exit = true;
		int Choosen = 0;
		callcuation placki = new callcuation();
		in = new Scanner(System.in);
		System.out.print("****Welcome in my program about Huffman encoidng****");
		// creating menu
		do {
			System.out.println(
					"\n 1 - Print text from file \n 2 - Print usnorted frequnct of ASCII \n 3 - Print sorted Frequency of ASCII "
							+ "\n 4 = to create Huffman Tree");
			System.out.print("\n" + "*** If you want exit program please enter 5***");
			try{
			Choosen = in.nextInt();
			}catch(InputMismatchException exception)
			{
				menu();
			}
			switch (Choosen) {
			case 1:
				System.out.println(readFile.read());
				break;
			case 2:
				System.out.print(placki.frequencyOfLetter());
				break;
			case 3:
				System.out.print(placki.sortedFrequcnyByValue());
				break;
			case 4:
				buildHuffmantree.compress();
				break;
			case 5:
				exit = false;
				break;
			default:
				System.err.print("wrong number please choose again");
			}

		} while (exit);
	}
	
	/**
	 * main function which start whole program 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException  {
		arm18cs21120Assign app = new arm18cs21120Assign();
		app.menu();

	}
	
	}

