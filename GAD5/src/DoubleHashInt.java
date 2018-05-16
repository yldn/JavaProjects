import java.util.Random;

/**
 * Die Klasse {@link DoubleHashInt} kann dazu verwendet werden, Integer zu
 * hashen.
 */
public class DoubleHashInt implements DoubleHashable<Integer> {
	private Random rand;
	private int size;
	private int x, y;

	/**
	 * Dieser Konstruktor initialisiert ein {@link DoubleHashInt} Objekt für einen
	 * gegebenen Maximalwert (size - 1) der gehashten Werte.
	 * 
	 * @param size
	 *            die Größe der Hashtabelle
	 */
	public DoubleHashInt(int size) {
		rand = new Random();
		this.size = size;
		x = generateRandom(x, size);
		y = generateRandom(y, size);
	}

	private int generateRandom(int x, int size) {
		rand = new Random();
		x = rand.nextInt(size - 1);
		while (x == 0) {
			x = rand.nextInt(size - 1);
		}
		return x;
	}

	/**
	 * Diese Methode berechnet h(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hash(Integer key) {
		if (key < 0) {
			key = -key;
		}
		long result = (x * (long) key) % size;
		return result;
	}

	/**
	 * Diese Methode berechnet h'(key) für einen Integer.
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @return der Hashwert des Schlüssels
	 */
	@Override
	public long hashTick(Integer key) {
		if (key < 0) {
			key = -key;
		}
		long result = (y * (long) key) % (size - 1) + 1;
		return result;
	}

}
