import java.time.LocalTime;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {
	private int[] matches;

	KMP(String pattern, String text) {
		int pos = 2;
		int count = 0;
		matches = new int[pattern.length()];
		matches[0] = -1;
		matches[1] = 0;

		if (pos < pattern.length()) {
			do {
				if (text.charAt(pos - 1) != text.charAt(count)) {
					if (count > 0) {
						count = matches[count];
					} else {
						matches[pos] = 0;
						pos++;
					}
				} else {
					matches[pos] = count + 1;
					pos++;
					count++;
				}
			} while (pos < pattern.length());
		}
	}

	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * 
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	int search(String pattern, String text) {
		//LocalTime start = LocalTime.now();
		//System.out.println(start);
		int p = 0;
		int t = 0;
		if (t + p < text.length()) {
			do {
				if (pattern.charAt(p) != text.charAt(t + p)) {
					if (matches[p] == -1) {
						t = t + p + 1;
						p = 0;
					} else {
						t = t + p - matches[p];
						p = matches[p];
					}
				} else {
					p++;
					if (p == matches.length) {
						//LocalTime end = LocalTime.now();
						//System.out.println(end);
						return t;
					}
				}
			} while (t + p < text.length());
		}
		//LocalTime end = LocalTime.now();
		//System.out.println(end);
		return -1;
	}

	int Bsearch(String pattern, String text){
		//LocalTime start = LocalTime.now();
		//System.out.println(start);
		char[] pChars = pattern.toCharArray();
		char[] tChars = text.toCharArray();

		for (int i = 0; i <= tChars.length - pChars.length; i++) {
			int j;
			for (j = 0; pChars.length > j; j++) {
				if(!(pChars[j] == tChars[i + j])){
					break;
				}
			}
			if (pChars.length == j) {
				//LocalTime end = LocalTime.now();
				//System.out.println(end);
				return i;
			}
		}
		//LocalTime end = LocalTime.now();
		//System.out.println(end);
		return -1;
	}
}
