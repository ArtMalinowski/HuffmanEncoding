public class MyHuffmanNode {

		public char character;
		public int quantity;
		public MyHuffmanNode left;
		public MyHuffmanNode right;

		/**
		 * Constructor to my Huffman Code which store character quantity ,node child left and node child right
		 * @param c
		 * @param f
		 * @param left_child_node
		 * @param right_child_node
		 */
		MyHuffmanNode(char c, int f, MyHuffmanNode left_child_node, MyHuffmanNode right_child_node) {

			character = c;
			quantity = f;
			left = left_child_node;
			right = right_child_node;
		}

	}