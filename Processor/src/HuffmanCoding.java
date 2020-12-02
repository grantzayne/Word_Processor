import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.time.LocalTime;

/**
 * A new instance of HuffmanCoding is created for every run. The constructor is
 * passed the full text to be encoded or decoded, so this is a good place to
 * construct the tree. You should store this tree in a field and then use it in
 * the encode and decode methods.
 */
public class HuffmanCoding {
    private HuffmanTree tree;
	/**
	 *  Compute and store the tree.
	 */
	public HuffmanCoding(String text) {
        //LocalTime start = LocalTime.now();
        //System.out.println(start);
        Map<Character,Integer> ftable = new HashMap<>();
	    char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!ftable.containsKey(c)) {
                ftable.put(c, 1);
            } else {
                ftable.replace(c, ftable.get(c) + 1);
            }
        }

        PriorityQueue<HuffmanTree> q = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : ftable.entrySet()) {
            if (entry.getValue() > 0) {
                q.add(new HuffmanTree(entry.getKey(), entry.getValue(), null, null));
            }
        }

        while (q.size() > 1) {
            HuffmanTree x = q.poll();
            HuffmanTree y = q.poll();

            HuffmanTree z = null;
            if (y != null) {
                z = new HuffmanTree('\0', x.f + y.f, x, y);
            }
            q.add(z);
        }
        tree = q.poll();
	}

    private static Map<Character, String> buildTree(HuffmanTree root) {
        Map<Character, String> bcodes = new HashMap<>();
        buildTree(bcodes, root, "");
        return bcodes;
    }

    private static void buildTree(Map<Character, String> tree, HuffmanTree n, String s) {
        if (!n.isLeafNode()) {
            buildTree(tree, n.left, s + '0');
            buildTree(tree, n.right, s + '1');
        } else {
            tree.put(n.c, s);
        }
    }

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 */
	public String encode(String text) {
        char[] chars = text.toCharArray();
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            encoded.append(buildTree(tree).get(c));
        }
        return encoded.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        char[] chars = encoded.toCharArray();

        int index = 0;
        HuffmanTree root = tree;
        HuffmanTree node = root;
        while (index < chars.length) {
            char binary = chars[index];
            if (binary != '0') {
                if (binary == '1') {
                    node = node.right;
                    if (node.left == null) {
                        decoded.append(node.getChar());
                        node = root;
                    }
                }
            } else {
                node = node.left;
                if (node.left == null) {
                    decoded.append(node.getChar());
                    node = root;
                }
            }
            index++;
        }
        //LocalTime end = LocalTime.now();
        //System.out.println(end);
        return decoded.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't want to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		return "";
	}
}
