
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;


public class callcuation {

	/**
	 * This function calculate number of characters and add them to Hash Map which have 
	 * key(character) and value (int)
	 * @return
	 */
	public HashMap<Character, Integer> frequencyOfLetter() {
		// use static function from class readFile to add characters to char array
		// there can be 2 coded character looking like space but they are new line carriage return
		char[] charsInText = readFile.read();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i < charsInText.length; i++) {
			if (map.get(charsInText[i]) == null) {
				map.put(charsInText[i], 1);
			} else {
				map.put(charsInText[i], (map.get(charsInText[i]) + 1));
			}
		}
		return map;
	}
	
	/**
	 * this function sorted all my characters by number of letters 
	 * from the biggest to the smallest
	 * @return
	 */
	public LinkedHashMap<Character, Integer> sortedFrequcnyByValue() {

		HashMap<Character, Integer> Hashmapa = frequencyOfLetter();

		PriorityQueue<Map.Entry<Character, Integer>> compareHashMap = new PriorityQueue<Map.Entry<Character, Integer>>(
				// comperator whcih is used in my priority queue to determinate order
				new Comparator<Map.Entry<Character, Integer>>() {
					@Override
					public int compare(Map.Entry<Character, Integer> mostCommonLetter1,
							Map.Entry<Character, Integer> mostCommonLetter2) {
						return mostCommonLetter2.getValue() - mostCommonLetter1.getValue();
					}
				});
		// function which add all characters to Hahmapy by loop
		for (Map.Entry<Character, Integer> entry : Hashmapa.entrySet()) {
			compareHashMap.offer(entry);

		}
		// create linkedhashmap to store all characters in correct order 
		LinkedHashMap<Character, Integer> sorted_list = new LinkedHashMap<Character, Integer>();
		// loop in  which poll from queue and add the value from queue to LinkedHashMap  
		for (int i = 0; i < Hashmapa.size(); i++) {
			Map.Entry<Character, Integer> entry = compareHashMap.poll();
			sorted_list.put(entry.getKey(), entry.getValue());
		}
		// return linkedhashmap in correct order
		return sorted_list;
	}
	public static int numberOfCharacetrs(HashMap<Character, Integer> characterFrequency){
		int characters = 0 ;
		for (Entry<Character, Integer> entryDecoded : characterFrequency.entrySet()){
			characters += (entryDecoded.getValue());
		}
		return characters;
	}

}
