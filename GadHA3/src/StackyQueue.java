/**
 * Die Klasse StackyQueue soll eine Warteschlange auf Basis der Klasse
 * {@link DynamicStack} implementieren. Es soll ausschließlich die Klasse
 * {@link DynamicStack} zur Datenspeicherung verwendet werden.
 */
public class StackyQueue {
	private DynamicStack first;
	private DynamicStack next;

	/**
	 * Diese Methode ermittelt die Länge der Warteschlange.
	 * 
	 * @return die Länge der Warteschlange
	 */
	public int getLength() {
		return (first.getLength() + next.getLength());
	}

	/**
	 * Dieser Kontruktor initialisiert eine neue Schlange.
	 * 
	 * @param growthFactor
	 * @param maxOverhead
	 */
	public StackyQueue(int growthFactor, int maxOverhead) {
		first = new DynamicStack(growthFactor, maxOverhead);
		next = new DynamicStack(growthFactor, maxOverhead);
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		first.pushBack(value);
	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		if (next.getLength() != 0)
			return next.popBack();
		else {
			while (first.getLength() != 0) {
				next.pushBack(first.popBack());
			}
			return next.popBack();
		}
	}
}
