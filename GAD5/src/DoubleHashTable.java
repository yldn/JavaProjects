import java.lang.reflect.Array;
import java.util.*;

/**
 * Die Klasse DoubleHashTable implementiert eine Hashtabelle, die doppeltes
 * Hashing verwendet.
 *
 * @param <K>
 *            der Typ der Schlüssel, die in der Hashtabelle gespeichert werden
 * @param <V>
 *            der Typ der Werte, die in der Hashtabelle gespeichert werden
 */
public class DoubleHashTable<K, V> {
	DoubleHashable<K> a;
	DoubleHashable<V> b;

	Pair<K, V>[] hashplate;
	private int maxrehash = 0;

	/**
	 * Diese Methode implementiert h(x, i).
	 * 
	 * @param key
	 *            der Schlüssel, der gehasht werden soll
	 * @param i
	 *            der Index, der angibt, der wievielte Hash für den gegebenen
	 *            Schlüssel berechnet werden soll
	 * @return der generierte Hash
	 */
	private int hash(K key, int i) {
		maxrehash = (i > maxrehash) ? maxrehash : i;
		int hash = (int) (a.hash(key) + i * a.hashTick(key)) % hashplate.length;
		return hash;
	}

	/**
	 * Dieser Konstruktor initialisiert die Hashtabelle.
	 * 
	 * @param primeSize
	 *            die Größe 'm' der Hashtabelle; es kann davon ausgegangen werden,
	 *            dass es sich um eine Primzahl handelt.
	 * @param hashableFactory
	 *            Fabrik, die aus einer Größe ein DoubleHashable<K>-Objekt erzeugt.
	 */
	@SuppressWarnings("unchecked")
	public DoubleHashTable(int primeSize, HashableFactory<K> hashableFactory) {
		this.a = hashableFactory.create(primeSize);
		this.hashplate = (Pair<K, V>[]) Array.newInstance(Pair.class, primeSize);
	}

	/**
	 * Diese Methode fügt entsprechend des doppelten Hashens ein Element in die
	 * Hashtabelle ein.
	 * 
	 * @param k
	 *            der Schlüssel des Elements, das eingefügt wird
	 * @param v
	 *            der Wert des Elements, das eingefügt wird
	 * @return 'true' falls das einfügen erfolgreich war, 'false' falls die
	 *         Hashtabelle voll ist.
	 */
	public boolean insert(K k, V v) {
		for (int i = 0; i < hashplate.length; i++) {
			int a = hash(k, i);
			if (hashplate[a] == null) {
				hashplate[a] = new Pair<K, V>(k, v);
				return true;
			}
			if (hashplate[a]._1.equals(k)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Diese Methode sucht ein Element anhand seines Schlüssels in der Hashtabelle
	 * und gibt den zugehörigen Wert zurück, falls der Schlüssel gefunden wurde.
	 * 
	 * @param k
	 *            der Schlüssel des Elements, nach dem gesucht wird
	 * @return der Wert des zugehörigen Elements, sonfern es gefunden wurde
	 */
	public Optional<V> find(K k) {
		for (int i = 0; i < hashplate.length; i++) {
			int a = hash(k, i);
			if (hashplate[a]._1.equals(k))
				return Optional.of(hashplate[a]._2);
		}
		return Optional.empty();
	}

	/**
	 * Diese Methode ermittelt die Anzahl der Kollisionen, also die Anzahl der
	 * Elemente, die nicht an der 'optimalen' Position in die Hashtabelle eingefügt
	 * werden konnten. Die optimale Position ist diejenige Position, die der erste
	 * Aufruf der Hashfunktion (i = 0) bestimmt.
	 * 
	 * @return die Anzahl der Kollisionen
	 */
	public int collisions() {
		int col = 0;
		for (int i = 0; i < hashplate.length; i++) {
			if (hashplate[i] != null) {
				col++;
			} else if (i != hash(hashplate[i]._1, 0)) {
				col++;
			}
		}
		return col;
	}

	/**
	 * Diese Methode berechnet die maximale Anzahl von Aufrufen der Hashfunktion,
	 * die nötig waren, um ein einziges Element in die Hashtabelle einzufügen.
	 * 
	 * @return die berechnete Maximalzahl von Aufrufen
	 */
	public int maxRehashes() {
		return maxrehash;
	}
}
