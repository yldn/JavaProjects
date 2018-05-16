import java.util.ArrayList;
import java.util.Random;
//import java.util.stream.LongStream;

/**
 * Die Klasse {@link DoubleHashString} kann dazu verwendet werden, Strings zu
 * hashen.
 */
public class DoubleHashString implements DoubleHashable<String> {
	int size;
	Random x;

	ArrayList<Long> a;

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashString} Objekt für
	 * einen gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashString(int size) {
		if (size >= Math.pow(2, 8)) {
			throw new RuntimeException();
		}
		this.size = size;
		x = new Random();
		a = new ArrayList<>();
	}

	/**
	 * Diese Methode berechnet h(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hash(String key) {
		a = new ArrayList<>();
		while (a.size() < key.length()) {
			a.add((long) x.nextInt((int) Math.pow(2, 8) - 1));
		}
		long hashwert = hashhelp(key, a) % size;
		return hashwert;
	}

	private long hashhelp(String key, ArrayList<Long> a) {
		long wert;
		long sum = 0;
		for (int i = 0; i < key.length(); i++) {
			wert = a.get(i) * key.charAt(i);
			sum += wert;
		}
		return sum;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen String.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	public long hashTick(String key) {
		a = new ArrayList<>();
		while (a.size() < key.length()) {
			a.add((long) x.nextInt(65536));
		}
		long hashwert = hashhelp(key, a) % (size - 1) + 1;
		return hashwert;
	}
}
