import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.PriorityQueue;
import java.util.Scanner;

public class buildHuffmantree {
	static Scanner readText = new Scanner(System.in);





	/**
	 * creating priorityQueue with my all nodes used HashMap with all characters and their quantity 
	 * @param sorted_list
	 * @return
	 */
	public static PriorityQueue<MyHuffmanNode> creteQueue(HashMap<Character, Integer> Characters) {
		// creating local comperator to compare all nodes in queue

		PriorityQueue<MyHuffmanNode> priority_Queue = new PriorityQueue<MyHuffmanNode>(Characters.size(),
				new Comparator<MyHuffmanNode>() {
			@Override
			public int compare(MyHuffmanNode n1, MyHuffmanNode n2) {
				return n1.quantity - n2.quantity;
			}
		});
		// add to my priority_Queue values by for each loop 
		for (Entry<Character, Integer> entry : Characters.entrySet()) {
			// nodes children value are equal to null
			MyHuffmanNode temp = new MyHuffmanNode((char) entry.getKey(), (int) entry.getValue(), null, null);
			//adding to my priority_Queue
			priority_Queue.add(temp);

		}
		// retrning priority_Queue with nodes 
		return priority_Queue;

	}

	/**
	 * create Hiffman tree whith all conection between parents and childrens 
	 * @param priority_Queue
	 * @return
	 */
	public static MyHuffmanNode createHuffmanTree(PriorityQueue<MyHuffmanNode> priority_Queue) {
		// check if priority_Queue is bigger than 1 because the last one we need to return as our root 
		while (priority_Queue.size() > 1) {
			// taking from queue the smallest characters by poll from queue
			MyHuffmanNode left = priority_Queue.poll();
			MyHuffmanNode right = priority_Queue.poll();
			
			// '\0' null in ASCII
			// creating the parent node 
			MyHuffmanNode Huff_node = new MyHuffmanNode('\0', left.quantity + right.quantity, left, right);

			priority_Queue.add(Huff_node);
		}
		// return root
		return priority_Queue.poll();
	}

	/**
	 * ceate binnary code for my Huffman Coding and return it as a big Hashmap
	 * @param root
	 * @return
	 */
	private static HashMap<Character, String> madeCode(MyHuffmanNode root) {

		HashMap<Character, String> decodedMessage = new HashMap<Character, String>();
		findChar(root, decodedMessage, "");

		return decodedMessage;

	}

	/**
	 * it find all nodes in my tree and add them 0 or 1 in deppendence of their possiton
	 * @param node
	 * @param decodedMessage
	 * @param huffCode
	 */
	private static void findChar(MyHuffmanNode node, HashMap<Character, String> decodedMessage, String huffCode) {
		// check if it is leaf and if it is add to my hahMap as leaf
		if (node.left == null & node.right == null)
			decodedMessage.put(node.character, huffCode);
		// check if left child node is not null and if he is not he will add 0 to the string of his code a
		// and do again by recursive
		if (node.left != null)
			findChar(node.left, decodedMessage, huffCode + "0");
		//the same as top one but for right chid node
		if (node.right != null)
			findChar(node.right, decodedMessage, huffCode + "1");
		// if it is node parent it add it also to my tree
		if ((node.left != null || node.right != null)) {
			decodedMessage.put(node.character, huffCode);
			// work in the same way as in leaf situatuion
			if (node.left != null)
				findChar(node.left, decodedMessage, huffCode + "0");
			// work in the same way as in leaf situatuion
			if (node.right != null)
				findChar(node.right, decodedMessage, huffCode + "1");
		}
	}

	/**
	 * count all nodes which we have in our tree also be revursive method
	 * @param tree
	 * @return
	 */
	public static int countNodes(MyHuffmanNode tree) {
		int l, r;
		if (tree == null)
			return 0;
		else
			//add left nodes and right to calculate all nodes 
			l = countNodes(tree.left) + 1;
			r = countNodes(tree.right) + 1;
			// we minus 1 to have correct number of nodes
			return (l + r)-1;
	}

