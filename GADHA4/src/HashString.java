import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Die Klasse {@link HashString} kann dazu verwendet werden, Strings zu hashen.
 */
public class HashString {
	// definition der Folie S.199
	private ArrayList<Integer> a;
	private int w;
	private Random rand;
	private int size;

	/**
	 * Dieser Konstruktor initialisiert ein {@link HashString} Objekt für einen
	 * gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public HashString(int size) {
		this.size = size;
		this.a = new ArrayList<Integer>();
		this.rand = new Random();
		this.w = logfloor(2, size);
	}

	// log_base(x)
	private int logfloor(int base, int x) {
		return (int) (Math.log(x) / Math.log(base));
	}

//	public static void main(String[] args) {
//		System.out.println();
//	}

	/**
	 * Diese Methode berechnet den Hashwert für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden sollen
	 * @return der Hashwert des Schlüssels
	 */
	public int hash(String key) {
		char[] keyIn = key.toCharArray();
		int[] tmp = new int[keyIn.length];
		int out = 0;
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = Character.getNumericValue(keyIn[i]);
			if (tmp[i] > (int) (Math.pow(2, w) - 1) || tmp[i] < 0) {
				System.out.println("pls give a valid key");
				return 0;
			}
		}
		// fill the array a with random in [0,size -1]
		for (int i = 0; i < keyIn.length; i++) {
			a.add(i, rand.nextInt(size - 1));
			;
		}

		for (int i = 0; i < tmp.length; i++) {
			out += tmp[i] * a.get(i);
		}
		out = out % size;
		return out;
	}
}
