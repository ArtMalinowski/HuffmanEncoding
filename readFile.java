import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readFile {
	static Scanner readText = new Scanner(System.in);
	private static Scanner in;


	/**
	 * read which is used to read from ile and add to character array all characters
	 * @return
	 */
	public static char[] read() {
		String adressOfFile;
		String text = "";
		char[] text_array;
		do {
			// find if the path is not wrong 
			try {
				System.out.println("please enter which file would you like to read");
				adressOfFile = readText.nextLine();

				System.out.print(adressOfFile);

				File file = new File(adressOfFile);

				in = new Scanner(file);
				text = in.useDelimiter("//z").next();

				text_array = text.toCharArray();

				return text_array;
				// what happend if the path is worng 
			} catch (FileNotFoundException e) {
				System.err.println("Error wrong Path ");
				text_array = null;

			}

		} while (text == "");
		
		
		// return character array 
		return text_array;
	}
}