	/**
	 * Calculate the height of my Hash Tree
	 * @param decodedMessage
	 * @return
	 */
	public static int heightOfTree(HashMap<Character, String> decodedMessage) {
		int longest = 0;
		for (Entry<Character, String> entryDecoded : decodedMessage.entrySet()) {
			if (longest == 0)
				longest = entryDecoded.getValue().length();
			// check which in which way the tee is the longest 
			else if (entryDecoded.getValue().length() > longest)
				longest = entryDecoded.getValue().length();
		}
		// add one because we need ad our root which is also part of height of tree 
		return longest;
	}

	/**
	 * this is my biggest fucntion which create all results for huffman coding system 
	 * create Huffman code check how much memory we save, how many nodes , what is the height
	 * of tree and save file with result if you want 
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void compress() throws FileNotFoundException, UnsupportedEncodingException {
		//create object which we will use to calucate things for Huffman Code
		callcuation callcForHuff = new callcuation();
		
		// calculate frequency of letter
		HashMap<Character, Integer> char_freq_order = callcForHuff.frequencyOfLetter();
		//create PQ whit our characters and their frequency
		PriorityQueue<MyHuffmanNode> prioQ = creteQueue(char_freq_order);
		// crete our tree 
		MyHuffmanNode root = createHuffmanTree(prioQ);
		// claculate nubmer of characters to check if is the same as nubmer of vlaue of root
		int numberOfCharacters = callcuation.numberOfCharacetrs(char_freq_order);
			// check if is correct if not print error
			if (root.quantity == numberOfCharacters) {
				// Print the value of our root
				System.out.println("the value of root at the end " + root.quantity);
				HashMap<Character, String> decodedMessage = madeCode(root);
				//Printing all results 
				System.out.println("Decoded Message in order by ASCII : ");
				PrintWriter wrtieTextFile = new PrintWriter("decodedMessage", "ASCII");
				int afterCompression = 0;
				// print charcters coded by huffman also calculate size of new file 
				for (Entry<Character, String> entryDecoded : decodedMessage.entrySet()) {
					for (Entry<Character, Integer> entryUncoded : char_freq_order.entrySet()) {
						if (entryDecoded.getKey()==entryUncoded.getKey()){
						afterCompression = afterCompression + (entryUncoded.getValue() * entryDecoded.getValue().length());
						entryUncoded.setValue(0);
						}
					}
					// there can be 2 coded character looking like space but they are new line carriage return
					if (entryDecoded.getKey() != '\0') {
						System.out.print("character = ");
						wrtieTextFile.print("character = ");
						System.out.println(entryDecoded.getKey());
						wrtieTextFile.println(entryDecoded.getKey());
						System.out.print("Coded Character = ");
						wrtieTextFile.print("Coded Character = ");
						System.out.println(entryDecoded.getValue());
						wrtieTextFile.println(entryDecoded.getValue());
					}
	
				}
				// calculate the huffman depth of tree 
					int y = 1;
					for (Entry<Character, String> entryDecoded : decodedMessage.entrySet()) {
						y += entryDecoded.getValue().length();
					}
				// printing more results need for project 
				System.out.println("\n\nBefore compression " + numberOfCharacters * 8);
					
				System.out.println("After compression " + afterCompression);
				// calculate how much we save memmory by using this method 
				
				float compression = ((float) numberOfCharacters * 8)/(float) afterCompression ;
				System.out.println("We saved by compression " + compression);
				System.out.println("The height of tree is " + heightOfTree(decodedMessage));
				
				System.out.println("The nubmer of nodes  " + countNodes(root));
				
				System.out.println("The averge depth of nodes  " + (float) y / countNodes(root));
								
					// fucntion to save results in file 
					System.out.println("If you want to save result in file please enter s if not please enter anything");
					String save = readText.nextLine();
					if (save.toLowerCase().equals("s")) {
						wrtieTextFile.println("The height of tree is " + heightOfTree(decodedMessage));
						wrtieTextFile.println("The nubmer of nodes  " + countNodes(root));
						wrtieTextFile.println("The averge depth of nodes  " + (float) y / countNodes(root));
						wrtieTextFile.println("Before compression " + numberOfCharacters * 8);
						wrtieTextFile.println("After compression " + afterCompression);
						wrtieTextFile.println("We saved by compression " + compression + "%");
						System.out.println("Save Done");
						wrtieTextFile.close();
						
					}
			} else
				System.out.println("Error nubmer Of character is not the same as the value of root");
		}
}
