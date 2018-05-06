/**
 * Die Klasse RingQueue soll eine zirkuläre Warteschlange auf Basis der Klasse
 * {@link DynamicArray} implementieren.
 */
public class RingQueue {
	private DynamicArray dynArr;

	private int size;

	private int from;

	private int to;

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Dieser Konstruktor erzeugt eine neue Ringschlange. Ein leere Ringschlange
	 * habe stets eine Größe von 0, sowie auf 0 gesetzte Objektvariablen to und
	 * from.
	 * 
	 * @param growthFactor
	 *            der Wachstumsfaktor des zugrundeliegenden dynamischen Feldes
	 * @param maxOverhead
	 *            der maximale Overhead des zugrundeliegenden dynamischen Feldes
	 */
	public RingQueue(int growthFactor, int maxOverhead) {
		dynArr = new DynamicArray(growthFactor, maxOverhead);
		size = 0;
		from = 0;
		to = 0;
	}

	/**
	 * Diese Methode reiht ein Element in die Schlange ein.
	 * 
	 * @param value
	 *            der einzufügende Wert
	 */
	public void enqueue(int value) {
		Interval x = null;
		if (!isEmpty()) {
			x = new NonEmptyInterval(from, to);

		} else {
			x = new EmptyInterval();
		}

		x = dynArr.reportUsage(x, size + 1);
		if (!isEmpty()) {
			if(to == dynArr.getInnerLength()-1)
				to-=(dynArr.getInnerLength()==0?0:1);
			
			from = x.getFrom();
			to = x.getTo();
			to = (to + 1) % (dynArr.getInnerLength());
		}
		dynArr.set(to, value);
		size++;
		System.out.println(dynArr.toString());
	}

	/**
	 * Diese Methode entfernt ein Element aus der Warteschlange.
	 * 
	 * @return das entfernte Element
	 */
	public int dequeue() {
		Interval x = null;
		if (!isEmpty()) {
			x = new NonEmptyInterval(from, to);
		} else
			throw new RuntimeException("empty!");

		x = dynArr.reportUsage(x, 0);
		int out = dynArr.get((from + dynArr.getInnerLength() % dynArr.getInnerLength()));
		dynArr.set(from, 0);

		size--;
		from = (from + 1) % (dynArr.getInnerLength() == 0 ? 1 : dynArr.getInnerLength());
		System.out.println(dynArr.toString());

		return out;

	}

	public static void main(String[] args) {
		RingQueue ringqueue = new RingQueue(2, 3); // [], size: 0, from 0, to 0
		ringqueue.enqueue(1); // [1, 0], size: 1, from 0, to 0
		ringqueue.enqueue(9); // [1, 9], size: 2, from 0, to 1
		ringqueue.enqueue(7); // [1, 9, 7, 0, 0, 0], size: 3, from 0, to 2
		ringqueue.dequeue(); // [0, 9, 7, 0, 0, 0], size: 2, from 1, to 2
		ringqueue.dequeue(); // [7, 0], size: 1, from 0, to 0
		ringqueue.enqueue(4); // [7, 4], size: 2, from 0, to 1
		ringqueue.enqueue(8); // [7, 4, 8, 0, 0, 0], size: 3, from 0, to 2
	}
}
